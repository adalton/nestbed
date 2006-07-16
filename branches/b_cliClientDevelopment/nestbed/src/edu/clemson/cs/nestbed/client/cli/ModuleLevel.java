/* $Id:$ */
/*
 * ModuleLevel.java
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class ModuleLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;
    private Program                        program;
    //private ProgramSymbolManager           symbolManager;
    private List<ProgramSymbol>            programSymbols;


    public ModuleLevel(Testbed                        testbed,
                       Project                        project,
                       ProjectDeploymentConfiguration config,
                       Program                        program,
                       String                         moduleName,
                       List<ProgramSymbol>            programSymbols,
                       Level                          parent)
                                                            throws Exception {
        super(moduleName, parent);
        //lookupRemoteManagers();

        this.testbed        = testbed;
        this.project        = project;
        this.config         = config;
        this.program        = program;
        this.programSymbols = programSymbols;

        for (ProgramSymbol i : programSymbols) {
            addEntry(new Entry(i.getSymbol()));
        }

        addCommand("profile", new ProfileCommand());
    }


    private class ProfileCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.out.println("ProfileCommand:  TODO");
        }


        public String getHelpText() {
            return "Selects a Program Symbol to be profiled";
        }
    }


//    private final void lookupRemoteManagers() throws RemoteException,
//                                                     NotBoundException,
//                                                     MalformedURLException {
//        symbolManager = (ProgramSymbolManager)
//                        Naming.lookup(RMI_BASE_URL + "ProgramSymbolManager");
//    }
}
