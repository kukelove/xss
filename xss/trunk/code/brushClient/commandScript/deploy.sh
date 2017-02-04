#!/bin/sh
trap "echo 'execute-result-failed:trap error';exit 1" ERR
if [ ! -d "/home/apps" ];then
      mkdir "/home/apps"
fi
if [ ! -d "/home/apps/bcInstaller" ];then
	mkdir "/home/apps/bcInstaller"
fi
cd /home/apps/bcInstaller
if [ -f "install.sh" ];then
    rm -f install.sh
fi
wget -q http://183.250.161.227:10001/brushms/clientInstaller/install.sh
chmod 755 install.sh
sh install.sh
cd ..
rm -rf bcInstaller
echo "execute-result-success"



