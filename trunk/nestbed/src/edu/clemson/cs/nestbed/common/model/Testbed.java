/* $Id$ */
/*
 * Testbed.java
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
import java.util.List;


public class Testbed implements Serializable {
    private int    id;
    private String name;
    private String description;
    private byte[] image;
    private Date   timestamp;


    public Testbed(int    id,    String name,     String description,
                   byte[] image, Date   timestamp) {
        this.id                     = id;
        this.name                   = name;
        this.description            = description;
        this.image                  = image;
        this.timestamp              = timestamp;
    }
    
    public int getID() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public byte[] getImage() {
        return image;
    }


    public Date getTimestamp() {
        return timestamp;
    }


    public String getInformation() {
        StringBuffer buf = new StringBuffer();

        buf.append("Testbed:\n");
        buf.append("-------------------------------------------------------\n");
        buf.append("id:           ").append(id         ).append("\n");
        buf.append("name:         ").append(name       ).append("\n");
        buf.append("description:  ").append(description).append("\n");
        buf.append("image:        ").append(image      ).append("\n");
        buf.append("timestamp:    ").append(timestamp  ).append("\n");

        return buf.toString();
    }


    public boolean equals(Testbed testbed) {
        return    (id == testbed.id)
               && (name.equals(testbed.name))
               && (description.equals(testbed.description))
               && (timestamp.equals(testbed.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof Testbed) {
            equal = equals((Testbed) o);
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
