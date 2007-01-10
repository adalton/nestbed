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


import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


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

        profilingSymbolMgr.addRemoteObserver(
                                   new ProgramProfilingSymbolManagerObserver());

        for (ProgramProfilingSymbol i : profilingSymbols) {
            ProgramSymbol programSymbol;
            programSymbol = symbolManager.getProgramSymbol(
                                                i.getProgramSymbolID());
            addEntry(new ProgramProfilingSymbolEntry(i, programSymbol));
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


    private class ProgramProfilingSymbolManagerObserver
                                                  extends    UnicastRemoteObject
                                                  implements RemoteObserver {

        public ProgramProfilingSymbolManagerObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                        throws RemoteException {
            ProgramProfilingSymbolManager.Message message;
            ProgramProfilingSymbol                profilingSymbol;
            ProgramSymbol                         programSymbol;

            message         = (ProgramProfilingSymbolManager.Message) msg;
            profilingSymbol = (ProgramProfilingSymbol)                arg;
            programSymbol   = symbolManager.getProgramSymbol(
                                          profilingSymbol.getProgramSymbolID());

            switch (message) {
                case NEW_SYMBOL:
                    addEntry(new ProgramProfilingSymbolEntry(profilingSymbol,
                                                             programSymbol));
                    profilingSymbols.add(profilingSymbol);
                    break;

                case DELETE_SYMBOL:
                    String name  = programSymbol.getModule() + "." +
                                   programSymbol.getSymbol();
                    Entry  entry = getEntryWithName(name);

                    if (entry instanceof ProgramProfilingSymbolEntry) {
                        ProgramProfilingSymbolEntry ppsEntry;
                        ppsEntry = (ProgramProfilingSymbolEntry) entry;

                        removeEntry(ppsEntry);
                        // TODO:  ppsEntry.destroy();
                        profilingSymbols.remove(profilingSymbol);
                    } else {
                        System.err.printf("%s is not a " +
                                          "ProgramProfilingSymbolEntry\n",
                                          name);
                    }
                    break;

                default:
                    System.err.println("Unknown message: " + message);
                    break;
            }
        }
    }


    private class ProgramProfilingSymbolEntry extends FileEntry {
        private ProgramProfilingSymbol programProfilingSymbol;
        private ProgramSymbol          programSymbol;


        public ProgramProfilingSymbolEntry(ProgramProfilingSymbol ppSymbol,
                                           ProgramSymbol          pSymbol) {
            super(pSymbol.getModule() + "." + pSymbol.getSymbol());

            this.programProfilingSymbol = ppSymbol;
            this.programSymbol          = pSymbol;
        }


        public String getFileContents() throws Exception {
            StringBuffer s = new StringBuffer(100);

            s.append("Module: ").append(programSymbol.getModule()).append("\n");
            s.append("Symbol: ").append(programSymbol.getSymbol()).append("\n");

            return s.toString();
        }


        public ProgramProfilingSymbol getProgramProfilingSymbol() {
            return programProfilingSymbol;
        }
    }


    private class RmCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = SymbolProfilingLevel.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.out.printf("usage:  %s <name>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String name  = args[1];
            Entry  entry = getEntryWithName(name);

            if (entry instanceof ProgramProfilingSymbolEntry) {
                ProgramProfilingSymbolEntry ppsEntry;
                ProgramProfilingSymbol      ppSymbol;

                ppsEntry = (ProgramProfilingSymbolEntry) entry;
                ppSymbol = ppsEntry.getProgramProfilingSymbol();

                profilingSymbolMgr.deleteProfilingSymbol(ppSymbol.getID());
            } else {
                System.err.printf("%s not a ProgramSymbolEntry\n", name);
                Variables.set("status", "2");
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Removes the specified symbol from the profiling list";
        }
    }
}
