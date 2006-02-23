/*
 * ComponentRef.java
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


public class ComponentRef extends AstNode {
    public String component;
    public String renamedComponent;


    public ComponentRef(String component, String renamedComponent) {
        this.component        = component;
        this.renamedComponent = renamedComponent;
    }

    public ComponentRef(String component) {
        this(component, null);
    }


    public String toString() {
        String retStr = component.toString();

        if (renamedComponent != null) {
            retStr += " as " + renamedComponent.toString();
        }

        return retStr;
    }
}
