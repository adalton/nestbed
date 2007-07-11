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
// Summary: This file defines a partially wired implementation
//          of ReliableCommM.
// ---------------


includes ReliableCommMConstants;

configuration ReliableCommC {
	provides {
		interface StdControl as Control;
		interface SendMsg[uint8_t id];
		interface ReceiveMsg[uint8_t id];
	}
}

implementation 
{
	components ReliableCommM, CC2420RadioC, GenericComm, QueueM, LinkMonitorM, RandomLFSR, TimerM;
    //components LedsC  as LedsImpl;
    components NoLeds as LedsImpl;
		
	Control = ReliableCommM.Control;
	SendMsg = ReliableCommM.SendMsg;
	ReceiveMsg = ReliableCommM.ReceiveMsg;

	ReliableCommM.RadioStdControl -> CC2420RadioC.StdControl;
	ReliableCommM.MacControl -> CC2420RadioC.MacControl;
	ReliableCommM.GCStdControl -> GenericComm.Control;
	ReliableCommM.RealSendMsg -> GenericComm.SendMsg[AM_RELIABLEMESSAGE];
	ReliableCommM.RealReceiveMsg -> GenericComm.ReceiveMsg[AM_RELIABLEMESSAGE];
	ReliableCommM.Queue -> QueueM.Queue;
	ReliableCommM.LinkMonitor -> LinkMonitorM.LinkMonitor;
	ReliableCommM.Random -> RandomLFSR.Random;
	ReliableCommM.TimerStdControl -> TimerM.StdControl;
	ReliableCommM.CongestionTimer -> TimerM.Timer[unique("Timer")];
	ReliableCommM.Leds -> LedsImpl.Leds;
}
