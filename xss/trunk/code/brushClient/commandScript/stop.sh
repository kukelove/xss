#!/bin/sh
trap "echo 'execute-result-failed: trap error';exit 1" ERR
cd /home/apps/brushClient
sh stop.sh
echo "execute-result-success"



