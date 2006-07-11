/* $Id$ */
/*
 * ManCommand.java
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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class ManCommand implements Command {
    private Level level;


    public ManCommand(Level level) {
        this.level = level;
    }


    public void execute(String[] args) throws Exception {
        int maxLength = 0;

        List<String> commandNames;
        commandNames = new ArrayList<String>(level.getCommandNames());
        Collections.sort(commandNames);

        for (String i : commandNames) {
            maxLength = (i.length() > maxLength) ? i.length() : maxLength;
        }

        if (maxLength > 0) {
            String formatString = "  %-" + maxLength + "s - %s\n";

            for (String i : commandNames) {
                Command command = level.getCommand(i);
                System.out.printf(formatString, i, command.getHelpText());
            }
        }

    }


    public String getHelpText() {
        return "Get help with commands";
    }
}
