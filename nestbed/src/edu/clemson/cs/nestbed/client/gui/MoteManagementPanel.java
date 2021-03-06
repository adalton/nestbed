/* $Id$ */
/*
 * MoteManagementPanel.java
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
package edu.clemson.cs.nestbed.client.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.deployment.ProgramDeploymentManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTypeManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramProbeManager;
import edu.clemson.cs.nestbed.common.management.power.MotePowerManager;
import edu.clemson.cs.nestbed.common.management.profiling.MessageManager;
import edu.clemson.cs.nestbed.common.management.sfcontrol.SerialForwarderManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.trace.StaticProgramData;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;

import edu.clemson.cs.nesctk.tools.trace.callgraph.CGVisualization;

/**
 * TODO:  Much of this is copied from MoteConfigPanel.java.  It would be nice
 *        to abstract what's in  common out to prevent this nasty code
 *        duplication.
 */
public class MoteManagementPanel extends MotePanel {
    private final static Log log = LogFactory.getLog(MoteManagementPanel.class);

    private final static String RMI_BASE_URL;
    private final static Color  BG_COLOR     = Color.WHITE;
    private final static int    LABEL_WIDTH  = 20;
    private final static int    LABEL_HEIGHT = 20;

    static {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");
    }


    private JMenuItem                          installProgram;

    private MessageManager                     messageManager;
    private ProgramManager                     programManager;
    private MoteDeploymentConfigurationManager mdcManager;
    private ProgramDeploymentManager           progDeployMgr;
    private MoteTestbedAssignment              mtbAssignment;
    private SerialForwarderManager             sfManager;
    private MotePowerManager                   motePowerManager;
    private ProgramProbeManager                programProbeManager;

    private Program                            program;
    private int                                projDepConfID;
    private Mote                               mote;
    private MoteType                           moteType;
    private MoteDeploymentConfiguration        moteDepConfig;
    private Image                              icon;
    private InstallAnimationThread             installAnimationThread;
    private boolean                            drawBorder;
    private boolean                            installedSuccessfully;


    public MoteManagementPanel(MoteTestbedAssignment            mtbAssignment,
                             ProgramManager                     programManager,
                             int                                projDepConfID,
                             MoteManager                        moteManager,
                             MoteTypeManager                    moteTypeManager,
                             MoteDeploymentConfigurationManager mdcManager)
                                throws RemoteException,
                                   NotBoundException, MalformedURLException {

        lookupRemoteManagers();

        this.programManager = programManager;
        this.mtbAssignment  = mtbAssignment;
        this.projDepConfID  = projDepConfID;
        this.mote           = moteManager.getMote(mtbAssignment.getMoteID());
        this.moteType       = moteTypeManager.getMoteType(mote.getMoteTypeID());
        this.mdcManager     = mdcManager;
        this.moteDepConfig  = mdcManager.getMoteDeploymentConfiguration(
                                                projDepConfID, mote.getID());

        setIcon(new ImageIcon(moteType.getImage()).getImage());

        if (this.moteDepConfig != null) {
            this.program = programManager.getProgram(moteDepConfig.getProgramID());
        }

        Dimension labelSize = new Dimension(LABEL_WIDTH, LABEL_HEIGHT);
        JLabel addressLabel = new JLabel("" + mtbAssignment.getMoteAddress());
        addressLabel.setSize(labelSize);
        addressLabel.setPreferredSize(labelSize);

        setToolTipText(getToolTipString());
        addMouseListener(new MotePanelMouseListener());

        add(addressLabel);
        addressLabel.setLocation(2, 0);

        progDeployMgr.addRemoteObserver(new ProgramInstallationObserver());
        mdcManager.addRemoteObserver(new MoteDeploymentConfigObserver());
    }


    private final void lookupRemoteManagers() throws NotBoundException,
                                                     MalformedURLException,
                                                     RemoteException {
        messageManager   = (MessageManager) Naming.lookup(
                            RMI_BASE_URL + "MessageManager");

        progDeployMgr    = (ProgramDeploymentManager) Naming.lookup(
                            RMI_BASE_URL + "ProgramDeploymentManager");

        sfManager        = (SerialForwarderManager) Naming.lookup(
                            RMI_BASE_URL + "SerialForwarderManager");

        motePowerManager = (MotePowerManager) Naming.lookup(
                            RMI_BASE_URL + "MotePowerManager");

        programProbeManager = (ProgramProbeManager) Naming.lookup(
                            RMI_BASE_URL + "ProgramProbeManager");
    }

    public Program getProgram() {
        return program;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (moteDepConfig == null) {
            paintDisabledPattern(g);
        } else if (drawBorder) {
            Color oldColor = g.getColor();
            Color newColor = (   installedSuccessfully
                              || (installAnimationThread != null))
                              ? Color.green : Color.red;
            int   arcSize  = 4;

            g.setColor(newColor);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1,
                            arcSize, arcSize);
            g.setColor(oldColor);
        }
    }


    private String getToolTipString() {
        StringBuffer tip = new StringBuffer(
               "<html><table border=0 cellspacing=0 cellpadding=1>" +
               "<tr>"      +
               "    <th align=left>"  + "Address:  "        + "</th>" +
               "    <td>"  + mtbAssignment.getMoteAddress() + "</td>" +
               "</tr><tr>" +
               "    <th align=left>"  + "Serial Number:  "  + "</th>" +
               "    <td>"  + mote.getMoteSerialID()         + "</td>" +
               "</tr><tr>" +
               "    <th align=left>"  + "Type:  "           + "</th>" +
               "    <td>"  + moteType.getName()             + "</td>" +
               "</tr><tr>" +
               "    <th align=left>"  + "Total ROM:  "      + "</th>" +
               "    <td>"  + moteType.getTotalROM()         + "</td>" +
               "</tr><tr>" +
               "    <th align=left>"  + "Total RAM:  "      + "</th>" +
               "    <td>"  + moteType.getTotalRAM()         + "</td>" +
               "</tr><tr>" +
               "    <th align=left>"  + "Total EEPROM:  "   + "</th>" +
               "    <td>"  + moteType.getTotalEEPROM()      + "</td>" +
               "</tr><tr>");

        if (moteDepConfig != null) {
            tip.append(
               "<th align=left>" + "Program Name: "      + "</th>" +
               "<td>"            + program.getName()     + "</td>" +
               "</tr><tr>"       +
               "<th align=left>" + "Radio Power Level: " + "</th>" +
               "<td>"            + moteDepConfig.getRadioPowerLevel() +
               "</td>");
        } else {
            tip.append("<th align=left colspan=2>Unconfigured</th>");
        }

        tip.append("</tr></table></html>");

        return tip.toString();
    }
    

    protected class MoteDeploymentConfigObserver extends    UnicastRemoteObject
                                                 implements RemoteObserver {
        public MoteDeploymentConfigObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            MoteDeploymentConfigurationManager.Message message;
            MoteDeploymentConfiguration                mdConfig;

            message  = (MoteDeploymentConfigurationManager.Message) msg;
            mdConfig = (MoteDeploymentConfiguration)                arg;

            switch (message) {
            case NEW_CONFIG:
                if (   (mdConfig.getMoteID() == mote.getID())
                    && (   (moteDepConfig    == null)
                        || (mdConfig.getID() == moteDepConfig.getID())
                        || (mdConfig.getProjectDeploymentConfigurationID()
                                                          == projDepConfID))) {
                    log.debug("Adding new/updating mote deployment " +
                              "configuration");

                    moteDepConfig         = mdConfig;
                    program               = programManager.getProgram(
                                                moteDepConfig.getProgramID());

                    if (installAnimationThread != null) {
                        installAnimationThread.done();
                    }
                    installedSuccessfully = false;
                    drawBorder            = false;

                    setToolTipText(getToolTipString());
                    MoteManagementPanel.this.repaint();
                }
                break;

            case DELETE_CONFIG:
                log.debug("Mote deployment configuration removal");

                if (        (moteDepConfig != null)
                         && (moteDepConfig.getID() == mdConfig.getID())) {
                    moteDepConfig = null;

                    if (installAnimationThread != null) {
                        installAnimationThread.done();
                    }
                    installedSuccessfully = false;
                    drawBorder            = false;

                    setToolTipText(getToolTipString());
                    MoteManagementPanel.this.repaint();

                }
                break;

            default:
                log.error("Unknoqn message:  " + msg);
                break;
            }
        }
    }


    protected class ProgramInstallationObserver extends    UnicastRemoteObject
                                                implements RemoteObserver {
        public ProgramInstallationObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProgramDeploymentManager.Message message;
            message = (ProgramDeploymentManager.Message) msg;

            switch (message) {
            case PROGRAM_INSTALL_BEGIN: {
                String moteSerialID = arg.toString();

                if (moteSerialID.equals(mote.getMoteSerialID())) {
                    log.info("Installing program on device: " + moteSerialID);
                    installAnimationThread = new InstallAnimationThread();
                    installAnimationThread.start();
                }
                break;
            }

            case PROGRAM_INSTALL_SUCCESS: {
                String moteSerialID = arg.toString();
                installProgram.setEnabled(true);

                if (moteSerialID.equals(mote.getMoteSerialID())) {
                    log.info("Program installed successfully on device: " +
                             moteSerialID);
                    installAnimationThread.done();
                    installedSuccessfully = true;
                    MoteManagementPanel.this.repaint();
                }
                break;
            }

            case PROGRAM_INSTALL_FAILURE: {
                String moteSerialID = arg.toString();

                installProgram.setEnabled(true);

                if (moteSerialID.equals(mote.getMoteSerialID())) {
                    log.warn("Program install FAILURE on device: " +
                             moteSerialID);
                    installAnimationThread.done();
                    installedSuccessfully = false;
                    MoteManagementPanel.this.repaint();
                }
                break;
            }
            }
        }
    }


    protected class MotePanelMouseListener extends MouseAdapter {
        private JPopupMenu menu;
        private JMenuItem  title;
        private JMenuItem  viewMoteDetails;
        private JMenuItem  resetMote;
        private JMenuItem  powerOff;
        private JMenuItem  powerOn;
        private JMenuItem  runSerialForwarder;
        private JMenuItem  viewCallGraph;


        public MotePanelMouseListener() {
            menu               = new JPopupMenu();
            title              = new JMenuItem("Mote " +
                                           mtbAssignment.getMoteAddress() +
                                           " (" + mote.getMoteSerialID() + ")");
            viewMoteDetails    = new JMenuItem("View Mote Details");
            runSerialForwarder = new JMenuItem("Create Gateway");
            installProgram     = new JMenuItem();
            resetMote          = new JMenuItem("Reset Mote");
            powerOff           = new JMenuItem("Power Off");
            powerOn            = new JMenuItem("Power On");
            viewCallGraph      = new JMenuItem("View Call Graph");

            title.setEnabled(false);
            menu.add(title);
            menu.add(new JSeparator());

            installProgram.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        installProgram.setEnabled(false);

                        messageManager.disable(mote.getID());
                        progDeployMgr.installProgram(
                                        mtbAssignment.getMoteAddress(),
                                        mote.getMoteSerialID(),
                                        moteType.getTosPlatform(),
                                        moteDepConfig.getProgramID(),
                                        new StringBuffer());

                    } catch (RemoteException ex) {
                        log.error("Remote Exception", ex);
                        ClientUtils.displayErrorMessage(MoteManagementPanel.this, ex);
                    }
                }
            });
            menu.add(installProgram);


            viewMoteDetails.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        MoteDetailFrame moteDetailFrame =
                                new MoteDetailFrame(projDepConfID,
                                                    mtbAssignment, mote,
                                                    moteType, program);
                        moteDetailFrame.setVisible(true);
                    } catch (Exception ex) {
                        log.error("Exception", ex);
                        ClientUtils.displayErrorMessage(MoteManagementPanel.this, ex);
                    }
                }
            });
            menu.add(viewMoteDetails);


            resetMote.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int choice;

                        choice = JOptionPane.showConfirmDialog(
                                    MoteManagementPanel.this,
                                    "Are you sure you want to reset mote " +
                                    mtbAssignment.getMoteAddress(),
                                    "Reset Confirmation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
                        if (choice == JOptionPane.YES_OPTION) {
                            progDeployMgr.resetMote(
                                                mtbAssignment.getMoteAddress(),
                                                mote.getMoteSerialID(),
                                                program.getID());
                        }

                    } catch (Exception ex) {
                        log.error("Exception\n", ex);
                        ClientUtils.displayErrorMessage(MoteManagementPanel.this, ex);
                    }
                }
            });
            menu.add(resetMote);

            powerOff.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int choice;

                        choice = JOptionPane.showConfirmDialog(
                                    MoteManagementPanel.this,
                                    "Are you sure you want to power off mote " +
                                    mtbAssignment.getMoteAddress(),
                                    "Power Down Confirmation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
                        if (choice == JOptionPane.YES_OPTION) {
                            motePowerManager.powerOff(mote.getID());
                        }

                    } catch (Exception ex) {
                        log.error("Exception\n", ex);
                        ClientUtils.displayErrorMessage(MoteManagementPanel.this, ex);
                    }
                }
            });
            menu.add(powerOff);

            powerOn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        motePowerManager.powerOn(mote.getID());
                    } catch (Exception ex) {
                        log.error("Exception\n", ex);
                        ClientUtils.displayErrorMessage(MoteManagementPanel.this, ex);
                    }
                }
            });
            menu.add(powerOn);

            runSerialForwarder.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (sfManager.isSerialForwarderEnabled(mote.getID())) {
                            sfManager.disableSerialForwarder(mote.getID());
                            runSerialForwarder.setText("Create Gateway");
                        } else {
                            sfManager.enableSerialForwarder(
                                                  mote.getID(),
                                                  mtbAssignment.getMoteAddress());
                            runSerialForwarder.setText("Destroy Gateway");
                        }
                    } catch (Exception ex) {
                        log.error("Exception\n", ex);
                        ClientUtils.displayErrorMessage(MoteManagementPanel.this, ex);
                    }
                }
            });
            menu.add(runSerialForwarder);


            viewCallGraph.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (program != null) {
                            StaticProgramData spd = programProbeManager.getStaticData(program);
                            CGVisualization viz = new CGVisualization(spd.getModules(),
                                                                      spd.getFunctions(),
                                                                      spd.getCallMap(),
                                                                      spd.getWirings());
                            viz.showSelectionGui();
                        }
                    } catch (Exception ex) {
                        log.error("Exception\n", ex);
                        ClientUtils.displayErrorMessage(MoteManagementPanel.this, ex);
                    }
                }
            });
            menu.add(viewCallGraph);
        }


        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e) && moteDepConfig != null) {
                installProgram.setText("Install Program '" +
                                       program.getName() + "'");
                //ARD viewMoteDetails.setEnabled(installedSuccessfully);
                menu.show(MoteManagementPanel.this, e.getX(), e.getY());
            }
        }
    }


    public class InstallAnimationThread extends Thread {
        private int     SECOND = 1000;
        private boolean isDone = false;


        public void run() {
            while (!isDone) {
                drawBorder = !drawBorder;
                MoteManagementPanel.this.repaint();
                try { Thread.sleep(1 * SECOND); } catch (Exception ex) { }
            }
            drawBorder = true;
            MoteManagementPanel.this.repaint();
        }


        public void done() {
            isDone = true;
            interrupt();
            installAnimationThread = null;
        }
    }
}
