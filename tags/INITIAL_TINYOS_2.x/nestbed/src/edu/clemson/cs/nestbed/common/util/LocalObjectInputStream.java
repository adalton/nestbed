/* $Id$ */
/*
 * LocalObjectInputStream.java
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
package edu.clemson.cs.nestbed.common.util;


import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;


public class LocalObjectInputStream extends ObjectInputStream {
    private Class c;


    public LocalObjectInputStream(InputStream in, Class c) throws IOException {
        super(in);
        this.c = c;
    }


    protected Class<?> resolveClass(ObjectStreamClass desc)
                            throws IOException, ClassNotFoundException {
        Class superC = null;
        try {
            superC = super.resolveClass(desc);
        } catch (Exception ex) { }

        if ((superC == null) || (superC.getName() == c.getName())) {
            return c;
        } else {
            return superC;
        }
    }
}
