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
package edu.clemson.cs.nestbed.server.management;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.ProgramProfilingMessageSymbolManager;
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


    public synchronized List<ProgramProfilingMessageSymbol>
                        getProgramProfilingMessageSymbolList() throws RemoteException {
        log.debug("getProgramProfilingMessageSymbolList() called");
        List<ProgramProfilingMessageSymbol> ppmsList = null;

        try {
            ppmsList = new ArrayList<ProgramProfilingMessageSymbol>(
                                                    ppmSymbols.values());
        } catch (Exception ex) {
            log.error("Exception in getProgramProfilingMessageSymbolList", ex);
            throw new RemoteException(ex.toString());
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
            log.error("Exception in getProgramProfilingMessageSymbols", ex);
            throw new RemoteException(ex.toString());
        }

        return symbolsForProg;
    }


    public List<ProgramProfilingMessageSymbol>
                                getProgramProfilingMessageSymbols(int configID)
                                                       throws RemoteException {
        log.debug("getProgramProfilingMessageSymbols() called.");

        List<ProgramProfilingMessageSymbol> symbolList;
        symbolList = new ArrayList<ProgramProfilingMessageSymbol>();

        try {
            synchronized (this) {
                for (ProgramProfilingMessageSymbol i : ppmSymbols.values()) {
                    if (i.getProjectDeploymentConfigurationID() == configID ) {
                        symbolList.add(i);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("Exception in getProgramProfilingMessageSymbols", ex);
            throw new RemoteException(ex.toString());
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

            synchronized (this) {
                ppmSymbols.put(ppms.getID(), ppms);
            }

            notifyObservers(Message.NEW_SYMBOL, ppms);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in createNewProfilingMessageSymbol", ex);
            throw new RemoteException(ex.toString());
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

            for (ProgramProfilingMessageSymbol i : newList) {
                synchronized (this) {
                    ppmSymbols.put(i.getID(), i);
                }

                notifyObservers(Message.NEW_SYMBOL, i);
            }
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in cloneProfilingMessageSymbol", ex);
            throw new RemoteException(ex.toString());
        }
    }


    public void deleteProgramProfilingMessageSymbol(int id)
                                                       throws RemoteException {
        log.info("Deleting program profiling message symbol with " +
                 "id: " + id);
        try {
            ProgramProfilingMessageSymbol ppms;
            ppms = ppmsAdapter.deleteProfilingMessageSymbol(id);

            synchronized (this) {
                ppmSymbols.remove(ppms.getID());
            }

            notifyObservers(Message.DELETE_SYMBOL, ppms);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in deleteProgramProfilingMessageSymbol", ex);
            throw new RemoteException(ex.toString());
        }
    }


    public void deleteProgProfMsgSymsFor(int programMessageSymbolID)
                                                        throws RemoteException {
        try {
            for (ProgramProfilingMessageSymbol i : ppmSymbols.values()) {
                if (i.getProgramMessageSymbolID() == programMessageSymbolID) {
                    deleteProgramProfilingMessageSymbol(i.getID());
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deleteProgProfMsgSymsFor", ex);
            throw new RemoteException(ex.toString());
        }
    }


    private ProgramProfilingMessageSymbolManagerImpl() throws RemoteException {
        super();

        try {
            ppmsAdapter =
                    AdapterFactory.createProgramProfilingMessageSymbolAdapter(
                                                              AdapterType.SQL);

            ppmSymbols  = ppmsAdapter.readProgramProfilingMessageSymbols();

            log.debug("ProgramProfilingMessageSymbols read:\n" +
                      ppmSymbols);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in ProgramProfilingMessageSymbolManagerImpl",
                      ex);
            throw new RemoteException(ex.toString());
        }
    }
}
