 #!/bin/bash
packageName=$1
  
if [ -z "$packageName" ];then  
echo "please input the packageName"  
packageName="com.oringnet.wm"  
else  
echo "input ok"  
fi    
pid=`./platform-tools/adb shell ps | grep $packageName | awk '{print $2}'`
echo ${pid}
./platform-tools/adb logcat -P ""
./platform-tools/adb shell setprop debug.dla.nttd.logger open
./platform-tools/adb logcat | grep --line-buffered ${pid} 2>&1 | tee wn_log.txt
