/* $Id$ */
/*
 * MoteComm.java
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
package edu.clemson.cs.nestbed.server.nesc.comm;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PacketSource;
import net.tinyos.packet.PhoenixError;
import net.tinyos.packet.PhoenixSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class MoteComm implements MessageListener {
    private final static Log log = LogFactory.getLog(MoteComm.class);

    private int                          address;
    private String                       commPort;
    private String                       platform;
    private PacketSource                 packetSource;
    private PhoenixSource                phoenixSource;
    private MoteIF                       moteIf;
    private Map<Class, MessageClassPair> listenerMap;


    public MoteComm(int address, String commPort) {
        this.address     = address;
        this.commPort    = commPort;
        this.platform    = "telos";
        this.listenerMap = new HashMap<Class, MessageClassPair>();
    }


    public void addMessageListener(Message msg, MoteCommListener listener) {
        Class            msgClass = msg.getClass();
        MessageClassPair pair     = listenerMap.get(msgClass);

        if (pair == null) {
            pair = new MessageClassPair();

            pair.message = msg;
            listenerMap.put(msgClass, pair);
        }

        pair.listeners.add(listener);
    }


    public void removeMessageListener(Message msg, MoteCommListener listener) {
        Class            msgClass = msg.getClass();
        MessageClassPair pair     = listenerMap.get(msgClass);

        if (pair != null) {
            pair.listeners.remove(listener);
        }
    }


    public void start() {
        packetSource  = BuildSource.makePacketSource("serial@" +
                                                     commPort + ":" +
                                                     platform);

        phoenixSource = BuildSource.makePhoenix(packetSource, null);
        phoenixSource.setPacketErrorHandler(new PhoenixError() {
            public void error(IOException ex) {
                log.warn("IOException in phoenix source on " +
                         commPort);
                stop();
            }
        });
        moteIf        = new MoteIF(phoenixSource);

        for (MessageClassPair i : listenerMap.values()) {
            moteIf.registerListener(i.message, this);
        }
    }


    public void stop() {
        for (MessageClassPair i : listenerMap.values()) {
            moteIf.deregisterListener(i.message, this);
        }

        if (packetSource != null) {
            try { packetSource.close(); } catch (IOException ex) { }
            packetSource = null;

            try { phoenixSource.shutdown(); } catch (Exception ex) { }
            phoenixSource = null;
        }
    }


    public void send(Message message) throws IOException {
        moteIf.send(address, message);
    }


    public void messageReceived(int toAddr, Message message) {
        Class            msgClass = message.getClass();
        MessageClassPair pair     = listenerMap.get(msgClass);

        for (MoteCommListener i : pair.listeners) {
            try {
                i.messageReceived(message);
            } catch (Exception ex) {
                // Ignore
            }
        }
    }


    private class MessageClassPair {
        public Message                message;
        public List<MoteCommListener> listeners;

        public MessageClassPair() {
            this.listeners = new ArrayList<MoteCommListener>();
        }
    }
}
