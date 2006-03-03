/* $Id$ */
/*
 * ProgramMessageSymbolManagerImpl.java
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

import edu.clemson.cs.nestbed.common.management.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProgramMessageSymbolAdapter;


public class ProgramMessageSymbolManagerImpl
                                        extends    UnicastRemoteObject
                                        implements ProgramMessageSymbolManager {
    private final static ProgramMessageSymbolManager instance;
    private final static Log log = LogFactory.getLog(
                                        ProgramMessageSymbolManagerImpl.class);

    private ProgramMessageSymbolAdapter          programMessageSymbolAdapter;
    private Map<Integer, ProgramMessageSymbol>   programMessageSymbols;

    static {
        ProgramMessageSymbolManagerImpl impl = null;

        try {
            impl = new ProgramMessageSymbolManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static ProgramMessageSymbolManager getInstance() {
        return instance;
    }


    public synchronized List<ProgramMessageSymbol>
                                getProgramMessageSymbolList()
                                                       throws RemoteException {
        log.debug("getProgramMessageSymbolList() called");
        List<ProgramMessageSymbol> pmsList = null;

        try {
            pmsList = new ArrayList<ProgramMessageSymbol>(
                                            programMessageSymbols.values());
        } catch (Exception ex) {
            throw new RemoteException(ex.toString());
        }

        return pmsList;
    }


    public synchronized List<ProgramMessageSymbol>
                                getProgramMessageSymbols(int programID)
                                                       throws RemoteException {
        log.debug("getProgramMessageSymbols() called");

        List<ProgramMessageSymbol> programMessageSymbolList;
        programMessageSymbolList = new ArrayList<ProgramMessageSymbol>();

        try {
            for (ProgramMessageSymbol i : programMessageSymbols.values()) {
                if (i.getProgramID() == programID) {
                    programMessageSymbolList.add(i);
                }
            }
        } catch (Exception ex) {
            log.error("Exception in getProgramMessageSymbols", ex);
            throw new RemoteException(ex.toString());
        }

        return programMessageSymbolList;
    }


    public synchronized void addProgramMessageSymbol(int    programID,
                                                     String name,
                                                     byte[] bytecode)
                                                      throws RemoteException {
        try {
            log.debug("getProgramMessageSymbolList() called");

            ProgramMessageSymbol pmt;
            pmt = programMessageSymbolAdapter.addProgramMessageSymbol(programID,
                                                                      name,
                                                                      bytecode);

            programMessageSymbols.put(pmt.getID(), pmt);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in addProgramMessageSymbol", ex);
            throw new RemoteException(ex.toString());
        }
    }


    public ProgramMessageSymbol getProgramMessageSymbol(int id)
                                                       throws RemoteException {
        return programMessageSymbols.get(id);
    }


    public void deleteProgramMessageSymbol(int id) throws RemoteException {
        try {
            ProgramMessageSymbol pmt;
            log.info("Deleting program message tyupe with id: " + id);

            pmt = programMessageSymbolAdapter.deleteProgramMessageSymbol(id);
            synchronized (this) {
                programMessageSymbols.remove(pmt.getID());
            }

            // notifyObservers(..., pmt);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in addProgramMessageSymbol", ex);
            throw new RemoteException(ex.toString());
        }
    }


    public void deleteSymbolsForProgram(int programID) throws RemoteException {
        ProgramMessageSymbol[] pmt = programMessageSymbols.values().toArray(
                       new ProgramMessageSymbol[programMessageSymbols.size()]);
        try {
            for (int i = 0; i < pmt.length; ++i) {
                if (pmt[i].getProgramID() == programID) {
                    ProgramProfilingMessageSymbolManagerImpl.getInstance().
                                    deleteProgProfMsgSymsFor(pmt[i].getID());
                    deleteProgramMessageSymbol(pmt[i].getID());
                }
            }
        } catch (Exception ex) {
            log.error("Exception in deleteSymbolsForProgram", ex);
            throw new RemoteException(ex.toString());
        }
    }


    private ProgramMessageSymbolManagerImpl() throws RemoteException {
        super();

        try {
            programMessageSymbolAdapter =
                            AdapterFactory.createProgramMessageSymbolAdapter(
                                                              AdapterType.SQL);

            programMessageSymbols       =
                        programMessageSymbolAdapter.readProgramMessageSymbols();

            log.debug("ProgramMessageSymbols read:\n" + programMessageSymbols);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in addProgramMessageSymbol", ex);
            throw new RemoteException(ex.toString());
        }
    }
}
