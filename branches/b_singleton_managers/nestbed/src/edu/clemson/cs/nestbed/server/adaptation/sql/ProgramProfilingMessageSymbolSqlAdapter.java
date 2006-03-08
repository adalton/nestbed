/* $Id$ */
/*
 * ProgramProfilingMessageSymbolSqlAdapter.java
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

import edu.clemson.cs.nestbed.common.model.ProgramProfilingMessageSymbol;
import edu.clemson.cs.nestbed.server.adaptation.ProgramProfilingMessageSymbolAdapter;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;


public class ProgramProfilingMessageSymbolSqlAdapter
                            implements ProgramProfilingMessageSymbolAdapter {

    private final static String CONN_STR;
    private final static Log    log = LogFactory.getLog(
                                ProgramProfilingMessageSymbolSqlAdapter.class);
    static {
        CONN_STR = System.getProperty("testbed.database.connectionString");
    }

    private enum Index {
        ID,
        PDCONFID,
        PROGMSGSYMID,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, ProgramProfilingMessageSymbol>
                               readProgramProfilingMessageSymbols() {
        Map<Integer, ProgramProfilingMessageSymbol>  profilingMessageSymbols;

        profilingMessageSymbols =
                    new HashMap<Integer, ProgramProfilingMessageSymbol>();

        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "SELECT * FROM ProgramProfilingMessageSymbols";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                ProgramProfilingMessageSymbol profilingMessageSymbol =
                                        getProfilingMessageSymbol(resultSet);
                profilingMessageSymbols.put(profilingMessageSymbol.getID(),
                                            profilingMessageSymbol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close();  } catch (Exception e) { }
            try { statement.close();  } catch (Exception e) { }
            try { connection.close(); } catch (Exception e) { }
        }

        return profilingMessageSymbols;
    }


    public ProgramProfilingMessageSymbol createNewProfilingMessageSymbol(
                                            int configID, int programMessageSymbolID) {
        ProgramProfilingMessageSymbol profilingMessageSymbol = null;
        Connection                    connection             = null;
        Statement                     statement              = null;
        ResultSet                     resultSet              = null;

        try {
            String query =
                "INSERT INTO ProgramProfilingMessageSymbols"    +
                "(projectDeploymentConfigurationID, programMessageSymbolID)" +
                " VALUES (" + configID + ", " + programMessageSymbolID + ")";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query =
                "SELECT * FROM ProgramProfilingMessageSymbols WHERE "          +
                "projectDeploymentConfigurationID = " + configID + " AND " +
                "programMessageSymbolID           = " + programMessageSymbolID;

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                profilingMessageSymbol = getProfilingMessageSymbol(resultSet);
            } else {
                log.error("Attempt to create program profiling message " +
                          "symbol failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "create program profiling message symbol.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { }
            try { statement.close();  } catch (Exception e) { }
            try { connection.close(); } catch (Exception e) { }
        }

        return profilingMessageSymbol;
    }


    public ProgramProfilingMessageSymbol deleteProfilingMessageSymbol(int id)
                                                   throws AdaptationException {

        ProgramProfilingMessageSymbol profilingMessageSymbol = null;
        Connection                    connection             = null;
        Statement                     statement              = null;
        ResultSet                     resultSet              = null;

        try {
            String query = "SELECT * FROM ProgramProfilingMessageSymbols " +
                           "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            if (resultSet.next()) {
                profilingMessageSymbol = getProfilingMessageSymbol(resultSet);
                query           = "DELETE FROM ProgramProfilingMessageSymbols " +
                                  "WHERE id = " + id;

                statement.executeUpdate(query);
            } else {
                log.error("Attempt to delete program profiling message " +
                          "symbol failed.");
            }
        } catch (SQLException ex) {
            log.error("SQLException occured while attempting to " +
                      "delete program profiling message symbol.", ex);

            throw new AdaptationException("SQLException", ex);
        } finally {
            try { resultSet.close();  } catch (Exception e) { }
            try { statement.close();  } catch (Exception e) { }
            try { connection.close(); } catch (Exception e) { }
        }

        return profilingMessageSymbol;

    }

/*

    public ProgramProfilingMessageSymbol updateProgramProfilingMessageSymbol(
                                            int id,              int configID,
                                            int programMessageSymbolID) {
        ProgramProfilingMessageSymbol pps        = null;
        Connection             connection = null;
        Statement              statement  = null;
        ResultSet              resultSet  = null;

        try {
            String query =
                "UPDATE ProgramProfilingMessageSymbols SET " +
                "projectDeploymentConfigurationID = " + configID        + ", " +
                "programMessageSymbolID                  = " + programMessageSymbolID + ", " +
                "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query = "SELECT * from ProgramProfilingMessageSymbols WHERE " +
                    "id = " + id;
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                pps = getProfilingMessageSymbol(resultSet);
            } else {
                log.error("Attempt to update program profiling symbol " +
                          "failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "update program profiling symbol.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { }
            try { statement.close();  } catch (Exception e) { }
            try { connection.close(); } catch (Exception e) { }
        }
        return pps;
    }
*/

    private final ProgramProfilingMessageSymbol getProfilingMessageSymbol(
                                    ResultSet resultSet) throws SQLException {

        int  id           = resultSet.getInt( Index.ID.index());
        int  pdConfId     = resultSet.getInt( Index.PDCONFID.index());
        int  progMsgSymID = resultSet.getInt( Index.PROGMSGSYMID.index());
        Date timestamp    = resultSet.getDate(Index.TIMESTAMP.index());

        return new ProgramProfilingMessageSymbol(id, pdConfId, progMsgSymID,
                                                 timestamp);
    }
}
