/*
 * Mote.java
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


public class Mote implements Serializable {
    private int      id;
    private String   moteSerialID;
    private int      moteTypeID;
    private Date     timestamp;


    public Mote(int id,         String moteSerialID,
                int moteTypeID, Date   timestamp) {
        this.id           = id;
        this.moteSerialID = moteSerialID;
        this.moteTypeID   = moteTypeID;
        this.timestamp    = timestamp;
    }


	public int getID() {
		return id;
	}


	public String getMoteSerialID() {
		return moteSerialID;
	}


	public int getMoteTypeID() {
		return moteTypeID;
	}


	public Date getTimestamp() {
		return timestamp;
	}


    public boolean equals(Mote mote) {
        return    (id         == mote.id)
               && (moteTypeID == mote.moteTypeID)
               && (moteSerialID.equals(mote.moteSerialID))
               && (timestamp.equals(mote.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof Mote) {
            equal = equals((Mote) o);
        }
        return equal;
    }


    public int hashCode() {
        return id;
    }


    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("Mote:\n");
        buf.append("-----------------------------------------------\n");
        buf.append("id:            ").append(id          ).append("\n");
        buf.append("moteSerialID:  ").append(moteSerialID).append("\n");
        buf.append("moteTypeID:    ").append(moteTypeID  ).append("\n");
        buf.append("timestamp:     ").append(timestamp   ).append("\n");

        return buf.toString();
    }
}
