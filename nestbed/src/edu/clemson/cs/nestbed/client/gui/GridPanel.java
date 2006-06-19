/* $Id$ */
/*
 * GridPanel.java
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


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class GridPanel extends JPanel {
    private Component[][] panel;


    public GridPanel(int rows, int cols) throws IllegalArgumentException {
        if (rows < 1) {
            throw new IllegalArgumentException("rows cannot be < 1");
        }

        if (cols < 1) {
            throw new IllegalArgumentException("cols cannot be < 1");
        }

        panel = new Component[rows][cols];
        setLayout(null);


        // Evil magic numbers
        Dimension size = new Dimension(125 * cols, 95 * rows);

        setSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }


    public void addPanel(Component comp, int row, int col) {
        panel[row][col] = comp;
        add(comp);
        setBackground(Color.WHITE);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int canvasWidth  = getWidth();
        int canvasHeight = getHeight();

        int fieldWidth   = panel[0].length;
        int fieldHeight  = panel.length;

        int colIncrement = canvasWidth  / fieldWidth;
        int rowIncrement = canvasHeight / fieldHeight;

        canvasWidth      = colIncrement * fieldWidth;
        canvasHeight     = rowIncrement * fieldHeight;

        // Draw vertical lines
        for (int i = (colIncrement / 2); i < canvasWidth; i += colIncrement) {
            g.drawLine(i, 0, i, rowIncrement * fieldWidth);
        }

        // Draw horizontal lines
        for (int i = (rowIncrement / 2); i < canvasHeight; i += rowIncrement) {
            g.drawLine(0, i, colIncrement * fieldWidth, i);
        }

        for (int i = 0; i < panel.length; ++i) {
            for (int j = 0; j < panel[i].length; ++j) {
                if (panel[i][j] != null) {
                    panel[i][j].setLocation(
                        (colIncrement / 2) +
                            (j * colIncrement) - (panel[i][j].getWidth()  / 2),
                        (rowIncrement / 2) +
                            (i * rowIncrement) - (panel[i][j].getHeight() / 2));
                }
            }
        }
    }
}
