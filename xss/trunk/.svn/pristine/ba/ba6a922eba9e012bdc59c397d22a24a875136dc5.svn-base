#!/bin/sh
trap "echo 'install-result-failed: trap error';exit 1" ERR

echo "begin install brushClient>>>>>>"
#安装文件的路径,即install.sh等文件的路径
installFiles_path=`pwd`
echo "install files path :"$installFiles_path
brushClientPath="/home/apps/brushClient"

#判断安装文件路径是否在/home/apps/brushClient路径下
if [ `echo $installFiles_path | grep $brushClientPath` ];then
	echo "install-result-failed: install.sh can not be under "$brushClientPath"!"
	exit 1
fi

#根路径
if [ -d $brushClientPath ];then
   echo "install-result-failed:"$brushClientPath" has already exists!"
   exit 1
fi

#mkdir $brushClientPath ,wget files
#if [ -d $brushClientPath ];then
#        printf "remove old brushClient path:'%s'\n" $brushClientPath
#        rm -rf $brushClientPath
#fi

printf "create brushClient path: '%s'\n" $brushClientPath
if [ ! -d "/home/apps" ];then
    mkdir "/home/apps"
fi
mkdir $brushClientPath

#获取安装包
cd $brushClientPath

echo "begin wget installer>>>>>>"
wgetRootPath="http://183.250.161.227:10001/brushms/clientInstaller"
wgetJdkPath=${wgetRootPath}"/jdk-8u11-linux-x64.tar.gz"
wgetBrushClientJarPath=${wgetRootPath}"/brushClient.jar"
wgetLibPath=${wgetRootPath}"/lib.tar.gz"
wgetScriptPath=${wgetRootPath}"/script.tar.gz"
wgetPhantomjsPath=${wgetRootPath}"/phantomjs.tar.gz"
wgetStartPath=${wgetRootPath}"/start.sh"
wgetStopPath=${wgetRootPath}"/stop.sh"
wgetUpdatePath=${wgetRootPath}"/update.sh"
#printf "wgetJdkPath is '%s'\n" $wgetJdkPath
wget -q $wgetBrushClientJarPath
wget -q $wgetLibPath
wget -q $wgetScriptPath
wget -q $wgetPhantomjsPath
wget -q $wgetStartPath
wget -q $wgetStopPath
wget -q $wgetUpdatePath

tar -xf lib.tar.gz
tar -xf script.tar.gz
tar -xf phantomjs.tar.gz
rm -f lib.tar.gz
rm -f script.tar.gz
rm -f phantomjs.tar.gz

chmod 755 brushClient.jar start.sh stop.sh update.sh phantomjs/bin/phantomjs

echo "end wget installer<<<<<<"

#安装jdk1.8
echo "begin install jdk1.8>>>>>>"

cur_jdk=`java -version 2>&1 | head -1`
echo "current jdk :"$cur_jdk
#expected_jdk=`java -version 2>&1 | head -1 |grep "1.8"`
# 检查是否已经安装1.8版本的jdk
#expected_jdk=`echo $cur_jdk | grep "1.8"`
if [[ ! $cur_jdk =~ "1.8" ]];then
         if [ ! -d "/usr/local/java" ];then
              mkdir "/usr/local/java"
         fi
         cd /usr/local/java
         wget -q $wgetJdkPath
         tar -xf jdk-8u11-linux-x64.tar.gz
         echo "end install jdk1.8<<<<<<"
else
        echo "jdk1.8 already exist!not need install"
fi
echo "install-result-success"
