/* $Id$ */
/*
 * ProgramManager.java
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
package edu.clemson.cs.nestbed.common.management;


import java.rmi.RemoteException;
import java.util.List;

import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.util.RemoteObservable;


public interface ProgramManager extends RemoteObservable {

    public enum Message {
        NEW_PROGRAM,
        DELETE_PROGRAM,
        COMPILE_STARTED,
        COMPILE_PROGRESS,
        COMPILE_COMPLETED,
        PROGRAM_INSTALL_BEGIN,
        PROGRAM_INSTALL_SUCCESS,
        PROGRAM_INSTALL_FAILURE
    }


    public Program       getProgram(int id)            throws RemoteException;


    public List<Program> getProgramList(int projectID) throws RemoteException;


    public void createNewProgram(int    testbedID, int    projectID,
                                 String name,      String description,
                                 byte[] buffer,    String tosPlatform)
                                                       throws RemoteException;


    public void deleteProgram(int programID)           throws RemoteException;


    public void installProgram(int          moteAddress, String moteSerialID,
                               String       tosPlatform, int    programID,
                               StringBuffer output)      throws RemoteException;
}
