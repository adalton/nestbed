/* $Id$ */
/*
 * MemoryProfilerC.nc
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2007
 * Dependable Systems Research Group
 * School of Computing
 * Clemson University
 * Andrew R. Dalton and Jason O. Hallstrom
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
 */
#include "MemoryProfiler.h"

configuration MemoryProfilerC {
    uses interface Boot;
}
implementation {
    components MemoryProfilerM;
    components NoLedsC as LedsC;
    components new SerialAMSenderC(AM_MEMORYPROFILINGMESSAGE);
    components new SerialAMReceiverC(AM_MEMORYPROFILINGMESSAGE);
    components SerialActiveMessageC;
    components MemoryInterfaceM;

    Boot                            =  MemoryProfilerM.Boot;

    MemoryProfilerM.UARTReceive     -> SerialAMReceiverC.Receive;
    MemoryProfilerM.UARTSend        -> SerialAMSenderC.AMSend;
    MemoryProfilerM.AMControl       -> SerialActiveMessageC.SplitControl;
    MemoryProfilerM.Leds            -> LedsC.Leds;
    MemoryProfilerM.Packet          -> SerialAMSenderC.Packet;
    MemoryProfilerM.MemoryInterface -> MemoryInterfaceM.MemoryInterface;
}
