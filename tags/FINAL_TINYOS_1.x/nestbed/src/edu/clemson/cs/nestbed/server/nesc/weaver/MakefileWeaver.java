/* $Id: */
/*
 * MakefileWeaver.java
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
package edu.clemson.cs.nestbed.server.nesc.weaver;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.server.util.FileUtils;


public class MakefileWeaver {
    private final static Log log = LogFactory.getLog(MakefileWeaver.class);

    private File         makefile;
    private File         makefileBackup;
	private List<String> linesAdded      = new ArrayList<String>();


    public MakefileWeaver(File makefile) throws FileNotFoundException,
                                                Exception {
        this.makefile       = makefile;
        this.makefileBackup = new File(makefile + ".orig");

        FileUtils.copyFile(makefile, makefileBackup);
    }


    public void addLine(String line) {
		linesAdded.add(line);
    }


    public void regenerateMakefile() throws FileNotFoundException,
		                                    IOException {
		boolean     append = true;
        PrintWriter out    = new PrintWriter(new FileWriter(makefile, append));

		for (String i : linesAdded) {
			out.println(i);
		}

        out.close();
    }
}
