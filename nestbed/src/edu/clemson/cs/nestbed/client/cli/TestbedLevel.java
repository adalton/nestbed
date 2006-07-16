/* $Id$ */
/*
 * TestbedLevel.java
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

import edu.clemson.cs.nestbed.common.management.configuration.ProjectManager;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.model.Project;


class TestbedLevel extends Level {
    private Testbed        testbed;
    private ProjectManager projectManager;
    private List<Project>  projects;


    public TestbedLevel(Testbed testbed, Level parentLevel) throws Exception {
        super(testbed.getName(), parentLevel);
        lookupRemoteManagers();

        this.testbed = testbed;
        this.projects = projectManager.getProjectList(testbed.getID());

        for (Project i : projects) {
            addEntry(new ProjectLevelEntry(i));
        }

        addCommand("rm",     new RmCommand());
        addCommand("mkproj", new MkProjCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        projectManager = (ProjectManager)
                    Naming.lookup(RMI_BASE_URL + "ProjectManager");
    }


    private class ProjectLevelEntry extends LevelEntry {
        private Project project;

        public ProjectLevelEntry(Project project) {
            super(project.getName());
            this.project = project;
        }

        public Level getLevel() throws Exception {
            return new ProjectLevel(testbed, project, TestbedLevel.this);
        }
    }


    private class RmCommand implements Command {
        public void execute(String[] args) {
            System.out.println("RmCommand:  TODO");
        }

        public String getHelpText() {
            return "Removes the specified project";
        }
    }


    private class MkProjCommand implements Command {
        public void execute(String[] args) {
            System.out.println("MkprojCommand:  TODO");
        }

        public String getHelpText() {
            return "Creates a new project";
        }
    }
}
