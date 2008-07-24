/* $Id$ */
/*
 * ProgramProbeManager.java
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * School of Computing
 * Clemson University
 * Andrew R. Dalton and Jason O. Hallstrom
 *
 * This  is free software; you can redistribute it and/or
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
package edu.clemson.cs.nestbed.common.management.instrumentation;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.trace.StaticProgramData;

import edu.clemson.cs.nesctk.tools.trace.TraceData;


public interface ProgramProbeManager extends Remote {
    public Map<String, List<String>> getModuleFunctionListMap(int programID)
                                                        throws RemoteException;

    public Map<Integer, List<TraceData>> collectData(List<MoteTestbedAssignment> interestingMotes) throws RemoteException;

    public void insertProbes(int                       programID,
                             Map<String, List<String>> moduleIncludeMap) throws RemoteException;

    public StaticProgramData getStaticData(Program program) throws RemoteException;
}
