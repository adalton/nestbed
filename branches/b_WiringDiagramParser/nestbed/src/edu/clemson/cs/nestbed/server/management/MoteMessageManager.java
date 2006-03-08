/* $Id$ */
/*
 * MoteMessageManager.java
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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.clemson.cs.nestbed.common.management.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.util.ByteClassLoader;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PacketSource;
import net.tinyos.packet.PhoenixError;
import net.tinyos.packet.PhoenixSource;
import net.tinyos.sf.SerialForwarder;


public class MoteMessageManager implements MessageListener {
    private final static Log log = LogFactory.getLog(MoteMessageManager.class);

    private ProgramMessageSymbolManager progMsgSymbolManager;
    private Map<ProgramMessageSymbol,
                List<RemoteObserver>>   messageObserverListMap;
    private Mote                        mote;

    private PacketSource                packetSource;
    private PhoenixSource               phoenixSource;
    private MoteIF                      moteIF;
    private boolean                     enabled;
    private Thread                      mainThread;
    private SerialForwarder             serialForwarder;
    private boolean                     sfEnabled;


    public MoteMessageManager(Mote mote, ProgramMessageSymbolManager pmsMgr) {
        messageObserverListMap    = new HashMap<ProgramMessageSymbol,
                                                List<RemoteObserver>>();
        this.mote                 = mote;
        this.progMsgSymbolManager = pmsMgr;
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

        pms       = progMsgSymbolManager.getProgramMessageSymbol(pmsID);
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
        } catch (NoSuchMethodException ex) {
            observers.remove(observer);
            log.error("Cannot create message type object", ex);
            throw new RemoteException("Cannot create message type object", ex);
        } catch (InstantiationException ex) {
            observers.remove(observer);
            log.error("Cannot create message type object", ex);
            throw new RemoteException("Cannot create message type object", ex);
        } catch (IllegalAccessException ex) {
            observers.remove(observer);
            log.error("Cannot create message type object", ex);
            throw new RemoteException("Cannot create message type object", ex);
        } catch (InvocationTargetException ex) {
            observers.remove(observer);
            log.error("Cannot create message type object", ex);
            throw new RemoteException("Cannot create message type object", ex);
        }
    }


    public void removeMessageObserver(int            pmsID,
                                      RemoteObserver observer)
                                                       throws RemoteException {
        log.info("Removing a message observer for moteID: " + mote.getID() +
                 " for message id: " + pmsID);

        ProgramMessageSymbol pms;
        List<RemoteObserver> observers;

        pms       = progMsgSymbolManager.getProgramMessageSymbol(pmsID);
        observers = messageObserverListMap.get(pms);

        if (observers != null) {
            observers.remove(observer);
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

    public void enableSerialForwarder(int moteAddress) throws RemoteException {
        try {
            if (!sfEnabled) {
                log.info("Enabling serial forwarder for mote: " + mote.getID());
                disable();


                serialForwarder = new SerialForwarder(new String[] {
                    "-port", Integer.toString(9000 + moteAddress),
                    "-comm", "serial@/dev/motes/" + mote.getMoteSerialID() +
                                    ":telos",
                    "-no-gui",
                    "-quiet"});
            }
            sfEnabled = !sfEnabled;
        } catch (IOException ex) {
            log.error("Exception while enabling serial forwarder\n", ex);
            ex.printStackTrace();
            throw new RemoteException("Exception while enabling serial " +
                                      "forwarder", ex);
        }
    }

    public void disableSerialForwarder(int moteAddress) {
        if (sfEnabled) {
            log.info("Disabling serial forwarder for mote: " + mote.getID());

            serialForwarder.stopListenServer();
            serialForwarder.listenServerStopped();
        }
        sfEnabled = !sfEnabled;
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
            log.error("Exception:\n", ex);
        }
    }
}
