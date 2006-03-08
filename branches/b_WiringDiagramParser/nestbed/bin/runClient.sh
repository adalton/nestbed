#!/bin/bash
# $Id$

CLASSPATH="/home/adalton/src/java/nestbed/dist/nestbed.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/log4j.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/useful.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/commons-logging.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/tinyos.jar"

JAVA_OPTS="-Djava.security.policy=/home/adalton/src/java/nestbed/java.policy"

echo ${CLASSPATH}

exec java -classpath ${CLASSPATH} ${JAVA_OPTS} edu.clemson.cs.nestbed.client.Client $*
