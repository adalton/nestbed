/* $Id$ */
/*
 * ProjectManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.ProgramManager;
import edu.clemson.cs.nestbed.common.management.ProjectDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.ProjectManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProjectAdapter;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProjectManagerImpl extends    RemoteObservableImpl
                                implements ProjectManager {
    private final static ProjectManager instance;
    private final static Log            log      = LogFactory.getLog(
                                                    ProjectManagerImpl.class);

    private ReadWriteLock         managerLock;
    private Lock                  readLock;
    private Lock                  writeLock;
    private ProjectAdapter        projectAdapter;
    private Map<Integer, Project> projects;

    static {
        ProjectManagerImpl impl = null;

        try {
            impl = new ProjectManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static ProjectManager getInstance() {
        return instance;
    }


    public List<Project> getProjectList(int testbedID) throws RemoteException {
        log.debug("getProjectList() called");
        List<Project> projectList = new ArrayList<Project>();

        readLock.lock();
        try {
            for (Project i : projects.values()) {
                if (i.getTestbedID() == testbedID) {
                    projectList.add(i);
                }
            }
        } catch (Exception ex) {
            log.error("Exception in getProjectList", ex);

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } finally {
            readLock.unlock();
        }

        return projectList;
    }


    public void createNewProject(int testbedID, String name,
                                 String description)   throws RemoteException {
        try {
            log.info("Request to create new Project:\n" +
                     "  testbedID:    " + testbedID   + "\n" +
                     "  name:         " + name        + "\n" +
                     "  description:  " + description);

            Project project = projectAdapter.createProject(testbedID, name,
                                                           description);

            writeLock.lock();
            try {
                projects.put(project.getID(), project);
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.NEW_PROJECT, project);
        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (Exception ex) {
            log.error("Exception in createNewProject", ex);

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }


    public void deleteProject(int projectID) throws RemoteException {
        try {
            log.info("Request to delete Project with id: " + projectID);

            cleanupPrograms(projectID);
            cleanupProjectDeploymentConfigurations(projectID);

            Project project = projectAdapter.deleteProject(projectID);
            writeLock.lock();
            try {
                projects.remove(project.getID());
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.DELETE_PROJECT, project);
        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deleteProject", ex);

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }


    private void cleanupPrograms(int projectID) throws RemoteException {
        ProgramManager pm          = ProgramManagerImpl.getInstance();
        List<Program>  programList = pm.getProgramList(projectID);

        try {
            for (Program i : programList) {
                pm.deleteProgram(i.getID());
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in cleanupPrograms", ex);

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }


    private void cleanupProjectDeploymentConfigurations(int projectID)
                                                        throws RemoteException {
        ProjectDeploymentConfigurationManager pdcm =
                        ProjectDeploymentConfigurationManagerImpl.getInstance();

        try {
            List<ProjectDeploymentConfiguration> configList =
                                    pdcm.getProjectDeploymentConfigs(projectID);

            for (ProjectDeploymentConfiguration i : configList) {
                pdcm.deleteProjectDeploymentConfig(i.getID());
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in cleanupProjectDeploymentConfigurations",
                      ex);

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }


    private ProjectManagerImpl() throws RemoteException {
        super();

        try {
            this.managerLock = new ReentrantReadWriteLock(true);
            this.readLock    = managerLock.readLock();
            this.writeLock   = managerLock.writeLock();
            projectAdapter   = AdapterFactory.createProjectAdapter(
                                                              AdapterType.SQL);
            projects         = projectAdapter.readProjects();

            log.debug("Projects read:\n" + projects);
        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (Exception ex) {
            log.error("Exception in ProjectManagerImpl", ex);

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }
}
