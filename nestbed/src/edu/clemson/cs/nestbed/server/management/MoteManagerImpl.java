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
package edu.clemson.cs.nestbed.server.management;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.MoteManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.MoteAdapter;


public class MoteManagerImpl extends    UnicastRemoteObject
                             implements MoteManager {

    private final static Log log =
                LogFactory.getLog(MoteManagerImpl.class);


    private MoteAdapter        moteAdapter;
    private Map<Integer, Mote> motes;


    public MoteManagerImpl() throws RemoteException {
        super();

        try {
            moteAdapter = AdapterFactory.createMoteAdapter(AdapterType.SQL);
            motes       = moteAdapter.readMotes();

            log.debug("Motes read:\n" + motes);
        } catch (AdaptationException ex) {
            log.error("AdaptationException:", ex);
            throw new RemoteException("AdaptationException:", ex);
        }
    }


    public synchronized Mote getMote(int moteID) throws RemoteException {
        log.debug("getMote() called");

        return motes.get(moteID);
    }


    public synchronized Mote getMote(String moteSerialID)
                                                    throws RemoteException {
        Mote mote = null;

        for (Mote i : motes.values()) {
            if (i.getMoteSerialID().equals(moteSerialID)) {
                mote = i;
                break;
            }
        }

        return mote;
    }

    public synchronized List<Mote> getMoteList() throws RemoteException {
        return new ArrayList<Mote>(motes.values());
    }
}
