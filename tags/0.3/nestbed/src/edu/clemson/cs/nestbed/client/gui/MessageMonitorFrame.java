/*
 * MessageMonitorFrame.java
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
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.clemson.cs.nestbed.common.management.MessageManager;
import edu.clemson.cs.nestbed.common.management.ProgramMessageSymbolManager;
import edu.clemson.cs.nestbed.common.model.Mote;
import edu.clemson.cs.nestbed.common.model.ProgramMessageSymbol;
import edu.clemson.cs.nestbed.common.model.ProgramProfilingMessageSymbol;
import edu.clemson.cs.nestbed.common.util.LocalObjectInputStream;
import edu.clemson.cs.nestbed.common.util.RemoteObserver;


public class MessageMonitorFrame extends JFrame {
    private final static Log log = LogFactory.getLog(MessageMonitorFrame.class);

    private final static String RMI_BASE_URL;
    private final static int    WINDOW_WIDTH  = 400;
    private final static int    WINDOW_HEIGHT = 450;

    static {
        RMI_BASE_URL = System.getProperty("testbed.rmi.baseurl");
    }

    private Mote                          mote              = null;
    private Map<Method, JTextField>       methodFieldMap    = null;
    private MessageManager                messageManager    = null;
    private ProgramMessageSymbolManager   progMsgSymManager = null;
    private ProgramProfilingMessageSymbol profMessageSymbol = null;
    private ProgramMessageSymbol          messageSymbol     = null;


    public MessageMonitorFrame(Mote                          mote,
                               ProgramProfilingMessageSymbol profMessageSymbol)
                                                throws RemoteException,
                                                       NotBoundException,
                                                       MalformedURLException,
                                                       ClassNotFoundException {
        this.mote              = mote;
        this.profMessageSymbol = profMessageSymbol;
        this.methodFieldMap    = new HashMap<Method, JTextField>();

        lookupRemoteManagers();

        messageSymbol = progMsgSymManager.getProgramMessageSymbol(
                                profMessageSymbol.getProgramMessageSymbolID());

        setTitle(messageSymbol.getName() + " Message Monitor");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setJMenuBar(buildMenuBar());

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        c.add(new JScrollPane(buildMessageDataPanel()), BorderLayout.NORTH);
        c.add(buildButtonPanel(),                       BorderLayout.SOUTH);
    }


    private final void lookupRemoteManagers() throws RemoteException,
                                                     NotBoundException,
                                                     MalformedURLException {

        messageManager        = (MessageManager) Naming.lookup(
                                RMI_BASE_URL + "MessageManager");

        progMsgSymManager     = (ProgramMessageSymbolManager) Naming.lookup(
                                RMI_BASE_URL + "ProgramMessageSymbolManager");
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
                MessageMonitorFrame.this.setVisible(false);
            }
        });
        menu.add(close);

        return menu;
    }


    private JPanel buildMessageDataPanel() {
        Class        msgClass   = messageSymbol.getMessageClass();
        List<Method> methodList = getPropertyMethodList(msgClass);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Message Data"));
        panel.setLayout(new BorderLayout());

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());

        innerPanel.add(buildLabelPanel(methodList), BorderLayout.WEST);
        innerPanel.add(buildFieldPanel(methodList), BorderLayout.CENTER);

        panel.add(innerPanel, BorderLayout.CENTER);

        return panel;
    }


    private List<Method> getPropertyMethodList(Class msgClass) {
        List<Method> methodList = new ArrayList<Method>();
        Method[]     methods    = msgClass.getMethods();

        for (Method i : methods) {
            if (i.getName().startsWith("get_")) {
                methodList.add(i);
            }
        }

        return methodList;
    }


    private JPanel buildLabelPanel(List<Method> methodList) {
        JPanel panel = new JPanel();
        int    rows  = methodList.size();
        int    cols  = 1;

        panel.setLayout(new GridLayout(rows, cols));

        for (Method i : methodList) {
            JLabel    label = new JLabel(i.getName().substring(4) + ":  ");

            panel.add(label);
        }

        return panel;
    }


    private JPanel buildFieldPanel(List<Method> methodList) {
        JPanel panel = new JPanel();
        int    rows  = methodList.size();
        int    cols  = 1;

        panel.setLayout(new GridLayout(rows, cols));


        for (Method i : methodList) {
            JTextField textField = new JTextField();
            textField.setEditable(false);

            methodFieldMap.put(i, textField);
            panel.add(textField);
        }

        return panel;
    }


    private JPanel buildButtonPanel() throws RemoteException {
        JPanel  panel  = new JPanel();
        JButton button = new JButton("Start Recording");

        panel.setLayout(new FlowLayout());
        panel.add(button);

        button.addActionListener(new StartStopActionListener(button));
        return panel;
    }


    protected class StartStopActionListener implements ActionListener {
        private JButton         button;
        private boolean         running;
        private MessageObserver messageObserver;

        public StartStopActionListener(JButton button) throws RemoteException {
            this.button          = button;
            this.running         = false;

            try {
            ClassLoader classLoader = messageSymbol.getClassLoader();
            Class       c           = classLoader.loadClass(
                                               MessageObserver.class.getName());
            Constructor constructor = c.getConstructor(
                                                MessageMonitorFrame.class);
            messageObserver = (MessageObserver)
                        constructor.newInstance(MessageMonitorFrame.this);
            } catch (Exception ex) {
                log.error("Exception:\n", ex);
            }
        }

        public void actionPerformed(ActionEvent e) {
            try {
                if (!running) {
                    JFileChooser chooser = new JFileChooser();
                    chooser.showSaveDialog(MessageMonitorFrame.this);
                    File file = chooser.getSelectedFile();
                    messageObserver.setSaveFile(file);
                    messageObserver.startRecording();

                    button.setText("Stop Recording");

                    messageManager.enable(mote.getID());
                    messageSymbol.getMessageClass();
                    messageManager.addMessageObserver(mote.getID(),
                                                      messageSymbol.getID(),
                                                      messageObserver);
                } else {
                    messageManager.removeMessageObserver(mote.getID(),
                                                         messageSymbol.getID(),
                                                         messageObserver);
                    messageObserver.stopRecording();
                    messageManager.disable(mote.getID());
                    button.setText("Start Recording");
                }

                running = !running;
            } catch (RemoteException ex) {
                log.error("RemoteException:\n", ex);
            }
        }
    }


    protected class MessageObserver extends    UnicastRemoteObject
                                    implements RemoteObserver {
        private File               saveFile;
        private ObjectOutputStream objOut;

        public MessageObserver() throws RemoteException {
            super();
        }


        public void setSaveFile(File saveFile) {
            this.saveFile = saveFile;
        }

        public void startRecording() {
            try {
                if (saveFile != null) {
                    objOut = new ObjectOutputStream(
                                new FileOutputStream(saveFile));
                } else {
                    if (objOut != null) {
                        objOut.flush();
                        objOut.close();
                    }
                    objOut = null;
                }
            } catch (Exception ex) {
                log.error("Exception:\n", ex);
            }
        }


        public void stopRecording() {
            if (objOut != null) {
                try {
                    objOut.flush();
                    objOut.close();
                } catch (Exception ex) {
                    // empty
                }
                objOut = null;
            }
        }


        public void update(Serializable pmsID, Serializable msg)
                                                        throws RemoteException {
            ClassLoader classLoader = messageSymbol.getClassLoader();
            try {
                byte[] msgBytes = (byte[]) msg;
                ByteArrayInputStream bain = new ByteArrayInputStream(msgBytes);
                Class                c    = classLoader.loadClass(
                                    LocalObjectInputStream.class.getName());
                Constructor          con  = c.getConstructor(InputStream.class,
                                                             Class.class);
                ObjectInputStream    in   = (ObjectInputStream)
                                               con.newInstance(bain,
                                   messageSymbol.getMessageClass());

                Object obj = in.readObject();
                if (objOut != null) {
                    objOut.writeObject(obj);
                    objOut.flush();
                }
                in.close();

                List<Method> methodList = getPropertyMethodList(obj.getClass());
                for (Method i : methodList) {
                    JTextField textfield = methodFieldMap.get(i);
                    textfield.setText(i.invoke(obj).toString());
                }

            } catch (Exception ex) {
                log.error("Exception:\n", ex);
            }
        }
    }

}
