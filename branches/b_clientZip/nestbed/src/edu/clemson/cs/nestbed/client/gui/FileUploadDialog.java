/* $Id$ */
/*
 * FileUploadDialog.java
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


public class FileUploadDialog extends    JDialog
                              implements ActionListener,
                                         PropertyChangeListener {

    private final static String OK_STRING     = "Ok";
    private final static String CANCEL_STRING = "Cancel";
    private final static int    WIDTH         = 350;
    private final static int    HEIGHT        = 225;

    private String      name;
    private String      description;
    private String      filename;

    private JTextField  nameField;
    private JTextField  descField;
    private JTextField  fileField;

    private JOptionPane optionPane;


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public String getFilename() {
        return filename;
    }


    /** Creates the reusable dialog. */
    public FileUploadDialog(Frame aFrame, String title) {
        super(aFrame, true);

        setSize(WIDTH, HEIGHT);
        setTitle(title);

        JButton fileButton = new JButton("...");
        JPanel  filePanel  = new JPanel();
        JPanel  panel      = new JPanel();
        int     rows       = 6;
        int     cols       = 1;

        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileFilter() {
                    public boolean accept(File f) {
                        return (f.getName().toLowerCase().endsWith(".zip") ||
                                f.isDirectory());
                    }


                    public String getDescription() {
                        return "Zipped nesC Source Project";
                    }
                });

                int returnVal = chooser.showOpenDialog(FileUploadDialog.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    fileField.setText(
                                chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        fileButton.setPreferredSize(new Dimension(15, 10));
        panel.setLayout(new GridLayout(rows, cols));
        filePanel.setLayout(new BorderLayout());

        nameField = new JTextField(10);
        descField = new JTextField(10);
        fileField = new JTextField(10);
        fileField.setEditable(false);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(descField);
        panel.add(new JLabel("File:"));

        filePanel.add(fileButton, BorderLayout.EAST);
        filePanel.add(fileField, BorderLayout.CENTER);
        panel.add(filePanel);

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
                nameField.requestFocusInWindow();
            }
        });

        // Register an event handler that puts the text into the option pane.
        nameField.addActionListener(this);

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
                name        = nameField.getText().trim();
                description = descField.getText().trim();
                filename    = fileField.getText().trim();

                if        (name == null || name.equals("")) {
                    JOptionPane.showMessageDialog(FileUploadDialog.this,
                                        "'" + name + "' is not a valid " +
                                        "program name.",
                                        "Invalid Program Name",
                                        JOptionPane.ERROR_MESSAGE);
                    nameField.selectAll();
                    nameField.requestFocusInWindow();
                    name = null;
                } else if (description == null || description.equals("")) {
                    JOptionPane.showMessageDialog(FileUploadDialog.this,
                                        "'" + description + "' is not a valid " +
                                        "program description.",
                                        "Invalid Program Description",
                                        JOptionPane.ERROR_MESSAGE);
                    descField.selectAll();
                    descField.requestFocusInWindow();
                    description = null;
                } else if (filename == null || filename.equals("")) {
                    JOptionPane.showMessageDialog(FileUploadDialog.this,
                                        "'" + filename + "' is not a valid " +
                                        "file.",
                                        "Invalid Program File",
                                        JOptionPane.ERROR_MESSAGE);
                    fileField.selectAll();
                    fileField.requestFocusInWindow();
                    filename = null;
                } else {
                    // We're done; clear and dismiss the dialog
                    clearAndHide();
                }
            } else { // User closed dialog or clicked cancel
                name        = null;
                description = null;
                filename    = null;
                clearAndHide();
            }
        }
    }


    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        nameField.setText(null);
        descField.setText(null);
        fileField.setText(null);
        setVisible(false);
    }
}
