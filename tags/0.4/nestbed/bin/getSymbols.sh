#!/bin/bash
# $Id$

export PATH="/bin:/usr/bin"

exec readelf -sW $*            | \
         grep   OBJECT         | \
         egrep  '\$'           | \
         awk    '{ print $8 }' | \
         sed -e 's/\$/\./g'    | \
         sort
