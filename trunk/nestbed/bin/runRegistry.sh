#!/bin/bash
# $Id$

CLASSPATH=${NESTBED_HOME}/dist/nestbed.jar
for i in ${NESTBED_LIB}/*; do
    CLASSPATH="${CLASSPATH}:$i"
done

export CLASSPATH
exec $(java-config-2 --get-env=JAVA_HOME)/bin/rmiregistry
