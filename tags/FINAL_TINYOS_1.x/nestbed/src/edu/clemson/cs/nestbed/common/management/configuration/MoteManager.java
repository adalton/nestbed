/* $Id$ */
/*
 * MoteManager.java
 *
 * Network Embedded System Testbed (NESTBed)
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
package edu.clemson.cs.nestbed.common.management.configuration;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.clemson.cs.nestbed.common.model.Mote;


public interface MoteManager extends Remote {
    public Mote       getMote(int moteID)          throws RemoteException;
    public Mote       getMote(String moteSerialID) throws RemoteException;
    public List<Mote> getMoteList()                throws RemoteException;
}
