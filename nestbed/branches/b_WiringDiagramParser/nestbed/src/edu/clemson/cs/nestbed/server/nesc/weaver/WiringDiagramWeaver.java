/* $Id$ */
/*
 * WiringDiagramWeaver.java
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
package edu.clemson.cs.nestbed.server.nesc.weaver;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.server.nesc.parser.Lexer;
import edu.clemson.cs.nestbed.server.nesc.parser.Parser;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.AstNode;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.Connection;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.ConfigurationDecl;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.ConfigurationDecls;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.Component;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.ComponentList;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.ComponentRef;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.Cuses;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.Endpoint;
import edu.clemson.cs.nestbed.server.nesc.parser.ast.ParameterisedIdentifier;
import edu.clemson.cs.nestbed.server.util.FileUtils;


public class WiringDiagramWeaver {
    private final static Log log = LogFactory.getLog(WiringDiagramWeaver.class);

    private File      nescFile;
    private File      nescFileBackup;
    private Component component;


    public WiringDiagramWeaver(File nescFile) throws FileNotFoundException,
                                                     IOException,
                                                     Exception {
        this.nescFile       = nescFile;
        this.nescFileBackup = new File(nescFile + ".orig");

        FileUtils.copyFile(nescFile, nescFileBackup);


        log.debug("Parsing " + nescFile + " for wiring diagram.");

        Lexer lexer   = new Lexer(nescFile);
        Parser parser = new Parser(lexer);
        parser.parse();

        this.component = parser.getComponent();
    }


    public void addComponentReference(String name, String as) throws Exception {
        ComponentRef  componentRef = new ComponentRef(name, as);
        List<AstNode> cusesList    = component.getNodesOfType(Cuses.class);

        if (cusesList.size() > 0) {
            Cuses cuses = (Cuses) cusesList.get(0);
            cuses.componentList = new ComponentList(cuses.componentList,
                                                    componentRef);
            log.debug("Added Component reference:  " + componentRef);
        } else {
            throw new Exception("No Cuses found in AST.");
        }
    }


    public void addConnection(String leftComp,  String leftInterface,
                              String rightComp, String rightInterface) 
                                                            throws Exception {
        ParameterisedIdentifier id1;
        ParameterisedIdentifier id2;

        id1 = new ParameterisedIdentifier(leftComp);
        id2 = new ParameterisedIdentifier(leftInterface);
        Endpoint left = new Endpoint(new Endpoint(id1), id2);

        id1 = new ParameterisedIdentifier(rightComp);
        id2 = new ParameterisedIdentifier(rightInterface);
        Endpoint right = new Endpoint(new Endpoint(id1), id2);


        Connection    connection = new Connection(left, "->", right);
        List<AstNode> connDecls  = component.getNodesOfType(
                                                    ConfigurationDecls.class);

        boolean connDeclFound = false;
        for (Iterator<AstNode> i = connDecls.iterator();    i.hasNext()
                                                         && !connDeclFound; ) {
            ConfigurationDecls decls = (ConfigurationDecls) i.next();

            if (decls.configurationDecl.connection != null) {
                ConfigurationDecl decl = new ConfigurationDecl(connection);
                decls.configurationDecls = new ConfigurationDecls(
                                                decls.configurationDecls,
                                                decl);
                connDeclFound = true;
            }
        }

        if (connDeclFound) {
            log.debug("Added Connection:  " + connection);
        } else {
            throw new Exception("Unable to find ConfigurationDecl");
        }
    }


    public void regenerateNescSource() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(nescFile);
        out.println(component);
        out.close();
    }
}
