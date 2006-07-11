/* $Id$ */
/*
 * ProgramSymbolManagerImpl.java
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

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProgramSymbolAdapter;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramSymbolManagerImpl extends    RemoteObservableImpl
                                      implements ProgramSymbolManager {
    private final static ProgramSymbolManager instance;
    private final static Log log = LogFactory.getLog(
                                                ProgramSymbolManagerImpl.class);

    private ReadWriteLock                 managerLock;
    private Lock                          readLock;
    private Lock                          writeLock;
    private ProgramSymbolAdapter          programSymbolAdapter;
    private Map<Integer, ProgramSymbol>   programSymbols;

    static {
        ProgramSymbolManagerImpl impl = null;

        try {
            impl = new ProgramSymbolManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static ProgramSymbolManager getInstance() {
        return instance;
    }


    public ProgramSymbol getProgramSymbol(int id) throws RemoteException {
        readLock.lock();
        try {
            return programSymbols.get(id);
        } finally {
            readLock.unlock();
        }
    }


    public List<ProgramSymbol> getProgramSymbols(int programID)
                                                    throws RemoteException {
        log.debug("getProgramSymbols() called.");

        List<ProgramSymbol> programSymbolList;
        programSymbolList = new ArrayList<ProgramSymbol>();

        readLock.lock();
        try {
            for (ProgramSymbol i : programSymbols.values()) {
                if (i.getProgramID() == programID) {
                    programSymbolList.add(i);
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in getProgramSymbols";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return programSymbolList;
    }


    public ProgramSymbol deleteProgramSymbol(int id) throws RemoteException {
        try {
            log.debug("Request to delete program symbol with id: " + id);

            cleanupProgramProfilingSymbol(id);

            ProgramSymbol programSymbol =
                                programSymbolAdapter.deleteProgramSymbol(id);

            writeLock.lock();
            try {
                programSymbols.remove(programSymbol.getID());
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.DELETE_SYMBOL, programSymbol);

            return programSymbol;
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in deleteProgramSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void createProgramSymbol(int programID, String module, String symbol)
                                                        throws RemoteException {
        writeLock.lock();
        try {
            log.debug(module + "." + symbol);
            ProgramSymbol programSymbol =
                        programSymbolAdapter.createNewProgramSymbol(programID,
                                                                    module,
                                                                    symbol);

            programSymbols.put(programSymbol.getID(), programSymbol);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in createProgramSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            writeLock.unlock();
        }
    }


    private void cleanupProgramProfilingSymbol(int programSymbolID) 
                                                        throws RemoteException {
        writeLock.lock();
        try {
            ProgramProfilingSymbolManagerImpl.getInstance().
                                   deleteProfilingSymbolWithID(programSymbolID);
            programSymbols.remove(programSymbolID);
        } finally {
            writeLock.unlock();
        }
    }


    private ProgramSymbolManagerImpl() throws RemoteException {
        super();

        try {
            this.managerLock     = new ReentrantReadWriteLock(true);
            this.readLock        = managerLock.readLock();
            this.writeLock       = managerLock.writeLock();
            programSymbolAdapter = AdapterFactory.createProgramSymbolAdapter(
                                                               AdapterType.SQL);
            programSymbols       = programSymbolAdapter.readProgramSymbols();

            log.debug("ProgramSymbols read:\n" + programSymbols);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in ProgramSymbolManagerImpl";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }
}
