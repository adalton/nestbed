/* $Id$ */
/*
 * Client.java
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
package edu.clemson.cs.nestbed.client;


import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.RMISecurityManager;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;

import edu.clemson.cs.nestbed.client.gui.TestbedManagerFrame;
import edu.clemson.cs.nestbed.common.util.ParentClassLoader;
import edu.clemson.cs.nestbed.common.util.LogOutputStream;
import edu.clemson.cs.nestbed.common.util.VariableProperties;
import edu.clemson.cs.nestbed.common.util.Version;


public class Client {
    private final static Log log = LogFactory.getLog(Client.class);


    private static void loadProperties() throws IOException {
        Properties  systemProperties;
        InputStream propertyStream;

        // Wrap the system properties with our variable-expanding version
        System.setProperties(new VariableProperties(System.getProperties()));

        systemProperties = System.getProperties();
        propertyStream   = Client.class.getClassLoader().
                                     getResourceAsStream("common.properties");
        systemProperties.load(propertyStream);
        propertyStream.close();
    }

    public static void main(String[] args) throws Exception {
        /*
        System.setOut(new PrintStream(new BufferedOutputStream(
                      new LogOutputStream(System.class, Level.WARN)),  true));
        System.setErr(new PrintStream(new BufferedOutputStream(
                      new LogOutputStream(System.class, Level.ERROR)), true));
                      */

        loadProperties();

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        PropertyConfigurator.configure(
                  Client.class.getClassLoader().getResource(
                                                "clientLog.conf"));

        log.info("******************************************************\n" +
                 "** NESTbed Client Starting\n" +
                 "******************************************************");
        log.info("Version:  " + Version.VERSION);

        log.debug("Class Loader:  " + Client.class.getClassLoader());
        ParentClassLoader.setParent(Client.class.getClassLoader());

        TestbedManagerFrame mainWindow = new TestbedManagerFrame();
        mainWindow.setVisible(true);
    }
}
