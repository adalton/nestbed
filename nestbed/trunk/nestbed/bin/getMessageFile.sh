#!/bin/bash

PATH=/bin:/usr/bin

BASEDIR=${1}
MESSAGE=${2}

FILES=$(egrep --with-filename "struct[ 	][ 	]*${MESSAGE}" ${BASEDIR}/*.h |
        awk -F: '{ print $1 }' 2> /dev/null)

UNIQ_FILES=$(echo $FILES | awk '{
    for (i = 1; i <= NF; ++i) {
        found = 0

        for (j = 1; j < i && !found; ++j) {
            found = ($j == $i);
        }

        if (!found) {
            print $i
        }
    }
}')

for i in ${UNIQ_FILES}; do
    echo $i;
done

