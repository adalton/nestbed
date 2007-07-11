#!/bin/bash
# $Id$

CLASSPATH=${NESTBED_LIB}/javacup.jar:${NESTBED_LIB}/JFlex.jar

$(java-config-2 --java) -classpath ${CLASSPATH} java_cup.Main -package edu.cs.clemson.nestbed.server.nesc.parser \
                   -parser  Parser \
                   -symbols Token < ../src/edu/clemson/cs/nestbed/server/nesc/parser/nesc.cup \
  && mv Parser.java Token.java ../src/edu/clemson/cs/nestbed/server/nesc/parser/ \
  && $(java-config-2 --java) -classpath ${CLASSPATH} JFlex.Main  --nobak -d ../src/edu/clemson/cs/nestbed/server/nesc/parser/ ../src/edu/clemson/cs/nestbed/server/nesc/parser/nesc.jflex


  exit $?
