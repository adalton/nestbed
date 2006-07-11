/* $Id$ */
/*
 * AstNode.java
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class AstNode {
    public List<AstNode> getNodesOfType(Class classType) {
        List<AstNode> nodeList = new ArrayList<AstNode>();
        Field[]       fields   = this.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            try {
                Object fieldObject = fields[i].get(this);

                if (fieldObject != null) {
                    if (fieldObject.getClass().equals(classType)) {
                        nodeList.add((AstNode) fieldObject);
                    }

                    if (fieldObject instanceof AstNode) {
                        AstNode node = (AstNode) fieldObject;
                        nodeList.addAll(node.getNodesOfType(classType));
                    }
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return nodeList;
    }
}
