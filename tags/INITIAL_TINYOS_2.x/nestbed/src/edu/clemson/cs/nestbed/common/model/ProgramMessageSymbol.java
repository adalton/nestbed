/* $Id$ */
/*
 * ProgramMessageSymbol.java
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

import edu.clemson.cs.nestbed.common.util.ByteClassLoader;


public class ProgramMessageSymbol implements Serializable, Comparable {
    private int    id;
    private int    programID;
    private String name;
    private byte[] bytecode;
    private Date   timestamp;

    private transient ByteClassLoader classLoader  = null;
    private transient Class           messageClass = null;


    public ProgramMessageSymbol(int    id,        int    programID,
                                String name,      byte[] bytecode,
                                Date   timestamp) {
        this.id         = id;
        this.programID  = programID;
        this.name       = name;
        this.bytecode   = bytecode;
        this.timestamp  = timestamp;
    }
    

    public int getID() {
        return id;
    }


    public int getProgramID() {
        return programID;
    }


    public String getName() {
        return name;
    }


    public Date getTimestamp() {
        return timestamp;
    }


    public Class getMessageClass() {
        if (messageClass == null) {
            getClassLoader();
            messageClass = classLoader.defineNewClass(name, bytecode, 0,
                                                      bytecode.length);
        }
        return messageClass;
    }


    public ClassLoader getClassLoader() {
        if (classLoader == null) {
            classLoader = new ByteClassLoader();
        }

        return classLoader;
    }


    public int compareTo(Object o) {
        ProgramMessageSymbol programMessageSymbol = (ProgramMessageSymbol) o;
        return name.compareTo(programMessageSymbol.name);
    }


    public boolean equals(ProgramMessageSymbol pmt) {
        return    (id        == pmt.id)
               && (programID == pmt.programID)
               && (name.equals(pmt.name))
               && (timestamp.equals(pmt.timestamp));
    }


    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof ProgramMessageSymbol) {
            equal = equals((ProgramMessageSymbol) o);
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
