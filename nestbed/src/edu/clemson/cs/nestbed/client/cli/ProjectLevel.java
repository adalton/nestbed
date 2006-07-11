/* $Id$ */
/*
 * ProjectLevel.java
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

import edu.clemson.cs.nestbed.common.management.configuration.ProjectDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class ProjectLevel extends Level {
    private Testbed                               testbed;
    private Project                               project;
    private ProjectDeploymentConfigurationManager configManager;
    private List<ProjectDeploymentConfiguration>  configs;


    public ProjectLevel(Testbed testbed, Project project, Level parentLevel)
                                                              throws Exception {
        super(project.getName(), parentLevel);
        lookupRemoteManagers();

        this.testbed = testbed;
        this.project = project;
        this.configs = configManager.getProjectDeploymentConfigs(
                                                            project.getID());

        for (ProjectDeploymentConfiguration i : configs) {
            addEntry(new ProjectDeploymentConfigurationLevelEntry(testbed,
                                                                  project,
                                                                  i, this));
        }

        addCommand("rm", new RmCommand());
        addCommand("mkconf", new MkConfCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        configManager = (ProjectDeploymentConfigurationManager)
                                    Naming.lookup(RMI_BASE_URL +
                                    "ProjectDeploymentConfigurationManager");
    }


    private class RmCommand implements Command {
        public void execute(String[] args) {
            System.out.println("RmCommand:  TODO");
        }

        public String getHelpText() {
            return "Removes the specified configuration";
        }
    }


    private class MkConfCommand implements Command {
        public void execute(String[] args) {
            System.out.println("MkconfCommand:  TODO");
        }

        public String getHelpText() {
            return "Creates a new configuration";
        }
    }
}
