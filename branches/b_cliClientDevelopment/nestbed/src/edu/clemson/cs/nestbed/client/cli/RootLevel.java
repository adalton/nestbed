/* $Id$ */
/*
 * RootLevel.java
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
package edu.clemson.cs.nestbed.client.cli;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import edu.clemson.cs.nestbed.common.management.configuration.TestbedManager;
import edu.clemson.cs.nestbed.common.model.Testbed;


class RootLevel extends Level {
    private TestbedManager testbedManager;
    private List<Testbed>  testbeds;


    public RootLevel() throws Exception {
        super("/", null);
        lookupRemoteManagers();

        this.testbeds = testbedManager.getTestbedList();

        for (Testbed i : testbeds) {
            addEntry(new TestbedLevelEntry(i));
        }
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        testbedManager = (TestbedManager)
                    Naming.lookup(RMI_BASE_URL + "TestbedManager");
    }


    private class TestbedLevelEntry extends LevelEntry {
        private Testbed testbed;

        public TestbedLevelEntry(Testbed testbed) {
            super(testbed.getName());
            this.testbed = testbed;
        }

        public Level getLevel() throws Exception {
            return new TestbedLevel(testbed, RootLevel.this);
        }
    }
}
