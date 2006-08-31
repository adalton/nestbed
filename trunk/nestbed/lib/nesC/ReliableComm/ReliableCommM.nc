// Copyright (C) 2006 Clemson University 
// Jason O. Hallstrom, Andrew R. Dalton
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
//
// ---------------
// Summary: This file defines a "reliable" implementation of
//          the SendMsg and ReceiveMsg interfaces. The module is
//          intended to serve as a drop-in replacement for 
//		    GenericComm.
// ---------------


includes ReliableCommMConstants;
includes ReliableMessage;
includes QueueEntry;

module ReliableCommM {
	provides {
		interface StdControl as Control;
		interface SendMsg[uint8_t id];
		interface ReceiveMsg[uint8_t id];
	}
	uses {
		interface StdControl as RadioStdControl;
		interface MacControl;
		interface StdControl as GCStdControl;
		interface SendMsg as RealSendMsg;
		interface ReceiveMsg as RealReceiveMsg;
		interface Queue;
		interface LinkMonitor;
		interface Random;
		interface StdControl as TimerStdControl;
		interface Timer as CongestionTimer;
		interface Leds;
	}
}

implementation {
	// the sending queue entry and message buffer
	QueueEntry sendEntry;
	TOS_Msg sendMsg;
	
	// for handling SendMsg.sendDone():
	// -- AM id of the last sent message on SendMsg.send();
	// -- pointer to the last sent message on SendMsg.send();
	// -- flag indicating whether last sent message on SendMsg.send() 
	//    was signaled as done;
	uint8_t lastSendId;
	TOS_MsgPtr lastSendMsg;
	bool lastSendSignaled;

	// flag to track whether processQueue() micro-thread is active
	// *** NOTE: This resolves a **third** state
	// *** machine related bug. We previously checked the
	// *** state of the queue to determine the number of
	// *** existing processors. State interactions violated
	// *** our assumptions. 
	bool queueProcessorActive;

	// the sendDone() signaling task;
	// the queue processing task
	task void handleSendDone();
	task void processQueue();

	command result_t Control.init() {
		result_t result;
		
		// the last send was signaled (vacuously);
		// the queue processor has not been started
		lastSendSignaled = TRUE;
		queueProcessorActive = FALSE;
		
		// initialize the message queue;
		// initialize the link monitor;
		// initialize the random number generator;
		call Queue.init();
		call LinkMonitor.init();
		result = call Random.init();
		
		// dispatch inner StdControl calls
		result = rcombine(result, call RadioStdControl.init());
		result = rcombine(result, call GCStdControl.init());
		result = rcombine(result, call TimerStdControl.init());
		return(result);
	}

	command result_t Control.start() {
		result_t result;
		
		// enable hardware acks
		call MacControl.enableAck();
		
		// dispatch inner StdControl calls
		result = call RadioStdControl.start();
		result = rcombine(result, call GCStdControl.start());
		result = rcombine(result, call TimerStdControl.start());
		return(result);
	}

	command result_t Control.stop() {
		// implementation does not respond
		// to stop() requests
		return(SUCCESS);
	}

	command result_t SendMsg.send[uint8_t id](uint16_t addr, uint8_t length, TOS_MsgPtr msg) {
		QueueEntry entry;
		
		// check signal status of previous send
		if(lastSendSignaled) {
			// check queue space availability
			// *** NOTE: This comparison ensures one free 
			// *** slot in the Queue component to account for
			// *** pending RealSendMsg.sendDone() events. 
			// *** (Removing the subtraction results in 
			// *** Queue overflow -- by 1 element.)
			// !!!a great bug example for a paper!!!
			if(call Queue.length() < (call Queue.capacity()-1)) {

				// record state required to signal sendDone()
				// *** NOTE: There was another state machine related
				// *** bug here. This block used to be out one
				// *** block further. But in times of high load,
				// *** the state machine would be blocked.
				lastSendId = id;
				lastSendMsg = msg;
				lastSendSignaled = FALSE;
				
				// ignore dead links with high probability
				if((call LinkMonitor.isLinkValid(addr)) || ((uint32_t) call Random.rand() <  ((uint32_t) 65535u*DEAD_LINK_RETRY_PROB)/100)) {
					// enqueue the message for later transmission
					entry.addr = addr;
					entry.msg.type = id;
					entry.msg.length = length;
					memcpy(entry.msg.data, msg->data, length);
					entry.attempts = 0;
					call Queue.enqueue(&entry);
			
					// check if the queue processing micro-thread 
					// (i.e., task-based state machine) needs restarting
					if(!queueProcessorActive) {
						queueProcessorActive = TRUE;
						post processQueue();
					}

					// schedule the sendDone() event to be signaled 
					post handleSendDone();
					return(SUCCESS);
				} else {
					// schedule the sendDone() event to be signaled
					post handleSendDone();
					return(SUCCESS);
				}
			} else {
				return(FAIL);
			}
		} else {
			return(FAIL);
		}
	}

	task void handleSendDone() {
		signal SendMsg.sendDone[lastSendId](lastSendMsg, SUCCESS);
		lastSendSignaled = TRUE;
	}

	task void processQueue() {
		// dequeue the next message to be sent
		call Queue.dequeue(&sendEntry);
		memcpy(sendMsg.data, &sendEntry.msg, sizeof(sendEntry.msg));
		if(call RealSendMsg.send(sendEntry.addr, sizeof(sendMsg.data), &sendMsg) == FAIL) {
			// retry (after a congestion delay)
			call Queue.enqueue(&sendEntry);
			call CongestionTimer.start(TIMER_ONE_SHOT, CONGESTION_DELAY);
		} else {
			// a message has been sent successfully;
			// the queue processing micro-thread (scheduler) has terminated
			call Leds.greenToggle();
			queueProcessorActive = FALSE;
		}
	}

	event result_t CongestionTimer.fired() {
		// process the next queue entry
		post processQueue();
		return(SUCCESS);
	}

	event result_t RealSendMsg.sendDone(TOS_MsgPtr msg, result_t success)  {
		if(success) {
			if(msg->ack) {
				call LinkMonitor.registerTransmitSuccess(msg->addr);
			} else {
				sendEntry.attempts++;
			}
		}

		if((!success) || (!msg->ack)) {
			if(sendEntry.attempts < MAX_SEND_ATTEMPTS) {
				call Queue.enqueue(&sendEntry);
			} else {
				call LinkMonitor.registerTransmitFailure(msg->addr);
			}
		}

		// check if the queue processing micro-thread 
		// (i.e., task-based state machine) needs restarting		
		if((call Queue.length() > 0) && (!queueProcessorActive)) {
			queueProcessorActive = TRUE;
			post processQueue();
		}
		return(SUCCESS);
	}

	event TOS_MsgPtr RealReceiveMsg.receive(TOS_MsgPtr msg) {
		ReliableMessage* wrappedData;
		TOS_MsgPtr ret;
		
		// unwrap the received message
		wrappedData = (ReliableMessage*) msg->data;
		msg->type = wrappedData->type;
		msg->length = wrappedData->length;

		// signal the outer receive event
		ret = signal ReceiveMsg.receive[msg->type](msg);
		return(ret);
    }

    default event result_t SendMsg.sendDone[uint8_t id](TOS_MsgPtr msg, result_t success) {
		return SUCCESS;
    }

	default event TOS_MsgPtr ReceiveMsg.receive[uint8_t id](TOS_MsgPtr msg) {
		return msg;
    }
}
