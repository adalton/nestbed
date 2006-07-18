/* $Id$ */
/*
 * SymbolProfilingLevel.java
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

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class SymbolProfilingLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;
    private ProgramSymbolManager           symbolManager;
    private ProgramProfilingSymbolManager  profilingSymbolMgr;
    private List<ProgramProfilingSymbol>   profilingSymbols;


    public SymbolProfilingLevel(Testbed testbed, Project project,
                                ProjectDeploymentConfiguration config,
                                Level parent) throws Exception {
        super("SymbolProfiling", parent);
        lookupRemoteManagers();

        this.testbed          = testbed;
        this.project          = project;
        this.config           = config;
        this.profilingSymbols = profilingSymbolMgr.getProgramProfilingSymbols(
                                                               config.getID());

        for (ProgramProfilingSymbol i : profilingSymbols) {
            ProgramSymbol programSymbol;
            programSymbol = symbolManager.getProgramSymbol(
                                                i.getProgramSymbolID());
            addEntry(new Entry(programSymbol.getModule() + "." +
                               programSymbol.getSymbol()));
        }


        addCommand("rm", new RmCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        profilingSymbolMgr = (ProgramProfilingSymbolManager)
                            Naming.lookup(RMI_BASE_URL +
                                         "ProgramProfilingSymbolManager");

        symbolManager      = (ProgramSymbolManager)
                            Naming.lookup(RMI_BASE_URL +
                                         "ProgramSymbolManager");
    }


    private class RmCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.out.println("RmCommand:  TODO");
        }


        public String getHelpText() {
            return "Removes the specified symbol from the profiling list";
        }
    }
}
