/*
 * ProgramSymbolSqlAdapter.java
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

import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.server.adaptation.ProgramSymbolAdapter;


public class ProgramSymbolSqlAdapter implements ProgramSymbolAdapter {

    private final static String CONN_STR;
    private final static Log    log = LogFactory.getLog(
                                        ProgramSymbolSqlAdapter.class);
    static {
        CONN_STR = System.getProperty("testbed.database.connectionString");
    }

    private enum Index {
        ID,
        PROGID,
        MODULE,
        SYMBOL,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, ProgramSymbol> readProgramSymbols() {
        Map<Integer, ProgramSymbol>  programSymbols =
                                new HashMap<Integer, ProgramSymbol>();

        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "SELECT * FROM ProgramSymbols";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                ProgramSymbol programSymbol = getProgramSymbol(resultSet);
                programSymbols.put(programSymbol.getID(), programSymbol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return programSymbols;
    }


    public ProgramSymbol createNewProgramSymbol(int    programID,
                                                String module,
                                                String symbol) {
        ProgramSymbol programSymbol = null;
        Connection    connection    = null;
        Statement     statement     = null;
        ResultSet     resultSet     = null;

        try {
            String query =
                "INSERT INTO ProgramSymbols (programID, module, symbol)" +
                " VALUES (" + programID + ", '" + module +
                          "',  '" + symbol + "')";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query =
                "SELECT * FROM ProgramSymbols WHERE  " +
                "programID =  " + programID + "  AND " +
                "module    = '" + module    + "' AND " +
                "symbol    = '" + symbol    + "'";

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                programSymbol = getProgramSymbol(resultSet);
            } else {
                log.error("Attempt to create program symbol failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "create program symbol.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return programSymbol;
    }


    public ProgramSymbol deleteProgramSymbol(int id) {
        ProgramSymbol programSymbol = null;
        Connection    connection    = null;
        Statement     statement     = null;
        ResultSet     resultSet     = null;

        try {
            String query = "SELECT * FROM ProgramSymbols " +
                           "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            if (resultSet.next()) {
                programSymbol = getProgramSymbol(resultSet);
                query           = "DELETE FROM ProgramSymbols " +
                                  "WHERE id = " + id;

                statement.executeUpdate(query);
            } else {
                log.error("Attempt to delete program symbol failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "delete program symbol.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return programSymbol;

    }


    private final ProgramSymbol getProgramSymbol(ResultSet resultSet)
                                                         throws SQLException {
        int    id        = resultSet.getInt(   Index.ID.index());
        int    programID = resultSet.getInt(   Index.PROGID.index());
        String module    = resultSet.getString(Index.MODULE.index());
        String symbol    = resultSet.getString(Index.SYMBOL.index());
        Date   timestamp = resultSet.getDate(  Index.TIMESTAMP.index());

        return new ProgramSymbol(id, programID, module, symbol, timestamp);
    }
}
