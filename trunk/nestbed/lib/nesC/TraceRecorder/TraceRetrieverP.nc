/* $Id$ */
/*
 * TraceRetrieverP.nc
 *
 * nesC Analysis and Instrumentation Framework
 *
 * Copyright (C) 2007
 * Dependable Systems Research Group
 * School of Computing
 * Clemson University
 * Andrew R. Dalton and Jason O. Hallstrom
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the
 *
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301, USA.
 *
 */
#include "TraceRecorder.h"

module TraceRetrieverP {
    uses interface Boot;
    uses interface Leds;
    uses interface LogRead;
    uses interface SplitControl  as AMControl;
    uses interface AMSend        as UARTSendData;
    uses interface AMSend        as UARTSendAck;
    uses interface Receive       as UARTReceive;
    uses interface Packet;
}
implementation {
    inline void readNextLog();
    inline void sendLogData();


    TraceLogEntry traceLogEntry;
    TraceEntry*   logEntry;
    bool          reading;
    uint16_t      logIndex;
    message_t     ack;
    message_t     logData;


    event void Boot.booted() {
        reading  = TRUE;
        logEntry = (TraceEntry*) call Packet.getPayload(&logData, sizeof(TraceEntry));

        call AMControl.start();
    }


    event void AMControl.startDone(error_t err) {
        if (err == SUCCESS) {
            reading = FALSE;
        } else {
            call AMControl.start();
        }
    }


    event message_t* UARTReceive.receive(message_t* bufPtr, void* payload,
                                         uint8_t    length) {
        if (!reading) {
            readNextLog();
        }
        return bufPtr;
    }


    inline void readNextLog() {
        reading = (call LogRead.read(&traceLogEntry,
                                     sizeof(TraceLogEntry)) == SUCCESS);
        if (!reading) {
            call Leds.led0On();
        }
    }


    event void LogRead.readDone(void* buf, storage_len_t length, error_t err) {
        if (err == SUCCESS) {
            if (length == sizeof(TraceLogEntry)) {
                logIndex = 0;
                sendLogData();
            } else {
                call UARTSendAck.send(AM_BROADCAST_ADDR, &ack, sizeof(TraceMessage));
            }
        } else {
            call Leds.led0On();
        }
    }


    inline void sendLogData() {
        memcpy(logEntry, &traceLogEntry.entries[logIndex], sizeof(TraceEntry));
        call UARTSendData.send(AM_BROADCAST_ADDR, &logData, sizeof(TraceEntry));
    }


    event void UARTSendData.sendDone(message_t* bufPtr, error_t error) {
        logIndex = logIndex + 1;

        if (logIndex < ENTRIES_PER_LOG) {
            sendLogData();
        } else {
            readNextLog();
        }
    }


    event void UARTSendAck.sendDone(message_t* bufPtr, error_t error) {
        reading = FALSE;
        call Leds.led1Toggle();
    }


    event void LogRead.seekDone(error_t err)   { }
    event void AMControl.stopDone(error_t err) { }
}
