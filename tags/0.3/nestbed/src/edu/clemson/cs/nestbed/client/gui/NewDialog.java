/*
 * NewDialog.java
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


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class NewDialog extends    JDialog
                       implements ActionListener,
                                  PropertyChangeListener {

    private String          name         = null;
    private String          description  = null;

    private String          okString     = "Ok";
    private String          cancelString = "Cancel";

    private JTextField      nameField;
    private JTextField      descriptionField;

    private JOptionPane     optionPane;


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public NewDialog(Frame aFrame, String title) {
        super(aFrame, true);

        setTitle(title);
        setSize(300, 170);

        nameField        = new JTextField(20);
        descriptionField = new JTextField(20);

        // Create an array of the text and components to be displayed.
        String msgString1 = "Name:";
        String msgString2 = "Description:";

        Object[] array = { msgString1, nameField,
                           msgString2, descriptionField };

        // Create an array specifying the number of dialog buttons
        // and their text.
        Object[] options = { okString, cancelString };

        // Create the JOptionPane.
        optionPane = new JOptionPane(array,
                                     JOptionPane.QUESTION_MESSAGE,
                                     JOptionPane.YES_NO_OPTION,
                                     null,
                                     options,
                                     options[0]);

        // Make this dialog display it.
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

        // Ensure the text field always gets the first focus.
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
        optionPane.setValue(okString);
    }


    /** This method reacts to state changes in the option pane. */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (   isVisible()
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

            if (okString.equals(value)) {
                name        = nameField.getText();
                description = descriptionField.getText();

                if        (name        == null || name.equals("")) {
                    JOptionPane.showMessageDialog(NewDialog.this,
                                        "'" + name + "' is not a valid " +
                                        "name.",
                                        "Invalid Name",
                                        JOptionPane.ERROR_MESSAGE);
                    nameField.selectAll();
                    nameField.requestFocusInWindow();
                    name = null;
                } else if (description == null || description.equals("")) {
                    JOptionPane.showMessageDialog(NewDialog.this,
                                        "'" + description +
                                        "' is not a valid " +
                                        "description.",
                                        "Invalid Description",
                                        JOptionPane.ERROR_MESSAGE);
                    descriptionField.selectAll();
                    descriptionField.requestFocusInWindow();
                    description = null;
                } else {
                    // We're done; clear and dismiss the dialog
                    clearAndHide();
                }
            } else { //user closed dialog or clicked cancel
                name        = null;
                description = null;
                clearAndHide();
            }
        }
    }


    /** This method clears the dialog and hides it. */
    public void clearAndHide() {
        nameField.setText(null);
        descriptionField.setText(null);
        setVisible(false);
    }
}

