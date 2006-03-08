/*
 * MoteType.java
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
import java.util.Map;
import java.util.HashMap;


public class MoteType implements Serializable {
    private static Map<Integer, MoteType> moteTypeMap =
                                            new HashMap<Integer, MoteType>();

    private int    id;
    private String name;
    private int    totalROM;
    private int    totalRAM;
    private int    totalEEPROM;
    private byte[] image;
    private String tosPlatform;
    private Date   timestamp;


    public static MoteType getMoteType(int    id,          String name,
                                       int    totalROM,    int    totalRAM,
                                       int    totalEEPROM, byte[] image,
                                       String tosPlatform, Date   timestamp) {

        MoteType moteType = moteTypeMap.get(id);

        if (moteType == null) {
            moteType = new MoteType(id, name, totalROM, totalRAM,
                                    totalEEPROM, image, tosPlatform,
                                    timestamp);

            moteTypeMap.put(id, moteType);
        }

        return moteType;
    }


    private MoteType(int    id,          String name,        int    totalROM,
                     int    totalRAM,    int    totalEEPROM, byte[] image,
                     String tosPlatform, Date   timestamp) {
        this.id          = id;
        this.name        = name;
        this.totalROM    = totalROM;
        this.totalRAM    = totalRAM;
        this.totalEEPROM = totalEEPROM;
        this.image       = image;
        this.tosPlatform = tosPlatform;
        this.timestamp   = timestamp;
    }


	public int getID() {
		return id;
	}


	public String getName() {
		return name;
	}


	public int getTotalROM() {
		return totalROM;
	}


	public int getTotalRAM() {
		return totalRAM;
	}


	public int getTotalEEPROM() {
		return totalEEPROM;
	}


	public byte[] getImage() {
		return image;
	}


	public String getTosPlatform() {
		return tosPlatform;
	}


	public Date getTimestamp() {
		return timestamp;
	}


    public boolean equals(MoteType moteType) {
        // Shouldn't need to check everything since
        // these cannot change.
        return (id == moteType.id);
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof MoteType) {
            equal = equals((MoteType) o);
        }

        return equal;
    }


    public int hashCode() {
        return id;
    }


    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("MoteType:\n");
        buf.append("---------------------------------------------\n");
        buf.append("id:           ").append(id         ).append("\n");
        buf.append("name:         ").append(name       ).append("\n");
        buf.append("totalROM:     ").append(totalROM   ).append("\n");
        buf.append("totalRAM:     ").append(totalRAM   ).append("\n");
        buf.append("totalEEPROM:  ").append(totalEEPROM).append("\n");
        buf.append("image:        ").append(image      ).append("\n");
        buf.append("tosPlatform:  ").append(tosPlatform).append("\n");
        buf.append("timestamp:    ").append(timestamp  ).append("\n");

        return buf.toString();
    }
}
