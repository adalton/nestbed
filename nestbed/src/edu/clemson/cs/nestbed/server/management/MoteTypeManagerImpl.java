/* $Id$ */
/*
 * MoteTypeManagerImpl.java
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
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.MoteTypeManager;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.MoteTypeAdapter;


public class MoteTypeManagerImpl extends    UnicastRemoteObject
                                 implements MoteTypeManager {

    private final static MoteTypeManager instance;
    private final static Log             log      = LogFactory.getLog(
                                                     MoteTypeManagerImpl.class);

    private ReadWriteLock          managerLock;
    private Lock                   readLock;
    private Lock                   writeLock;
    private MoteTypeAdapter        moteTypeAdapter;
    private Map<Integer, MoteType> moteTypes;

    static {
        MoteTypeManagerImpl impl = null;

        try {
            impl = new MoteTypeManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static MoteTypeManager getInstance() {
        return instance;
    }


    public MoteType getMoteType(int id) throws RemoteException {
        log.debug("getMoteType() called.");

        readLock.lock();
        try {
            return moteTypes.get(id);
        } finally {
            readLock.unlock();
        }
    }


    private MoteTypeManagerImpl() throws RemoteException {
        super();

        try {
            managerLock     = new ReentrantReadWriteLock(true);
            readLock        = managerLock.readLock();
            writeLock       = managerLock.writeLock();
            moteTypeAdapter = AdapterFactory.createMoteTypeAdapter(
                                                            AdapterType.SQL);
            moteTypes       = moteTypeAdapter.readMoteTypes();

            log.debug("MoteTypes read:\n" + moteTypes);
        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (Exception ex) {
            log.error("Exception in MoteTypeManagerImpl");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }
}
