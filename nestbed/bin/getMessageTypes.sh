#!/bin/bash
# $Id$

PATH=/bin:/usr/bin

BASEDIR=${1}
PLATFORM=${2}

MSG_TYPES=$(grep "case AM_" ${BASEDIR}/build/${PLATFORM}/app.c 2> /dev/null | \
    sort -u | \
    sed -e 's/AM_//g' -e 's/://g' | \
    awk '{ print $2 }' 2> /dev/null)


TYPES=""
for i in ${MSG_TYPES}; do
    for j in ${BASEDIR}/*.h; do
        MATCHES="$MATCHES $(grep --ignore-case $i $j 2> /dev/null | \
                sed -e 's/;//' -e 's/{//' 2>/dev/null)"
    done

    #MATCHES=$(grep --ignore-case $i ${BASEDIR}/*.h 2> /dev/null)
    if [ $? -eq 0 ]; then
        for j in $(echo ${MATCHES} | grep struct 2> /dev/null); do
            if $(echo $j | grep --silent --ignore-case $i 2> /dev/null); then
                TYPES="${TYPES} $j"
            fi
        done
    fi
done

echo $TYPES | sed -e 's/AM_[A-Z][A-Z]*//g' | awk ' {
    for (i = 1; i <= NF; ++i) {
        found = 0

        for (j = 1; j < i && !found; ++j) {
            found = ($j == $i);
        }

        if (!found) {
            print $i
        }
    }
}' > /tmp/types.$$

cat /tmp/types.$$ | sed -e 's///' | sort -u
