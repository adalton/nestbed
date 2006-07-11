/* $Id:$ */
/*
 * TestbedLevel.java
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


class TestbedLevel extends Level {
    public TestbedLevel(String name, Level parentLevel) throws Exception {
        super(name, parentLevel);

        addEntry(new LevelEntry("MyProject1"));
        addEntry(new LevelEntry("MyProject2"));

        addCommand("rm",     new RmCommand());
        addCommand("mkproj", new MkprojCommand());
    }

    public Level getLevel(String name) throws Exception {
        return new ProjectLevel(name, this);
    }


    private class RmCommand implements Command {
        public void execute(String[] args) {
            System.out.println("RmCommand:  TODO");
        }

        public String getHelpText() {
            return "Removes the specified project";
        }
    }


    private class MkprojCommand implements Command {
        public void execute(String[] args) {
            System.out.println("MkprojCommand:  TODO");
        }

        public String getHelpText() {
            return "Creates a new project";
        }
    }
}
