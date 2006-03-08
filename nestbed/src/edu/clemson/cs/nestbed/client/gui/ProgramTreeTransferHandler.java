/*
 * ProgramTreeTransferHandler.java
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


import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.model.Program;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramSymbol;


public class ProgramTreeTransferHandler extends TransferHandler {
    private final static Log log = LogFactory.getLog(
                                            ProgramTreeTransferHandler.class);

    private DataFlavor localProgramFlavor  = null;
    private DataFlavor serialProgramFlavor = null;
    private String     localProgramType    =
                                DataFlavor.javaJVMLocalObjectMimeType +
                                ";class=edu.clemson.cs.nestbed.common." +
                                "model.Program";

    private DataFlavor localProgramSymbolFlavor  = null;
    private DataFlavor serialProgramSymbolFlavor = null;
    private String     localProgramSymbolType    =
                                DataFlavor.javaJVMLocalObjectMimeType +
                                ";class=edu.clemson.cs.nestbed.common." +
                                "model.ProgramSymbol";

    private DataFlavor localProgramMessageSymbolFlavor  = null;
    private DataFlavor serialProgramMessageSymbolFlavor = null;
    private String     localProgramMessageSymbolType    =
                                DataFlavor.javaJVMLocalObjectMimeType +
                                ";class=edu.clemson.cs.nestbed.common." +
                                "model.ProgramMessageSymbol";

    private JTree      source                    = null;


    public ProgramTreeTransferHandler() throws ClassNotFoundException {
        log.debug("ProgramTreeTransferHandler created.");

        localProgramFlavor              = new DataFlavor(localProgramType);
        localProgramSymbolFlavor        = new DataFlavor(
                                                localProgramSymbolType);
        localProgramMessageSymbolFlavor = new DataFlavor(
                                                localProgramMessageSymbolType);

        serialProgramFlavor              = new DataFlavor(Program.class,
                                                          "Program");
        serialProgramSymbolFlavor        = new DataFlavor(ProgramSymbol.class,
                                                          "ProgramSymbol");
        serialProgramMessageSymbolFlavor = new DataFlavor(
                                                    ProgramMessageSymbol.class,
                                                    "ProgramMessageSymbol");
    }


    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }


    protected Transferable createTransferable(JComponent c) {
        log.debug("createTransferable() called.");

        Transferable transferable = null;

        if (c instanceof JTree) {
            source = (JTree) c;

            DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)
                            source.getSelectionPath().getLastPathComponent();
            Object o = dmtn.getUserObject();

            if (o instanceof Program) {
                log.debug("Transfering a Program");

                Program program = (Program) o;
                transferable = new ProgramTransferable(program);
            } else if (o instanceof ProgramSymbol) {
                log.debug("Transfering a ProgramSymbol");

                ProgramSymbol programSymbol = (ProgramSymbol) o;
                transferable = new ProgramSymbolTransferable(programSymbol);
            } else if (o instanceof ProgramMessageSymbol) {
                log.debug("Transfering a ProgramMessageSymbol");

                ProgramMessageSymbol programMessageSymbol =
                                                (ProgramMessageSymbol) o;

                transferable =
                    new ProgramMessageSymbolTransferable(programMessageSymbol);
            } else {
                log.error("Selected value is not a Program");
            }
        }

        return transferable;
    }


    protected class ProgramTransferable implements Transferable {
        private Program data;


        public ProgramTransferable(Program program) {
            data = program;
        }


        public Object getTransferData(DataFlavor flavor)
                                 throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return data;
        }


        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { localProgramFlavor,
                                      serialProgramFlavor };
        }


        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return localProgramFlavor.equals(flavor) ||
                   serialProgramFlavor.equals(flavor);
        }
    }


    protected class ProgramSymbolTransferable implements Transferable {
        private ProgramSymbol data;


        public ProgramSymbolTransferable(ProgramSymbol programSymbol) {
            this.data = programSymbol;
        }


        public Object getTransferData(DataFlavor flavor)
                                     throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return data;
        }


        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { localProgramSymbolFlavor,
                                      serialProgramSymbolFlavor };
        }


        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return localProgramSymbolFlavor.equals(flavor) ||
                   serialProgramSymbolFlavor.equals(flavor);
        }
    }


    protected class ProgramMessageSymbolTransferable implements Transferable {
        private ProgramMessageSymbol data;


        public ProgramMessageSymbolTransferable(ProgramMessageSymbol pms) {
            this.data = pms;
        }


        public Object getTransferData(DataFlavor flavor)
                                     throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return data;
        }


        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { localProgramMessageSymbolFlavor,
                                      serialProgramMessageSymbolFlavor };
        }


        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return localProgramMessageSymbolFlavor.equals(flavor) ||
                   serialProgramMessageSymbolFlavor.equals(flavor);
        }
    }
}
