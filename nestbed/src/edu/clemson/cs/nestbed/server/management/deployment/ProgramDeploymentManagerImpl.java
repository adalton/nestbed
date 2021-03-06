/* $Id$ */
/*
 * ProgramDeploymentManagerImpl.java
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
package edu.clemson.cs.nestbed.server.management.deployment;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.deployment.ProgramDeploymentManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.server.nesc.comm.MoteComm;
import edu.clemson.cs.nestbed.server.nesc.comm.mig.ControlMessage;
import edu.clemson.cs.nestbed.server.management.configuration.MoteDeploymentConfigurationManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteTestbedAssignmentManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.MoteTypeManagerImpl;
import edu.clemson.cs.nestbed.server.management.configuration.ProgramManagerImpl;
import edu.clemson.cs.nestbed.server.util.RemoteObservableImpl;


public class ProgramDeploymentManagerImpl extends    RemoteObservableImpl
                                          implements ProgramDeploymentManager {

    private final static ProgramDeploymentManagerImpl instance;

    private final static Log log = LogFactory.getLog(
                                           ProgramDeploymentManagerImpl.class);

    private final static int    MAX_POWER = 31;
    private final static int    MAX_RETRY = 2;
    private final static int    MAX_THREADS;
    private final static String INSTALL;
    private final static String NESTBED_NESC_ROOT;
    private final static String TRACE_RECORDER_DIR;

    static {
        String property;

        property = "nestbed.bin.install";
        String install = System.getProperty(property);
        if (install == null || !(new File(install).exists())) {
            log.fatal("Property '" + property + "' is not set " +
                      " or file does not exist: " + install);
            System.exit(1);
        }
        INSTALL = install;
        log.info(property + " = " + INSTALL);

        property = "nestbed.dir.lib.nesc";
        String rootStr = System.getProperty(property);
        File   root    = null;

        if (rootStr == null) {
            log.fatal("Property '" + property + "' is not set");
            System.exit(1);

        } else {
            root = new File(rootStr);

            if (!root.exists()) {
                log.fatal("Directory " + rootStr + " does not exist!");
                System.exit(1);
            }
        }
        NESTBED_NESC_ROOT = root.getAbsolutePath();
        log.info(property + " = " + NESTBED_NESC_ROOT);
        TRACE_RECORDER_DIR = root.getAbsolutePath() + "/TraceRecorder";


        property = "nestbed.options.maxDeploymentThreads";
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



        // This has to be last -- it depends upon what's above
        ProgramDeploymentManagerImpl impl = null;
        try {
            impl = new ProgramDeploymentManagerImpl();
        } catch (Exception ex) {
            log.fatal("Unable to create singleton instance", ex);
            System.exit(1);
        } finally {
            instance = impl;
        }
    }

    private ExecutorService threadPool;
    private Random          random     = new Random();


    public static ProgramDeploymentManager getInstance() {
        return instance;
    }

    public List<String> installTraceReceiver(List<MoteTestbedAssignment> interestingMotes) throws RemoteException {
        List<String> success = new ArrayList<String>();

        for (MoteTestbedAssignment mta : interestingMotes) {
            Mote   mote         = MoteManagerImpl.getInstance().getMote(mta.getMoteID());
            int    address      = mta.getMoteAddress();
            String moteSerialID = mote.getMoteSerialID();
            String tosPlatform  = "telosb"; // Should really look this up
            String commPort     = "/dev/motes/" + moteSerialID;

            try {

                ProcessBuilder  processBuilder;

                log.info("Installing:  " + INSTALL + " "
                                         + TRACE_RECORDER_DIR + " "
                                         + tosPlatform + " "
                                         + address + " "
                                         + commPort);

                processBuilder = new ProcessBuilder(INSTALL,
                                                    TRACE_RECORDER_DIR,
                                                    tosPlatform,
                                                    Integer.toString(address),
                                                    commPort);
                processBuilder.redirectErrorStream(true);


                Process process = processBuilder.start();
                process.waitFor();

                int exitValue = process.exitValue();

                if (exitValue != 0) {
                    log.error(moteSerialID + " -- Install failed with exit code: " + exitValue);
                }
                success.add(Integer.toString(address));

            } catch (Exception ex) {
                log.error("installTraceReceiver failed for mote: " + address, ex);
            }
        }

        return success;
    }


    public void deployConfiguration(int configID) throws RemoteException {
        List<MoteDeploymentConfiguration> moteDeploymentConfigs;

        try {
            moteDeploymentConfigs = MoteDeploymentConfigurationManagerImpl.
                         getInstance().getMoteDeploymentConfigurations(configID);

            for (MoteDeploymentConfiguration i : moteDeploymentConfigs) {
                StringBuffer          output;
                Mote                  mote;
                MoteType              type;
                MoteTestbedAssignment mtba;

                output = new StringBuffer();
                mote   = MoteManagerImpl.getInstance().getMote(i.getMoteID());
                type   = MoteTypeManagerImpl.getInstance().
                                         getMoteType(mote.getMoteTypeID());
                mtba   = MoteTestbedAssignmentManagerImpl.getInstance().
                                         getMoteTestbedAssignment(mote.getID());

                log.info("Installing\n" +
                         " program:  " + i.getProgramID() + "\n" +
                         " on mote:  " + mote.getID()     + "\n" +
                         " type:     " + type.getName()   + "\n" +
                         " address:  " + mtba.getMoteAddress());


                installProgramInternal(mtba.getMoteAddress(),
                                       mote.getMoteSerialID(),
                                       type.getTosPlatform(),
                                       i.getProgramID(), output);
            }
        } catch (RemoteException ex) {
            throw ex;
        } catch (Exception ex) {
            String msg = "Exception in deployConfiguration";
            log.error(msg, ex);
            throw new RemoteException(msg, ex);
        }
    }


    public void resetMote(int moteAddress, String moteSerialID, int programID)
                                                        throws RemoteException {
        log.debug("Request to reset mote:\n" +
                  "moteAddress:  " + moteAddress  + "\n" +
                  "moteSerialID: " + moteSerialID);

        try {
            String         commPort   = "/dev/motes/" + moteSerialID;
            MoteComm       moteComm   = new MoteComm(moteAddress, commPort);
            ControlMessage controlMsg = new ControlMessage();

            controlMsg.set_cmd((short) ControlCommands.RESET.ordinal());

            moteComm.start();
            moteComm.send(controlMsg);
            moteComm.stop();

            setRadioPowerLevel(moteAddress, commPort, moteSerialID, programID);
        } catch (IOException ex) {
            throw new RemoteException("I/O Exception while trying to  " +
                                      "reset mote.", ex);
        }
    }
                          

    public void installProgram(int          moteAddress, String moteSerialID,
                               String       tosPlatform, int    programID,
                               StringBuffer output) throws RemoteException {
        installProgramInternal(moteAddress, moteSerialID,
                               tosPlatform, programID, output);
    }


    private void installProgramInternal(final int          moteAddress,
                                        final String       moteSerialID,
                                        final String       tosPlatform,
                                        final int          programID,
                                        final StringBuffer output) 
                                                       throws RemoteException {
        threadPool.execute(new InstallThread(moteAddress, moteSerialID,
                                             tosPlatform, programID,
                                             output));
    }

    private class InstallThread implements Runnable {
        private int          moteAddress;
        private String       moteSerialID;
        private String       tosPlatform;
        private int          programID;
        private StringBuffer output;
        private int          retryCount;


        public InstallThread(int          moteAddress, String moteSerialID,
                             String       tosPlatform, int    programID,
                             StringBuffer output)  {
            this.moteAddress  = moteAddress;
            this.moteSerialID = moteSerialID;
            this.tosPlatform  = tosPlatform;
            this.programID    = programID;
            this.output       = output;
            this.retryCount   = 0;
        }


        public void run() {
            try {
                log.debug("Installing\n" +
                          "    address:       " + moteAddress  + "\n" +
                          "    moteSerialID:  " + moteSerialID + "\n" +
                          "    tosPlatform:   " + tosPlatform  + "\n" +
                          "    programID:     " + programID    + "\n" +
                          "    retryCount:    " + retryCount);

                if (retryCount == 0) {
                    notifyObservers(Message.PROGRAM_INSTALL_BEGIN,
                                    moteSerialID);
                }

                try {
                    // Sleep randomly between 1 and 20 seconds
                    Thread.sleep(1000 * (1 + random.nextInt(20)));
                } catch (InterruptedException ex) { }

                Program         program;
                ProcessBuilder  processBuilder;
                String          commPort;

                commPort       = "/dev/motes/" + moteSerialID;
                program        = ProgramManagerImpl.getInstance().getProgram(
                                                                    programID);

                log.info("Installing:  " + INSTALL + " "
                                         + program.getSourcePath() + " "
                                         + tosPlatform + " "
                                         + moteAddress + " "
                                         + commPort);

                processBuilder = new ProcessBuilder(INSTALL,
                                                    program.getSourcePath(),
                                                    tosPlatform,
                                                    Integer.toString(moteAddress),
                                                    commPort);
                processBuilder.redirectErrorStream(true);


                Process process = processBuilder.start();
                output.append(getProcessOutput(process, null));

                process.waitFor();
                int exitValue = process.exitValue();

                if (exitValue == 0) {
                    setRadioPowerLevel(moteAddress,  commPort,
                                       moteSerialID, programID);
                    notifyObservers(Message.PROGRAM_INSTALL_SUCCESS,
                                    moteSerialID);
                } else {
                    log.error(moteSerialID + " -- Install failed with exit code: " + exitValue);
                    if (!maybeRetry()) {
                        notifyObservers(Message.PROGRAM_INSTALL_FAILURE,
                                        moteSerialID);
                    }
                }
            } catch (InterruptedException ex) {
                String msg = "process interrupted while waiting for " +
                             "install";
                log.error(msg, ex);

                if (!maybeRetry()) {
                    notifyObservers(Message.PROGRAM_INSTALL_FAILURE,
                                    moteSerialID);
                }
            } catch (IOException ex) {
                String msg = "I/O Exception while installing program";
                log.error(msg, ex);

                if (!maybeRetry()) {
                    notifyObservers(Message.PROGRAM_INSTALL_FAILURE,
                                    moteSerialID);
                }
            }
        }

        private boolean maybeRetry() {
            boolean retry = false;

            if (++retryCount < MAX_RETRY) {
                log.info("Install failed on mote " + moteAddress + " (" +
                         moteSerialID + ") -- retrying");

                retry = true;
                threadPool.execute(this);
            }

            return retry;
        }
    }


    private void setRadioPowerLevel(int    address,      String commPort,
                                    String moteSerialID, int    programID)
                                           throws RemoteException, IOException {
        log.debug("Setting radio power on mote:\n" +
                  "address:       " + address + "\n" +
                  "moteSerialID:  " + moteSerialID);

        ControlMessage controlMsg = new ControlMessage();
        Mote           mote       = MoteManagerImpl.getInstance().getMote(
                                                                  moteSerialID);
        int            moteID     = mote.getID();
        int            powerLevel = MoteDeploymentConfigurationManagerImpl.
                                          getInstance().
                                      getMoteDeploymentConfigurationByProgramID(
                                        moteID, programID).getRadioPowerLevel();
        if (powerLevel != MAX_POWER) {
            try { Thread.sleep(3000); } catch (Exception ex) { }
            MoteComm moteComm = new MoteComm(address, commPort);

            controlMsg.set_cmd((short) ControlCommands.SET_POWER.ordinal());
            controlMsg.set_arg((short) powerLevel);

            moteComm.start();
            moteComm.send(controlMsg);
            moteComm.stop();
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


    private ProgramDeploymentManagerImpl() throws RemoteException {
        super();

        this.threadPool = Executors.newFixedThreadPool(MAX_THREADS);
    }
}
