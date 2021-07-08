package com.oring.oring_wm_ui_test.apkTest

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.reporter.ExtentSparkReporter
import com.google.gson.Gson
import com.oring.oring_wm_ui_test.apkTest.constants.Url
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.junit.After
import org.junit.Before
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

open class BaseExtentReportTest  {

    protected var driver: AndroidDriver<MobileElement>? = null
//    protected var driver: IOSDriver<MobileElement>? = null
    protected open var capabilities: DesiredCapabilities? = null
    private val webDriverURL: URL = URL(Url.APPIUM_LOCAL_URL)
    protected open var extent: ExtentReports? = null




    @Before
    fun setUp() {
        println("before ,${Gson().toJson(capabilities)} ")
        this.driver = AndroidDriver(webDriverURL, capabilities)
//        this.driver = IOSDriver(webDriverURL, capabilities)
        this.extent = ExtentReports().apply {
            attachReporter(ExtentSparkReporter(System.getProperty("user.dir") + Url.EXTENT_REPORT_HTML_SAVE_PATH))
        }
    }

    @After
    fun tearDown() {
//        println("after")
//        this.driver?.quit() ?: throw Exception("Driver instance was unable to quit.")
        extent?.flush()
    }

}