/* $Id$ */
/*
 * ShutdownTrigger.java
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2006-2007
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
package edu.clemson.cs.nestbed.server.util;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ShutdownTrigger {
    private final static Log log  = LogFactory.getLog(ShutdownTrigger.class);
    private final static int PORT = 24482;

    private static class ShutdownTriggerThread implements Runnable {
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                Socket       socket       = serverSocket.accept();

                log.fatal("Shutdown trigger tripped by connection from " + socket);
                System.exit(0);
            } catch (IOException ex) {
                log.error("I/O Exception while trying to establish server socket.", ex);
            }
        }
    }

    public ShutdownTrigger() {
        Thread t = new Thread(new ShutdownTriggerThread());
        t.start();
    }
}
