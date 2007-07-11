/* $Id$ */
/*
 * MoteConfigLevel.java
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


import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTestbedAssignmentManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTypeManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


class MoteConfigLevel extends Level {
    private Testbed                            testbed;
    private Project                            project;
    private ProjectDeploymentConfiguration     config;
    private MoteTestbedAssignment[][]          assignments;
    private MoteManager                        moteManager;
    private MoteTypeManager                    moteTypeManager;
    private ProgramManager                     programManager;
    private MoteTestbedAssignmentManager       mtbaManager;
    private MoteDeploymentConfigurationManager mdConfigManager;


    public MoteConfigLevel(Testbed                        testbed,
                           Project                        project,
                           ProjectDeploymentConfiguration config,
                           Level                          parent)
                                                            throws Exception {
        super("Motes", parent);
        lookupRemoteManagers();

        this.testbed = testbed;
        this.project = project;
        this.config  = config;

        mdConfigManager.addRemoteObserver(
                              new MoteDeploymentConfigurationManagerObserver());

        List<MoteTestbedAssignment> mtbAssignments;
        int                         rows = -1;
        int                         cols = -1;

        mtbAssignments = mtbaManager.getMoteTestbedAssignmentList(
                                                              testbed.getID());

        for (MoteTestbedAssignment i : mtbAssignments) {
            int x = i.getMoteLocationX();
            int y = i.getMoteLocationY();

            if (y > rows) {
                rows = y;
            }

            if (x > cols) {
                cols = x;
            }
        }

        assignments = new MoteTestbedAssignment[++rows][++cols];

        for (MoteTestbedAssignment i : mtbAssignments) {
            int x = i.getMoteLocationX();
            int y = i.getMoteLocationY();

            assignments[y][x] = i;

            addEntry(new MoteTestbedAssignmentEntry(i));
        }

        addCommand("ls",          new MoteLsCommand());
        addCommand("configure",   new ConfigureCommand());
        addCommand("unconfigure", new UnconfigureCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        moteManager     = (MoteManager)
                              Naming.lookup(RMI_BASE_URL +
                                         "MoteManager");

        moteTypeManager = (MoteTypeManager)
                              Naming.lookup(RMI_BASE_URL +
                                         "MoteTypeManager");

        programManager  = (ProgramManager)
                              Naming.lookup(RMI_BASE_URL +
                                         "ProgramManager");

        mtbaManager     = (MoteTestbedAssignmentManager)
                              Naming.lookup(RMI_BASE_URL +
                                         "MoteTestbedAssignmentManager");
        mdConfigManager = (MoteDeploymentConfigurationManager)
                               Naming.lookup(RMI_BASE_URL +
                                         "MoteDeploymentConfigurationManager");
    }


    private class MoteDeploymentConfigurationManagerObserver
                                                extends    UnicastRemoteObject
                                                implements RemoteObserver {

        public MoteDeploymentConfigurationManagerObserver()
                                                        throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                        throws RemoteException {
            MoteDeploymentConfigurationManager.Message message;
            MoteDeploymentConfiguration                mdConfig;

            message  = (MoteDeploymentConfigurationManager.Message) msg;
            mdConfig = (MoteDeploymentConfiguration)                arg;

            switch (message) {
            case NEW_CONFIG:
                for (Entry i : getEntries()) {
                    if (i instanceof MoteTestbedAssignmentEntry) {
                        MoteTestbedAssignmentEntry mtbaEntry;
                        mtbaEntry = (MoteTestbedAssignmentEntry) i;

                        if (mtbaEntry.getMote().getID() ==
                                                        mdConfig.getMoteID()) {
                            mtbaEntry.setMoteDeploymentConfiguration(mdConfig);
                        }
                    }
                }
                break;

            case DELETE_CONFIG:
                for (Entry i : getEntries()) {
                    if (i instanceof MoteTestbedAssignmentEntry) {
                        MoteTestbedAssignmentEntry mtbaEntry;
                        mtbaEntry = (MoteTestbedAssignmentEntry) i;

                        if (mtbaEntry.getMote().getID() ==
                                                        mdConfig.getMoteID()) {
                            mtbaEntry.setMoteDeploymentConfiguration(null);
                        }
                    }
                }
                break;

            default:
                break;
            }
        }
    }


    private class MoteTestbedAssignmentEntry extends FileEntry {
        private MoteTestbedAssignment       moteTestbedAssignment;
        private Mote                        mote;
        private MoteType                    moteType;
        private MoteDeploymentConfiguration mdConfig;
        private Program                     program;


        public MoteTestbedAssignmentEntry(MoteTestbedAssignment mtbAssign)
                                                        throws RemoteException {
            super(Integer.toString(mtbAssign.getMoteAddress()));

            this.moteTestbedAssignment = mtbAssign;
            this.mote                  = moteManager.getMote(
                                                        mtbAssign.getMoteID());
            this.moteType              = moteTypeManager.getMoteType(
                                                        mote.getMoteTypeID());
            this.mdConfig              = mdConfigManager.
                                             getMoteDeploymentConfiguration(
                                                 config.getID(),
                                                 moteTestbedAssignment.
                                                                   getMoteID());
            if (this.mdConfig != null) {
                this.program = programManager.getProgram(
                                                    mdConfig.getProgramID());
            }
        }


        public String getFileContents() throws Exception {
            StringBuffer s = new StringBuffer(2000);

            s.append("Address:             ");
            s.append(Integer.toString(moteTestbedAssignment.getMoteAddress()));
            s.append("\n");

            s.append("Location:            (");
            s.append(moteTestbedAssignment.getMoteLocationX()).append(", ");
            s.append(moteTestbedAssignment.getMoteLocationY()).append(")\n");

            s.append("Serial ID:           ").append(mote.getMoteSerialID()).append("\n");
            s.append("Type:                ").append(moteType.getName()).append("\n");
            s.append("Total ROM:           ").append(moteType.getTotalROM()).append("\n");
            s.append("Total RAM:           ").append(moteType.getTotalRAM()).append("\n");
            s.append("Total EEPROM:        ").append(moteType.getTotalEEPROM()).append("\n");

            if (mdConfig != null) {
                s.append("\n");
                s.append("Program Name:        ").append(program.getName()).append("\n");
                s.append("Program Description: ").append(program.getDescription()).append("\n");
                s.append("Radio Power Level:   ").append(mdConfig.getRadioPowerLevel()).append("\n");
            }

            return s.toString();
        }


        public MoteTestbedAssignment getMoteTestbedAssignment() {
            return moteTestbedAssignment;
        }


        public Mote getMote() {
            return mote;
        }


        public void setMoteDeploymentConfiguration(
                                        MoteDeploymentConfiguration mdConfig) {
            this.mdConfig = mdConfig;
        }


        public MoteDeploymentConfiguration getMoteDeploymentConfig() {
            return mdConfig;
        }
    }


    private class MoteLsCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Variables.set("status", "0");

            for (int i = 0; i < assignments.length; ++i) {
                for (int j = 0; j < assignments[i].length; ++j) {
                    if (assignments[i][j] != null) {
                        String name = Integer.toString(
                                            assignments[i][j].getMoteAddress());
                        Entry entry = getEntryWithName(name);

                        if (entry instanceof MoteTestbedAssignmentEntry) {
                            MoteTestbedAssignmentEntry  mtbaEntry;
                            MoteDeploymentConfiguration mdConfig;

                            mtbaEntry = (MoteTestbedAssignmentEntry) entry;
                            mdConfig  = mtbaEntry.getMoteDeploymentConfig();

                            if (mdConfig != null) {
                                System.out.printf(" %3d(%2d) ",
                                             assignments[i][j].getMoteAddress(),
                                             mdConfig.getRadioPowerLevel());
                            } else {
                                System.out.printf("  [%3d]  ",
                                            assignments[i][j].getMoteAddress());
                            }
                        }
                    } else {
                        System.out.print("             ");
                    }
                }
                System.out.println();
            }

            return MoteConfigLevel.this;
        }


        public String getHelpText() {
            return "Display network topology";
        }
    }


    private class ConfigureCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = MoteConfigLevel.this;

            Variables.set("status", "0");

            if (args.length != 4) {
                System.out.printf("usage:  %s <moteAddress> <programName> " +
                                  "<radioPowerLevel>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            int     moteAddress     = Integer.parseInt(args[1]);
            String  programName     = args[2];
            int     radioPowerLevel = Integer.parseInt(args[3]);
            Program program         = null;

            for (Program i : programManager.getProgramList(project.getID())) {
                if (i.getName().equals(programName)) {
                    program = i;
                    break;
                }
            }

            if (program != null) {
                Entry entry = getEntryWithName(args[1]);

                if (entry instanceof MoteTestbedAssignmentEntry) {
                    MoteTestbedAssignmentEntry mtbaEntry;
                    Mote                       mote;

                    mtbaEntry = (MoteTestbedAssignmentEntry) entry;
                    mote      = mtbaEntry.getMote();

                    mdConfigManager.setMoteDeploymentConfiguration(
                                                               config.getID(),
                                                               mote.getID(),
                                                               program.getID(),
                                                               radioPowerLevel);
                } else {
                    System.err.printf("No such mote:  %d\n", moteAddress);
                    Variables.set("status", "2");
                }
            } else {
                System.err.printf("No such program:  %s\n", programName);
                Variables.set("status", "3");
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Configure the specified mote";
        }
    }


    private class UnconfigureCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = MoteConfigLevel.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.err.printf("usage:  %s <moteAddress>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String name  = args[1];
            Entry  entry = getEntryWithName(name);

            if (entry instanceof MoteTestbedAssignmentEntry) {
                MoteTestbedAssignmentEntry  mtbaEntry;
                MoteDeploymentConfiguration mdConfig;

                mtbaEntry = (MoteTestbedAssignmentEntry) entry;
                mdConfig  = mtbaEntry.getMoteDeploymentConfig();

                mdConfigManager.deleteMoteDeploymentConfiguration(
                                                        mdConfig.getID());
            } else {
                System.err.printf("%s is not a MoteTestbedAssignmentEntry\n",
                                  name);
                Variables.set("status", "2");
            }
            return nextLevel;
        }


        public String getHelpText() {
            return "Unconfigure the specified mote";
        }
    }
}
