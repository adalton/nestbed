/*
 * RemoteObservableImpl.java
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
package edu.clemson.cs.nestbed.server.util;


import java.rmi.ConnectException;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.util.RemoteObservable;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


public class RemoteObservableImpl extends    UnicastRemoteObject
                                  implements RemoteObservable {
    private final static Log log =
                            LogFactory.getLog(RemoteObservableImpl.class);

    private List<RemoteObserver> observers;


    public RemoteObservableImpl() throws RemoteException {
        observers = new ArrayList<RemoteObserver>();
    }


    public synchronized void addRemoteObserver(RemoteObserver o)
                                                    throws RemoteException {
        log.debug("Remote observer registered.");
        observers.add(o);
    }


    public synchronized void deleteRemoteObserver(RemoteObserver o)
                                                    throws RemoteException {
        log.debug("Remote observer deregistered.");
        observers.remove(o);
    }


    public synchronized void notifyObservers(Serializable msg,
                                             Serializable arg) {
        List<RemoteObserver> deadObservers = new ArrayList<RemoteObserver>();

        String msgType = (msg != null) ? msg.getClass().getName() : "";
        String argType = (arg != null) ? arg.getClass().getName() : "";

        log.debug("Notifying observers\n"  +
                  "  message\n"  +
                  "    type:   " + msgType + "\n" +
                  "    value:  " + msg     + "\n" +
                  "  argument\n" +
                  "    type:   " + argType + "\n" +
                  "    value:  " + arg);

        for (RemoteObserver i : observers) {
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
