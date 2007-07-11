/* $Id$ */
/*
 * MoteMessageManager.java
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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PacketSource;
import net.tinyos.packet.PhoenixError;
import net.tinyos.packet.PhoenixSource;

import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramMessageSymbolManagerImpl;


public class MoteMessageManager implements MessageListener {
    private final static Log log = LogFactory.getLog(MoteMessageManager.class);

    private Map<ProgramMessageSymbol,
                List<RemoteObserver>> messageObserverListMap;
    private Mote                      mote;
    private PacketSource              packetSource;
    private PhoenixSource             phoenixSource;
    private MoteIF                    moteIF;
    private boolean                   enabled;
    private Thread                    mainThread;
    private boolean                   sfEnabled;


    public MoteMessageManager(Mote mote) {
        messageObserverListMap    = new HashMap<ProgramMessageSymbol,
                                                List<RemoteObserver>>();
        this.mote                 = mote;
        this.enabled              = false;
        this.mainThread           = Thread.currentThread();
    }


    public void addMessageObserver(int            pmsID,
                                   RemoteObserver observer)
                                                       throws RemoteException {
        log.info("Adding a message observer for moteID: " + mote.getID() +
                 " for message id: " + pmsID);
        ProgramMessageSymbol pms;
        List<RemoteObserver> observers;

        pms       = ProgramMessageSymbolManagerImpl.getInstance().
                                            getProgramMessageSymbol(pmsID);
        observers = messageObserverListMap.get(pms);

        if (observers == null) {
            observers = new ArrayList<RemoteObserver>();
            messageObserverListMap.put(pms, observers);
        }

        try {
            observers.add(observer);

            Class       c           = pms.getMessageClass();
            Constructor constructor = c.getConstructor();
            Message     msg         = (Message) constructor.newInstance();

            moteIF.registerListener(msg, this);
        } catch (Exception ex) {
            observers.remove(observer);

            String msg = "Cannot create message type object: ";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void removeMessageObserver(int            pmsID,
                                      RemoteObserver observer)
                                                       throws RemoteException {
        log.info("Removing a message observer for moteID: " + mote.getID() +
                 " for message id: " + pmsID);

        ProgramMessageSymbol pms;
        List<RemoteObserver> observers;

        try {
            pms       = ProgramMessageSymbolManagerImpl.getInstance().
                                                getProgramMessageSymbol(pmsID);
            observers = messageObserverListMap.get(pms);

            if (observers != null) {
                observers.remove(observer);
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in removeMessageObserver";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void enable() {
        if (!enabled) {
            log.info("Enabling messages for mote: " + mote.getID());

            packetSource  = BuildSource.makePacketSource("serial@/dev/motes/" +
                                                        mote.getMoteSerialID() +
                                                        ":telos");
            phoenixSource = BuildSource.makePhoenix(packetSource, null);

            phoenixSource.setPacketErrorHandler(new PhoenixError() {
                public void error(IOException e) {
                    log.warn("IOException in phoenix source for\n" +
                             MoteMessageManager.this.mote +
                             "\nDisabling communication for this mote.");
                    disable();
                }
            });
            moteIF        = new MoteIF(phoenixSource);
            enabled       = true;
        }
    }


    public void disable() {
        if (enabled) {
            log.info("Disabling messages for mote: " + mote.getID());

            phoenixSource.shutdown();
            phoenixSource = null;

            try { packetSource.close(); } catch (IOException ex) { }

            packetSource = null;
            moteIF       = null;
            enabled      = false;
        }
    }


    // We're fighting RMI design here a bit.  RMI expects all classes to
    // either be defined ahead of time or to be dynamically loadable
    // via a web-server.  We're not doing the web-server bit, so our
    // message class can't be loaded directly by the rmiregistry.
    //
    // To get around this, instead of writing a serialized object, we
    // write the serialized object to a byte array, then send the byte
    // array.  On the other side, we'll reverse this process.
    public void messageReceived(int toAddr, Message msg) {
        try {
            String                name = msg.getClass().getName();
            ByteArrayOutputStream out  = new ByteArrayOutputStream();
            ObjectOutputStream    oos  = new ObjectOutputStream(out);
            oos.writeObject(msg);
            oos.flush();

        outerLoop:
            for (ProgramMessageSymbol i : messageObserverListMap.keySet()) {
                if (i.getName().equals(name)) {
                    List<RemoteObserver> observers;
                    observers = messageObserverListMap.get(i);

                    for (RemoteObserver j : observers) {
                        byte[] bytes = out.toByteArray();
                        out.close();
                        log.info(bytes.length);
                        j.update(i.getID(), bytes);
                    }
                    break outerLoop;
                }
            }
        } catch (Exception ex) {
            log.error("Exception while receiving message", ex);
        }
    }
}
