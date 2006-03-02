/* $Id$ */
/*
 * ProgramMessageSymbolSqlAdapter.java
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


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProjectAdapter;
import edu.clemson.cs.nestbed.server.adaptation.ProgramMessageSymbolAdapter;


public class ProgramMessageSymbolSqlAdapter
                                        implements ProgramMessageSymbolAdapter {
    private final static String CONN_STR;
    private final static Log    log      =
                           LogFactory.getLog(ProgramMessageSymbolAdapter.class);
    static {
        CONN_STR = System.getProperty("testbed.database.connectionString");
    }

    private enum Index {
        ID,
        PROGRAMID,
        NAME,
        BYTECODE,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, ProgramMessageSymbol> readProgramMessageSymbols() {
        Map<Integer, ProgramMessageSymbol> programMessageSymbols;
        Connection                         connection;
        Statement                          statement;
        ResultSet                          resultSet;
        ProjectAdapter                     projectAdapter;

        programMessageSymbols = new HashMap<Integer, ProgramMessageSymbol>();
        connection            = null;
        statement             = null;
        resultSet             = null;
        projectAdapter        = AdapterFactory.createProjectAdapter(
                                                            AdapterType.SQL);

        try {
            String query = "SELECT * FROM ProgramMessageSymbols";

            connection   = DriverManager.getConnection(CONN_STR);
            statement    = connection.createStatement();
            resultSet    = statement.executeQuery(query);

            while (resultSet.next()) {
                ProgramMessageSymbol programMessageSymbol;
                programMessageSymbol = getProgramMessageSymbol(resultSet);

                programMessageSymbols.put(programMessageSymbol.getID(),
                                        programMessageSymbol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return programMessageSymbols;
    }


    public ProgramMessageSymbol addProgramMessageSymbol(int    programID,
                                                        String name,
                                                        byte[] bytecode) {
        ProgramMessageSymbol programMessageSymbol = null;
        Connection           connection           = null;
        PreparedStatement    preparedStatement    = null;
        Statement            statement            = null;
        ResultSet            resultSet            = null;
        InputStream          stream               = new ByteArrayInputStream(
                                                                    bytecode);
        try {
            String query =
                    "INSERT INTO ProgramMessageSymbols(programID, name, " +
                    "bytecode) VALUES ( ?, ?, ? )";

            connection         = DriverManager.getConnection(CONN_STR);
            preparedStatement  = connection.prepareStatement(query);

            preparedStatement.setInt(         1, programID);
            preparedStatement.setString(      2, name);
            preparedStatement.setBinaryStream(3, stream, bytecode.length);
            preparedStatement.executeUpdate();


            statement  = connection.createStatement();
            query = "SELECT * FROM ProgramMessageSymbols WHERE " +
                    "programID =  " + programID + " AND " +
                    "name      = '" + name      + "'";
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                programMessageSymbol = getProgramMessageSymbol(resultSet);
            } else {
                log.error("Attempt to add program message type failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "add program message type.", e);
        } finally {
            try { resultSet.close();         } catch (Exception e) { }
            try { preparedStatement.close(); } catch (Exception e) { }
            try { statement.close();         } catch (Exception e) { }
            try { connection.close();        } catch (Exception e) { }
        }

        return programMessageSymbol;
    }


    public ProgramMessageSymbol deleteProgramMessageSymbol(int id) {
        ProgramMessageSymbol pmt        = null;
        Connection           connection = null;
        Statement            statement  = null;
        ResultSet            resultSet  = null;

        try {
            String query = "SELECT * FROM ProgramMessageSymbols " +
                           "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            if (resultSet.next()) {
                pmt   = getProgramMessageSymbol(resultSet);
                query = "DELETE FROM ProgramMessageSymbols " +
                        "WHERE id = " + id;

                statement.executeUpdate(query);
            } else {
                log.error("Attempt to delete program message type " +
                          "failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "delete program message type.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { }
            try { statement.close();  } catch (Exception e) { }
            try { connection.close(); } catch (Exception e) { }
        }

        return pmt;
    }


    private final ProgramMessageSymbol
                        getProgramMessageSymbol(ResultSet resultSet)
                                                        throws SQLException {
        int    id           = resultSet.getInt(    Index.ID.index());
        int    programID    = resultSet.getInt(    Index.PROGRAMID.index());
        String name         = resultSet.getString( Index.NAME.index());
        Blob   bytecodeBlob = resultSet.getBlob(   Index.BYTECODE.index());
        Date   timestamp    = resultSet.getDate(   Index.TIMESTAMP.index());

        byte[] bytecode     = bytecodeBlob.getBytes(1,
                                                 (int) bytecodeBlob.length());

        return new ProgramMessageSymbol(id, programID, name, bytecode,
                                        timestamp);
    }
}
