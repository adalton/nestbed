/* $Id$ */
/*
 * MoteSymbolProfilingLevel.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.management.profiling.NucleusManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;



class MoteSymbolProfilingLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;
    private MoteTestbedAssignment          mtbAssignment;
    private MoteState[][]                  moteState;
    private Mote                           mote;
    private MoteType                       moteType;
    private MoteDeploymentConfiguration    mdConfig;
    private Program                        program;
    private ProgramProfilingSymbolManager  profSymMgr;
    private List<ProgramProfilingSymbol>   profilingSymbols;
    private ProgramSymbolManager           progSymMgr;
    private NucleusManager                 nucleusManager;
    private Map<String, Integer>           nameValueMap;


    public MoteSymbolProfilingLevel(Testbed                        testbed,
                                    Project                        project,
                                    ProjectDeploymentConfiguration config,
                                    MoteTestbedAssignment         mtbAssignment,
                                    MoteState[][]                  moteState,
                                    Mote                           mote,
                                    MoteType                       moteType,
                                    MoteDeploymentConfiguration    mdConfig,
                                    Program                        program,
                                    Level                          parent)
                                                            throws Exception {

        super("ProfilingSymbols", parent);
        lookupRemoteManagers();

        this.testbed       = testbed;
        this.project       = project;
        this.config        = config;
        this.mtbAssignment = mtbAssignment;
        this.moteState     = moteState;
        this.mote          = mote;
        this.moteType      = moteType;
        this.mdConfig      = mdConfig;
        this.program       = program;
        this.nameValueMap  = new HashMap<String, Integer>();

        if (program != null) {
            this.profilingSymbols = profSymMgr.getProgramProfilingSymbols(
                                               config.getID(), program.getID());


            for (ProgramProfilingSymbol i : profilingSymbols) {
                ProgramSymbol programSymbol = progSymMgr.getProgramSymbol(
                                                        i.getProgramSymbolID());

                addEntry(new ProgramProfilingSymbolEntry(i, programSymbol));
            }
        }


        addCommand("query", new QueryCommand());
        addCommand("write", new SetCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        profSymMgr     = (ProgramProfilingSymbolManager)
                            Naming.lookup(RMI_BASE_URL +
                                          "ProgramProfilingSymbolManager");

        progSymMgr     = (ProgramSymbolManager)
                            Naming.lookup(RMI_BASE_URL +
                                          "ProgramSymbolManager");

        nucleusManager = (NucleusManager)
                            Naming.lookup(RMI_BASE_URL +
                                          "NucleusManager");
    }


    private class ProgramProfilingSymbolEntry extends FileEntry {
        private ProgramProfilingSymbol programProfilingSymbol;
        private ProgramSymbol          programSymbol;


        public ProgramProfilingSymbolEntry(ProgramProfilingSymbol pps,
                                           ProgramSymbol          ps) {
            super(ps.getModule() + "." + ps.getSymbol());

            this.programProfilingSymbol = pps;
            this.programSymbol          = ps;
        }


        public String getFileContents() throws Exception {
            String  name          = getName();
            Integer integerValue = nameValueMap.get(name);
            int     value;
            String  contents;
            
            if (integerValue != null) {
                value = nameValueMap.get(name);
                contents = getName() + " = " + value;
            } else {
                contents = getName() + " = " + "<unknown>";
            }

            return contents;
        }


        public ProgramProfilingSymbol getProgramProfilingSymbol() {
            return programProfilingSymbol;
        }


        public ProgramSymbol getProgramSymbol() {
            return programSymbol;
        }
    }


    private class QueryCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = MoteSymbolProfilingLevel.this;

            try {
                Variables.set("status", "0");

                if (args.length != 2) {
                    System.out.printf("usage:  %s <symbol>\n", args[0]);
                    Variables.set("status", "1");
                    return nextLevel;
                }

                String name = args[1];
                Entry  entry = getEntryWithName(name);

                if (entry instanceof ProgramProfilingSymbolEntry) {
                    ProgramProfilingSymbolEntry ppsEntry;
                    int                         profilingSymbolID;
                    String                      programSourcePath;
                    String                      tosPlatform;
                    String                      moteSerialID;
                    int                         value;

                    ppsEntry          = (ProgramProfilingSymbolEntry) entry;
                    profilingSymbolID = ppsEntry.getProgramProfilingSymbol()
                                                                    .getID();
                    programSourcePath = program.getSourcePath();
                    tosPlatform       = moteType.getTosPlatform();
                    moteSerialID      = mote.getMoteSerialID();

                    value             = nucleusManager.querySymbol(
                                                            profilingSymbolID,
                                                            programSourcePath,
                                                            tosPlatform,
                                                            moteSerialID);

                    System.out.printf("%s = %d\n", name, value);
                    nameValueMap.put(name, value);
                } else {
                    System.out.printf("Unknown symbol: %s\n", name);
                    Variables.set("status", "2");
                }
            } catch (RemoteException ex) {
                Variables.set("status", "3");
                System.out.println("Failed to query symbol, reason: " +
                                   ex.getMessage());
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Query the value of the specified symbol";
        }
    }


    private class SetCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = MoteSymbolProfilingLevel.this;

            try {
                Variables.set("status", "0");

                if (args.length != 3) {
                    System.err.printf("usage:  %s <symbol> <value>\n", args[0]);
                    Variables.set("status", "1");
                    return nextLevel;
                }

                String name  = args[1];
                int    value;
                
                try {
                    value = Integer.parseInt(args[2]);
                } catch (NumberFormatException ex) {
                    System.err.printf("%s is not a number\n", args[2]);
                    Variables.set("status", "2");
                    return nextLevel;
                }

                Entry entry = getEntryWithName(name);

                if (entry instanceof ProgramProfilingSymbolEntry) {
                    ProgramProfilingSymbolEntry ppsEntry;
                    int                         profilingSymbolID;
                    String                      programSourcePath;
                    String                      tosPlatform;
                    String                      moteSerialID;

                    ppsEntry          = (ProgramProfilingSymbolEntry) entry;
                    profilingSymbolID = ppsEntry.getProgramProfilingSymbol()
                                                                    .getID();
                    programSourcePath = program.getSourcePath();
                    tosPlatform       = moteType.getTosPlatform();
                    moteSerialID      = mote.getMoteSerialID();

                    nucleusManager.setSymbol(value, profilingSymbolID,
                                             programSourcePath, tosPlatform,
                                             moteSerialID);
                    nameValueMap.put(name, value);
                } else {
                    System.out.printf("Unknown symbol: %s\n", name);
                    Variables.set("status", "3");
                }
            } catch (RemoteException ex) {
                Variables.set("status", "4");
                System.out.println("Failed to set symbol, reason: " +
                                   ex.getMessage());
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Set the value of the specified symbol";
        }
    }
}
