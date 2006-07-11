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


import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.common.util.ZipUtils;
import edu.clemson.cs.nestbed.server.adaptation.AdaptationException;
import edu.clemson.cs.nestbed.server.adaptation.AdapterFactory;
import edu.clemson.cs.nestbed.server.adaptation.AdapterType;
import edu.clemson.cs.nestbed.server.adaptation.ProgramAdapter;
import edu.clemson.cs.nestbed.server.util.FileUtils;
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


    public int createNewProgram(int    testbedID, int    projectID,
                                String name,      String description,
                                byte[] zipData,   String tosPlatform)
                                                       throws RemoteException {
        log.info("Request to create new program:\n"        +
                 "  testbedID:    " + testbedID     + "\n" +
                 "  projectID:    " + projectID     + "\n" +
                 "  name:         " + name          + "\n" +
                 "  description:  " + description   + "\n" +
                 "  zipData size: " + zipData.length);

        int     programID = -1;
        boolean error     = false;

        try {
            Program program;
            File    dir;

            program   = programAdapter.createNewProgram(projectID, name,
                                                        description);
            programID = program.getID();
            dir       = FileUtils.makeProgramDir(TOS_ROOT,  testbedID,
                                                 projectID, programID);
            dir       = ZipUtils.unzip(zipData, dir);
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
            error = true;
            String msg = "I/O Exception while creating new program";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } catch (AdaptationException ex) {
            error = true;
            String msg = "AdaptationException";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } catch (Exception ex) {
            error = true;
            String msg = "Exception";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        } finally {
            if (error && programID != -1) {
                try { deleteProgram(programID); } catch (Exception ex2) { }
            }
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

            FileUtils.deleteDirectory(sourcePath);
            FileUtils.cleanupParentDirectories(TOS_ROOT, sourcePath);

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


    private void cleanupProgramSymbols(int programID) throws RemoteException {
        List<ProgramSymbol> programSymbolList;
        ProgramSymbolManager psm = ProgramSymbolManagerImpl.getInstance();

        programSymbolList = new ArrayList<ProgramSymbol>(
                                            psm.getProgramSymbols(programID));

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
