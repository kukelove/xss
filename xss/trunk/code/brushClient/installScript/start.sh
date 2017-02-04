#!/bin/sh
trap "echo 'start-result-failed:trap error';exit 1" ERR
num=100
if [ $1 ]; then
        num=$1
fi
echo "start brushClient>>>>>>"$num
cur_jdk=`java -version 2>&1 | head -1`
echo "current jdk :"$cur_jdk
#expected_jdk=`java -version 2>&1 | head -1 |grep "1.8"`
# 检查是否已经安装1.8版本的jdk
#expected_jdk=`echo $cur_jdk | grep "1.8"`
if [[ ! $cur_jdk =~ "1.8" ]];then
    echo "export JAVA_HOME=/usr/local/java/jdk1.8.0_11"
    if [ ! -d /usr/local/java/jdk1.8.0_11 ];then
        echo "start-result-failed: /usr/local/java/jdk1.8.0_11 not exists!"
    fi
    export JAVA_HOME=/usr/local/java/jdk1.8.0_11
    export JRE_HOME=/usr/local/java/jdk1.8.0_11/jre
    export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
    export PATH=$JAVA_HOME/bin:$PATH
else
    echo "use current jdk"
fi

#nohup /usr/local/java/jdk1.8.0_11/bin/java -jar /home/apps/brushClient/brushClient.jar >> /home/apps/brushClient/run.log 2>&1 &

curPath=`pwd`
if [ ! -d $curPath"/logs" ];then
	mkdir $curPath"/logs"
fi
runlogf=$curPath/logs/`date '+%Y-%m-%d'`_run.log
nohup java -jar $curPath/brushClient.jar $num >> $runlogf 2>&1 &
echo "start-result-success"
