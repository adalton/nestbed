/* $Id$ */
/*
 * NetworkMonitorLevel.java
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

import edu.clemson.cs.nestbed.common.management.deployment.ProgramDeploymentManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTestbedAssignmentManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTypeManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


class NetworkMonitorLevel extends Level {
    private Testbed                            testbed;
    private Project                            project;
    private ProjectDeploymentConfiguration     config;
    private MoteTestbedAssignmentManager       mtbaManager;
    private MoteDeploymentConfigurationManager mdConfigManager;
    private MoteManager                        moteManager;
    private MoteTypeManager                    moteTypeManager;
    private ProgramDeploymentManager           progDeployMgr;
    private List<MoteTestbedAssignment>        moteTestbedAssignments;
    private int                                numRows;
    private int                                numCols;
    private MoteState[][]                      moteState;


    public NetworkMonitorLevel(Testbed testbed, Project project,
                               ProjectDeploymentConfiguration config,
                               Level parent) throws Exception {
        super("NetworkMonitor", parent);
        lookupRemoteManagers();

        this.testbed                = testbed;
        this.project                = project;
        this.config                 = config;
        this.moteTestbedAssignments = mtbaManager.getMoteTestbedAssignmentList(
                                                            testbed.getID());

        for (MoteTestbedAssignment i : moteTestbedAssignments) {
            int x = i.getMoteLocationX();
            int y = i.getMoteLocationY();

            if (y > numRows) {
                numRows = y;
            }

            if (x > numCols) {
                numCols = x;
            }
        }
        ++numRows;
        ++numCols;

        this.moteState = new MoteState[numRows][numCols];
        for (int i = 0; i < moteState.length; ++i) {
            for (int j = 0; j < moteState[i].length; ++j) {
                moteState[i][j] = MoteState.U;
            }
        }


        progDeployMgr.addRemoteObserver(new ProgramDeploymentManagerObserver());


        for (MoteTestbedAssignment i : moteTestbedAssignments) {
            addEntry(new MoteManagementLevelEntry(i));
        }

        addCommand("ls",      new MonitorLsCommand());
        addCommand("install", new InstallCommand());
        addCommand("reset",   new ResetCommand());
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

        mtbaManager     = (MoteTestbedAssignmentManager)
                                Naming.lookup(RMI_BASE_URL +
                                         "MoteTestbedAssignmentManager");

        mdConfigManager = (MoteDeploymentConfigurationManager)
                                Naming.lookup(RMI_BASE_URL +
                                         "MoteDeploymentConfigurationManager");

        progDeployMgr   = (ProgramDeploymentManager)
                                Naming.lookup(RMI_BASE_URL +
                                         "ProgramDeploymentManager");
    }


    private class ProgramDeploymentManagerObserver extends   UnicastRemoteObject
                                                   implements RemoteObserver {
        public ProgramDeploymentManagerObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProgramDeploymentManager.Message message;
            String                           moteSerialID;
            MoteTestbedAssignment            mtbAssignment;

            message       = (ProgramDeploymentManager.Message) msg;
            moteSerialID  = (String)                           arg;
            mtbAssignment = null;

            for (Entry i : getEntries()) {
                if (i instanceof MoteManagementLevelEntry) {
                    MoteManagementLevelEntry mmle;
                    Mote                     mote;

                    mmle = (MoteManagementLevelEntry) i;
                    mote = mmle.getMote();

                    if (mote.getMoteSerialID().equals(moteSerialID)) {
                        mtbAssignment = mmle.getMoteTestbedAssignment();
                        break;
                    }
                }
            }

            if (mtbAssignment != null) {
                int x = mtbAssignment.getMoteLocationX();
                int y = mtbAssignment.getMoteLocationY();

                switch (message) {
                case PROGRAM_INSTALL_BEGIN:
                    moteState[y][x] = MoteState.I;
                    break;

                case PROGRAM_INSTALL_SUCCESS:
                    moteState[y][x] = MoteState.P;
                    break;

                case PROGRAM_INSTALL_FAILURE:
                    moteState[y][x] = MoteState.F;
                    break;

                default:
                    System.err.println("Unknown message:  " + message);
                    break;
                }
            }
        }
    }


    private class MoteManagementLevelEntry extends LevelEntry {
        private MoteTestbedAssignment       mtbAssignment;
        private Mote                        mote;
        private MoteType                    moteType;
        private MoteDeploymentConfiguration mdConfig;

        public MoteManagementLevelEntry(MoteTestbedAssignment mtbAssign)
                                                        throws RemoteException {
            super(Integer.toString(mtbAssign.getMoteAddress()));

            this.mtbAssignment = mtbAssign;
            this.mote          = moteManager.getMote(mtbAssign.getMoteID());
            this.moteType      = moteTypeManager.getMoteType(
                                                        mote.getMoteTypeID());
            this.mdConfig      = mdConfigManager.getMoteDeploymentConfiguration(
                                                     config.getID(),
                                                     mtbAssign.getMoteID());
        }


        public Level getLevel() throws Exception {
            int x = mtbAssignment.getMoteLocationX();
            int y = mtbAssignment.getMoteLocationY();

            return new MoteManagementLevel(testbed, project, config,
                                           mtbAssignment, moteState[y][x],
                                           NetworkMonitorLevel.this);
        }


        public MoteTestbedAssignment getMoteTestbedAssignment() {
            return mtbAssignment;
        }


        public Mote getMote() {
            return mote;
        }


        public MoteType getMoteType() {
            return moteType;
        }


        public void setMoteDeploymentConfiguration(
                                        MoteDeploymentConfiguration mdConfig) {
            this.mdConfig = mdConfig;
        }


        public MoteDeploymentConfiguration getMoteDeploymentConfig() {
            return mdConfig;
        }
    }


    private class MonitorLsCommand implements Command {
        private MoteTestbedAssignment[][] assignments;

        public MonitorLsCommand() {
            assignments = new MoteTestbedAssignment[numRows][numCols];

            for (MoteTestbedAssignment i : moteTestbedAssignments) {
                int x = i.getMoteLocationX();
                int y = i.getMoteLocationY();

                assignments[y][x] = i;
            }
        }


        public Level execute(String[] args) throws Exception {
            Variables.set("status", "0");

            for (int i = 0; i < assignments.length; ++i) {
                for (int j = 0; j < assignments[i].length; ++j) {
                    if (assignments[i][j] != null) {
                        MoteDeploymentConfiguration mdConfig;
                        mdConfig = mdConfigManager.
                                getMoteDeploymentConfiguration(
                                                  config.getID(),
                                                  assignments[i][j].getMoteID());
                        if (mdConfig != null) {
                            System.out.printf(" %3d[%s]/ ", 
                                             assignments[i][j].getMoteAddress(),
                                             moteState[i][j]);
                        } else {
                            System.out.printf("  [%3d]/ ",
                                             assignments[i][j].getMoteAddress());
                        }
                    } else {
                        System.out.print("              ");
                    }
                }
                System.out.println();
            }

            return NetworkMonitorLevel.this;
        }


        public String getHelpText() {
            return "Display network topology";
        }
    }


    private class InstallCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = NetworkMonitorLevel.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.err.printf("usage: %s <moteAddress>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String name  = args[1];
            Entry  entry = getEntryWithName(name);

            if (entry instanceof MoteManagementLevelEntry) {
                MoteManagementLevelEntry    mmlEntry;
                MoteDeploymentConfiguration mdConfig;

                mmlEntry = (MoteManagementLevelEntry) entry;
                mdConfig = mmlEntry.getMoteDeploymentConfig();

                if (mdConfig != null) {
                    MoteTestbedAssignment mtbAssignment;
                    Mote                  mote;
                    MoteType              moteType;

                    mtbAssignment = mmlEntry.getMoteTestbedAssignment();
                    mote          = mmlEntry.getMote();
                    moteType      = mmlEntry.getMoteType();

                    progDeployMgr.installProgram(mtbAssignment.getMoteAddress(),
                                                 mote.getMoteSerialID(),
                                                 moteType.getTosPlatform(),
                                                 mdConfig.getProgramID(),
                                                 new StringBuffer());
                } else {
                    System.err.printf("Mote %s has not been configured\n",
                                      name);
                    Variables.set("status", "2");
                }
            } else {
                System.err.printf("Mote %s not found\n", name);
                Variables.set("status", "3");
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Installs assigned application on specified mote";
        }
    }


    private class ResetCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = NetworkMonitorLevel.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.err.printf("usage: %s <moteAddress>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String name  = args[1];
            Entry  entry = getEntryWithName(name);

            if (entry instanceof MoteManagementLevelEntry) {
                MoteManagementLevelEntry    mmlEntry;
                MoteDeploymentConfiguration mdConfig;

                mmlEntry = (MoteManagementLevelEntry) entry;
                mdConfig = mmlEntry.getMoteDeploymentConfig();

                if (mdConfig != null) {
                    MoteTestbedAssignment mtbAssignment;
                    Mote                  mote;
                    MoteType              moteType;

                    mtbAssignment = mmlEntry.getMoteTestbedAssignment();
                    mote          = mmlEntry.getMote();
                    moteType      = mmlEntry.getMoteType();

                    progDeployMgr.resetMote(mtbAssignment.getMoteAddress(),
                                            mote.getMoteSerialID(),
                                            mdConfig.getProgramID());
                } else {
                    System.err.printf("Mote %s has not been configured\n",
                                      name);
                    Variables.set("status", "2");
                }
            } else {
                System.err.printf("Mote %s not found\n", name);
                Variables.set("status", "3");
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Reset specified mote";
        }
    }
}
