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


import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import edu.clemson.cs.nestbed.common.management.configuration.ProjectManager;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


class TestbedLevel extends Level {
    private Testbed        testbed;
    private ProjectManager projectManager;
    private List<Project>  projects;


    public TestbedLevel(Testbed testbed, Level parentLevel) throws Exception {
        super(testbed.getName(), parentLevel);
        lookupRemoteManagers();

        this.testbed = testbed;
        this.projects = projectManager.getProjectList(testbed.getID());

        projectManager.addRemoteObserver(new ProjectManagerObserver());


        addEntry(new TestbedDetailFileEntry());
        for (Project i : projects) {
            addEntry(new ProjectLevelEntry(i));
        }

        addCommand("rmproj", new RmProjCommand());
        addCommand("mkproj", new MkProjCommand());
        addCommand("cat",    new CatCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        projectManager = (ProjectManager)
                    Naming.lookup(RMI_BASE_URL + "ProjectManager");
    }


    private class TestbedDetailFileEntry extends FileEntry {
        public TestbedDetailFileEntry() {
            super("details");
        }


        @Override
        public String getFileContents() throws Exception {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Name:         ").append(testbed.getName());
            buffer.append("\n");
            buffer.append("Description:  ").append(testbed.getDescription());
            buffer.append("\n");

            return buffer.toString();
        }
    }


    private class ProjectLevelEntry extends LevelEntry {
        private Project project;

        public ProjectLevelEntry(Project project) {
            super(project.getName());
            this.project = project;
        }


        @Override
        public Level getLevel() throws Exception {
            return new ProjectLevel(testbed, project, TestbedLevel.this);
        }

        public Project getProject() {
            return project;
        }
    }


    private class RmProjCommand implements Command {
        public Level execute(String[] args) {
            Level nextLevel = TestbedLevel.this;

            if (args.length != 2) {
                System.err.println("rmproj <name>");
                return nextLevel;
            }

            try {
                String name  = args[1];
                Entry  entry = getEntryWithName(name);

                if (entry != null) {
                    if (entry instanceof ProjectLevelEntry) {
                        ProjectLevelEntry plEntry;
                        Project           project;

                        plEntry = (ProjectLevelEntry) entry;
                        project = plEntry.getProject();

                        projectManager.deleteProject(project.getID());
                    } else {
                        System.err.println("Entry is not a project");
                    }
                } else {
                    System.err.println("No such entry: " + name);
                } 
            } catch (RemoteException ex) {
                System.out.println("Remote Exception occured while creating " +
                                   "project: " + ex);
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Removes the specified project";
        }
    }


    private class MkProjCommand implements Command {
        public Level execute(String[] args) {
            Level nextLevel = TestbedLevel.this;


            if (args.length != 3) {
                System.err.println("mkproj <name> <description>");
                return nextLevel;
            }

            try {
                String name        = args[1];
                String description = args[2];

                projectManager.createNewProject(testbed.getID(), name,
                                                description);
            } catch (RemoteException ex) {
                System.out.println("Remote Exception occured while creating " +
                                   "project: " + ex);
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Creates a new project";
        }
    }


    private class ProjectManagerObserver extends    UnicastRemoteObject
                                         implements RemoteObserver {
        public ProjectManagerObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                        throws RemoteException {
            ProjectManager.Message message;
            Project                proj;

            message = (ProjectManager.Message) msg;
            proj    = (Project)                arg;


            switch (message) {
            case NEW_PROJECT:
                projects.add(proj);
                addEntry(new ProjectLevelEntry(proj));
                break; 

            case DELETE_PROJECT:
                Entry entry = getEntryWithName(proj.getName());

                if (entry instanceof ProjectLevelEntry) {
                    ProjectLevelEntry plEntry;
                    plEntry = (ProjectLevelEntry) entry;

                    removeEntry(plEntry);
                    // TODO:  plEntry.destroy();
                    projects.remove(proj);
                }
                break;

            default:
                System.out.println("Unknown message:  " + message);
            }
        }
    }
}
