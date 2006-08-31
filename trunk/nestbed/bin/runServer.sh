#!/bin/bash
# $Id$

TOSBED_HOME=/home/adalton/src/java/nestbed
TOSBED_LIB=${TOSBED_HOME}/lib

CLASSPATH=${TOSBED_HOME}/dist/nestbed.jar
CLASSPATH=${CLASSPATH}:$(/opt/tinyos-1.x/tools/java/javapath)
for i in ${TOSBED_LIB}/*; do
    CLASSPATH="${CLASSPATH}:$i"
done
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/lib/tools.jar

exec java -classpath ${CLASSPATH} edu.clemson.cs.nestbed.server.Server
