/*
 * ProgramProfilingSymbolSqlAdapter.java
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

import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.ProgramProfilingSymbolAdapter;


public class ProgramProfilingSymbolSqlAdapter
                                    implements ProgramProfilingSymbolAdapter {

    private final static String CONN_STR;
    private final static Log    log = LogFactory.getLog(
                                        ProgramProfilingSymbolSqlAdapter.class);
    static {
        CONN_STR = System.getProperty("testbed.database.connectionString");
    }

    private enum Index {
        ID,
        PDCONFID,
        PROGSYMID,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, ProgramProfilingSymbol> readProgramProfilingSymbols() {
        Map<Integer, ProgramProfilingSymbol>  profilingSymbols =
                                new HashMap<Integer, ProgramProfilingSymbol>();

        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "SELECT * FROM ProgramProfilingSymbols";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                ProgramProfilingSymbol profilingSymbol =
                                            getProfilingSymbol(resultSet);
                profilingSymbols.put(profilingSymbol.getID(), profilingSymbol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return profilingSymbols;
    }


    public ProgramProfilingSymbol createNewProfilingSymbol(
                                            int configID, int programSymbolID)
                                                throws AdaptationException {
        ProgramProfilingSymbol profilingSymbol = null;
        Connection             connection      = null;
        Statement              statement       = null;
        ResultSet              resultSet       = null;

        try {
            String query =
                "INSERT INTO ProgramProfilingSymbols"    +
                "(projectDeploymentConfigurationID, programSymbolID)" +
                " VALUES (" + configID + ", " + programSymbolID + ")";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query =
                "SELECT * FROM ProgramProfilingSymbols WHERE "          +
                "projectDeploymentConfigurationID = " + configID        +
                " AND programSymbolID             = " + programSymbolID;

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                profilingSymbol = getProfilingSymbol(resultSet);
            } else {
                log.error("Attempt to create program profiling symbol failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "create program profiling symbol.", e);
            throw new AdaptationException("SQLException occured while " +
                                          "attempting to to create program " +
                                          "profiling symbol.");
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return profilingSymbol;
    }


    public ProgramProfilingSymbol deleteProfilingSymbol(int id) {
        ProgramProfilingSymbol profilingSymbol = null;
        Connection             connection      = null;
        Statement              statement       = null;
        ResultSet              resultSet       = null;

        try {
            String query = "SELECT * FROM ProgramProfilingSymbols " +
                           "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            if (resultSet.next()) {
                profilingSymbol = getProfilingSymbol(resultSet);
                query           = "DELETE FROM ProgramProfilingSymbols " +
                                  "WHERE id = " + id;

                statement.executeUpdate(query);
            } else {
                log.error("Attempt to delete program profiling symbol failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "delete program profiling symbol.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return profilingSymbol;

    }


    public ProgramProfilingSymbol updateProgramProfilingSymbol(
                                            int id,              int configID,
                                            int programSymbolID) {
        ProgramProfilingSymbol pps        = null;
        Connection             connection = null;
        Statement              statement  = null;
        ResultSet              resultSet  = null;

        try {
            String query =
                "UPDATE ProgramProfilingSymbols SET " +
                "projectDeploymentConfigurationID = " + configID        + ", " +
                "programSymbolID                  = " + programSymbolID + ", " +
                "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query = "SELECT * from ProgramProfilingSymbols WHERE " +
                    "id = " + id;
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                pps = getProfilingSymbol(resultSet);
            } else {
                log.error("Attempt to update program profiling symbol " +
                          "failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "update program profiling symbol.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }
        return pps;
    }


    private final ProgramProfilingSymbol getProfilingSymbol(
                                    ResultSet resultSet) throws SQLException {

        int  id              = resultSet.getInt( Index.ID.index());
        int  tbDepConfID     = resultSet.getInt( Index.PDCONFID.index());
        int  programSymbolID = resultSet.getInt( Index.PROGSYMID.index());
        Date timestamp       = resultSet.getDate(Index.TIMESTAMP.index());

        return new ProgramProfilingSymbol(id, tbDepConfID, programSymbolID,
                                          timestamp);
    }
}
