package com.oring.oring_wm_ui_test.apkTest

import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.markuputils.CodeLanguage
import com.aventstack.extentreports.markuputils.MarkupHelper
import com.google.gson.Gson
import com.oring.oring_wm_ui_test.apkTest.constants.LogName
import com.oring.oring_wm_ui_test.apkTest.constants.Target
import com.oring.oring_wm_ui_test.apkTest.model.DrawerPageData
import com.oring.oring_wm_ui_test.apkTest.model.TerminalBlockData
import com.oring.oring_wm_ui_test.apkTest.model.TerminalBlockMember
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader


class WMReportTest: BaseExtentReportTest() {

    private val targetPackageName = Target.PackageName.ORING_WM_PACKAGE


    override var capabilities: DesiredCapabilities? = addAndroidCapability()

    private fun addAndroidCapability()= ProjectCapabilities.androidBaseCapabilities().apply {
        addApkPath(Target.ApkName.ORING_WM_APK)
        addPlatformVersion(Target.AndroidVersion.Android10)
        addDeviceName(Target.DeviceName.K52)
        addPlatformName(Target.Platform.Android)
        addPackage(targetPackageName)
//        addAppActivity("com.oring.LoginActivity")
    }
    private fun addIosCapability()=ProjectCapabilities.iosBaseCapabilities().apply {
        addPlatformVersion(Target.IosVersion.Ios14_5)
        addPlatformName(Target.Platform.IOS)
        addDeviceName(Target.DeviceName.Iphone12)
        addUdid(Target.IosUDID.Iphone12)


    }

    @Test
    fun oIosRingWMDemo() {
//        val wait = WebDriverWait(driver?.let { it }, 10)
//        val extentTest: ExtentTest = extent!!.createTest("Start")
//        Runtime.getRuntime().exec("/usr/bin/open -a Terminal /path/to/the/executable");
//        val command = "/usr/bin/xterm"
//        val rt = Runtime.getRuntime()
//        val pr = rt.exec(command)
//        Runtime.getRuntime().exec("/bin/bash -c iTerm2");

    }

    private val confirmButton: String = "${targetPackageName}:id/txt_ble"
    private val layoutBaby: String = "${targetPackageName}:id/layout_baby"

    private val startButton: String = "${targetPackageName}:id/intoMenu"
    private val signUpButton: String = "${targetPackageName}:id/btn_createAccount"
    private val forgetButton: String = "${targetPackageName}:id/btn_forget"


    @Test
    fun captureLogcat() {
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("LogCat Message.")
        val listLog = ParseUtil.readLogCatMessage(System.getProperty("user.dir") + "/auto_test/logCatcher/wn_log.txt")





        ParseUtil.parseAllLogName(listLog, extentTest)

//        scenario.createNode(Given::class.java, "Jeff has bought a microwave for $100").pass("pass")

    }



    @Test
    fun oRingWMDemo() {


        val thread = Thread {
            ParseUtil.execCmdShell("./android_wm_.sh", "/auto_test/logCatcher/")
        }
        thread.start()


        startTest()
        /*** BluetoothAdapter* */
        deviceListTest()
        /*** FastBle*  BluetoothGatt CreateTest*/
        loginTest()

        /*** SP_SW  EEWW    DashboardTest AI_value AI_raw RTD_mode finish*/
        terminalBlockPage()
        navigationBarPage()
        deviceInfoPage()
        /**GatewayTest */
        gateWayPage()
        /*** Cloud CS_Info CloudSetting HEX*/
        cloudSettingPage()
        forceModePage()

        /**
         * MiniDump
         * */

        captureLogcat()
    }

    @Test
    fun startTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Start")
        extentTest.screenshotInfo(driver!!, "startPage", "Start page.")
        extentTest.log(Status.PASS, "pass")

        val startButton = "${targetPackageName}:id/txt_ble"
        driver?.findElementById(startButton)?.click()
    }

    @Test
    fun deviceListTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Device")
        val deviceList = "${targetPackageName}:id/list_table"
        val deviceListLayout = "android:id/content"
        val itemTextID = "${targetPackageName}:id/txt_id"
        val itemTextMAC = "${targetPackageName}:id/txt_mac"
        val itemTextRSSI = "${targetPackageName}:id/txt_rssi"

        wait.untilViewLoad(deviceListLayout)
        extentTest.screenshotInfo(driver!!, "DeviceList", "device list page")

        val list = driver?.findElements(By.id(deviceList))

        if (list != null && list.size >0) {
//            list[0].apply {
//                extentTest.info("Current ID ：${findElement(By.id(itemTextID)).text}")
//                extentTest.info("Current MAC ：${findElement(By.id(itemTextMAC)).text}")
//                extentTest.info("Current RSSI ：${findElement(By.id(itemTextRSSI)).text}")
//                click()
//            }
            wait.waitForVisibleByText(driver!!, By.id(itemTextID), "WM_willy_test", 10)
//
//
//
//            extentTest.info(MarkupHelper.createCodeBlock(Gson().toJson(list), CodeLanguage.JSON));
//
//
            list.forEach {
//                wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(viewID)))
                if (it.findElement(By.id(itemTextID)).text == "WM_willy_test") {
                    val messageInfo =
                            "Current ID ：${it.findElement(By.id(itemTextID)).text},\n" +
                            "Current MAC ：${it.findElement(By.id(itemTextMAC)).text},\n" +
                            "Current RSSI ：${it.findElement(By.id(itemTextRSSI)).text}\n"


                    extentTest.info(MarkupHelper.createCodeBlock(messageInfo, CodeLanguage.JSON));


                    extentTest.info("Current ID ：${it.findElement(By.id(itemTextID)).text}")
                    extentTest.info("Current MAC ：${it.findElement(By.id(itemTextMAC)).text}")
                    extentTest.info("Current RSSI ：${it.findElement(By.id(itemTextRSSI)).text}")
                    extentTest.pass("pass")

                    it.click()
                    return@forEach
                }
            }
        } else {
            extentTest.fail("Can not find ble device !")
        }


    }

    @Test
    fun loginTest(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Login")

        val deviceInfoText = "${targetPackageName}:id/txt_devInfo"
        val inputPasswordEditText = "${targetPackageName}:id/edt_ble_password_check"
        val loginButton = "${targetPackageName}:id/btn_Check_apply"

        wait.untilViewLoad(inputPasswordEditText)
        extentTest.screenshotInfo(driver!!, "LoginPage", "Login device page.")

        extentTest.info("Current Device Info ：${driver?.findElement(By.id(deviceInfoText))?.text}")

        driver?.findElementById(inputPasswordEditText)?.sendKeys("123456")
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver?.findElementById(inputPasswordEditText), "text"))
        extentTest.screenshotInfo(driver!!, "AfterInput", "Auto input password.")
        ParseUtil.parseLoginTestLog("Login test log :", extentTest)
        extentTest.info("pass")
        driver?.findElementById(loginButton)?.click()

    }

    @Test
    fun terminalBlockPage(){
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Terminal Block")

        val deviceInfoText = "${targetPackageName}:id/txt_devInfo"
        val inputPasswordEditText = "${targetPackageName}:id/edt_ble_password_check"
        val loginButton = "${targetPackageName}:id/btn_Check_apply"
        val terminalBlockLayout = "${targetPackageName}:id/action_bar_root"
        val relayText = "${targetPackageName}:id/txt_relay"

        val diTableList = "${targetPackageName}:id/di_table"

        val itemName = "${targetPackageName}:id/txt_name"
        val itemStatus = "${targetPackageName}:id/txt_status"
        val itemEvent = "${targetPackageName}:id/txt_event"
        val itemSwitch = "android:id/text1"

        val textDi = "${targetPackageName}:id/txt_di"
        val diListXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.ListView[1]/android.widget.LinearLayout"

        val textDo = "${targetPackageName}:id/txt_do"
        val doListXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.ListView[2]/android.widget.LinearLayout"


        val textRelay = "${targetPackageName}:id/txt_relay"
        val relayListXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.ListView[2]/android.widget.LinearLayout"

        val textAi = "${targetPackageName}:id/txt_ai"
        val aiListXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.ListView[3]/android.widget.LinearLayout"

        val textRtd = "${targetPackageName}:id/txt_rtd"
        val rtdListXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.ListView[4]/android.widget.LinearLayout"

        wait.untilViewLoad(terminalBlockLayout)
        extentTest.screenshotInfo(driver!!, "terminalMainPage", "Terminal Block main page.")
        val terminalBlockData = mutableListOf<TerminalBlockData>()
        terminalBlockData.insertTerminalData(textDi,diListXpath){
            val textName = it.findElement(By.id(itemName)).text
            val textEvent = it.findElement(By.id(itemEvent)).text
            TerminalBlockMember(name = textName, event = textEvent)
        }

        terminalBlockData.insertTerminalData(textDo,doListXpath){
            val textName = it.findElement(By.id(itemName)).text
            TerminalBlockMember(name = textName)
        }

        scrollToId(driver!!,relayText)
        wait.untilViewLoad(relayText)
        extentTest.screenshotInfo(driver!!, "relayText", "Scroll to Relay text.")

        terminalBlockData.insertTerminalData(textRelay,relayListXpath){
            val textName = it.findElement(By.id(itemName)).text
            TerminalBlockMember(name = textName)
        }

        terminalBlockData.insertTerminalData(textAi,aiListXpath){
            val textName = it.findElement(By.id(itemName)).text
            val textStatus = it.findElement(By.id(itemStatus)).text
            TerminalBlockMember(name = textName, status = textStatus)
        }


        terminalBlockData.insertTerminalData(textRtd,rtdListXpath){
            val textName = it.findElement(By.id(itemName)).text
            val textStatus = it.findElement(By.id(itemStatus)).text
            val textSwitch = it.findElement(By.id(itemSwitch)).text
            TerminalBlockMember(name = textName, status = textStatus,swithc = textSwitch)
        }
        extentTest.info("The data on this page is ：")
        extentTest.info(MarkupHelper.createCodeBlock(Gson().toJson(terminalBlockData), CodeLanguage.JSON))
        ParseUtil.parseTerminalBlockTestLog("Terminal Block main page test log :", extentTest)

        extentTest.pass("pass")
        scrollToId(driver!!,textDi)
        wait.untilViewLoad(textDi)

    }

    @Test
    fun navigationBarPage() {
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Navigation bar")

        driver?.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")?.click()

        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout")))
        extentTest.screenshotInfo(driver!!,"navigationBar","After click navigation bar button.")

        val listDrawerPage = driver?.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat")
        mutableListOf<DrawerPageData>().apply {
            val itemText = "${targetPackageName}:id/design_menu_item_text"

            listDrawerPage?.forEach {
                this.add(DrawerPageData(it.findElement(By.id(itemText)).text))
            }
            extentTest.info("There are several sub title below ：")
            extentTest.info(MarkupHelper.createCodeBlock(Gson().toJson(this), CodeLanguage.JSON))

        }
        extentTest.pass("open / close navigation bar success.")

        listDrawerPage?.get(3)?.click()

    }


    @Test
    fun deviceInfoPage() {
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("DeviceInfo")
        val textName = "${targetPackageName}:id/txt_name"
        val textInfo = "${targetPackageName}:id/txt_info"

        val buttonResetPassword = "${targetPackageName}:id/btn_ResetPW"
        val buttonEdit= "${targetPackageName}:id/btn_EditConfig"



        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout")))

        extentTest.screenshotInfo(driver!!,"deviceInfoPage","The device info page.")

        val deviceInfoList = driver!!.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout")

        deviceInfoList.forEach {
            extentTest.info("${it.findElement(By.id(textName)).text}： ${it.findElement(By.id(textInfo)).text}")
        }

        driver!!.findElement(By.id(buttonResetPassword)).click()

        val etCurrentPassword = "${targetPackageName}:id/edt_ble_password_check"
        val etNewPassword = "${targetPackageName}:id/edt_ble_password1"
        val etVerifyPassword = "${targetPackageName}:id/edt_ble_password2"
        val buttonApply = "${targetPackageName}:id/btn_apply"

        wait.untilViewLoad(etCurrentPassword)
        extentTest.screenshotInfo(driver!!,"resetPWPage","Reset password page.")
        driver!!.findElement(By.id(etCurrentPassword)).sendKeys("123456")
        driver!!.findElement(By.id(etNewPassword)).sendKeys("123456")
        driver!!.findElement(By.id(etVerifyPassword)).sendKeys("123456")
        driver!!.findElement(By.id(buttonApply)).click()

        wait.untilViewLoad(buttonEdit)
        driver!!.findElement(By.id(buttonEdit)).click()

        val edtDevName = "${targetPackageName}:id/edt_devName"

        val buttonApplyDevName = "${targetPackageName}:id/btn_change_apply"

        wait.untilViewLoad(edtDevName)
        driver!!.findElement(By.id(edtDevName)).sendKeys("fuxkingGoodByAppium")
        driver!!.findElement(By.id(buttonApplyDevName)).click()

        wait.untilViewLoad(buttonEdit)

        driver?.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")?.click()
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout")))
        val listDrawerPage = driver?.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat")
        listDrawerPage?.get(2)?.click()


        extentTest.pass("Reset Device Name pass.")
        extentTest.pass("Reset Password pass.")

    }

    @Test
    fun gateWayPage() {
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("GateWay")
        val buttonEnable = "${targetPackageName}:id/btn_Enable"
        val loEnable = "${targetPackageName}:id/LO_Enable"

        val swRequestControl = "${targetPackageName}:id/sw_req_control"
        val toggleApply = "${targetPackageName}:id/btn_Toggle_apply"

        wait.untilViewLoad(buttonEnable)
        extentTest.screenshotInfo(driver!!,"gateWayPage","Gateway page.")

        driver!!.findElement(By.id(buttonEnable)).click()

        wait.untilViewLoad(loEnable)
        extentTest.screenshotInfo(driver!!,"slaveRequestToggle","Slave Request Toggle page.")

        val listLo = driver!!.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.LinearLayout")

        listLo.forEach {
            it.findElement(By.id(swRequestControl)).click()
        }

        extentTest.screenshotInfo(driver!!,"afterClickToggle","After click toggle button.")
        driver!!.findElement(By.id(toggleApply)).click()

        wait.untilViewLoad(buttonEnable)
        driver!!.findElement(By.id(buttonEnable)).click()
        wait.untilViewLoad(loEnable)
        listLo.forEach {
            it.findElement(By.id(swRequestControl)).click()
        }
        extentTest.screenshotInfo(driver!!,"ClickAgainToggle","After click toggle again.")
        driver!!.findElement(By.id(toggleApply)).click()


        val buttonRtuConfig = "${targetPackageName}:id/btn_RTU_CONFIG"
        val buttonReqDetailConfig = "${targetPackageName}:id/btn_ReqDetail_CONFIG"

        wait.untilViewLoad(buttonEnable)
        driver!!.findElement(By.id(buttonRtuConfig)).click()


        val spinnerBaudRate = "${targetPackageName}:id/spn_baudRate"

        val spinnerDataBits = "${targetPackageName}:id/spn_dataBits"
        val spinnerStopBits = "${targetPackageName}:id/spn_stopBits"
        val spinnerParity = "${targetPackageName}:id/spn_parity"
        val editTextTimeout = "${targetPackageName}:id/edt_timeout"
        wait.untilViewLoad(spinnerBaudRate)
        extentTest.screenshotInfo(driver!!,"ModbusRTU","Modbus RTU Page.")

//        driver!!.findElement(By.id(spinnerBaudRate)).sendKeys("4800")
//        driver!!.findElement(By.id(spinnerDataBits)).sendKeys("8")
//        driver!!.findElement(By.id(spinnerStopBits)).sendKeys("2")
//        driver!!.findElement(By.id(spinnerParity)).sendKeys("Odd")
//        driver!!.findElement(By.id(editTextTimeout)).sendKeys("2")

        val buttonCancel = "${targetPackageName}:id/btn_cancel"

        driver!!.findElement(By.id(buttonCancel)).click()
        wait.untilViewLoad(buttonEnable)
        driver!!.findElement(By.id(buttonReqDetailConfig)).click()

        val buttonShowDetail = "${targetPackageName}:id/btn_showDetail_config"
        val buttonBack = "${targetPackageName}:id/btn_ReqDetail_CONFIG"
        val textDataType = "${targetPackageName}:id/txt_dataType"

        wait.untilViewLoad(buttonShowDetail)
        extentTest.screenshotInfo(driver!!,"ModbusSlave","Modbus Slave Page.")
        driver!!.findElement(By.id(buttonShowDetail)).click()
        wait.untilViewLoad(textDataType)
        extentTest.screenshotInfo(driver!!,"ModbusSlaveDetail","Modbus Slave detail page.")
        driver!!.findElement(By.id(buttonBack)).click()


        ParseUtil.parseGateWayTestLog("Gate way test page lost :" , extentTest)
        extentTest.pass("pass.")

        driver?.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")?.click()
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout")))
        val listDrawerPage = driver?.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat")
        listDrawerPage?.get(5)?.click()

    }

    @Test
    fun cloudSettingPage() {
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Cloud Setting")
        val listTable = "${targetPackageName}:id/list_table"
        val textName = "${targetPackageName}:id/txt_name"
        val textInfo = "${targetPackageName}:id/txt_info"
        val buttonEdit = "${targetPackageName}:id/btn_EditConfig"


        wait.untilViewLoad(listTable)


        extentTest.screenshotInfo(driver!!,"cloudSettingPage","Cloud setting page.")

        val deviceInfoList = driver!!.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout")

        deviceInfoList.forEach {
            extentTest.info("${it.findElement(By.id(textName)).text}： ${it.findElement(By.id(textInfo)).text}")
        }

        val editTextBrokerAddress = "${targetPackageName}:id/edt_brokerAddress"
        val editTextMQTTPort = "${targetPackageName}:id/edit_MQTT_Port"
        val editTextClientID = "${targetPackageName}:id/edt_clientID"
        val editTextUserName = "${targetPackageName}:id/edt_ClientUserName"
        val editTextPassword = "${targetPackageName}:id/edt_ClientPassword"

        wait.untilViewLoad(listTable)
        driver!!.findElement(By.id(buttonEdit)).click()
        wait.untilViewLoad(editTextBrokerAddress)
        driver!!.findElement(By.id(editTextBrokerAddress)).clear()
        driver!!.findElement(By.id(editTextBrokerAddress)).sendKeys("127.0.0.1")
        driver!!.findElement(By.id(editTextMQTTPort)).clear()
        driver!!.findElement(By.id(editTextMQTTPort)).sendKeys("1233")
        driver!!.findElement(By.id(editTextClientID)).clear()
        driver!!.findElement(By.id(editTextClientID)).sendKeys("AppiumTest")
        driver!!.findElement(By.id(editTextUserName)).clear()
        driver!!.findElement(By.id(editTextUserName)).sendKeys("Appium")
        driver!!.findElement(By.id(editTextPassword)).clear()
        driver!!.findElement(By.id(editTextPassword)).sendKeys("123456")

        val changeApply = "${targetPackageName}:id/btn_change_apply"


        wait.untilViewLoad(changeApply)
        driver!!.findElement(By.id(changeApply)).click()

        wait.untilViewLoad(listTable)
        extentTest.screenshotInfo(driver!!,"cloudSettingPage","After Cloud setting edited.")

        extentTest.info("After cloud setting edited ：")
        deviceInfoList.forEach {
            extentTest.info("${it.findElement(By.id(textName)).text}： ${it.findElement(By.id(textInfo)).text}")
        }
        ParseUtil.parseCloudSettingTestLog("Cloud setting log :", extentTest)


        driver?.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")?.click()
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout")))
        val listDrawerPage = driver?.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat")
        listDrawerPage?.get(0)?.click()

    }

    @Test
    fun forceModePage() {
        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("Force Mode")

        val buttonForce = "${targetPackageName}:id/btn_force"
        val textForce = "${targetPackageName}:id/txt_force"
        wait.untilViewLoad(buttonForce)
        driver!!.findElement(By.id(buttonForce)).click()
        wait.untilViewLoad(textForce)
        extentTest.screenshotInfo(driver!!,"forceModePage","Force mode page.")


        val textRtd = "${targetPackageName}:id/txt_relay"
        scrollToId(driver!!,textRtd)
        wait.untilViewLoad(textRtd)
        extentTest.screenshotInfo(driver!!, "relayTextForce", "Scroll to Relay text.")
        extentTest.pass("pass")
    }
    private fun MutableList<TerminalBlockData>.insertTerminalData(subjectId: String, listXPath: String, addLambda: (mobileElement: MobileElement) -> TerminalBlockMember) {

        val subjectText = driver?.findElement(By.id(subjectId))?.text
        val listAboutSubject: List<MobileElement> = driver?.findElementsByXPath(listXPath) as List<MobileElement>
        val terminalBlockMember = mutableListOf<TerminalBlockMember>()


        listAboutSubject.forEach {
            terminalBlockMember.apply {
                add(addLambda(it))
            }
        }

        add(TerminalBlockData(subjectText, terminalBlockMember))


    }
    @Test
    fun fullTest(){
        signUpPageTest()
        loginPageTest()
        mainPageTest()
    }
    @Test
    fun mainPageTest() {
        val hostFragment = "${targetPackageName}:id/nav_host_fragment"
        val toListButton = "${targetPackageName}:id/jump_toList"
        val scanQRCodeButton = "${targetPackageName}:id/scan_QRcode"


        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("main page")
        wait.untilViewLoad(hostFragment)
        extentTest.screenshotInfo(driver!!, "mainPage", "main page")
        driver?.findElementById(toListButton)?.click()


        val wellListView = "${targetPackageName}:id/thing_list_service"
        wait.untilViewLoad(wellListView)
        extentTest.screenshotInfo(driver!!, "wellListView", "well list page")
        driver?.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")?.click()

        wait.untilViewLoad(hostFragment)
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id(scanQRCodeButton)))
        wait.untilViewLoad(scanQRCodeButton).click()

        extentTest.screenshotInfo(driver!!, "scanQRCode", "qr code page")


    }

    @Test
    fun signUpPageTest() {
        val editTextSignUpName = "${targetPackageName}:id/editText_R_name"
        val editTextSignUpEmail = "${targetPackageName}:id/editText_R_Email"
        val editTextSignUpPhone = "${targetPackageName}:id/editText_R_phone"
        val editTextSignUpPassword = "${targetPackageName}:id/editText_R_Password"
        val editTextSignUpP2ssword = "${targetPackageName}:id/editText_R_AgainPassword"
        val backButton = "${targetPackageName}:id/Back_register"
        val commitButton =  "${targetPackageName}:id/btn_commit"

        val wait = WebDriverWait(driver?.let { it }, 10)
        val extentTest: ExtentTest = extent!!.createTest("sign up")

        wait.untilViewLoad(signUpButton).click()

        extentTest.screenshotInfo(driver!!, "signUpPageBefore", "before input")
        wait.untilViewLoad(commitButton)
        driver?.findElementById(editTextSignUpName)?.sendKeys("0986888333")
        driver?.findElementById(editTextSignUpEmail)?.sendKeys("0986888333@test.com")
        driver?.findElementById(editTextSignUpPhone)?.sendKeys("0986888333")
        driver?.findElementById(editTextSignUpPassword)?.sendKeys("00000000")
        driver?.findElementById(editTextSignUpP2ssword)?.sendKeys("00000000")
        extentTest.screenshotInfo(driver!!, "signUpPageAfter", "after input")
        wait.untilViewLoad(backButton).click()


    }

    @Test
    fun forgetPasswordTest() {
//        val wait = WebDriverWait(driver?.let { it }, 10)
//        val extentTest: ExtentTest = extent!!.createTest("forget password")
//        wait.untilViewLoad(forgetButton).click()


    }
    @Test
    fun loginPageTest() {


        val extentTest: ExtentTest = extent!!.createTest("login")
        extentTest.info("start!")

        val wait = WebDriverWait(driver?.let { it }, 10)
        val editTextAccount: String = "${targetPackageName}:id/L_editText_account"
        val editTextPassword: String = "${targetPackageName}:id/L_editText_pwd"
        val loginButton = "${targetPackageName}:id/btn_login"




        wait.untilViewLoad(loginButton)
        extentTest.screenshotInfo(driver!!, "loginPage", "login page")

        driver?.findElementById(editTextAccount)?.sendKeys("0986888333")
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver?.findElementById(editTextAccount), "text"))
        extentTest.screenshotInfo(driver!!, "afterInputAccount", "input account")

        driver?.findElementById(editTextPassword)?.sendKeys("00000000")
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver?.findElementById(editTextPassword), "text"))
        extentTest.screenshotInfo(driver!!, "afterInputPassword", "input password")
        driver?.findElementById(confirmButton)?.click()

        wait.untilViewLoad(layoutBaby)
        extentTest.screenshotInfo(driver!!, "after login", "before start")
        driver?.findElementById(startButton)?.click()


    }

}