/* $Id$ */
/*
 * MotePowerManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management.power;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.power.MotePowerManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.server.management.configuration.MoteManagerImpl;
import edu.clemson.cs.nestbed.server.util.UsbPowerControl;


public class MotePowerManagerImpl extends    UnicastRemoteObject
                                  implements MotePowerManager {

    private final static MotePowerManager instance;
    private final static Log              log      =
                                LogFactory.getLog(MotePowerManagerImpl.class);

    static {
        MotePowerManager impl = null;

        try {
            impl = new MotePowerManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }

    private MoteManager moteManager = MoteManagerImpl.getInstance();


    public static MotePowerManager getInstance() {
        return instance;
    }


    public void powerOff(int moteID) throws RemoteException {
        log.debug("Request to power off mote with moteID " + moteID);

        try {
            Mote mote = moteManager.getMote(moteID);

            if (mote != null) {
                UsbPowerControl.powerOffDevice(mote.getHubBus(),
                                               mote.getHubDevice(),
                                               mote.getHubPort());
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in powerOff";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void powerOn(int moteID) throws RemoteException {
        log.debug("Request to power on mote with moteID " + moteID);

        try {
            Mote mote = moteManager.getMote(moteID);

            if (mote != null) {
                UsbPowerControl.powerOnDevice(mote.getHubBus(),
                                              mote.getHubDevice(),
                                              mote.getHubPort());
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in powerOn";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    private MotePowerManagerImpl() throws RemoteException {
        super();
    }
}
