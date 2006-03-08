#!/bin/bash

while [ true ]; do
    PREMOTES=$(ls /dev/motes/)

    echo -n "Remove a mote and press enter: "
    read blah

    POSTMOTES=$(ls /dev/motes/)

    for i in ${PREMOTES}; do
        found=false

        for j in ${POSTMOTES}; do
            if [ "$i" == "$j" -a "$found" == "false" ]; then
                found=true
            fi
        done

        if [ "$found" == "false" ]; then
            echo $i
        fi
    done

    echo -n "Press any key to continue..."
    read blah
done
