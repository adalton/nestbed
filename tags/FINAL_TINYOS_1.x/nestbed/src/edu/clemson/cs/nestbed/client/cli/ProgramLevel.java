/* $Id$ */
/*
 * ProgramLevel.java
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * Department of Computer Science
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


import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramCompileManager;
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramWeaverManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;
import edu.clemson.cs.nestbed.common.util.ZipUtils;



class ProgramLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;
    private ProgramManager                 programManager;
    private List<Program>                  programs;
    private ProgramCompileManager          programCompileManager;
    private ProgramWeaverManager           programWeaverManager;
    private Object                         compileMonitor = new Object();
    private boolean                        compiling;



    public ProgramLevel(Testbed                        testbed, Project project,
                        ProjectDeploymentConfiguration config,  Level   parent)
                                                            throws Exception {
        super("Programs", parent);
        lookupRemoteManagers();

        this.testbed  = testbed;
        this.project  = project;
        this.config   = config;
        this.programs = programManager.getProgramList(project.getID());

        programManager.addRemoteObserver(new ProgramManagerObserver());
        programCompileManager.addRemoteObserver(
                                           new ProgramCompileManagerObserver());

        for (Program i : programs) {
            addEntry(new ProgramLevelEntry(i));
        }

        addCommand("rm",     new RmCommand());
        addCommand("upload", new UploadCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {

        programManager        = (ProgramManager)
                                     Naming.lookup(RMI_BASE_URL +
                                                        "ProgramManager");

        programCompileManager = (ProgramCompileManager)
                                     Naming.lookup(RMI_BASE_URL +
                                                       "ProgramCompileManager");

        programWeaverManager  = (ProgramWeaverManager)
                                     Naming.lookup(RMI_BASE_URL +
                                                       "ProgramWeaverManager");
    }


    private class ProgramLevelEntry extends LevelEntry {
        private Program program;

        public ProgramLevelEntry(Program program) {
            super(program.getName());
            this.program = program;
        }


        public Level getLevel() throws Exception {
            return new SpecificProgramLevel(testbed, project, config,
                                            program, ProgramLevel.this);
        }


        public Program getProgram() {
            return program;
        }
    }


    private class RmCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = ProgramLevel.this;

            Variables.set("status", "0");

            if (args.length != 2) {
                System.err.printf("usage:  %s <name>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String name  = args[1];
            Entry  entry = getEntryWithName(name);

            if (entry instanceof ProgramLevelEntry) {
                ProgramLevelEntry plEntry = (ProgramLevelEntry) entry;
                Program           prog    = plEntry.getProgram();

                programManager.deleteProgram(prog.getID());
            } else {
                System.err.println(name + " is not a Program");
                Variables.set("status", "2");
            }

            return nextLevel;
        }

        public String getHelpText() {
            return "Removes a program";
        }
    }


    private class UploadCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = ProgramLevel.this;

            Variables.set("status", "0");

            if (args.length != 4) {
                System.err.printf("usage:  %s <name> <description> " +
                                  "<directory>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String name        = args[1];
            String description = args[2];
            File   directory   = new File(args[3]);

            if (directory.exists()) {
                // TODO:  The moteType of "telosb" should not be hard coded
                byte[] zipData   = ZipUtils.zipDirectory(directory);
                int    programID = programManager.createNewProgram(
                                                            testbed.getID(),
                                                            project.getID(),
                                                            name, description,
                                                            zipData, "telosb");
                programWeaverManager.weaveInComponents(programID,
                                                new HashMap<String, String>());
                compiling = true;
                programCompileManager.compileProgram(programID, "telosb");
                synchronized(compileMonitor) {
                    compileMonitor.wait();
                }
            } else {
                System.out.println("Directory " + directory +
                                   " does not exist.");
                Variables.set("status", "2");
            }
            return nextLevel;
        }

        public String getHelpText() {
            return "Uploads a program";
        }
    }


    private class ProgramManagerObserver extends    UnicastRemoteObject
                                         implements RemoteObserver {
        public ProgramManagerObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                        throws RemoteException {
            ProgramManager.Message message;
            Program                prog;

            message = (ProgramManager.Message) msg;
            prog    = (Program)                arg;

            switch (message) {
            case NEW_PROGRAM:
                programs.add(prog);
                addEntry(new ProgramLevelEntry(prog));
                break;

            case DELETE_PROGRAM:
                Entry entry = getEntryWithName(prog.getName());

                if (entry instanceof ProgramLevelEntry) {
                    ProgramLevelEntry plEntry;
                    plEntry = (ProgramLevelEntry) entry;

                    removeEntry(plEntry);
                    // TODO:  plEntry.destroy();
                    programs.remove(prog);
                }
                break;

            default:
                System.out.println("Unknown message:  " + message);
                break;
            }

        }
    }


    private class ProgramCompileManagerObserver extends    UnicastRemoteObject
                                                implements RemoteObserver {
        public ProgramCompileManagerObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                        throws RemoteException {
            ProgramCompileManager.Message message;
            message = (ProgramCompileManager.Message) msg;

            switch (message) {
            case COMPILE_STARTED:
                // Do nothing
                break;

            case COMPILE_PROGRESS:
                if (compiling) {
                    System.out.println(arg);
                }
                break;

            case COMPILE_COMPLETED:
                if (compiling) {
                    compiling = false;
                    synchronized(compileMonitor) {
                        compileMonitor.notifyAll();
                    }
                }
                break;

            default:
                System.err.println("Unknown message:  " + msg);
                break;
            }
        }
    }
}
