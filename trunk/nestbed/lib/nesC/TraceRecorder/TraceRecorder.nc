/* $Id$ */
/*
 * TraceRecorder.nc
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

#include <AM.h>
#include <message.h>

interface TraceRecorder {
    async command bool enter(uint16_t moduleId, uint16_t functionId);
    async command bool exit(uint16_t moduleId,  uint16_t functionId);
          command bool send(am_addr_t dest, message_t* message, uint8_t length, void* payload);
          command bool receive(message_t* message, void* payload, uint8_t length, am_addr_t source);
}
