/* $Id$ */
/*
 * TraceRetrieverC.nc
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
#include <Storage.h>
#include "TraceRecorder.h"

generic configuration TraceRetrieverC(volume_id_t volume_id) {
    uses interface Boot;
}
implementation {
    components TraceRetrieverP as App;
    components LedsC;
    components new LogStorageC(volume_id, FALSE);
    components SerialActiveMessageC;
    components new SerialAMSenderC(AM_TRACEENTRY)     as UARTSendData;
    components new SerialAMSenderC(AM_TRACEMESSAGE)   as UARTSendAck;
    components new SerialAMReceiverC(AM_TRACEMESSAGE);

    Boot             =  App.Boot;
    App.Leds         -> LedsC.Leds;
    App.LogRead      -> LogStorageC.LogRead;
    App.AMControl    -> SerialActiveMessageC.SplitControl;
    App.UARTSendData -> UARTSendData.AMSend;
    App.UARTSendAck  -> UARTSendAck.AMSend;
    App.UARTReceive  -> SerialAMReceiverC.Receive;
    App.Packet       -> UARTSendData.Packet;
}
