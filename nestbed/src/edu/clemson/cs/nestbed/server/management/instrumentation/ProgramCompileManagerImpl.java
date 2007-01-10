/* $Id$ */
/*
 * ProgramCompileManagerImpl.java
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * Department of Computer Science
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


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramCompileManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramMessageSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramSymbolManagerImpl;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramCompileManagerImpl extends    RemoteObservableImpl
                                       implements ProgramCompileManager {

    private final static ProgramCompileManagerImpl instance;

    private final static Log log = LogFactory.getLog(
                                           ProgramCompileManagerImpl.class);

    private final static String MAKE;
    private final static String MIG;
    private final static String MAKEOPTS = "-C";
    private final static String GET_TYPES;
    private final static String GET_FILE;
    private final static int    MAX_THREADS;


    static {
        String property;

        property = "nestbed.bin.make";
        String make = System.getProperty(property);
        if (make == null || !(new File(make).exists())) {
            log.fatal("Property '" + property + "' is not set " +
                      " or file does not exist: " + make);
            System.exit(1);
        }
        MAKE = make;
        log.info(property + " = " + MAKE);



        property = "nestbed.bin.mig";
        String mig = System.getProperty(property);
        if (mig == null || !(new File(mig).exists())) {
            log.fatal("Property '" + property + "' is not set " +
                      " or file does not exist: " + mig);
            System.exit(1);
        }
        MIG = mig;
        log.info(property + " = " + MIG);



        property = "nestbed.bin.getMessageTypes";
        String getTypes = System.getProperty(property);
        if (getTypes == null || !(new File(getTypes).exists())) {
            log.fatal("Property '" + property + "' is not set " +
                      " or file does not exist: " + getTypes);
            System.exit(1);
        }
        GET_TYPES = getTypes;
        log.info(property + " = " + GET_TYPES);



        property = "nestbed.bin.getMessageFile";
        String getFile = System.getProperty(property);
        if (getFile == null || !(new File(getFile).exists())) {
            log.fatal("Property '" + property + "' is not set " +
                      " or file does not exist: " + getFile);
            System.exit(1);
        }
        GET_FILE = getFile;
        log.info(property + " = " + GET_FILE);




        property = "nestbed.options.maxCompileThreads";
        String maxThreadsStr = System.getProperty(property);
        int    maxThreads    = 0;
        if (maxThreadsStr == null) {
            log.fatal("Property '" + property + "' is not set!");
            System.exit(1);
        } else {
            try {
                maxThreads = Integer.parseInt(maxThreadsStr);
            } catch (NumberFormatException ex) {
                log.fatal("Property '" + property +
                          "' is not an integer:  " + maxThreadsStr);
                System.exit(1);
            }
        }
        MAX_THREADS = maxThreads;
        log.info(property + " = " + MAX_THREADS);



        // This has to be last -- it depends upon what's above it
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

        log.info("Generating MIG classes from header file: " +
                 headerFile.getAbsolutePath() + " for messageType: " +
                 messageType + " to directory " +
                 directory.getAbsolutePath());

        processBuilder = new ProcessBuilder(MIG, "java",
                                            "-java-classname=" + messageType,
                                            headerFile.getAbsolutePath(),
                                            messageType, "-o",
                                            directory.getAbsolutePath() + "/" +
                                            messageType + ".java");

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try {
            process.waitFor();
            int exitValue = process.exitValue();

            if (exitValue != 0) {
                log.error("MIG Failed with exit code:  " + exitValue);
                throw new IOException("MIG Failed with exit code " + exitValue);
            }
        } catch (InterruptedException ex) {
            log.warn("MIG interrupted");
        }
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

            if (headerFile != null) {
                generateMigClass(headerFile, i, dir);
                byte[] bytecode = compileFile(dir, i, ".java");

                ProgramMessageSymbolManagerImpl.getInstance().
                                            addProgramMessageSymbol(program.getID(),
                                                                    i, bytecode);
            }
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
