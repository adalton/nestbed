/* $Id$ */
/*
 * ProgramLevel.java
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
package edu.clemson.cs.nestbed.client.cli;


class ProgramLevel extends Level {
    public ProgramLevel(String name, Level parentLevel) throws Exception {
        super(name, parentLevel);

        addEntry(new Entry("Program1"));
        addEntry(new Entry("Program2"));
        addEntry(new Entry("Program3"));

        addCommand("upload", new UploadCommand());
    }


    private class UploadCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.out.println("UploadCommand:  TODO");
        }

        public String getHelpText() {
            return "Upload a program";
        }
    }
}
