/* $Id$ */
/*
 * MoteDetailFrame.java
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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingSymbolManager;
import edu.clemson.cs.nestbed.common.management.profiling.NucleusManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.MoteTestbedAssignment;
import edu.clemson.cs.nestbed.common.model.MoteType;
import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;


public class MoteDetailFrame extends JFrame {
    private final static Log log = LogFactory.getLog(MoteDetailFrame.class);

    private final static String RMI_BASE_URL;
    private final static int    WINDOW_WIDTH  = 400;
    private final static int    WINDOW_HEIGHT = 500;

    static {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");
    }


    private int                                  configID;
    private MoteTestbedAssignment                mtba;
    private Mote                                 mote;
    private MoteType                             moteType;
    private Program                              program;

    private ProgramSymbolManager                 programSymbolManager;
    private ProgramProfilingMessageSymbolManager progProfMsgSymManager;
    private ProgramProfilingSymbolManager        profSymManager;
    private ProgramMessageSymbolManager          progMsgSymManager;
    private NucleusManager                       nucleusManager;

    private List<ProgramProfilingSymbol>         profilingSymbols;
    private List<ProgramProfilingMessageSymbol>  progProfMsgSymbols;
    private Map<Integer, ProgramSymbol>          programSymbols;
    private Map<Integer, ProgramMessageSymbol>   programMessageSymbols;

    private JTextField  addressField      =  new JTextField();
    private JTextField  locationField     =  new JTextField();
    private JTextField  serialIdField     =  new JTextField();
    private JTextField  typeField         =  new JTextField();
    private JTextField  totalRomField     =  new JTextField();
    private JTextField  totalRamField     =  new JTextField();
    private JTextField  totalEepromField  =  new JTextField();
    private JTextField  programNameField  =  new JTextField();
    private JTextField  programDescField  =  new JTextField();

    private JTable      symbolTable       =  new JTable();
    private JTable      messageTable      =  new JTable();


    public MoteDetailFrame(int configID, MoteTestbedAssignment mtba,
                           Mote mote, MoteType moteType, Program program)
                                                throws RemoteException,
                                                       NotBoundException,
                                                       MalformedURLException,
                                                       ClassNotFoundException {
        super("Mote " + mtba.getMoteAddress() +
              " - (" + mote.getMoteSerialID() + ")");

        this.mtba     = mtba;
        this.mote     = mote;
        this.moteType = moteType;
        this.program  = program;

        lookupRemoteManagers();
        profilingSymbols   = profSymManager.getProgramProfilingSymbols(
                                                              configID,
                                                              program.getID());
        progProfMsgSymbols =
                progProfMsgSymManager.getProgramProfilingMessageSymbols(
                                                              configID,
                                                              program.getID());

        programSymbols     = new HashMap<Integer, ProgramSymbol>();
        List<ProgramSymbol> ps = programSymbolManager.getProgramSymbols(
                                                               program.getID());
        for (ProgramSymbol i : ps) {
            programSymbols.put(i.getID(), i);
        }

        programMessageSymbols = new HashMap<Integer, ProgramMessageSymbol>();
        List<ProgramMessageSymbol> pms =
                            progMsgSymManager.getProgramMessageSymbols(
                                                              program.getID());

        for (ProgramMessageSymbol i : pms) {
            programMessageSymbols.put(i.getID(), i);
        }



        symbolTable.addMouseListener(new ProfilingTableMouseListener());
        symbolTable.setModel(new ProfilingTableModel());
        symbolTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        messageTable.addMouseListener(new MessageTableMouseListener());
        messageTable.setModel(new MessageTableModel());
        messageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setJMenuBar(buildMenuBar());

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        c.add(buildMotePanel(),  BorderLayout.NORTH);
        c.add(buildBottomPane(), BorderLayout.CENTER);
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {

        profSymManager       = (ProgramProfilingSymbolManager) Naming.lookup(
                                RMI_BASE_URL + "ProgramProfilingSymbolManager");

        programSymbolManager = (ProgramSymbolManager) Naming.lookup(
                                RMI_BASE_URL + "ProgramSymbolManager");

        progMsgSymManager     = (ProgramMessageSymbolManager) Naming.lookup(
                                RMI_BASE_URL + "ProgramMessageSymbolManager");

        progProfMsgSymManager = (ProgramProfilingMessageSymbolManager)
                                 Naming.lookup(RMI_BASE_URL +
                                    "ProgramProfilingMessageSymbolManager");

        nucleusManager        = (NucleusManager) Naming.lookup(RMI_BASE_URL +
                                    "NucleusManager");
    }


    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(buildFileMenu());

        return menuBar;
    }


    private JMenu buildFileMenu() {
        JMenu     menu  = new JMenu("File");
        JMenuItem close = new JMenuItem("Close");

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MoteDetailFrame.this.setVisible(false);
            }
        });
        menu.add(close);

        return menu;
    }


    private JPanel buildMotePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Mote Information"));
        panel.setLayout(new BorderLayout());

        panel.add(buildMoteLabelPanel(), BorderLayout.WEST);
        panel.add(buildMoteFieldPanel(), BorderLayout.CENTER);

        return panel;
    }


    private JPanel buildMoteLabelPanel() {
        JPanel panel = new JPanel();
        int    rows  = 10;
        int    cols  = 1;

        panel.setLayout(new GridLayout(rows, cols));
        panel.add(new JLabel("Address:  "));
        panel.add(new JLabel("Location:  "));
        panel.add(new JLabel("Serial ID: "));
        panel.add(new JLabel("Type: "));
        panel.add(new JLabel("Total ROM: "));
        panel.add(new JLabel("Total RAM: "));
        panel.add(new JLabel("Total EEPROM: "));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Program Name: "));
        panel.add(new JLabel("Program Description:  "));

        return panel;
    }


    private JPanel buildMoteFieldPanel() {
        JPanel panel = new JPanel();
        int    rows  = 10;
        int    cols  = 1;

        panel.setLayout(new GridLayout(rows, cols));

        addressField.setEditable(false);
        locationField.setEditable(false);
        serialIdField.setEditable(false);
        typeField.setEditable(false);
        totalRomField.setEditable(false);
        totalRamField.setEditable(false);
        totalEepromField.setEditable(false);
        programNameField.setEditable(false);
        programDescField.setEditable(false);

        panel.add(addressField);
        panel.add(locationField);
        panel.add(serialIdField);
        panel.add(typeField);
        panel.add(totalRomField);
        panel.add(totalRamField);
        panel.add(totalEepromField);
        panel.add(new JLabel(""));
        panel.add(programNameField);
        panel.add(programDescField);

        addressField.setText(Integer.toString(mtba.getMoteAddress()));
        locationField.setText("("  + mtba.getMoteLocationY() +
                              ", " + mtba.getMoteLocationX() + ")");
        serialIdField.setText(mote.getMoteSerialID());
        typeField.setText(moteType.getName());
        totalRomField.setText(Integer.toString(moteType.getTotalROM()));
        totalRamField.setText(Integer.toString(moteType.getTotalRAM()));
        totalEepromField.setText(Integer.toString(moteType.getTotalEEPROM()));
        programNameField.setText(program.getName());
        programDescField.setText(program.getDescription());

        return panel;
    }


    private JPanel buildBottomPane() {
        JPanel      panel      = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();

        panel.setLayout(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.add("Symbol Profiling",  new JScrollPane(symbolTable));
        tabbedPane.add("Message Profiling", new JScrollPane(messageTable));

        return panel;
    }


    protected class ProfilingTableModel extends AbstractTableModel {
        private final static int MODULE_CELL = 0;
        private final static int SYMBOL_CELL = MODULE_CELL + 1;
        private final static int VALUE_CELL  = SYMBOL_CELL + 1;
        private final static int NUM_COLS    = VALUE_CELL  + 1;

        private ProgramProfilingSymbol[] symbols;
        private Integer[]                values;


        public ProfilingTableModel() {
            symbols = profilingSymbols.toArray(
                        new ProgramProfilingSymbol[profilingSymbols.size()]);
            values  = new Integer[symbols.length];
        }


        public int getColumnCount() {
            return NUM_COLS;
        }


        public int getRowCount() {
            return symbols.length;
        }


        public String getColumnName(int col) {
            String name = null;

            switch (col) {
            case MODULE_CELL:
                name = "Module";
                break;
            case SYMBOL_CELL:
                name = "Symbol";
                break;
            case VALUE_CELL:
                name = "Last Known Value";
                break;
            }

            return name;
        }


        public Object getValueAt(int row, int col) {
            Object value = null;

            switch (col) {
            case MODULE_CELL:
                value = programSymbols.get(
                        symbols[row].getProgramSymbolID()).getModule();
                break;
            case SYMBOL_CELL:
                value = programSymbols.get(
                        symbols[row].getProgramSymbolID()).getSymbol();
                break;
            case VALUE_CELL:
                value = (values[row] == null) ? "" : values[row];
                break;
            }

            return value;
        }


        public Class getColumnClass(int col) {
            Class columnClass = Object.class;

            switch (col) {
            case MODULE_CELL:
                columnClass = String.class;
                break;
            case SYMBOL_CELL:
                columnClass = String.class;
                break;
            case VALUE_CELL:
                columnClass = Integer.class;
                break;
            }
            return columnClass;
        }


        public boolean isCellEditable(int row, int col) {
            return (col == VALUE_CELL);
        }


        public void setValueAt(Object aValue, int row, int col) {
            log.debug("User-invoked setValueAt, row: " +
                      row + ", value: " + aValue);
            try {
                ProgramProfilingSymbol profilingSymbol = symbols[row];

                if (profilingSymbol != null) {
                    int value = ((Integer) aValue).intValue();
                    if (nucleusManager.setSymbol(value,
                                                 profilingSymbol.getID(),
                                                 program.getSourcePath(),
                                                 moteType.getTosPlatform(),
                                                 mote.getMoteSerialID())) {
                        values[row] = (Integer) aValue;
                    }
                    symbolTable.repaint();
                }
            } catch (Exception ex) {
                log.error("Exception", ex);
                ClientUtils.displayErrorMessage(MoteDetailFrame.this, ex);
            }
        }

        public void setValue(Integer value, int row) {
            log.debug("Setting value at row " + row + " to " + value);
            values[row] = value;
            symbolTable.repaint();
        }


        public ProgramProfilingSymbol getProfilingSymbol(int row) {
            return (row != symbols.length) ? symbols[row] : null;
        }
    }


    protected class MessageTableModel extends AbstractTableModel {
        private int NUM_COLS = 1;

        private ProgramProfilingMessageSymbol[] symbols;


        public MessageTableModel() {
            symbols = progProfMsgSymbols.toArray(
                new ProgramProfilingMessageSymbol[progProfMsgSymbols.size()]);
        }


        public int getColumnCount() {
            return NUM_COLS;
        }


        public int getRowCount() {
            return symbols.length;
        }


        public String getColumnName(int col) {
            String name = null;

            if (col == 0) { name = "Message"; }

            return name;
        }


        public Object getValueAt(int row, int col) {
            Object value = null;

            if (col == 0) {
                value = programMessageSymbols.get(
                        symbols[row].getProgramMessageSymbolID()).getName();
            }

            return value;
        }


        public ProgramProfilingMessageSymbol
                                        getProfilingMessageSymbol(int row) {
            return (row != symbols.length) ? symbols[row] : null;
        }
    }


    protected class ProfilingTableMouseListener extends MouseAdapter {
        private JPopupMenu menu;
        private JMenuItem  title;
        private JMenuItem  queryValue;


        public ProfilingTableMouseListener() {
            menu  = new JPopupMenu();
            title = new JMenuItem();

            title.setEnabled(false);
            menu.add(title);
            menu.add(new JSeparator());

            queryValue = new JMenuItem("Query Symbol Value");
            queryValue.addActionListener(new QuerySymbolActionListener());

            menu.add(queryValue);
        }


        public void mouseClicked(MouseEvent e) {
            int row = symbolTable.getSelectedRow();

            if (SwingUtilities.isRightMouseButton(e) && (row != -1)) {
                ProfilingTableModel    model;
                ProgramProfilingSymbol profilingSymbol;

                model           = (ProfilingTableModel) symbolTable.getModel();
                profilingSymbol = model.getProfilingSymbol(row);

                title.setText(model.getValueAt(row, 0).toString());
                menu.show(symbolTable, e.getX(), e.getY());
            }
        }
    }


    protected class MessageTableMouseListener extends MouseAdapter {
        private JPopupMenu menu;
        private JMenuItem  title;
        private JMenuItem  viewMsgMonitor;


        public MessageTableMouseListener() {
            menu           = new JPopupMenu();
            title          = new JMenuItem();
            viewMsgMonitor = new JMenuItem("View Message Monitor");
            viewMsgMonitor.addActionListener(
                                        new MessageMonitorActionListener());

            title.setEnabled(false);
            menu.add(title);
            menu.add(new JSeparator());
            menu.add(viewMsgMonitor);
        }


        public void mouseClicked(MouseEvent e) {
            int row = messageTable.getSelectedRow();

            if (SwingUtilities.isRightMouseButton(e) && (row != -1)) {
                MessageTableModel             model;
                ProgramProfilingMessageSymbol profMsgSymbol;

                model         = (MessageTableModel) messageTable.getModel();
                profMsgSymbol = model.getProfilingMessageSymbol(row);

                title.setText(model.getValueAt(row, 0).toString());
                menu.show(messageTable, e.getX(), e.getY());
            }
        }
    }


    protected class QuerySymbolActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ProfilingTableModel    model;
                ProgramProfilingSymbol profilingSymbol;

                model           = (ProfilingTableModel) symbolTable.getModel();
                profilingSymbol = model.getProfilingSymbol(
                                                    symbolTable.getSelectedRow());
                if (profilingSymbol != null) {
                    int value = nucleusManager.querySymbol(
                                                    profilingSymbol.getID(),
                                                    program.getSourcePath(),
                                                    moteType.getTosPlatform(),
                                                    mote.getMoteSerialID());

                    //model.setValueAt(value, symbolTable.getSelectedRow(), 1);
                    model.setValue(value, symbolTable.getSelectedRow());
                    symbolTable.repaint();
                }
            } catch (Exception ex) {
                log.error("Exception", ex);
                ClientUtils.displayErrorMessage(MoteDetailFrame.this, ex);
            }
        }
    }


    protected class MessageMonitorActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                MessageTableModel             model;
                ProgramProfilingMessageSymbol symbol;

                model  = (MessageTableModel) messageTable.getModel();
                symbol = model.getProfilingMessageSymbol(
                                                messageTable.getSelectedRow());

                MessageMonitorFrame monitor = new MessageMonitorFrame(mote,
                                                                      symbol);
                monitor.setVisible(true);
            } catch (Exception ex) {
                log.error("Exception: ", ex);
                ClientUtils.displayErrorMessage(MoteDetailFrame.this, ex);
            }
        }
    }
}
