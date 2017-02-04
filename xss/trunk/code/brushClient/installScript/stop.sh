#!/bin/sh
trap "echo 'stop-result-failed:trap error';exit 1" ERR
clientPIDS=`ps -ef | grep java | awk '/brushClient/{print $2}'`
if [ "$clientPIDS" ];then
        for i in $clientPIDS
        do
                kill -9 $i
        done
fi
phantomjsCount=`ps -ef | grep phantomjs |grep -v "grep" |wc -l`
if [ $phantomjsCount -gt 0 ];then
   killall -9 phantomjs
fi
echo "stop-result-success"