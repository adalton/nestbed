/* $Id$ */
/*
 * VisitableList.java
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;


public class VisitableList extends JList {
    private ListVisitor listVisitor;

    public VisitableList(ListVisitor visitor) {
        this.listVisitor = visitor;

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = getSelectedIndex();

                if (   SwingUtilities.isRightMouseButton(e)
                    && !isSelectionEmpty()
                    && getCellBounds(index, index).contains(e.getPoint())) {

                    JPopupMenu popupMenu =
                                listVisitor.visitPopupMenu(VisitableList.this);

                    if (popupMenu != null) {
                        popupMenu.show(VisitableList.this, e.getX(), e.getY());
                    }
                }
            }
        });
    }


    public String getToolTipText(MouseEvent e) {
        String  tip   = null;
        int     index = locationToIndex(e.getPoint());

        if (getCellBounds(index, index).contains(e.getPoint())) {
            tip = listVisitor.visitToolTip(this);
        }

        return tip;
    }
}
