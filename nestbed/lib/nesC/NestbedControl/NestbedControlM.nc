/* $Id$ */
/*
 * NestbedControlM.nc
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006
 * Dependable Systems Research Group
 * Department of Computer Science
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
    provides interface StdControl;
    uses     interface Leds;
    uses     interface ReceiveMsg    as ReceivePower;
    uses     interface CC2420Control as Radio;
    uses     interface Reset;
}

implementation {
    command result_t StdControl.init() {
        call Leds.init();
        return SUCCESS;
    }


    event TOS_MsgPtr ReceivePower.receive(TOS_MsgPtr msgPtr) {
        ControlMessage* controlMessage = (ControlMessage*) msgPtr->data;

        switch (controlMessage->cmd) {
        case SET_POWER:
            call Radio.SetRFPower(controlMessage->arg);
            call Leds.set(controlMessage->arg);
            break;

        case RESET:
            call Reset.reset();
            // Should not reach this point
            break;

        default:
            // Unknown command
            call Leds.set(7);
            break;
        }

        return msgPtr;
    }


    command result_t StdControl.start() { return SUCCESS; }
    command result_t StdControl.stop()  { return SUCCESS; }
}
