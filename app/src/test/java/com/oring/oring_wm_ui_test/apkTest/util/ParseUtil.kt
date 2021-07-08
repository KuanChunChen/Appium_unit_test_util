package com.oring.oring_wm_ui_test.apkTest.util

import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.markuputils.MarkupHelper
import com.google.gson.Gson
import com.oring.oring_wm_ui_test.apkTest.constants.LogName
import com.oring.oring_wm_ui_test.apkTest.model.LogCatchModel
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.regex.Pattern

object ParseUtil {


    private fun parseLogName(scenario: (logCatchModel: LogCatchModel) -> Unit) {

        val listLog =
                readLogCatMessage(System.getProperty("user.dir") + "/auto_test/logCatcher/wn_log.txt")
        listLog.forEach {
            scenario(it)
        }
    }

    fun parseLoginTestLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        parseLogName {
            when (it.logName) {
                LogName.FastBle,
                LogName.BluetoothGatt,
                LogName.CreateTest -> {
                    node.log(Status.INFO, it.totalMessage)
                }
            }
        }

    }

    fun parseDashboardTestLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {

                LogName.Dashboard -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))

                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }

    fun parseDashboardIOTestLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {
                LogName.dashboardIO -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))
                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }

    fun parseForceModeLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {

                LogName.ForcingPage -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))
                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }


    fun parseDOPageLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {

                LogName.DoPage -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))
                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }

    fun parseDIPageLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {

                LogName.DiPage -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))
                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }


    fun parseAIPageLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {

                LogName.AiPage -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))
                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }

    fun parseRTDPageLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {

                LogName.RTDPage -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))
                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }

    fun parseNetworkStatusLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {

                LogName.NetworkStatus -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))
                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }


    fun parseCloudSettingTestLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            when (it.logName) {

                LogName.CloudSetting -> {
                    targetList.add(replaceLogTag(it.totalMessage!!))
                }
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }

    fun parseDeviceInfoTestLog(targetName: String, extentTest: ExtentTest) {

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            if (it.logName != null && it.logName!!.contains(LogName.DeviceInfo)) {
                targetList.add(replaceLogTag(it.totalMessage!!))
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))


    }

    private fun replaceLogTag(input: String): String {

        return input
            .replace("<", "&lt;")
            .replace(">", "&gt;")

    }



    fun parseGateWayTestLog(targetName: String, extentTest: ExtentTest){

        val node = extentTest.createNode(targetName)
        val targetList = mutableListOf<String>()

        parseLogName {
            if (it.logName != null && it.logName!!.contains(LogName.Gateway)) {
                targetList.add(replaceLogTag(it.totalMessage!!))
            }
        }
        node.info(MarkupHelper.createOrderedList(targetList))

    }





    fun parseIOSAllLogName(listLog: MutableList<LogCatchModel>, extentTest: ExtentTest) {
        listLog.forEach {
            println(Gson().toJson(it))
        }
    }

    fun parseAllLogName(listLog: MutableList<LogCatchModel>, extentTest: ExtentTest) {

        val DSQN: ExtentTest = extentTest.createNode(LogName.DSQN)
        val ActivityManager: ExtentTest = extentTest.createNode(LogName.ActivityManager)
        val AutomaticBrightnessControllerEx: ExtentTest = extentTest.createNode(LogName.AutomaticBrightnessControllerEx)
        val Zygote: ExtentTest = extentTest.createNode(LogName.Zygote)
        val Com: ExtentTest = extentTest.createNode(LogName.Com)
        val Typeface: ExtentTest = extentTest.createNode(LogName.Typeface)
        val MultiDex: ExtentTest = extentTest.createNode(LogName.MultiDex)
        val FirebaseInitProvider: ExtentTest = extentTest.createNode(LogName.FirebaseInitProvider)
        val PhoneWindow: ExtentTest = extentTest.createNode(LogName.PhoneWindow)
        val ShowFilePath: ExtentTest = extentTest.createNode(LogName.ShowFilePath)
        val SQLiteOpenHelper: ExtentTest = extentTest.createNode(LogName.SQLiteOpenHelper)
        val TouchFilterInputStageHelper: ExtentTest = extentTest.createNode(LogName.TouchFilterInputStageHelper)
        val BezellessGripSuppressionFilter: ExtentTest = extentTest.createNode(LogName.BezellessGripSuppressionFilter)
        val BufferQueueConsumer: ExtentTest = extentTest.createNode(LogName.BufferQueueConsumer)
        val SurfaceFlinger: ExtentTest = extentTest.createNode(LogName.SurfaceFlinger)
        val GPUD: ExtentTest = extentTest.createNode(LogName.GPUD)
        val Surface: ExtentTest = extentTest.createNode(LogName.Surface)
        val BufferQueueProducer: ExtentTest = extentTest.createNode(LogName.BufferQueueProducer)
        val Gralloc3: ExtentTest = extentTest.createNode(LogName.Gralloc3)
        val ion: ExtentTest = extentTest.createNode(LogName.ion)
        val MiniDump: ExtentTest = extentTest.createNode(LogName.MiniDump)
        val Timeline: ExtentTest = extentTest.createNode(LogName.Timeline)
        val ViewRootImpl: ExtentTest = extentTest.createNode(LogName.ViewRootImpl)
        val ActivityTaskManager: ExtentTest = extentTest.createNode(LogName.ActivityTaskManager)
        val ActivityThread: ExtentTest = extentTest.createNode(LogName.ActivityThread)
        val BluetoothAdapter: ExtentTest = extentTest.createNode(LogName.BluetoothAdapter)
        val BluetoothLeScanner: ExtentTest = extentTest.createNode(LogName.BluetoothLeScanner)
        val FastBle: ExtentTest = extentTest.createNode(LogName.FastBle)
        val BufferQueue: ExtentTest = extentTest.createNode(LogName.BufferQueue)
        val BluetoothGatt: ExtentTest = extentTest.createNode(LogName.BluetoothGatt)
        val CreateTest: ExtentTest = extentTest.createNode(LogName.CreateTest)
        val HEX: ExtentTest = extentTest.createNode(LogName.HEX)
        val DeviceName: ExtentTest = extentTest.createNode(LogName.DeviceName)
        val Password: ExtentTest = extentTest.createNode(LogName.Password)
        val CloudConfig: ExtentTest = extentTest.createNode(LogName.CloudConfig)
        val fc_Status: ExtentTest = extentTest.createNode(LogName.fc_Status)
        val CheckPW: ExtentTest = extentTest.createNode(LogName.CheckPW)
        val blePassword: ExtentTest = extentTest.createNode(LogName.blePassword)
        val SP_SW: ExtentTest = extentTest.createNode(LogName.SP_SW)
        val EEWW: ExtentTest = extentTest.createNode(LogName.EEWW)
        val Choreographer: ExtentTest = extentTest.createNode(LogName.Choreographer)
        val sw_ini: ExtentTest = extentTest.createNode(LogName.sw_ini)
        val UiThreadStatement: ExtentTest = extentTest.createNode(LogName.UiThreadStatement)
        val AI_value: ExtentTest = extentTest.createNode(LogName.AI_value)
        val AI_raw: ExtentTest = extentTest.createNode(LogName.AI_raw)
        val DashboardTest: ExtentTest = extentTest.createNode(LogName.Dashboard)
        val DeviceInfo: ExtentTest = extentTest.createNode(LogName.DeviceInfo)
        val RTD_mode: ExtentTest = extentTest.createNode(LogName.RTD_mode)
        val finish: ExtentTest = extentTest.createNode(LogName.finish)
        val TouchFlickNoti: ExtentTest = extentTest.createNode(LogName.TouchFlickNoti)
        val DeviceInfoTest: ExtentTest = extentTest.createNode(LogName.DeviceInfoTest)
        val NB_RSSI: ExtentTest = extentTest.createNode(LogName.NB_RSSI)
        val NB_RSRQ: ExtentTest = extentTest.createNode(LogName.NB_RSRQ)
        val NB_RSRP: ExtentTest = extentTest.createNode(LogName.NB_RSRP)
        val NB_SINR: ExtentTest = extentTest.createNode(LogName.NB_SINR)
        val KeepALive: ExtentTest = extentTest.createNode(LogName.KeepALive)
        val TTTIII: ExtentTest = extentTest.createNode(LogName.TTTIII)
        val chatty: ExtentTest = extentTest.createNode(LogName.chatty)
        val DataBits: ExtentTest = extentTest.createNode(LogName.DataBits)
        val GatewayTest: ExtentTest = extentTest.createNode(LogName.Gateway)
        val Listenable: ExtentTest = extentTest.createNode(LogName.Listenable)
        val Req_Enable: ExtentTest = extentTest.createNode(LogName.Req_Enable)
        val BitShift: ExtentTest = extentTest.createNode(LogName.BitShift)
        val Bau: ExtentTest = extentTest.createNode(LogName.Bau)
        val SERIAL_DATA_BITS: ExtentTest = extentTest.createNode(LogName.SERIAL_DATA_BITS)
        val Cloud: ExtentTest = extentTest.createNode(LogName.Cloud)
        val CS_Info: ExtentTest = extentTest.createNode(LogName.CS_Info)
        val CloudSetting: ExtentTest = extentTest.createNode(LogName.CloudSetting)
        val CS_WR_Info: ExtentTest = extentTest.createNode(LogName.CS_WR_Info)
        val Address_Compare: ExtentTest = extentTest.createNode(LogName.Address_Compare)
        val refresh_step_1: ExtentTest = extentTest.createNode(LogName.refresh_step_1)
        val swmodeHH: ExtentTest = extentTest.createNode(LogName.swmodeHH)
        val swmode: ExtentTest = extentTest.createNode(LogName.swmode)
        val w: ExtentTest = extentTest.createNode(LogName.w)
        val wd: ExtentTest = extentTest.createNode(LogName.wd)
        val write: ExtentTest = extentTest.createNode(LogName.write)
        val networkStatusTest: ExtentTest = extentTest.createNode(LogName.NetworkStatusTest)

        listLog.forEach {
            println(Gson().toJson(it))


            if (it.logName!!.contains("CloudSetting")) {
                CloudSetting.log(Status.INFO, it.logName + ":" + it.message)
            }

            if (it.logName!!.contains(LogName.NetworkStatusTest)) {
                networkStatusTest.log(Status.INFO, it.logName + ":" + it.message)
            }

            when (it.logName) {

                LogName.DSQN->{

                    DSQN.log(Status.INFO,it.message)
                }
                LogName.ActivityManager->{
                    ActivityManager.log(Status.INFO,it.message)
                }
                LogName.AutomaticBrightnessControllerEx->{
                    AutomaticBrightnessControllerEx.log(Status.INFO,it.message)
                }
                LogName.Zygote->{
                    Zygote.log(Status.INFO,it.message)
                }
                LogName.Com->{
                    Com.log(Status.INFO,it.message)
                }
                LogName.Typeface->{
                    Typeface.log(Status.INFO,it.message)
                }
                LogName.MultiDex->{
                    MultiDex.log(Status.INFO,it.message)
                }
                LogName.FirebaseInitProvider->{
                    FirebaseInitProvider.log(Status.INFO,it.message)
                }
                LogName.PhoneWindow->{
                    PhoneWindow.log(Status.INFO,it.message)
                }
                LogName.ShowFilePath->{
                    ShowFilePath.log(Status.INFO,it.message)
                }
                LogName.SQLiteOpenHelper->{
                    SQLiteOpenHelper.log(Status.INFO,it.message)
                }
                LogName.TouchFilterInputStageHelper->{
                    TouchFilterInputStageHelper.log(Status.INFO,it.message)
                }
                LogName.BezellessGripSuppressionFilter->{
                    BezellessGripSuppressionFilter.log(Status.INFO,it.message)
                }
                LogName.BufferQueueConsumer->{
                    BufferQueueConsumer.log(Status.INFO,it.message)
                }
                LogName.SurfaceFlinger->{
                    SurfaceFlinger.log(Status.INFO,it.message)
                }
                LogName.GPUD->{
                    GPUD.log(Status.INFO,it.message)
                }
                LogName.Surface->{
                    Surface.log(Status.INFO,it.message)
                }
                LogName.BufferQueueProducer->{
                    BufferQueueProducer.log(Status.INFO,it.message)
                }
                LogName.Gralloc3->{
                    Gralloc3.log(Status.INFO,it.message)
                }
                LogName.ion->{
                    ion.log(Status.INFO,it.message)
                }
                LogName.MiniDump->{
                    MiniDump.log(Status.INFO,it.message)
                }
                LogName.Timeline->{
                    Timeline.log(Status.INFO,it.message)
                }
                LogName.ViewRootImpl->{
                    ViewRootImpl.log(Status.INFO,it.message)
                }
                LogName.ActivityTaskManager->{
                    ActivityTaskManager.log(Status.INFO,it.message)
                }
                LogName.ActivityThread->{
                    ActivityThread.log(Status.INFO,it.message)
                }
                LogName.BluetoothAdapter->{
                    BluetoothAdapter.log(Status.INFO,it.message)
                }
                LogName.BluetoothLeScanner->{
                    BluetoothLeScanner.log(Status.INFO,it.message)
                }
                LogName.FastBle->{
                    FastBle.log(Status.INFO,it.message)
                }
                LogName.BufferQueue->{
                    BufferQueue.log(Status.INFO,it.message)
                }
                LogName.BluetoothGatt->{
                    BluetoothGatt.log(Status.INFO,it.message)
                }
                LogName.CreateTest->{
                    CreateTest.log(Status.INFO,it.message)
                }
                LogName.HEX->{
                    HEX.log(Status.INFO,it.message)
                }
                LogName.DeviceName->{
                    DeviceName.log(Status.INFO,it.message)
                }
                LogName.Password->{
                    Password.log(Status.INFO,it.message)
                }
                LogName.CloudConfig->{
                    CloudConfig.log(Status.INFO,it.message)
                }
                LogName.fc_Status->{
                    fc_Status.log(Status.INFO,it.message)
                }
                LogName.CheckPW->{
                    CheckPW.log(Status.INFO,it.message)
                }
                LogName.blePassword->{
                    blePassword.log(Status.INFO,it.message)
                }
                LogName.SP_SW->{
                    SP_SW.log(Status.INFO,it.message)
                }
                LogName.EEWW->{
                    EEWW.log(Status.INFO,it.message)
                }
                LogName.Choreographer->{
                    Choreographer.log(Status.INFO,it.message)
                }
                LogName.sw_ini->{
                    sw_ini.log(Status.INFO,it.message)
                }
                LogName.UiThreadStatement->{
                    UiThreadStatement.log(Status.INFO,it.message)
                }
                LogName.AI_value->{
                    AI_value.log(Status.INFO,it.message)
                }
                LogName.AI_raw->{
                    AI_raw.log(Status.INFO,it.message)
                }
                LogName.Dashboard->{
                    DashboardTest.log(Status.INFO,it.message)
                }
                LogName.DeviceInfo->{
                    DeviceInfo.log(Status.INFO,it.message)
                }

                LogName.RTD_mode->{
                    RTD_mode.log(Status.INFO,it.message)
                }
                LogName.finish->{
                    finish.log(Status.INFO,it.message)
                }
                LogName.TouchFlickNoti->{
                    TouchFlickNoti.log(Status.INFO,it.message)
                }
                LogName.DeviceInfoTest->{
                    DeviceInfoTest.log(Status.INFO,it.message)
                }
                LogName.NB_RSSI->{
                    NB_RSSI.log(Status.INFO,it.message)
                }
                LogName.NB_RSRQ->{
                    NB_RSRQ.log(Status.INFO,it.message)
                }
                LogName.NB_RSRP->{
                    NB_RSRP.log(Status.INFO,it.message)
                }
                LogName.NB_SINR->{
                    NB_SINR.log(Status.INFO,it.message)
                }
                LogName.KeepALive->{
                    KeepALive.log(Status.INFO,it.message)
                }
                LogName.TTTIII->{
                    TTTIII.log(Status.INFO,it.message)
                }
                LogName.chatty->{
                    chatty.log(Status.INFO,it.message)
                }
                LogName.DataBits->{
                    DataBits.log(Status.INFO,it.message)
                }
                LogName.Gateway->{
                    GatewayTest.log(Status.INFO,it.message)
                }
                LogName.Listenable->{
                    Listenable.log(Status.INFO,it.message)
                }
                LogName.Req_Enable->{
                    Req_Enable.log(Status.INFO,it.message)
                }
                LogName.BitShift->{
                    BitShift.log(Status.INFO,it.message)
                }
                LogName.Bau->{
                    Bau.log(Status.INFO,it.message)
                }
                LogName.SERIAL_DATA_BITS->{
                    SERIAL_DATA_BITS.log(Status.INFO,it.message)
                }
                LogName.Cloud->{
                    Cloud.log(Status.INFO,it.message)
                }
                LogName.CS_Info->{
                    CS_Info.log(Status.INFO,it.message)
                }
                LogName.CS_WR_Info->{
                    CS_WR_Info.log(Status.INFO,it.message)
                }
                LogName.Address_Compare->{
                    Address_Compare.log(Status.INFO,it.message)
                }
                LogName.refresh_step_1->{
                    refresh_step_1.log(Status.INFO,it.message)
                }
                LogName.swmodeHH->{
                    swmodeHH.log(Status.INFO,it.message)
                }
                LogName.swmode->{
                    swmode.log(Status.INFO,it.message)
                }
                LogName.w->{
                    w.log(Status.INFO,it.message)
                }
                LogName.wd->{
                    wd.log(Status.INFO,it.message)
                }
                LogName.write->{
                    write.log(Status.INFO,it.message)
                }
            }
        }

    }
    fun readLogCatMessage(directory: String): MutableList<LogCatchModel> {
        val listLog = mutableListOf<LogCatchModel>()
        File(directory).forEachLine {
            val splitDate = it.substring(0,32)
            val splitMessage = it.substring(32)

            val splitMessageList = splitMessage.split(Pattern.compile(":"),2)

            listLog.apply {
                add(LogCatchModel(splitDate, splitMessageList[0].trim(), splitMessageList[1],splitMessage))
            }

        }
        return listLog

    }


    fun readIDeviceLogMessage(directory: String): MutableList<LogCatchModel> {
        val listLog = mutableListOf<LogCatchModel>()
        File(directory).forEachLine {
//            val splitDate = it.substring(0,32)
//            val splitMessage = it.substring(32)
//
//            val splitMessageList = splitMessage.split(Pattern.compile(":"),2)
//
//            listLog.apply {
//                add(LogCatchModel(splitDate, splitMessageList[0].trim(), splitMessageList[1],splitMessage))
//            }

            println(Gson().toJson(it))

        }
        return listLog

    }


    fun execCmdShell(command: String, path: String): Process? {

        val processBuilder = ProcessBuilder(command)
        val directory = System.getProperty("user.dir") + path
        processBuilder.directory(File(directory))

        var readString: String? = null

        try {


            val process = processBuilder.start()
            val stdInput = BufferedReader(InputStreamReader(process.inputStream))
            val stdError = BufferedReader(InputStreamReader(process.errorStream))
            readString = stdInput.readLine()

            while (readString != null) {
                readString = stdInput.readLine()
                println(readString)
            }
            process.waitFor()
            return process

        } catch (e: IOException) {
            println(e.toString())
        } catch (e: InterruptedException) {
            println(e.toString())

        }

        return null
    }

}