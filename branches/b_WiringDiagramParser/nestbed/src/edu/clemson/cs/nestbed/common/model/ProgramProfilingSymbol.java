/* $Id$ */
/*
 * ProgramProfilingSymbol.java
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
package edu.clemson.cs.nestbed.common.model;


import java.io.Serializable;
import java.util.Date;


public class ProgramProfilingSymbol implements Serializable {
    private int  id;
    private int  projectDeploymentConfigurationID;
    private int  programSymbolID;
    private Date timestamp;


    public ProgramProfilingSymbol(int  id,              int  projDepConfigID,
                                  int  programSymbolID, Date timestamp) {
        this.id                               = id;
        this.projectDeploymentConfigurationID = projDepConfigID;
        this.programSymbolID                  = programSymbolID;
        this.timestamp                        = timestamp;
    }


    public int getID() {
		return id;
	}


	public int getProgramSymbolID() {
		return programSymbolID;
	}


	public int getProjectDeploymentConfigurationID() {
		return projectDeploymentConfigurationID;
	}


	public Date getTimestamp() {
		return timestamp;
	}


    public boolean equals(ProgramProfilingSymbol pps) {
        return    (id              == pps.id)
               && (programSymbolID == pps.programSymbolID)
               && (projectDeploymentConfigurationID ==
                                        pps.projectDeploymentConfigurationID)
               && (timestamp.equals(pps.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof ProgramProfilingSymbol) {
            equal = equals((ProgramProfilingSymbol) o);
        }

        return equal;
    }


    public int hashCode() {
        return id;
    }


    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("ProgramProfilingSymbol:\n");
        buf.append("-------------------------------------------------\n");

        buf.append("id:                                ");
        buf.append(id).append("\n");

        buf.append("projectDeploymentConfigurationID:  ");
        buf.append(projectDeploymentConfigurationID).append("\n");

        buf.append("programSymbolID:                   ");
        buf.append(programSymbolID).append("\n");

        buf.append("timestamp:                         ");
        buf.append(timestamp).append("\n");

        return buf.toString();
    }
}
