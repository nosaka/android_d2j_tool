#!/bin/sh

MY_DIR=$(cd $(dirname $0) && pwd)

APK_DIR="./apk/"
APK_FILE="target.apk"

DECOMP_DIR="./decomp"

D2J_SH="./dex2jar-2.0/d2j-dex2jar.sh"
D2J_JAR="classes-dex2jar.jar"

mkdir -p $APK_DIR
rm -rf $APK_DIR*

mkdir -p $DECOMP_DIR
rm -rf $DECOMP_DIR

echo -n "Please input package name."
read in_package

echo "Search [$in_package]"
pm_result=`adb shell pm list packages -f | grep $in_package | head -1`

echo "================"
echo "$pm_result"
echo "================"

target_pkg=`echo $pm_result | sed -e "s/^.*=\(.*\).*$/\1/"`
target_apk=`echo $pm_result | sed -e "s/^.*package:\(.*\)=.*.*$/\1/"`
echo $target_apk

echo -n "Analyze [$target_pkg]? y/n"
read is_analyze

case $is_analyze in 
	y)
		echo 'last name of Mami is Tomoe.'
		;;
	*)
		echo 'Exit.'
		exit
		;;
	esac

rm -rf $APK_DIR*
adb pull $target_apk $APK_DIR$APK_FILE

cd  $APK_DIR
unzip $APK_FILE
rm -rf $APK_FILE

cd $MY_DIR

$D2J_SH $APK_DIR"classes.dex" -o $APK_DIR$D2J_JAR  --force 

cd  $APK_DIR
unzip $D2J_JAR

cd $MY_DIR

jad -o -r -sjava -d$DECOMP_DIR $APK_DIR"**/*.class"



