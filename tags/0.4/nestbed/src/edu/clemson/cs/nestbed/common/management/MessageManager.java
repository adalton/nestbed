/* $Id$ */
/*
 * MessageManager.java
 *
 * Network Embedded System Testbed (NESTBed)
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
package edu.clemson.cs.nestbed.common.management;


import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.clemson.cs.nestbed.common.util.RemoteObserver;


public interface MessageManager extends Remote {
    public void addMessageObserver(int            moteID,
                                   int            programMessageID,
                                   RemoteObserver observer)
                                                        throws RemoteException;


    public void removeMessageObserver(int            moteID,
                                      int            programMessageID,
                                      RemoteObserver observer)
                                                        throws RemoteException;

    public void enable(int moteID)  throws RemoteException;

    public void disable(int moteID) throws RemoteException;

    public void enableSerialForwarder(int moteID, int moteAddress)
                                                        throws RemoteException;

    public void disableSerialForwarder(int moteID, int moteAddress)
                                                        throws RemoteException;
}
