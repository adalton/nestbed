/* $Id$ */
/*
 * ProgramProfilingSymbolManagerImpl.java
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

import net.tinyos.nucleus.NucleusInterface;
import net.tinyos.nucleus.NucleusResult;
import net.tinyos.nucleus.NucleusValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.ProgramSymbolManager;
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


    //public synchronized List<ProgramProfilingSymbol>
    public List<ProgramProfilingSymbol>
                                getProgramProfilingSymbols(int configID)
                                                       throws RemoteException {
        log.debug("getProgramProfilingSymbols() called.");
        List<ProgramProfilingSymbol> symbolList;
        symbolList = new ArrayList<ProgramProfilingSymbol>();

        try {
            for (ProgramProfilingSymbol i : progProfSymbols.values()) {
                if (i.getProjectDeploymentConfigurationID() == configID ) {
                    symbolList.add(i);
                }
            }
        } catch (Exception ex) {
            log.error("Exception in getProgramProfilingSymbols", ex);
            throw new RemoteException(ex.toString());
        }

        return symbolList;
    }


    public synchronized List<ProgramProfilingSymbol>
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
            log.error("Exception in getProgramProfilingSymbols", ex);
            throw new RemoteException(ex.toString());
        }

        return symbolsForProg;
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

            synchronized (this) {
                progProfSymbols.put(profilingSymbol.getID(), profilingSymbol);
            }

            notifyObservers(Message.NEW_SYMBOL, profilingSymbol);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in createNewProfilingSymbol", ex);
            throw new RemoteException(ex.toString());
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

            for (ProgramProfilingSymbol i : newList) {
                synchronized (this) {
                    progProfSymbols.put(i.getID(), i);
                }

                notifyObservers(Message.NEW_SYMBOL, i);
            }
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in cloneProfilingSymbol", ex);
            throw new RemoteException(ex.toString());
        }
    }


    public void deleteProfilingSymbol(int symbolID) throws RemoteException {
        try {
            log.info("Request to delete ProgramProfilingSymbol with id: " +
                     symbolID);

            ProgramProfilingSymbol profilingSymbol =
                        progProfSymbolAdapter.deleteProfilingSymbol(symbolID);

            synchronized (this) {
                progProfSymbols.remove(profilingSymbol.getID());
            }

            notifyObservers(Message.DELETE_SYMBOL, profilingSymbol);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in deleteProfilingSymbol", ex);
            throw new RemoteException(ex.toString());
        }
    }


    public synchronized void deleteProfilingSymbolWithID(int programSymbolID)
                                                        throws RemoteException {
        log.info("Request to delete ProgramProfilingSymbol with " +
                 "programSymbolID: " + programSymbolID);

        try {
            for (ProgramProfilingSymbol i :
                        new ArrayList<ProgramProfilingSymbol>(
                                                    progProfSymbols.values())) {
                if (i.getProgramSymbolID() == programSymbolID) {
                    deleteProfilingSymbol(i.getID());
                }
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deleteProfilingSymbolWithID");
            throw new RemoteException(ex.toString());
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
                synchronized (this) {
                    progProfSymbols.put(pps.getID(), pps);
                }

                notifyObservers(Message.NEW_SYMBOL, pps);
            } else {
                log.error("Unable to update symbol with id: " + id);
            }
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in updateProgramProfilingSymbol");
            throw new RemoteException(ex.toString());
        }
    }


    public synchronized int querySymbol(int    id,       String sourcePath,
                                        String moteType, String moteSerialID)
                                                        throws RemoteException {
        int value = -1;

        try {
            int    queryDelay = 10;
            String device     = "/dev/motes/" + moteSerialID; 

            log.info("Query symbol on device: " + device);
            NucleusInterface nucleusInterface = new NucleusInterface(device,
                                                                     "telos");

            ProgramProfilingSymbol pps     = progProfSymbols.get(id);
            ProgramSymbol    programSymbol = ProgramSymbolManagerImpl.
                                                getInstance().
                                                    getProgramSymbol(
                                                      pps.getProgramSymbolID());
            String           moduleName    = programSymbol.getModule();
            String           symbolName    = programSymbol.getSymbol();

            if (!moduleName.equals("<global>")) {
                symbolName = moduleName + "." + symbolName;
            }
            nucleusInterface.loadSchema(sourcePath + "/build/" +
                                        moteType + "/nucleusSchema.xml");

            log.info("Query for symbol: " + symbolName);
            List result = nucleusInterface.get(NucleusInterface.DEST_LINK,
                                               0x7E, queryDelay,
                                               new String[] { symbolName },
                                               new int[]    { 0 });
            nucleusInterface.close();

            if (result.size() > 0) {
                NucleusResult nucleusResult = (NucleusResult) result.get(0);

                NucleusValue[] values = (NucleusValue[])
                        nucleusResult.attrs.values().toArray(new NucleusValue[0]);

                if (values.length > 0) {
                    value = ((Integer) values[0].value).intValue();
                } else {
                    throw new RemoteException("Error reading symbol");
                }
            } else {
                throw new RemoteException("Error reading symbol");
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in querySymbol", ex);
            throw new RemoteException(ex.toString());
        }

        return value;
    }


    private ProgramProfilingSymbolManagerImpl() throws RemoteException {
        super();

        try {
            progProfSymbolAdapter =
                        AdapterFactory.createProgramProfilingSymbolAdapter(
                                                            AdapterType.SQL);
            progProfSymbols       =
                        progProfSymbolAdapter.readProgramProfilingSymbols();

            log.debug("ProgramProfilingSymbols read:\n" + progProfSymbols);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in ProgramProfilingSymbolManagerImpl");
            throw new RemoteException(ex.toString());
        }
    }
}
