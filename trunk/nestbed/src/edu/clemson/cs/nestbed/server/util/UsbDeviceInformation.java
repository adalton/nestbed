/* $Id:$ */
/*
 * UsbDeviceInformation.java
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


import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UsbDeviceInformation {
    private final static Log    log          =
                                  LogFactory.getLog(UsbDeviceInformation.class);
    private final static String GET_DEV_INFO = "/home/adalton/src/java/nestbed/bin/get_dev_info";

    private String moteSerialID;
    private int    bus;
    private int    device;
    private int    port;


    public UsbDeviceInformation(String moteSerialID) {
        this.moteSerialID = moteSerialID;
        updateInternal();
    }


    public int getBus() {
        return bus;
    }


    public int getDevice() {
        return device;
    }


    public int getPort() {
        return port;
    }


    public void update() {
        updateInternal();
    }


    private void updateInternal() {
        ProcessBuilder processBuilder;
        Process        process;
        int            exitValue;

        try {
            processBuilder = new ProcessBuilder(GET_DEV_INFO, "-s", moteSerialID);
            process        = processBuilder.start();

            process.waitFor();
            exitValue = process.exitValue();

            if (exitValue == 0) {
                Properties p = new Properties();
                p.load(process.getInputStream());

                String busString    = p.getProperty("bus");
                String deviceString = p.getProperty("device");
                String portString   = p.getProperty("port");

                log.debug("busString    = " + busString);
                log.debug("deviceString = " + deviceString);
                log.debug("portString   = " + portString);

                if (        (busString    != null)
                         && (deviceString != null)
                         && (portString   != null) ) {
                    try {
                        bus    = Integer.parseInt(busString);
                        device = Integer.parseInt(deviceString);
                        port   = Integer.parseInt(portString);
                    } catch (NumberFormatException ex) {
                        log.error("Error converting string to int", ex);
                    }
                } else {
                    log.error("Required property not set");
                }
            } else {
                log.error("Unable to get USB device information for " +
                          "moteSerialID: " + moteSerialID);
            }
        } catch (InterruptedException ex) {
            log.error("Process interrupted", ex);
        } catch (IOException ex) {
            log.error("I/O Exception occured while reading device info", ex);
        }
    }
}
