/* $Id$ */
/*
 * ProgramSymbol.java
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


public class ProgramSymbol implements Serializable, Comparable {
    private int    id;
    private int    programID;
    private String module;
    private String symbol;
    private int    address;
    private short  size;
    private Date   timestamp;


    public ProgramSymbol(int    id,     int programID, String module,
                         String symbol, int address,   short  size,
                         Date   timestamp) {
        this.id        = id;
        this.programID = programID;
        this.module    = module;
        this.symbol    = symbol;
        this.address   = address;
        this.size      = size;
        this.timestamp = timestamp;
    }


    public int getID() {
		return id;
	}


	public int getProgramID() {
		return programID;
	}


    public String getModule() {
        return module;
    }


	public String getSymbol() {
		return symbol;
	}


    public int getAddress() {
        return address;
    }


    public short getSize() {
        return size;
    }


	public Date getTimestamp() {
		return timestamp;
	}


    public boolean equals(ProgramSymbol ps) {
        return    (id        == ps.id)
               && (programID == ps.programID)
               && (module.equals(ps.module))
               && (symbol.equals(ps.symbol))
               && (timestamp.equals(ps.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof ProgramSymbol) {
            equal = equals((ProgramSymbol) o);
        }

        return equal;
    }


    public int hashCode() {
        return id;
    }


    public String toString() {
        return  symbol;
    }


    public int compareTo(Object obj) {
        ProgramSymbol ps = (ProgramSymbol) obj;
        return (module + symbol).compareTo(ps.module + ps.symbol);
    }
}
