/* $Id$ */
/*
 * Server.java
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
package edu.clemson.cs.nestbed.server;


import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;

import edu.clemson.cs.nestbed.common.management.configuration.TestbedManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTestbedAssignmentManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTypeManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProjectManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProjectDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MessageManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.util.LogOutputStream;
import edu.clemson.cs.nestbed.common.util.ParentClassLoader;

import edu.clemson.cs.nestbed.server.management.configuration.TestbedManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MessageManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteTestbedAssignmentManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteTypeManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProjectManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProjectDeploymentConfigurationManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteDeploymentConfigurationManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramMessageSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramProfilingMessageSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramProfilingSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.util.ShutdownTrigger;


public class Server {
    private final static Log log = LogFactory.getLog(Server.class);

    private static String RMI_BASE_URL;

    private ShutdownTrigger                       shutdownTrigger;

    private ProgramSymbolManager                  programSymbolManager;
    private ProgramProfilingSymbolManager         progProfSymbolManager;
    private ProgramManager                        programManager;
    private MoteDeploymentConfigurationManager    moteDepConfigManager;
    private ProjectDeploymentConfigurationManager projDepConfigManager;
    private ProjectManager                        projectManager;
    private MoteTypeManager                       moteTypeManager;
    private MoteManager                           moteManager;
    private MoteTestbedAssignmentManager          moteTbAssignManager;
    private TestbedManager                        testbedManger;
    private ProgramMessageSymbolManager           progMsgSymbolManager;
    private ProgramProfilingMessageSymbolManager  progProfMsgSymbolMgnr;
    private MessageManager                        messageManager;



    public Server() throws MalformedURLException, RemoteException {
        System.setOut(new PrintStream(new BufferedOutputStream(
                      new LogOutputStream(System.class, Level.WARN)),  true));
        System.setErr(new PrintStream(new BufferedOutputStream(
                      new LogOutputStream(System.class, Level.ERROR)), true));

        log.info("******************************************************\n" +
                 "** NESTBed Server Starting\n" +
                 "******************************************************");
        ParentClassLoader.setParent(Server.class.getClassLoader());

        shutdownTrigger       = new ShutdownTrigger();
        programSymbolManager  = ProgramSymbolManagerImpl.getInstance();
        progProfSymbolManager = ProgramProfilingSymbolManagerImpl.getInstance();
        programManager        = ProgramManagerImpl.getInstance();
        moteDepConfigManager  = MoteDeploymentConfigurationManagerImpl.getInstance();
        projDepConfigManager  = ProjectDeploymentConfigurationManagerImpl.getInstance();
        projectManager        = ProjectManagerImpl.getInstance();
        moteTypeManager       = MoteTypeManagerImpl.getInstance();
        moteManager           = MoteManagerImpl.getInstance();
        moteTbAssignManager   = MoteTestbedAssignmentManagerImpl.getInstance();
        testbedManger         = TestbedManagerImpl.getInstance();
        progMsgSymbolManager  = ProgramMessageSymbolManagerImpl.getInstance();
        progProfMsgSymbolMgnr = ProgramProfilingMessageSymbolManagerImpl.getInstance();
        messageManager        = MessageManagerImpl.getInstance();

        bindRemoteObjects();
    }


    private final void bindRemoteObjects()
                            throws RemoteException, MalformedURLException {

        Naming.rebind(RMI_BASE_URL + "TestbedManager", testbedManger);
        log.debug("TestbedManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "MoteTestbedAssignmentManager",
                      moteTbAssignManager);
        log.debug("MoteTestbedAssignmentManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "MoteManager", moteManager);
        log.debug("MoteManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "MoteTypeManager", moteTypeManager);
        log.debug("MoteTypeManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "ProjectManager", projectManager);
        log.debug("ProjectManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "ProjectDeploymentConfigurationManager",
                      projDepConfigManager);
        log.debug("ProjectDeploymentConfigurationManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "MoteDeploymentConfigurationManager",
                      moteDepConfigManager);
        log.debug("MoteDeploymentConfigurationManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "ProgramManager", programManager);
        log.debug("ProgramManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "ProgramProfilingSymbolManager",
                      progProfSymbolManager);
        log.debug("ProgramProfilingSymbolManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "ProgramSymbolManager",
                      programSymbolManager);
        log.debug("ProgramSymbolManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "ProgramMessageSymbolManager",
                      progMsgSymbolManager);
        log.debug("ProgramMessageSymbolManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "ProgramProfilingMessageSymbolManager",
                      progProfMsgSymbolMgnr);
        log.debug("ProgramProfilingMessageSymbolManager successfully bound.");

        Naming.rebind(RMI_BASE_URL + "MessageManager", messageManager);
        log.debug("MessageManager successfully bound.");
    }


    private static void loadProperties() throws IOException {
        Properties  systemProperties;
        InputStream propertyStream;

        systemProperties = System.getProperties();
        propertyStream   = Server.class.getClassLoader().
                                     getResourceAsStream("server.properties");
        systemProperties.load(propertyStream);
        propertyStream.close();
        
        propertyStream   = Server.class.getClassLoader().
                                     getResourceAsStream("common.properties");
        systemProperties.load(propertyStream);
        propertyStream.close();
    }


    public static void main(String[] args) throws Exception {
        loadProperties();
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        PropertyConfigurator.configure(
                  Server.class.getClassLoader().getResource("serverLog.conf"));

        try {
            Server server = new Server();
        } catch (RemoteException e) {
            log.fatal("Remote Exception occured!", e);
            System.exit(1);
        }
    }
}
