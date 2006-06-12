/* $Id$ */
/*
 * ProjectDeploymentConfigurationManagerImpl.java
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

import edu.clemson.cs.nestbed.common.management.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.MoteManager;
import edu.clemson.cs.nestbed.common.management.MoteTestbedAssignmentManager;
import edu.clemson.cs.nestbed.common.management.MoteTypeManager;
import edu.clemson.cs.nestbed.common.management.ProgramManager;
import edu.clemson.cs.nestbed.common.management.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.ProjectDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProjectDeploymentConfigurationAdapter;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProjectDeploymentConfigurationManagerImpl
                            extends    RemoteObservableImpl
                            implements ProjectDeploymentConfigurationManager {

    private final static ProjectDeploymentConfigurationManager instance;
    private final static Log log = LogFactory.getLog(
                               ProjectDeploymentConfigurationManagerImpl.class);

    private ReadWriteLock                                managerLock;
    private Lock                                         readLock;
    private Lock                                         writeLock;
    private ProjectDeploymentConfigurationAdapter        projDepConfigAdapter;
    private Map<Integer, ProjectDeploymentConfiguration> projDepConfigs;

    static {
        ProjectDeploymentConfigurationManagerImpl impl = null;

        try {
            impl = new ProjectDeploymentConfigurationManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static ProjectDeploymentConfigurationManager getInstance() {
        return instance;
    }


    public List<ProjectDeploymentConfiguration>
                        getProjectDeploymentConfigs(int projectID)
                                                        throws RemoteException {
        log.debug("getProjectDeploymentConfigs() called");

        List<ProjectDeploymentConfiguration> configList;
        configList = new ArrayList<ProjectDeploymentConfiguration>();

        readLock.lock();
        try {
            for (ProjectDeploymentConfiguration i : projDepConfigs.values()) {
                if (i.getProjectID() == projectID) {
                    configList.add(i);
                }
            }
        } catch (Exception ex) {
            log.error("Exception in getProjectDeploymentConfigs", ex);
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } finally {
            readLock.unlock();
        }

        return configList;
    }


    public void createNewProjectDeploymentConfig(int    projectID,
                                                 String name,
                                                 String description)
                                                        throws RemoteException {
        log.info("Request to create new ProjectDeploymentConfiguration:\n" +
             "  projectID:    " + projectID + "\n" +
             "  name:         " + name      + "\n" +
             "  description:  " + description);

        try {
            ProjectDeploymentConfiguration config =
                projDepConfigAdapter.createNewProjectDeploymentConfig(
                                                projectID, name, description);

            log.info(config);
            writeLock.lock();
            try {
                projDepConfigs.put(config.getID(), config);
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.NEW_CONFIG, config);
        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (Exception ex) {
            log.error("Exception in createNewProjectDeploymentConfig");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }


    public void cloneProjectDeploymentConfig(int    sourceID,
                                             String name,
                                             String description)
                                                        throws RemoteException {
        log.info("Request to clone ProjectDeploymentConfiguration:\n" +
                 "  sourceID:        " + sourceID + "\n" +
                 "  newName:         " + name     + "\n" +
                 "  newDescription:  " + description);

        try {
            ProjectDeploymentConfiguration oldConfig =
                                                projDepConfigs.get(sourceID);

            ProjectDeploymentConfiguration config =
                    projDepConfigAdapter.createNewProjectDeploymentConfig(
                                                    oldConfig.getProjectID(),
                                                    name, description);

            writeLock.lock();
            try {
                projDepConfigs.put(config.getID(), config);
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.NEW_CONFIG, config);


            MoteDeploymentConfigurationManagerImpl.getInstance().
                                  cloneMoteDeploymentConfigurations(sourceID,
                                                                    config.getID());

            ProgramProfilingSymbolManagerImpl.getInstance().
                                           cloneProfilingSymbol(sourceID,
                                                                config.getID());


            ProgramProfilingMessageSymbolManagerImpl.getInstance().
                        cloneProfilingMessageSymbol(sourceID, config.getID());

        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in cloneProjectDeploymentConfig");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }



    public void deleteProjectDeploymentConfig(int id) throws RemoteException {
        try {
            log.info("Request to delete ProjectDeploymentConfiguration with " +
                     " id:  " + id);

            ProjectDeploymentConfiguration config =
                    projDepConfigAdapter.deleteProjectDeploymentConfig(id);

            writeLock.lock();
            try {
                projDepConfigs.remove(config.getID());
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.DELETE_CONFIG, config);
        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (Exception ex) {
            log.error("Exception in deleteProjectDeploymentConfig");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }


    public void deployConfiguration(int id) throws RemoteException {
        List<MoteDeploymentConfiguration> moteDeploymentConfigs;

        readLock.lock();
        try {
            moteDeploymentConfigs = MoteDeploymentConfigurationManagerImpl.
                             getInstance().getMoteDeploymentConfigurations(id);

            for (MoteDeploymentConfiguration i : moteDeploymentConfigs) {
                StringBuffer          output;
                Mote                  mote;
                MoteType              type;
                MoteTestbedAssignment mtba;

                output = new StringBuffer();
                mote   = MoteManagerImpl.getInstance().getMote(i.getMoteID());
                type   = MoteTypeManagerImpl.getInstance().
                                         getMoteType(mote.getMoteTypeID());
                mtba   = MoteTestbedAssignmentManagerImpl.getInstance().
                                         getMoteTestbedAssignment(mote.getID());

                log.info("Installing\n" +
                         " program:  " + i.getProgramID() + "\n" +
                         " on mote:  " + mote.getID()     + "\n" +
                         " type:     " + type.getName()   + "\n" +
                         " address:  " + mtba.getMoteAddress());


                 ProgramManagerImpl.getInstance().installProgram(
                                                        mtba.getMoteAddress(),
                                                        mote.getMoteSerialID(),
                                                        type.getTosPlatform(),
                                                        i.getProgramID(),
                                                        output);
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deployConfiguration", ex);

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } finally {
            readLock.unlock();
        }
    }


    private ProjectDeploymentConfigurationManagerImpl() throws RemoteException {
        super();

        try {
            this.managerLock      = new ReentrantReadWriteLock(true);
            this.readLock         = managerLock.readLock();
            this.writeLock        = managerLock.writeLock();
            projDepConfigAdapter  = AdapterFactory.
                   createProjectDeploymentConfigurationAdapter(AdapterType.SQL);
            projDepConfigs        = projDepConfigAdapter.
                   readProjectDeploymentConfigurations();

            log.debug("ProjectDeploymentConfigurations read:\n" +
                      projDepConfigs);
        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (Exception ex) {
            log.error("Exception in ProjectDeploymentConfigurationManagerImpl",
                      ex);

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }
}