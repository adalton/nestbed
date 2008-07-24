/* $Id$ */
/*
 * WiringDiagramWeaver.java
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
package edu.clemson.cs.nestbed.server.nesc.weaver;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nesctk.NescToolkit;
import edu.clemson.cs.nesctk.SourceFile;
import edu.clemson.cs.nesctk.ast.*;
import edu.clemson.cs.nesctk.util.AstUtils;
import edu.clemson.cs.nesctk.util.FileUtils;


public class WiringDiagramWeaver {
    private final static Log log = LogFactory.getLog(WiringDiagramWeaver.class);

    private NescToolkit toolkit;
    private SourceFile  sourceFile;


    public WiringDiagramWeaver(File nescFile) throws Exception {
        nescFile = nescFile.getAbsoluteFile();

        log.info("Top-Level configuration " + nescFile.getAbsolutePath());

        toolkit = new NescToolkit(nescFile, nescFile.getAbsoluteFile().getParentFile());
        toolkit.addIncludePath("/opt/tinyos-2.x/tos/lib/net");
        toolkit.addIncludePath("/opt/tinyos-2.x/tos/lib/net/ctp");
        toolkit.addIncludePath("/opt/tinyos-2.x/tos/lib/net/le");

        toolkit.appendGccArgument("-I");toolkit.appendGccArgument("/opt/tinyos-2.x/tos/lib/net");
        toolkit.appendGccArgument("-I");toolkit.appendGccArgument("/opt/tinyos-2.x/tos/lib/net/ctp");
        toolkit.appendGccArgument("-I");toolkit.appendGccArgument("/opt/tinyos-2.x/tos/lib/net/le");

        Map<String, SourceFile> sourceFileMap = toolkit.load();
        sourceFile = sourceFileMap.get(nescFile.getName());
        FileUtils.deleteRecursive(new File(nescFile.getParent(), "analysis"));
    }


    public void addComponentReference(String name, String as) throws Exception {
        ConfigurationComponent configurationComponent = (ConfigurationComponent)
                                                       sourceFile.getTreeRoot();

        AstUtils.addComponentToConfig(configurationComponent, name, as);
    }


    public void addConnection(String leftComp,  String leftInterface,
                              String rightComp, String rightInterface)
                                                              throws Exception {
        ConfigurationComponent configurationComponent = (ConfigurationComponent)
                                                       sourceFile.getTreeRoot();

        AstUtils.addWiring(configurationComponent, leftComp,  leftInterface,
                                                   rightComp, rightInterface);
    }


    public void updateComponent(String oldComponent, String newComponent)
                                                              throws Exception {
        for (AstNode i : sourceFile.getTreeRoot().getNodesOfType(Cuses.class, true)) {
            Cuses cuses = (Cuses) i;

            for (ComponentRefList j = cuses.getComponentRefList();
                                                      j != null; j = j.next()) {
                if (j.getItem().getComponentName().equals(oldComponent)) {
                    j.getItem().setComponentName(newComponent);
                }
            }
        }



        for (AstNode i : sourceFile.getTreeRoot().getNodesOfType(Endpoint.class)) {
            Endpoint                endpoint;
            ParameterisedIdentifier id;

            endpoint = (Endpoint) i;
            id       = endpoint.getItem();


            if (id.getIdentifierName().equals(oldComponent)) {
                id.setIdentifierName(newComponent);
            }
        }
    }


    public void regenerateNescSource() throws FileNotFoundException {
        AstUtils.regenerateSource(sourceFile, sourceFile.getSourceFile());
    }
}
