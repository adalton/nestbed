/* $Id$ */
/*
 * MoteManagementLevel.java
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * School of Computing
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

import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTypeManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class MoteManagementLevel extends Level {
    private Testbed                            testbed;
    private Project                            project;
    private ProjectDeploymentConfiguration     config;
    private MoteTestbedAssignment              mtbAssignment;
    private MoteState[][]                      moteState;
    private int                                stateRow;
    private int                                stateCol;
    private MoteManager                        moteManager;
    private Mote                               mote;
    private MoteTypeManager                    moteTypeManager;
    private MoteType                           moteType;
    private MoteDeploymentConfigurationManager mdConfigManager;
    private MoteDeploymentConfiguration        mdConfig;
    private ProgramManager                     programManager;
    private Program                            program;


    public MoteManagementLevel(Testbed                        testbed,
                               Project                        project,
                               ProjectDeploymentConfiguration config,
                               MoteTestbedAssignment          mtbAssignment,
                               int                            stateRow,
                               int                            stateCol,
                               MoteState[][]                  moteState,
                               Level                          parent)
                                                            throws Exception {

        super(Integer.toString(mtbAssignment.getMoteAddress()), parent);
        lookupRemoteManagers();

        this.testbed       = testbed;
        this.project       = project;
        this.config        = config;
        this.mtbAssignment = mtbAssignment;
        this.moteState     = moteState;
        this.stateRow      = stateRow;
        this.stateCol      = stateCol;
        this.mote          = moteManager.getMote(mtbAssignment.getMoteID());
        this.moteType      = moteTypeManager.getMoteType(mote.getMoteTypeID());
        this.mdConfig      = mdConfigManager.getMoteDeploymentConfiguration(
                                                  config.getID(), mote.getID());
        if (mdConfig != null) {
            this.program = programManager.getProgram(mdConfig.getProgramID());
        }

        addEntry(new MoteDetailFileEntry());
        addEntry(new MoteSymbolProfilingLevelEntry());
        addEntry(new MoteMessageProfilingLevelEntry());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {

        moteManager     = (MoteManager)     Naming.lookup(RMI_BASE_URL +
                                                          "MoteManager");

        moteTypeManager = (MoteTypeManager) Naming.lookup(RMI_BASE_URL +
                                                          "MoteTypeManager");

        mdConfigManager = (MoteDeploymentConfigurationManager)
                                Naming.lookup(RMI_BASE_URL +
                                        "MoteDeploymentConfigurationManager");

        programManager  = (ProgramManager)  Naming.lookup(RMI_BASE_URL +
                                                          "ProgramManager");
    }


    private class MoteDetailFileEntry extends FileEntry {
        public MoteDetailFileEntry() {
            super("details");
        }


        @Override
        public String getFileContents() throws Exception {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Address:             ");
            buffer.append(mtbAssignment.getMoteAddress());
            buffer.append("\n");

            buffer.append("Location:            ");
            buffer.append("(").append(mtbAssignment.getMoteLocationX());
            buffer.append(", ").append(mtbAssignment.getMoteLocationY());
            buffer.append(")\n");
            
            buffer.append("Serial ID:           ");
            buffer.append(mote.getMoteSerialID());
            buffer.append("\n");

            buffer.append("Type:                ");
            buffer.append(moteType.getName());
            buffer.append("\n");

            buffer.append("Total ROM:           ");
            buffer.append(moteType.getTotalROM());
            buffer.append("\n");

            buffer.append("Total RAM:           ");
            buffer.append(moteType.getTotalRAM());
            buffer.append("\n");

            buffer.append("Total EEPROM:        ");
            buffer.append(moteType.getTotalEEPROM());
            buffer.append("\n");



            buffer.append("\n");

            if (program != null) {
                buffer.append("Activity State:      ");
                buffer.append(moteState[stateRow][stateCol].getLongForm());
                buffer.append("\n");

                buffer.append("\n");

                buffer.append("Program Name:        ");
                buffer.append(program.getName());
                buffer.append("\n");

                buffer.append("Program Description: ");
                buffer.append(program.getDescription());
            } else {
                buffer.append("Unconfigured\n");
            }

            return buffer.toString();
        }
    }


    private class MoteSymbolProfilingLevelEntry extends LevelEntry {
        public MoteSymbolProfilingLevelEntry() {
            super("ProfilingSymbols");
        }


        public Level getLevel() throws Exception {
            return new MoteSymbolProfilingLevel(testbed, project, config,
                                                mtbAssignment, moteState,
                                                mote, moteType, mdConfig,
                                                program,
                                                MoteManagementLevel.this);
        }
    }


    private class MoteMessageProfilingLevelEntry extends LevelEntry {
        public MoteMessageProfilingLevelEntry() {
            super("ProfilingMessages");
        }


        public Level getLevel() throws Exception {
            return new MoteMessageProfilingLevel(config, program, mote,
                                                 MoteManagementLevel.this);
        }
    }
}
