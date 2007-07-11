/* $Id$ */
/*
 * AdapterFactory.java
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
package edu.clemson.cs.nestbed.server.adaptation;


import edu.clemson.cs.nestbed.server.adaptation.*;
import edu.clemson.cs.nestbed.server.adaptation.sql.*;


public class AdapterFactory {
    public static MoteAdapter createMoteAdapter(AdapterType type) {
        MoteAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new MoteSqlAdapter();
            break;
        }

        return adapter;
    }


    public static MoteDeploymentConfigurationAdapter
                    createMoteDeploymentConfigurationAdapter(AdapterType type) {
        MoteDeploymentConfigurationAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new MoteDeploymentConfigurationSqlAdapter();
            break;
        }

        return adapter;
    }


    public static MoteTestbedAssignmentAdapter
                    createMoteTestbedAssignmentAdapter(AdapterType type) {
        MoteTestbedAssignmentAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new MoteTestbedAssignmentSqlAdapter();
            break;
        }

        return adapter;
    }


    public static MoteTypeAdapter createMoteTypeAdapter(AdapterType type) {
        MoteTypeAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new MoteTypeSqlAdapter();
            break;
        }

        return adapter;
    }


    public static ProgramAdapter createProgramAdapter(AdapterType type) {
        ProgramAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new ProgramSqlAdapter();
            break;
        }

        return adapter;
    }


    public static ProgramProfilingSymbolAdapter
                    createProgramProfilingSymbolAdapter(AdapterType type) {
        ProgramProfilingSymbolAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new ProgramProfilingSymbolSqlAdapter();
            break;
        }

        return adapter;
    }


    public static ProgramSymbolAdapter
                    createProgramSymbolAdapter(AdapterType type) {
        ProgramSymbolAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new ProgramSymbolSqlAdapter();
            break;
        }

        return adapter;
    }


    public static ProjectAdapter createProjectAdapter(AdapterType type) {
        ProjectAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new ProjectSqlAdapter();
            break;
        }

        return adapter;
    }


    public static ProjectDeploymentConfigurationAdapter
                    createProjectDeploymentConfigurationAdapter(
                                                            AdapterType type) {
        ProjectDeploymentConfigurationAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new ProjectDeploymentConfigurationSqlAdapter();
            break;
        }

        return adapter;
    }


    public static TestbedAdapter createTestbedAdapter(AdapterType type) {
        TestbedAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new TestbedSqlAdapter();
            break;
        }

        return adapter;
    }


    public static ProgramMessageSymbolAdapter
                           createProgramMessageSymbolAdapter(AdapterType type) {
        ProgramMessageSymbolAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new ProgramMessageSymbolSqlAdapter();
            break;
        }

        return adapter;
    }


    public static ProgramProfilingMessageSymbolAdapter
                   createProgramProfilingMessageSymbolAdapter(AdapterType type) {
        ProgramProfilingMessageSymbolAdapter adapter = null;

        switch (type) {
        case SQL:
            adapter = new ProgramProfilingMessageSymbolSqlAdapter();
            break;
        }

        return adapter;
    }
}
