#!/bin/sh
trap "echo 'install-result-failed: trap error';exit 1" ERR

echo "begin install brushClient>>>>>>"
#安装文件的路径,即install.sh等文件的路径
installFiles_path=`pwd`
echo "install files path :"$installFiles_path

#根路径
brushClientPath="/home/apps/brushClient"
#判断安装文件路径是否在/home/apps/brushClient路径下
if [ `echo $installFiles_path | grep $brushClientPath` ];then
	echo "result-failed: install.sh can not be under "$brushClientPath"!"
	exit 1
fi

#mkdir $brushClientPath ,wget files
if [ -d $brushClientPath ];then
        printf "remove old brushClient path:'%s'\n" $brushClientPath
        rm -rf $brushClientPath
fi
printf "create brushClient path: '%s'\n" $brushClientPath
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
#wget $wgetJdkPath
wget $wgetBrushClientJarPath
wget $wgetLibPath
wget $wgetScriptPath
wget $wgetPhantomjsPath
wget $wgetStartPath
wget $wgetStopPath
wget $wgetUpdatePath

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

jdkPath="/usr/local/java/jdk1.8.0_11"
if [ ! -d $jdkPath ];then
       if [ ! -d "/usr/local/java" ];then
       	  mkdir "/usr/local/java"
       fi
       cd /usr/local/java
       cp $brushClientPath"/jdk-8u11-linux-x64.tar.gz" .
       rm -f $brushClientPath"/jdk-8u11-linux-x64.tar.gz"
       tar -zxf jdk-8u11-linux-x64.tar.gz
       echo "end install jdk1.8<<<<<<"
else
	echo "jdk1.8.0_11 already exist!not need install"
fi
echo "end install brushClient<<<<<<<"
echo "install-result-success"
