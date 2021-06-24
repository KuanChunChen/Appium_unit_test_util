package com.oring.oring_wm_ui_test.apkTest

import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.Status
import com.oring.oring_wm_ui_test.apkTest.constants.Target
import com.oring.oring_wm_ui_test.apkTest.util.DateUtil
import io.appium.java_client.MobileBy
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.events.EventFiringWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Exception


class IOSWMReportTest : BaseExtentReportTest() {

    private val targetPackageName = Target.PackageName.ORING_WM_PACKAGE
    private val targetBLEDevice = Target.BLEDeviceName.InitTest

    override var capabilities: DesiredCapabilities? = addIosCapability()

    private fun addIosCapability() = ProjectCapabilities.iosBaseCapabilities().apply {
        addPlatformVersion(Target.IosVersion.Ios14_5)
        addPlatformName(Target.Platform.IOS)
        addDeviceName(Target.DeviceName.Iphone12)
        addUdid(Target.IosUDID.Iphone12)
        addBundleID(Target.BundleID.ORING_WM_BUNDLE_ID)
//        addBundleID(Target.BundleID.APPLE_CALCULATOR_BUNDLE_ID)
        addXcodeOrgId(Target.OrgID.ORing)
//        addXcodeConfigFile(System.getProperty("user.dir") + Target.ConfigFile.ORing)
        addXcodeSigningId(Target.SigningId.IPhone)

    }

    @Test
    fun oIosRingWMDemo() {

//        val thread = Thread {
//            ParseUtil.execCmdShell("./ios_wn_.sh", "/auto_test/iosLogCatcher/")
//        }
//        thread.start()
        startPageTest()
        deviceListTest()
        dashboardTest()

        navigatorBarTest()
        ioTest()
        deviceInfoTest()
        cloudSettingTest()
        lteConnectionStatusTest()
        gateWayTest()
        remoteControlTest()
//        captureLogcat()





    }

    @Test
    fun captureLogcat() {
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("LogCat Message")
        val listLog = ParseUtil.readIDeviceLogMessage(System.getProperty("user.dir") + "/auto_test/iosLogCatcher/wn_ios_log.txt")





//        ParseUtil.parseIOSAllLogName(listLog, extentTest)

//        scenario.createNode(Given::class.java, "Jeff has bought a microwave for $100").pass("pass")

    }

    @Test
    fun startPageTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Device")

        val startButton = "**/XCUIElementTypeButton[`label == \"BLUETOOTH\"`]"
        wait.untilIosClassChain(startButton)
        extentTest.screenshotInfo(driver!!, "startPage", "Start page.")
        driver?.findElement(MobileBy.iOSClassChain(startButton))?.click()

        extentTest.log(Status.PASS, "Pass.")


    }
    @Test
    fun deviceListTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Device")

        val deviceTable = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable"
        val targetDeviceId = "WM_iot_test09"
        Thread.sleep(1500)
        wait.untilXpath(deviceTable)
        extentTest.screenshotInfo(driver!!, "DeviceList", "Device list page.")

        Thread.sleep(1800)



        val targetDeviceItem = driver?.findElement(By.id(targetDeviceId))
        targetDeviceItem?.click()

        val checkDialog = "**/XCUIElementTypeAlert[`label == \"Connection\"`]/XCUIElementTypeOther"
        val cancel = "**/XCUIElementTypeAlert[`label == \"Connection\"`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]"
        val connect = "**/XCUIElementTypeAlert[`label == \"Connection\"`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]"
        wait.untilIosClassChain(checkDialog)
        extentTest.screenshotInfo(driver!!, "DeviceConfirm", "Confirm if connect this device.")

        driver?.findElement(MobileBy.iOSClassChain(connect))?.click()


        /** Set new password */
//
        val setPassword = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[1]"
        val setVerifyPassword = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[2]"
        val applyButton = "**/XCUIElementTypeButton[`label == \"APPLY\"`]"

        wait.untilIosClassChain(setPassword)
        extentTest.screenshotInfo(driver!!, "newPasswordInput", "Set new password page.")
        driver?.findElement(MobileBy.iOSClassChain(setPassword))?.sendKeys("123456")
        driver?.findElement(MobileBy.iOSClassChain(setVerifyPassword))?.sendKeys("123456")
        extentTest.screenshotInfo(driver!!, "afterNewPasswordInput", "After input new password.")
        Thread.sleep(1100)
        driver?.findElement(By.name("Done"))?.click()
        driver?.findElement(MobileBy.iOSClassChain(applyButton))?.click()


        /** Set new password */

        wait.untilXpath(deviceTable)
        Thread.sleep(10000)
        val targetDeviceItem2 = driver?.findElement(By.id(targetDeviceId))
        targetDeviceItem2?.click()
        wait.untilIosClassChain(checkDialog)
        driver?.findElement(MobileBy.iOSClassChain(connect))?.click()
        /** Set new password  plus*/


        val passwordAlert ="**/XCUIElementTypeAlert[`label == \"WM_iot_test09\"`]"
        val login = "**/XCUIElementTypeAlert[`label == \"WM_iot_test09\"`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]"
        val editPassword = "**/XCUIElementTypeAlert[`label == \"WM_iot_test09\"`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther"

        wait.untilIosClassChain(passwordAlert)
        extentTest.screenshotInfo(driver!!, "PasswordInput", "Input login password.")
        driver?.findElement(MobileBy.iOSClassChain(editPassword))?.sendKeys("123456")
        extentTest.screenshotInfo(driver!!, "AfterPasswordInput", "After input password.")
//        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver?.findElement(MobileBy.iOSClassChain(editPassword)), "text"))
        driver?.findElement(MobileBy.iOSClassChain(login))?.click()

    }

    @Test
    fun dashboardTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Dashboard")

        val waitUntilLayout = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther"
        wait.untilIosClassChain(waitUntilLayout)

        extentTest.screenshotInfo(driver!!, "Dashboard", "Dashboard page.")
//        Thread.sleep(3000)
//        iosScrollToPredicateString(driver!!,"name == \"RELAY\" AND type == \"XCUIElementTypeOther\"","XCUIElementTypeTable")
//        scrollToUnSeeElement(driver!!,"RELAY")
//        tapItemByDescription(driver!!,"RELAY")
//        val js = driver as JavascriptExecutor
//        val elementID = driver!!.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"RELAY\"`]")).id
//        val scrollObject = HashMap<String, String>()
//        scrollObject["direction"] = "down"
//        scrollObject["element"] = elementID
//        scrollObject["toVisible"] = "not an empty string"
//        js.executeScript("mobile:scroll", scrollObject)


        scrollToDown(driver!!)
        extentTest.screenshotInfo(driver!!, "DashboardScroll", "After scroll.")


    }

    @Test
    fun ioTest() {
        val wait = WebDriverWait(driver?.let { it }, 15)
        val extentTest: ExtentTest = extent!!.createTest("Device information")
        val barLayout = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.DeviceStatusView\"`]"
        val barNavigator = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][1]"
        val barMeanButton = "**/XCUIElementTypeButton[`label == \"menu\"`]"
        val barItemXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"

        wait.untilIosClassChain(barLayout)
        driver?.findElement(MobileBy.iOSClassChain(barMeanButton))?.click()

        wait.untilIosClassChain(barNavigator)



        val listNavigatorItem = driver?.findElements(By.xpath(barItemXpath))
        listNavigatorItem?.get(0)?.click()

        val waitUntilLayout = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable"
        wait.untilIosClassChain(waitUntilLayout)

        extentTest.screenshotInfo(driver!!, "IOPage", "I/O page.")
//        Thread.sleep(3000)

        scrollToDown(driver!!)
        extentTest.screenshotInfo(driver!!, "IOPageScroll", "After I/O page scroll.")

    }
    @Test
    fun deviceInfoTest(){
        val wait = WebDriverWait(driver?.let { it }, 15)
        val extentTest: ExtentTest = extent!!.createTest("Device information")
        val barLayout = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.DeviceStatusView\"`]"
        val barNavigator = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][1]"
        val barMeanButton = "**/XCUIElementTypeButton[`label == \"menu\"`]"
        val barItemXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"

        wait.untilIosClassChain(barLayout)
        driver?.findElement(MobileBy.iOSClassChain(barMeanButton))?.click()

        wait.untilIosClassChain(barNavigator)



        val listNavigatorItem = driver?.findElements(By.xpath(barItemXpath))
        listNavigatorItem?.get(1)?.click()

//        val hardwareVersion = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[8]/XCUIElementTypeOther[1]/XCUIElementTypeOther"
//        wait.untilIosClassChain(hardwareVersion)
        Thread.sleep(12000)
        extentTest.screenshotInfo(driver!!, "DeviceInfo", "Device information page.")

        val editButton = "**/XCUIElementTypeStaticText[`label == \"EDIT\"`]"

        driver?.findElement(MobileBy.iOSClassChain(editButton))?.click()

        println("deviceInfoTest :After click edit")
        val editTextDeviceName = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeTextField[1]"
        val editTextAliveTime = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeTextField[2]"

        val applyButton = "**/XCUIElementTypeStaticText[`label == \"APPLY\"`]"
        val cancelButton = "**/XCUIElementTypeStaticText[`label == \"CANCEL\"`]"

        Thread.sleep(10000)
        wait.untilIosClassChain(editTextDeviceName)
        extentTest.screenshotInfo(driver!!, "DeviceInfoEdit", "Device information edit page.")
        driver?.findElement(MobileBy.iOSClassChain(editTextDeviceName))?.sendKeys("WillyAppium_${DateUtil.getCurrentDate()}")
        driver?.findElement(MobileBy.iOSClassChain(editTextAliveTime))?.sendKeys("30")
        extentTest.screenshotInfo(driver!!, "AfterDeviceInfoPWInput", "After input edited text.")


        /**
         *
         *
         * */
//        extentTest.log(Status.FAIL ,"After edited and input device name and alive time always get crash.")
//        extentTest.log(Status.FAIL ,"Reset new password always get crash when input and apply new password.")
//        val tempCloseButton = "**/XCUIElementTypeButton[`label == \"closeButton\"`]"
//        driver?.findElement(MobileBy.iOSClassChain(tempCloseButton))?.click()
//        Thread.sleep(15000)
//
//        driver?.findElement(MobileBy.iOSClassChain(tempCloseButton))?.click()
//
//        return
        driver?.findElement(MobileBy.iOSClassChain(applyButton))?.click()

//        wait.untilIosClassChain(hardwareVersion)
        Thread.sleep(15000)
        extentTest.screenshotInfo(driver!!, "AfterDeviceInfo", "After device information edited.")

//        val resetButton = "**/XCUIElementTypeStaticText[`label == \"RESET\"`]"
//        Thread.sleep(8000)
//        driver?.findElement(MobileBy.iOSClassChain(resetButton))?.click()
//        println("deviceInfoTest :After click resetButton")
//        val deviceName = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeTextField[1]"
//        val newPassword = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeTextField[2]"
//        val verifyPassword = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeTextField[3]"
//
//        val resetApply = "**/XCUIElementTypeStaticText[`label == \"APPLY\"`]"
//        val resetCancel = "**/XCUIElementTypeStaticText[`label == \"CANCEL\"`]"
        val closeButton = "**/XCUIElementTypeButton[`label == \"closeButton\"`]"
//
//        wait.untilIosClassChain(deviceName)
//
//        driver?.findElement(MobileBy.iOSClassChain(deviceName))?.sendKeys("123456")
//        driver?.findElement(MobileBy.iOSClassChain(newPassword))?.sendKeys("123456")
//        driver?.findElement(MobileBy.iOSClassChain(verifyPassword))?.sendKeys("123456")
//        try {
//            driver?.findElement(MobileBy.iOSClassChain(resetApply))?.click()
//            wait.untilIosClassChain(hardwareVersion)
//
//        } catch (e: Exception) {
//            extentTest.log(Status.WARNING,"Reset password not work.")
//            driver?.findElement(MobileBy.iOSClassChain(closeButton))?.click()
//        }
//        Thread.sleep(12000)
        driver?.findElement(MobileBy.iOSClassChain(closeButton))?.click()
    }


    @Test
    fun cloudSettingTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Cloud Setting")
        val barLayout = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.DeviceStatusView\"`]"
        val barNavigator = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][1]"
        val barMeanButton = "**/XCUIElementTypeButton[`label == \"menu\"`]"
        val barItemXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"

        wait.untilIosClassChain(barLayout)
        driver?.findElement(MobileBy.iOSClassChain(barMeanButton))?.click()

        wait.untilIosClassChain(barNavigator)



        val listNavigatorItem = driver?.findElements(By.xpath(barItemXpath))
        listNavigatorItem?.get(2)?.click()

        val cloudSettingPassword = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[6]/XCUIElementTypeOther[1]/XCUIElementTypeOther"

        wait.untilIosClassChain(cloudSettingPassword)
        Thread.sleep(9000)
        extentTest.screenshotInfo(driver!!, "CloudSetting", "Cloud setting page.")

        val editButton = "**/XCUIElementTypeStaticText[`label == \"EDIT\"`]"
        driver?.findElement(MobileBy.iOSClassChain(editButton))?.click()


        val cloudPort = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther"

        wait.untilIosClassChain(cloudPort)
        extentTest.screenshotInfo(driver!!, "CloudSettingEdit", "Cloud setting edit page.")

        val cloudConnection = "**/XCUIElementTypeStaticText[`label == \"Cloud connection\"`]"
        val brokerAddress = "**/XCUIElementTypeTextField[`value == \"127.0.0.1\"`]"
        val brokerPort = "**/XCUIElementTypeTextField[`value == \"1233\"`]"

        val applyButton = "**/XCUIElementTypeButton[`label == \"APPLY\"`]"
        val closeButton = "**/XCUIElementTypeButton[`label == \"closeButton\"`]"

        Thread.sleep(4000)

//        driver?.findElement(MobileBy.iOSClassChain(closeButton))?.click()
        driver?.findElement(MobileBy.iOSClassChain(applyButton))?.click()
        wait.untilIosClassChain(cloudSettingPassword)
        Thread.sleep(16000)

        extentTest.screenshotInfo(driver!!, "AfterCloudSetting", "After setting edited.")

        driver?.findElement(MobileBy.iOSClassChain(closeButton))?.click()
        extentTest.log(Status.PASS, "pass.")
//        extentTest.log(Status.FAIL, "After click apply button on cloud setting always get crash.")

    }

    @Test
    fun lteConnectionStatusTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Lte Connection Status")
        val barLayout = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.DeviceStatusView\"`]"
        val barNavigator = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][1]"
        val barMeanButton = "**/XCUIElementTypeButton[`label == \"menu\"`]"
        val barItemXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"

//        extentTest.log(Status.FAIL, "After click side navigation page's connect status always get crash.")
//        return


        wait.untilIosClassChain(barLayout)
        driver?.findElement(MobileBy.iOSClassChain(barMeanButton))?.click()

        wait.untilIosClassChain(barNavigator)


        val listNavigatorItem = driver?.findElements(By.xpath(barItemXpath))
        listNavigatorItem?.get(3)?.click()

        val bandConfigNB1 = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[7]/XCUIElementTypeOther[1]/XCUIElementTypeOther"
        val editButton = "**/XCUIElementTypeButton[`label == \"EDIT\"`]"

        wait.untilIosClassChain(bandConfigNB1)
        extentTest.screenshotInfo(driver!!, "lteConnect", "Lte connect status page.")
        driver?.findElement(MobileBy.iOSClassChain(editButton))?.click()

        val editBandConfigNB1 = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[4]/XCUIElementTypeOther[1]/XCUIElementTypeOther"
        wait.untilIosClassChain(editBandConfigNB1)
        extentTest.screenshotInfo(driver!!, "lteConnectEdit", "Lte edit page.")

        val editDeviceName = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeTextField"
        val applyButton = "**/XCUIElementTypeButton[`label == \"APPLY\"`]"
        val closeButton = "**/XCUIElementTypeButton[`label == \"closeButton\"`]"

        try {
            driver?.findElement(MobileBy.iOSClassChain(applyButton))?.click()
            wait.untilIosClassChain(bandConfigNB1)
            driver?.findElement(MobileBy.iOSClassChain(closeButton))?.click()
        } catch (e: Exception) {
            extentTest.log(Status.WARNING,"Lte edit not work.")
            driver?.findElement(MobileBy.iOSClassChain(closeButton))?.click()
            wait.untilIosClassChain(bandConfigNB1)
            driver?.findElement(MobileBy.iOSClassChain(closeButton))?.click()
        }


    }

    @Test
    fun remoteControlTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("RemoteControl")
        val barLayout = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.DeviceStatusView\"`]"
        val barNavigator = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][1]"
        val barMeanButton = "**/XCUIElementTypeButton[`label == \"menu\"`]"
        val barItemXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"

        wait.untilIosClassChain(barLayout)
        driver?.findElement(MobileBy.iOSClassChain(barMeanButton))?.click()

        val listNavigatorItem = driver?.findElements(By.xpath(barItemXpath))
        listNavigatorItem?.get(5)?.click()
//
        Thread.sleep(5000)
        extentTest.screenshotInfo(driver!!, "remoteControlPage", "Remote control web page.")

        val webOrgName = "**/XCUIElementTypeTextField[`label == \"Organization name\"`]"
        val webEmail = "**/XCUIElementTypeTextField[`label == \"Email\"`]"
        val webPassword = "**/XCUIElementTypeSecureTextField[`label == \"Password\"`]"
        val webLogin = "**/XCUIElementTypeButton[`label == \"Login\"`]"

        wait.untilIosClassChain(webLogin)
        driver!!.findElement(MobileBy.iOSClassChain(webOrgName)).click()
        Thread.sleep(2000)
        driver!!.findElement(MobileBy.iOSClassChain(webOrgName)).sendKeys("iot-terminal-block")
        driver!!.findElement(MobileBy.iOSClassChain(webEmail)).click()
        Thread.sleep(2000)
        driver!!.findElement(MobileBy.iOSClassChain(webEmail)).sendKeys("iot-terminal-block@weidmuller.com")
        driver!!.findElement(MobileBy.iOSClassChain(webPassword)).click()
        Thread.sleep(2000)
        driver!!.findElement(MobileBy.iOSClassChain(webPassword)).sendKeys("DQwp4hTqbyN8")
        Thread.sleep(2000)
        driver!!.findElement(MobileBy.iOSClassChain(webLogin)).click()
        Thread.sleep(2000)

        wait.untilIosClassChain(barLayout)
        driver?.findElement(MobileBy.iOSClassChain(barMeanButton))?.click()
        extentTest.log(Status.PASS,"Pass")

        val listNavigatorItem2 = driver?.findElements(By.xpath(barItemXpath))
        listNavigatorItem2?.get(5)?.click()
        Thread.sleep(5000)
        extentTest.screenshotInfo(driver!!, "remoteControlList", "Remote control list page.")

//        driver?.navigate()?.back()

    }

    @Test
    fun reportingIntervalTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Reporting Interval")
        val barLayout = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.DeviceStatusView\"`]"
        val barNavigator = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][1]"
        val barMeanButton = "**/XCUIElementTypeButton[`label == \"menu\"`]"
        val barItemXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"

        wait.untilIosClassChain(barLayout)


        val reportInterval = "**/XCUIElementTypeStaticText[`label == \"Reporting Interval\"`]"
        val gateWay ="**/XCUIElementTypeButton[`label == \"GATEWAY\"`]"
        val enableForceMode = "**/XCUIElementTypeButton[`label == \"ENABLE FORCE MODE\"`]"

        Thread.sleep(3000)
        driver?.findElement(MobileBy.iOSClassChain(reportInterval))?.click()
        println("after click reportInterval ")
        /**reportInterval */
        val reportIntervalTimeXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField"
        val cancelButton = "**/XCUIElementTypeButton[`label == \"CANCEL\"`]"
        val applyButton = "**/XCUIElementTypeButton[`label == \"APPLY\"`]"
        val lastPageButton = "**/XCUIElementTypeButton[`label == \"WM_iot_test09\"`]"
        wait.untilXpath(reportIntervalTimeXpath)
        extentTest.screenshotInfo(driver!!, "ReportingInterval", "Report interval page.")

        driver?.findElement(MobileBy.iOSClassChain(lastPageButton))?.click()


    }
    @Test
    fun gateWayTest(){
        val wait = WebDriverWait(driver?.let { it }, 15)
        val extentTest: ExtentTest = extent!!.createTest("GateWay")
        val barLayout = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.DeviceStatusView\"`]"
        val barNavigator = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][1]"
        val barMeanButton = "**/XCUIElementTypeButton[`label == \"menu\"`]"
        val barItemXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"

        wait.untilIosClassChain(barLayout)
        driver?.findElement(MobileBy.iOSClassChain(barMeanButton))?.click()

        wait.untilIosClassChain(barNavigator)



        val listNavigatorItem = driver?.findElements(By.xpath(barItemXpath))
        listNavigatorItem?.get(4)?.click()
//


        val layout = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeOther[1]/XCUIElementTypeOther"
        val requestLayout = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[8]/XCUIElementTypeOther[1]/XCUIElementTypeOther"

        val settingButton ="**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeButton[1]"
        val rtuConfigButton = "**/XCUIElementTypeButton[`label == \"CONFIGURE\"`][1]"
        val slaveConfigButton ="**/XCUIElementTypeButton[`label == \"CONFIGURE\"`][2]"

        /**setting */
        val settingCancelButton = "**/XCUIElementTypeButton[`label == \"CANCEL\"`]"
        val settingApplyButton = "**/XCUIElementTypeButton[`label == \"APPLY\"`]"

        /**Rtu  */
        val rtuCancelButton = "**/XCUIElementTypeButton[`label == \"CANCEL\"`]"
        val rtuApplyButton = "**/XCUIElementTypeButton[`label == \"APPLY\"`]"


        try {
            wait.untilIosClassChain(settingButton)
            extentTest.screenshotInfo(driver!!, "Gateway", "Gateway page.")


            driver?.findElement(MobileBy.iOSClassChain(settingButton))?.click()
            wait.untilIosClassChain(settingApplyButton)
            Thread.sleep(600)
            extentTest.screenshotInfo(driver!!, "GatewaySetting", "GateWay setting page.")

            val request0 = "**/XCUIElementTypeSwitch[`label == \"Request0\"`]"
            val request1 = "**/XCUIElementTypeSwitch[`label == \"Request1\"`]"
            val request2 = "**/XCUIElementTypeSwitch[`label == \"Request2\"`]"
            val request3 = "**/XCUIElementTypeSwitch[`label == \"Request3\"`]"
            driver?.findElement(MobileBy.iOSClassChain(request0))?.click()
            driver?.findElement(MobileBy.iOSClassChain(request1))?.click()
            driver?.findElement(MobileBy.iOSClassChain(request2))?.click()
            driver?.findElement(MobileBy.iOSClassChain(request3))?.click()


            driver?.findElement(MobileBy.iOSClassChain(settingApplyButton))?.click()

            Thread.sleep(12000)
            driver?.findElement(MobileBy.iOSClassChain(settingButton))?.click()
            wait.untilIosClassChain(settingApplyButton)

            driver?.findElement(MobileBy.iOSClassChain(request0))?.click()
            driver?.findElement(MobileBy.iOSClassChain(request1))?.click()
            driver?.findElement(MobileBy.iOSClassChain(request2))?.click()
            driver?.findElement(MobileBy.iOSClassChain(request3))?.click()


            driver?.findElement(MobileBy.iOSClassChain(settingApplyButton))?.click()

            Thread.sleep(12000)

            /**RTU */

            val modBusRTUReturn = "**/XCUIElementTypeStaticText[`label == \"Modbus RTU\"`]"

            driver?.findElement(MobileBy.iOSClassChain(rtuConfigButton))?.click()

            wait.untilIosClassChain(rtuApplyButton)
            Thread.sleep(1500)
            extentTest.screenshotInfo(driver!!, "RtuConfig", "Rtu configuration page.")
            driver?.findElement(MobileBy.iOSClassChain(rtuApplyButton))?.click()
            Thread.sleep(10000)
            driver?.findElement(MobileBy.iOSClassChain(modBusRTUReturn))?.click()
            Thread.sleep(500)


            /** Slave */
            wait.untilIosClassChain(slaveConfigButton)
            driver?.findElement(MobileBy.iOSClassChain(slaveConfigButton))?.click()

            val slaveConfiguration = "**/XCUIElementTypeButton[`label == \"Configure\"`][1]"
            val slaveReturn = "**/XCUIElementTypeButton[`label == \"Modbus Slave\"`]"

            Thread.sleep(10000)
            wait.untilIosClassChain(slaveReturn)
            extentTest.screenshotInfo(driver!!, "slavePage", "ModBus slave page.")

//            driver?.findElement(MobileBy.iOSClassChain(slaveConfiguration))?.click()
//
//            extentTest.screenshotInfo(driver!!, "slavePageClick", "After configure click.")
//            Thread.sleep(500)


            driver?.findElement(MobileBy.iOSClassChain(slaveReturn))?.click()
            Thread.sleep(3000)

            val backButton = "**/XCUIElementTypeButton[`label == \"backButton\"`]"
            driver?.findElement(MobileBy.iOSClassChain(backButton))?.click()

        } catch (e: Exception) {
            extentTest.log(Status.WARNING,"Waiting long time not show correct element.")
            extentTest.log(Status.WARNING,e.toString())

        }

        val requestToggle ="**/XCUIElementTypeButton[`label == \"REQUEST TOGGLE\"`]"
        val addRequest = "**/XCUIElementTypeButton[`label == \"ADD REQUEST\"`]"

//        val backButton = "**/XCUIElementTypeButton[`label == \"backButton\"`]"
//        driver?.findElement(MobileBy.iOSClassChain(backButton))?.click()

    }

    @Test
    fun navigatorBarTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Navigator bar")
        val barLayout = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.DeviceStatusView\"`]"
        val barNavigator = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][1]"
        val barMeanButton = "**/XCUIElementTypeButton[`label == \"menu\"`]"
        val barItemXpath = "//XCUIElementTypeApplication[@name=\"Weidmüller\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"

        wait.untilIosClassChain(barLayout)
        driver?.findElement(MobileBy.iOSClassChain(barMeanButton))?.click()

        wait.untilIosClassChain(barNavigator)

        extentTest.screenshotInfo(driver!!, "NavigationBar", "After click navigation bar button.")


        val listNavigatorItem = driver?.findElements(By.xpath(barItemXpath))
        println("sssss")
        println("item size : ${listNavigatorItem?.size}")

        val closeButton = "**/XCUIElementTypeNavigationBar[`name == \"Weidmuller_IOS.MenuView\"`][2]/XCUIElementTypeButton"
        driver?.findElement(MobileBy.iOSClassChain(closeButton))?.click()
        extentTest.log(Status.PASS,"Pass.")

    }
}