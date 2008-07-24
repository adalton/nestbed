/* $Id$ */
/*
 * MoteDeploymentConfigurationManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management.configuration;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.MoteDeploymentConfigurationAdapter;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class MoteDeploymentConfigurationManagerImpl
                                extends    RemoteObservableImpl
                                implements MoteDeploymentConfigurationManager {

    private final static MoteDeploymentConfigurationManager instance;
    private final static Log log = LogFactory.getLog(
                                MoteDeploymentConfigurationManagerImpl.class);

    private ReadWriteLock                             managerLock;
    private Lock                                      readLock;
    private Lock                                      writeLock;
    private MoteDeploymentConfigurationAdapter        moteDepConfigAdapter;
    private Map<Integer, MoteDeploymentConfiguration> moteDepConfigs;


    static {
        MoteDeploymentConfigurationManagerImpl impl = null;

        try {
            impl = new MoteDeploymentConfigurationManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static MoteDeploymentConfigurationManager getInstance() {
        return instance;
    }


    public MoteDeploymentConfiguration
                            getMoteDeploymentConfiguration(int projDepConfID,
                                                           int moteID)
                                                        throws RemoteException {

        log.debug("getMoteDeploymentConfiguration() called.");

        return findMoteDeploymentConfiguration(projDepConfID, moteID);
    }


    public MoteDeploymentConfiguration
                    getMoteDeploymentConfigurationByProgramID(int moteID,
                                                              int programID)
                                                        throws RemoteException {
        MoteDeploymentConfiguration mdc = null;

        readLock.lock();
        try {
            for (MoteDeploymentConfiguration i : moteDepConfigs.values()) {
                if (       (i.getMoteID()    == moteID)
                        && (i.getProgramID() == programID) ) {
                    mdc = i;
                    break;
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in getMoteDeploymentConfigurationByProgramID";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return mdc;
    }


    public List<MoteDeploymentConfiguration>
                    getMoteDeploymentConfigurations(int projDepConfID)
                                                       throws RemoteException {
        List<MoteDeploymentConfiguration> mdcs;
        mdcs = new ArrayList<MoteDeploymentConfiguration>();

        readLock.lock();
        try {
            for (MoteDeploymentConfiguration i : moteDepConfigs.values()) {
                if (i.getProjectDeploymentConfigurationID() == projDepConfID) {
                    mdcs.add(i);
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in getMoteDeploymentConfigurations";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return mdcs;
    }


    public void setMoteDeploymentConfiguration(int projectDepConfID,
                                               int moteID,
                                               int programID,
                                               int radioPowerLevel)
                                                        throws RemoteException {
        writeLock.lock();
        try {
            MoteDeploymentConfiguration mdc;
            mdc = findMoteDeploymentConfiguration(projectDepConfID, moteID);

            if (mdc != null) {
                log.info("Updating existing mote deployment configuration");
                mdc =
                    moteDepConfigAdapter.updateMoteDeploymentConfiguration(
                                                    mdc.getID(), programID,
                                                    radioPowerLevel);
            } else {
                log.info("Adding new mote deployment configuration");
                mdc = moteDepConfigAdapter.addMoteDeploymentConfiguration(
                                                projectDepConfID, moteID,
                                                programID, radioPowerLevel);
            }

            moteDepConfigs.put(mdc.getID(), mdc);
            notifyObservers(Message.NEW_CONFIG, mdc);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in setMoteDeploymentConfiguration";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            writeLock.unlock();
        }
    }


    public void cloneMoteDeploymentConfigurations(int origProjDepConfigID,
                                                  int newProjDepConfigID)
                                                        throws RemoteException {
        try {
            List<MoteDeploymentConfiguration> newList;
            newList = new ArrayList<MoteDeploymentConfiguration>();

            readLock.lock();
            try {
                for (MoteDeploymentConfiguration i : moteDepConfigs.values()) {
                    if (        i.getProjectDeploymentConfigurationID()
                             == origProjDepConfigID) {
                        MoteDeploymentConfiguration mdc =
                            moteDepConfigAdapter.addMoteDeploymentConfiguration(
                                                       newProjDepConfigID,
                                                       i.getMoteID(),
                                                       i.getProgramID(),
                                                       i.getRadioPowerLevel());
                        newList.add(mdc);
                    }
                }
            } finally {
                readLock.unlock();
            }

            writeLock.lock();
            try {
                for (MoteDeploymentConfiguration i : newList) {
                    moteDepConfigs.put(i.getID(), i);
                }
            } finally {
                writeLock.unlock();
            }

            for (MoteDeploymentConfiguration i : newList) {
                notifyObservers(Message.NEW_CONFIG, i);
            }
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in cloneMoteDeploymentConfigurations";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteMoteDeploymentConfiguration(int id)
                                                        throws RemoteException {
        try {
            log.info("Deleting mote deployment configuration with id: " + id);
            MoteDeploymentConfiguration mdc = null;

            writeLock.lock();
            try {
                mdc = moteDepConfigAdapter.deleteMoteDeploymentConfiguration(id);
                moteDepConfigs.remove(mdc.getID());
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.DELETE_CONFIG, mdc);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in deleteMoteDeploymentConfiguration";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteConfigsForProgram(int programID) throws RemoteException {
        MoteDeploymentConfiguration[] mdc = null;
        
        readLock.lock();
        try {
            mdc = moteDepConfigs.values().toArray(
                       new MoteDeploymentConfiguration[moteDepConfigs.size()]);
        } finally {
            readLock.unlock();
        }

        for (int i = 0; i < mdc.length; ++i) {
            if (mdc[i].getProgramID() == programID) {
                deleteMoteDeploymentConfiguration(mdc[i].getID());
            }
        }
    }


    public void deleteMoteDeploymentConfigWithProjectDepConfID(int pdcID)
                                                        throws RemoteException {
        log.info("Request to delete MoteDeploymentConfiguration with " +
                 "ProjectDeploymentConfigurationID: " + pdcID);

        try {
            List<MoteDeploymentConfiguration> list;
            list = new ArrayList<MoteDeploymentConfiguration>(
                                                    moteDepConfigs.values());
            for (MoteDeploymentConfiguration i : list) {
                if (i.getProjectDeploymentConfigurationID() == pdcID) {
                    deleteMoteDeploymentConfiguration(i.getID());
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in deleteMoteDeploymentConfiguration";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    private MoteDeploymentConfiguration findMoteDeploymentConfiguration(
                                                            int projDepConfID,
                                                            int moteID) {
        MoteDeploymentConfiguration moteDeploymentConfiguration = null;

        readLock.lock();
        try {
            for (MoteDeploymentConfiguration i : moteDepConfigs.values()) {
                if (  (i.getProjectDeploymentConfigurationID() == projDepConfID)
                   && (i.getMoteID()                           == moteID) ) {
                    moteDeploymentConfiguration = i;
                    break;
                }
            }
        } finally {
            readLock.unlock();
        }

        return moteDeploymentConfiguration;
    }


    private MoteDeploymentConfigurationManagerImpl() throws RemoteException {
        super();

        try {
            managerLock          = new ReentrantReadWriteLock(true);
            readLock             = managerLock.readLock();
            writeLock            = managerLock.writeLock();
            moteDepConfigAdapter =
                AdapterFactory.createMoteDeploymentConfigurationAdapter(
                                                            AdapterType.SQL);
            moteDepConfigs       =
                    moteDepConfigAdapter.readMoteDeploymentConfigurations();

            log.debug("MoteDeploymentConfigurations read:\n" + moteDepConfigs);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in MoteDeploymentConfigurationManagerImpl";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }
}
