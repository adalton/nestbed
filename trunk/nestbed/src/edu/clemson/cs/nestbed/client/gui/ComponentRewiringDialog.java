/* $Id:$ */
/*
 * ComponentRewiringDialog.java
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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;


public class ComponentRewiringDialog extends    JDialog
                                     implements PropertyChangeListener {

    private final static String OK_STRING     = "Ok";
    private final static int    WIDTH         = 500;
    private final static int    HEIGHT        = 350;

    private JOptionPane optionPane;
    private String      radioComponent = "GenericComm";
    private JTable      wiringTable = new JTable(new WiringTableModel());


    /** Creates the reusable dialog. */
    public ComponentRewiringDialog(Frame aFrame) {
        super(aFrame, true);
        setSize(WIDTH, HEIGHT);
        setTitle("Component Rewiring");

        Object[] options = { OK_STRING };
        optionPane = new JOptionPane(buildMainPanel(),
                                     JOptionPane.QUESTION_MESSAGE,
                                     JOptionPane.YES_NO_OPTION,
                                     null,
                                     options,
                                     options[0]);

        setContentPane(optionPane);

        // Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.out.println("hi");
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
            }
        });

        // Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
    }


    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (        isVisible()
                 && (e.getSource() == optionPane)
                 && (   JOptionPane.VALUE_PROPERTY.equals(prop)
                     || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                // ignore reset
                return;
            }

            // Reset the JOptionPane's value.
            // If you don't do this, then if the user
            // presses the same button next time, no
            // property change event will be fired.
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

            setVisible(false);
        }
    }


    public Map<String, String> getRewiringMap() {
        Map<String, String> map = ((WiringTableModel)
                                       wiringTable.getModel()).getRewiringMap();

        if (!radioComponent.equals("GenericComm")) {
            map.put("GenericComm", radioComponent);
        }

        return map;
    }


    private JPanel buildMainPanel() {
        JPanel panel = new JPanel();
        int    rows  = 2;
        int    cols  = 1;

        panel.setLayout(new GridLayout(rows, cols));

        panel.add(buildTopPanel());
        panel.add(buildBottomPanel());

        return panel;
    }


    private JPanel buildTopPanel() {
        JPanel panel = new JPanel();
        int    rows  = 4;
        int    cols  = 1;
        panel.setLayout(new GridLayout(rows, cols));
        panel.setBorder(new TitledBorder("Radio Stack Selection"));

        JRadioButton genericComm      = new JRadioButton("GenericComm", true);
        JRadioButton reliableComm     = new JRadioButton("ReliableComm");
        JRadioButton uniformLossyComm = new JRadioButton("UniformLossyComm");

        genericComm.setActionCommand("GenericComm");
        reliableComm.setActionCommand("ReliableComm");
        uniformLossyComm.setActionCommand("UniformLossyComm");

        genericComm.addActionListener(new RadioButtonActionListener());
        reliableComm.addActionListener(new RadioButtonActionListener());
        uniformLossyComm.addActionListener(new RadioButtonActionListener());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(genericComm);
        buttonGroup.add(reliableComm);
        buttonGroup.add(uniformLossyComm);

        panel.add(new JLabel("Select the radio stack implementation to use:"));
        panel.add(genericComm);
        panel.add(reliableComm);
        panel.add(uniformLossyComm);

        return panel;
    }


    private JPanel buildBottomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new TitledBorder("Alternate Component Selection"));

        JScrollPane scrollPane = new JScrollPane(wiringTable);
        wiringTable.addMouseListener(new WiringTableMouseListener());

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }


    private class WiringTableMouseListener extends MouseAdapter {
        private JPopupMenu menu         = new JPopupMenu();
        private JMenuItem  addWiring    = new JMenuItem("Add Wiring");
        private JMenuItem  editWiring   = new JMenuItem("Edit Wiring");
        private JMenuItem  deleteWiring = new JMenuItem("Delete Wiring");


        public WiringTableMouseListener() {
            addWiring.addActionListener(new AddWiringActionListener());
            editWiring.addActionListener(new EditWiringActionListener());
            deleteWiring.addActionListener(new DeleteWiringActionListener());

            menu.add(addWiring);
            menu.add(editWiring);
            menu.add(deleteWiring);
        }

        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                int     selectedRow = wiringTable.getSelectedRow();
                boolean enabled     = true;

                if ((selectedRow == -1) ||
                    (selectedRow == wiringTable.getModel().getRowCount() - 1)) {
                    enabled = false;
                }
                editWiring.setEnabled(enabled);
                deleteWiring.setEnabled(enabled);

                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }


    private class AddWiringActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            WiringTableModel model = (WiringTableModel) wiringTable.getModel();

            RewiringDialog rewiringDialog = new RewiringDialog(
                                                  ComponentRewiringDialog.this,
                                                  "Add Rewiring");
            rewiringDialog.setVisible(true);

            String oldComponent = rewiringDialog.getOldComponent();
            String newComponent = rewiringDialog.getNewComponent();

            if (oldComponent != null && newComponent != null) {
                model.addRewiring(oldComponent, newComponent);
            }
        }
    }


    private class EditWiringActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            WiringTableModel model;
            Rewiring         rewiring;
            RewiringDialog   rewiringDialog;

            model          = (WiringTableModel) wiringTable.getModel();
            rewiring       = model.getRewiring(wiringTable.getSelectedRow());
            rewiringDialog = new RewiringDialog(ComponentRewiringDialog.this,
                                                "Edit Rewiring");

            rewiringDialog.setOldComponent(rewiring.getOldComponent());
            rewiringDialog.setNewComponent(rewiring.getNewComponent());
            rewiringDialog.setVisible(true);

            String oldComponent = rewiringDialog.getOldComponent();
            String newComponent = rewiringDialog.getNewComponent();

            if (oldComponent != null && newComponent != null) {
                rewiring.setOldComponent(oldComponent);
                rewiring.setNewComponent(newComponent);
                model.fireTableDataChanged();
            }
        }
    }


    private class DeleteWiringActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            WiringTableModel model;

            model = (WiringTableModel) wiringTable.getModel();
            model.deleteRewiring(wiringTable.getSelectedRow());
        }
    }


    private class WiringTableModel extends AbstractTableModel {
        private final static int NUM_COLUMNS = 2;

        private List<Rewiring> rewiringList = new ArrayList<Rewiring>();


        public int getColumnCount() {
            return NUM_COLUMNS;
        }


        public int getRowCount() {
            return rewiringList.size() + 1;
        }


        public String getColumnName(int col) {
            String name = "";

            switch(col) {
                case 0: name = "Original Component"; break;
                case 1: name = "New Component";      break;
            }

            return name;
        }


        public Object getValueAt(int row, int col) {
            String value = "";

            if (row < rewiringList.size()) {
                Rewiring rewiring = rewiringList.get(row);

                if (col == 0) {
                    value = rewiring.getOldComponent();
                } else if (col == 1) {
                    value = rewiring.getNewComponent();
                }
            }

            return value;
        }


        public void addRewiring(String oldComponent, String newComponent) {
            rewiringList.add(new Rewiring(oldComponent, newComponent));
            fireTableDataChanged();
        }


        public Rewiring getRewiring(int row) {
            return rewiringList.get(row);
        }

        public void deleteRewiring(int row) {
            rewiringList.remove(row);
            fireTableDataChanged();
        }

        public Map<String, String> getRewiringMap() {
            Map<String, String> map = new HashMap<String, String>();

            for (Rewiring i : rewiringList) {
                map.put(i.getOldComponent(), i.getNewComponent());
            }

            return map;
        }
    }


    private class Rewiring {
        private String oldComponent;
        private String newComponent;


        public Rewiring(String oldComponent, String newComponent) {
            this.oldComponent = oldComponent;
            this.newComponent = newComponent;
        }


        public String getOldComponent() {
            return oldComponent;
        }


        public void setOldComponent(String oldComponent) {
            this.oldComponent = oldComponent;
        }


        public String getNewComponent() {
            return newComponent;
        }

        public void setNewComponent(String newComponent) {
            this.newComponent = newComponent;
        }
    }


    private class RadioButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            radioComponent = e.getActionCommand();
        }
    }


    public static void main(String[] args) {
        ComponentRewiringDialog crd = new ComponentRewiringDialog(null);
        crd.setVisible(true);

        Map<String, String> m = crd.getRewiringMap();

        for (String i : m.keySet()) {
            System.out.println(i + " -> " + m.get(i));
        }
    }

}
