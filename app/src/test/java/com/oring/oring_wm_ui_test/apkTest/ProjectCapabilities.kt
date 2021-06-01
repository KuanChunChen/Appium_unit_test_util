package com.oring.oring_wm_ui_test.apkTest

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File


class ProjectCapabilities {

    companion object {

        fun androidBaseCapabilities(): DesiredCapabilities {
            val capabilities = DesiredCapabilities()
//            capabilities.setCapability("browserstack.appiumLogs", "true")
            capabilities.setCapability("autoAcceptAlerts", true)
            capabilities.setCapability("automationName", "UiAutomator2")
            capabilities.setCapability("newCommandTimeout", "6000")

            capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true)

//            capabilities.setCapability("appPackage", "dbx.taiwantaxi.dev")
//            capabilities.setCapability("appActivity", "dbx.taiwantaxi.v9.login.LoginActivity")

            return capabilities
        }


        fun iosBaseCapabilities(): DesiredCapabilities {
            val capabilities = DesiredCapabilities()

            capabilities.setCapability("automationName", "XCUITest")
//            capabilities.setCapability("browserName", "Safari")

//            capabilities.setCapability("bundleId", "<your bundle id>");
//            capabilities.setCapability("xcodeOrgId", "<your org id>");
//            capabilities.setCapability("xcodeSigningId", "iPhone Developer");
//            capabilities.setCapability("updatedWDABundleId", "appium.io.test")

//            capabilities.setCapability("newCommandTimeout", "6000")



            return capabilities
        }
    }


}


fun DesiredCapabilities.addXcodeConfigFile(configFilePath: String) {

    setCapability("xcodeConfigFile", configFilePath)
}


fun DesiredCapabilities.addXcodeOrgId(orgId: String) {

    setCapability("xcodeOrgId", orgId)
}


fun DesiredCapabilities.addXcodeSigningId(signingId: String) {

    setCapability("xcodeSigningId", signingId)
}

fun DesiredCapabilities.addBundleID(bundleId: String) {

    setCapability("bundleId", bundleId)
}

fun DesiredCapabilities.addDeviceName(deviceName: String) {

    setCapability("deviceName", deviceName)
}

fun DesiredCapabilities.addUdid(udid: String) {

    setCapability("udid", udid)
}

fun DesiredCapabilities.addPlatformName(platformName: String) {

    setCapability("platformName", platformName)
}

fun DesiredCapabilities.addPlatformVersion(platformVersion: String) {

    setCapability("platformVersion", platformVersion)
}

fun DesiredCapabilities.addApkPath(apkName: String) {

    val filePath = File(System.getProperty("user.dir"),"auto_test/apk/")
    print("$filePath")
    val app = File(filePath, apkName)
    setCapability("app", app.absolutePath)
}

fun DesiredCapabilities.addPackage(packageName: String) {

    setCapability("appPackage", packageName)
}

fun DesiredCapabilities.addAppActivity(activityName: String){
    setCapability("appActivity", activityName)
}

