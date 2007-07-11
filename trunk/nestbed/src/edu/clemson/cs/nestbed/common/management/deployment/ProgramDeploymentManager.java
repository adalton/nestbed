/* $Id$ */
/*
 * ProgramDeploymentManager.java
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
package edu.clemson.cs.nestbed.common.management.deployment;


import java.rmi.RemoteException;

import edu.clemson.cs.nestbed.common.util.RemoteObservable;


public interface ProgramDeploymentManager extends RemoteObservable {
    public enum Message {
        PROGRAM_INSTALL_BEGIN,
        PROGRAM_INSTALL_SUCCESS,
        PROGRAM_INSTALL_FAILURE
    }

    public void deployConfiguration(int id) throws RemoteException;

    public void installProgram(int          moteAddress, String moteSerialID,
                               String       tosPlatform, int    programID,
                               StringBuffer output)      throws RemoteException;

    public void resetMote(int moteAddress, String moteSerialID, int programID)
                                                        throws RemoteException;
}
