/* $Id:$ */
/*
 * Level.java
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


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.Set;

abstract class Level {
    private Level                parentLevel;
    private Level                nextLevel;
    private String               levelName;
    private List<Entry>          entries    = new ArrayList<Entry>();
    private Map<String, Command> commandMap = new HashMap<String, Command>();
    private BufferedReader       in;


    public Level(String levelName, Level parentLevel) throws Exception {
        this.levelName   = levelName;
        this.parentLevel = parentLevel;
        this.in          = new BufferedReader(
                               new InputStreamReader(
                                   System.in));

        // All levels have these commands
        addCommand("cd",    new CdCommand(this));
        addCommand("ls",    new LsCommand(this));
        addCommand("man",   new ManCommand(this));
        addCommand("help",  new ManCommand(this));
        addCommand("quit",  new QuitCommand(this));
        addCommand("alias", new AliasCommand(this));

        // All levels have these LevelEntries
        addEntry(new LevelEntry("."));
        addEntry(new LevelEntry(".."));
    }

    public Level getParentLevel() {
        return (parentLevel != null) ? parentLevel : this;
    }

    public Level process() throws Exception {
        nextLevel = this;
        displayPrompt();

        String[] args = readCommand();

        if (args.length > 0 && !args[0].trim().equals("")) {
            Command  command = commandMap.get(args[0]);

            if (command != null) {
                command.execute(args);
            } else {
                System.err.println("NESTshell: " + args[0] +
                                   " command not found");
            }
        }

        // NOTE:  The command may have changed nextLevel since the time
        //        it was initialized at the beginning of this method.
        return nextLevel;
    }


    public List<Entry> getEntries() {
        return entries;
    }


    public void setNextLevel(Level nextLevel) {
        this.nextLevel = nextLevel;
    }


    public Set<String> getCommandNames() {
        return commandMap.keySet();
    }


    public Command getCommand(String name) {
        return commandMap.get(name);
    }


    public abstract Level getLevel(String name) throws Exception;

    protected String getPrompt() {
        String prompt = "";
        if (parentLevel != null) {
            String parentPrompt = parentLevel.getPrompt();

            if (!parentPrompt.equals("/")) {
                prompt = parentPrompt + "/";
            } else {
                prompt = parentPrompt;
            }
        }
        prompt += levelName;
        return prompt;
    }

    protected void displayPrompt() {
        System.out.print(getPrompt() + " $ ");
        System.out.flush();
    }

    protected String[] readCommand() throws Exception {
        return in.readLine().split("\\s");
    }

    protected void addEntry(Entry entry) {
        entries.add(entry);
    }

    protected void addCommand(String name, Command command) {
        commandMap.put(name, command);
    }
}
