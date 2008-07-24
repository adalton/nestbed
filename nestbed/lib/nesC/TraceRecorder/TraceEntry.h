/* $Id$ */
/*
 * TraceEntry.h
 *
 * nesC Analysis and Instrumentation Framework
 *
 * Copyright (C) 2007
 * Dependable Systems Research Group
 * School of Computing
 * Clemson University
 * Andrew R. Dalton and Jason O. Hallstrom
 * All rights reserved.
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
 *
 */
#ifndef TRACE_ENTRY_H
#define TRACE_ENTRY_H

nx_struct CallTraceEntry {
    nx_uint8_t moduleId;
    nx_uint8_t functionId;
} __attribute__((packed));
typedef nx_struct CallTraceEntry CallTraceEntry;


nx_struct RadioTraceEntry {
    nx_uint8_t  address;
    nx_uint16_t magic;
} __attribute__((packed));
typedef nx_struct RadioTraceEntry RadioTraceEntry;


nx_struct TraceEntry {
    nx_uint8_t type;
    nx_union {
        CallTraceEntry  callTrace;
        RadioTraceEntry radioTrace;
    } u;
} __attribute__((packed));
typedef nx_struct TraceEntry TraceEntry;


enum {
    AM_TRACEENTRY = 28
};

#endif
/* vim: :set ft=nc: */
