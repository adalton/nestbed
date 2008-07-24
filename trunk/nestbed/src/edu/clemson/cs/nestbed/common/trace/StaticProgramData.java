/* $Id$ */
/*
 * StaticProgramData.java
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
package edu.clemson.cs.nestbed.common.trace;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.clemson.cs.nesctk.records.FunctionCalleeRecord;
import edu.clemson.cs.nesctk.records.FunctionRecord;
import edu.clemson.cs.nesctk.records.TraceFunctionRecord;
import edu.clemson.cs.nesctk.records.TraceModuleRecord;
import edu.clemson.cs.nesctk.records.WiringRecord;


public class StaticProgramData implements Serializable {
    static final long serialVersionUID = 7007267575134727811L;

    private List<TraceModuleRecord>                         modules;
    private List<TraceFunctionRecord>                       functions;
    private Map<FunctionRecord, List<FunctionCalleeRecord>> callMap;
    private List<WiringRecord>                              wirings;


    public StaticProgramData(List<TraceModuleRecord>                         modules,
                             List<TraceFunctionRecord>                       functions,
                             Map<FunctionRecord, List<FunctionCalleeRecord>> callMap,
                             List<WiringRecord>                              wirings) {
        this.modules   = modules;
        this.functions = functions;
        this.callMap   = callMap;
        this.wirings   = wirings;
    }

    public List<TraceModuleRecord> getModules() {
        return modules;
    }

    public List<TraceFunctionRecord> getFunctions() {
        return functions;
    }

    public Map<FunctionRecord, List<FunctionCalleeRecord>> getCallMap() {
        return callMap;
    }

    public List<WiringRecord> getWirings() {
        return wirings;
    }
}
