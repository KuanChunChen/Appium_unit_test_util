 #!/bin/bash
bundleID=$1
appName =$1
if [ -z "$bundleID" ];then  
echo "please input the bundleID"  
bundleID="willy.appium-io-test"  
else  
echo "input ok"  
fi  
echo "Start with ${bundleID}"   
idevicesyslog | grep --line-buffered 'appium.io.test' | tee wn_ios_log.txt

