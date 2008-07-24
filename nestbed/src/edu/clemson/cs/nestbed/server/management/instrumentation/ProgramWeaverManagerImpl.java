/* $Id$ */
/*
 * ProgramWeaverManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management.instrumentation;


import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramWeaverManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.server.nesc.weaver.MakefileWeaver;
import edu.clemson.cs.nestbed.server.nesc.weaver.WiringDiagramWeaver;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramManagerImpl;
import edu.clemson.cs.nestbed.server.util.FileUtils;


public class ProgramWeaverManagerImpl extends    UnicastRemoteObject
                                      implements ProgramWeaverManager {

    private final static ProgramWeaverManagerImpl instance;

    private final static Log log = LogFactory.getLog(
                                           ProgramWeaverManagerImpl.class);

    private final static String[] NESC_LIBRARY_DIRECTORIES;


    static {
        String   property = "nestbed.libs.nesC";
        String   value    = System.getProperty(property);
        String[] tokens;

        if (value != null) {
            tokens = value.split(":");
        } else {
            tokens = new String[0];
        }

        NESC_LIBRARY_DIRECTORIES = tokens;
        for (String directory : NESC_LIBRARY_DIRECTORIES) {
            log.info("NesC Library directory: " + directory);
        }


        // This has to be last -- it depends on what's above
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

        weaver.addComponentReference("MemoryProfilerC", "NESTbedMemoryProfiler");
        weaver.addConnection("NESTbedMemoryProfiler", "Boot",
                             "MainC",                 "Boot");

        weaver.addComponentReference("NestbedControlC", "NestbedControl");
        weaver.addConnection("NestbedControl", "Boot",
                             "MainC",          "Boot");

        for (String i : updatedComponents.keySet()) {
            weaver.updateComponent(i, updatedComponents.get(i));
        }

        weaver.regenerateNescSource();
    }


    private void updateMakefile(File makefile) throws FileNotFoundException,
                                                      Exception {
        MakefileWeaver mfWeaver = new MakefileWeaver(makefile);

        for (String directory : NESC_LIBRARY_DIRECTORIES) {
            mfWeaver.addLine("CFLAGS += -I" + directory);
        }

        mfWeaver.addLine("# The following line *MUST* be last");
        mfWeaver.addLine("include $(TOSROOT)/support/make/Makerules");

        mfWeaver.regenerateMakefile();
    }


    public String findComponentFromMakefile(File makefile)
                                                throws RemoteException {
        String  component = null;
        Scanner scanner   = null;

        try {
            log.debug("Makefile:  " + makefile);
            scanner = new Scanner(makefile);

            while (scanner.hasNext() && component == null) {
                String  line    = scanner.nextLine();
                String  regExp  = "^COMPONENT=(\\S+)";
                Pattern pattern = Pattern.compile(regExp);
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    component = matcher.group(1);
                }
            }
        } catch (FileNotFoundException fnfe) {
            throw new RemoteException("FileNotFoundException", fnfe);
        } finally {
            try { scanner.close(); } catch (Exception ex) { /* empty */ }
        }


        if (component == null) {
            // FIXME:  Shouldn't be throwing generic Exceptions
            throw new RemoteException("No main component found in Makefile.");
        }

        return component;
    }



    private ProgramWeaverManagerImpl() throws RemoteException {
        super();
    }
}
