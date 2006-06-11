/* $Id$ */
/*
 * ProgramManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management.configuration;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProgramAdapter;
import edu.clemson.cs.nestbed.server.nesc.comm.MoteComm;
import edu.clemson.cs.nestbed.server.nesc.comm.mig.PowerMessage;
import edu.clemson.cs.nestbed.server.nesc.weaver.MakefileWeaver;
import edu.clemson.cs.nestbed.server.nesc.weaver.WiringDiagramWeaver;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramManagerImpl extends    RemoteObservableImpl
                                implements ProgramManager {

    private final static ProgramManager instance;
    private final static Log            log      = LogFactory.getLog(
                                                      ProgramManagerImpl.class);
    private final static int    MAX_THREADS = 40;
    private final static File   TOS_ROOT    = new File("/tmp/nestbed");
    private final static String MAKE        = "/usr/bin/make";
    private final static String MAKEOPTS    = "-C";
    private final static String GET_TYPES   =
                    "/home/adalton/src/java/nestbed/bin/getMessageTypes.sh";
    private final static String GET_FILE   =
                    "/home/adalton/src/java/nestbed/bin/getMessageFile.sh";
    static {
        ProgramManagerImpl impl = null;

        try {
            impl = new ProgramManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }


    private ProgramAdapter        programAdapter;
    private Map<Integer, Program> programs;
    private ExecutorService       threadPool;
    private ReadWriteLock         managerLock;
    private Lock                  readLock;
    private Lock                  writeLock;


    public static ProgramManager getInstance() {
        return instance;
    }


    public Program getProgram(int id) throws RemoteException {
        log.debug("getProgram() called.");

        readLock.lock();
        try {
            return programs.get(id);
        } finally {
            readLock.unlock();
        }
    }


    public List<Program> getProgramList(int projectID) throws RemoteException {
        log.debug("getProgramList() called");
        List<Program>       programList = new ArrayList<Program>();

        readLock.lock();
        try {
            for (Program i : programs.values()) {
                if (i.getProjectID() == projectID) {
                    programList.add(i);
                }
            }
        } catch (Exception ex) {
            String msg = "Exception in getProgramList";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            readLock.unlock();
        }

        return programList;
    }


    public void createNewProgram(final int    testbedID,
                                 final int    projectID,
                                 final String name,
                                 final String description,
                                 final byte[] buffer,
                                 final String tosPlatform)
                                                   throws RemoteException {
        log.info("Request to create new program:\n"        +
                 "  testbedID:    " + testbedID     + "\n" +
                 "  projectID:    " + projectID     + "\n" +
                 "  name:         " + name          + "\n" +
                 "  description:  " + description   + "\n" +
                 "  file size:    " + buffer.length);

        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    Program program;
                    program = programAdapter.createNewProgram(projectID, name,
                                                              description);

                    File file = saveTempFile(buffer);
                    File dir  = makeProgramDir(testbedID, projectID,
                                               program.getID());

                    dir = extractZipFile(file, dir);

                    program = programAdapter.updateProgramPath(program.getID(),
                                                         dir.getAbsolutePath());

                    weaveInComponents(dir);

                    notifyObservers(Message.COMPILE_STARTED, null);

                    StringBuffer output      = new StringBuffer();
                    boolean      exitSuccess = false;


                    output.append("-------------------------------------");
                    output.append("-----------------------------------\n");
                    output.append("Building for platform ").append(tosPlatform);
                    output.append("\n");
                    output.append("-------------------------------------");
                    output.append("-----------------------------------\n");

                    notifyObservers(Message.COMPILE_PROGRESS,
                                    output.toString());

                    ProcessBuilder processBuilder;
                    processBuilder = new ProcessBuilder(MAKE, MAKEOPTS,
                                                        program.getSourcePath(),
                                                        tosPlatform, "nucleus");
                    processBuilder.redirectErrorStream(true);
                    Process process = processBuilder.start();
                    output.append(getProcessOutput(process,
                                                   Message.COMPILE_PROGRESS));
                    process.waitFor();
                    exitSuccess = (process.exitValue() == 0);

                    notifyObservers(Message.COMPILE_COMPLETED, exitSuccess);

                    if (exitSuccess) {
                        log.info("Program compiled successfully.");

                        try {
                            loadProgramSymbols(program, tosPlatform);
                            loadProgramMessageTypes(program, tosPlatform);

                            writeLock.lock();
                            try {
                                programs.put(program.getID(), program);
                            } finally {
                                writeLock.unlock();
                            }

                            notifyObservers(Message.NEW_PROGRAM, program);
                        } catch (JDOMException ex) {
                            log.error("JDOMException:", ex);
                            programAdapter.deleteProgram(program.getID());
                        } catch (IOException ex) {
                            log.error("IOException:", ex);
                            programAdapter.deleteProgram(program.getID());
                        }
                    } else {
                        log.warn("Program failed to compile.");
                        deleteProgram(program.getID());
                    }
                } catch (IOException ex) {
                    String msg = "I/O Exception while creating new program";
                    log.error(msg, ex);
                } catch (InterruptedException ex) {
                    String msg = "Compilation interrupted";
                    log.error(msg, ex);
                } catch (AdaptationException ex) {
                    log.error("AdaptationException:", ex);
                } catch (Exception ex) {
                    log.error("Exception:", ex);
                }
            }
        });
    }


    private void weaveInComponents(File directory)
                                    throws FileNotFoundException, Exception {
        log.debug("Weaving in components in directory " + directory);

        File   makefile      = new File(directory + "/Makefile");
        String component     = findComponentFromMakefile(makefile);
        File   componentNesc = new File(directory + "/" +
                                        component + ".nc");

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
            try { scanner.close(); } catch (Exception ex) { /* empty */ }
        }


        if (component == null) {
            // FIXME:  Shouldn't be throwing generic Exceptions
            throw new Exception("No main component found in Makefile.");
        }

        return component;
    }


    public void deleteProgram(int programID) throws RemoteException {
        try {
            log.info("Deleting program with id  " + programID);

            cleanupProgramMessageSymbols(programID);
            cleanupProgramSymbols(programID);
            cleanupMoteDeploymentConfigurations(programID);
            cleanupProgramMessageSymbols(programID);

            Program program = programAdapter.deleteProgram(programID);
            writeLock.lock();
            try {
                programs.remove(program.getID());
            } finally {
                writeLock.unlock();
            }

            File sourcePath = new File(program.getSourcePath());
            deleteDirectory(sourcePath);
            cleanupParentDirectories(sourcePath);
            notifyObservers(Message.DELETE_PROGRAM, program);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in deleteProgram";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void installProgram(final int          moteAddress,
                               final String       moteSerialID,
                               final String       tosPlatform,
                               final int          programID,
                               final StringBuffer output) 
                                                       throws RemoteException {

        threadPool.execute(new Runnable() {
            public void run() {
                try {
                   // make -C <sourcePath> \
                   //     <tosPlatform> reinstall.<moteAddress> \
                   //     bsl,/dev/motes/<moteSerialID>
                    Program         program;

                    readLock.lock();
                    try {
                        program = programs.get(programID);
                    } finally {
                        readLock.unlock();
                    }

                    String          commPort       = "/dev/motes/" +
                                                     moteSerialID;
                    ProcessBuilder  processBuilder = new ProcessBuilder(MAKE,
                                                  MAKEOPTS, 
                                                  program.getSourcePath(), 
                                                  tosPlatform, "nucleus",
                                                  "reinstall." + moteAddress,
                                                  "bsl," + commPort);
                    processBuilder.redirectErrorStream(true);
                    notifyObservers(Message.PROGRAM_INSTALL_BEGIN,
                                    moteSerialID);

                    Process process = processBuilder.start();
                    output.append(getProcessOutput(process, null));
                    process.waitFor();
                    int exitValue = process.exitValue();

                    if (exitValue == 0) {
                        try { Thread.sleep(3000); } catch (Exception ex) { }
                        setRadioPowerLevel(moteAddress, commPort, tosPlatform,
                                           moteSerialID, programID);

                        notifyObservers(Message.PROGRAM_INSTALL_SUCCESS,
                                        moteSerialID);
                    } else {
                        notifyObservers(Message.PROGRAM_INSTALL_FAILURE,
                                        moteSerialID);
                    }
                } catch (InterruptedException ex) {
                    String msg = "process interrupted while waiting for " +
                                 "install";
                    log.error(msg, ex);
                } catch (IOException ex) {
                    String msg = "I/O Exception while installing program";

                    log.error(msg, ex);
                }
            }
        });
    }


    private void setRadioPowerLevel(int    address,     String commPort,
                                    String tosPlatform, String moteSerialID,
                                    int    programID) throws RemoteException,
                                                             IOException {
        log.debug("Setting radio power on mote with\n" +
                  "    address:       " + address     + "\n" +
                  "    commPort:      " + commPort    + "\n" +
                  "    tosPlatform:   " + tosPlatform + "\n" +
                  "    moteSerialID:  " + moteSerialID);

        PowerMessage powerMessage = new PowerMessage();
        Mote         mote         = MoteManagerImpl.getInstance().getMote(
                                                                moteSerialID);
        int          moteID       = mote.getID();
        int          powerLevel   = MoteDeploymentConfigurationManagerImpl.
                                        getInstance().
                                      getMoteDeploymentConfigurationByProgramID(
                                        moteID, programID).getRadioPowerLevel();
        MoteComm     moteComm     = new MoteComm(address, commPort);

        powerMessage.set_powerLevel((short) powerLevel);

        moteComm.start();
        moteComm.send(powerMessage);
        moteComm.stop();
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
        File         directory = new File(program.getSourcePath());
        List<String> msgList   = getMessageList(directory, tosPlatform);;

        for (String i : msgList) {
            File headerFile = getMessageFile(directory, i);

            generateMigClass(headerFile, i, directory);
            byte[] bytecode = compileFile(directory, i, ".java");

            ProgramMessageSymbolManagerImpl.getInstance().
                                        addProgramMessageSymbol(program.getID(),
                                                                i, bytecode);
        }
    }


    private void generateMigClass(File headerFile, String messageType,
                                  File directory)     throws IOException {

        log.info("Generating MIG classes for messageType: " + messageType +
                 " to directory " + directory.getAbsolutePath());

        ProcessBuilder processBuilder =
            new ProcessBuilder("/usr/local/bin/mig", "java",
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


    private File getMessageFile(File directory, String messageType)
                                                           throws IOException {
        log.info("getMessageFile():  directory = " + directory.getAbsolutePath() +
                 ", messageType = " + messageType);

        ProcessBuilder processBuilder =
                                new ProcessBuilder(GET_FILE,
                                                   directory.getAbsolutePath(),
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


    private List<String> getMessageList(File directory, String tosPlatform)
                                                        throws IOException {
        log.info("getMessageList(): directory = " + directory.getAbsolutePath() +
                 ", tosPlatform = " + tosPlatform);
        List<String>   messageList    = new ArrayList<String>();
        ProcessBuilder processBuilder = 
                                new ProcessBuilder(GET_TYPES,
                                                   directory.getAbsolutePath(),
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

    private File saveTempFile(byte[] buffer) throws IOException {
        File             file = File.createTempFile("nestbed", ".zip");
        FileOutputStream fout = new FileOutputStream(file);

        fout.write(buffer);
        fout.close();

        file.deleteOnExit();
        return file;
    }


    private File extractZipFile(File file, File baseDir)
                    throws ZipException, IOException {

        File    root    = null;
        ZipFile zipFile = new ZipFile(file);

        for (Enumeration e = zipFile.entries(); e.hasMoreElements(); ) {
            ZipEntry zipEntry = (ZipEntry) e.nextElement();

            if (zipEntry.isDirectory()) {
                File f = new File(baseDir, zipEntry.getName());
                f.mkdir();

                if (root == null) {
                    root = f;
                }
            } else {
                BufferedReader in = new BufferedReader(
                                         new InputStreamReader(
                                             zipFile.getInputStream(zipEntry)));

                BufferedWriter out = new BufferedWriter(
                                         new OutputStreamWriter(
                                             new FileOutputStream(
                                                 new File(baseDir,
                                                     zipEntry.toString()))));

                char[] buffer = new char[4096];
                int    length = in.read(buffer, 0, buffer.length);

                while (length != -1) {
                    out.write(buffer, 0, length);
                    length = in.read(buffer, 0, buffer.length);
                }

                out.close();
                in.close();
            }
        }

        return root;
    }


    private void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }

            directory.delete();
        }
    }


    private void cleanupParentDirectories(File directory) {
        for (File dir = directory; dir != null; dir = dir.getParentFile()) {
            if (dir.exists()) {
                dir.delete();
            }
        }
    }


    private void cleanupProgramSymbols(int programID) throws RemoteException {
        List<ProgramSymbol> programSymbolList;
        ProgramSymbolManager psm = ProgramSymbolManagerImpl.getInstance();

        programSymbolList = psm.getProgramSymbols(programID);

        for (ProgramSymbol i : programSymbolList) {
            psm.deleteProgramSymbol(i.getID());
        }
    }


    private void cleanupMoteDeploymentConfigurations(int programID)
                                                       throws RemoteException {
        MoteDeploymentConfigurationManagerImpl.getInstance().
                                    deleteConfigsForProgram(programID);
    }


    private void cleanupProgramMessageSymbols(int programID) 
                                                       throws RemoteException {
        ProgramMessageSymbolManagerImpl.getInstance().deleteSymbolsForProgram(
                                                                    programID);
    }


    private File makeProgramDir(int testbedID, int projectID, int programID) {
        File dir  = new File(TOS_ROOT, Integer.toString(testbedID));
        dir.mkdir();

        dir = new File(dir, Integer.toString(projectID));
        dir.mkdir();

        dir = new File(dir, Integer.toString(programID));
        dir.mkdir();

        return dir;
    }


    private ProgramManagerImpl() throws RemoteException {
        super();

        try {
            this.threadPool     = Executors.newFixedThreadPool(MAX_THREADS);
            this.managerLock    = new ReentrantReadWriteLock(true);
            this.readLock       = managerLock.readLock();
            this.writeLock      = managerLock.writeLock();
            this.programAdapter = AdapterFactory.createProgramAdapter(
                                                            AdapterType.SQL);
            this.programs       = programAdapter.readPrograms();
            log.debug("Programs read:\n" + programs);
        } catch (AdaptationException ex) {
            throw new RemoteException("AdaptationException", ex);
        }
    }
}
