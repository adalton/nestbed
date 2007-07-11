/* $Id$ */
/*
 * NestbedControlM.nc
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * School of Computing
 * Clemson University
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
module NestbedControlM {
    uses interface Boot;
    uses interface Leds;
    uses interface SplitControl  as AMControl;
    uses interface Receive       as UARTReceive;
    uses interface Timer<TMilli> as ResetTimer;
    uses interface CC2420Config  as Radio;
}

implementation {
    event message_t* UARTReceive.receive(message_t* bufPtr, void* payload,
                                         uint8_t length) {
        if (length == sizeof(ControlMessage)) {
            ControlMessage* controlMessage = (ControlMessage*) payload;

            switch (controlMessage->cmd) {
            case SET_POWER:
                call Radio.setRFPower(controlMessage->arg);
                call Leds.set(controlMessage->arg);
                break;

            case RESET:
                // We can't just reset here -- the Java side of the house is
                // expecting an ACK.  If we reset now, the ACK will never
                // arrive and the Java stuff will resend the message (which
                // will reset the device again!).
                call ResetTimer.startOneShot(250);
                break;

            default:
                // Unknown command
                call Leds.set(7);
                break;
            }
        }

        return bufPtr;
    }


    event void ResetTimer.fired() {
        WDTCTL = 0;
        // Should not reach this point
    }

    event void Radio.syncDone(error_t error) {
        // Do nothing
    }


    event void Boot.booted() {
        call AMControl.start();
    }


    event void AMControl.startDone(error_t err) {
        if (err != SUCCESS) {
            call AMControl.start();
        }
    }


    event void AMControl.stopDone(error_t err) {
        // Do nothing
    }
}
