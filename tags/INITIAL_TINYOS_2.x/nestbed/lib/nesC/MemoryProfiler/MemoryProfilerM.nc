/* $Id$ */
/*
 * MemoryProfilerM.nc
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

module MemoryProfilerM {
    uses interface Boot;
    uses interface Leds;
    uses interface MemoryInterface;
    uses interface SplitControl    as AMControl;
    uses interface AMSend          as UARTSend;
    uses interface Receive         as UARTReceive;
    uses interface Packet;
}
implementation {
    message_t packet;
    bool      sending;


    event message_t* UARTReceive.receive(message_t* bufPtr, void* payload,
                                         uint8_t    length) {

        if (length == sizeof(MemoryProfilingMessage)) {
            MemoryProfilingMessage* mpmIn = (MemoryProfilingMessage*) payload;

            if (mpmIn->read && !sending) {
                MemoryProfilingMessage* mpmOut = call Packet.getPayload(&packet,
                                                                        NULL);
                call Leds.led0Toggle();
                memcpy(mpmOut, mpmIn, sizeof(MemoryProfilingMessage));
                mpmOut->value =
                        call MemoryInterface.readFromAddress(mpmIn->address,
                                                             mpmIn->size,
                                                             mpmIn->offset);

                sending = (call UARTSend.send(AM_BROADCAST_ADDR, &packet,
                                    sizeof(MemoryProfilingMessage)) == SUCCESS);
            } else if (!mpmIn->read) {
                call Leds.led1Toggle();
                call MemoryInterface.writeToAddress(mpmIn->address,
                                                    mpmIn->size,
                                                    mpmIn->offset,
                                                    mpmIn->value);
            }
        }

        return bufPtr;
    }


    event void UARTSend.sendDone(message_t* bufPtr, error_t error) {
        sending = FALSE;
    }


    event void Boot.booted() {
        sending = TRUE;
        call AMControl.start();
    }


    event void AMControl.startDone(error_t err) {
        if (err == SUCCESS) {
            sending = FALSE;
        } else {
            call AMControl.start();
        }
    }


    event void AMControl.stopDone(error_t err) {
        // Do nothing
    }
}
