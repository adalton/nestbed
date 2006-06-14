/* $Id$ */
/*
 * MoteDeploymentConfigurationManager.java
 *
 * Network Embedded System Testbed (NESTBed)
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
package edu.clemson.cs.nestbed.common.management.configuration;


import java.rmi.RemoteException;
import java.util.List;

import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.util.RemoteObservable;


public interface MoteDeploymentConfigurationManager extends RemoteObservable {

    public enum Message {
        NEW_CONFIG,
        DELETE_CONFIG,
    }

    public MoteDeploymentConfiguration
            getMoteDeploymentConfiguration(int projDepConfID,
                                           int moteID)  throws RemoteException;

    public MoteDeploymentConfiguration
                    getMoteDeploymentConfigurationByProgramID(int moteID,
                                                              int programID)
                                                        throws RemoteException;

    public List<MoteDeploymentConfiguration>
            getMoteDeploymentConfigurations(int projDepConfID)
                                                        throws RemoteException;


    public void setMoteDeploymentConfiguration(int projectDepConfID,
                                               int moteID,
                                               int programID,
                                               int radioPowerLevel)
                                                        throws RemoteException;


    public void cloneMoteDeploymentConfigurations(int origProjDepConfigID,
                                                  int newProjDepConfigID)
                                                        throws RemoteException;
                                                 

    public void deleteMoteDeploymentConfiguration(int id)
                                                        throws RemoteException;


    public void deleteConfigsForProgram(int programID)  throws RemoteException;


    public void deleteMoteDeploymentConfigWithProjectDepConfID(int pdcID)
                                                        throws RemoteException;
}
