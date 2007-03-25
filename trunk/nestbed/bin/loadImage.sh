#!/bin/bash
# $Id$


JAVA_OPTS="-Djdbc.drivers=com.mysql.jdbc.Driver:org.apache.commons.dbcp.PoolingDriver -Djava.security.policy=/opt/nestbed/misc/java.policy"

TOSBED_HOME=/home/adalton/src/java/nestbed
TOSBED_LIB=${TOSBED_HOME}/lib

CLASSPATH=${TOSBED_HOME}/dist/nestbed.jar

for i in ${TOSBED_LIB}/*; do
    CLASSPATH="${CLASSPATH}:$i"
done

echo $CLASSPATH
java ${JAVA_OPTS} -classpath ${CLASSPATH} edu.clemson.cs.nestbed.server.tools.LoadImage $*
