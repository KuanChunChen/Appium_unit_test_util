package com.oring.oring_wm_ui_test.apkTest.model.appium

data class Caps(
    val appActivity: String,
    val appPackage: String,
    val autoAcceptAlerts: Boolean,
    val autoGrantPermissions: Boolean,
    val automationName: String,
    val databaseEnabled: Boolean,
    val desired: Desired,
    val deviceApiLevel: Int,
    val deviceManufacturer: String,
    val deviceModel: String,
    val deviceName: String,
    val deviceScreenDensity: Int,
    val deviceScreenSize: String,
    val deviceUDID: String,
    val javascriptEnabled: Boolean,
    val locationContextEnabled: Boolean,
    val networkConnectionEnabled: Boolean,
    val newCommandTimeout: Int,
    val pixelRatio: Double,
    val platform: String,
    val platformName: String,
    val platformVersion: String,
    val statBarHeight: Int,
    val takesScreenshot: Boolean,
    val viewportRect: ViewportRect,
    val warnings: Warnings,
    val webStorageEnabled: Boolean
)