/* $Id$ */
/*
 * SerialForwarderManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management.sfcontrol;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.tinyos.sf.SerialForwarder;

import edu.clemson.cs.nestbed.common.management.sfcontrol.SerialForwarderManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.server.management.configuration.MoteManagerImpl;


public class SerialForwarderManagerImpl extends    UnicastRemoteObject
                                implements SerialForwarderManager {

    private final static SerialForwarderManager instance;
    private final static Log            log      = LogFactory.getLog(
                                              SerialForwarderManagerImpl.class);

    private Map<Integer, SerialForwarder> serialForwarderMap;

    static {
        SerialForwarderManager impl = null;

        try {
            impl = new SerialForwarderManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static SerialForwarderManager getInstance() {
        return instance;
    }


    public void enableSerialForwarder(int moteID, int moteAddress)
                                                        throws RemoteException {
        try {
            Mote mote = MoteManagerImpl.getInstance().getMote(moteID);

            SerialForwarder sf = new SerialForwarder(new String[] {
                    "-port", Integer.toString(9000 + moteAddress),
                    "-comm", "serial@/dev/motes/" + mote.getMoteSerialID() +
                                    ":telos",
                    "-no-gui",
                    "-quiet"});
            serialForwarderMap.put(moteID, sf);
        } catch (Exception ex) {
            String msg = "Exception while enabling serial forwarder";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void disableSerialForwarder(int moteID) throws RemoteException {
        SerialForwarder sf = serialForwarderMap.remove(moteID);

        if (sf != null) {
            log.info("Disabling serial forwarder for mote: " + moteID);

            sf.stopListenServer();
            sf.listenServerStopped();

            log.info("Serial forwarder for mote " + moteID + " disabled");
        } else {
            log.warn("No serial forwarder running for mote: " + moteID);
        }
    }


    public boolean isSerialForwarderEnabled(int moteID) throws RemoteException {
        return (serialForwarderMap.get(moteID) != null);
    }


    private SerialForwarderManagerImpl() throws RemoteException {
        super();

        serialForwarderMap = new HashMap<Integer, SerialForwarder>();
    }
}
