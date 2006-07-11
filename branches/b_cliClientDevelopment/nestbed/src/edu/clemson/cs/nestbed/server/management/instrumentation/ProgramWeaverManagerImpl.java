/* $Id:$ */
/*
 * ProgramWeaverManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management.instrumentation;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramWeaverManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.nesc.weaver.MakefileWeaver;
import edu.clemson.cs.nestbed.server.nesc.weaver.WiringDiagramWeaver;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramMessageSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramWeaverManagerImpl extends    UnicastRemoteObject
                                      implements ProgramWeaverManager {

    private final static ProgramWeaverManagerImpl instance;

    private final static Log log = LogFactory.getLog(
                                           ProgramWeaverManagerImpl.class);


    static {
        ProgramWeaverManagerImpl impl = null;

        try {
            impl = new ProgramWeaverManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    public static ProgramWeaverManager getInstance() {
        return instance;
    }


    public void weaveInComponents(int                 programID,
                                  Map<String, String> updatedComponents)
                                                        throws RemoteException {
        try {
            ProgramManager progMgr = ProgramManagerImpl.getInstance();
            Program program = progMgr.getProgram(programID);
            weaveInComponents(new File(program.getSourcePath()),
                              updatedComponents);
        } catch (Exception ex) {
            throw new RemoteException("Exception", ex);
        }
    }


    private void weaveInComponents(File                dir,
                                   Map<String, String> updatedComponents)
                                                 throws FileNotFoundException,
                                                        Exception {
        log.debug("Weaving in components in directory " + dir);

        File   makefile      = new File(dir + "/Makefile");
        String component     = findComponentFromMakefile(makefile);
        File   componentNesc = new File(dir + "/" + component + ".nc");

        updateWiringDiagram(componentNesc, updatedComponents);
        updateMakefile(makefile);

    }


    private void updateWiringDiagram(File                componentNesc,
                                     Map<String, String> updatedComponents)
                                                throws FileNotFoundException,
                                                       Exception {
        WiringDiagramWeaver weaver = new WiringDiagramWeaver(componentNesc);

        weaver.addComponentReference("MgmtQueryC", "NucleusInterface");
        weaver.addConnection("Main",             "StdControl",
                             "NucleusInterface", "StdControl");

        weaver.addComponentReference("RemoteSetC", "NucleusSet");
        weaver.addConnection("Main",             "StdControl",
                             "NucleusSet",       "StdControl");

        weaver.addComponentReference("RadioControlC", "NestbedRadioControl");
        weaver.addConnection("Main",                "StdControl",
                             "NestbedRadioControl", "StdControl");

        for (String i : updatedComponents.keySet()) {
            weaver.updateComponent(i, updatedComponents.get(i));
        }

        weaver.regenerateNescSource();
    }


    // TODO:  This should really not be hard-coded like this.  It should
    //        be externally configurable.
    private void updateMakefile(File makefile) throws FileNotFoundException,
                                                      Exception {
        MakefileWeaver mfWeaver = new MakefileWeaver(makefile);
        mfWeaver.addLine("TOSMAKE_PATH += " +
                         "$(TOSDIR)/../contrib/nucleus/scripts");
        mfWeaver.addLine("CFLAGS += -I$(TOSDIR)/../beta/Drip");
        mfWeaver.addLine("CFLAGS += -I$(TOSDIR)/../beta/Drain");
        mfWeaver.addLine("CFLAGS += " +
                         "-I$(TOSDIR)/../contrib/nucleus/tos/lib/Nucleus");
        mfWeaver.addLine("CFLAGS += -I/opt/nestbed/lib/RadioPower");

        mfWeaver.addLine("# The following line *MUST* be last");
        mfWeaver.addLine("include $(TOSROOT)/tools/make/Makerules");

        mfWeaver.regenerateMakefile();
    }


    private String findComponentFromMakefile(File makefile)
                                                throws FileNotFoundException,
                                                       Exception {
        Scanner scanner   = new Scanner(makefile);
        String  component = null;

        log.debug("Makefile:  " + makefile);

        try {
            while (scanner.hasNext() && component == null) {
                String  line    = scanner.nextLine();
                String  regExp  = "^COMPONENT=(\\S+)";
                Pattern pattern = Pattern.compile(regExp);
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    component = matcher.group(1);
                }
            }
        } finally {
            try { scanner.close(); } catch (Exception ex) { /* empty */ }
        }


        if (component == null) {
            // FIXME:  Shouldn't be throwing generic Exceptions
            throw new Exception("No main component found in Makefile.");
        }

        return component;
    }



    private ProgramWeaverManagerImpl() throws RemoteException {
        super();
    }
}
