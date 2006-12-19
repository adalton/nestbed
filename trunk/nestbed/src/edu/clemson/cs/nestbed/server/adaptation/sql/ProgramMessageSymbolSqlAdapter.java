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
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProjectAdapter;
import edu.clemson.cs.nestbed.server.adaptation.ProgramMessageSymbolAdapter;


public class ProgramMessageSymbolSqlAdapter extends SqlAdapter
                                        implements ProgramMessageSymbolAdapter {
    private final static Log log = LogFactory.getLog(
                                            ProgramMessageSymbolAdapter.class);
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


    public Map<Integer, ProgramMessageSymbol> readProgramMessageSymbols()
                                                throws AdaptationException {
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
        } catch (SQLException ex) {
            String msg = "SQLException in readProgramMessageSymbols";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();  } catch (Exception ex) { }
            try { statement.close();  } catch (Exception ex) { }
            try { connection.close(); } catch (Exception ex) { }
        }

        return programMessageSymbols;
    }


    public ProgramMessageSymbol addProgramMessageSymbol(int    programID,
                                                        String name,
                                                        byte[] bytecode)
                                                    throws AdaptationException {
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

            log.info("INSERT INTO ProgramMessageSymbols(programID, name, " +
                                "bytecode) VALUES (" +
                                programID + ", '" +
                                name      + "', " +
                                "<bytecode>)");
            preparedStatement.executeUpdate();


            statement  = connection.createStatement();
            query = "SELECT * FROM ProgramMessageSymbols WHERE " +
                    "programID =  " + programID + " AND " +
                    "name      = '" + name      + "'";
            resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                connection.rollback();
                String msg = "Attempt to add program message symbol failed.";
                log.error(msg);;
                throw new AdaptationException(msg);
            }

            programMessageSymbol = getProgramMessageSymbol(resultSet);
            connection.commit();
        } catch (SQLException ex) {
            try { connection.rollback(); } catch (Exception e) { }

            String msg = "SQLException in addProgramMessageSymbol";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();         } catch (Exception ex) { }
            try { preparedStatement.close(); } catch (Exception ex) { }
            try { statement.close();         } catch (Exception ex) { }
            try { connection.close();        } catch (Exception ex) { }
        }

        return programMessageSymbol;
    }


    public ProgramMessageSymbol deleteProgramMessageSymbol(int id)
                                                    throws AdaptationException {
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

            if (!resultSet.next()) {
                String msg = "Attempt to delete program message type " +
                             "failed.";
                log.error(msg);;
                throw new AdaptationException(msg);
            }

            pmt   = getProgramMessageSymbol(resultSet);
            query = "DELETE FROM ProgramMessageSymbols " +
                    "WHERE id = " + id;

            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException ex) {
            try { connection.rollback(); } catch (Exception e) { }

            String msg = "SQLException in deleteProgramMessageSymbol";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();  } catch (Exception ex) { }
            try { statement.close();  } catch (Exception ex) { }
            try { connection.close(); } catch (Exception ex) { }
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
