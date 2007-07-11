#!/bin/bash
# $Id$

# 
# parser.sh
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

CLASSPATH=${NESTBED_LIB}/javacup.jar:${NESTBED_LIB}/JFlex.jar

$(java-config-2 --java) -classpath ${CLASSPATH} java_cup.Main -package edu.cs.clemson.nestbed.server.nesc.parser \
                   -parser  Parser \
                   -symbols Token < ../src/edu/clemson/cs/nestbed/server/nesc/parser/nesc.cup \
  && mv Parser.java Token.java ../src/edu/clemson/cs/nestbed/server/nesc/parser/ \
  && $(java-config-2 --java) -classpath ${CLASSPATH} JFlex.Main  --nobak -d ../src/edu/clemson/cs/nestbed/server/nesc/parser/ ../src/edu/clemson/cs/nestbed/server/nesc/parser/nesc.jflex


  exit $?
