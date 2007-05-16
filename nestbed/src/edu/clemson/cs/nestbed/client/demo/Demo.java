/* $Id:$ */
/*
 * Demo.java
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * Department of Computer Science
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
package edu.clemson.cs.nestbed.client.demo;


import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.util.List;
import java.util.Properties;

import edu.clemson.cs.nestbed.common.management.configuration.TestbedManager;
import edu.clemson.cs.nestbed.common.model.Testbed;


public class Demo {
    private TestbedManager testbedManager;
    private String         RMI_BASE_URL;


    public Demo() throws Exception {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");

        lookupRemoteManagers();

        List<Testbed> testbeds = testbedManager.getTestbedList();

        for (Testbed i : testbeds) {
            System.out.printf("Name: %-30s Description: %s\n", i.getName(),
                              i.getDescription());
        }
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        testbedManager = (TestbedManager)
                    Naming.lookup(RMI_BASE_URL + "TestbedManager");
    }


    public static void main(String[] args) throws Exception {
        loadProperties();

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        Demo demo = new Demo();
    }


    private static void loadProperties() throws IOException {
        Properties  systemProperties;
        InputStream propertyStream;

        systemProperties = System.getProperties();
        propertyStream   = Demo.class.getClassLoader().
                                     getResourceAsStream("common.properties");
        systemProperties.load(propertyStream);
        propertyStream.close();
    }
}
