/* $Id$ */
/*
 * MessageManagerImpl.java
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


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.clemson.cs.nestbed.common.management.configuration.MessageManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MessageManagerImpl extends    UnicastRemoteObject
                                implements MessageManager {

    private final static MessageManager instance;
    private final static Log            log      = LogFactory.getLog(
                                                      MessageManagerImpl.class);

    private Map<Integer, MoteMessageManager> moteMessageManagerMap;

    static {
        MessageManagerImpl impl = null;

        try {
            impl = new MessageManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static MessageManager getInstance() {
        return instance;
    }


    public void addMessageObserver(int            moteID,
                                   int            programMessageID,
                                   RemoteObserver observer)
                                                        throws RemoteException {
        MoteMessageManager moteMessageManager;
        moteMessageManager = moteMessageManagerMap.get(moteID);

        moteMessageManager.addMessageObserver(programMessageID, observer);
    }


    public void removeMessageObserver(int            moteID,
                                      int            programMessageID,
                                      RemoteObserver observer)
                                                        throws RemoteException {
        MoteMessageManager moteMessageManager;
        moteMessageManager = moteMessageManagerMap.get(moteID);

        moteMessageManager.removeMessageObserver(programMessageID,
                                                 observer);
    }


    public void enable(int moteID)  throws RemoteException {
        MoteMessageManager moteMessageManager;
        moteMessageManager = moteMessageManagerMap.get(moteID);

        moteMessageManager.enable();
    }


    public void disable(int moteID) throws RemoteException {
        MoteMessageManager moteMessageManager;
        moteMessageManager = moteMessageManagerMap.get(moteID);

        moteMessageManager.disable();
    }


    public void enableSerialForwarder(int moteID, int moteAddress)
                                                        throws RemoteException {
        MoteMessageManager moteMessageManager;
        moteMessageManager = moteMessageManagerMap.get(moteID);

        moteMessageManager.enableSerialForwarder(moteAddress);
    }

    public void disableSerialForwarder(int moteID, int moteAddress)
                                                        throws RemoteException {
        MoteMessageManager moteMessageManager;
        moteMessageManager = moteMessageManagerMap.get(moteID);

        moteMessageManager.disableSerialForwarder(moteAddress);
    }


    private MessageManagerImpl() throws RemoteException {
        super();

        moteMessageManagerMap = new HashMap<Integer, MoteMessageManager>();
        List<Mote> moteList = MoteManagerImpl.getInstance().getMoteList();


        for (Mote i : moteList) {
            moteMessageManagerMap.put(i.getID(), new MoteMessageManager(i));
        }
    }
}
