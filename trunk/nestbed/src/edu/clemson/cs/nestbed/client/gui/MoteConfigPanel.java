/* $Id$ */
/*
 * MoteConfigPanel.java
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
package edu.clemson.cs.nestbed.client.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
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
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JToolTip;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTypeManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


public class MoteConfigPanel extends MotePanel {
    private final static Log log = LogFactory.getLog(MoteConfigPanel.class);

    private final static Color  BG_COLOR         = Color.WHITE;
    private final static int    MIN_RADIO_POWER  =  1;
    private final static int    MAX_RADIO_POWER  = 31;
    private final static int    RADIO_POWER_INCR =  1;
    private final static int    LABEL_WIDTH      = 20;
    private final static int    LABEL_HEIGHT     = 20;
    private final static int    SPINNER_WIDTH    = 40;
    private final static int    SPINNER_HEIGHT   = 20;

    private ProgramManager                     programManager;
    private MoteDeploymentConfigurationManager mdcManager;
    private MoteTestbedAssignment              mtbAssignment;
    private Program                            program;
    private int                                projDepConfID;
    private Mote                               mote;
    private MoteType                           moteType;
    private MoteDeploymentConfiguration        moteDepConfig;
    private Image                              icon;
    private JSpinner                           radioSpinner;
    private ChangeListener                     radioChangeListener;


    public MoteConfigPanel(MoteTestbedAssignment              mtbAssignment,
                     ProgramManager                     programManager,
                     int                                projDepConfID,
                     MoteManager                        moteManager,
                     MoteTypeManager                    moteTypeManager,
                     MoteDeploymentConfigurationManager mdcManager)
                                throws RemoteException,
                                   NotBoundException, MalformedURLException {

        this.programManager = programManager;
        this.mtbAssignment  = mtbAssignment;
        this.projDepConfID  = projDepConfID;
        this.mote           = moteManager.getMote(mtbAssignment.getMoteID());
        this.moteType       = moteTypeManager.getMoteType(mote.getMoteTypeID());
        this.mdcManager     = mdcManager;
        this.moteDepConfig  = mdcManager.getMoteDeploymentConfiguration(
                                                projDepConfID, mote.getID());
        this.radioChangeListener = new RadioChangeListener();
        this.radioSpinner   = new JSpinner(
                                  new SpinnerNumberModel(MAX_RADIO_POWER,
                                                         MIN_RADIO_POWER,
                                                         MAX_RADIO_POWER,
                                                         RADIO_POWER_INCR));

        setIcon(new ImageIcon(moteType.getImage()).getImage());

        if (this.moteDepConfig != null) {
            this.program    = programManager.getProgram(
                                                moteDepConfig.getProgramID());
            radioSpinner.setValue(moteDepConfig.getRadioPowerLevel());
        } else {
            radioSpinner.setEnabled(false);
        }
        radioSpinner.setToolTipText("Radio Power Level");

        Dimension labelSize = new Dimension(LABEL_WIDTH, LABEL_HEIGHT);
        JLabel addressLabel = new JLabel("" + mtbAssignment.getMoteAddress());
        addressLabel.setSize(labelSize);
        addressLabel.setPreferredSize(labelSize);

        Dimension spinnerSize = new Dimension(SPINNER_WIDTH, SPINNER_HEIGHT);
        radioSpinner.setSize(spinnerSize);
        radioSpinner.setPreferredSize(spinnerSize);
        radioSpinner.addChangeListener(radioChangeListener);

        setToolTipText(getToolTipString());
        addMouseListener(new MotePanelMouseListener());

        add(addressLabel);
        addressLabel.setLocation(2, 0);

        add(radioSpinner);
        radioSpinner.setLocation(getWidth()  - SPINNER_WIDTH - 2,
                                 getHeight() - SPINNER_HEIGHT - 2);

        mdcManager.addRemoteObserver(new MoteDeploymentConfigObserver());
        new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,
                       new ProgramDropTarget(), true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (moteDepConfig == null) {
            paintDisabledPattern(g);
        }
    }


    public String getMoteTosPlatform() {
        return moteType.getTosPlatform();
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
    

    protected class ProgramDropTarget extends DropTargetAdapter {
        private final String MIME_TYPE =
                                    DataFlavor.javaJVMLocalObjectMimeType +
                                    ";class=edu.clemson.cs.nestbed."       +
                                    "common.model.Program";
        private DataFlavor programFlavor;


        public ProgramDropTarget() {
            try {
                this.programFlavor = new DataFlavor(MIME_TYPE);
            } catch (ClassNotFoundException ex) {
                log.error("ClassNotFoundException:\n", ex);
                ClientUtils.displayErrorMessage(MoteConfigPanel.this, ex);
            }
        }


        public void drop(DropTargetDropEvent e) {
            try {
                Transferable t = e.getTransferable();
                Program program = (Program) t.getTransferData(programFlavor);

                mdcManager.setMoteDeploymentConfiguration(projDepConfID,
                                mote.getID(), program.getID(), MAX_RADIO_POWER);
            } catch (UnsupportedFlavorException ex) {
                log.error("DataFlavor " + programFlavor + " unsupported", ex);
                ClientUtils.displayErrorMessage(MoteConfigPanel.this, ex);
            } catch (IOException ex) {
                log.error("I/O exception on data flavor " + programFlavor, ex);
                ClientUtils.displayErrorMessage(MoteConfigPanel.this, ex);
            }
        }
    }


    protected class RadioChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            try {
                int pow = Integer.parseInt(radioSpinner.getValue().toString());
                mdcManager.setMoteDeploymentConfiguration(projDepConfID,
                                                          mote.getID(),
                                                          program.getID(),
                                                          pow);
            } catch (RemoteException ex) {
                log.error("Remote exception while setting config", ex);
                ClientUtils.displayErrorMessage(MoteConfigPanel.this, ex);
            }
        }
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

                    moteDepConfig = mdConfig;
                    //
                    // This is a nasty hack.  We update the value of
                    // the spinner when we get an update; however, updating
                    // the spinner generates a new update event.  Here,
                    // we deregister the listener, update the component,
                    // then re-add the listener.
                    //
                    // We could have fixed this using some state variable
                    // to indicate we generated the update.  That might
                    // be a better solution than this.
                    //
                    synchronized(radioSpinner) {
                        radioSpinner.removeChangeListener(radioChangeListener);
                        radioSpinner.setEnabled(true);
                        radioSpinner.setValue(
                                moteDepConfig.getRadioPowerLevel());
                        radioSpinner.addChangeListener(radioChangeListener);
                    }
                    program       = programManager.getProgram(
                                            moteDepConfig.getProgramID());
                    setToolTipText(getToolTipString());
                    MoteConfigPanel.this.repaint();
                }
                break;

            case DELETE_CONFIG:
                log.debug("Mote deployment configuration removal");

                if (moteDepConfig != null) {
                    log.debug("moteDepConfig is not null\n" +
                              "id:          " + moteDepConfig.getID() + "\n" +
                              "mdConfig id: " + mdConfig.getID());

                    if (moteDepConfig.getID() == mdConfig.getID()) {
                        moteDepConfig = null;
                        // More hackery like above
                        synchronized(radioSpinner) {
                            radioSpinner.removeChangeListener(
                                                        radioChangeListener);
                            radioSpinner.setEnabled(false);
                            radioSpinner.setValue(MAX_RADIO_POWER);
                            radioSpinner.addChangeListener(radioChangeListener);
                        }
                        setToolTipText(getToolTipString());
                        MoteConfigPanel.this.repaint();
                    }
                } else {
                    log.warn("moteDepConfig is null");
                }
                break;

            default:
                log.error("Unknown message:  " + msg);
                break;
            }
        }
    }


    protected class MotePanelMouseListener extends MouseAdapter {
        private JPopupMenu menu;
        private JMenuItem  title;
        private JMenuItem  unconfigureMote;


        public MotePanelMouseListener() {
            menu            = new JPopupMenu();
            title           = new JMenuItem("Mote " +
                                        mtbAssignment.getMoteAddress() +
                                        " (" + mote.getMoteSerialID() + ")");
            unconfigureMote = new JMenuItem("Unconfigure Mote Deployment");

            title.setEnabled(false);
            menu.add(title);
            menu.add(new JSeparator());

            unconfigureMote.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        mdcManager.deleteMoteDeploymentConfiguration(
                                                        moteDepConfig.getID());
                    } catch (RemoteException ex) {
                        log.error("Remote Exception", ex);
                        ClientUtils.displayErrorMessage(MoteConfigPanel.this,
                                                        ex);
                    }
                }
            });

            menu.add(unconfigureMote);
        }


        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e) && moteDepConfig != null) {
                menu.show(MoteConfigPanel.this, e.getX(), e.getY());
            }
        }
    }
}
