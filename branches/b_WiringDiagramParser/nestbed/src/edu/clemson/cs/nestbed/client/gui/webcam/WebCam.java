/* $Id$ */
package edu.clemson.cs.nestbed.client.gui.webcam;

/*
 * WebCam.java
 *
 * Created on February 24, 2002, 12:25 AM
 */

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author  mike morrison
 */
public class WebCam extends JFrame
{
    private JFrame      f;
    private ImageCanvas imageCanvas;

    public WebCam (String host, int port) {
        imageCanvas = new ImageCanvas(host, port, 30, 480, 640);
        imageCanvas.setMainWindow(this);
        setTitle("Webcam viewer: " + host + ":" + port);

        add(imageCanvas);
        pack();
        setVisible(true);

        addComponentListener (new ComponentAdapter() {
            public void componentResized (ComponentEvent e) {
                imageCanvas.setImageSize (e.getComponent().getSize());
            }
        });

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                imageCanvas.disconnect();
                WebCam.this.setVisible(false);
                WebCam.this.dispose();
            }
        });
    }
}
