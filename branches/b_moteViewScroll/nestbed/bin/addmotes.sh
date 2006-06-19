#!/bin/bash
# $Id$

MOTES=$(ls /dev/motes)

(
for i in ${MOTES}; do
    echo "INSERT INTO Motes(moteSerialID, moteTypeID) VALUES('$i', 3);"
done
) | mysql -u nestbed -p nestbed
