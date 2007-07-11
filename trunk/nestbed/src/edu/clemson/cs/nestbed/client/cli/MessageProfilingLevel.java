/* $Id$ */
/*
 * MessageProfilingLevel.java
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
package edu.clemson.cs.nestbed.client.cli;


import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import java.rmi.server.UnicastRemoteObject;

import edu.clemson.cs.nestbed.common.management.configuration.ProgramProfilingMessageSymbolManager;
import edu.clemson.cs.nestbed.common.management.configuration.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingMessageSymbol;
import edu.clemson.cs.nestbed.common.model.Project;
import edu.clemson.cs.nestbed.common.model.ProjectDeploymentConfiguration;
import edu.clemson.cs.nestbed.common.model.Testbed;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


class MessageProfilingLevel extends Level {
    private Testbed                               testbed;
    private Project                               project;
    private ProjectDeploymentConfiguration        config;
    private ProgramMessageSymbolManager           symbolManager;
    private ProgramProfilingMessageSymbolManager  progProfMsgSymManager;
    private List<ProgramProfilingMessageSymbol>   profilingMessages;


    public MessageProfilingLevel(Testbed testbed, Project project,
                                ProjectDeploymentConfiguration config,
                                Level parent) throws Exception {
        super("MessageProfiling", parent);
        lookupRemoteManagers();

        this.testbed           = testbed;
        this.project           = project;
        this.config            = config;
        this.profilingMessages = progProfMsgSymManager.
                            getProgramProfilingMessageSymbols(config.getID());

        for (ProgramProfilingMessageSymbol i : profilingMessages) {
            ProgramMessageSymbol messageSymbol;
            messageSymbol = symbolManager.getProgramMessageSymbol(
                                                i.getProgramMessageSymbolID());
            addEntry(new MessageSymbolEntry(messageSymbol, i));
        }

        progProfMsgSymManager.addRemoteObserver(
                                new ProgramProfilingMessageSymbolObserver());

        addCommand("rm", new RmCommand());
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {
        progProfMsgSymManager = (ProgramProfilingMessageSymbolManager)
                                Naming.lookup(RMI_BASE_URL +
                                         "ProgramProfilingMessageSymbolManager");

        symbolManager         = (ProgramMessageSymbolManager)
                                Naming.lookup(RMI_BASE_URL +
                                         "ProgramMessageSymbolManager");
    }


    private class MessageSymbolEntry extends FileEntry {
        private ProgramMessageSymbol          programMessageSymbol;
        private ProgramProfilingMessageSymbol programProfilingMessageSymbol;

        public MessageSymbolEntry(ProgramMessageSymbol          pms,
                                  ProgramProfilingMessageSymbol ppms) {
            super(pms.getName());

            this.programMessageSymbol          = pms;
            this.programProfilingMessageSymbol = ppms;
        }


        public ProgramMessageSymbol getProgramMessageSymbol() {
            return programMessageSymbol;
        }

        public ProgramProfilingMessageSymbol getProgramProfilingMessageSymbol() {
            return programProfilingMessageSymbol;
        }


        public String getFileContents() throws Exception {
            StringBuffer s = new StringBuffer(100);

            s.append("Name: ").append(programMessageSymbol.getName());
            s.append("\n");

            return s.toString();
        }
    }


    private class RmCommand implements Command {
        public Level execute(String[] args) throws Exception {
            Level nextLevel = MessageProfilingLevel.this;
            Variables.set("status", "0");

            if (args.length != 2) {
                System.out.printf("usage: %s <messageSymbol>\n", args[0]);
                Variables.set("status", "1");
                return nextLevel;
            }

            String name = args[1];
            Entry entry = getEntryWithName(name);

            if (entry instanceof MessageSymbolEntry) {
                MessageSymbolEntry            msEntry;
                ProgramProfilingMessageSymbol ppms;

                msEntry = (MessageSymbolEntry) entry;
                ppms     = msEntry.getProgramProfilingMessageSymbol();

                progProfMsgSymManager.deleteProgramProfilingMessageSymbol(
                                                                ppms.getID());
            }

            return nextLevel;
        }


        public String getHelpText() {
            return "Removes the specified message from the profiling list";
        }
    }


    protected class ProgramProfilingMessageSymbolObserver
                                             extends    UnicastRemoteObject
                                             implements RemoteObserver {

        public ProgramProfilingMessageSymbolObserver() throws RemoteException {
            super();
        }


        public void update(Serializable msg, Serializable arg)
                                                    throws RemoteException {
            ProgramProfilingMessageSymbolManager.Message message;
            ProgramProfilingMessageSymbol                profilingMsgSymbol;

            message            = (ProgramProfilingMessageSymbolManager.Message)
                                  msg;
            profilingMsgSymbol = (ProgramProfilingMessageSymbol) arg;


            switch (message) {
            case NEW_SYMBOL: {
                ProgramMessageSymbol msgSymbol;

                msgSymbol = symbolManager.getProgramMessageSymbol(
                                profilingMsgSymbol.getProgramMessageSymbolID());

                addEntry(new MessageSymbolEntry(msgSymbol, profilingMsgSymbol));
                break;
            }

            case DELETE_SYMBOL: {
                ProgramMessageSymbol msgSymbol;

                msgSymbol = symbolManager.getProgramMessageSymbol(
                                profilingMsgSymbol.getProgramMessageSymbolID());

                Entry entry = getEntryWithName(msgSymbol.getName());
                removeEntry(entry);
                break;
            }

            default:
                System.err.println("Unknown message:  " + msg);
                break;
            }
        }
    }
}
