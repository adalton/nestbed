/* $Id$ */
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


abstract class Level {
    protected final static String RMI_BASE_URL;

    static {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");
    }


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
        addCommand("cd",    new CdCommand());
        addCommand("ls",    new LsCommand());
        addCommand("man",   new ManCommand());
        addCommand("help",  new ManCommand());
        addCommand("quit",  new QuitCommand());
        addCommand("exit",  new QuitCommand());
        addCommand("alias", new AliasCommand());
        addCommand("pwd",   new PwdCommand());

        // All levels have these LevelEntries
        addEntry(new Entry("."));
        addEntry(new Entry(".."));
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
        TokenReader reader = new TokenReader(in.readLine());
        return reader.readTokens();
    }


    protected void addEntry(Entry entry) {
        entries.add(entry);
    }


    protected void addCommand(String name, Command command) {
        commandMap.put(name, command);
    }


    protected class CdCommand implements Command {
        public void execute(String[] args) throws Exception {
            if (args.length != 2) {
                System.err.println("usage:  cd <level>");
                return;
            }

            if (args[1].equals(".")) {
                return;
            }

            if (args[1].equals("..")) {
                setNextLevel(getParentLevel());
                return;
            }

            boolean found = false;

            for (Entry i : getEntries()) {
                if (i instanceof LevelEntry) {
                    LevelEntry levelEntry = (LevelEntry) i;

                    if (levelEntry.getName().equals(args[1])) {
                        setNextLevel(levelEntry.getLevel());
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


    protected class LsCommand implements Command {
        public void execute(String[] args) throws Exception {
            for (Entry i : getEntries()) {
                System.out.println(i);
            }
        }


        public String getHelpText() {
            return "Lists the available levels";
        }
    }


    protected class ManCommand implements Command {
        public void execute(String[] args) throws Exception {
            int maxLength = 0;

            List<String> commandNames;
            commandNames = new ArrayList<String>(getCommandNames());
            Collections.sort(commandNames);

            for (String i : commandNames) {
                maxLength = (i.length() > maxLength) ? i.length() : maxLength;
            }

            if (maxLength > 0) {
                String formatString = "  %-" + maxLength + "s - %s\n";

                for (String i : commandNames) {
                    Command command = getCommand(i);
                    System.out.printf(formatString, i, command.getHelpText());
                }
            }
        }


        public String getHelpText() {
            return "Get help with commands";
        }
    }


    protected class QuitCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.exit(0);
        }


        public String getHelpText() {
            return "Quit this applicaton";
        }
    }


    protected class AliasCommand implements Command {
        public void execute(String[] args) throws Exception {
            if (args.length != 3) {
                System.err.println("Usage:  alias <oldCommand> <newCommand>");
                return;
            }

            Command command = getCommand(args[1]);
            if (command == null) {
                System.err.println("Command " + args[1] + " not found");
                return;
            }

            Command newCommand = getCommand(args[2]);
            if (newCommand != null) {
                System.err.println("Command " + args[2] + " already defined");
                return;
            }

            addCommand(args[2], new AliasWrapper(args[1], command));
        }


        public String getHelpText() {
            return "Create an alias for a command";
        }
    }


    protected class AliasWrapper implements Command {
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


    protected class PwdCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.out.println(getPrompt());
        }

        public String getHelpText() {
            return "Prints the current working directory";
        }
    }
}

