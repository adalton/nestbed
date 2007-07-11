#!/bin/bash
# $Id$

# 
# install.sh
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

if [[ $# -ne 4 ]]; then
    echo "Incorrect number of parameters" 2>&1
    exit 1;
fi

export TOSROOT=/opt/tinyos-2.x
export TOSDIR=${TOSROOT}/tos
export PATH=/opt/tinyos-2.x-tools/bin:/opt/msp430/bin:${PATH}
export MAKERULES=/opt/tinyos-2.x/support/make/Makerules
export PYTHONPATH=/opt/tinyos-2.x-tools/lib/tinyos

directory=$1
platform=$2
address=$3
device=$4

exec /usr/bin/make -C ${directory} ${platform} reinstall.${address} bsl,${device}
