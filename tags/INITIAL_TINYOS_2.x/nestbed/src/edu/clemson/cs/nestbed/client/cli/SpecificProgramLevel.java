/* $Id$ */
/*
 * SpecificProgramLevel.java
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


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class SpecificProgramLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;
    private Program                        program;
    private ProgramManager                 programManager;
    private List<Program>                  programs;


    public SpecificProgramLevel(
                        Testbed                        testbed, Project project,
                        ProjectDeploymentConfiguration config,  Program program,
                        Level                          parent)
                                                            throws Exception {
        super(program.getName(), parent);

        this.testbed  = testbed;
        this.project  = project;
        this.config   = config;
        this.program  = program;


        addEntry(new SpecificProgramDetailFileEntry());
        addEntry(new MessagesLevelEntry());
        addEntry(new SymbolsLevelEntry());

        //addCommand("cat", new CatCommand());
    }


    private class SpecificProgramDetailFileEntry extends FileEntry {
        public SpecificProgramDetailFileEntry() {
            super("details");
        }


        @Override
        public String getFileContents() throws Exception {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Name:         ").append(program.getName());
            buffer.append("\n");
            buffer.append("Description:  ").append(program.getDescription());
            buffer.append("\n");

            return buffer.toString();
        }
    }


    private class MessagesLevelEntry extends LevelEntry {
        public MessagesLevelEntry() {
            super("Messages");
        }


        public Level getLevel() throws Exception {
            return new ProgramMessageLevel(testbed, project, config, program,
                                           SpecificProgramLevel.this);
        }
    }


    private class SymbolsLevelEntry extends LevelEntry {
        public SymbolsLevelEntry() {
            super("Symbols");
        }


        public Level getLevel() throws Exception {
            return new ProgramSymbolLevel(testbed, project, config, program,
                                          SpecificProgramLevel.this);
        }
    }
}
