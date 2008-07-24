#!/bin/bash
# $Id$

# 
# run.sh
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

cd ${NESTBED_HOME}/bin/

while [ true ]; do
    echo "Starting RMI Registry..."
    ./runRegistry.sh &
    sleep 3s

    echo "Starting NESTbed Server..."
    ./runServer.sh
    echo "... NESTbed Server has terminated."

    echo "Killing RMI Registry..."
    killall rmiregistry

    echo "Waiting 5 seconds to restart..."
    sleep 5s
done
