/* $Id$ */
/*
 * ProgramProfilingSymbolManagerImpl.java
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

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProgramProfilingSymbolAdapter;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramProfilingSymbolManagerImpl
                                    extends    RemoteObservableImpl
                                    implements ProgramProfilingSymbolManager {

    private final static ProgramProfilingSymbolManager instance;
    private final static Log log = LogFactory.getLog(
                                       ProgramProfilingSymbolManagerImpl.class);

    private ReadWriteLock                        managerLock;
    private Lock                                 readLock;
    private Lock                                 writeLock;
    private ProgramProfilingSymbolAdapter        progProfSymbolAdapter;
    private Map<Integer, ProgramProfilingSymbol> progProfSymbols;

    static {
        ProgramProfilingSymbolManagerImpl impl = null;

        try {
            impl = new ProgramProfilingSymbolManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static ProgramProfilingSymbolManager getInstance() {
        return instance;
    }


    public List<ProgramProfilingSymbol>
                            getProgramProfilingSymbols(int configID)
                                                       throws RemoteException {
        log.debug("getProgramProfilingSymbols() called.");
        List<ProgramProfilingSymbol> symbolList;
        symbolList = new ArrayList<ProgramProfilingSymbol>();

        readLock.lock();
        try {
            for (ProgramProfilingSymbol i : progProfSymbols.values()) {
                if (i.getProjectDeploymentConfigurationID() == configID ) {
                    symbolList.add(i);
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in getProgramProfilingSymbols";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return symbolList;
    }


    public List<ProgramProfilingSymbol>
                        getProgramProfilingSymbols(int configID,
                                                   int programID)
                                                       throws RemoteException {
        List<ProgramProfilingSymbol> symbolList     =
                                    getProgramProfilingSymbols(configID);

        List<ProgramProfilingSymbol> symbolsForProg =
                                    new ArrayList<ProgramProfilingSymbol>();

        try {

            for (ProgramProfilingSymbol i : symbolList) {
                ProgramSymbol programSymbol = ProgramSymbolManagerImpl.
                                                getInstance().
                                                    getProgramSymbol(
                                                        i.getProgramSymbolID());

                if (programSymbol.getProgramID() == programID) {
                    symbolsForProg.add(i);
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in getProgramProfilingSymbols";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }

        return symbolsForProg;
    }


    public ProgramProfilingSymbol getProgramProfilingSymbol(int id)
                                                    throws RemoteException {
        ProgramProfilingSymbol symbol = null;

        readLock.lock();
        try {
            symbol = progProfSymbols.get(id);
        } finally {
            readLock.unlock();
        }

        return symbol;
    }


    public void createNewProfilingSymbol(int configID, int programSymbolID)
                                                       throws RemoteException {
        try {
            log.info(
             "Request to create new ProgramProfilingSymbol:\n" +
              "  projectDeploymentConfigurationID:  " + configID        + "\n" +
              "  programSymbolID:                   " + programSymbolID);

            ProgramProfilingSymbol profilingSymbol =
                progProfSymbolAdapter.createNewProfilingSymbol(configID,
                                                               programSymbolID);

            writeLock.lock();
            try {
                progProfSymbols.put(profilingSymbol.getID(), profilingSymbol);
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.NEW_SYMBOL, profilingSymbol);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in createNewProfilingSymbol";
            throw new RemoteException(msg, ex);
        }
    }


    public void cloneProfilingSymbol(int srcConfigID, int newConfigID)
                                                       throws RemoteException {
        try {
            log.info("Request to clone ProgramProfilingSymbol with " +
                     "ProjectDeploymentconfigurationID:  " + srcConfigID);

            List<ProgramProfilingSymbol> newList;
            newList = new ArrayList<ProgramProfilingSymbol>();

            for (ProgramProfilingSymbol i : progProfSymbols.values()) {
                if (i.getProjectDeploymentConfigurationID() == srcConfigID) {
                    ProgramProfilingSymbol profilingSymbol =
                        progProfSymbolAdapter.createNewProfilingSymbol(
                                                        newConfigID,
                                                        i.getProgramSymbolID());
                    newList.add(profilingSymbol);
                }
            }

            writeLock.lock();
            try {
                for (ProgramProfilingSymbol i : newList) {
                    progProfSymbols.put(i.getID(), i);
                }
            } finally {
                writeLock.unlock();
            }

            for (ProgramProfilingSymbol i : newList) {
                notifyObservers(Message.NEW_SYMBOL, i);
            }
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in cloneProfilingSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteProfilingSymbol(int symbolID) throws RemoteException {
        try {
            log.info("Request to delete ProgramProfilingSymbol with id: " +
                     symbolID);

            ProgramProfilingSymbol profilingSymbol =
                        progProfSymbolAdapter.deleteProfilingSymbol(symbolID);

            writeLock.lock();
            try {
                progProfSymbols.remove(profilingSymbol.getID());
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.DELETE_SYMBOL, profilingSymbol);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in deleteProfilingSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteProfilingSymbolWithID(int programSymbolID)
                                                        throws RemoteException {
        log.info("Request to delete ProgramProfilingSymbol with " +
                 "programSymbolID: " + programSymbolID);

        try {
            List<ProgramProfilingSymbol> list;
            list  = new ArrayList<ProgramProfilingSymbol>(
                                                    progProfSymbols.values());

            for (ProgramProfilingSymbol i : list) {
                if (i.getProgramSymbolID() == programSymbolID) {
                    deleteProfilingSymbol(i.getID());
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deleteProfilingSymbolWithID");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }


    public void deleteProfilingSymbolWithProjectDepConfID(int pdcID)
                                                        throws RemoteException {
        log.info("Request to delete ProgramProfilingSymbol with " +
                 "projectDeploymentConfigurationID: " + pdcID);

        try {
            List<ProgramProfilingSymbol> list;
            list  = new ArrayList<ProgramProfilingSymbol>(
                                                    progProfSymbols.values());

            for (ProgramProfilingSymbol i : list) {
                if (i.getProjectDeploymentConfigurationID() == pdcID) {
                    deleteProfilingSymbol(i.getID());
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deleteProfilingSymbolWithID");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }


    public void updateProgramProfilingSymbol(int id, int configID,
                                             int programSymbolID) 
                                                        throws RemoteException {
        try {
            ProgramProfilingSymbol pps = progProfSymbols.get(id);

            if (pps != null) {
                log.info("Updating existing program profiling symbol");

                pps = progProfSymbolAdapter.updateProgramProfilingSymbol(
                                                    id,              configID,
                                                    programSymbolID);
                writeLock.lock();
                try {
                    progProfSymbols.put(pps.getID(), pps);
                } finally {
                    writeLock.unlock();
                }

                notifyObservers(Message.NEW_SYMBOL, pps);
            } else {
                log.error("Unable to update symbol with id: " + id);
            }
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in updateProgramProfilingSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    private ProgramProfilingSymbolManagerImpl() throws RemoteException {
        super();

        try {
            this.managerLock      = new ReentrantReadWriteLock(true);
            this.readLock         = managerLock.readLock();
            this.writeLock        = managerLock.writeLock();
            progProfSymbolAdapter =
                        AdapterFactory.createProgramProfilingSymbolAdapter(
                                                            AdapterType.SQL);
            progProfSymbols       =
                        progProfSymbolAdapter.readProgramProfilingSymbols();

            log.debug("ProgramProfilingSymbols read:\n" + progProfSymbols);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in ProgramProfilingSymbolManagerImpl";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }
}
