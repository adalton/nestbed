/* $Id$ */
/*
 * FileUtils.java
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * Department of Computer Science
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
package edu.clemson.cs.nestbed.server.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FileUtils {
    private final static Log log  = LogFactory.getLog(FileUtils.class);

    public static void copyFile(File in, File out)
                                                throws FileNotFoundException,
                                                       IOException {
        FileChannel sourceChannel      = null;
        FileChannel destinationChannel = null;

        try {
            sourceChannel      = new FileInputStream(in).getChannel();
            destinationChannel = new FileOutputStream(out).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                                     destinationChannel);
        } finally {
            try { sourceChannel.close();      } catch (Exception ex) { }
            try { destinationChannel.close(); } catch (Exception ex) { }
        }
    }


    public static File makeProgramDir(File root,      int testbedID,
                                      int  projectID, int programID) {
        File dir  = new File(root, Integer.toString(testbedID));
        dir.mkdir();

        dir = new File(dir, Integer.toString(projectID));
        dir.mkdir();

        dir = new File(dir, Integer.toString(programID));
        dir.mkdir();

        return dir;
    }


    public static void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }

            directory.delete();
        }
    }


    public static void cleanupParentDirectories(File root, File directory) {
        for (File dir = directory; dir != null && !dir.equals(root);
                                                dir = dir.getParentFile()) {
            if (dir.exists()) {
                dir.delete();
            }
        }
    }
}
