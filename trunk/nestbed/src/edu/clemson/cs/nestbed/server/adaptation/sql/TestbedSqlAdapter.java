/* $Id$ */
/*
 * TestbedSqlAdapter.java
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

import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProjectAdapter;
import edu.clemson.cs.nestbed.server.adaptation.TestbedAdapter;


public class TestbedSqlAdapter extends    SqlAdapter
                               implements TestbedAdapter {
    private final static Log log = LogFactory.getLog(TestbedSqlAdapter.class);

    private enum Index {
        ID,
        NAME,
        DESCRIPTION,
        IMAGE,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, Testbed> readTestbeds() throws AdaptationException {
        Map<Integer, Testbed> testbeds   = new HashMap<Integer, Testbed>();
        Connection            connection = null;
        Statement             statement  = null;
        ResultSet             resultSet  = null;

        ProjectAdapter  projectAdapter   =
                        AdapterFactory.createProjectAdapter(AdapterType.SQL);

        try {
            String query = "SELECT * FROM Testbeds";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                int    id;
                String name;
                String description;
                Blob   imageBlob;
                Date   timestamp;

                id           = resultSet.getInt(    Index.ID.index());
                name         = resultSet.getString( Index.NAME.index());
                description  = resultSet.getString( Index.DESCRIPTION.index());
                imageBlob    = resultSet.getBlob(   Index.IMAGE.index());
                timestamp    = resultSet.getDate(   Index.TIMESTAMP.index());

                byte[] image = (imageBlob != null && imageBlob.length() > 0)
                               ? imageBlob.getBytes(0, (int) imageBlob.length())
                               : null;


                Testbed testbed = new Testbed(id, name, description,
                                              image, timestamp);
                testbeds.put(id, testbed);
            }
        } catch (SQLException ex) {
            String msg = "SQLException in readTestbeds";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();  } catch (Exception ex) { }
            try { statement.close();  } catch (Exception ex) { }
            try { connection.close(); } catch (Exception ex) { }
        }

        return testbeds;
    }
}
