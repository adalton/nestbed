#!/bin/bash
# $Id$

# 
# database_restore.sh
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
export PATH=/bin:/usr/bin

if [ $# -ne 1 ]; then
    echo "usage:  $0 <mysql schema>" 1>&2
    exit 1
fi

(
echo "SET AUTOCOMMIT=0;"
echo "SET FOREIGN_KEY_CHECKS=0;"
cat $1
echo "SET FOREIGN_KEY_CHECKS=1;"
echo "COMMIT;"
echo "SET AUTOCOMMIT=1;"
) | mysql --user=nestbed -p nestbed
