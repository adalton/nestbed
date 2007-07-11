#!/bin/bash
# $Id$

# 
# getMessageTypes.sh
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
PLATFORM=${2}

#MSG_TYPES=$(grep "case AM_" ${BASEDIR}/build/${PLATFORM}/app.c 2> /dev/null | \
#    sort -u | \
#    sed -e 's/AM_//g' -e 's/://g' | \
#    awk '{ print $2 }' 2> /dev/null)

MSG_TYPES=$(grep "[^_(A-Za-z]AM_[^,]*=" ${BASEDIR}/build/${PLATFORM}/app.c 2> /dev/null | \
    sed -e 's/AM_//g' | \
    awk '{ print $1 }' | \
    sort -u)

TYPES=""
for i in ${MSG_TYPES}; do
    for j in ${BASEDIR}/*.h; do
        MATCHES="$MATCHES $(grep -v \# $j | grep --ignore-case $i 2> /dev/null | \
                sed -e 's/;//' -e 's/{//' 2>/dev/null)"
    done

    #MATCHES=$(grep --ignore-case $i ${BASEDIR}/*.h 2> /dev/null)
    if [ $? -eq 0 ]; then
        for j in $(echo ${MATCHES} | grep nx_struct 2> /dev/null); do
            if $(echo $j | grep --silent --ignore-case $i 2> /dev/null); then
                TYPES="${TYPES} $j"
            fi
        done
    fi
done

echo $TYPES | sed -e 's/AM_[A-Z][A-Z]*//g' | awk ' {
    for (i = 1; i <= NF; ++i) {
        found = 0

        for (j = 1; j < i && !found; ++j) {
            found = ($j == $i);
        }

        if (!found) {
            print $i
        }
    }
}' > /tmp/types.$$

cat /tmp/types.$$ | sed -e 's///' | sort -u
