#!/bin/bash
# $Id$
export PATH=/usr/bin

mysqldump --opt --databases nestbed -u nestbed -p > nestbed.sql

