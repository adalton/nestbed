#!/bin/bash
# $Id$

CLASSPATH=${NESTBED_HOME}/dist/nestbed.jar
CLASSPATH=${CLASSPATH}:$(/opt/tinyos-1.x/tools/java/javapath)
CLASSPATH=$(java-config-2 --get-env=JAVA_HOME)/jre/lib:${CLASSPATH}

for i in ${NESTBED_LIB}/*; do
    CLASSPATH="${CLASSPATH}:$i"
done
CLASSPATH=${CLASSPATH}:$(java-config-2 --tools)

exec $(java-config-2 --java) -classpath ${CLASSPATH} edu.clemson.cs.nestbed.server.Server
