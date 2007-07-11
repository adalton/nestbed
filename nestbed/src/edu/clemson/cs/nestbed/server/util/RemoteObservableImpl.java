/* $Id$ */
/*
 * RemoteObservableImpl.java
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
package edu.clemson.cs.nestbed.server.util;


import java.rmi.ConnectException;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.util.RemoteObservable;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


public class RemoteObservableImpl extends    UnicastRemoteObject
                                  implements RemoteObservable {
    private final static Log log =
                            LogFactory.getLog(RemoteObservableImpl.class);

    private ReadWriteLock        managerLock;
    private Lock                 readLock;
    private Lock                 writeLock;
    private List<RemoteObserver> observers;


    public RemoteObservableImpl() throws RemoteException {
        managerLock = new ReentrantReadWriteLock(true);
        readLock    = managerLock.readLock();
        writeLock   = managerLock.writeLock();
        observers   = new ArrayList<RemoteObserver>();
    }


    public void addRemoteObserver(RemoteObserver o) throws RemoteException {
        log.debug("Remote observer registered.");
        writeLock.lock();
        try {
            observers.add(o);
        } finally {
            writeLock.unlock();
        }
    }


    public void deleteRemoteObserver(RemoteObserver o)
                                                    throws RemoteException {
        log.debug("Remote observer deregistered.");
        writeLock.lock();
        try {
            observers.remove(o);
        } finally {
            writeLock.unlock();
        }
    }


    public void notifyObservers(Serializable msg, Serializable arg) {
        List<RemoteObserver> deadObservers = new ArrayList<RemoteObserver>();

        String msgType = (msg != null) ? msg.getClass().getName() : "";
        String argType = (arg != null) ? arg.getClass().getName() : "";

//        log.debug("Notifying observers\n"  +
//                  "  message\n"  +
//                  "    type:   " + msgType + "\n" +
//                  "    value:  " + msg     + "\n" +
//                  "  argument\n" +
//                  "    type:   " + argType + "\n" +
//                  "    value:  " + arg);

        for (RemoteObserver i : new ArrayList<RemoteObserver>(observers)) {
            try {
                i.update(msg, arg);
            } catch (ConnectException e) {
                log.info("Dropping RemoteObserver:\n" + i);
                deadObservers.add(i);
            } catch (NoSuchObjectException e) {
                log.info("Dropping RemoteObserver:\n" + i);
                deadObservers.add(i);
            } catch (Exception e) {
                log.error("Exception occured while processing observers", e);
                e.printStackTrace();
            }
        }

        try {
            for (RemoteObserver i : deadObservers) {
                deleteRemoteObserver(i);
            }
        } catch (RemoteException e) { /* empty */ }
    }
}
