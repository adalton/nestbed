/*
 * OutputWindow.java
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


import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class OutputWindow extends JFrame {
    private final static int WIDTH  = 550;
    private final static int HEIGHT = 600;

    private JTextArea textArea = new JTextArea();


    public OutputWindow(String title) {
        super(title);
        this.setSize(WIDTH, HEIGHT);
        this.setJMenuBar(buildMenuBar());

        Container c = this.getContentPane();

        c.setLayout(new BorderLayout());
        c.add(buildDisplayPanel(), BorderLayout.CENTER);
    }



    public void setText(String text) {
        textArea.setText(text);
    }


    public void appendText(String text) {
        textArea.setText(textArea.getText() + text + "\n");
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
                OutputWindow.this.setVisible(false);
            }
        });

        menu.add(close);

        return menu;
    }



    private JPanel buildDisplayPanel() {
        JPanel panel = new JPanel();

        panel.setBorder(new TitledBorder("Compilation Output"));
        panel.setLayout(new BorderLayout());

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(false);

        textArea.getDocument().
                    addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { }
            public void insertUpdate(DocumentEvent e) {
                textArea.setCaretPosition(
                            textArea.getText().length() - 1);
            } 
            public void removeUpdate(DocumentEvent e) { }
        });
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}
