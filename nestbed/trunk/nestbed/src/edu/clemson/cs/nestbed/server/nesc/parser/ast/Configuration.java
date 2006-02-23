/*
 * Configuration.java
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
package edu.clemson.cs.nestbed.server.nesc.parser.ast;


public class Configuration extends AstNode {
    public Configuration(String identifier, RequiresOrProvidesList list,
                         IConfiguration iconfiguration) {
        this.identifier             = identifier;
        this.requiresOrProvidesList = list;
        this.iconfiguration         = iconfiguration;
    }


    public String                 identifier;
    public RequiresOrProvidesList requiresOrProvidesList;
    public IConfiguration         iconfiguration;


    public String toString() {
        String retStr = "\nconfiguration " + identifier + " {\n";

        if (requiresOrProvidesList != null) {
            retStr += requiresOrProvidesList.toString();
        }

        retStr += "}\n" + iconfiguration.toString();

        return retStr;
    }
}
