#!/bin/bash

TOSBED_HOME=/home/adalton/src/java/nestbed
TOSBED_LIB=${TOSBED_HOME}/lib

CLASSPATH=${TOSBED_HOME}/dist/nestbed-0.3.jar
for i in ${TOSBED_LIB}/*; do
    CLASSPATH="${CLASSPATH}:$i"
done
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/lib/tools.jar

echo $CLASSPATH

exec java -classpath ${CLASSPATH} edu.clemson.cs.nestbed.server.Server
