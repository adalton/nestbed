/* $Id$ */
/*
 * ProgramSymbolLevel.java
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


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class ProgramSymbolLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;
    private Program                        program;
    private ProgramSymbolManager           symbolManager;
    private List<ProgramSymbol>            programSymbols;


    public ProgramSymbolLevel(Testbed                        testbed,
                              Project                        project,
                              ProjectDeploymentConfiguration config,
                              Program                        program,
                              Level                          parent)
                                                            throws Exception {
        super("Symbols", parent);
        lookupRemoteManagers();

        this.testbed        = testbed;
        this.project        = project;
        this.config         = config;
        this.program        = program;
        this.programSymbols = symbolManager.getProgramSymbols(program.getID());


        Map<String,  List<ProgramSymbol>> moduleSymbolMap;
        moduleSymbolMap = new HashMap<String, List<ProgramSymbol>>();

        for (ProgramSymbol i : programSymbols) {
            List<ProgramSymbol> list = moduleSymbolMap.get(i.getModule());

            if (list == null) {
                list = new ArrayList<ProgramSymbol>();
                moduleSymbolMap.put(i.getModule(), list);
            }
            list.add(i);
        }

        for (String i : moduleSymbolMap.keySet()) {
            addEntry(new ModuleLevelEntry(i, moduleSymbolMap.get(i)));
        }
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        symbolManager = (ProgramSymbolManager)
                        Naming.lookup(RMI_BASE_URL + "ProgramSymbolManager");
    }


    private class ModuleLevelEntry extends LevelEntry {
        private String              moduleName;
        private List<ProgramSymbol> programSymbols;


        public ModuleLevelEntry(String              moduleName,
                                List<ProgramSymbol> programSymbols) {
            super(moduleName);
            this.moduleName     = moduleName;
            this.programSymbols = programSymbols;
        }


        public Level getLevel() throws Exception {
            return new ModuleLevel(testbed, project,    config,
                                   program, moduleName, programSymbols,
                                   ProgramSymbolLevel.this);
        }
    }
}
