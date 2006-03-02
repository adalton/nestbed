/* $Id$ */
/*
 * ProjectSqlAdapter.java
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

import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.server.adaptation.ProjectAdapter;


public class ProjectSqlAdapter implements ProjectAdapter {
    private final static String CONN_STR;
    private final static Log    log      =
                                    LogFactory.getLog(ProjectSqlAdapter.class);
    static {
        CONN_STR = System.getProperty("testbed.database.connectionString");
    }

    private enum Index {
        ID,
        TESTBEDID,
        NAME,
        DESCRIPTION,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, Project> readProjects() {
        Map<Integer, Project>  projects   = new HashMap<Integer, Project>();
        Connection             connection = null;
        Statement              statement  = null;
        ResultSet              resultSet  = null;

        try {
            String query = "SELECT * FROM Projects";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                Project project = getProject(resultSet);
                projects.put(project.getID(), project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return projects;
    }


    public Project createProject(int testbedID, String name,
                                 String description) {

        Project    project    = null;
        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "INSERT INTO Projects(testbedID, name, " +
                           "description) VALUES (" + testbedID +
                           ", '" + name + "', '" + description + "')";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query = "SELECT * FROM Projects WHERE "  +
                    " testbedID   = "  + testbedID   + "  AND " +
                    " name        = '" + name        + "' AND " +
                    " description = '" + description + "'";

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                project = getProject(resultSet);
            } else {
                log.error("Attempt to create project failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "create project.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return project;
    }


    public Project deleteProject(int projectID) {
        Project    project    = null;
        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "SELECT * FROM Projects WHERE id = " + projectID;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            if (resultSet.next()) {
                project = getProject(resultSet);
                query   = "DELETE FROM Projects WHERE id = " + projectID;

                statement.executeUpdate(query);
            } else {
                log.error("Attempt to delete project failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "delete project.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return project;
    }


    private final Project getProject(ResultSet resultSet) throws SQLException {
        int    id          = resultSet.getInt(   Index.ID.index());
        int    testbedID   = resultSet.getInt(   Index.TESTBEDID.index());
        String name        = resultSet.getString(Index.NAME.index());
        String description = resultSet.getString(Index.DESCRIPTION.index());
        Date   timestamp   = resultSet.getDate(  Index.TIMESTAMP.index());

        return new Project(id, testbedID, name, description, timestamp);
    }
}
