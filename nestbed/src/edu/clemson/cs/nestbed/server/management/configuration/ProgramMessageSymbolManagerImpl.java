/* $Id$ */
/*
 * ProgramMessageSymbolManagerImpl.java
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
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
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

    private ReadWriteLock                        managerLock;
    private Lock                                 readLock;
    private Lock                                 writeLock;
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


    public List<ProgramMessageSymbol> getProgramMessageSymbolList()
                                                       throws RemoteException {
        log.debug("getProgramMessageSymbolList() called");
        List<ProgramMessageSymbol> pmsList = null;

        readLock.lock();
        try {
            pmsList = new ArrayList<ProgramMessageSymbol>(
                                            programMessageSymbols.values());
        } catch (Exception ex) {
            String msg = "Exception in getProgramMessageSymbolList";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return pmsList;
    }


    public List<ProgramMessageSymbol> getProgramMessageSymbols(int programID)
                                                       throws RemoteException {
        log.debug("getProgramMessageSymbols() called");

        List<ProgramMessageSymbol> programMessageSymbolList;
        programMessageSymbolList = new ArrayList<ProgramMessageSymbol>();

        readLock.lock();
        try {
            for (ProgramMessageSymbol i : programMessageSymbols.values()) {
                if (i.getProgramID() == programID) {
                    programMessageSymbolList.add(i);
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in getProgramMessageSymbols";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return programMessageSymbolList;
    }


    public void addProgramMessageSymbol(int    programID, String name,
                                        byte[] bytecode)
                                                      throws RemoteException {
        writeLock.lock();
        try {
            log.debug("addProgramMessageSymbol():\n" +
                      "    programID:       " + programID + "\n" +
                      "    name:            " + name      + "\n" +
                      "    bytecode.length: " + bytecode.length);

            ProgramMessageSymbol pmt;
            pmt = programMessageSymbolAdapter.addProgramMessageSymbol(programID,
                                                                      name,
                                                                      bytecode);

            programMessageSymbols.put(pmt.getID(), pmt);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in addProgramMessageSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            writeLock.unlock();
        }
    }


    public ProgramMessageSymbol getProgramMessageSymbol(int id)
                                                       throws RemoteException {
        readLock.lock();
        try {
            return programMessageSymbols.get(id);
        } finally {
            readLock.unlock();
        }
    }


    public void deleteProgramMessageSymbol(int id) throws RemoteException {
        try {
            ProgramMessageSymbol pmt;
            log.info("Deleting program message symbol with id: " + id);

            cleanupProgramProfilingMessageSymbols(id);

            writeLock.lock();
            try {
                pmt = programMessageSymbolAdapter.deleteProgramMessageSymbol(id);
                programMessageSymbols.remove(pmt.getID());
            } finally {
                writeLock.unlock();
            }

            // notifyObservers(..., pmt);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in addProgramMessageSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteSymbolsForProgram(int programID) throws RemoteException {
        ProgramMessageSymbol[] pmt = null;
        
        readLock.lock();
        try {
            pmt = programMessageSymbols.values().toArray(
                       new ProgramMessageSymbol[programMessageSymbols.size()]);
        } finally {
            readLock.unlock();
        }

        try {
            for (int i = 0; i < pmt.length; ++i) {
                if (pmt[i].getProgramID() == programID) {
                    ProgramProfilingMessageSymbolManagerImpl.getInstance().
                                    deleteProgProfMsgSymsFor(pmt[i].getID());
                    deleteProgramMessageSymbol(pmt[i].getID());
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in deleteSymbolsForProgram";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    private void cleanupProgramProfilingMessageSymbols(int id)
                                                        throws RemoteException {
        ProgramProfilingMessageSymbolManager ppmsm;
        ppmsm = ProgramProfilingMessageSymbolManagerImpl.getInstance();

        ppmsm.deleteProgramProfilingMessageSymbolWithProgMsgSymID(id);
    }


    private ProgramMessageSymbolManagerImpl() throws RemoteException {
        super();

        try {
            this.managerLock            = new ReentrantReadWriteLock(true);
            this.readLock               = managerLock.readLock();
            this.writeLock              = managerLock.writeLock();
            programMessageSymbolAdapter =
                            AdapterFactory.createProgramMessageSymbolAdapter(
                                                              AdapterType.SQL);

            programMessageSymbols       =
                        programMessageSymbolAdapter.readProgramMessageSymbols();

            log.debug("ProgramMessageSymbols read:\n" + programMessageSymbols);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in addProgramMessageSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }
}
