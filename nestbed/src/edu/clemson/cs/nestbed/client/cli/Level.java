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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


abstract class Level {
    protected final static String RMI_BASE_URL;

    static {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");
    }

    protected static BufferedReader in;
    protected static boolean        interactive = true;


    private Level                parentLevel;
    private String               levelName;
    private List<Entry>          entries    = new ArrayList<Entry>();
    private Map<String, Command> commandMap = new HashMap<String, Command>();



    public static void setBufferedReader(BufferedReader reader) {
        in = reader;
    }


    public static void setInteractive(boolean active) {
        interactive = active;
    }


    public Level(String levelName, Level parentLevel) throws Exception {
        this.levelName   = levelName;
        this.parentLevel = parentLevel;

        // All levels have these commands
        addCommand("cd",      new CdCommand());
        addCommand("ls",      new LsCommand());
        addCommand("man",     new ManCommand());
        addCommand("help",    new ManCommand());
        addCommand("quit",    new QuitCommand());
        addCommand("exit",    new QuitCommand());
        addCommand("alias",   new AliasCommand());
        addCommand("pwd",     new PwdCommand());
        addCommand("set",     new SetCommand());
        addCommand("unset",   new UnsetCommand());
        addCommand("echo",    new EchoCommand());
        addCommand("env",     new EnvCommand());
        addCommand("shell",   new ShellCommand());
        addCommand("foreach", new ForeachCommand());
        addCommand("iferror", new IfErrorCommand());

        // All levels have these LevelEntries
        addEntry(new RootLevelEntry());
        addEntry(new DotLevelEntry());
        addEntry(new DotDotLevelEntry());
    }


    public Level getParentLevel() {
        return (parentLevel != null) ? parentLevel : this;
    }


    public Level process() throws Exception {
        if (interactive) {
            displayPrompt();
        }
        return runCommand(readLine());
    }


    public Level runCommand(String[] args) throws Exception {
        Level nextLevel = this;

        if (args.length > 0 && !args[0].trim().equals("")) {
            Command command = commandMap.get(args[0]);

            if (command != null) {
                nextLevel = command.execute(args);
            } else {
                System.err.println("NESTshell: " + args[0] +
                                   " command not found");
            }
        }

        return nextLevel;
    }


    public List<Entry> getEntries() {
        return entries;
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


    public static String[] readLine() throws Exception {
        TokenReader reader = new TokenReader(in.readLine());
        String[]   tokens  =  reader.readTokens();

        for (int i = 0; i < tokens.length; ++i) {
            Variables.set("__internal", tokens[i]);
            tokens[i] = Variables.unset("__internal");
        }

        return tokens;
    }


    public static String[] readLineNoExpand() throws Exception {
        TokenReader reader = new TokenReader(in.readLine());
        String[]   tokens  =  reader.readTokens();

        return tokens;
    }


    protected void addEntry(Entry entry) {
        entries.add(entry);
    }


    protected void removeEntry(Entry entry) {
        entries.remove(entry);
    }


    protected Entry getEntryWithName(String name) {
        for (Entry i : getEntries()) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }


    protected void addCommand(String name, Command command) {
        commandMap.put(name, command);
    }


    protected class RootLevelEntry extends LevelEntry {
        public RootLevelEntry() {
            super("/");
        }


        public Level getLevel() throws Exception {
            Level parentLevel = getParentLevel();

            // TODO:  cleanup along the way...
            for (Level i = Level.this; i != parentLevel;
                                        i = i.getParentLevel(),
                                            parentLevel = i.getParentLevel());

            return parentLevel;
        }
    }


    protected class DotLevelEntry extends LevelEntry {
        public DotLevelEntry() {
            super(".");
        }

        public Level getLevel() throws Exception {
            return Level.this;
        }
    }


    protected class DotDotLevelEntry extends LevelEntry {
        public DotDotLevelEntry() {
            super("..");
        }


        public Level getLevel() throws Exception {
            return getParentLevel();
        }
    }


    protected class CdCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = Level.this;
            Variables.set("status", "0");

            if (args.length != 2) {
                System.err.println("usage:  cd <directory>");
                Variables.set("status", "1");
                return nextLevel;
            }

            String  target = args[1];
            boolean found  = false;

            for (Entry i : getEntries()) {
                if (i instanceof LevelEntry) {
                    LevelEntry levelEntry = (LevelEntry) i;

                    if (levelEntry.getName().equals(target)) {
                        nextLevel = levelEntry.getLevel();
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.printf("NESTshell:  %s:  %s:  No such file " +
                                  "or directory\n", args[0], target);
                Variables.set("status", "2");
            }

            return nextLevel;
        }

        public String getHelpText() {
            return "Change the working directory";
        }
    }


    protected class LsCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Variables.set("status", "0");

            for (Entry i : getEntries()) {
                // Filter out the / entry
                if (!i.getName().equals("/")) {
                    System.out.println(i);
                }
            }

            return Level.this;
        }


        public String getHelpText() {
            return "Lists directory contents";
        }
    }


    protected class ManCommand implements Command {
        public Level execute(String[] args) throws Exception {
            int maxLength = 0;

            Variables.set("status", "0");

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

            return Level.this;
        }


        public String getHelpText() {
            return "Get help with commands";
        }
    }


    protected class QuitCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Variables.set("status", "0");
            System.exit(0);
            return null; // Should never get here
        }


        public String getHelpText() {
            return "Quit this applicaton";
        }
    }


    protected class AliasCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = Level.this;

            Variables.set("status", "0");

            if (args.length != 3) {
                System.err.println("Usage:  alias <oldCommand> <newCommand>");
                Variables.set("status", "1");
                return nextLevel;
            }

            Command command = getCommand(args[1]);
            if (command == null) {
                System.err.println("Command " + args[1] + " not found");
                Variables.set("status", "2");
                return nextLevel;
            }

            Command newCommand = getCommand(args[2]);
            if (newCommand != null) {
                System.err.println("Command " + args[2] + " already defined");
                Variables.set("status", "3");
                return nextLevel;
            }

            addCommand(args[2], new AliasWrapper(args[1], command));
            return nextLevel;
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

        public Level execute(String[] args) throws Exception {
            return command.execute(args);
        }


        public String getHelpText() {
            return command.getHelpText() + " (Alias for " + name + ")";
        }
    }


    protected class PwdCommand implements Command {
        public Level execute(String[] args) throws Exception {
            System.out.println(getPrompt());
            Variables.set("status", "0");
            return Level.this;
        }


        public String getHelpText() {
            return "Prints the current working directory";
        }
    }


    protected class SetCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = Level.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.out.printf("usage:  %s <varName>=<value>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String exp = args[1];
            String[] tokens = exp.split("=");

            if (tokens.length != 2) {
                System.err.println("Malformed expression:  " + exp);
                Variables.set("status", "2");
                return nextLevel;
            }

            String variable = tokens[0];
            String value    = tokens[1];

            Variables.set(variable, value);

            return  nextLevel;
        }


        public String getHelpText() {
            return "Sets the value of the specified variable";
        }
    }


    protected class UnsetCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = Level.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.out.printf("usage:  %s <varName>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String varName = args[1];
            Variables.unset(varName);

            return nextLevel;
        }


        public String getHelpText() {
            return "Unsets the value of the specified variable";
        }
    }
    
    
    protected class EchoCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = Level.this;

            Variables.set("status", "0");

            for (int i = 1; i < args.length; ++i) {
                System.out.printf("%s ", args[i]);
            }
            System.out.println();

            return nextLevel;
        }


        public String getHelpText() {
            return "Prints the specified string, expanding variables";
        }
    }


    protected class EnvCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Variables.set("status", "0");

            for (Iterator<String> i = Variables.nameIterator(); i.hasNext(); ) {
                String varName = i.next();
                String value   = Variables.get(varName);
                System.out.printf("%s=%s\n", varName, value);
            }
            return Level.this;
        }


        public String getHelpText() {
            return "Prints the current environment variables";
        }
    }


    protected class ShellCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Variables.set("status", "0");

            Level nextLevel = Level.this;

            if (args.length < 2) {
                System.err.printf("usage:  %s <command> [<arg>, ...]");
                Variables.set("status", "1");
                return nextLevel;
            }

            List<String>   cmd = new ArrayList<String>();
            for (int i = 1; i < args.length; ++i) {
                cmd.add(args[i]);
            }

            ProcessBuilder builder = new ProcessBuilder(cmd);
            builder.redirectErrorStream(true);

            Process process = builder.start();
            printProcessOutput(process);
            process.waitFor();

            Variables.set("status", Integer.toString(process.exitValue()));

            return nextLevel;
        }


        public String getHelpText() {
            return "Executes the specified external command " +
                   "given the specified arguments";
        }


        private void printProcessOutput(Process process) throws IOException {
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                        process.getInputStream()));

            String input;

            while ( (input = in.readLine()) != null ) {
                System.out.println(input);
            }
            in.close();
        }
    }


    protected class ForeachCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Variables.set("status", "0");

            PendingCommand command = new ForeachCompoundPendingCommand(args);
            return command.runCommand(Level.this);
        }


        public String getHelpText() {
            return "Loop over the specified list, executing " +
                   "the specified commands";
        }
    }


    protected class IfErrorCommand implements Command {
        public Level execute(String[] args) throws Exception {
            PendingCommand command = new IfErrorCompoundPendingCommand(args);
            return command.runCommand(Level.this);
        }


        public String getHelpText() {
            return "If the error status flag is set, " +
                   "execute the specified commands";
        }
    }


    protected class CatCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = Level.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.err.println("usage:  cat <file>");
                Variables.set("status", "1");
                return nextLevel;
            }

            for (Entry i : getEntries()) {
                if (i.getName().equals(args[1]) && (i instanceof FileEntry)) {
                    FileEntry fileEntry = (FileEntry) i;
                    System.out.println(fileEntry.getFileContents());
                    break;
                }
            }
            return nextLevel;
        }


        public String getHelpText() {
            return "Displays the contents of a file";
        }
    }
}

