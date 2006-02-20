/*
 * ProgramSqlAdapter.java
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

import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.server.adaptation.ProgramAdapter;


public class ProgramSqlAdapter implements ProgramAdapter {
    private final static String CONN_STR;
    private final static Log    log = LogFactory.getLog(
                                                    ProgramSqlAdapter.class);
    static {
        CONN_STR = System.getProperty("testbed.database.connectionString");
    }

    private enum Index {
        ID,
        PROJECTID,
        NAME,
        DESCRIPTION,
        SOURCEPATH,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, Program> readPrograms() {
        Map<Integer, Program>  programs   = new HashMap<Integer, Program>();
        Connection             connection = null;
        Statement              statement  = null;
        ResultSet              resultSet  = null;

        try {
            String query = "SELECT * FROM Programs";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                Program program = getProgram(resultSet);
                programs.put(program.getID(), program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return programs;
    }


    public Program createNewProgram(int    projectID, String name,
                                    String description) {
        Program    program    = null;
        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "INSERT INTO Programs(projectID, name, " +
                           "description, sourcePath) VALUES ( "     +
                           projectID         + ", " +
                           "'" + name        + "', " +
                           "'" + description + "', " +
                           "'" + "<unknown>" + "')";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query = "SELECT * FROM Programs WHERE " +
                    " projectID   =  " + projectID   + "  AND " +
                    " name        = '" + name        + "' AND " +
                    " description = '" + description + "'";

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                program = getProgram(resultSet);
            } else {
                log.error("Attempt to create program failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "create program.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return program;
    }


    public Program updateProgramPath(int id, String sourcePath) {
        Program    program    = null;
        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "UPDATE Programs SET " +
                           "sourcePath = '" + sourcePath + "' " +
                           "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query     = "SELECT * from Programs WHERE id = " + id;
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                program = getProgram(resultSet);
            } else {
                log.error("Attempt to update program failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "update program.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return program;
    }


    public Program deleteProgram(int id) {
        Program    program    = null;
        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "SELECT * FROM Programs WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            if (resultSet.next()) {
                program = getProgram(resultSet);
                query   = "DELETE FROM Programs WHERE id = " + id;

                statement.executeUpdate(query);
            } else {
                log.error("Attempt to delete program failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "delete program.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return program;
    }


    private final Program getProgram(ResultSet resultSet) throws SQLException {
        int    id          = resultSet.getInt(   Index.ID.index());
        int    projectID   = resultSet.getInt(   Index.PROJECTID.index());
        String name        = resultSet.getString(Index.NAME.index());
        String description = resultSet.getString(Index.DESCRIPTION.index());
        String sourcePath  = resultSet.getString(Index.SOURCEPATH.index());
        Date   timestamp   = resultSet.getDate(  Index.TIMESTAMP.index());

        return new Program(id,          projectID,  name,
                           description, sourcePath, timestamp);
    }
}
