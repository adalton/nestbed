/* $Id$ */
/*
 * TestbedManagerImpl.java
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

import edu.clemson.cs.nestbed.common.management.TestbedManager;
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


    public synchronized List<Testbed> getTestbedList() throws RemoteException {
        log.debug("getTestbedList() called");
        List<Testbed> testbedList = null;

        try {
            testbedList = new ArrayList<Testbed>(testbeds.values());
        } catch (Exception ex) {
            log.error("Exception in getTestbedList", ex);
            throw new RemoteException(ex.toString());
        }

        return testbedList;
    }


    private TestbedManagerImpl() throws RemoteException {
        super();

        try {
            testbedAdapter = AdapterFactory.createTestbedAdapter(
                                                            AdapterType.SQL);
            testbeds       = testbedAdapter.readTestbeds();

            log.debug("Testbeds read:\n" + testbeds);
        } catch (AdaptationException ex) {
            throw new RemoteException(ex.toString());
        } catch (Exception ex) {
            log.error("Exception in TestbedManagerImpl", ex);
            throw new RemoteException(ex.toString());
        }
    }
}
