#!/bin/bash

TOSBED_HOME=/home/adalton/src/java/nestbed
TOSBED_LIB=${TOSBED_HOME}/lib

CLASSPATH=${TOSBED_HOME}/dist/nestbed.jar
for i in ${TOSBED_LIB}/*; do
    CLASSPATH="${CLASSPATH}:$i"
done

export CLASSPATH
exec rmiregistry
