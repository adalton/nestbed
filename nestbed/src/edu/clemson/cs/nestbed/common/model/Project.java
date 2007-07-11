/* $Id$ */
/*
 * Project.java
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
import java.util.List;


public class Project implements Serializable {
    private int    id;
    private int    testbedID;
    private String name;
    private String description;
    private Date   timestamp;


    public Project(int    id,          int  testbedID, String name,
                   String description, Date timestamp) {
        this.id          = id;
        this.testbedID   = testbedID;
        this.name        = name;
        this.description = description;
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


	public int getTestbedID() {
		return testbedID;
	}


	public Date getTimestamp() {
		return timestamp;
	}


    public String getInformation() {
        StringBuffer buf = new StringBuffer();

        buf.append("Project:\n");
        buf.append("---------------------------------------------\n");
        buf.append("id:           ").append(id         ).append("\n");
        buf.append("testbedID:    ").append(testbedID  ).append("\n");
        buf.append("name:         ").append(name       ).append("\n");
        buf.append("description:  ").append(description).append("\n");
        buf.append("timestamp:    ").append(timestamp  ).append("\n");

        return buf.toString();
    }


    public boolean equals(Project project) {
        return    (id        == project.id)
               && (testbedID == project.testbedID)
               && (name.equals(project.name))
               && (description.equals(project.description))
               && (timestamp.equals(project.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof Project) {
            equal = equals((Project) o);
        }

        return equal;
    }


    public int hashCode() {
        return id;
    }


    public String toString() {
        return name;
    }
}
