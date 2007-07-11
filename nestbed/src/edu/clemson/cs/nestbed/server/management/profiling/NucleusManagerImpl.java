/* $Id$ */
/*
 * NucleusManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management.profiling;


import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PhoenixSource;
import net.tinyos.util.Messenger;

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
    private final static LogMessenger   logMessenger = new LogMessenger();


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


    public long querySymbol(int    id,       String sourcePath,
                            String moteType, String moteSerialID)
                                                    throws RemoteException {
        long value = -1;

        try {
            String device = "/dev/motes/" + moteSerialID; 
            log.info("Query symbol on device: " + device);

            ProgramProfilingSymbol pps;
            ProgramSymbol          programSymbol;
            String                 moduleName;
            String                 symbolName;

            pps           = ProgramProfilingSymbolManagerImpl.getInstance().
                                                  getProgramProfilingSymbol(id);
            programSymbol = ProgramSymbolManagerImpl.getInstance().
                                                  getProgramSymbol(
                                                      pps.getProgramSymbolID());
            moduleName    = programSymbol.getModule();
            symbolName    = programSymbol.getSymbol();

            if (!moduleName.equals("<global>")) {
                symbolName = moduleName + "." + symbolName;
            }

            String                 source  = "serial@" + device + ":telos";
            PhoenixSource          phoenix = BuildSource.makePhoenix(source,
                                                                  logMessenger);
            MoteIF                 moteIF  = new MoteIF(phoenix);
            MemoryProfilingMessage payload = new MemoryProfilingMessage();
            MemoryProfilingMessageListener listener
                    = new MemoryProfilingMessageListener();

            moteIF.registerListener(new MemoryProfilingMessage(),
                                    listener);

            payload.set_read((byte) 1);
            payload.set_offset((byte) 0);
            payload.set_address(programSymbol.getAddress());
            payload.set_size(programSymbol.getSize());

            moteIF.send(0, payload);

            value = listener.getValue();
            phoenix.shutdown();
        } catch (IOException ex) {
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
            String device = "/dev/motes/" + moteSerialID; 
            log.info("Setting symbol on device: " + device + " to value " + value);

            ProgramProfilingSymbol pps;
            ProgramSymbol          programSymbol;
            String                 moduleName;
            String                 symbolName;

            pps           = ProgramProfilingSymbolManagerImpl.getInstance().
                                                  getProgramProfilingSymbol(id);
            programSymbol = ProgramSymbolManagerImpl.getInstance().
                                                  getProgramSymbol(
                                                      pps.getProgramSymbolID());
            moduleName    = programSymbol.getModule();
            symbolName    = programSymbol.getSymbol();

            if (!moduleName.equals("<global>")) {
                symbolName = moduleName + "." + symbolName;
            }
            log.info("Set symbol: " + symbolName + " to " + value);

            String                 source  = "serial@" + device + ":telos";
            PhoenixSource          phoenix = BuildSource.makePhoenix(source,
                                                                  logMessenger);
            MoteIF                 moteIF  = new MoteIF(phoenix);
            MemoryProfilingMessage payload = new MemoryProfilingMessage();

            payload.set_read((byte) 0);
            payload.set_offset((byte) 0);
            payload.set_address(programSymbol.getAddress());
            payload.set_size(programSymbol.getSize());
            payload.set_value(value);

            moteIF.send(0, payload);
            phoenix.shutdown();

            okay = true;
        } catch (IOException ex) {
            String msg = "Exception in setSymbol";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
        return okay;
    }


    private NucleusManagerImpl() throws RemoteException {
        super();
    }


    private static class LogMessenger implements Messenger {
        public void message(String s) {
            log.error(s);
        }
    }


    private class MemoryProfilingMessageListener implements MessageListener {
        private long    value;
        private boolean recorded = false;


        public synchronized void messageReceived(int to, Message message) {
            MemoryProfilingMessage msg = (MemoryProfilingMessage) message;
            value    = msg.get_value();
            recorded = true;
            notifyAll();
        }


        public synchronized long getValue() {
            while (!recorded) {
                try { wait(); } catch (InterruptedException ex) { }
            }

            recorded = false;
            return value;
        }
    }
}
