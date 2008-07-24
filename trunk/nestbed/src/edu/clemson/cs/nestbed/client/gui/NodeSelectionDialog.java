/* $Id$ */
/*
 * NodeSelectionDialog.java
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2007
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
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.table.AbstractTableModel;

import edu.clemson.cs.nestbed.common.model.Program;

import edu.clemson.cs.nesctk.records.*;

public class NodeSelectionDialog extends JFrame {
    private Map<String, DefaultMutableTreeNode>             moduleNodeMap = new TreeMap<String, DefaultMutableTreeNode>();
    private List<TraceModuleRecord>                         modules;
    private List<TraceFunctionRecord>                       functions;
    private Map<FunctionRecord, List<FunctionCalleeRecord>> callMap;



    private Program                 program;
    private JCheckBox[][]           boxes;
    private Map<Integer, JCheckBox> boxMap         = new TreeMap<Integer, JCheckBox>();
    private JTree                   tree;
    private JTable                  table          = new JTable(new SelectionTableModel());
    private JButton                 showSeq        = new JButton("Sequence Diagram");
    private JButton                 showSubSeq     = new JButton("Subsequence Diagram");
    //private JButton                 showCf         = new JButton("Control Flow");
    //private JButton                 cancel         = new JButton("Cancel");
    private JSlider                 startingSlider = new JSlider(SwingConstants.HORIZONTAL);
    private JSlider                 countSlider    = new JSlider(SwingConstants.HORIZONTAL);
    private JTextField              startValue     = new JTextField(5);
    private JTextField              countValue     = new JTextField(5);

    public NodeSelectionDialog(GridPanel                                       gridPanel,
                               Program                                         program,
                               List<TraceModuleRecord>                         modules,
                               List<TraceFunctionRecord>                       functions,
                               Map<FunctionRecord, List<FunctionCalleeRecord>> callMap) {
        super("Visualization Filter");

        int width           = gridPanel.getRows();
        int height          = gridPanel.getCols();
        int startingAddress = 0;

        this.program = program;
        boxes   = new JCheckBox[width][height];

        table.addMouseListener(new TableMouseListener());

        this.callMap   = callMap;
        this.modules   = modules;
        this.functions = functions;

        JMenuItem removeSymbol = new JMenuItem("Remove");
        removeSymbol.addActionListener(new RemoveSymbolListener());

        add(buildTopPanel(gridPanel, startingAddress), BorderLayout.NORTH);
        add(buildSelectionPanel(),                     BorderLayout.CENTER);
        add(buildButtonPanel(),                        BorderLayout.SOUTH);
        pack();
    }

    private JComponent buildTopPanel(GridPanel gridPanel, int startingAddress) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buildCheckBoxPanel(gridPanel, startingAddress), BorderLayout.CENTER);
        panel.add(buildSliderPanel(),                             BorderLayout.SOUTH);
        return panel;
    }

    private JComponent buildSliderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Events"));

        setSlidersEnabled(false);

        startValue.setEnabled(false);
        startingSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                startValue.setText(startingSlider.getValue() + "");
            }
        });

        countValue.setEnabled(false);
        countSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                countValue.setText(countSlider.getValue() + "");
            }
        });

        JPanel labelPanel;
        JPanel innerPanel = new JPanel(new GridLayout(2, 3));
        labelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        labelPanel.add(new JLabel("Starting Event:"));
        innerPanel.add(labelPanel);
        innerPanel.add(startingSlider);
        innerPanel.add(startValue);

        labelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        labelPanel.add(new JLabel("Count:"));
        innerPanel.add(labelPanel);
        innerPanel.add(countSlider);
        innerPanel.add(countValue);

        panel.add(innerPanel, BorderLayout.CENTER);
        return panel;
    }
/*
    private JComponent buildSliderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Events"));

        setSlidersEnabled(false);

        startValue.setEnabled(false);
        startingSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                startValue.setText(startingSlider.getValue() + "");
            }
        });
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Starting Event:"));
        topPanel.add(startingSlider);
        topPanel.add(startValue);
        panel.add(topPanel, BorderLayout.NORTH);


        countValue.setEnabled(false);
        countSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                countValue.setText(countSlider.getValue() + "");
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Count:"));
        bottomPanel.add(countSlider);
        bottomPanel.add(countValue);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
*/

    public void setStartRange(int min, int max) {
        setRange(startingSlider, min, max);
        setRange(countSlider, min, max);
    }

    private void setRange(JSlider slider, int min, int max) {
        slider.setMinimum(min);
        slider.setMaximum(max);

        if (max > 0) {
            slider.setLabelTable(slider.createStandardLabels((max - min) / 4));
        }
    }

    public void setSlidersEnabled(boolean enabled) {
        startingSlider.setEnabled(enabled);
        startingSlider.setPaintLabels(enabled);

        countSlider.setEnabled(enabled);
        countSlider.setPaintLabels(enabled);


        if (enabled) {
            //startValue.setText(startingSlider.getValue() + "");
            //countValue.setText(countSlider.getValue() + "");
            startValue.setText("0");
            countValue.setText("0");
        } else {
            startValue.setText("");
            countValue.setText("");
        }
    }


    public int getStartIndex() {
        return startingSlider.getValue();
    }

    public int getEndIndex() {
        return startingSlider.getValue() + countSlider.getValue();
    }


    private JComponent buildSelectionPanel() {
        return new JSplitPane(JSplitPane.VERTICAL_SPLIT, buildTreePane(), buildTablePane());
    }


    private JComponent buildTreePane() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Module/Action List"));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        for (FunctionRecord i : callMap.keySet()) {
            outer:
            for (TraceModuleRecord tmr : modules) {
                if (i.getModuleName().endsWith(tmr.getModuleName())) {
                    for (TraceFunctionRecord tfr : functions) {
                        if (i.getModuleName().endsWith(tfr.getModuleName()) && getFullName(i).equals(getFullName(tfr))) {
                            DefaultMutableTreeNode moduleNode = moduleNodeMap.get(i.getModuleName());

                            if (moduleNode == null) {
                                moduleNode = new DefaultMutableTreeNode(i.getModuleName());
                                moduleNodeMap.put(i.getModuleName(), moduleNode);
                                root.add(moduleNode);
                            }

                            DefaultMutableTreeNode functionNode = new DefaultMutableTreeNode(getFullName(i));
                            moduleNode.add(functionNode);
                            break outer;
                        }
                    }
                }
            }
        }

        tree = new JTree(new DefaultTreeModel(root));
        tree.addMouseListener(new TreeMouseListener());
        tree.setRootVisible(false);

        panel.add(new JScrollPane(tree), BorderLayout.CENTER);
        return panel;
    }


    private JComponent buildTablePane() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Selected Modules/Actions"));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JComponent buildCheckBoxPanel(GridPanel gridPanel, int startingAddress) {
        int width  = gridPanel.getRows();
        int height = gridPanel.getCols();

        JPanel panel = new JPanel(new GridLayout(width, height));
        panel.setBorder(new TitledBorder("Node Selection"));

        //cancel.addActionListener(new ActionListener() {
        //    public void actionPerformed(ActionEvent e) {
        //        NodeSelectionDialog.this.setVisible(false);
        //        NodeSelectionDialog.this.dispose();
        //    }
        //});

        int next = startingAddress;

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                boxes[i][j] = new JCheckBox("" + next);
                boxes[i][j].setName("" + next);
                boxMap.put(next, boxes[i][j]);
                panel.add(boxes[i][j]);

                MoteManagementPanel mmp = ((MoteManagementPanel) gridPanel.getComponentAt(i, j));

                if (       (mmp                      == null)
                        || (mmp.getProgram()         == null)
                        || (mmp.getProgram().getID() != program.getID()) ) {
                    boxes[i][j].setEnabled(false);
                }

                next++;
            }
        }

        return panel;
    }

    public void setCheckBoxListener(ActionListener listener) {
        for (int i = 0; i < boxes.length; ++i) {
            for (int j = 0; j < boxes[i].length; ++j) {
                boxes[i][j].addActionListener(listener);
            }
        }
    }

    private JComponent buildButtonPanel() {
        JPanel panel = new JPanel();

        panel.add(showSeq);
        panel.add(showSubSeq);
        //panel.add(showCf);
        //panel.add(cancel);

        return panel;
    }

    public List<Integer> getSelected() {
        List<Integer> selected = new ArrayList<Integer>();

        for (int i = 0; i < boxes.length; ++i) {
            for (int j = 0; j < boxes[i].length; ++j) {
                if (boxes[i][j].isSelected()) {
                    selected.add(Integer.parseInt(boxes[i][j].getName()));
                }
            }
        }

        return selected;
    }

    private String getFullName(FunctionRecord record) {
        String name = "";
        if (!record.getInterfaceRecord().getAsName().equals("")) {
            name = record.getInterfaceRecord().getAsName() + ".";
        }
        name = name + record.getFunctionName();

        return name;
    }


    public void addSequenceDiagramActionListener(ActionListener listener) {
        showSeq.addActionListener(listener);
    }


    public void addSubSequenceDiagramActionListener(ActionListener listener) {
        showSubSeq.addActionListener(listener);
    }

    public void addGlobalViewActionListener(ActionListener listener) {
        //showCf.addActionListener(listener);
    }

    public Map<Integer, List<Integer>> getIdModuleIncludeMap() {
        return ((SelectionTableModel) table.getModel()).getIdModuleIncludeMap();
    }

    private class TreeMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int selectedRow = tree.getRowForLocation(e.getX(), e.getY());

            if (selectedRow != -1 && e.getClickCount() == 2) {
                TreePath selectedPath = tree.getPathForLocation(e.getX(), e.getY());

                if (selectedPath.getPathCount() == 3) {
                    String module   = (String) ((DefaultMutableTreeNode) selectedPath.getPathComponent(1)).getUserObject();
                    String function = (String) ((DefaultMutableTreeNode) selectedPath.getPathComponent(2)).getUserObject();
                    SelectionTableModel model = (SelectionTableModel) table.getModel();
                    model.addNewRow(module, function);
                }
            }
        }
    }

    private class TableMouseListener extends MouseAdapter {
        private JPopupMenu menu;
        private JMenuItem  deleteSymbol;

        public TableMouseListener() {
            menu         = new JPopupMenu();
            deleteSymbol = new JMenuItem("Remove");
            deleteSymbol.addActionListener(new RemoveSymbolListener());

            menu.add(deleteSymbol);
        }

        public void mouseClicked(MouseEvent e) {
            int row = table.getSelectedRow();

            if (       SwingUtilities.isRightMouseButton(e)
                    && (row != -1)
                    && (row != (table.getRowCount() - 1))) {
                menu.show(table, e.getX(), e.getY());
            }
        }
    }


    private class SelectionTableModel extends AbstractTableModel {
        private int NUM_COLUMNS = 2;

        private List<String>  modules     = new ArrayList<String>();
        private List<String>  functions   = new ArrayList<String>();
        private List<Integer> moduleIDs   = new ArrayList<Integer>();
        private List<Integer> functionIDs = new ArrayList<Integer>();

        public int getColumnCount() {
            return NUM_COLUMNS;
        }

        public int getRowCount() {
            return functions.size() + 1;
        }

        public String getColumnName(int column) {
            String name = "UNKNOWN5";

            switch(column) {
            case 0:
                name = "Module";
                break;

            case 1:
                name = "Action";
                break;
            }

            return name;
        }

        public Class<?> getColumnClass(int columnIndex) {
            return java.lang.String.class;
        }

        public Object getValueAt(int row, int column) {
            Object value = "";

            if (column == 0 && row < modules.size()) {
                value = modules.get(row);
            } else if (column == 1 && row < functions.size()){
                value = functions.get(row);
            }

            return value;
        }

        public String getModule(int row) {
            return modules.get(row);
        }

        public String getFunction(int row) {
            return functions.get(row);
        }

        public void addNewRow(String module, String function) {
            boolean found = false;

            for (TraceModuleRecord tmr : NodeSelectionDialog.this.modules) {
                if (tmr.getModuleName().equals(module)) {
                    //System.out.printf("\n\n%s: %d\n", module, tmr.getTraceId());
                    moduleIDs.add(tmr.getTraceId());
                    break;
                }
            }


            for (TraceFunctionRecord tfr : NodeSelectionDialog.this.functions) {
                if (tfr.getModuleName().equals(module) && getFullName(tfr).equals(function)) {
                    //System.out.printf("%s: %d\n", function, tfr.getTraceId());
                    functionIDs.add(tfr.getTraceId());
                }
            }
            for (int i = 0; i < modules.size(); ++i) {
                if (modules.get(i).equals(module) && functions.get(i).equals(function)) {
                    found = true;
                }
            }

            if (!found) {
                modules.add(module);
                functions.add(function);
                fireTableRowsInserted(modules.size(), modules.size());
            }
        }

        public void deleteRow(int row) {
            modules.remove(row);
            functions.remove(row);
            moduleIDs.remove(row);
            functionIDs.remove(row);
            fireTableRowsInserted(modules.size(), modules.size());
        }

        public Map<String, List<String>> getModuleIncludeMap() {
            Map<String, List<String>> includeMap = new HashMap<String, List<String>>();

            for (int i = 0; i < modules.size(); ++i) {
                String moduleName   = modules.get(i);
                String functionName = functions.get(i);

                List<String> functionList = includeMap.get(moduleName);
                if (functionList == null) {
                    functionList = new ArrayList<String>();
                    includeMap.put(moduleName, functionList);
                }
                functionList.add(functionName);
            }

            return includeMap;
        }

        public Map<Integer, List<Integer>> getIdModuleIncludeMap() {
            Map<Integer, List<Integer>> includeMap = new HashMap<Integer, List<Integer>>();

            for (int i = 0; i < moduleIDs.size(); ++i) {
                Integer moduleId   = moduleIDs.get(i);
                Integer functionid = functionIDs.get(i);

                List<Integer> functionList = includeMap.get(moduleId);
                if (functionList == null) {
                    functionList = new ArrayList<Integer>();
                    includeMap.put(moduleId, functionList);
                }
                functionList.add(functionid);
            }

            return includeMap;
        }
    }

    protected class RemoveSymbolListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SelectionTableModel model = (SelectionTableModel) table.getModel();
            model.deleteRow(table.getSelectedRow());
        }
    }
}
