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
        addCommand("cd",    new CdCommand(this));
        addCommand("ls",    new LsCommand(this));
        addCommand("man",   new ManCommand(this));
        addCommand("help",  new ManCommand(this));
        addCommand("quit",  new QuitCommand(this));
        addCommand("exit",  new QuitCommand(this));
        addCommand("alias", new AliasCommand(this));
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

    private static class TokenReader {
        private String line;
        private int    index = 0;
        private State  state;


        public TokenReader(String line) {
            this.line  = line;
            this.state = State.WHITE;
        }


        public String[] readTokens() {
            List<String> tokens = new ArrayList<String>();
            while (index < line.length()) {
                tokens.add(nextToken());
            }

            if (state == State.QUOTE) {
                System.err.println("Error: Unterminated string constant");
                tokens.clear();
            }
            return tokens.toArray(new String[tokens.size()]);
        }

        private String nextToken() {
            StringBuffer buffer = new StringBuffer();
            boolean      done   = false;

            while (index < line.length() && !done) {
                switch(state) {
                case WHITE:
                    if (Character.isWhitespace(line.charAt(index))) {
                        index++;
                    } else if (line.charAt(index) == '"') {
                        state = State.QUOTE;
                        index++;
                    } else {
                        state = State.BLACK;
                    }
                    break;

                case BLACK:
                    if (Character.isWhitespace(line.charAt(index))) {
                        state = State.WHITE;
                        done  = true;
                    } else {
                        buffer.append(line.charAt(index));
                        index++;
                    }
                    break;

                case QUOTE:
                    if (line.charAt(index) != '"') {
                        buffer.append(line.charAt(index));
                    } else {
                        state = State.WHITE;
                        done  = true;
                    }
                    index++;
                    break;
                }
            }

            return buffer.toString();
        }

        enum State {
            WHITE,
            BLACK,
            QUOTE
        }
    }


    private class PwdCommand implements Command {
        public void execute(String[] args) throws Exception {
            System.out.println(getPrompt());
        }

        public String getHelpText() {
            return "Prints the current working directory";
        }
    }
}

