/* $Id$ */
/*
 * CdCommand.java
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


class CdCommand implements Command {
    private Level level;

    public CdCommand(Level level) {
        this.level = level;
    }

    public void execute(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("usage:  cd <level>");
            return;
        }

        if (args[1].equals(".")) {
            return;
        }

        if (args[1].equals("..")) {
            level.setNextLevel(level.getParentLevel());
            return;
        }

        boolean found = false;

        for (Entry i : level.getEntries()) {
            if (i instanceof LevelEntry) {
                LevelEntry levelEntry = (LevelEntry) i;

                if (levelEntry.getName().equals(args[1])) {
                    level.setNextLevel(levelEntry.getLevel());
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.err.println("Level " + args[1] + " not found.");
        }
    }

    public String getHelpText() {
        return "Changes to the specified level";
    }
}
