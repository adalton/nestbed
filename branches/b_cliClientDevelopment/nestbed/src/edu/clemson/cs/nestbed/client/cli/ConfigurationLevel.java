/* $Id:$ */
/*
 * ConfigurationLevel.java
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


class ConfigurationLevel extends Level {
    public ConfigurationLevel(String name, Level parentLevel) throws Exception {
        super(name, parentLevel);

        addEntry(new LevelEntry("Programs"));
        addEntry(new LevelEntry("SymbolProfiling"));
        addEntry(new LevelEntry("MessageProfiling"));
    }

    public Level getLevel(String name) throws Exception {
        Level newLevel = this;

        if (name.equals("Programs")) {
            newLevel = new ProgramLevel(name, this);
        } else if (name.equals("SymbolProfiling")) {
            newLevel = new SymbolProfilingLevel(name, this);
        } else if (name.equals("MessageProfiling")) {
            newLevel = new MessageProfilingLevel(name, this);
        } else {
            System.err.println("Unknown level:  " + name);
        }

        return newLevel;
    }
}
