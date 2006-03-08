#!/bin/bash

source ~/.gentoo/java-env
cd /home/adalton/src/java/nestbed/bin/

while [ true ]; do
    echo "Starting RMI Registry..."
    ./runRegistry.sh &
    sleep 3s

    echo "Starting NESTBed Server..."
    ./runServer.sh
    echo "... NESTBed Server has terminated."

    echo "Killing RMI Registry..."
    killall rmiregistry

    echo "Waiting 5 seconds to restart..."
    sleep 5s
done
