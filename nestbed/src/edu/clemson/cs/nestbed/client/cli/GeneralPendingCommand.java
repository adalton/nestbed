/* $Id:$ */ 
/*
 * GeneralPendingCommand.java
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


class GeneralPendingCommand implements PendingCommand {
    private String[] args;


    public GeneralPendingCommand(String[] args) {
        this.args = args;
    }


    public Level runCommand(Level level) throws Exception {
        String[] expandedArgs = new String[args.length];
        for (int i = 0; i < expandedArgs.length; ++i) {
            Variables.set("__internal", args[i]);
            expandedArgs[i] = Variables.unset("__internal");
        }

        return level.runCommand(expandedArgs);
    }
}
