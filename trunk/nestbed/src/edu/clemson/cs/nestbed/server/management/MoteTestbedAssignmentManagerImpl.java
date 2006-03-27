/* $Id$ */
/*
 * MoteTestbedAssignmentManagerImpl.java
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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.MoteTestbedAssignmentManager;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.MoteTestbedAssignmentAdapter;


public class MoteTestbedAssignmentManagerImpl extends UnicastRemoteObject
                                    implements MoteTestbedAssignmentManager {
    private final static MoteTestbedAssignmentManager instance;
    private final static Log log = LogFactory.getLog(
                                        MoteTestbedAssignmentManagerImpl.class);

    private ReadWriteLock                       managerLock;
    private Lock                                readLock;
    private Lock                                writeLock;
    private MoteTestbedAssignmentAdapter        moteTestbedAssignmentAdapter;
    private Map<Integer, MoteTestbedAssignment> moteTestbedAssignments;

    static {
        MoteTestbedAssignmentManagerImpl impl = null;

        try {
            impl = new MoteTestbedAssignmentManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static MoteTestbedAssignmentManager getInstance() {
        return instance;
    }


    public List<MoteTestbedAssignment>
                            getMoteTestbedAssignmentList(int testbedID)
                                                        throws RemoteException {
        log.debug("getMoteTestbedAssignmentList() called");
        List<MoteTestbedAssignment> mtbaList =
                                        new ArrayList<MoteTestbedAssignment>();
        readLock.lock();
        try {
            for (MoteTestbedAssignment i : moteTestbedAssignments.values()) {
                if (i.getTestbedID() == testbedID) {
                    mtbaList.add(i);
                }
            }
        } catch (Exception ex) {
            log.error("Exception in getMoteTestbedAssignmentList");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } finally {
            readLock.unlock();
        }

        return mtbaList;
    }


    public MoteTestbedAssignment getMoteTestbedAssignment(int moteID)
                                                       throws RemoteException {
        MoteTestbedAssignment mtba = null;

        readLock.lock();
        try {
            for (MoteTestbedAssignment i : moteTestbedAssignments.values()) {
                if (i.getMoteID() == moteID) {
                    mtba = i;
                    break;
                }
            }
        } catch (Exception ex) {
            log.error("Exception in getMoteTestbedAssignment");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } finally {
            readLock.unlock();
        }

        return mtba;
    }


    private MoteTestbedAssignmentManagerImpl() throws RemoteException {
        super();

        try {
            managerLock                  = new ReentrantReadWriteLock(true);
            readLock                     = managerLock.readLock();
            writeLock                    = managerLock.writeLock();
            moteTestbedAssignmentAdapter =
                    AdapterFactory.createMoteTestbedAssignmentAdapter(
                                                            AdapterType.SQL);
            moteTestbedAssignments       =
                    moteTestbedAssignmentAdapter.readMoteTestbedAssignments();

            log.debug("MoteTestbedAssignments read:\n" +
                      moteTestbedAssignments);
        } catch (AdaptationException ex) {
            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        } catch (Exception ex) {
            log.error("Exception in MoteTestbedAssignmentManagerImpl");

            RemoteException rex = new RemoteException(ex.toString());
            rex.initCause(ex);
            throw rex;
        }
    }
}
