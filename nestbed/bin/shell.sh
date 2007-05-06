#!/bin/bash
# $Id$

JAVA_OPTS="-Djava.security.policy=${NESTBED_HOME}/misc/java.policy"
CLASSPATH="${NESTBED_HOME}/dist/nestbed.jar"

for i in jline-0.9.9.jar log4j.jar useful.jar commons-logging.jar tinyos.jar; do
    CLASSPATH="${CLASSPATH}:${NESTBED_LIB}/$i"
done

exec $(java-config-2 --java) -classpath ${CLASSPATH} ${JAVA_OPTS} edu.clemson.cs.nestbed.client.cli.Example $*
