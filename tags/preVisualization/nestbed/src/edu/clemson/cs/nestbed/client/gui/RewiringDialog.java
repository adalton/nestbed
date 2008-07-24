/* $Id$ */
/*
 * RewiringDialog.java
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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;


public class RewiringDialog extends    JDialog
                            implements ActionListener, PropertyChangeListener {
    private final static String OK_STRING     = "Ok";
    private final static String CANCEL_STRING = "Cancel";
    private final static int    WIDTH         = 350;
    private final static int    HEIGHT        = 225;

    private String      oldComponent;
    private String      newComponent;

    private JTextField  oldComponentField;
    private JTextField  newComponentField;

    private JOptionPane optionPane;


    public String getOldComponent() {
        return oldComponent;
    }


    public void setOldComponent(String oldComponent) {
        oldComponentField.setText(oldComponent);
    }


    public String getNewComponent() {
        return newComponent;
    }

    public void setNewComponent(String newComponent) {
        newComponentField.setText(newComponent);
    }


    /** Creates the reusable dialog. */
    public RewiringDialog(JDialog aDialog, String title) {
        super(aDialog, true);
        init(title);
    }

    public RewiringDialog(Frame aFrame, String title) {
        super(aFrame, true);
        init(title);
    }

    private final void init(String title) {
        setSize(WIDTH, HEIGHT);
        setTitle(title);

        JPanel  panel      = new JPanel();
        int     rows       = 6;
        int     cols       = 1;

        panel.setLayout(new GridLayout(rows, cols));

        oldComponentField = new JTextField(10);
        newComponentField = new JTextField(10);

        panel.add(new JLabel("Old Component Name:"));
        panel.add(oldComponentField);
        panel.add(new JLabel("New Component Name:"));
        panel.add(newComponentField);

        Object[] options = { OK_STRING, CANCEL_STRING };

        optionPane = new JOptionPane(panel,
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
                /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
            }
        });

        // Ensure the name field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                oldComponentField.requestFocusInWindow();
            }
        });

        // Register an event handler that puts the text into the option pane.
        oldComponentField.addActionListener(this);
        newComponentField.addActionListener(this);

        // Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
    }


    /** This method handles events for the text field. */
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(OK_STRING);
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

            if (OK_STRING.equals(value)) {
                oldComponent  = oldComponentField.getText().trim();
                newComponent  = newComponentField.getText().trim();

                if (     (oldComponent == null)
                      || (oldComponent.equals(""))
                      || (oldComponent.indexOf(" ") != -1) ) {
                    JOptionPane.showMessageDialog(RewiringDialog.this,
                                        "'" + oldComponent + "' is not a " +
                                        "valid component name.",
                                        "Invalid Component Name",
                                        JOptionPane.ERROR_MESSAGE);
                    oldComponentField.selectAll();
                    oldComponentField.requestFocusInWindow();
                    oldComponent = null;
                } else if (     (newComponent == null)
                             || (newComponent.equals(""))
                             || (newComponent.indexOf(" ") != -1) ) {
                    JOptionPane.showMessageDialog(RewiringDialog.this,
                                        "'" + newComponent + "' is not a " +
                                        "valid component name.",
                                        "Invalid Component Name",
                                        JOptionPane.ERROR_MESSAGE);
                    newComponentField.selectAll();
                    newComponentField.requestFocusInWindow();
                    newComponent = null;
                } else {
                    // We're done; clear and dismiss the dialog
                    clearAndHide();
                }
            } else { // User closed dialog or clicked cancel
                oldComponent = null;
                newComponent = null;
                clearAndHide();
            }
        }
    }


    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        oldComponentField.setText(null);
        newComponentField.setText(null);
        setVisible(false);
    }
}
