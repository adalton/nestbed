#!/bin/bash
# $Id:$

CLASSPATH="/home/adalton/src/java/nestbed/dist/nestbed.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/jline-0.9.9.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/log4j.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/useful.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/commons-logging.jar"
CLASSPATH="${CLASSPATH}:/home/adalton/src/java/nestbed/lib/tinyos.jar"

JAVA_OPTS="-Djava.security.policy=/home/adalton/src/java/nestbed/misc/java.policy"

exec java -classpath ${CLASSPATH} ${JAVA_OPTS} edu.clemson.cs.nestbed.client.cli.Example $*
