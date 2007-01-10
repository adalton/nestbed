/* $Id$ */
/*
 * ProgramProfilingMessageSymbol.java
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
package edu.clemson.cs.nestbed.common.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ProgramProfilingMessageSymbol implements Serializable {
    private int    id;
    private int    programMessageSymbolID;
    private int    projectDeploymentConfigurationID;
    private Date   timestamp;


    public ProgramProfilingMessageSymbol(int  id,
                                         int  pdcID,
                                         int  programMessageSymbolID,
                                         Date timestamp) {

        this.id                               = id;
        this.programMessageSymbolID           = programMessageSymbolID;
        this.projectDeploymentConfigurationID = pdcID;
        this.timestamp                        = timestamp;
    }
    
    public int getID() {
        return id;
    }


    public int getProgramMessageSymbolID() {
        return programMessageSymbolID;
    }


    public int getProjectDeploymentConfigurationID() {
        return projectDeploymentConfigurationID;
    }


    public Date getTimestamp() {
        return timestamp;
    }


    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("id:                               ");
        buffer.append(id).append("\n");
        buffer.append("programMessageSymbolID:           ");
        buffer.append(programMessageSymbolID).append("\n");
        buffer.append("projectDeploymentConfigurationID: ");
        buffer.append(projectDeploymentConfigurationID).append("\n");
        buffer.append("timestamp:                        ");
        buffer.append(timestamp).append("\n");

        return buffer.toString();
    }


    public boolean equals(ProgramProfilingMessageSymbol pmt) {
        return    (id                      == pmt.id)
               && (programMessageSymbolID  == pmt.programMessageSymbolID)
               && (projectDeploymentConfigurationID ==
                                        pmt.projectDeploymentConfigurationID)
               && (timestamp.equals(pmt.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof ProgramProfilingMessageSymbol) {
            equal = equals((ProgramProfilingMessageSymbol) o);
        }

        return equal;
    }


    public int hashCode() {
        return id;
    }
}
