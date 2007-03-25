/* $Id$ */
/*
 * NucleusManagerImpl.java
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * Department of Computer Science
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
package edu.clemson.cs.nestbed.server.management.profiling;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import net.tinyos.nucleus.NucleusInterface;
import net.tinyos.nucleus.NucleusResult;
import net.tinyos.nucleus.NucleusValue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.profiling.NucleusManager;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramProfilingSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramSymbolManagerImpl;


public class NucleusManagerImpl extends    UnicastRemoteObject
                                implements NucleusManager {

    private final static NucleusManager instance;
    private final static Log            log =
                                LogFactory.getLog(NucleusManagerImpl.class);

    static {
        NucleusManager impl = null;

        try {
            impl = new NucleusManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static NucleusManager getInstance() {
        return instance;
    }


    public int querySymbol(int    id,       String sourcePath,
                           String moteType, String moteSerialID)
                                                    throws RemoteException {
        int value = -1;

        try {
            int    queryDelay = 10;
            String device     = "/dev/motes/" + moteSerialID; 

            log.info("Query symbol on device: " + device);
            NucleusInterface nucleusInterface = new NucleusInterface(device,
                                                                     "telos");

            ProgramProfilingSymbol pps     = ProgramProfilingSymbolManagerImpl.
                                                getInstance().
                                                    getProgramProfilingSymbol(id);
            ProgramSymbol    programSymbol = ProgramSymbolManagerImpl.
                                                getInstance().
                                                    getProgramSymbol(
                                                      pps.getProgramSymbolID());
            String           moduleName    = programSymbol.getModule();
            String           symbolName    = programSymbol.getSymbol();

            if (!moduleName.equals("<global>")) {
                symbolName = moduleName + "." + symbolName;
            }
            String schema = sourcePath + "/build/" + moteType +
                            "/nucleusSchema.xml";

            log.debug("Nucleus schema file: " + schema);

            nucleusInterface.loadSchema(schema);

            log.info("Query for symbol: " + symbolName);
            List result = nucleusInterface.get(NucleusInterface.DEST_LINK,
                                               0x7E, queryDelay,
                                               new String[] { symbolName },
                                               new int[]    { 0 });
            nucleusInterface.close();

            if (result.size() > 0) {
                NucleusResult nucleusResult = (NucleusResult) result.get(0);

                @SuppressWarnings({"unchecked"})
                NucleusValue[] values = (NucleusValue[])
                        nucleusResult.attrs.values().toArray(new NucleusValue[0]);

                if (values.length > 0) {
                    value = ((Integer) values[0].value).intValue();
                } else {
                    throw new RemoteException("Error reading symbol - value.length == 0");
                }
            } else {
                throw new RemoteException("Error reading symbol - results.size() == 0");
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in querySymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }

        return value;
    }


    public boolean setSymbol(int    value,       int    id,
                             String sourcePath,  String moteType,
                             String moteSerialID)      throws RemoteException {
        boolean okay = false;

        try {
            String                 device;
            NucleusInterface       nucleusInterface;
            ProgramProfilingSymbol pps;
            ProgramSymbol          programSymbol;
            String                 moduleName;
            String                 symbolName;

            device           = "/dev/motes/" + moteSerialID; 
            log.info("Set symbol on device: " + device + " to " + value);
            nucleusInterface = new NucleusInterface(device, "telos");

            pps              = ProgramProfilingSymbolManagerImpl.getInstance().
                                                getProgramProfilingSymbol(id);
            programSymbol    = ProgramSymbolManagerImpl.getInstance().
                                                    getProgramSymbol(
                                                      pps.getProgramSymbolID());
            moduleName       = programSymbol.getModule();
            symbolName       = programSymbol.getSymbol();

            if (!moduleName.equals("<global>")) {
                symbolName = moduleName + "." + symbolName;
            }
            String schema = sourcePath + "/build/" + moteType +
                            "/nucleusSchema.xml";

            log.debug("Nucleus schema file: " + schema);

            nucleusInterface.loadSchema(schema);

            log.info("Set symbol: " + symbolName + " to " + value);
            okay = nucleusInterface.set(NucleusInterface.DEST_LINK,
                                        symbolName, 0,
                                        new short[]  { (short) value });
            if (okay) {
                log.info("Symbol set successfully");
            } else {
                log.warn("Symbol not set successfully");
            }
            nucleusInterface.close();
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in setSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }

        return okay;
    }


    private NucleusManagerImpl() throws RemoteException {
        super();
    }
}
