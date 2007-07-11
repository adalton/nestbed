/* $Id$ */
/*
 * TestbedManagerImpl.java
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

import edu.clemson.cs.nestbed.common.management.configuration.TestbedManager;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.TestbedAdapter;


public class TestbedManagerImpl extends    UnicastRemoteObject
                                implements TestbedManager {

    private final static TestbedManager instance;
    private final static Log            log      = LogFactory.getLog(
                                                     TestbedManagerImpl.class);

    private ReadWriteLock         managerLock;
    private Lock                  readLock;
    private Lock                  writeLock;
    private TestbedAdapter        testbedAdapter;
    private Map<Integer, Testbed> testbeds;

    static {
        TestbedManagerImpl impl = null;

        try {
            impl = new TestbedManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static TestbedManager getInstance() {
        return instance;
    }


    public List<Testbed> getTestbedList() throws RemoteException {
        log.debug("getTestbedList() called");
        List<Testbed> testbedList = null;

        readLock.lock();
        try {
            testbedList = new ArrayList<Testbed>(testbeds.values());
        } catch (Exception ex) {
            String msg = "Exception in getTestbedList";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return testbedList;
    }


    private TestbedManagerImpl() throws RemoteException {
        super();

        try {
            this.managerLock = new ReentrantReadWriteLock(true);
            this.readLock    = managerLock.readLock();
            this.writeLock   = managerLock.writeLock();
            testbedAdapter   = AdapterFactory.createTestbedAdapter(
                                                            AdapterType.SQL);
            testbeds         = testbedAdapter.readTestbeds();

            log.debug("Testbeds read:\n" + testbeds);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (Exception ex) {
            String msg = "Exception in TestbedManagerImpl";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }
}
