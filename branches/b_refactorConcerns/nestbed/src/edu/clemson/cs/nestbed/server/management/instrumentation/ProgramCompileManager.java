/* $Id:$ */
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
import java.io.InputStreamReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramCompileManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.server.nesc.comm.MoteComm;
import edu.clemson.cs.nestbed.server.nesc.comm.mig.PowerMessage;
import edu.clemson.cs.nestbed.server.management.configuration.MoteDeploymentConfigurationManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteTestbedAssignmentManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteTypeManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramManagerImpl;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramCompileManagerImpl extends    RemoteObservableImpl
                                       implements ProgramCompileManager {

    private final static ProgramCompileManagerImpl instance;

    private final static Log log = LogFactory.getLog(
                                           ProgramCompileManagerImpl.class);

    private final static int    MAX_THREADS = 8;
    private final static String MAKE        = "/usr/bin/make";
    private final static String MAKEOPTS    = "-C";


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


    public static ProgramDeploymentManager getInstance() {
        return instance;
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
}
