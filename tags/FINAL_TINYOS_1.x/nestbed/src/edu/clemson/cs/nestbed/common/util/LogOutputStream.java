/* $Id: */
/*
 * LogOutputStream.java
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * Department of Computer Science
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
package edu.clemson.cs.nestbed.common.util;


import java.io.FilterOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;


public class LogOutputStream extends FilterOutputStream {
    private StringBuffer buffer;
    private Logger       logger;
    private Level        level;

    public LogOutputStream(Class clazz, Level level) {
        super(null);
        this.buffer = new StringBuffer();
        this.logger = Logger.getLogger(clazz);
        this.level  = level;
    }

    public synchronized void write(int b) throws IOException {
        buffer.append((char) b);
    }

    public synchronized void write(byte[] b) throws IOException {
        buffer.append(new String(b));
    }

    public synchronized void write(byte[] b, int off, int len)
                                                throws IOException {
        buffer.append(new String(b, off, len));
    }

    public void flush() {
        if (buffer.indexOf("\n") != -1) {
            logger.log(level, buffer.toString());
            buffer.setLength(0);
        }
    }
}
