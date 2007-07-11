#!/bin/bash
# $Id$

# 
# getMessageFile.sh
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

PATH=/bin:/usr/bin

BASEDIR=${1}
MESSAGE=${2}

FILES=$(egrep --with-filename "struct[ 	][ 	]*${MESSAGE}" ${BASEDIR}/*.h 2> /dev/null |
        awk -F: '{ print $1 }' 2> /dev/null)

UNIQ_FILES=$(echo $FILES | awk '{
    for (i = 1; i <= NF; ++i) {
        found = 0

        for (j = 1; j < i && !found; ++j) {
            found = ($j == $i);
        }

        if (!found) {
            print $i
        }
    }
}')

for i in ${UNIQ_FILES}; do
    echo $i;
done

