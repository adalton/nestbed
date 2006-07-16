/* $Id$ */
/*
 * ProjectDeploymentConfigurationLevel.java
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


import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;


class ProjectDeploymentConfigurationLevel extends Level {
    private Testbed                        testbed;
    private Project                        project;
    private ProjectDeploymentConfiguration config;


    public ProjectDeploymentConfigurationLevel(
                                    Testbed                        testbed,
                                    Project                        project,
                                    ProjectDeploymentConfiguration config,
                                    Level                          parentLevel)
                                                              throws Exception {
        super(config.getName(), parentLevel);

        this.testbed = testbed;
        this.project = project;
        this.config  = config;

        addEntry(new ProgramLevelEntry());
        addEntry(new Entry("SymbolProfiling"));
        addEntry(new Entry("MessageProfiling"));
        addEntry(new MoteConfigLevelEntry());
    }


    private class ProgramLevelEntry extends LevelEntry {
        public ProgramLevelEntry() {
            super("Programs");
        }

        public Level getLevel() throws Exception {
            return new ProgramLevel(testbed, project, config,
                                    ProjectDeploymentConfigurationLevel.this);
        }
    }


    private class MoteConfigLevelEntry extends LevelEntry {
        private MoteConfigLevelEntry() {
            super("Motes");
        }


        public Level getLevel() throws Exception {
            return new MoteConfigLevel(testbed, project, config,
                                      ProjectDeploymentConfigurationLevel.this);
        }
    }
}
