/* $Id:$ */
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


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import edu.clemson.cs.nestbed.common.management.configuration.MoteTestbedAssignmentManager;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class NetworkMonitorLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;
    private MoteTestbedAssignmentManager   mtbaManager;
    private List<MoteTestbedAssignment>    moteTestbedAssignments;


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
            addEntry(new MoteManagementLevelEntry(i));
        }

        addCommand("ls",      new MonitorLsCommand());
        addCommand("install", new InstallCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        mtbaManager = (MoteTestbedAssignmentManager)
                            Naming.lookup(RMI_BASE_URL +
                                         "MoteTestbedAssignmentManager");
    }


    private class MoteManagementLevelEntry extends LevelEntry {
        private MoteTestbedAssignment mtbAssignment;

        public MoteManagementLevelEntry(MoteTestbedAssignment mtbAssignment) {
            super(Integer.toString(mtbAssignment.getMoteAddress()));

            this.mtbAssignment = mtbAssignment;
        }


        public Level getLevel() throws Exception {
            // TODO:  Fix this
            return NetworkMonitorLevel.this;
        }
    }


    private class MonitorLsCommand implements Command {
        private int                       rows = -1;
        private int                       cols = -1;
        private MoteTestbedAssignment[][] assignments;

        public MonitorLsCommand() {
            for (MoteTestbedAssignment i : moteTestbedAssignments) {
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

            for (MoteTestbedAssignment i : moteTestbedAssignments) {
                int x = i.getMoteLocationX();
                int y = i.getMoteLocationY();

                assignments[y][x] = i;
            }

        }


        public void execute(String[] args) throws Exception {
            for (int i = 0; i < assignments.length; ++i) {
                for (int j = 0; j < assignments[i].length; ++j) {
                    if (assignments[i][j] != null) {
                        //MoteDeploymentConfiguration mdConfig;
                        //mdConfig = mdConfigManager.
                        //        getMoteDeploymentConfiguration(
                        //                          config.getID(),
                        //                          assignments[i][j].getMoteID());
//
//                        if (mdConfig != null) {
//                            System.out.printf(" %3d(%2d)/ ",
//                                             assignments[i][j].getMoteAddress(),
//                                             mdConfig.getRadioPowerLevel());
//                        } else {
                            System.out.printf("  [%3d]/  ",
                                             assignments[i][j].getMoteAddress());
//                        }
                    } else {
                        System.out.print("              ");
                    }
                }
                System.out.println();
            }
        }


        public String getHelpText() {
            return "Display network topology";
        }
    }


    private class InstallCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.out.println("InstallCommand:  TODO");
        }

        public String getHelpText() {
            return "Installs applications on motes";
        }
    }
}
