/* $Id$ */
/*
 * MoteSqlAdapter.java
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
package edu.clemson.cs.nestbed.server.adaptation.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.MoteAdapter;
import edu.clemson.cs.nestbed.server.util.UsbDeviceInformation;


public class MoteSqlAdapter extends   SqlAdapter
                            implements MoteAdapter {

    private final static Log log = LogFactory.getLog(MoteSqlAdapter.class);

    private enum Index {
        ID,
        MOTESERIALID,
        MOTETYPEID,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, Mote> readMotes() throws AdaptationException {
        Map<Integer, Mote> motes      = new HashMap<Integer, Mote>();
        Connection         connection = null;
        Statement          statement  = null;
        ResultSet          resultSet  = null;

        try {
            String query = "SELECT * FROM Motes";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                int                  id;
                String               moteSerialID;
                int                  moteTypeID;
                Date                 timestamp;
                int                  bus;
                int                  device;
                int                  port;
                UsbDeviceInformation usbDevInfo;

                id           = resultSet.getInt(    Index.ID.index());
                moteSerialID = resultSet.getString( Index.MOTESERIALID.index());
                moteTypeID   = resultSet.getInt(    Index.MOTETYPEID.index());
                timestamp    = resultSet.getDate(   Index.TIMESTAMP.index());

                usbDevInfo   = new UsbDeviceInformation(moteSerialID);
                bus          = usbDevInfo.getBus();
                device       = usbDevInfo.getDevice();
                port         = usbDevInfo.getPort();

                Mote mote = new Mote(id, moteSerialID, moteTypeID, timestamp,
                                     bus, device, port);

                motes.put(id, mote);
            }
        } catch (SQLException ex) {
            String msg = "SQLException in readMotes";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();  } catch (Exception ex) { }
            try { statement.close();  } catch (Exception ex) { }
            try { connection.close(); } catch (Exception ex) { }
        }

        return motes;
    }
}
