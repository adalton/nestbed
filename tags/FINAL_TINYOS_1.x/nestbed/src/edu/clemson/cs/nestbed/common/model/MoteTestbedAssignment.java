/* $Id$ */
/*
 * MoteTestbedAssignment.java
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


public class MoteTestbedAssignment implements Serializable {
	private int  id;
    private int  testbedID;
    private int  moteID;
    private int  moteAddress;
    private int  moteLocationX;
    private int  moteLocationY;
    private Date timestamp;


    public MoteTestbedAssignment(int  id,            int  testbedID,
                                 int  moteID,        int  moteAddress,
                                 int  moteLocationX, int  moteLocationY,
                                 Date timestamp) {
        this.id            = id;
        this.testbedID     = testbedID;
        this.moteID        = moteID;
        this.moteAddress   = moteAddress;
        this.moteLocationX = moteLocationX;
        this.moteLocationY = moteLocationY;
        this.timestamp     = timestamp;
    }


	public int getID() {
		return id;
	}


	public int getMoteAddress() {
		return moteAddress;
	}


	public int getMoteID() {
		return moteID;
	}


	public int getMoteLocationX() {
		return moteLocationX;
	}


	public int getMoteLocationY() {
		return moteLocationY;
	}


	public int getTestbedID() {
		return testbedID;
	}


	public Date getTimestamp() {
		return timestamp;
	}


    public boolean equals(MoteTestbedAssignment mta) {
        return    (id            == mta.id)
               && (testbedID     == mta.testbedID)
               && (moteID        == mta.moteID)
               && (moteAddress   == mta.moteAddress)
               && (moteLocationX == mta.moteLocationX)
               && (moteLocationY == mta.moteLocationY)
               && (timestamp.equals(mta.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof MoteTestbedAssignment) {
            equal = equals((MoteTestbedAssignment) o);
        }

        return equal;
    }


    public int hashCode() {
        return id;
    }


    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("MoteTestbedAssignment:\n");
        buf.append("---------------------------\n");
        buf.append("id:             ").append(id           ).append("\n");
        buf.append("testbedID:      ").append(testbedID    ).append("\n");
        buf.append("moteID:         ").append(moteID       ).append("\n");
        buf.append("moteAddress:    ").append(moteAddress  ).append("\n");
        buf.append("moteLocationX:  ").append(moteLocationX).append("\n");
        buf.append("moteLocationY:  ").append(moteLocationY).append("\n");
        buf.append("timestamp:      ").append(timestamp    ).append("\n");

        return buf.toString();
    }
}
