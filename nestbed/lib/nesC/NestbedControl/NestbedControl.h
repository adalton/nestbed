/* $Id$ */
/*
 * NestbedControl.h
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * School of Computing
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
#ifndef __NESTBED_CONTROL_H
#define __NESTBEDRADIO_CONTROL_H

enum {
    AM_CONTROLMESSAGE = 170
};

enum Command {
    SET_POWER,
    RESET,
};

nx_struct ControlMessage {
    nx_uint8_t cmd;
    nx_uint8_t arg;
};

typedef nx_struct ControlMessage ControlMessage;

// vim: :set ft=nc:
#endif
