#!/bin/bash
# $Id$

# 
# getProgramSymbols.sh
# 
# Network Embedded Sensor Testbed (NESTbed)
# 
# Copyright (C) 2006-2007
# Dependable Systems Research Group
# School of Computing
# Clemson University
# Andrew R. Dalton and Jason O. Hallstrom
# 
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the
# 
# Free Software Foundation, Inc.
# 51 Franklin Street, Fifth Floor
# Boston, MA  02110-1301, USA.
# 

if [[ $# -ne 1 ]]; then
    echo 0 0 Invalid.Number.Of.Parameters
    exit 1
fi

# dump objects | \
#   print only elements that are marked as .data or .bss, and print only the 1st, 5th, and 6th columns | \
#   replace all '$' literals with .
msp430-objdump -t $1 | \
    awk '{ if (NF == 6 && ($4 == ".data" || $4 == ".bss") && $5 != 0) { print($1, $5, $6) | "sed -e s/\\\\$/./g" } }'
