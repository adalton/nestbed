/* $Id$ */
/*
 * BuildTestbed.java
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
package edu.clemson.cs.nestbed.server.tools;


import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import edu.clemson.cs.nestbed.server.adaptation.MoteAdapter;
import edu.clemson.cs.nestbed.server.adaptation.sql.MoteSqlAdapter;
import edu.clemson.cs.nestbed.common.model.Mote;


public class BuildTestbed {
    private final static Log log = LogFactory.getLog(BuildTestbed.class);
    //private final static String CONN_STR = "jdbc:apache:commons:dbcp:/nestbed";
    //private final static String CONN_STR = ;

    public static void main(String[] args)  {
        try {
            BasicConfigurator.configure();
            loadProperties();

            if(args.length < 2) {
                System.out.println("Usage: BuildTestbed <testbedID> <inputfile>");
                System.exit(0);
            }

            int                testbedID   = Integer.parseInt(args[0]);
            String             filename    = args[1];
            Connection         conn        = null;
            Statement          statement   = null;
            MoteSqlAdapter     adapter     = new MoteSqlAdapter();
            Map<Integer, Mote> motes       = adapter.readMotes();

            log.info(motes);

            String connStr = System.getProperty("nestbed.options.databaseConnectionString");
            log.info("connStr: " + connStr);

            conn      = DriverManager.getConnection(connStr);
            statement = conn.createStatement();

            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(filename)));

            String line;
            while ( (line = in.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                int    address = Integer.parseInt(tokenizer.nextToken());
                String serial  = tokenizer.nextToken();
                int    xLoc    = Integer.parseInt(tokenizer.nextToken());
                int    yLoc    = Integer.parseInt(tokenizer.nextToken());

                log.info("Input Mote:\n" +
                         "-----------\n" +
                         "address:  " + address + "\n" +
                         "serial:   " + serial  + "\n" +
                         "xLoc:     " + xLoc    + "\n" +
                         "yLoc:     " + yLoc);

                for (Mote i : motes.values()) {
                    if (i.getMoteSerialID().equals(serial)) {
                        String query =
                            "INSERT INTO MoteTestbedAssignments" +
                                "(testbedID, moteID, moteAddress," +
                                " moteLocationX, moteLocationY) VALUES (" +
                                testbedID + ", " +
                                i.getID() + ", " +
                                address   + ", " +
                                xLoc      + ", " +
                                yLoc + ")";
                        log.info(query);
                        statement.executeUpdate(query);
                    }
                }
            }
            conn.commit();
        } catch (Exception ex) {
            log.error("Exception in main", ex);
        }
    }

    private static void loadProperties() throws IOException {
        Properties  systemProperties;
        InputStream propertyStream;

        systemProperties = System.getProperties();
        propertyStream   = BuildTestbed.class.getClassLoader().
                                     getResourceAsStream("server.properties");
        systemProperties.load(propertyStream);
        propertyStream.close();

        propertyStream   = BuildTestbed.class.getClassLoader().
                                     getResourceAsStream("common.properties");
        systemProperties.load(propertyStream);
        propertyStream.close();
    }

}
