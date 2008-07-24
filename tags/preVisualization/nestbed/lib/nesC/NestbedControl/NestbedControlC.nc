/* $Id$ */
/*
 * NestbedControlC.nc
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
#include "NestbedControl.h"

configuration NestbedControlC {
    uses interface Boot;
}

implementation {
    //components LedsC;
    components NoLedsC as LedsC;
    components new SerialAMReceiverC(AM_CONTROLMESSAGE);
    components SerialActiveMessageC;
    components NestbedControlM;
    components new TimerMilliC() as ResetTimer;
    components CC2420ControlC;


    Boot                        =  NestbedControlM.Boot;

    NestbedControlM.Leds        -> LedsC.Leds;
    NestbedControlM.AMControl   -> SerialActiveMessageC.SplitControl;
    NestbedControlM.UARTReceive -> SerialAMReceiverC.Receive;
    NestbedControlM.ResetTimer  -> ResetTimer.Timer;
    NestbedControlM.Radio       -> CC2420ControlC.CC2420Config;
}
