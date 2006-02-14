/*
 * ParentClassLoader.java
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
package edu.clemson.cs.nestbed.common.util;


/**
 * This is a hack.  If it can be fixed, it should be.  Here's the problem:
 * <p>
 * <ol>
 *     <li>The <code>ByteClassLoader</code> takes an array of bytes (that
 *         we dig out of the database) and loads a Class from those bytes.
 *     <li>Any class loader will allow it's parent to load classes.  If it's
 *         parent doesn't know what it is, it will attempt to load the class
 *         itself.
 *     <li>With Java Web Start, the JNLP Class loader loads classes out of the
 *         .jar files downloaded with the program.
 *     <li>The <code>ByteClassLoader</code> does not have the
 *         <code>JNLPClassLoader</code> in it's parent path.
 *     <li>Attempting to dynamically load a class that depends on another
 *         class in one of the .jar files fails because of this.
 * </ol>
 * <p>
 * What this class does is get and save the classloader that loads the entry
 * point to both the Client and the Server (i.e. the class loader that
 * loaded Client.class in the client and Server.class in the server.  The
 * <code>ByteClassLoader</code> then accesses this class to pass that class
 * loader to its constructor as its parent.  This way, in the server, we
 * get the normal behavior.  In the client, we can deligate to the
 * <code>JNLPClassLoader</code> to to the other classes we need.
 * <p>
 * There <b>has</b> to be a better way to do this.
 */
public class ParentClassLoader {
    private static ClassLoader m_parent;

    public static synchronized void setParent(ClassLoader parent) {
        if (m_parent != null) {
            throw new IllegalArgumentException("Cannot set parent twice");
        }

        m_parent = parent;
    }


    public static synchronized ClassLoader getParent() {
        return m_parent;
    }
}
