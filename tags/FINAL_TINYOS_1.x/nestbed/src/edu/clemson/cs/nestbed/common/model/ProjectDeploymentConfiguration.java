/* $Id$ */
/*
 * ProjectDeploymentConfiguration.java
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


public class ProjectDeploymentConfiguration implements Serializable {
    private int    id;
    private int    projectID;
    private String name;
    private String description;
    private Date   timestamp;


    public ProjectDeploymentConfiguration(int id, int projectID,
                                          String name,
                                          String description,
                                          Date timestamp) {
        this.id               = id;
        this.projectID        = projectID;
        this.name             = name;
        this.description      = description;
        this.timestamp        = timestamp;
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


	public Date getTimestamp() {
		return timestamp;
	}


    public String getInformation() {
        StringBuffer buf = new StringBuffer();

        buf.append("ProjectDeploymentConfiguration:\n");
        buf.append("---------------------------------------------\n");
        buf.append("id:           ").append(id         ).append("\n");
        buf.append("projectID:    ").append(projectID  ).append("\n");
        buf.append("name:         ").append(name       ).append("\n");
        buf.append("description:  ").append(description).append("\n");
        buf.append("timestamp:    ").append(timestamp  ).append("\n");

        return buf.toString();
    }


    public boolean equals(ProjectDeploymentConfiguration pdc) {
        return    (id        == pdc.id)
               && (projectID == pdc.projectID)
               && (name.equals(pdc.name))
               && (description.equals(pdc.description))
               && (timestamp.equals(pdc.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof ProjectDeploymentConfiguration) {
            equal = equals((ProjectDeploymentConfiguration) o);
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
