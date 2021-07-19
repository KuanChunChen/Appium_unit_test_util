package com.oring.oring_wm_ui_test.apkTest.util

import com.google.gson.Gson
import com.oring.oring_wm_ui_test.apkTest.model.appium.AndroidInfo
import com.oring.oring_wm_ui_test.apkTest.model.appium.AppiumInfo
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver

class AndroidInfoUtil {

    private fun getAllCap(driver: AndroidDriver<MobileElement>?): AppiumInfo {

        val capabilities = driver?.capabilities
        return Gson().fromJson(Gson().toJson(capabilities), AppiumInfo::class.java)


    }

    fun getStartInfo(driver: AndroidDriver<MobileElement>?): String {
        val appiumInfo = getAllCap(driver)
        val androidInfo = AndroidInfo(
                "Iot Terminal Block",
                "2.6.0 A1",
                appiumInfo.caps.appActivity,
                appiumInfo.caps.appPackage,
                appiumInfo.caps.autoAcceptAlerts.toString(),
                appiumInfo.caps.autoGrantPermissions.toString(),
                appiumInfo.caps.automationName,
                appiumInfo.caps.desired.platformName,
                appiumInfo.caps.desired.deviceName,
                appiumInfo.caps.desired.newCommandTimeout.toString(),
                appiumInfo.caps.desired.platformVersion,
                appiumInfo.caps.deviceApiLevel.toString(),
                appiumInfo.caps.deviceManufacturer,
                appiumInfo.caps.deviceModel,
                appiumInfo.caps.deviceScreenDensity.toString(),
                appiumInfo.caps.deviceScreenSize,
                appiumInfo.caps.deviceUDID,
                appiumInfo.caps.pixelRatio.toString(),
                appiumInfo.caps.platform
        )




        return Gson().toJson(androidInfo)

    }
}