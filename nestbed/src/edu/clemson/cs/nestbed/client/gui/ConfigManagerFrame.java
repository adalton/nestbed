/* $Id$ */
/*
 * ConfigManagerFrame.java
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


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dotuseful.ui.tree.SortedTreeNode;

import edu.clemson.cs.nestbed.common.management.configuration.MoteDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTestbedAssignmentManager;
import edu.clemson.cs.nestbed.common.management.configuration.MoteTypeManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramCompileManager;
import edu.clemson.cs.nestbed.common.management.instrumentation.ProgramWeaverManager;
import edu.clemson.cs.nestbed.common.model.MoteDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;
import edu.clemson.cs.nestbed.common.util.ZipUtils;


public class ConfigManagerFrame extends JFrame {
    private final static Log log = LogFactory.getLog(ConfigManagerFrame.class);

    private final static String RMI_BASE_URL;
    private final static int    WINDOW_WIDTH  = 1000;
    private final static int    WINDOW_HEIGHT = 650;
    private final static int    SIZE_IGNORED  = 0;

    static {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");
    }

    private Testbed                              testbed;
    private Project                              project;
    private ProjectDeploymentConfiguration       config;
    private String                               tosPlatform;

    private ProgramManager                       programManager;
    private MoteTestbedAssignmentManager         mtbaManager;
    private MoteManager                          moteManager;
    private MoteTypeManager                      moteTypeManager;
    private MoteDeploymentConfigurationManager   moteDepConfMgr;
    private ProgramProfilingSymbolManager        progProfManager;
    private ProgramSymbolManager                 programSymbolManager;
    private ProgramProfilingMessageSymbolManager progProfMsgSymManager;
    private ProgramMessageSymbolManager          progMsgSymManager;
    private ProgramCompileManager                programCompileManager;
    private ProgramWeaverManager                 programWeaverManager;

    private List<MoteTestbedAssignment>          mtbAssignmentData;

    private JTree                                programTree;
    private JTable                               profilingSymbolTable;
    private JTable                               profilingMsgTable;

    private boolean                              compiling;
    private OutputWindow                         outputWindow;


    public ConfigManagerFrame(Testbed testbed, Project project,
                              ProjectDeploymentConfiguration config)
                                                throws RemoteException,
                                                       NotBoundException,
                                                       MalformedURLException,
                                                       ClassNotFoundException {
        super(testbed.getName() + " Deployment Configuration Manager");

        this.testbed = testbed;
        this.project = project;
        this.config  = config;

        lookupRemoteManagers();
        // TODO:  We need to deregister listeners
        programCompileManager.addRemoteObserver(new ProgramCompileObserver());
        programManager.addRemoteObserver(new ProgramObserver());
        progProfManager.addRemoteObserver(new ProgramProfilingSymbolObserver());
        progProfMsgSymManager.addRemoteObserver(
                                new ProgramProfilingMessageSymbolObserver());


        createComponents();
        programTree.addMouseListener(new ProgramTreeMouseListener());
        programTree.setCellRenderer(new ProgramTreeCellRenderer());
        programTree.setRootVisible(false);
        programTree.setShowsRootHandles(true);
        programTree.setDragEnabled(true);
        programTree.setTransferHandler(new ProgramTreeTransferHandler());
        programTree.getSelectionModel().setSelectionMode(
                                TreeSelectionModel.SINGLE_TREE_SELECTION);


        profilingSymbolTable.addMouseListener(
                                      new ProfilingSymbolTableMouseListener());
        profilingSymbolTable.setModel(new ProfilingSymbolTableModel());
        profilingSymbolTable.setSelectionMode(
                                        ListSelectionModel.SINGLE_SELECTION);
        new DropTarget(profilingSymbolTable, DnDConstants.ACTION_COPY_OR_MOVE,
                       new ProgramSymbolDropTarget(), true);



        profilingMsgTable.addMouseListener(
                                     new ProfilingMessageTableMouseListener());
        profilingMsgTable.setModel(new ProfilingMessageSymbolTableModel());
        profilingMsgTable.setSelectionMode(
                                        ListSelectionModel.SINGLE_SELECTION);
        new DropTarget(profilingMsgTable, DnDConstants.ACTION_COPY_OR_MOVE,
                       new ProgramMessageSymbolDropTarget(), true);



        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setJMenuBar(buildMenuBar());

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JSplitPane top   = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                     buildLeftPanel(), buildMoteGridPanel());

        JSplitPane panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                     top, buildBottomPanel());

        c.add(panel, BorderLayout.CENTER);
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {

        programManager        = (ProgramManager) Naming.lookup(
                                  RMI_BASE_URL + "ProgramManager"); 

        mtbaManager           = (MoteTestbedAssignmentManager) Naming.lookup(
                                  RMI_BASE_URL + "MoteTestbedAssignmentManager");

        moteManager           = (MoteManager) Naming.lookup(
                                  RMI_BASE_URL + "MoteManager");

        moteTypeManager       = (MoteTypeManager) Naming.lookup(
                                  RMI_BASE_URL + "MoteTypeManager");

        progProfManager       = (ProgramProfilingSymbolManager) Naming.lookup(
                                 RMI_BASE_URL + "ProgramProfilingSymbolManager");

        programSymbolManager  = (ProgramSymbolManager) Naming.lookup(
                                 RMI_BASE_URL + "ProgramSymbolManager");

        progMsgSymManager     = (ProgramMessageSymbolManager) Naming.lookup(
                                 RMI_BASE_URL + "ProgramMessageSymbolManager");

        progProfMsgSymManager = (ProgramProfilingMessageSymbolManager)
                                     Naming.lookup( RMI_BASE_URL +
                                    "ProgramProfilingMessageSymbolManager");

        moteDepConfMgr        = (MoteDeploymentConfigurationManager)
                                     Naming.lookup(RMI_BASE_URL +
                                           "MoteDeploymentConfigurationManager");

        programCompileManager = (ProgramCompileManager)
                                     Naming.lookup(RMI_BASE_URL +
                                                       "ProgramCompileManager");

        programWeaverManager  = (ProgramWeaverManager)
                                     Naming.lookup(RMI_BASE_URL +
                                                       "ProgramWeaverManager");
    }


    private final void createComponents() throws RemoteException {
        profilingSymbolTable = new JTable();
        profilingMsgTable    = new JTable();
        programTree          = new JTree();
        buildProgramTreeNodes();
        mtbAssignmentData = mtbaManager.getMoteTestbedAssignmentList(
                                                            testbed.getID());
    }


    private void buildProgramTreeNodes() throws RemoteException {
        List<Program>  programList;
        SortedTreeNode root;

        programList = programManager.getProgramList(project.getID());
        root        = new SortedTreeNode("root", new TreeComparator());

        for (Program i : programList) {
            Map<String, SortedTreeNode> moduleMap;
            SortedTreeNode              programNode;
            SortedTreeNode              messagesNode;
            SortedTreeNode              symbolsNode;
            List<ProgramMessageSymbol>  programMsgSymbolList;
            List<ProgramSymbol>         programSymbolList;

            programMsgSymbolList = progMsgSymManager.getProgramMessageSymbols(
                                                                    i.getID());

            programNode  = new SortedTreeNode(i, new TreeComparator());
            messagesNode = new SortedTreeNode("Messages",
                                                new TreeComparator());

            for (ProgramMessageSymbol j : programMsgSymbolList) {
                SortedTreeNode programMessageNode = new SortedTreeNode(j);
                messagesNode.add(programMessageNode);
            }
            programNode.add(messagesNode);


            symbolsNode       = new SortedTreeNode("Symbols",
                                                   new TreeComparator());
            moduleMap         = new HashMap<String, SortedTreeNode>();
            programSymbolList = programSymbolManager.getProgramSymbols(
                                                                    i.getID());

            for (ProgramSymbol j : programSymbolList) {
                SortedTreeNode moduleSymbolNode = moduleMap.get(j.getModule());
                SortedTreeNode programSymbolNode = new SortedTreeNode(j);

                if (moduleSymbolNode == null) {
                    String moduleName = j.getModule();

                    moduleSymbolNode = new SortedTreeNode(moduleName,
                                                       new TreeComparator());

                    moduleMap.put(moduleName, moduleSymbolNode);
                }
                moduleSymbolNode.add(programSymbolNode);
            }



            for (SortedTreeNode j : moduleMap.values()) {
                symbolsNode.add(j);
            }
            programNode.add(symbolsNode);
            root.add(programNode);
        }

        programTree.setModel(new DefaultTreeModel(root));
    }


    private final JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(buildFileMenu());
        menuBar.add(buildProgramMenu());
        menuBar.add(buildProfilingMenu());

        return menuBar;
    }


    private final JMenu buildFileMenu() {
        JMenu     menu  = new JMenu("File");
        JMenuItem close = new JMenuItem("Close");

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfigManagerFrame.this.setVisible(false);
            }
        });
        menu.add(close);

        return menu;
    }


    private final JMenu buildProgramMenu() {
        final JMenu     menu           = new JMenu("Program");
        final JMenuItem uploadProgram  = new JMenuItem("Upload Program");
        final JMenuItem deleteProgram  = new JMenuItem("Delete Program");
        final JMenuItem configureAll   = new JMenuItem("Configure All Motes");
        final JMenuItem unconfigureAll = new JMenuItem("Unconfigure All " +
                                                        "Motes");

        uploadProgram.addActionListener(new UploadProgramActionListener());
        menu.add(uploadProgram);

        deleteProgram.addActionListener(new DeleteProgramActionListener());
        menu.add(deleteProgram);

        menu.add(new JSeparator());

        configureAll.addActionListener(new ConfigureAllActionListener());
        menu.add(configureAll);

        unconfigureAll.addActionListener(new UnconfigureAllActionListener());
        menu.add(unconfigureAll);

        menu.addMenuListener(new ProgramMenuListener(deleteProgram));

        return menu;
    }


    private final JMenu buildProfilingMenu() {
        final JMenu     menu                   = new JMenu("Profiling");
        final JMenuItem deleteProfilingSymbol  = new JMenuItem("Delete " +
                                                               "Profiling " +
                                                               "Symbol");
        final JMenuItem deleteProfilingMessage = new JMenuItem("Delete " +
                                                               "Profiling " +
                                                               "Message");

        deleteProfilingSymbol.addActionListener(
                           new DeleteProgramProfilingSymbolListener());
        menu.add(deleteProfilingSymbol);


        deleteProfilingMessage.addActionListener(
                           new DeleteProgramProfilingMessageSymbolListener());
        menu.add(deleteProfilingMessage);


        menu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                setDeleteProfilingSymbolEnabled();
                setDeleteProfilingMessageEnabled();
            }


            private void setDeleteProfilingSymbolEnabled() {
                int row = profilingSymbolTable.getSelectedRow();

                deleteProfilingSymbol.setEnabled(
                            (row != -1) &&
                            (row != (profilingSymbolTable.getRowCount() - 1)));
            }


            private void setDeleteProfilingMessageEnabled() {
                int row = profilingMsgTable.getSelectedRow();

                deleteProfilingMessage.setEnabled(
                            (row != -1) &&
                            (row != (profilingMsgTable.getRowCount() - 1)));
            }


            public void menuCanceled(MenuEvent e)   { }
            public void menuDeselected(MenuEvent e) { }

        });

        return menu;
    }


    private final JPanel buildLeftPanel() throws ClassNotFoundException {
        JPanel    panel = new JPanel(new BorderLayout());
        Dimension size  = new Dimension(WINDOW_WIDTH  / 4,
                                        (int) (WINDOW_HEIGHT * (2 / 3.0)));

        panel.setBorder(new TitledBorder("Programs"));
        panel.setSize(size);
        panel.setPreferredSize(size);
        panel.setMinimumSize(size);

        panel.add(new JScrollPane(programTree), BorderLayout.CENTER);

        return panel;
    }


    private final JPanel buildBottomPanel() {
        JPanel      panel      = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        Dimension   size       = new Dimension(WINDOW_WIDTH,
                                               WINDOW_HEIGHT / 10);
                            
        panel.setSize(size);
        panel.setPreferredSize(size);

        tabbedPane.add("Symbol Profiling",
                       new JScrollPane(profilingSymbolTable));

        tabbedPane.add("Message Profiling",
                       new JScrollPane(profilingMsgTable));

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                profilingSymbolTable.clearSelection();
                profilingMsgTable.clearSelection();
            }
        });
        panel.add(tabbedPane);

        return panel;
    }


    private final JComponent buildMoteGridPanel() throws RemoteException,
                                    NotBoundException, MalformedURLException {
        GridPanel gridPanel = buildGridPanel();
            
        for (MoteTestbedAssignment i : mtbAssignmentData) {
            int             row       = i.getMoteLocationY();
            int             col       = i.getMoteLocationX();
            MoteConfigPanel motePanel = new MoteConfigPanel(i, programManager,
                                                            config.getID(),
                                                            moteManager,
                                                            moteTypeManager,
                                                            moteDepConfMgr);
            
            tosPlatform = motePanel.getMoteTosPlatform();
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


    protected class ProgramCompileObserver extends    UnicastRemoteObject
                                           implements RemoteObserver {
        public ProgramCompileObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProgramCompileManager.Message message;
            message = (ProgramCompileManager.Message) msg;

            DefaultTreeModel model;
            SortedTreeNode   root;

            model = (DefaultTreeModel) programTree.getModel();
            root  = (SortedTreeNode)   programTree.getModel().getRoot();

            switch (message) {
            case COMPILE_STARTED: {
                if (compiling) {
                    if (outputWindow != null) {
                        outputWindow.setVisible(false);
                        outputWindow.dispose();
                    }
                    outputWindow = new OutputWindow("Compilation Results");
                    outputWindow.setVisible(true);
                }
                break;
            }

            case COMPILE_PROGRESS: {
                if (outputWindow != null) {
                    String results = arg.toString();
                    outputWindow.appendText(results);
                }
                break;
            }

            case COMPILE_COMPLETED: {
                Boolean success = (Boolean) arg;

                if (compiling) {
                    compiling = false;
                }

                if (success.booleanValue()) {
                    log.debug("Compile completed successfully.");
                } else {
                    log.debug("Compile unsuccessful.");
                }
                buildProgramTreeNodes();
                break;
            }
            }
        }
    }


    protected class ProgramObserver extends    UnicastRemoteObject
                                    implements RemoteObserver {
        public ProgramObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProgramManager.Message message = (ProgramManager.Message) msg;

            DefaultTreeModel model;
            SortedTreeNode   root;

            model = (DefaultTreeModel) programTree.getModel();
            root  = (SortedTreeNode)   programTree.getModel().getRoot();

            switch (message) {
            case NEW_PROGRAM:
                log.info("New Program:  " + arg.toString());
                buildProgramTreeNodes();
                break;

            case DELETE_PROGRAM:
                log.info("Delete Program:  " + arg.toString());
                buildProgramTreeNodes();

                break;
            }
        }
    }


    protected class ProgramProfilingSymbolObserver
                                             extends    UnicastRemoteObject
                                             implements RemoteObserver {
        public ProgramProfilingSymbolObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProgramProfilingSymbolManager.Message message;
            ProgramProfilingSymbol                profilingSymbol;

            message         = (ProgramProfilingSymbolManager.Message) msg;
            profilingSymbol = (ProgramProfilingSymbol)                arg;

            switch (message) {
            case NEW_SYMBOL:
                log.debug("Adding new project profiling symbol: " +
                          profilingSymbol);
                profilingSymbolTable.setModel(new ProfilingSymbolTableModel());
                break;

            case DELETE_SYMBOL:
                log.debug("Deleting project profiling symbol " +
                          profilingSymbol);
                profilingSymbolTable.setModel(new ProfilingSymbolTableModel());
                break;

            default:
                log.error("Unknown message:  " + msg);
                break;
            }
        }
    }


    protected class ProgramProfilingMessageSymbolObserver
                                             extends    UnicastRemoteObject
                                             implements RemoteObserver {

        public ProgramProfilingMessageSymbolObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProgramProfilingMessageSymbolManager.Message message;
            ProgramProfilingMessageSymbol                profilingMsgSymbol;

            message            = (ProgramProfilingMessageSymbolManager.Message)
                                  msg;
            profilingMsgSymbol = (ProgramProfilingMessageSymbol) arg;

            switch (message) {
            case NEW_SYMBOL:
                log.debug("Adding new project profiling message symbol: " +
                          profilingMsgSymbol);
                profilingMsgTable.setModel(
                                    new ProfilingMessageSymbolTableModel());
                break;

            case DELETE_SYMBOL:
                log.debug("Deleting project profiling message symbol " +
                          profilingMsgSymbol);
                profilingMsgTable.setModel(
                                    new ProfilingMessageSymbolTableModel());
                break;

            default:
                log.error("Unknown message:  " + msg);
                break;
            }
        }
    }


    protected class ProfilingSymbolTableModel extends AbstractTableModel {
        private int NUM_COLS = 3;

        private ProgramProfilingSymbol[] symbols;


        public ProfilingSymbolTableModel() throws RemoteException {
            List<ProgramProfilingSymbol> symbolList;

            symbolList = progProfManager.getProgramProfilingSymbols(
                                                               config.getID());
            symbols    = symbolList.toArray(
                            new ProgramProfilingSymbol[symbolList.size()]);
        }


        public int getColumnCount() {
            return NUM_COLS;
        }


        public int getRowCount() {
            return symbols.length + 1;
        }


        public String getColumnName(int col) {
            String name = null;

            if      (col == 0) { name = "Program"; }
            else if (col == 1) { name = "Module";  }
            else if (col == 2) { name = "Symbol";  }

            return name;
        }


        public boolean symbolExists(ProgramSymbol programSymbol) {
            boolean exists = false;

            for (int i = 0; !exists && i < symbols.length; ++i) {
                exists = (   symbols[i].getProgramSymbolID()
                          == programSymbol.getID());
            }

            return exists;
        }


        public Object getValueAt(int row, int col) {
            Object value = null;

            try {
                if (row != symbols.length) {
                    ProgramSymbol symbol;
                    Program       program;

                    symbol  = programSymbolManager.getProgramSymbol(
                                            symbols[row].getProgramSymbolID());
                    program = programManager.getProgram(symbol.getProgramID());

                    if (col == 0) {
                        value = program.getName();
                    } else if (col == 1) {
                        value = symbol.getModule();
                    } else if (col == 2) {
                        value = symbol.getSymbol();
                    }
                } else {
                    value = "";
                }
            } catch (RemoteException ex) {
                log.error("RemoteException:", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }

            return value;
        }


        public ProgramProfilingSymbol getProfilingSymbol(int row) {
            return (row != symbols.length) ? symbols[row] : null;
        }
    }


    protected class ProfilingMessageSymbolTableModel
                                                extends AbstractTableModel {
        private int NUM_COLS = 2;
        private ProgramProfilingMessageSymbol[] symbols;


        public ProfilingMessageSymbolTableModel() throws RemoteException {
            List<ProgramProfilingMessageSymbol> symbolList;

            symbolList =
                    progProfMsgSymManager.getProgramProfilingMessageSymbols(
                                                               config.getID());
            symbols    = symbolList.toArray(
                         new ProgramProfilingMessageSymbol[symbolList.size()]);
        }


        public int getColumnCount() {
            return NUM_COLS;
        }


        public int getRowCount() {
            return symbols.length + 1;
        }


        public String getColumnName(int col) {
            String name = null;

            if      (col == 0) { name = "Program"; }
            else if (col == 1) { name = "Message Symbol";  }

            return name;
        }


        public boolean symbolExists(ProgramMessageSymbol programMsgSymbol) {
            boolean exists = false;

            for (int i = 0; !exists && i < symbols.length; ++i) {
                exists = (   symbols[i].getProgramMessageSymbolID()
                          == programMsgSymbol.getID());
            }

            return exists;
        }


        public Object getValueAt(int row, int col) {
            Object value = null;

            try {
                if (row != symbols.length) {
                    ProgramMessageSymbol symbol;
                    Program              program;

                    symbol = progMsgSymManager.getProgramMessageSymbol(
                                     symbols[row].getProgramMessageSymbolID());

                    program = programManager.getProgram(symbol.getProgramID());

                    if (col == 0) {
                        value = program.getName();
                    } else if (col == 1) {
                        value = symbol.getName();
                    }
                } else {
                    value = "";
                }
            } catch (RemoteException ex) {
                log.error("RemoteException:", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }

            return value;
        }


        public ProgramProfilingMessageSymbol getProfilingMsgSymbol(int row) {
            return (row != symbols.length) ? symbols[row] : null;
        }
    }




    public class UploadProgramActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            FileUploadDialog fud;
            fud = new FileUploadDialog(ConfigManagerFrame.this,
                                       "Project Upload");
            fud.setVisible(true);

            String name        = fud.getName();
            String description = fud.getDescription();
            File   directory   = fud.getDirectory();

            try {
                if (name != null) {
                    byte[] zipData   = ZipUtils.zipDirectory(directory);
                    int    programID = programManager.createNewProgram(
                                                            testbed.getID(),
                                                            project.getID(),
                                                            name, description,
                                                            zipData,
                                                            tosPlatform);
                    ComponentRewiringDialog crd;
                    crd = new ComponentRewiringDialog(ConfigManagerFrame.this);
                    crd.setVisible(true);

                    programWeaverManager.weaveInComponents(programID,
                                                           crd.getRewiringMap());

                    compiling = true;
                    programCompileManager.compileProgram(programID,
                                                         tosPlatform);
                }
            } catch (RemoteException ex) {
                log.error("Remote Exception", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            } catch (FileNotFoundException ex) {
                log.error("Directory '" + directory + "' not found.", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            } catch (IOException ex) {
                log.error("Error reading directory '" + directory + "'", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    public class DeleteProgramActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                TreePath       path;
                SortedTreeNode node;
                Program        program;

                path    = programTree.getSelectionPath();
                node    = (SortedTreeNode) path.getLastPathComponent();
                program = (Program)        node.getUserObject();

                programManager.deleteProgram(program.getID());
            } catch (RemoteException ex) {
                log.error("Remote exception while deleting program", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    public class ConfigureAllActionListener implements ActionListener {
        private final int MAX_RADIO_POWER = 31;

        private Object[] radioValues = new Object[MAX_RADIO_POWER];


        public ConfigureAllActionListener() {
            for (int i = 0; i < MAX_RADIO_POWER; ++i) {
                radioValues[i] = new Integer(i + 1);
            }
        }


        public void actionPerformed(ActionEvent e) {
            try {
                TreePath       path;
                SortedTreeNode node;
                Program        program;
                Integer        selectedValue;

                path          = programTree.getSelectionPath();
                node          = (SortedTreeNode) path.getLastPathComponent();
                program       = (Program)        node.getUserObject();
                selectedValue = (Integer) JOptionPane.showInputDialog(
                                              ConfigManagerFrame.this,
                                              "Select Radio Power Level",
                                              "Radio Power Level",
                                              JOptionPane.QUESTION_MESSAGE,
                                              null, radioValues,
                                              radioValues[MAX_RADIO_POWER - 1]);

                if (selectedValue != null) {
                    int radioValue = selectedValue.intValue();

                    for (MoteTestbedAssignment i : mtbaManager.
                                getMoteTestbedAssignmentList(testbed.getID())) {
                        moteDepConfMgr.setMoteDeploymentConfiguration(
                                                        config.getID(),
                                                        i.getMoteID(),
                                                        program.getID(),
                                                        radioValue);
                    }
                } else {
                    log.info("Configure all cancelled by user.");
                }
            } catch (RemoteException ex) {
                log.error("Remote exception while configuring all", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    public class UnconfigureAllActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                TreePath       path;
                SortedTreeNode node;
                Program        program;

                path          = programTree.getSelectionPath();
                node          = (SortedTreeNode) path.getLastPathComponent();
                program       = (Program)        node.getUserObject();

                for (MoteTestbedAssignment i :
                        new ArrayList<MoteTestbedAssignment>(mtbaManager.
                            getMoteTestbedAssignmentList(testbed.getID()))) {
                    MoteDeploymentConfiguration mdc;
                    mdc = moteDepConfMgr.
                            getMoteDeploymentConfigurationByProgramID(
                                                            i.getMoteID(),
                                                            program.getID());

                    if (mdc != null) {
                        moteDepConfMgr.deleteMoteDeploymentConfiguration(
                                                                mdc.getID());
                    }
                }
            } catch (RemoteException ex) {
                log.error("Remote exception while unconfigureAll program", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    protected class ProgramMenuListener implements MenuListener {
        private JMenuItem deleteProgram;


        public ProgramMenuListener(JMenuItem deleteProgram) {
            this.deleteProgram = deleteProgram;
        }


        public void menuSelected(MenuEvent e) {
            boolean  enabled = false;
            TreePath path    = programTree.getSelectionPath();

            if (path != null) {
                SortedTreeNode node;
                node = (SortedTreeNode) path.getLastPathComponent();

                if (node != null) {
                    Object userObject = node.getUserObject();

                    if (userObject instanceof Program) {
                        enabled = true;
                    }
                }
            }

            deleteProgram.setEnabled(enabled);
        }



        public void menuCanceled(MenuEvent e)                      { }
        public void menuDeselected(MenuEvent e)                    { }
    }


    protected class DeleteProgramProfilingSymbolListener
                                                implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                ProfilingSymbolTableModel model;
                ProgramProfilingSymbol    profilingSymbol;

                model           = (ProfilingSymbolTableModel)
                                        profilingSymbolTable.getModel();
                profilingSymbol = model.getProfilingSymbol(
                                        profilingSymbolTable.getSelectedRow());

                if (profilingSymbol != null) {
                    progProfManager.deleteProfilingSymbol(
                                                profilingSymbol.getID());
                }
            } catch (RemoteException ex) {
                log.error("RemoteException", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    protected class DeleteProgramProfilingMessageSymbolListener
                                                implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                ProfilingMessageSymbolTableModel model;
                ProgramProfilingMessageSymbol    profilingMsgSymbol;

                model              = (ProfilingMessageSymbolTableModel)
                                        profilingMsgTable.getModel();
                profilingMsgSymbol = model.getProfilingMsgSymbol(
                                        profilingMsgTable.getSelectedRow());

                if (profilingMsgSymbol != null) {
                    progProfMsgSymManager.deleteProgramProfilingMessageSymbol(
                                                profilingMsgSymbol.getID());
                }
            } catch (RemoteException ex) {
                log.error("RemoteException", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    protected class ProgramTreeMouseListener extends MouseAdapter {
        private JPopupMenu menu;
        private JMenuItem  title;
        private JMenuItem  deleteProgram;
        private JMenuItem  configureAll;
        private JMenuItem  unconfigureAll;


        public ProgramTreeMouseListener() {
            menu           = new JPopupMenu();
            title          = new JMenuItem();
            deleteProgram  = new JMenuItem("Delete Program");
            configureAll   = new JMenuItem("Configure All Motes");
            unconfigureAll = new JMenuItem("Unconfigure All Motes");

            title.setEnabled(false);
            menu.add(title);
            menu.add(new JSeparator());

            deleteProgram.addActionListener(new DeleteProgramActionListener());
            configureAll.addActionListener(new ConfigureAllActionListener());
            unconfigureAll.addActionListener(
                                          new UnconfigureAllActionListener());

            menu.add(deleteProgram);

            menu.add(new JSeparator());

            menu.add(configureAll);
            menu.add(unconfigureAll);
        }


        public void mouseClicked(MouseEvent e) {
            TreePath path = programTree.getSelectionPath();

            if (SwingUtilities.isRightMouseButton(e) && path != null) {
                SortedTreeNode node;
                Object                 obj;

                node = (SortedTreeNode) path.getLastPathComponent();
                obj  = node.getUserObject();

                if (obj instanceof Program) {
                    Program program = (Program) obj;

                    title.setText(program.getName());
                    menu.show(programTree, e.getX(), e.getY());
                }
            }
        }
    }


    protected class ProfilingSymbolTableMouseListener extends MouseAdapter {
        private JPopupMenu menu;
        private JMenuItem  title;
        private JMenuItem  deleteSymbol;


        public ProfilingSymbolTableMouseListener() {
            menu  = new JPopupMenu();
            title = new JMenuItem();

            title.setEnabled(false);
            menu.add(title);
            menu.add(new JSeparator());

            deleteSymbol = new JMenuItem("Delete Profiling Symbol");
            deleteSymbol.addActionListener(
                                new DeleteProgramProfilingSymbolListener());

            menu.add(deleteSymbol);
        }


        public void mouseClicked(MouseEvent e) {
            try {
                int row = profilingSymbolTable.getSelectedRow();

                if (   SwingUtilities.isRightMouseButton(e)
                    && (row != -1)
                    && (row != (profilingSymbolTable.getRowCount() - 1))) {

                    ProfilingSymbolTableModel model;
                    ProgramProfilingSymbol    profilingSymbol;

                    model           = (ProfilingSymbolTableModel)
                                              profilingSymbolTable.getModel();
                    profilingSymbol = model.getProfilingSymbol(row);

                    title.setText(programSymbolManager.getProgramSymbol(
                            profilingSymbol.getProgramSymbolID()).getSymbol());

                    menu.show(profilingSymbolTable, e.getX(), e.getY());
                }
            } catch (RemoteException ex) {
                log.error("RemoteException:", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    protected class ProfilingMessageTableMouseListener extends MouseAdapter {
        private JPopupMenu menu;
        private JMenuItem  title;
        private JMenuItem  deleteSymbol;


        public ProfilingMessageTableMouseListener() {
            menu  = new JPopupMenu();
            title = new JMenuItem();

            title.setEnabled(false);
            menu.add(title);
            menu.add(new JSeparator());

            deleteSymbol = new JMenuItem("Delete Profiling Message Symbol");
            deleteSymbol.addActionListener(
                            new DeleteProgramProfilingMessageSymbolListener());

            menu.add(deleteSymbol);
        }


        public void mouseClicked(MouseEvent e) {
            try {
                int row = profilingMsgTable.getSelectedRow();

                if (   SwingUtilities.isRightMouseButton(e)
                    && (row != -1)
                    && (row != (profilingMsgTable.getRowCount() - 1))) {

                    ProfilingMessageSymbolTableModel model;
                    ProgramProfilingMessageSymbol    profilingMsgSymbol;

                    model              = (ProfilingMessageSymbolTableModel)
                                            profilingMsgTable.getModel();
                    profilingMsgSymbol = model.getProfilingMsgSymbol(row);

                    title.setText(
                            progMsgSymManager.getProgramMessageSymbol(
                              profilingMsgSymbol.getProgramMessageSymbolID()).
                                                                    getName());

                    menu.show(profilingMsgTable, e.getX(), e.getY());
                }
            } catch (RemoteException ex) {
                log.error("RemoteException:", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    protected class ProgramSymbolDropTarget extends DropTargetAdapter {
        private final String MIME_TYPE =
                                    DataFlavor.javaJVMLocalObjectMimeType +
                                    ";class=edu.clemson.cs.nestbed."       +
                                    "common.model.ProgramSymbol";
        private DataFlavor programSymbolFlavor;


        public ProgramSymbolDropTarget() {
            try {
                this.programSymbolFlavor = new DataFlavor(MIME_TYPE);
            } catch (ClassNotFoundException e) { }
        }


        public void drop(DropTargetDropEvent e) {
            try {
                Transferable  t             = e.getTransferable();
                ProgramSymbol programSymbol = (ProgramSymbol)
                                        t.getTransferData(programSymbolFlavor);

                ProfilingSymbolTableModel pstm = (ProfilingSymbolTableModel)
                                            profilingSymbolTable.getModel();

                if (!pstm.symbolExists(programSymbol)) {
                    progProfManager.createNewProfilingSymbol(config.getID(),
                                                         programSymbol.getID());
                } else {
                    log.warn("Program profiling symbol already exists");
                }
            } catch (UnsupportedFlavorException ex) {
                log.error("DataFlavor " + programSymbolFlavor +
                          " unsupported", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            } catch (IOException ex) {
                log.error("I/O exception on data flavor " +
                          programSymbolFlavor, ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    protected class ProgramMessageSymbolDropTarget extends DropTargetAdapter {
        private final String MIME_TYPE =
                                    DataFlavor.javaJVMLocalObjectMimeType +
                                    ";class=edu.clemson.cs.nestbed."       +
                                    "common.model.ProgramMessageSymbol";
        private DataFlavor programMessageSymbolFlavor;

        public ProgramMessageSymbolDropTarget() {
            try {
                this.programMessageSymbolFlavor = new DataFlavor(MIME_TYPE);
            } catch (ClassNotFoundException e) { }
        }


        public void drop(DropTargetDropEvent e) {
            try {
                Transferable         t                = e.getTransferable();
                ProgramMessageSymbol programMsgSymbol = (ProgramMessageSymbol)
                                t.getTransferData(programMessageSymbolFlavor);

                ProfilingMessageSymbolTableModel pmstm =
                                        (ProfilingMessageSymbolTableModel)
                                                 profilingMsgTable.getModel();

                if (!pmstm.symbolExists(programMsgSymbol)) {
                    progProfMsgSymManager.createNewProfilingMessageSymbol(
                                                     config.getID(),
                                                     programMsgSymbol.getID());
                } else {
                    log.warn("Program profiling message symbol already exists");
                }
            } catch (UnsupportedFlavorException ex) {
                log.error("DataFlavor " + programMessageSymbolFlavor +
                          " unsupported", ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            } catch (IOException ex) {
                log.error("I/O exception on data flavor " +
                          programMessageSymbolFlavor, ex);
                ClientUtils.displayErrorMessage(ConfigManagerFrame.this, ex);
            }
        }
    }


    protected class ProgramTreeCellRenderer extends DefaultTreeCellRenderer {
        public Component getTreeCellRendererComponent(JTree   tree,
                                                      Object  value,
                                                      boolean sel,
                                                      boolean expanded,
                                                      boolean leaf,
                                                      int     row,
                                                      boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel,
                                               expanded, leaf, row,
                                               hasFocus);

            if (isFolderNode(value)) {
                if (expanded) {
                    setIcon(getDefaultOpenIcon());
                } else {
                    setIcon(getDefaultClosedIcon());
                }
            } else {
                setIcon(getDefaultLeafIcon());
            }

            return this;
        }


        protected boolean isFolderNode(Object value) {
            boolean programNode = false;

            SortedTreeNode node = (SortedTreeNode) value;
            Object                 obj  = node.getUserObject();

            if (      obj instanceof Program
                   || obj instanceof String) {
                programNode = true;
            }

            return programNode;
        }
    }


    protected class TreeComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            SortedTreeNode stn1 = (SortedTreeNode) o1;
            SortedTreeNode stn2 = (SortedTreeNode) o2;
            Comparable     c1   = (Comparable) stn1.getUserObject();
            Comparable     c2   = (Comparable) stn2.getUserObject();

            @SuppressWarnings({"unchecked"})
            int            retVal = c1.compareTo(c2);

            return retVal;
        }


        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
