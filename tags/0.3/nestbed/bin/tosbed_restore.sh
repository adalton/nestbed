#!/bin/bash
export PATH=/usr/bin

if [ $# -ne 1 ]; then
    echo "usage:  $0 <mysql schema>" 1>&2
    exit 1
fi

(
echo "SET AUTOCOMMIT=0;"
echo "SET FOREIGN_KEY_CHECKS=0;"
cat $1
echo "SET FOREIGN_KEY_CHECKS=1;"
echo "COMMIT;"
echo "SET AUTOCOMMIT=1;"
) | mysql --user=tosbed -p tosbed
