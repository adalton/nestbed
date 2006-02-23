#!/bin/bash
java java_cup.Main -package edu.cs.clemson.nestbed.server.nesc.parser \
                   -parser  Parser \
                   -symbols Token < ../src/edu/clemson/cs/nestbed/server/nesc/parser/nesc.cup \
  && mv Parser.java Token.java ../src/edu/clemson/cs/nestbed/server/nesc/parser/ \
  && /opt/jflex/bin/jflex --nobak -d ../src/edu/clemson/cs/nestbed/server/nesc/parser/ ../src/edu/clemson/cs/nestbed/server/nesc/parser/nesc.jflex


  exit $?
