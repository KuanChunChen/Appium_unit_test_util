 #!/bin/bash
bundleID=$1
appName =$1
if [ -z "$bundleID" ];then  
echo "please input the bundleID"  
bundleID="com.oring.weidmulleriosapp"  
else  
echo "input ok"  
fi  
echo "Start with ${bundleID}"   
idevicesyslog | grep --line-buffered 'com.oring.weidmulleriosapp' | tee wn_ios_log.txt

