/* $Id$ */
/*
 * MoteDeploymentConfigurationSqlAdapter.java
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

import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.server.adaptation.MoteDeploymentConfigurationAdapter;


public class MoteDeploymentConfigurationSqlAdapter
                                implements MoteDeploymentConfigurationAdapter {

    private final static Log    log = LogFactory.getLog(
                                   MoteDeploymentConfigurationSqlAdapter.class);
    private final static String CONN_STR;

    static {
        CONN_STR = System.getProperty("testbed.database.connectionString");
    }

    private enum Index {
        ID,
        PROJECTCONFID,
        MOTEID,
        PROGRAMID,
        RADIOPOWERLEVEL,
        TIMESTAMP;

        public int index() {
            return ordinal() + 1;
        }
    }


    public Map<Integer, MoteDeploymentConfiguration>
                                        readMoteDeploymentConfigurations() {

        Map<Integer, MoteDeploymentConfiguration>  moteDepConfigs =
                        new HashMap<Integer, MoteDeploymentConfiguration>();

        Connection connection = null;
        Statement  statement  = null;
        ResultSet  resultSet  = null;

        try {
            String query = "SELECT * FROM MoteDeploymentConfigurations";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            while (resultSet.next()) {
                MoteDeploymentConfiguration moteDepConfiguration;
                moteDepConfiguration = getMoteDeploymentConfiguration(
                                                                resultSet);
                moteDepConfigs.put(moteDepConfiguration.getID(),
                                   moteDepConfiguration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return moteDepConfigs;
    }


    public MoteDeploymentConfiguration updateMoteDeploymentConfiguration(
                                                    int mdConfigID,
                                                    int programID,
                                                    int radioPowerLevel) {
        MoteDeploymentConfiguration mdc        = null;
        Connection                  connection = null;
        Statement                   statement  = null;
        ResultSet                   resultSet  = null;

        try {
            String query = "UPDATE MoteDeploymentConfigurations SET "    +
                           "programID       = " + programID       + ", " +
                           "radioPowerLevel = " + radioPowerLevel + "  " +
                           "WHERE id = "        + mdConfigID;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query = "SELECT * from MoteDeploymentConfigurations WHERE " +
                    "id = " + mdConfigID;
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                mdc = getMoteDeploymentConfiguration(resultSet);
            } else {
                log.error("Attempt to update mote deployment configuration " +
                          "failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "update mote deployment configuration.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return mdc;
    }


    public MoteDeploymentConfiguration addMoteDeploymentConfiguration(
                                                    int projectDepConfID,
                                                    int moteID,
                                                    int programID,
                                                    int radioPowerLevel) {
        MoteDeploymentConfiguration mdc        = null;
        Connection                  connection = null;
        Statement                   statement  = null;
        ResultSet                   resultSet  = null;

        try {
            String query = "INSERT INTO MoteDeploymentConfigurations(" +
                           "projectDeploymentConfigurationID, " +
                           "moteID, programID, radioPowerLevel) VALUES (" +
                           projectDepConfID + ", " + moteID          + ", " +
                           programID        + ", " + radioPowerLevel + ")";

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            query = "SELECT * from MoteDeploymentConfigurations WHERE " +
                    "projectDeploymentConfigurationID = " + projectDepConfID +
                    " AND moteID = " + moteID;

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                mdc = getMoteDeploymentConfiguration(resultSet);
            } else {
                log.error("Attempt to add new mote deployment " +
                          "configuration failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "add mote deployment configuration.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return mdc;
    }


    public MoteDeploymentConfiguration
                                deleteMoteDeploymentConfiguration(int id) {

        MoteDeploymentConfiguration mdc        = null;
        Connection                  connection = null;
        Statement                   statement  = null;
        ResultSet                   resultSet  = null;

        try {
            String query = "SELECT * FROM MoteDeploymentConfigurations " +
                           "WHERE id = " + id;

            connection = DriverManager.getConnection(CONN_STR);
            statement  = connection.createStatement();
            resultSet  = statement.executeQuery(query);

            if (resultSet.next()) {
                mdc   = getMoteDeploymentConfiguration(resultSet);
                query = "DELETE FROM MoteDeploymentConfigurations " +
                        "WHERE id = " + id;

                statement.executeUpdate(query);
            } else {
                log.error("Attempt to delete mote deployment configuration " +
                          "failed.");
            }
        } catch (SQLException e) {
            log.error("SQLException occured while attempting to " +
                      "delete mote deployment configuration.", e);
        } finally {
            try { resultSet.close();  } catch (Exception e) { /* empty */ }
            try { statement.close();  } catch (Exception e) { /* empty */ }
            try { connection.close(); } catch (Exception e) { /* empty */ }
        }

        return mdc;
    }


    private final MoteDeploymentConfiguration
                        getMoteDeploymentConfiguration(ResultSet resultSet)
                                                        throws SQLException {
        int  id              = resultSet.getInt( Index.ID.index());
        int  configID        = resultSet.getInt( Index.PROJECTCONFID.index());
        int  moteID          = resultSet.getInt( Index.MOTEID.index());
        int  programID       = resultSet.getInt( Index.PROGRAMID.index());
        int  radioPowerLevel = resultSet.getInt( Index.RADIOPOWERLEVEL.index());
        Date timestamp       = resultSet.getDate(Index.TIMESTAMP.index());

        return new MoteDeploymentConfiguration(id, configID,
                                               moteID, programID,
                                               radioPowerLevel, timestamp);
    }
}
