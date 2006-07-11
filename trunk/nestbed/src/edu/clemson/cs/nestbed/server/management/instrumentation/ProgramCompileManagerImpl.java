/* $Id$ */
/*
 * ProgramCompileManagerImpl.java
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
import java.util.ArrayList;
import java.util.List;
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
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramCompileManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.nesc.weaver.MakefileWeaver;
import edu.clemson.cs.nestbed.server.nesc.weaver.WiringDiagramWeaver;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramMessageSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramCompileManagerImpl extends    RemoteObservableImpl
                                       implements ProgramCompileManager {

    private final static ProgramCompileManagerImpl instance;

    private final static Log log = LogFactory.getLog(
                                           ProgramCompileManagerImpl.class);

    private final static int    MAX_THREADS = 8;
    private final static String MAKE        = "/usr/bin/make";
    private final static String MAKEOPTS    = "-C";
    private final static String GET_TYPES   =
                    "/home/adalton/src/java/nestbed/bin/getMessageTypes.sh";
    private final static String GET_FILE    =
                    "/home/adalton/src/java/nestbed/bin/getMessageFile.sh";


    static {
        ProgramCompileManagerImpl impl = null;

        try {
            impl = new ProgramCompileManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }

    private ExecutorService threadPool;


    public static ProgramCompileManager getInstance() {
        return instance;
    }


    public void compileProgram(int programID, String tosPlatform)
                                                       throws RemoteException {
        log.info("Request to compile program:\n"        +
                 "  programID:    " + programID     + "\n" +
                 "  tosPlatform:  " + tosPlatform);

        threadPool.execute(new CompilationRunnable(programID, tosPlatform));
    }


    private void generateMigClass(File headerFile, String messageType,
                                  File directory)     throws IOException {

        ProcessBuilder processBuilder;

        log.info("Generating MIG classes for messageType: " + messageType +
                 " to directory " + directory.getAbsolutePath());

        processBuilder = new ProcessBuilder("/usr/local/bin/mig", "java",
                                            "-java-classname=" + messageType,
                                            headerFile.getAbsolutePath(),
                                            messageType, "-o",
                                            directory.getAbsolutePath() + "/" +
                                            messageType + ".java");

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try { process.waitFor(); } catch (InterruptedException ex) { }
    }


    private byte[] compileFile(File directory, String file, String extension)
                                                        throws IOException {
        String dir = directory.getAbsolutePath();

        if (com.sun.tools.javac.Main.compile(new String[] {
                                             dir + "/" + file + extension,
                                             "-d", dir }) != 0) {
            log.error("Unable to compile: " + file + extension);
            return null;
        }

        File        classFile = new File(dir + "/" + file + ".class");
        InputStream in        = new FileInputStream(classFile);
        byte[]      bytecode  = new byte[(int) classFile.length()];

        in.read(bytecode);

        try { in.close(); } catch (Exception e) { }
        classFile.delete();

        return bytecode;
    }


    private File getMessageFile(File dir, String messageType)
                                                           throws IOException {
        log.info("getMessageFile():  directory = " + dir.getAbsolutePath() +
                 ", messageType = " + messageType);

        ProcessBuilder processBuilder =
                                new ProcessBuilder(GET_FILE,
                                                   dir.getAbsolutePath(),
                                                   messageType);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    process.getInputStream()));

        String line = in.readLine();
        in.close();

        return (line != null) ? new File(line) : null;
    }


    private List<String> getMessageList(File dir, String tosPlatform)
                                                        throws IOException {
        log.info("getMessageList(): directory = " + dir.getAbsolutePath() +
                 ", tosPlatform = " + tosPlatform);
        List<String>   messageList    = new ArrayList<String>();
        ProcessBuilder processBuilder = 
                                new ProcessBuilder(GET_TYPES,
                                                   dir.getAbsolutePath(),
                                                   tosPlatform);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();


        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    process.getInputStream()));

        String line;
        while ( (line = in.readLine()) != null) {
            messageList.add(line);
        }
        in.close();

        return messageList;
    }

/*
    private void weaveInComponents(File dir)
                                    throws FileNotFoundException, Exception {
        log.debug("Weaving in components in directory " + dir);

        File   makefile      = new File(dir + "/Makefile");
        String component     = findComponentFromMakefile(makefile);
        File   componentNesc = new File(dir + "/" + component + ".nc");

        updateWiringDiagram(componentNesc);
        updateMakefile(makefile);

    }


    private void updateWiringDiagram(File componentNesc)
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
            try { scanner.close(); } catch (Exception ex) { }
        }


        if (component == null) {
            // FIXME:  Shouldn't be throwing generic Exceptions
            throw new Exception("No main component found in Makefile.");
        }

        return component;
    }
*/


    private void loadProgramSymbols(Program program, String tosPlatform) 
                                                        throws JDOMException,
                                                               IOException {
        SAXBuilder builder    = new SAXBuilder();
        Document   schema     = builder.build(program.getSourcePath() +
                                                   "/build/" + tosPlatform +
                                                   "/nucleusSchema.xml");
        List       attributes = schema.getRootElement().getChild(
                                                   "symbols").getChildren();

        for (Object i : attributes) {
            Element  attribute = (Element) i;
            String   name      = attribute.getAttributeValue("name");
            String[] values    = name.split("\\.");
            String   module;
            String   symbol;

            if (values.length == 2) {
                module = values[0];
                symbol = values[1];
            } else {
                module = "<global>";
                symbol = values[0];
            }

            ProgramSymbolManagerImpl.getInstance().createProgramSymbol(
                                              program.getID(), module, symbol);
        }
    }


    private void loadProgramMessageTypes(Program program, String tosPlatform)
                                                           throws IOException {
        File         dir     = new File(program.getSourcePath());
        List<String> msgList = getMessageList(dir, tosPlatform);;

        for (String i : msgList) {
            File headerFile = getMessageFile(dir, i);

            generateMigClass(headerFile, i, dir);
            byte[] bytecode = compileFile(dir, i, ".java");

            ProgramMessageSymbolManagerImpl.getInstance().
                                        addProgramMessageSymbol(program.getID(),
                                                                i, bytecode);
        }
    }


    private String getProcessOutput(Process process, Message message)
                                                           throws IOException {
        StringBuffer   buffer = new StringBuffer();
        BufferedReader in     = new BufferedReader(
                                    new InputStreamReader(
                                        process.getInputStream()));
        String input;

        while ( (input = in.readLine()) != null) {
            if (message != null) {
                notifyObservers(message, input);
            }
            buffer.append(input).append("\n");
        }
        in.close();

        return buffer.toString();
    }


    private ProgramCompileManagerImpl() throws RemoteException {
        super();

        this.threadPool = Executors.newFixedThreadPool(MAX_THREADS);
    }


    protected class CompilationRunnable implements Runnable {
        private int    programID;
        private String tosPlatform;


        public CompilationRunnable(int programID, String tosPlatform) {
            this.programID   = programID;
            this.tosPlatform = tosPlatform;
        }


        public void run() {
            ProgramManager progMgr = ProgramManagerImpl.getInstance();
            Program        prog    = null;
            boolean        failed  = false;

            try {
                StringBuffer   output          = new StringBuffer();
                boolean        exitSuccess     = false;
                ProcessBuilder processBuilder  = null;
                Process        process         = null;

                prog = progMgr.getProgram(programID);

                //weaveInComponents(new File(prog.getSourcePath()));
                notifyObservers(Message.COMPILE_STARTED, null);

                output.append("-------------------------------------");
                output.append("-----------------------------------\n");
                output.append("Building for platform ").append(tosPlatform);
                output.append("\n-------------------------------------");
                output.append("-----------------------------------\n");

                notifyObservers(Message.COMPILE_PROGRESS, output.toString());

                processBuilder = new ProcessBuilder(MAKE, MAKEOPTS,
                                                    prog.getSourcePath(),
                                                    tosPlatform, "nucleus");
                processBuilder.redirectErrorStream(true);
                process = processBuilder.start();
                output.append(getProcessOutput(process,
                                               Message.COMPILE_PROGRESS));
                process.waitFor();
                exitSuccess = (process.exitValue() == 0);

                if (exitSuccess) {
                    log.info("Program compiled successfully.");

                    loadProgramSymbols(prog, tosPlatform);
                    loadProgramMessageTypes(prog, tosPlatform);
                } else {
                    log.warn("Program failed to compile.");
                    failed = true;
                }

                notifyObservers(Message.COMPILE_COMPLETED, exitSuccess);
            } catch (JDOMException ex) {
                log.error("JDOMException:", ex);
                failed = true;
            } catch (IOException ex) {
                log.error("I/O Exception while compiling new program", ex);
                failed = true;
            } catch (InterruptedException ex) {
                log.error("Compilation interrupted", ex);
                failed = true;
            } catch (Exception ex) {
                log.error("Exception:", ex);
                failed = true;
            } finally {
                if (failed) {
                    try {
                        progMgr.deleteProgram(prog.getID());
                    } catch (Exception ex) {
                        log.error("Exception in failure hander", ex);
                    }
                }
            }
        }
    }
}
