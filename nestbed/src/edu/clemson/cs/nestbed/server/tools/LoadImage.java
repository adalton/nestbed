/*
 * LoadImage.java
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


public class LoadImage {
    private final static String CONN_STR = "jdbc:apache:commons:dbcp:/nestbed";

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: LoadImage <filename> <MoteTypeID>");
            System.exit(0);
        }

        String            filename   = args[0];
        int               moteTypeID = Integer.parseInt(args[1]);
        Connection        conn       = null;
        PreparedStatement ps         = null;

        try {
            String url      = "jdbc:mysql://localhost:3306/webphotogallery";
            String username = "root";
            String password = "";

            conn = DriverManager.getConnection(CONN_STR);
            ps   = conn.prepareStatement(
                    "UPDATE MoteTypes SET image = ? WHERE id = " + moteTypeID);


            // Insert the image into the second Blob
            File            image = new File(filename);
            FileInputStream fis   = new FileInputStream(image);
            ps.setBinaryStream(1, fis, (int) image.length());

            // Execute the INSERT
            int count = ps.executeUpdate();
            System.out.println("Rows inserted: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { ps.close();   } catch (Exception e) { }
            try { conn.close(); } catch (Exception e) { }
        }
    }
}
