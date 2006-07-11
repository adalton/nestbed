/* $Id$ */
/*
 * ProgramLevel.java
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

import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class ProgramLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;
    private ProgramManager                 programManager;
    private List<Program>                  programs;


    public ProgramLevel(Testbed                        testbed, Project project,
                        ProjectDeploymentConfiguration config,  Level   parent)
                                                            throws Exception {
        super("Programs", parent);
        lookupRemoteManagers();

        this.testbed  = testbed;
        this.project  = project;
        this.config   = config;
        this.programs = programManager.getProgramList(project.getID());

        for (Program i : programs) {
            addEntry(new ApplicationLevelEntry(testbed, project,
                                               config, i, this));
        }

        addCommand("rm",     new RmCommand());
        addCommand("upload", new UploadCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        programManager = (ProgramManager) Naming.lookup(RMI_BASE_URL +
                                                        "ProgramManager");
    }


    private class RmCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.out.println("RmCommand:  TODO");
        }

        public String getHelpText() {
            return "Removes a program";
        }
    }


    private class UploadCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.out.println("UploadCommand:  TODO");
        }

        public String getHelpText() {
            return "Uploads a program";
        }
    }
}
