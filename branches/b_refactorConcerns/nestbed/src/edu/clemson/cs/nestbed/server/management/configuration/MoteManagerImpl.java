/* $Id$ */
/*
 * MoteManagerImpl.java
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
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.MoteAdapter;


public class MoteManagerImpl extends    UnicastRemoteObject
                             implements MoteManager {

    private final static MoteManager instance;
    private final static Log         log      = LogFactory.getLog(
                                                        MoteManagerImpl.class);
    private ReadWriteLock      monitorLock;
    private Lock               readLock;
    private Lock               writeLock;
    private MoteAdapter        moteAdapter;
    private Map<Integer, Mote> motes;

    static {
        MoteManagerImpl impl = null;

        try {
            impl = new MoteManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static MoteManager getInstance() {
        return instance;
    }


    public Mote getMote(int moteID) throws RemoteException {
        log.debug("getMote() called");

        readLock.lock();
        try {
            return motes.get(moteID);
        } finally {
            readLock.unlock();
        }
    }


    public Mote getMote(String moteSerialID) throws RemoteException {
        Mote mote = null;

        readLock.lock();
        try {
            for (Mote i : motes.values()) {
                if (i.getMoteSerialID().equals(moteSerialID)) {
                    mote = i;
                    break;
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in getMote";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return mote;
    }


    public List<Mote> getMoteList() throws RemoteException {
        List<Mote> moteList = null;

        readLock.lock();
        try {
            moteList = new ArrayList<Mote>(motes.values());
        } catch (Exception ex) {
            String msg = "Exception in getMoteList";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return moteList;
    }


    private MoteManagerImpl() throws RemoteException {
        super();

        try {
            monitorLock = new ReentrantReadWriteLock(true);
            readLock    = monitorLock.readLock();
            writeLock   = monitorLock.writeLock();
            moteAdapter = AdapterFactory.createMoteAdapter(AdapterType.SQL);
            motes       = moteAdapter.readMotes();

            log.debug("Motes read:\n" + motes);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in MoteManagerImpl";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }
}
