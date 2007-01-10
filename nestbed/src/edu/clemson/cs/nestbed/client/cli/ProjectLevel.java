/* $Id$ */
/*
 * ProjectLevel.java
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

import edu.clemson.cs.nestbed.common.management.configuration.ProjectDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


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

        configManager.addRemoteObserver(
                           new ProjectDeploymentConfigurationManagerObserver());

        addEntry(new ProjectDetailFileEntry());
        for (ProjectDeploymentConfiguration i : configs) {
            addEntry(new ProjectDeploymentConfigurationLevelEntry(i));
        }

        addCommand("rmconf", new RmConfCommand());
        addCommand("mkconf", new MkConfCommand());
        //addCommand("cat",    new CatCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        configManager = (ProjectDeploymentConfigurationManager)
                                    Naming.lookup(RMI_BASE_URL +
                                    "ProjectDeploymentConfigurationManager");
    }


    private class ProjectDetailFileEntry extends FileEntry {
        public ProjectDetailFileEntry() {
            super("details");
        }


        @Override
        public String getFileContents() throws Exception {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Name:         ").append(project.getName());
            buffer.append("\n");
            buffer.append("Description:  ").append(project.getDescription());
            buffer.append("\n");

            return buffer.toString();
        }
    }


    private class ProjectDeploymentConfigurationLevelEntry extends LevelEntry {
        private ProjectDeploymentConfiguration config;

        public ProjectDeploymentConfigurationLevelEntry(
                                        ProjectDeploymentConfiguration config) {
            super(config.getName());
            this.config = config;
        }


        public Level getLevel() throws Exception {
            return new ProjectDeploymentConfigurationLevel(testbed,
                                                           project,
                                                           config,
                                                           ProjectLevel.this);
        }


        public ProjectDeploymentConfiguration
                                          getProjectDeploymentConfiguration() {
            return config;
        }
    }


    private class RmConfCommand implements Command {
        public Level execute(String[] args) {
            Level nextLevel = ProjectLevel.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.err.println("rmconf <name>");
                Variables.set("status", "0");
                return nextLevel;
            }

            try {
                String name  = args[1];
                Entry  entry = getEntryWithName(name);

                if (entry != null) {
                    if (entry instanceof
                                    ProjectDeploymentConfigurationLevelEntry) {
                        ProjectDeploymentConfigurationLevelEntry pdcEntry;
                        ProjectDeploymentConfiguration           pdConfig;

                        pdcEntry =
                            (ProjectDeploymentConfigurationLevelEntry) entry;
                        pdConfig = pdcEntry.getProjectDeploymentConfiguration();

                        configManager.deleteProjectDeploymentConfig(
                                                              pdConfig.getID());
                    } else {
                        System.err.println("Entry is not a " +
                                           "ProjectDeploymentConfiguration");
                        Variables.set("status", "1");
                    }
                } else {
                    System.err.println("No such entry: " + name);
                    Variables.set("status", "2");
                } 
            } catch (RemoteException ex) {
                System.out.println("Remote Exception occured while removing " +
                                   "configuration: " + ex);
                Variables.set("status", "3");
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Removes the specified configuration";
        }
    }


    private class MkConfCommand implements Command {
        public Level execute(String[] args) {
            Level nextLevel = ProjectLevel.this;

            Variables.set("status", "0");

            if (args.length != 3) {
                System.err.println("mkconf <name> <description>");
                Variables.set("status", "1");
                return nextLevel;
            }

            try {
                String name        = args[1];
                String description = args[2];

                configManager.createNewProjectDeploymentConfig(project.getID(),
                                                               name,
                                                               description);
            } catch (RemoteException ex) {
                System.out.println("Remote Exception occured while creating " +
                                   "configuration: " + ex);
                Variables.set("status", "2");
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Creates a new configuration";
        }
    }


    private class ProjectDeploymentConfigurationManagerObserver
                                                 extends    UnicastRemoteObject
                                                 implements RemoteObserver {
        public ProjectDeploymentConfigurationManagerObserver()
                                                        throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                        throws RemoteException {
            ProjectDeploymentConfigurationManager.Message message;
            ProjectDeploymentConfiguration                conf;

            message = (ProjectDeploymentConfigurationManager.Message) msg;
            conf    = (ProjectDeploymentConfiguration)                arg;


            switch (message) {
            case NEW_CONFIG:
                configs.add(conf);
                addEntry(new ProjectDeploymentConfigurationLevelEntry(conf));
                break; 

            case DELETE_CONFIG:
                Entry entry = getEntryWithName(conf.getName());

                if (entry instanceof ProjectDeploymentConfigurationLevelEntry) {
                    ProjectDeploymentConfigurationLevelEntry pdcEntry;
                    pdcEntry = (ProjectDeploymentConfigurationLevelEntry) entry;

                    removeEntry(pdcEntry);
                    // TODO:  pdcEntry.destroy();
                    configs.remove(pdcEntry);
                }
                break;

            default:
                System.out.println("Unknown message:  " + message);
            }
        }
    }
}
