/* $Id$ */
/*
 * NetworkMonitorFrame.java
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
package edu.clemson.cs.nestbed.client.gui;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTestbedAssignmentManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTypeManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProjectDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.deployment.ProgramDeploymentManager;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.client.gui.webcam.WebCam;


public class NetworkMonitorFrame extends JFrame {
    private final static Log log = LogFactory.getLog(NetworkMonitorFrame.class);

    private final static String RMI_BASE_URL;
    private final static int    WINDOW_WIDTH  = 800;
    private final static int    WINDOW_HEIGHT = 600;

    static {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");
    }


    private Testbed                               testbed;
    private Project                               project;
    private ProjectDeploymentConfiguration        config;
    private List<MoteTestbedAssignment>           mtbAssignmentData;

    private ProgramManager                        programManager;
    private MoteManager                           moteManager;
    private MoteTypeManager                       moteTypeManager;
    private MoteTestbedAssignmentManager          mtbaManager;
    private MoteDeploymentConfigurationManager    moteDepConfMgr;
    private ProjectDeploymentConfigurationManager configManager;
    private ProgramDeploymentManager              progDeployMgr;


    public NetworkMonitorFrame(Testbed testbed, Project project,
                               ProjectDeploymentConfiguration config)
                                                throws RemoteException,
                                                       NotBoundException,
                                                       MalformedURLException,
                                                       ClassNotFoundException {
        super(testbed.getName() + " Network Monitor");

        this.testbed = testbed;
        this.project = project;
        this.config  = config;

        lookupRemoteManagers();
        createComponents();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setJMenuBar(buildMenuBar());

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(buildMoteGridPanel(), BorderLayout.CENTER);
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {

        programManager  = (ProgramManager) Naming.lookup(
                           RMI_BASE_URL + "ProgramManager");

        moteManager     = (MoteManager) Naming.lookup(
                           RMI_BASE_URL + "MoteManager");

        moteTypeManager = (MoteTypeManager) Naming.lookup(
                           RMI_BASE_URL + "MoteTypeManager");

        mtbaManager     = (MoteTestbedAssignmentManager) Naming.lookup(
                           RMI_BASE_URL + "MoteTestbedAssignmentManager");

        moteDepConfMgr  = (MoteDeploymentConfigurationManager) Naming.lookup(
                           RMI_BASE_URL + "MoteDeploymentConfigurationManager");

        configManager   = (ProjectDeploymentConfigurationManager) Naming.lookup(
                           RMI_BASE_URL +
                                    "ProjectDeploymentConfigurationManager");

        progDeployMgr  = (ProgramDeploymentManager) Naming.lookup(
                           RMI_BASE_URL + "ProgramDeploymentManager");
    }


    private final void createComponents() throws RemoteException {
        mtbAssignmentData = mtbaManager.getMoteTestbedAssignmentList(
                                                            testbed.getID());
    }


    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(buildFileMenu());
        menuBar.add(buildNetworkMenu());

        return menuBar;
    }


    private final JMenu buildFileMenu() {
        JMenu     menu  = new JMenu("File");
        JMenuItem video = new JMenuItem("Video");
        JMenuItem close = new JMenuItem("Close");

        video.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WebCam cam = new WebCam("nestbed.cs.clemson.edu", 9192);
            }
        });
        menu.add(video);

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NetworkMonitorFrame.this.setVisible(false);
            }
        });
        menu.add(new JSeparator());
        menu.add(close);

        return menu;
    }


    private final JMenu buildNetworkMenu() {
        final JMenu     menu    = new JMenu("Network");
        final JMenuItem install = new JMenuItem("Install Programs");

        install.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        try {
                            install.setEnabled(false);
                            progDeployMgr.deployConfiguration(config.getID());
                            //configManager.deployConfiguration(config.getID());
                        } catch (Exception ex) {
                            log.error("Exception occured while installing " +
                                      "programs.", ex);
                            ClientUtils.displayErrorMessage(
                                                NetworkMonitorFrame.this, ex);
                        } finally {
                            install.setEnabled(true);
                        }
                    }
                }.start();
            }
        });

        menu.add(install);

        return menu;
    }

    private final JComponent buildMoteGridPanel() throws RemoteException,
                                    NotBoundException, MalformedURLException {
        GridPanel gridPanel = buildGridPanel();
            
        for (MoteTestbedAssignment i : mtbAssignmentData) {
            int                 row       = i.getMoteLocationY();
            int                 col       = i.getMoteLocationX();
            MoteManagementPanel motePanel = new MoteManagementPanel(i,
                                               programManager, config.getID(),
                                               moteManager, moteTypeManager,
                                               moteDepConfMgr);
            gridPanel.addPanel(motePanel, row, col);
        }

        JScrollPane scrollPane;
        scrollPane = new JScrollPane(gridPanel);
        scrollPane.setViewportBorder(new TitledBorder("Testbed Topology"));

        return scrollPane;
    }


    private final GridPanel buildGridPanel() {
        int maxRow = -1;
        int maxCol = -1;

        for (MoteTestbedAssignment i : mtbAssignmentData) {
            int x = i.getMoteLocationX();
            int y = i.getMoteLocationY();

            if (y > maxRow) {
                maxRow = y;
            }

            if (x > maxCol) {
                maxCol = x;
            }
        }

        return new GridPanel(maxRow + 1, maxCol + 1);
    }
}
