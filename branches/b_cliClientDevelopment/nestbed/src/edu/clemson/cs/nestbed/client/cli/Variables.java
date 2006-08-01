/* $Id:$ */
/*
 * Variables.java
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
package edu.clemson.cs.nestbed.client.cli;


import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import edu.clemson.cs.nestbed.common.util.VariableProperties;


public class Variables {
    private static Properties variables = new VariableProperties();


    public static String set(String variable, String value) {
        return (String) variables.setProperty(variable, value);
    }


    public static String get(String variable) {
        return variables.getProperty(variable);
    }


    public static String unset(String variable) {
        String oldValue = (String) variables.get(variable);

        //((Hashtable<Object, Object>) variables).remove(variable);
        variables.remove(variable);

        return oldValue;
    }


    public static Iterator<String> nameIterator() {
        return new Iterator<String>() {
            private Enumeration e = variables.propertyNames();


            public boolean hasNext() {
                return e.hasMoreElements();
            }


            public String next() {
                return (String) e.nextElement();
            }


            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    private Variables() { }
}

