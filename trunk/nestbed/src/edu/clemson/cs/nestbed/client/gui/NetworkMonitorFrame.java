/* $Id$ */
/*
 * NetworkMonitorFrame.java
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


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramProbeManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.trace.StaticProgramData;
import edu.clemson.cs.nestbed.client.gui.webcam.WebCam;

import edu.clemson.cs.nesctk.records.*;
import edu.clemson.cs.nesctk.tools.trace.sequencediagram.Entry;
import edu.clemson.cs.nesctk.tools.trace.sequencediagram.MaxSubsequence;
import edu.clemson.cs.nesctk.tools.trace.sequencediagram.SubsequenceViewFrame;
import edu.clemson.cs.nesctk.tools.trace.sequencediagram.TabbedViewFrame;
import edu.clemson.cs.nesctk.tools.trace.distributedviz.GlobalGraph;
import edu.clemson.cs.nesctk.tools.trace.FunctionTraceData;
import edu.clemson.cs.nesctk.tools.trace.RadioTraceData;
import edu.clemson.cs.nesctk.tools.trace.TraceData;


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
    private GridPanel                             gridPanel;

    private ProgramManager                        programManager;
    private MoteManager                           moteManager;
    private MoteTypeManager                       moteTypeManager;
    private MoteTestbedAssignmentManager          mtbaManager;
    private MoteDeploymentConfigurationManager    moteDepConfMgr;
    private ProjectDeploymentConfigurationManager configManager;
    private ProgramDeploymentManager              progDeployMgr;
    private ProgramProbeManager                   programProbeManager;


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

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(buildMoteGridPanel(), BorderLayout.CENTER);

        setJMenuBar(buildMenuBar());
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

        programProbeManager = (ProgramProbeManager) Naming.lookup(
                           RMI_BASE_URL + "ProgramProbeManager");
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
        final JMenuItem vizMenu = new JMenuItem("Visualizations");

        install.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread() { public void run() {
                    try {
                        install.setEnabled(false);
                        progDeployMgr.deployConfiguration(config.getID());
                    } catch (Exception ex) {
                        log.error("Exception occured while installing " +
                                  "programs.", ex);
                        ClientUtils.displayErrorMessage(
                                            NetworkMonitorFrame.this, ex);
                    } finally {
                        install.setEnabled(true);
                    }
                }}.start();
            }
        });
        menu.add(install);
        menu.add(new JSeparator());


        vizMenu.addActionListener(new VisualizationMenuListener());
        menu.add(vizMenu);

        return menu;
    }


    class VisualizationMenuListener implements ActionListener {
        private NodeSelectionDialog           nsd;
        private Program                       program;
        private StaticProgramData             staticData;
        private Map<Integer, List<TraceData>> dynamicData = new TreeMap<Integer, List<TraceData>>();
        private CheckBoxActionListener        cbl         = new CheckBoxActionListener();




        private void getNewData() throws Exception {
            List<MoteTestbedAssignment> interestingMotes = getSelected(nsd.getSelected());

            if (interestingMotes.size() > 0) {
                Map<Integer, List<TraceData>> newDynamicData;
                progDeployMgr.installTraceReceiver(interestingMotes);
                newDynamicData = programProbeManager.collectData(interestingMotes);
                dynamicData.putAll(newDynamicData);
            }

        }

        private List<MoteTestbedAssignment> getSelected(List<Integer> selected) {
            List<MoteTestbedAssignment> interestingMotes = new ArrayList<MoteTestbedAssignment>();

            for (MoteTestbedAssignment mta : mtbAssignmentData) {
                for (Integer i : selected) {
                    if (dynamicData.get(i) == null) {
                        if (mta.getMoteAddress() == i.intValue()) {
                            interestingMotes.add(mta);
                        }
                    }
                }
            }


            return interestingMotes;
        }

        private StaticProgramData staticFilter() {
            Map<Integer, List<Integer>>     selectedIds  = nsd.getIdModuleIncludeMap();
            List<TraceModuleRecord>         modules      = new ArrayList<TraceModuleRecord>();
            List<TraceFunctionRecord>       functions    = new ArrayList<TraceFunctionRecord>();

            for (Integer moduleId : selectedIds.keySet()) {
                String moduleName = null;

                for (TraceModuleRecord tmr : staticData.getModules()) {
                    if (tmr.getTraceId() == moduleId.intValue()) {
                        modules.add(tmr);
                        moduleName = tmr.getModuleName();
                        break;
                    }
                }

                for (Integer functionId : selectedIds.get(moduleId)) {
                    for (TraceFunctionRecord tfr : staticData.getFunctions()) {
                        if (tfr.getTraceId() == functionId.intValue() && tfr.getModuleName().endsWith(moduleName)) {
                            functions.add(tfr);
                            break;
                        }
                    }
                }
            }

            return new StaticProgramData(modules, functions, staticData.getCallMap(), staticData.getWirings());
        }


        private Map<Integer, List<TraceData>> dynamicFilter() {
            List<Integer>                 selectedNodes = nsd.getSelected();
            Map<Integer, List<Integer>>   selectedIds   = nsd.getIdModuleIncludeMap();
            Map<Integer, List<TraceData>> filteredData  = new TreeMap<Integer, List<TraceData>>();

            for (Integer nodeId : dynamicData.keySet()) {
                if (selectedNodes.contains(nodeId)) {
                    //for (TraceData traceData : dynamicData.get(nodeId))
                    for (int i = 0; i < dynamicData.get(nodeId).size(); ++i) {
                        if (i >= nsd.getStartIndex() && i <= nsd.getEndIndex()) {
                            TraceData traceData = dynamicData.get(nodeId).get(i);

                            if (traceData instanceof FunctionTraceData) {
                                FunctionTraceData functionTraceData = (FunctionTraceData) traceData;

                                List<Integer> functionList = selectedIds.get(functionTraceData.getModuleId());
                                if (functionList != null) {
                                    if (functionList.contains(functionTraceData.getFunctionId())) {
                                        addTrace(nodeId, filteredData, traceData);
                                    }
                                }
                            } else if (traceData instanceof RadioTraceData) {
                                RadioTraceData radioTraceData = (RadioTraceData) traceData;

                                if (radioTraceData.getType() == 2) { // Send
                                    addTrace(nodeId, filteredData, traceData);
                                } else { // Receive
                                    if (selectedNodes.contains(radioTraceData.getAddress())) {
                                        addTrace(nodeId, filteredData, traceData);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return filteredData;
        }

        private void addTrace(Integer nodeId, Map<Integer, List<TraceData>> filteredData, TraceData traceData) {
            List<TraceData> traceDataList = filteredData.get(nodeId);
            if (traceDataList == null) {
                traceDataList = new ArrayList<TraceData>();
                filteredData.put(nodeId, traceDataList);
            }
            traceDataList.add(traceData);
        }


        public void actionPerformed(ActionEvent e) {
            try {
                Program[] programs = programManager.getProgramList(config.getProjectID()).toArray(new Program[0]);

                program = (Program)
                        JOptionPane.showInputDialog(NetworkMonitorFrame.this,
                                                    "Select the program to visualize:",
                                                    "Program Selection",
                                                    JOptionPane.QUESTION_MESSAGE,
                                                    null, // No custom icon
                                                    programs,
                                                    programs[0]);

                staticData = programProbeManager.getStaticData(program);

                nsd = new NodeSelectionDialog(gridPanel,
                                              program,
                                              staticData.getModules(),
                                              staticData.getFunctions(),
                                              staticData.getCallMap());


                nsd.setCheckBoxListener(cbl);

                nsd.addSequenceDiagramActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Thread() { public void run() {
                            try {
                                Map<Integer, List<TraceData>> filteredDynamicData;
                                StaticProgramData             filteredStaticData;

                                filteredDynamicData = dynamicFilter();
                                filteredStaticData  = staticFilter();

                                System.out.printf("Modules:   %s\n", filteredStaticData.getModules().toString());
                                System.out.printf("Functions: %s\n", filteredStaticData.getFunctions().toString());

                                TabbedViewFrame tvf = new TabbedViewFrame(filteredStaticData.getModules(),
                                                                          filteredStaticData.getFunctions(),
                                                                          filteredStaticData.getCallMap(),
                                                                          filteredStaticData.getWirings(),
                                                                          filteredDynamicData);
                                tvf.showFrame();
                            } catch (Exception ex) {
                                ClientUtils.displayErrorMessage(NetworkMonitorFrame.this, ex);
                            }
                        }}.start();
                    }
                });

                nsd.addSubSequenceDiagramActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Thread() { public void run() {
                            try {
                                Map<Integer, List<TraceData>> filteredDynamicData;
                                StaticProgramData             filteredStaticData;

                                filteredDynamicData = dynamicFilter();
                                filteredStaticData  = staticFilter();

                                System.out.printf("Modules:   %s\n", filteredStaticData.getModules().toString());
                                System.out.printf("Functions: %s\n", filteredStaticData.getFunctions().toString());
                                MaxSubsequence ms = new MaxSubsequence(filteredDynamicData);
                                List<Map<Integer, List<TraceData>>> allSequences = ms.generateAllSubsequences();

                                SubsequenceViewFrame svf = new SubsequenceViewFrame(filteredStaticData.getModules(),
                                                                          filteredStaticData.getFunctions(),
                                                                          filteredStaticData.getCallMap(),
                                                                          filteredStaticData.getWirings(),
                                                                          new ArrayList<Integer>(filteredDynamicData.keySet()),
                                                                          allSequences);
                                svf.showFrame();
                            } catch (Exception ex) {
                                ClientUtils.displayErrorMessage(NetworkMonitorFrame.this, ex);
                            }
                        }}.start();
                    }
                });

                nsd.addGlobalViewActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Thread() { public void run() {
                            try {
                                Map<Integer, List<TraceData>> filteredDynamicData;

                                filteredDynamicData = dynamicFilter();

                                GlobalGraph globalGraph = new GlobalGraph(filteredDynamicData);
                                JFrame      frame       = new JFrame("Global Event Graph");
                                frame.getContentPane().add(globalGraph, BorderLayout.CENTER);
                                frame.setSize(700, 800);
                                frame.setVisible(true);
                            } catch (Exception ex) {
                                ClientUtils.displayErrorMessage(NetworkMonitorFrame.this, ex);
                            }
                        }}.start();
                    }
                });

                nsd.setVisible(true);
            } catch (Exception ex) {
                ClientUtils.displayErrorMessage(NetworkMonitorFrame.this, ex);
            }
        }

        class CheckBoxActionListener implements ActionListener {
            private Object lock = new Object();

            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        synchronized(lock) {
                            try {
                                int max = 0;

                                getNewData();

                                for (Integer i : nsd.getSelected()) {
                                    if ( (dynamicData.get(i) != null) && (dynamicData.get(i).size() > max) ) {
                                        max = dynamicData.get(i).size();
                                    }
                                }

                                nsd.setStartRange(0, max);
                                nsd.setSlidersEnabled(max > 0);
                            } catch (Exception ex) {
                                ClientUtils.displayErrorMessage(NetworkMonitorFrame.this, ex);
                            }
                        }
                    }
                }.start();
            }
        }
    }


    private final JComponent buildMoteGridPanel() throws RemoteException,
                                    NotBoundException, MalformedURLException {
        gridPanel = buildGridPanel();
            
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
