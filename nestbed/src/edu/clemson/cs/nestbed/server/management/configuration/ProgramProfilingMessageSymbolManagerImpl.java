/* $Id$ */
/*
 * ProgramProfilingMessageSymbolManagerImpl.java
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

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingMessageSymbol;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProgramProfilingMessageSymbolAdapter;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramProfilingMessageSymbolManagerImpl
                               extends    RemoteObservableImpl
                               implements ProgramProfilingMessageSymbolManager {

    private final static ProgramProfilingMessageSymbolManager instance;
    private final static Log log = LogFactory.getLog(
                            ProgramProfilingMessageSymbolManagerImpl.class);

    private ReadWriteLock                               managerLock;
    private Lock                                        readLock;
    private Lock                                        writeLock;
    private ProgramProfilingMessageSymbolAdapter        ppmsAdapter;
    private Map<Integer, ProgramProfilingMessageSymbol> ppmSymbols;

    static {
        ProgramProfilingMessageSymbolManagerImpl impl = null;

        try {
            impl = new ProgramProfilingMessageSymbolManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static ProgramProfilingMessageSymbolManager getInstance() {
        return instance;
    }


    public List<ProgramProfilingMessageSymbol>
                                getProgramProfilingMessageSymbolList()
                                                        throws RemoteException {
        log.debug("getProgramProfilingMessageSymbolList() called");
        List<ProgramProfilingMessageSymbol> ppmsList = null;

        readLock.lock();
        try {
            ppmsList = new ArrayList<ProgramProfilingMessageSymbol>(
                                                    ppmSymbols.values());
        } catch (Exception ex) {
            String msg = "Exception in getProgramProfilingMessageSymbolList";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return ppmsList;
    }


    public List<ProgramProfilingMessageSymbol>
                            getProgramProfilingMessageSymbols(int configID,
                                                              int programID)
                                                       throws RemoteException {
        List<ProgramProfilingMessageSymbol> symbolList;
        List<ProgramProfilingMessageSymbol> symbolsForProg;

        try {
            symbolList     = getProgramProfilingMessageSymbols(configID);
            symbolsForProg = new ArrayList<ProgramProfilingMessageSymbol>();

            for (ProgramProfilingMessageSymbol i : symbolList) {
                ProgramMessageSymbol programMsgSymbol;

                programMsgSymbol = ProgramMessageSymbolManagerImpl.getInstance().
                                            getProgramMessageSymbol(
                                                i.getProgramMessageSymbolID());

                if (programMsgSymbol.getProgramID() == programID) {
                    symbolsForProg.add(i);
                }
            }
        }  catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in getProgramProfilingMessageSymbols";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }

        return symbolsForProg;
    }


    public List<ProgramProfilingMessageSymbol>
                                getProgramProfilingMessageSymbols(int configID)
                                                       throws RemoteException {
        log.debug("getProgramProfilingMessageSymbols() called.");

        List<ProgramProfilingMessageSymbol> symbolList;
        symbolList = new ArrayList<ProgramProfilingMessageSymbol>();

        readLock.lock();
        try {
            for (ProgramProfilingMessageSymbol i : ppmSymbols.values()) {
                if (i.getProjectDeploymentConfigurationID() == configID ) {
                    symbolList.add(i);
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in getProgramProfilingMessageSymbols";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return symbolList;
    }


    public void createNewProfilingMessageSymbol(int configID,
                                                int programMsgSymbolID)
                                                       throws RemoteException {
        try {
            log.info(
             "Request to create new ProgramProfilingMessageSymbol:\n" +
              "  projectDeploymentConfigurationID:  " + configID      + "\n" +
              "  programMessageSymbolID:            " + programMsgSymbolID);

            ProgramProfilingMessageSymbol ppms =
                ppmsAdapter.createNewProfilingMessageSymbol(configID,
                                                           programMsgSymbolID);

            writeLock.lock();
            try {
                ppmSymbols.put(ppms.getID(), ppms);
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.NEW_SYMBOL, ppms);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in createNewProfilingMessageSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void cloneProfilingMessageSymbol(int srcConfigID, int newConfigID)
                                                       throws RemoteException {
        try {
            log.info(
             "Request to clone ProgramProfilingMessageSymbol with " +
             "ConfigID: " + srcConfigID);

            List<ProgramProfilingMessageSymbol> newList;
            newList = new ArrayList<ProgramProfilingMessageSymbol>();

            for (ProgramProfilingMessageSymbol i : ppmSymbols.values()) {
                if (i.getProjectDeploymentConfigurationID() == srcConfigID) {
                    ProgramProfilingMessageSymbol ppms =
                        ppmsAdapter.createNewProfilingMessageSymbol(newConfigID,
                                                i.getProgramMessageSymbolID());
                    newList.add(ppms);
                }
            }

            writeLock.lock();
            try {
                for (ProgramProfilingMessageSymbol i : newList) {
                    ppmSymbols.put(i.getID(), i);
                }
            } finally {
                writeLock.unlock();
            }

            for (ProgramProfilingMessageSymbol i : newList) {
                notifyObservers(Message.NEW_SYMBOL, i);
            }
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in cloneProfilingMessageSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteProgramProfilingMessageSymbol(int id)
                                                       throws RemoteException {
        log.info("Deleting program profiling message symbol with " +
                 "id: " + id);
        try {
            ProgramProfilingMessageSymbol ppms;
            ppms = ppmsAdapter.deleteProfilingMessageSymbol(id);

            writeLock.lock();
            try {
                ppmSymbols.remove(ppms.getID());
            } finally {
                writeLock.unlock();
            }

            notifyObservers(Message.DELETE_SYMBOL, ppms);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in deleteProgramProfilingMessageSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteProgProfMsgSymsFor(int programMessageSymbolID)
                                                        throws RemoteException {
        log.info("Request to delete ProgramProfilingMessageSymbol for " +
                 "programMessageSymbolID: " + programMessageSymbolID);

        try {
            List<ProgramProfilingMessageSymbol> list;
            list = new ArrayList<ProgramProfilingMessageSymbol>(
                                                        ppmSymbols.values());

            for (ProgramProfilingMessageSymbol i : list) {
                if (i.getProgramMessageSymbolID() == programMessageSymbolID) {
                    deleteProgramProfilingMessageSymbol(i.getID());
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in deleteProgProfMsgSymsFor";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteProfilingMessageSymbolWithProjectDepConfID(int pdcID)
                                                        throws RemoteException {
        log.info("Request to delete ProgramProfilingMessageSymbol for " +
                 "ProjectDeploymentConfigurationID: " + pdcID);

        try {
            List<ProgramProfilingMessageSymbol> list;
            list = new ArrayList<ProgramProfilingMessageSymbol>(
                                                        ppmSymbols.values());

            for (ProgramProfilingMessageSymbol i : list) {
                if (i.getProjectDeploymentConfigurationID() == pdcID) {
                    deleteProgramProfilingMessageSymbol(i.getID());
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in " +
                         "deleteProfilingMessageSymbolWithProjectDepConfID";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void deleteProgramProfilingMessageSymbolWithProgMsgSymID(int pmsID)
                                                        throws RemoteException {
        log.info("Request to delete ProgramProfilingMessageSymbol for " +
                 "ProgramMessageSymbol: " + pmsID);

        try {
            List<ProgramProfilingMessageSymbol> list;
            list = new ArrayList<ProgramProfilingMessageSymbol>(
                                                        ppmSymbols.values());

            for (ProgramProfilingMessageSymbol i : list) {
                if (i.getProgramMessageSymbolID() == pmsID) {
                    deleteProgramProfilingMessageSymbol(i.getID());
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in " +
                         "deleteProgramProfilingMessageSymbolWithProgMsgSymID";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    private ProgramProfilingMessageSymbolManagerImpl() throws RemoteException {
        super();

        try {
            this.managerLock = new ReentrantReadWriteLock(true);
            this.readLock    = managerLock.readLock();
            this.writeLock   = managerLock.writeLock();
            ppmsAdapter      =
                    AdapterFactory.createProgramProfilingMessageSymbolAdapter(
                                                              AdapterType.SQL);

            ppmSymbols       = ppmsAdapter.readProgramProfilingMessageSymbols();

            log.debug("ProgramProfilingMessageSymbols read:\n" +
                      ppmSymbols);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in " +
                         "ProgramProfilingMessageSymbolManagerImpl";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }
}
