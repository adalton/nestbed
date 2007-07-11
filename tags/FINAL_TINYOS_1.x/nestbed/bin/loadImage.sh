#!/bin/bash
# $Id$

JAVA_OPTS="-Djdbc.drivers=com.mysql.jdbc.Driver:org.apache.commons.dbcp.PoolingDriver -Djava.security.policy=${NESTBED_HOME}/misc/java.policy"
CLASSPATH="${NESTBED_HOME}/dist/nestbed.jar"

for i in "${NESTBED_LIB}"/*; do
    CLASSPATH="${CLASSPATH}:$i"
done

exec $(java-config-2 --java) ${JAVA_OPTS} -classpath ${CLASSPATH} edu.clemson.cs.nestbed.server.tools.LoadImage $*
