/* $Id$ */
/*
 * TestbedManagerFrame.java
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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.ProjectManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProjectDeploymentConfigurationManager;
import edu.clemson.cs.nestbed.common.management.configuration.TestbedManager;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


public class TestbedManagerFrame extends JFrame {
    private final static Log log = LogFactory.getLog(TestbedManagerFrame.class);

    private final static String RMI_BASE_URL;
    private final static String WINDOW_TITLE  = "NESTbed Manager";
    private final static int    WINDOW_WIDTH  = 600;
    private final static int    WINDOW_HEIGHT = 400;
    private final static int    SIZE_IGNORED  = 0;

    static {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");
    }


    private TestbedManager                         testbedManager;
    private ProjectManager                         projectManager;
    private ProjectDeploymentConfigurationManager  configManager;

    private JTextField                             testbedName;
    private JTextField                             testbedDescription;

    private JTextField                             projectName;
    private JTextField                             projectDescription;

    private JTextField                             configurationName;
    private JTextField                             configurationDesc;

    private JList                                  testbedList;
    private Vector<Testbed>                        testbedData;

    private JList                                  projectList;
    private Vector<Project>                        projectData;

    private JList                                  configurationList;
    private Vector<ProjectDeploymentConfiguration> configurationData;

    private ConfigManagerFrame                     configManagerFrame;


    public TestbedManagerFrame() throws RemoteException, NotBoundException,
                                        MalformedURLException {
        super(WINDOW_TITLE);

        lookupRemoteManagers();
        // TODO: testbedManager.addRemoteObserver(new TestbedObserver());
        projectManager.addRemoteObserver(new ProjectObserver());
        configManager.addRemoteObserver(new ProjectDeploymentConfigObserver());

        createComponents();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setJMenuBar(buildMenuBar());

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(buildListPanel(),   BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                doExit();
            }
        });
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        testbedManager = (TestbedManager)
                    Naming.lookup(RMI_BASE_URL + "TestbedManager");
        projectManager = (ProjectManager)
                    Naming.lookup(RMI_BASE_URL + "ProjectManager");
        configManager = (ProjectDeploymentConfigurationManager)
                    Naming.lookup(RMI_BASE_URL +
                                  "ProjectDeploymentConfigurationManager");
    }


    private final void createComponents() {
        testbedName         = new JTextField();
        testbedDescription  = new JTextField();

        projectName         = new JTextField();
        projectDescription  = new JTextField();

        configurationName   = new JTextField();
        configurationDesc   = new JTextField();

        testbedList         = new VisitableList(new TestbedListVisitor());
        testbedData         = new Vector<Testbed>();

        projectList         = new VisitableList(new ProjectListVisitor());
        projectData         = new Vector<Project>();

        configurationList   = new VisitableList(new ConfigurationListVisitor());
        configurationData   = new Vector<ProjectDeploymentConfiguration>();
    }


    private final JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(buildFileMenu());
        menuBar.add(buildProjectMenu());
        menuBar.add(buildConfigurationMenu());

        return menuBar;
    }


    private final JMenu buildFileMenu() {
        JMenu     file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doExit();
            }
        });

        file.add(exit);

        return file;
    }

    private void doExit() {
        int choice;

        choice = JOptionPane.showConfirmDialog(this,
                                               "Do you wish to exit NESTBed?",
                                               "Exit Confirmation",
                                               JOptionPane.YES_NO_OPTION,
                                               JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            log.fatal("Application shutdown per user request.");
            System.exit(0);
        }
    }

    private final JMenu buildProjectMenu() {
        final JMenu     menu          = new JMenu("Project");
        final JMenuItem addProject    = new JMenuItem("Add");
        final JMenuItem deleteProject = new JMenuItem("Delete");

        addProject.addActionListener(new AddProjectActionListener());
        menu.add(addProject);

        deleteProject.addActionListener(new DeleteProjectActionListener());
        menu.add(deleteProject);

        menu.addMenuListener(new ProjectMenuListener(addProject,
                                                     deleteProject));
        return menu;
    }


    private final JMenu buildConfigurationMenu() {
        final JMenu     menu           = new JMenu("Configuration");
        final JMenuItem addConfig      = new JMenuItem("Add");
        final JMenuItem cloneConfig    = new JMenuItem("Clone");
        final JMenuItem modifyConfig   = new JMenuItem("Modify");
        final JMenuItem deleteConfig   = new JMenuItem("Delete");
        final JMenuItem viewNetMonitor = new JMenuItem("View Network Monitor");


        addConfig.addActionListener(new AddConfigurationActionListener());
        menu.add(addConfig);

        cloneConfig.addActionListener(new CloneConfigurationActionListener());
        menu.add(cloneConfig);

        modifyConfig.addActionListener(new ViewConfigurationActionListener());
        menu.add(modifyConfig);

        deleteConfig.addActionListener(new DeleteConfigurationActionListener());
        menu.add(deleteConfig);

        menu.add(new JSeparator());
        viewNetMonitor.addActionListener(
                                    new ViewNetworkMonitorActionListener());
        menu.add(viewNetMonitor);


        menu.addMenuListener(new ConfigurationMenuListener(addConfig,
                                                           cloneConfig,
                                                           deleteConfig,
                                                           modifyConfig,
                                                           viewNetMonitor));
        return menu;
    }


    private final JPanel buildListPanel() throws RemoteException {
        int    rows  = 3;
        int    cols  = 1;
        JPanel panel = new JPanel(new GridLayout(rows, cols));

        panel.add(buildTestbedPanel());
        panel.add(buildProjectPanel());
        panel.add(buildConfigurationPanel());

        return panel;
    }


    private final JPanel buildTestbedPanel() throws RemoteException {
        JPanel testbedPanel = new JPanel(new BorderLayout());
        testbedPanel.setBorder(new TitledBorder("Physical Deployments"));


        // --- left-side -------------------------------------------------
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(WINDOW_WIDTH  / 3,
                                                 SIZE_IGNORED));

        testbedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testbedList.setListData(testbedManager.getTestbedList().toArray());
        testbedList.addListSelectionListener(
                                        new TestbedListSelectionListener());

        sidePanel.add(new JScrollPane(testbedList), BorderLayout.CENTER);
        testbedPanel.add(sidePanel, BorderLayout.WEST);


        // --- right-side ------------------------------------------------
        sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(310, SIZE_IGNORED));

        JPanel rsTopPanel = new JPanel(new BorderLayout());
        rsTopPanel.setPreferredSize(new Dimension(SIZE_IGNORED, 50));

        JPanel labelPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        labelPanel.add(new JLabel("Name:  "));
        labelPanel.add(new JLabel("Description:  "));

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.add(testbedName);
        testbedName.setEditable(false);

        infoPanel.add(testbedDescription);
        testbedDescription.setEditable(false);

        rsTopPanel.add(labelPanel, BorderLayout.WEST);
        rsTopPanel.add(infoPanel,  BorderLayout.CENTER);

        sidePanel.add(rsTopPanel, BorderLayout.NORTH);

        testbedPanel.add(sidePanel, BorderLayout.EAST);

        return testbedPanel;
    }


    private final JPanel buildProjectPanel() {
        JPanel projectPanel = new JPanel(new BorderLayout());
        projectPanel.setBorder(new TitledBorder("Projects"));


        // --- left-side -------------------------------------------------
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(WINDOW_WIDTH / 3,
                                                 SIZE_IGNORED));

        projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectList.addListSelectionListener(
                                        new ProjectListSelectionListener());

        sidePanel.add(new JScrollPane(projectList), BorderLayout.CENTER);
        projectPanel.add(sidePanel, BorderLayout.WEST);


        // --- right-side ------------------------------------------------
        sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(310, SIZE_IGNORED));

        JPanel rsTopPanel = new JPanel(new BorderLayout());
        rsTopPanel.setPreferredSize(new Dimension(SIZE_IGNORED, 50));

        JPanel labelPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        labelPanel.add(new JLabel("Name:  "));
        labelPanel.add(new JLabel("Description:  "));

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.add(projectName);
        projectName.setEditable(false);

        infoPanel.add(projectDescription);
        projectDescription.setEditable(false);

        rsTopPanel.add(labelPanel, BorderLayout.WEST);
        rsTopPanel.add(infoPanel,  BorderLayout.CENTER);

        sidePanel.add(rsTopPanel, BorderLayout.NORTH);

        projectPanel.add(sidePanel, BorderLayout.EAST);

        return projectPanel;
    }


    private final JPanel buildConfigurationPanel() {
        JPanel configPanel = new JPanel(new BorderLayout());
        configPanel.setBorder(new TitledBorder("Configurations"));

        // --- left-side -------------------------------------------------
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(WINDOW_WIDTH / 3,
                                                 SIZE_IGNORED));

        configurationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        configurationList.addListSelectionListener(
                                        new ConfigListSelectionListener());

        sidePanel.add(new JScrollPane(configurationList), BorderLayout.CENTER);
        configPanel.add(sidePanel, BorderLayout.WEST);


        // --- right-side ------------------------------------------------
        sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(310, SIZE_IGNORED));

        JPanel rsTopPanel = new JPanel(new BorderLayout());
        rsTopPanel.setPreferredSize(new Dimension(SIZE_IGNORED, 50));

        JPanel labelPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        labelPanel.add(new JLabel("Name:  "));
        labelPanel.add(new JLabel("Description:  "));

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.add(configurationName);
        configurationName.setEditable(false);

        infoPanel.add(configurationDesc);
        configurationDesc.setEditable(false);

        rsTopPanel.add(labelPanel, BorderLayout.WEST);
        rsTopPanel.add(infoPanel,  BorderLayout.CENTER);

        sidePanel.add(rsTopPanel, BorderLayout.NORTH);

        configPanel.add(sidePanel, BorderLayout.EAST);

        return configPanel;
    }


    protected class TestbedListVisitor implements ListVisitor {
        public JPopupMenu visitPopupMenu(VisitableList list) {
            return null;
        }


        public String visitToolTip(VisitableList list) {
            Testbed testbed = (Testbed) list.getSelectedValue();
            String  tip     = null;

            if (testbed != null) {
                tip = "<html><table border=0 cellspacing=0 cellpadding=1>" +
                      "<tr>"      +
                      "    <th align=left>"  + "Name:  "        + "</th>"  +
                      "    <td>"  + testbed.getName() + "</td>" +
                      "</tr><tr>" +
                      "    <th align=left>"  + "Description:  " + "</th>" +
                      "    <td>"  + testbed.getDescription()    + "</td>" +
                      "</tr></table></html>";
            }

            return tip;
        }
    }


    protected class ProjectListVisitor implements ListVisitor {
        private JPopupMenu popupMenu     = new JPopupMenu();
        private JMenuItem  title         = new JMenuItem("Project");
        private JMenuItem  addProject    = new JMenuItem("Add");
        private JMenuItem  deleteProject = new JMenuItem("Delete");


        public ProjectListVisitor() {
            title.setEnabled(false);
            popupMenu.add(title);
            popupMenu.add(new JSeparator());

            addProject.addActionListener(new AddProjectActionListener());
            popupMenu.add(addProject);

            deleteProject.addActionListener(new DeleteProjectActionListener());
            popupMenu.add(deleteProject);

            popupMenu.addPopupMenuListener(new ProjectMenuListener(addProject,
                                                              deleteProject));
        }


        public JPopupMenu visitPopupMenu(VisitableList list) {
            Object selected = list.getSelectedValue();

            if (selected != null) {
                String title = selected.toString();

                deleteProject.setText("Delete '" + title + "'");
            } else {
                deleteProject.setText("Delete");
            }

            return popupMenu;
        }


        public String visitToolTip(VisitableList list) {
            Project project = (Project) list.getSelectedValue();
            String  tip     = null;

            if (project != null) {
                tip = "<html><table border=0 cellspacing=0 cellpadding=1>" +
                      "<tr>"      +
                      "    <th align=left>"  + "Name:  "        + "</th>"  +
                      "    <td>"  + project.getName() + "</td>" +
                      "</tr><tr>" +
                      "    <th align=left>"  + "Description:  " + "</th>" +
                      "    <td>"  + project.getDescription()    + "</td>" +
                      "</tr></table></html>";
            }

            return tip;
        }
    }


    protected class ConfigurationListVisitor implements ListVisitor {
        private JPopupMenu popupMenu      = new JPopupMenu();
        private JMenuItem  title          = new JMenuItem("Configuration");

        private JMenuItem  addConfig      = new JMenuItem("Add");
        private JMenuItem  cloneConfig    = new JMenuItem("Clone");
        private JMenuItem  modifyConfig   = new JMenuItem("Modify");
        private JMenuItem  deleteConfig   = new JMenuItem("Delete");
        private JMenuItem  viewNetMonitor = new JMenuItem("View Network " +
                                                          "Monitor");


        public ConfigurationListVisitor() {
            title.setEnabled(false);
            popupMenu.add(title);
            popupMenu.add(new JSeparator());


            addConfig.addActionListener(new AddConfigurationActionListener());
            popupMenu.add(addConfig);


            cloneConfig.addActionListener(
                                        new CloneConfigurationActionListener());
            popupMenu.add(cloneConfig);


            modifyConfig.addActionListener(
                                 new ViewConfigurationActionListener());
            popupMenu.add(modifyConfig);


            deleteConfig.addActionListener(
                                 new DeleteConfigurationActionListener());
            popupMenu.add(deleteConfig);


            popupMenu.add(new JSeparator());
            viewNetMonitor.addActionListener(
                                new ViewNetworkMonitorActionListener());
            popupMenu.add(viewNetMonitor);


            popupMenu.addPopupMenuListener(new ConfigurationMenuListener(
                                                   addConfig,    cloneConfig,
                                                   deleteConfig, modifyConfig,
                                                   viewNetMonitor));
        }


        public JPopupMenu visitPopupMenu(VisitableList list) {
            Object selected = list.getSelectedValue();

            if (selected != null) {
                String title = selected.toString();

                cloneConfig.setText("Clone '" + title + "'");
                modifyConfig.setText("Modify '" + title + "'");
                deleteConfig.setText("Delete '" + title + "'");
            } else {
                cloneConfig.setText("Clone");
                modifyConfig.setText("Modify");
                deleteConfig.setText("Delete");
            }

            return popupMenu;
        }


        public String visitToolTip(VisitableList list) {
            ProjectDeploymentConfiguration config;
            String                         tip;

            config = (ProjectDeploymentConfiguration) list.getSelectedValue();
            tip    = null;

            if (config != null) {
                tip = "<html><table border=0 cellspacing=0 cellpadding=1>" +
                      "<tr>"      +
                      "    <th align=left>"  + "Name:  "        + "</th>"  +
                      "    <td>"  + config.getName() + "</td>"  +
                      "</tr><tr>" +
                      "    <th align=left>"  + "Description:  " + "</th>" +
                      "    <td>"  + config.getDescription()     + "</td>" +
                      "</tr></table></html>";
            }

            return tip;
        }
    }


    protected class ProjectObserver extends    UnicastRemoteObject
                                    implements RemoteObserver {

        public ProjectObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProjectManager.Message message = (ProjectManager.Message) msg;
            Project                project = (Project)                arg;

            switch (message) {
            case NEW_PROJECT:
                log.debug("Adding new project: " + project);

                projectData.add(project);
                projectList.setListData(projectData);

                break;

            case DELETE_PROJECT:
                log.debug("Deleting project: " + project);

                if (projectData.remove(project)) {
                    projectList.setListData(projectData);
                } else {
                    log.error("Project " + project + " with id " +
                              project.getID() + " not found.");
                }
                break;

            default:
                log.error("Unknoqn message:  " + msg);
                break;
            }
        }
    }


    protected class ProjectDeploymentConfigObserver
                                                  extends    UnicastRemoteObject
                                                  implements RemoteObserver {
        public ProjectDeploymentConfigObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProjectDeploymentConfigurationManager.Message message;
            ProjectDeploymentConfiguration                config;

            message = (ProjectDeploymentConfigurationManager.Message) msg;
            config  = (ProjectDeploymentConfiguration)                arg;

            switch(message) {
            case NEW_CONFIG:
                log.debug("Adding new project deployment configuration: " +
                          config);

                configurationData.add(config);
                configurationList.setListData(configurationData);

                break;

            case DELETE_CONFIG:
                log.debug("Deleting project deployment configuration " + 
                          config);

                if (configurationData.remove(config)) {
                    configurationList.setListData(configurationData);
                } else {
                    log.error("Project deployment configuration " + config +
                              " with id " + config.getID() + " not found.");
                }
                break;

            default:
                log.error("Unknown message:  " + msg);
                break;
            }
        }
    }


    protected class TestbedListSelectionListener
                                        implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            try {
                if (!e.getValueIsAdjusting()) {
                    Testbed testbed = (Testbed) testbedList.getSelectedValue();

                    if (testbed != null) {
                        int           id       = testbed.getID();
                        List<Project> projects =
                                            projectManager.getProjectList(id);

                        projectData = new Vector<Project>(projects);
                        projectList.setListData(projectData);

                        testbedName.setText(testbed.getName());
                        testbedDescription.setText(testbed.getDescription());
                    } else {
                        testbedName.setText("");
                        testbedDescription.setText("");

                        projectData = new Vector<Project>();
                        projectList.setListData(projectData);
                    }

                }
            } catch (RemoteException ex) {
                log.error("Exception occured while attemting " +
                          "to get project list.", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }


    protected class ProjectListSelectionListener
                                        implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            try {
                if (!e.getValueIsAdjusting()) {
                    Project project = (Project) projectList.getSelectedValue();

                    if (project != null) {
                        int                                  id;
                        List<ProjectDeploymentConfiguration> configs;

                        id      = project.getID();
                        configs = configManager.getProjectDeploymentConfigs(id);
                        configurationData =
                            new Vector<ProjectDeploymentConfiguration>(configs);
                        configurationList.setListData(configurationData);

                        projectName.setText(project.getName());
                        projectDescription.setText(project.getDescription());
                    } else {
                        projectName.setText("");
                        projectDescription.setText("");

                        configurationData =
                                new Vector<ProjectDeploymentConfiguration>();
                        configurationList.setListData(configurationData);
                    }
                }
            } catch (RemoteException ex) {
                log.error("Exception occured while attemting " +
                          "to get project config list.", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }


    protected class ConfigListSelectionListener
                                        implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                ProjectDeploymentConfiguration configuration;
                configuration = (ProjectDeploymentConfiguration)
                                        configurationList.getSelectedValue();

                if (configuration != null) {
                    configurationName.setText(configuration.getName());
                    configurationDesc.setText(configuration.getDescription());
                } else {
                    configurationName.setText("");
                    configurationDesc.setText("");
                }
            }
        }
    }


    protected class AddProjectActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                NewDialog newDialog =
                    new NewDialog(TestbedManagerFrame.this, "Add Project");
                newDialog.setVisible(true);

                String name        = newDialog.getName();
                String description = newDialog.getDescription();

                if (name != null && description != null) {
                    Testbed testbed = (Testbed)
                                            testbedList.getSelectedValue();
                    projectManager.createNewProject(testbed.getID(),
                                                    name, description);
                } else {
                    log.info("Add Project aborted by user");
                }
            } catch (RemoteException ex) {
                log.error("RemoteException", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }


    protected class DeleteProjectActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Project project = (Project) projectList.getSelectedValue();
                if (confirmDelete(project.getName())) {
                    projectManager.deleteProject(project.getID());
                }
            } catch (RemoteException ex) {
                log.error("RemoteException", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }

    private boolean confirmDelete(String text) {
        int choice;

        choice = JOptionPane.showConfirmDialog(this,
                                               "Do you wisth to delete '"
                                               + text + "'?",
                                               "Delete Confirmation",
                                               JOptionPane.YES_NO_OPTION,
                                               JOptionPane.QUESTION_MESSAGE);
        return (choice == JOptionPane.YES_OPTION);
    }


    protected class ProjectMenuListener implements MenuListener,
                                                   PopupMenuListener {
        private JMenuItem addProject;
        private JMenuItem deleteProject;


        public ProjectMenuListener(JMenuItem addProject,
                                   JMenuItem deleteProject) {
            this.addProject    = addProject;
            this.deleteProject = deleteProject;
        }


        public void menuSelected(MenuEvent e) {
            setMenuItemState();
        }


        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            setMenuItemState();
        }


        public void menuCanceled(MenuEvent e)                      { }
        public void menuDeselected(MenuEvent e)                    { }
        public void popupMenuCanceled(PopupMenuEvent e)            { }
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { }


        private void setMenuItemState() {
            addProject.setEnabled(testbedList.getSelectedValue() != null);
            deleteProject.setEnabled(projectList.getSelectedValue() != null);
        }
    }


    protected class AddConfigurationActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                NewDialog newDialog = new NewDialog(TestbedManagerFrame.this,
                                                    "Add Configuration");
                newDialog.setVisible(true);

                String name        = newDialog.getName();
                String description = newDialog.getDescription();

                if (name != null && description != null) {
                    Project project = (Project) projectList.getSelectedValue();

                    configManager.createNewProjectDeploymentConfig(
                                                         project.getID(),
                                                         name, description);
                } else {
                    log.info("Add Configuration aborted by user");
                }
            } catch (RemoteException ex) {
                log.error("RemoteException", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }


    protected class CloneConfigurationActionListener
                                                implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                NewDialog newDialog = new NewDialog(TestbedManagerFrame.this,
                                                    "Clone Configuration");
                newDialog.setVisible(true);

                String name        = newDialog.getName();
                String description = newDialog.getDescription();

                if (name != null && description != null) {
                    ProjectDeploymentConfiguration config;
                    config = (ProjectDeploymentConfiguration)
                                        configurationList.getSelectedValue();

                    log.info("Cloning config: " + config);
                    configManager.cloneProjectDeploymentConfig(config.getID(),
                                                               name,
                                                               description);
                }
            } catch (RemoteException ex) {
                log.error("RemoteException", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }


    protected class DeleteConfigurationActionListener
                                                implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ProjectDeploymentConfiguration config;
                config = (ProjectDeploymentConfiguration)
                                    configurationList.getSelectedValue();

                if (confirmDelete(config.getName())) {
                    configManager.deleteProjectDeploymentConfig(config.getID());
                }
            } catch (RemoteException ex) {
                log.error("RemoteException", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }


    protected class ConfigurationMenuListener implements MenuListener,
                                                         PopupMenuListener {
        private JMenuItem addConfig;
        private JMenuItem cloneConfig;
        private JMenuItem modifyConfig;
        private JMenuItem deleteConfig;
        private JMenuItem viewNetMonitor;


        public ConfigurationMenuListener(JMenuItem addConfig,
                                         JMenuItem cloneConfig,
                                         JMenuItem deleteConfig,
                                         JMenuItem modifyConfig,
                                         JMenuItem viewNetMonitor) {
            this.addConfig      = addConfig;
            this.cloneConfig    = cloneConfig;
            this.modifyConfig   = modifyConfig;
            this.deleteConfig   = deleteConfig;
            this.viewNetMonitor = viewNetMonitor;
        }


        public void menuSelected(MenuEvent e) {
            setMenuItemState();
        }


        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            setMenuItemState();
        }

        public void menuCanceled(MenuEvent e)                      { }
        public void menuDeselected(MenuEvent e)                    { }
        public void popupMenuCanceled(PopupMenuEvent e)            { }
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { }


        private void setMenuItemState() {
            boolean newEnabled   = projectList.getSelectedValue()       != null;
            boolean otherEnabled = configurationList.getSelectedValue() != null;

            addConfig.setEnabled(newEnabled);
            cloneConfig.setEnabled(otherEnabled);
            modifyConfig.setEnabled(otherEnabled);
            deleteConfig.setEnabled(otherEnabled);
            viewNetMonitor.setEnabled(otherEnabled);
        }
    }


    protected class ViewConfigurationActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (configManagerFrame != null) {
                    configManagerFrame.setVisible(false);
                }

                Testbed                        testbed;
                Project                        project;
                ProjectDeploymentConfiguration config;

                testbed = (Testbed) testbedList.getSelectedValue();
                project = (Project) projectList.getSelectedValue();
                config  = (ProjectDeploymentConfiguration)
                                    configurationList.getSelectedValue();

                configManagerFrame = new ConfigManagerFrame(testbed, project,
                                                            config);
                configManagerFrame.setVisible(true);
            } catch (Exception ex) {
                log.error("Exception occured while opening config " +
                          "manager frame", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }
    

    protected class ViewNetworkMonitorActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Testbed                        testbed;
                Project                        project;
                ProjectDeploymentConfiguration config;
                JFrame                         networkMonitorFrame;

                testbed = (Testbed) testbedList.getSelectedValue();
                project = (Project) projectList.getSelectedValue();
                config  = (ProjectDeploymentConfiguration)
                                    configurationList.getSelectedValue();

                networkMonitorFrame = new NetworkMonitorFrame(testbed, project,
                                                              config);
                networkMonitorFrame.setVisible(true);
            } catch (Exception ex) {
                log.error("Exception occured while opening network " +
                          "monitor frame", ex);
                ClientUtils.displayErrorMessage(TestbedManagerFrame.this, ex);
            }
        }
    }
}
