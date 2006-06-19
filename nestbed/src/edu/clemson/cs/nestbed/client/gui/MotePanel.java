/* $Id$ */
/*
 * MotePanel.java
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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MotePanel extends JPanel {
    private final static Log    log      = LogFactory.getLog(MotePanel.class);
    private final static Color  BG_COLOR = Color.WHITE;

    private Image icon;


    public MotePanel() {
        this.icon = icon;

        setBackground(BG_COLOR);
        setLayout(null);
    }


    public void setIcon(Image icon) {
        this.icon = icon;

        Dimension size = new Dimension((int) (1.88 * icon.getWidth(null)),
                                       (int) (1.88 * icon.getHeight(null)));
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics;

        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        g.drawImage(icon, getWidth() / 4, getHeight() / 4, null);
    }


    public void paintDisabledPattern(Graphics g) {
        int arcSize = 4;
        int width   = getWidth()  - 1;
        int height  = getHeight() - 1;

        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1,
                        arcSize, arcSize);

        for (int i = 1, numLines = 3; i <= numLines; ++i) {
            g.drawLine(width / numLines * i, 0, 0, height / numLines * i);
            g.drawLine(width, height / numLines * i, width / numLines * i, height);
        }
    }
}
