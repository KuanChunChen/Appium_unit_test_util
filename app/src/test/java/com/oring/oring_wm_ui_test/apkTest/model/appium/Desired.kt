package com.oring.oring_wm_ui_test.apkTest.model.appium

data class Desired(
    val appActivity: String,
    val appPackage: String,
    val autoAcceptAlerts: Boolean,
    val autoGrantPermissions: Boolean,
    val automationName: String,
    val deviceName: String,
    val newCommandTimeout: Int,
    val platformName: String,
    val platformVersion: String
)