/* $Id$ */
/*
 * MemoryInterfaceM.nc
 *
 * Network Embedded Sensor Testbed (NESTbed)
 *
 * Copyright (C) 2007
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
module MemoryInterfaceM {
    provides interface MemoryInterface;
}
implementation {
    command uint32_t MemoryInterface.readFromAddress(uint16_t address,
                                                     uint8_t  size,
                                                     uint8_t  offset) {
        uint32_t value = 0xFACEFACE;

        switch (size) {
        case 1: {
            uint8_t* pointer = (uint8_t*) address;
            pointer = pointer + offset;
            value = *pointer;
            break;
        }
        case 2: {
            uint16_t* pointer = (uint16_t*) address;
            pointer = pointer + offset;
            value = *pointer;
            break;
        }
        case 4: {
            uint32_t* pointer = (uint32_t*) address;
            pointer = pointer + offset;
            value = *pointer;
            break;
        }}

        return value;
    }


    command void MemoryInterface.writeToAddress(uint16_t address,
                                                uint8_t  size,
                                                uint8_t  offset,
                                                uint32_t value) {
        switch (size) {
        case 1: {
            uint8_t* pointer = (uint8_t*) address;
            pointer = pointer + offset;
            *pointer = value;
            break;
        }
        case 2: {
            uint16_t* pointer = (uint16_t*) address;
            pointer = pointer + offset;
            *pointer = value;
            break;
        }
        case 4: {
            uint32_t* pointer = (uint32_t*) address;
            pointer = pointer + offset;
            *pointer = value;
            break;
        }}
    }
}
