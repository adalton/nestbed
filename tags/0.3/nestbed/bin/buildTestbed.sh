#!/bin/sh

JAVA_OPTS="-Djdbc.drivers=com.mysql.jdbc.Driver:org.apache.commons.dbcp.PoolingDriver -Djava.security.policy=/home/adalton/src/java/nestbed/java.policy"

TOSBED_HOME=/home/adalton/src/java/nestbed
TOSBED_LIB=${TOSBED_HOME}/lib

CLASSPATH=${TOSBED_HOME}/dist/nestbed-0.3.jar

for i in ${TOSBED_LIB}/*; do
    CLASSPATH="${CLASSPATH}:$i"
done

echo $CLASSPATH
java ${JAVA_OPTS} -classpath ${CLASSPATH} edu.clemson.cs.nestbed.server.tools.BuildTestbed $*