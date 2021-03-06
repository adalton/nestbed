/* $Id$ */
/*
 * MoteTypeSqlAdapter.java
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


import java.sql.Blob;
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

import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.MoteTypeAdapter;


public class MoteTypeSqlAdapter extends   SqlAdapter
                                implements MoteTypeAdapter {

    private final static Log log = LogFactory.getLog(MoteTypeSqlAdapter.class);

    private enum Index {
        ID,
        NAME,
        TOTALROM,
        TOTALRAM,
        TOTALEEPROM,
        IMAGE,
        TOSPLATFORM,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, MoteType> readMoteTypes() throws AdaptationException {
        Map<Integer, MoteType> moteTypes  = new HashMap<Integer, MoteType>();
        Connection             connection = null;
        Statement              statement  = null;
        ResultSet              resultSet  = null;

        try {
            String query = "SELECT * FROM MoteTypes";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                int    id;
                String name;
                int    totalROM;
                int    totalRAM;
                int    totalEEPROM;
                Blob   imageBlob;
                String tosPlatform;
                Date   timestamp;

                id           = resultSet.getInt(    Index.ID.index());
                name         = resultSet.getString( Index.NAME.index());
                totalROM     = resultSet.getInt(    Index.TOTALROM.index());
                totalRAM     = resultSet.getInt(    Index.TOTALRAM.index());
                totalEEPROM  = resultSet.getInt(    Index.TOTALEEPROM.index());
                imageBlob    = resultSet.getBlob(   Index.IMAGE.index());
                tosPlatform  = resultSet.getString( Index.TOSPLATFORM.index());
                timestamp    = resultSet.getDate(   Index.TIMESTAMP.index());

                byte[] image = (imageBlob != null && imageBlob.length() > 0)
                               ? imageBlob.getBytes(1, (int) imageBlob.length())
                               : null;

                MoteType moteType  = MoteType.getMoteType(id, name,
                                            totalROM, totalRAM, totalEEPROM,
                                            image, tosPlatform, timestamp);
                moteTypes.put(id, moteType);
            }
        } catch (SQLException ex) {
            String msg = "SQLException in readMoteTypes";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();  } catch (Exception ex) { }
            try { statement.close();  } catch (Exception ex) { }
            try { connection.close(); } catch (Exception ex) { }
        }

        return moteTypes;
    }
}
