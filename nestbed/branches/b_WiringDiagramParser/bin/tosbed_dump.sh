#!/bin/bash
# $Id$
export PATH=/usr/bin

mysqldump --opt --databases tosbed -u tosbed -p > tosbed.sql

