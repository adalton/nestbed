/* $Id:$ */
/*
 * ClientUtils.java
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


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ClientUtils {
    public static void displayErrorMessage(Component component, Throwable t) {
        Frame parent = null;

        for (Component c = component; c != null && parent == null;
                                                            c = c.getParent()) {
            if (c instanceof Frame) {
                parent = (Frame) c;
            }
        }

        JDialog dialog = createExceptionDialog(parent,
                                               t.getClass().getName(), t);
        dialog.setVisible(true);
    }


    private static JDialog createExceptionDialog(Frame     parent, String title,
                                                 Throwable error) {
        ByteArrayOutputStream messageStream = new ByteArrayOutputStream();
        PrintWriter           out           = new PrintWriter(messageStream);
        error.printStackTrace(out);
        out.close();

        JTextField  errorMsg  = new JTextField(error.toString());
        JTextArea   errorText = new JTextArea(messageStream.toString());
        JScrollPane textPane  = new JScrollPane(errorText);

        errorText.setEditable(false);
        errorMsg.setEditable(false);

        textPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane warning = new JOptionPane(textPane,
                                              JOptionPane.ERROR_MESSAGE);

        JDialog dialog = warning.createDialog(parent, title);
        dialog.getContentPane().add(warning);
        dialog.setResizable(true);

        return dialog;
    }
}
