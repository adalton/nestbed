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
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProgramAdapter;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramManagerImpl extends    RemoteObservableImpl
                                implements ProgramManager {

    private final static ProgramManager instance;
    private final static Log            log      = LogFactory.getLog(
                                                      ProgramManagerImpl.class);
    private final static File   TOS_ROOT    = new File("/tmp/nestbed");

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


    public int createNewProgram(final int    testbedID,
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

        int programID = -1;

        try {
            Program program;
            program = programAdapter.createNewProgram(projectID, name,
                                                      description);
            programID = program.getID();

            File file = saveTempFile(buffer);
            File dir  = makeProgramDir(testbedID, projectID, projectID);

            dir = extractZipFile(file, dir);

            program   = programAdapter.updateProgramPath(programID,
                                                         dir.getAbsolutePath());

            writeLock.lock();
            try {
                programs.put(programID, program);
            } finally {
                writeLock.unlock();
            }
            notifyObservers(Message.NEW_PROGRAM, program);
        } catch (IOException ex) {
            String msg = "I/O Exception while creating new program";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } catch (AdaptationException ex) {
            String msg = "AdaptationException";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } catch (Exception ex) {
            String msg = "Exception";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }

        return programID;
    }


    public void deleteProgram(int programID) throws RemoteException {
        try {
            log.info("Deleting program with id  " + programID);

            cleanupProgramMessageSymbols(programID);
            cleanupProgramSymbols(programID);
            cleanupMoteDeploymentConfigurations(programID);

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
        MoteDeploymentConfigurationManager mdcm;
        mdcm = MoteDeploymentConfigurationManagerImpl.getInstance();

        mdcm.deleteConfigsForProgram(programID);
    }


    private void cleanupProgramMessageSymbols(int programID) 
                                                       throws RemoteException {
        ProgramMessageSymbolManager pmsm;
        pmsm = ProgramMessageSymbolManagerImpl.getInstance();

        pmsm.deleteSymbolsForProgram(programID);
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
