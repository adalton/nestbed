/*
 * MoteDeploymentConfigurationManagerImpl.java
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
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.MoteDeploymentConfigurationAdapter;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class MoteDeploymentConfigurationManagerImpl
                                extends    RemoteObservableImpl
                                implements MoteDeploymentConfigurationManager {

    private final static Log log =
                LogFactory.getLog(MoteDeploymentConfigurationManagerImpl.class);


    private MoteDeploymentConfigurationAdapter        moteDepConfigAdapter;
    private Map<Integer, MoteDeploymentConfiguration> moteDepConfigs;


    public MoteDeploymentConfigurationManagerImpl() throws RemoteException {
        super();

        try {
            moteDepConfigAdapter =
                AdapterFactory.createMoteDeploymentConfigurationAdapter(
                                                            AdapterType.SQL);
            moteDepConfigs       =
                    moteDepConfigAdapter.readMoteDeploymentConfigurations();

            log.debug("MoteDeploymentConfigurations read:\n" + moteDepConfigs);
        } catch (AdaptationException ex) {
            log.error("AdaptationException: ", ex);
            throw new RemoteException("AdaptationException:", ex);
        }
    }


    public MoteDeploymentConfiguration
                            getMoteDeploymentConfiguration(int projDepConfID,
                                                           int moteID)
                                                        throws RemoteException {

        log.debug("getMoteDeploymentConfiguration() called.");

        return findMoteDeploymentConfiguration(projDepConfID, moteID);
    }


    public List<MoteDeploymentConfiguration>
                    getMoteDeploymentConfigurations(int projDepConfID)
                                                       throws RemoteException {
        List<MoteDeploymentConfiguration> mdcs;
        mdcs = new ArrayList<MoteDeploymentConfiguration>();

        synchronized (this) {
            for (MoteDeploymentConfiguration i : moteDepConfigs.values()) {
                if (i.getProjectDeploymentConfigurationID() == projDepConfID) {
                    mdcs.add(i);
                }
            }
        }

        return mdcs;
    }


    public void setMoteDeploymentConfiguration(int projectDepConfID,
                                               int moteID,
                                               int programID,
                                               int radioPowerLevel)
                                                        throws RemoteException {
        try {
            MoteDeploymentConfiguration mdc;

            synchronized (this) {
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
            }
            notifyObservers(Message.NEW_CONFIG, mdc);
        } catch (AdaptationException ex) {
            log.error("AdaptationException:", ex);
            throw new RemoteException("AdaptationException:", ex);
        }
    }


    public void cloneMoteDeploymentConfigurations(int origProjDepConfigID,
                                                  int newProjDepConfigID)
                                                        throws RemoteException {
        try {
            List<MoteDeploymentConfiguration> newList;
            newList = new ArrayList<MoteDeploymentConfiguration>();

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

            for (MoteDeploymentConfiguration i : newList) {
                synchronized (this) {
                    moteDepConfigs.put(i.getID(), i);
                }
                notifyObservers(Message.NEW_CONFIG, i);
            }
        } catch (AdaptationException ex) {
            log.error("AdaptationException:\n", ex);
            throw new RemoteException("AdaptationException:", ex);
        }
    }


    public void deleteMoteDeploymentConfiguration(int id)
                                                        throws RemoteException {
        try {
            log.info("Deleting mote deployment configuration with id: " +
                     id);
            MoteDeploymentConfiguration mdc;

            mdc = moteDepConfigAdapter.deleteMoteDeploymentConfiguration(id);
            synchronized (this) {
                moteDepConfigs.remove(mdc.getID());
            }

            notifyObservers(Message.DELETE_CONFIG, mdc);
        } catch (AdaptationException ex) {
            log.error("AdaptationException:", ex);
            throw new RemoteException("AdaptationException:", ex);
        }
    }


    public synchronized void deleteConfigsForProgram(int programID)
                                                        throws RemoteException {
        MoteDeploymentConfiguration[] mdc = moteDepConfigs.values().toArray(
                       new MoteDeploymentConfiguration[moteDepConfigs.size()]);

        for (int i = 0; i < mdc.length; ++i) {
            if (mdc[i].getProgramID() == programID) {
                deleteMoteDeploymentConfiguration(mdc[i].getID());
            }
        }
    }


    private synchronized MoteDeploymentConfiguration
                    findMoteDeploymentConfiguration(int projDepConfID,
                                                    int moteID) {
        MoteDeploymentConfiguration moteDeploymentConfiguration = null;

        for (MoteDeploymentConfiguration i : moteDepConfigs.values()) {
            if (   (i.getProjectDeploymentConfigurationID() == projDepConfID)
                && (i.getMoteID()                           == moteID) ) {
                moteDeploymentConfiguration = i;
            }
        }

        return moteDeploymentConfiguration;
    }
}
