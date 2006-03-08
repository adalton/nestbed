/* $Id$ */
package edu.clemson.cs.nestbed.client.gui.webcam;

/*
 * ImageCanvas.java
 *
 * Created on February 25, 2002, 1:29 AM
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;
import javax.swing.*;


/**
 *
 * @author  mike morrison
 */
public class ImageCanvas extends Canvas {
    final static int CONNECTED    = 0;
    final static int DISCONNECTED = 1;
    final static int STOPPED      = 2;

    final private String connectingImageFilename    = "init.jpg";
    final private String cannotConnectImageFilename = "error.jpg";

    private String  host;
    private int     port;
    private double  fps;	
    private int     connected   = STOPPED;
    private boolean first_image = true;

    private Dimension preferredSize;

    private Graphics offGraphics;
    private Image    currentImage;
    private Image    offImage;

    private JPopupMenu popup;
    private JMenuItem  startStop;

    private JFrame          mainWindow = null;
    private ImageDownloader downloader;
    private ImageCanvas     thisImageCanvas;
    private JFrame          about;
    private JTextArea       output;
    private	JTextField      input;
    private JTextField      name = new JTextField("name", 10);

    /** Creates a new instance of ImageCanvas */
    public ImageCanvas(String host, int port, int fps, int width, int height) {
        this.host = host;
        this.port = port;
        this.fps  = fps;

        preferredSize = new Dimension(width,height);

        File f = new File(connectingImageFilename);
        if(f.exists()) {
            setImage(Toolkit.getDefaultToolkit().getImage(connectingImageFilename));
        } else {
            System.out.println(connectingImageFilename + " not found");
        }

        about = createAbout();
        //ARD FIXME: setPopupMenu(createMenu());
        //add(createMenu());
        thisImageCanvas = this;

        // mouse listener
        //addMouseListener(createMouseListener());
        downloader = new ImageDownloader(host, port, fps, this);
        downloader.start();
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    public double getFPS() {
        return fps;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public void setMainWindow(JFrame f) {
        mainWindow = f;
    }

    /**
     * returns the time it took to set the image 
     */
    public void setImageSize(Dimension size) {
        preferredSize = size;
        setSize(size);
    }

    /**
     * Sets Start/Stop menu item label
     */
    public void setStartStopText(String text) {
        //startStop.setText(text);
    }

    /**
     * sets the current image (refreshes frame)
     */
    public int setImage(Image img) {
        //System.out.println("setImage: " + img);
        if(first_image && mainWindow != null)
        {
            //System.out.println("if(first_image && mainWindow != null)");
            //System.out.println("Width:  " + img.getWidth(null));
            //System.out.println("Height:  " + img.getHeight(null));
            int w, h;
            while((w = img.getWidth(null)) < 0 || (h = img.getHeight(null)) < 0);
            //System.out.println("w = " + w + ", h = " + h);
            mainWindow.setBounds(100,100,w,h);
            first_image = false;
        }

        //System.out.println("afte if");
        int count = 0, waitTime = 5;
        while (!prepareImage(img,this))
        {
            //System.out.println("in while");
            wait(waitTime);
            count ++;
            if (count * waitTime > 5000) break;
        }
        currentImage = img;
        repaint();
        return (count * waitTime);
    }

    /**
     * displays image in cannotConnectImageFilename upon failed connection
     */
    public void couldNotConnect() {
        setStartStopText("Start");
        File f = new File(cannotConnectImageFilename);
        if(f.exists())
            setImage(Toolkit.getDefaultToolkit().getImage(cannotConnectImageFilename));
        else
            System.out.println(cannotConnectImageFilename + " not found");
    }

    /**
     * sets connected state
     */
    public void setConnected(int connected) {
        this.connected = connected;
        repaint();
    }

    /**
     * sleeps current thread for n milliseconds
     */
    public void wait(int n) {
        try {
            Thread.sleep(n);
        } catch(InterruptedException e) {}
    }

    public void disconnect() {
        downloader.disconnect();
    }


    public JFrame createAbout()
    {
        JFrame f = new JFrame("About");
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0,1));

        p.add(new JLabel(" "));
        p.add(new JLabel("Copyright (C) 2002"));
        p.add(new JLabel("Applet by Mike Morrison"));
        p.add(new JLabel("Server by Donn Morrison"));
        p.add(new JLabel(" "));

        JButton b = new JButton("Ok");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                about.setVisible(false);
            }
        });

        f.add(p,BorderLayout.NORTH);
        p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);
        f.add(p,BorderLayout.CENTER);
        f.pack();
        f.setBackground(Color.lightGray);
        f.setResizable(false);
        return f;          
    }
    public JPopupMenu createMenu()
    {
        //create the menu
        JMenuItem menuItem;
        popup = new JPopupMenu("WebCam Options");
        startStop = new JMenuItem("Stop");
        startStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (downloader == null || !downloader.isConnected()) {
                    downloader = new ImageDownloader(thisImageCanvas.getHost(),thisImageCanvas.getPort(),thisImageCanvas.getFPS(),thisImageCanvas);
                    downloader.start();
                    downloader.setMaxFPS(thisImageCanvas.fps);
                } else {
                    thisImageCanvas.fps = downloader.getMaxFPS();
                    downloader.disconnect();
                    downloader = null;
                }

            }
        });
        popup.add(startStop);


        //frames per second submenu
        JMenu subMenu = new JMenu("Max FPS");
        menuItem = new JMenuItem("0.1");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set maxfps to 0.1
                if (downloader != null) {
                    downloader.setMaxFPS(.1);
                    thisImageCanvas.fps = .1;
                }
            }
        });
        subMenu.add(menuItem);

        menuItem = new JMenuItem("0.2");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set maxfps to 0.2
                if (downloader != null) {
                    downloader.setMaxFPS(.2);
                    thisImageCanvas.fps = .2;
                }
            }
        });
        subMenu.add(menuItem);

        menuItem = new JMenuItem("0.5");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set maxfps to 0.5
                if (downloader != null) {
                    downloader.setMaxFPS(.5);
                    thisImageCanvas.fps =.5;
                }
            }
        });
        subMenu.add(menuItem);
        menuItem = new JMenuItem("1");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set maxfps to 1
                if (downloader != null) {
                    downloader.setMaxFPS(1.0);
                    thisImageCanvas.fps = 1.0;
                }
            }
        });
        subMenu.add(menuItem);
        menuItem = new JMenuItem("2");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set maxfps to 2
                if (downloader != null) {
                    downloader.setMaxFPS(2.0);
                    thisImageCanvas.fps = 2.0;
                }
            }
        });
        subMenu.add(menuItem);
        menuItem = new JMenuItem("5");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set maxfps to 5
                if (downloader != null) {
                    downloader.setMaxFPS(5.0);
                    thisImageCanvas.fps = 5.0;
                }
            }
        });
        subMenu.add(menuItem);
        menuItem = new JMenuItem("10");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set maxfps to 10
                if (downloader != null) {
                    downloader.setMaxFPS(10.0);
                    thisImageCanvas.fps = 10.0;
                }
            }
        });
        subMenu.add(menuItem);
        menuItem = new JMenuItem("Unlimited");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set maxfps to 30
                if (downloader != null) {
                    downloader.setMaxFPS(30.0);
                    thisImageCanvas.fps = 30.0;
                }
            }
        });
        subMenu.add(menuItem);

        popup.add(subMenu);
        popup.addSeparator();

        //about
        menuItem = new JMenuItem("About");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //display about box
                about.setVisible(true);
            }
        });

        popup.add(menuItem);
        return (popup);
    }

    //////////////////////////////////////////
    // paint methods /////////////////////////
    //////////////////////////////////////////

    public void paint(Graphics g)
    {
        update(g);
    }

    public void update(Graphics g)
    {
        Color color = Color.black;
        if (currentImage != null)
        {
            // dubble buffer
            offImage = createImage(preferredSize.width,preferredSize.height);
            offGraphics = offImage.getGraphics();
            offGraphics.drawImage(currentImage,0,0,preferredSize.width,preferredSize.height,this);
            g.drawImage(offImage,0,0,this);

            switch(connected)
            {
                case CONNECTED:
                    color = new Color(30, 175, 30); // dark green
                    break;
                case DISCONNECTED:
                    color = new Color(175,30,30); // dark red
                    break;
                case STOPPED:
                    color = new Color(175,30,30); // dark red
                    break;
                default:
                    break;
            }

            // display an indicator in bottom corner of frame
            g.setColor(Color.black);
            g.fillOval(preferredSize.width - 11, preferredSize.height - 11, 7, 7);
            g.setColor(color);
            g.fillOval(preferredSize.width - 12, preferredSize.height - 12, 7, 7);

            // no double buffer
            // g.drawImage(currentImage,0,0,preferredSize.width,preferredSize.height,this);
        }
    }
}
