/* $Id$ */
/*
 * ProgramSymbolManager.java
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
package edu.clemson.cs.nestbed.common.management.configuration;


import java.rmi.RemoteException;
import java.util.List;

import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.common.util.RemoteObservable;


public interface ProgramSymbolManager extends RemoteObservable {

    public enum Message {
        NEW_SYMBOLS,
        DELETE_SYMBOL
    }


    public ProgramSymbol getProgramSymbol(int id)       throws RemoteException;


    public List<ProgramSymbol> getProgramSymbols(int programID)
                                                        throws RemoteException;


    public ProgramSymbol deleteProgramSymbol(int programID)
                                                        throws RemoteException;


    public void createProgramSymbol(int programID, String module, String symbol,
                                    int address,   int    size)
                                                        throws RemoteException;
}
