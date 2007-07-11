/* $Id$ */
/*
 * ProjectDeploymentConfigurationSqlAdapter.java
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

import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.ProjectDeploymentConfigurationAdapter;


public class ProjectDeploymentConfigurationSqlAdapter extends SqlAdapter
                            implements ProjectDeploymentConfigurationAdapter {

    private final static Log log = LogFactory.getLog(
                               ProjectDeploymentConfigurationSqlAdapter.class);
    private enum Index {
        ID,
        PROJECTID,
        NAME,
        DESCRIPTION,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, ProjectDeploymentConfiguration>
                                        readProjectDeploymentConfigurations()
                                                    throws AdaptationException {

        Map<Integer, ProjectDeploymentConfiguration>  projDepConfigs =
                        new HashMap<Integer, ProjectDeploymentConfiguration>();

        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "SELECT * FROM ProjectDeploymentConfigurations";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                ProjectDeploymentConfiguration pdConfiguration =
                                getProjectDeploymentConfiguration(resultSet);
                projDepConfigs.put(pdConfiguration.getID(), pdConfiguration);
            }
        } catch (SQLException ex) {
            String msg = "SQLException in readProjectDeploymentConfigurations";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();  } catch (Exception ex) { }
            try { statement.close();  } catch (Exception ex) { }
            try { connection.close(); } catch (Exception ex) { }
        }

        return projDepConfigs;
    }


    public ProjectDeploymentConfiguration
                createNewProjectDeploymentConfig(int    projectID, String name,
                                                 String description)
                                                    throws AdaptationException {

        ProjectDeploymentConfiguration config     = null;
        Connection                     connection = null;
        Statement                      statement  = null;
        ResultSet                      resultSet  = null;

        try {
            String query = "INSERT INTO ProjectDeploymentConfigurations" +
                           "(projectID, name, description) VALUES ("     +
                           projectID   + ", '" + name + "', '"           +
                           description + "')";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query = "SELECT * FROM ProjectDeploymentConfigurations WHERE "  +
                    " projectID   = "  + projectID   + "  AND " +
                    " name        = '" + name        + "' AND " +
                    " description = '" + description + "'";

            resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                connection.rollback();
                String msg = "Attempt to create " +
                              "ProjectDeploymentConfiguration failed.";
                log.error(msg);
                throw new AdaptationException(msg);
            }

            config = getProjectDeploymentConfiguration(resultSet);
            connection.commit();
        } catch (SQLException ex) {
            try { connection.rollback(); } catch (Exception e) { }

            String msg = "SQLException in createNewProjectDeploymentConfig";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();  } catch (Exception ex) { }
            try { statement.close();  } catch (Exception ex) { }
            try { connection.close(); } catch (Exception ex) { }
        }

        return config;
    }


    public ProjectDeploymentConfiguration
                                        deleteProjectDeploymentConfig(int id)
                                                    throws AdaptationException {
        ProjectDeploymentConfiguration config     = null;
        Connection                     connection = null;
        Statement                      statement  = null;
        ResultSet                      resultSet  = null;

        try {
            String query = "SELECT * FROM ProjectDeploymentConfigurations " +
                           "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            if (!resultSet.next()) {
                connection.rollback();
                String msg = "Attempt to delete project deployment " +
                             "configuration failed.";
                log.error(msg);
                throw new AdaptationException(msg);
            }
            config = getProjectDeploymentConfiguration(resultSet);
            query   = "DELETE FROM ProjectDeploymentConfigurations " +
                      "WHERE id = " + id;

            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException ex) {
            try { connection.rollback(); } catch (Exception e) { }

            String msg = "SQLException in deleteProjectDeploymentConfig";
            log.error(msg, ex);
            throw new AdaptationException(msg, ex);
        } finally {
            try { resultSet.close();  } catch (Exception ex) { }
            try { statement.close();  } catch (Exception ex) { }
            try { connection.close(); } catch (Exception ex) { }
        }

        return config;
    }


    private ProjectDeploymentConfiguration
                    getProjectDeploymentConfiguration(ResultSet resultSet)
                                                        throws SQLException {
        int    id          = resultSet.getInt(   Index.ID.index());
        int    projectID   = resultSet.getInt(   Index.PROJECTID.index());
        String name        = resultSet.getString(Index.NAME.index());
        String description = resultSet.getString(Index.DESCRIPTION.index());
        Date   timestamp   = resultSet.getDate(  Index.TIMESTAMP.index());

        return new ProjectDeploymentConfiguration(id, projectID, name,
                                                  description, timestamp);
    }
}
