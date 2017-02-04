#!/bin/sh
trap "echo 'update-result-failed: trap error';exit 1" ERR

echo "begin update brushClient>>>>>>"

brushClientPath="/home/apps/brushClient"
cd $brushClientPath

wgetRootPath="http://183.250.161.227:10001/brushms/clientInstaller"

fs=("brushClient.jar" "lib.tar.gz" "script.tar.gz" "phantomjs.tar.gz" "start.sh" "stop.sh")

echo "args num:"$#
if [ $# > 0 ];then
  fs=
  i=0
  for arg in "$@"
  do
     fs[i++]=$arg
  done
fi

for f in ${fs[@]}
do
    echo "wget -q "${wgetRootPath}"/"${f}">>>"
    wget -q ${wgetRootPath}"/"${f}
    if [[ ${f} =~ "tar.gz" ]];then
        tar -xf ${f}
        rm -rf ${f}
    fi
    if [[ ${f} =~ ".sh" ]];then
            chmod 755 ${f}
    fi
done
chmod 755 phantomjs/bin/phantomjs
echo "update-result-success"
