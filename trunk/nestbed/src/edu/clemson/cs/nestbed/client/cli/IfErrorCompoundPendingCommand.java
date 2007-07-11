/* $Id$ */
/*
 * IfErrorCompoundPendingCommand.java
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


import java.util.ArrayList;
import java.util.List;


public class IfErrorCompoundPendingCommand extends CompoundPendingCommand {
    private List<PendingCommand> elseCommands =
                                        new ArrayList<PendingCommand>();

    public IfErrorCompoundPendingCommand(String[] args) throws Exception {
        if (args.length != 1) {
            throw new Exception("IfErrorCompoundPendingCommand:  FIXME");
        }

        if (!Level.readLine()[0].equals("then")) {
            throw new Exception("IfErrorCompoundPendingCommand:  FIXME");
        }

        String[] command   = Level.readLineNoExpand();
        boolean  doingIf   = true;
        boolean  doingElse = false;

        // This is such a hack...
        while (doingIf || doingElse) {
            if (command[0].equals("endif")) {
                doingIf   = false;
                doingElse = false;
                break;
            } else if (doingIf) {
                if (command[0].equals("else")) {
                    doingIf   = false;
                    doingElse = true;
                } else if (command[0].equals("foreach")) {
                    addPendingCommand(
                                    new ForeachCompoundPendingCommand(command));
                } else if (command[0].equals("iferror")) {
                    addPendingCommand(new IfErrorCompoundPendingCommand(command));
                } else {
                    addPendingCommand(new GeneralPendingCommand(command));
                }
            } else if (doingElse) {
                if (command[0].equals("foreach")) {
                    elseCommands.add(new ForeachCompoundPendingCommand(command));
                } else if (command[0].equals("iferror")) {
                    elseCommands.add(new IfErrorCompoundPendingCommand(command));
                } else {
                    elseCommands.add(new GeneralPendingCommand(command));
                }
            }

            command = Level.readLineNoExpand();
        }
    }


    public Level runCommand(Level nextLevel) throws Exception {
        String status = Variables.get("status");

        if (!status.equals("0")) {
            for (PendingCommand j : getPendingCommands()) {
                nextLevel = j.runCommand(nextLevel);
            }
        } else {
            for (PendingCommand j : elseCommands) {
                nextLevel = j.runCommand(nextLevel);
            }
        }

        return nextLevel;
    }
}
