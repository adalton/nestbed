/* $Id$ */
/*
 * ZipUtils.java
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
package edu.clemson.cs.nestbed.common.util;


import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ZipUtils {
    private final static Log log         = LogFactory.getLog(ZipUtils.class);
    private final static int BUFFER_SIZE = 4096;

    public static byte[] zipDirectory(File directory) throws IOException {
        byte[] data;

        log.debug("zipDirectory: " + directory.getName());

        ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFFER_SIZE);
        ZipOutputStream zos = new ZipOutputStream(baos);
        zipDirectory(directory, directory.getName(), zos);

        data = baos.toByteArray();
        baos.close();

        return data;
    }


    public static void zipDirectory(File directory, File output)
                                                        throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(output));
        zipDirectory(directory, directory.getName(), zos);
        zos.close();
    }


    public static File unzip(byte[] zipData, File directory)
                                                        throws IOException {
        ByteArrayInputStream bais  = new ByteArrayInputStream(zipData);
        ZipInputStream       zis   = new ZipInputStream(bais);
        ZipEntry             entry = zis.getNextEntry();
        File                 root  = null;

        while (entry != null) {
            if (entry.isDirectory()) {
                File f = new File(directory, entry.getName());
                f.mkdir();

                if (root == null) {
                    root = f;
                }
            } else {
                BufferedOutputStream out;
                out = new BufferedOutputStream(
                                  new FileOutputStream(
                                      new File(directory, entry.toString())),
                              BUFFER_SIZE);

                // ZipInputStream can only give us one byte at a time...
                for (int data = zis.read(); data != -1; data = zis.read()) {
                    out.write(data);
                }
                out.close();
            }

            zis.closeEntry();
            entry = zis.getNextEntry();
        }
        zis.close();

        return root;
    }


    private static void zipDirectory(File directory, String name,
                                     ZipOutputStream zos) throws IOException {
        // *MUST* append the trailing slash for a ZipEntry to identify an
        // entry as a directory.
        name += "/";

        zos.putNextEntry(new ZipEntry(name));
        zos.closeEntry();

        String[] entryList = directory.list();

        for (int i = 0; i < entryList.length; ++i) {
            File f = new File(directory, entryList[i]);

            if (f.isDirectory()) {
                zipDirectory(f, name + f.getName(), zos);
            } else {
                FileInputStream fis     = new FileInputStream(f);
                ZipEntry        entry   = new ZipEntry(name + f.getName());
                byte[]          buffer  = new byte[BUFFER_SIZE];
                int             bytesIn = 0;

                zos.putNextEntry(entry);

                while ( (bytesIn = fis.read(buffer)) != -1 ) {
                    zos.write(buffer, 0, bytesIn);
                }

                fis.close();
                zos.closeEntry();
            }
        }
    }
}
