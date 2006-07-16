/* $Id$ */
/*
 * AliasCommand.java
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


class AliasCommand implements Command {
    private Level level;


    public AliasCommand(Level level) {
        this.level = level;
    }


    public void execute(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage:  alias <oldCommand> <newCommand>");
            return;
        }

        Command command = level.getCommand(args[1]);
        if (command == null) {
            System.err.println("Command " + args[1] + " not found");
            return;
        }

        Command newCommand = level.getCommand(args[2]);
        if (newCommand != null) {
            System.err.println("Command " + args[2] + " already defined");
            return;
        }

        level.addCommand(args[2], new AliasWrapper(args[1], command));
    }


    public String getHelpText() {
        return "Create an alias for a command";
    }


    private class AliasWrapper implements Command {
        private String  name;
        private Command command;


        public AliasWrapper(String name, Command command) {
            this.name    = name;
            this.command = command;
        }

        public void execute(String[] args) throws Exception {

            command.execute(args);
        }


        public String getHelpText() {
            return command.getHelpText() + " (Alias for " + name + ")";
        }
    }
}
