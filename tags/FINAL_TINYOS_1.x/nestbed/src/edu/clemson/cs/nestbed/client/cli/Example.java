/* $Id$ */
/*
 * Example.java
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
package edu.clemson.cs.nestbed.client.cli;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.rmi.RMISecurityManager;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import edu.clemson.cs.nestbed.common.util.ParentClassLoader;


public class Example {
    private static void loadProperties() throws IOException {
        Properties  systemProperties;
        InputStream propertyStream;

        systemProperties = System.getProperties();
        propertyStream   = Example.class.getClassLoader().
                                     getResourceAsStream("common.properties");
        systemProperties.load(propertyStream);
        propertyStream.close();
    }


    public static void main(String[] args) throws Exception {
        loadProperties();

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        PropertyConfigurator.configure(
                  Example.class.getClassLoader().getResource(
                                                "clientLog.conf"));
        ParentClassLoader.setParent(Example.class.getClassLoader());


        if (args.length == 0) {
            Level.setBufferedReader(new BufferedReader(
                                        new InputStreamReader(System.in)));
            Level.setInteractive(true);
        } else {
            Level.setBufferedReader(new BufferedReader(
                                        new InputStreamReader(
                                            new FileInputStream(args[0]))));
            Level.setInteractive(false);
        }

        Level level = new RootLevel();
        while ( (level = level.process()) != null);
    }
}
