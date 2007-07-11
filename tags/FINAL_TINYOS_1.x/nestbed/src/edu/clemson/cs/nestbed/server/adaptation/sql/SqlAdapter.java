/* $Id$ */
/*
 * SqlAdapter.java
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
package edu.clemson.cs.nestbed.server.adaptation.sql;


/**
 * This class is mainly intended to prevent us from having to lookup
 * the connection string in *every* SQL adapter.  It will be the same
 * for ALL SQL adapters.
 * <p>
 * <b>Note:</b> This class is intentionally <i>not</i> public.  Other
 * packages have no reason to interact with an SqlAdapter directly.
 * <p>
 * <b>Note:</b> All other SqlAdapters are tighly coupled with this class.
 * If you change CONN_STR, you'll effect everything else.
 */
abstract class SqlAdapter {
    protected final static String CONN_STR;

    static {
        CONN_STR = System.getProperty("nestbed.options.databaseConnectionString");
    }
}