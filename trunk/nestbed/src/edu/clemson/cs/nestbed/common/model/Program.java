/* $Id$ */
/*
 * Program.java
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
package edu.clemson.cs.nestbed.common.model;


import java.io.Serializable;
import java.util.Date;


public class Program implements Serializable, Comparable {
    private int    id;
    private int    projectID;
    private String name;
    private String description;
    private String sourcePath;
    private Date   timestamp;


    public Program(int     id,          int    projectID,  String name,
                    String description, String sourcePath, Date   timestamp) {

        this.id          = id;
        this.projectID   = projectID;
        this.name        = name;
        this.description = description;
        this.sourcePath  = sourcePath;
        this.timestamp   = timestamp;
    }


	public String getDescription() {
		return description;
	}


	public int getID() {
		return id;
	}


	public String getName() {
		return name;
	}


	public int getProjectID() {
		return projectID;
	}


	public String getSourcePath() {
		return sourcePath;
	}


	public Date getTimestamp() {
		return timestamp;
	}


    public String getInformation() {
        StringBuffer buf = new StringBuffer();

        buf.append("Program:\n");
        buf.append("---------------------------------------------\n");
        buf.append("id:           ").append(id         ).append("\n");
        buf.append("projectID:    ").append(projectID  ).append("\n");
        buf.append("name:         ").append(name       ).append("\n");
        buf.append("description:  ").append(description).append("\n");
        buf.append("sourcePath:   ").append(sourcePath ).append("\n");
        buf.append("timestamp:    ").append(timestamp  ).append("\n");

        return buf.toString();
    }


    public boolean equals(Program program) {
        return    (id        == program.id)
               && (projectID == program.projectID)
               && (name.equals(program.name))
               && (description.equals(program.description))
               && (sourcePath.equals(program.sourcePath))
               && (timestamp.equals(program.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof Program) {
            equal = equals((Program) o);
        }

        return equal;
    }


    public int hashCode() {
        return id;
    }


    public String toString() {
        return name;
    }


    public int compareTo(Object obj) {
        Program program = (Program) obj;

        return name.compareTo(program.name);
    }
}
