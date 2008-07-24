/* $Id$ */
/*
 * ForeachCompoundPendingCommand.java
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * School of Computing
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
package edu.clemson.cs.nestbed.client.cli;


public class ForeachCompoundPendingCommand extends CompoundPendingCommand {
    private String   variable;
    private String[] targets;


    public ForeachCompoundPendingCommand(String[] args) throws Exception {
        if (args.length != 3) {
            throw new Exception("ForeachCompoundPendingCommand:  FIXME");
        }

        variable = args[1];
        targets  = Level.readLine();

        if (targets[0].charAt(0) == '[') {
            StringBuffer buffer = new StringBuffer();
            String       token       = targets[0];
            String       range       = token.substring(1, token.length() - 1);
            String[]     rangeTokens = range.split("-");

            if (rangeTokens.length != 2) {
                throw new Exception("ForeachCompoundPendingCommand:  FIXME");
            }

            for (int i = Integer.parseInt(rangeTokens[0]);
                                i < Integer.parseInt(rangeTokens[1]); ++i) {
                buffer.append(i).append(" ");
            }
            buffer.append(rangeTokens[1]);

            targets = buffer.toString().split("\\s");
        }

        if (!Level.readLine()[0].equals("do")) {
            throw new Exception("ForeachCompoundPendingCommand:  FIXME");
        }

        String[] command = Level.readLineNoExpand();

        while (command.length == 0) {
            command = Level.readLineNoExpand();
        }

        while (!command[0].equals("done")) {
            if (command[0].equals("foreach")) {
                addPendingCommand(
                                new ForeachCompoundPendingCommand(command));
            } else if (command[0].equals("iferror")) {
                addPendingCommand(new IfErrorCompoundPendingCommand(command));
            } else {
                addPendingCommand(new GeneralPendingCommand(command));
            }

            command = Level.readLineNoExpand();
            while (command.length == 0) {
                command = Level.readLineNoExpand();
            }
        }
    }


    public Level runCommand(Level nextLevel) throws Exception {
        for (int i = 0; i < targets.length; ++i) {
            Variables.set(variable, targets[i]);

            for (PendingCommand j : getPendingCommands()) {
                nextLevel = j.runCommand(nextLevel);
            }
        }
        Variables.unset(variable);

        return nextLevel;
    }
}
