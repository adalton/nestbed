/* $Id$ */
/*
 * AdaptationException.java
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
package edu.clemson.cs.nestbed.server.adaptation;


// This is mostly (all) borrowed from java.rmi.RemoteException
public class AdaptationException extends Exception {
    private Throwable detail;


    public AdaptationException() {
        initCause(null);
    }


    public AdaptationException(String s) {
        super(s);
        initCause(null);
    }


    public AdaptationException(String s, Throwable cause) {
        super(s);
        initCause(null);
        detail = cause;
    }


    public String getMessage() {
        String message = super.getMessage();

        if (detail != null) {
            message += "; nested exception is:\n\t" + detail.toString();
        }

        return message;
    }


    public Throwable getCause() {
        return detail;
    }


    public String toString() {
        String str = super.toString();

        if (detail != null) {
            str += "\n  caused by:\n" + detail.toString();
        }

        return str;
    }
}
