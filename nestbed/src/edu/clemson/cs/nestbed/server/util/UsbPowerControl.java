/* $Id$ */
/*
 * UsbPowerControl.java
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
package edu.clemson.cs.nestbed.server.util;


import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UsbPowerControl {
    private final static Log    log          =
                                  LogFactory.getLog(UsbPowerControl.class);
    private final static String SET_DEV_POWER;
    
    static {
        String property    = "nestbed.bin.set_dev_power";
        String setDevPower = System.getProperty(property);

        if ( (setDevPower == null) || !(new File(setDevPower).exists()) ) {
            log.fatal("Property '" + property + "' is not set " +
                      " or file does not exist: " + setDevPower);
            System.exit(1);
        }
        SET_DEV_POWER = setDevPower;
        log.info(property + " = " + SET_DEV_POWER);
    }


    public static void powerOnDevice(int bus, int device, int port) {
        setPowerState(bus, device, port, 1);
    }


    public static void powerOffDevice(int bus, int device, int port) {
        setPowerState(bus, device, port, 0);
    }


    private static void setPowerState(int bus, int device, int port, int state) {
        ProcessBuilder processBuilder;
        Process        process;
        int            exitValue;

        try {
            //processBuilder = new ProcessBuilder(SET_DEV_POWER,
            processBuilder = new ProcessBuilder("/usr/bin/sudo", SET_DEV_POWER,
                                                "-b", Integer.toString(bus),
                                                "-d", Integer.toString(device),
                                                "-P", Integer.toString(port),
                                                "-p", Integer.toString(state));
            process        = processBuilder.start();

            process.waitFor();
            exitValue = process.exitValue();

            if (exitValue == 0) {
                log.debug("Set power state successfully");
            } else {
                log.error("Unable to set power state");
            }
        } catch (InterruptedException ex) {
            log.error("Process interrupted", ex);
        } catch (IOException ex) {
            log.error("I/O Exception occured while reading device info", ex);
        }
    }
}
