/* $Id$ */
/*
 * MessageProfilingLevel.java
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
package edu.clemson.cs.nestbed.client.cli;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingMessageSymbol;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class MessageProfilingLevel extends Level {
    private Testbed                               testbed;
    private Project                               project;
    private ProjectDeploymentConfiguration        config;
    private ProgramMessageSymbolManager           symbolManager;
    private ProgramProfilingMessageSymbolManager  profilingSymbolMgr;
    private List<ProgramProfilingMessageSymbol>   profilingMessages;


    public MessageProfilingLevel(Testbed testbed, Project project,
                                ProjectDeploymentConfiguration config,
                                Level parent) throws Exception {
        super("MessageProfiling", parent);
        lookupRemoteManagers();

        this.testbed           = testbed;
        this.project           = project;
        this.config            = config;
        this.profilingMessages = profilingSymbolMgr.
                            getProgramProfilingMessageSymbols(config.getID());

        for (ProgramProfilingMessageSymbol i : profilingMessages) {
            ProgramMessageSymbol messageSymbol;
            messageSymbol = symbolManager.getProgramMessageSymbol(
                                                i.getProgramMessageSymbolID());
            addEntry(new MessageSymbolEntry(messageSymbol));
        }


        addCommand("rm", new RmCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        profilingSymbolMgr = (ProgramProfilingMessageSymbolManager)
                            Naming.lookup(RMI_BASE_URL +
                                         "ProgramProfilingMessageSymbolManager");

        symbolManager      = (ProgramMessageSymbolManager)
                            Naming.lookup(RMI_BASE_URL +
                                         "ProgramMessageSymbolManager");
    }


    private class MessageSymbolEntry extends FileEntry {
        private ProgramMessageSymbol programMessageSymbol;

        public MessageSymbolEntry(ProgramMessageSymbol programMessageSymbol) {
            super(programMessageSymbol.getName());

            this.programMessageSymbol = programMessageSymbol;
        }


        public String getFileContents() throws Exception {
            StringBuffer s = new StringBuffer(100);

            s.append("Name: ").append(programMessageSymbol.getName());
            s.append("\n");

            return s.toString();
        }
    }


    private class RmCommand implements Command {
        public Level execute(String[] args) throws Exception {
            System.out.println("RmCommand:  TODO");
            Variables.set("status", "1");
            return MessageProfilingLevel.this;
        }


        public String getHelpText() {
            return "Removes the specified message from the profiling list";
        }
    }
}
