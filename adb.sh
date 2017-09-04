#!/bin/sh

# constant
DIR_TOOL="./tools/"
DIR_TEMPLATE="./template/"
DIR_WORK="./work/"
DIR_DECOMP="./decomp/"
DIR_DECOMP_SOURCE=$DIR_DECOMP"app/src/main/"
DIR_DECOMP_SOURCE_JAVA=$DIR_DECOMP_SOURCE"java/"

APK_FILE=$DIR_WORK"target.apk"
APK_JAR=$DIR_WORK"classes-dex2jar.jar"

CMD_D2J=$DIR_TOOL"dex2jar-2.0/d2j-dex2jar.sh"
CMD_APKTOOL=$DIR_TOOL"apktool.sh" 

# apk格納ディレクトリの作成、及びディレクトリ内ファイル削除
mkdir -p $DIR_WORK
rm -rf $DIR_WORK*

# decomp格納ディレクトリの作成、及びディレクトリ内ファイル削除
mkdir -p $DIR_DECOMP
rm -rf $DIR_DECOMP*

# パッケージ名の入力
echo -n "Please input package name."
read in_package

# 端末内パッケージの検索
echo "Search [$in_package] from the device."
pm_result=`adb shell pm list packages -f | grep $in_package | head -1`

if [ ! -n "$pm_result" ]; then
	echo "[$in_package] was not found." >&2
	exit
fi

echo "================"
echo "$pm_result"
echo "================"


# 対象パッケージの確認
target_pkg=`echo $pm_result | sed -e "s/^.*=\(.*\).*$/\1/"`
target_apk=`echo $pm_result | sed -e "s/^.*package:\(.*\)=.*.*$/\1/"`
echo $target_apk

echo -n "Analyze [$target_pkg]? y/n"
read is_analyze

case $is_analyze in 
	y*)
		;;
	*)
		echo 'Exit.'
		exit
		;;
	esac

# 端末からapkの抜き出し
echo 'Pull out apk from device.'
adb pull $target_apk $APK_FILE >/dev/null

echo 'Please wait a moment...'

# apktoolの実行
$CMD_APKTOOL decode --no-src $APK_FILE -o $DIR_DECOMP_SOURCE -f >/dev/null 2>&1

# dex2jarの実行
$CMD_D2J $DIR_DECOMP_SOURCE"classes.dex" -o $APK_JAR --force >/dev/null 2>&1

# jarの解凍
unzip $APK_JAR  -d $DIR_WORK > /dev/null

# jadの実行
mkdir -p $DIR_DECOMP_SOURCE
jad -o -r -sjava -d$DIR_DECOMP_SOURCE_JAVA $DIR_WORK"**/*.class" >/dev/null 2>&1

# gradleファイルのコピー
cp $DIR_TEMPLATE"settings.gradle" $DIR_DECOMP"settings.gradle"
cp $DIR_TEMPLATE"pj_build.gradle" $DIR_DECOMP"build.gradle"
cp $DIR_TEMPLATE"app_build.gradle" $DIR_DECOMP"app/build.gradle"

echo '★★★★ Success ★★★★'

# 後処理
rm -rf $DIR_WORK



