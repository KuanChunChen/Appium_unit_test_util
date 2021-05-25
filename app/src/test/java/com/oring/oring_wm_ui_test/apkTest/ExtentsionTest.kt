package com.oring.oring_wm_ui_test.apkTest

import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.apache.commons.io.FileUtils
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.util.concurrent.TimeUnit


var screenShotsDir: String = System.getProperty("user.dir")+ "//auto_test///reports//screenshots//"



private fun screenShot(driver: AndroidDriver<MobileElement>?, saveDir: String){
//    val screenshot: File = driver!!.getScreenshotAs(OutputType.FILE)
    val screenshot = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
    FileUtils.copyFile(screenshot, File(saveDir))
}

fun ExtentTest.screenshotPass(driver: AndroidDriver<MobileElement>?, fileName: String, message: String) {

    val saveDir = "$screenShotsDir$fileName.png"
    screenShot(driver, saveDir)
    addScreenCaptureFromPath(saveDir)
    pass(message, MediaEntityBuilder.createScreenCaptureFromPath(saveDir).build())
}

fun ExtentTest.screenshotInfo(driver: AndroidDriver<MobileElement>?, fileName: String, message: String) {
    val saveDir = "$screenShotsDir$fileName.png"


    screenShot(driver, saveDir)
//    addScreenCaptureFromPath(saveDir)
//    info(message).addScreenCaptureFromPath(saveDir)
    info(message, MediaEntityBuilder.createScreenCaptureFromPath(saveDir).build())
}

fun WebDriverWait.untilViewLoad(viewID: String): WebElement {
    return this.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(viewID)))

}

fun WebDriverWait.untilViewLoad(viewID: String, extentTest: ExtentTest) {

    try {
        this.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(viewID)))
    } catch (e: Exception) {
        extentTest.log(Status.FAIL, "Some error happened, cause ： ${e.message}")
    }


}
fun WebDriverWait.waitForVisibleByID(driver: AndroidDriver<MobileElement>?,by: By?, waitTime: Int) {

    for (attempt in 0 until waitTime) {
        try {
            driver?.findElement(by)
            break
        } catch (e: Exception) {
            driver?.manage()?.timeouts()?.implicitlyWait(1, TimeUnit.SECONDS)
        }
    }
    until(ExpectedConditions.visibilityOfElementLocated(by))
}

fun WebDriverWait.waitForVisibleByText(driver: AndroidDriver<MobileElement>?, by: By?, equalText: String, waitTime: Int) {


    for (attempt in 0 until waitTime) {
        print(attempt)
        try {
            if (driver?.findElement(by)?.text == equalText) {
                break
            } else {
                throw Exception("not find assign text");
            }

        } catch (e: Exception) {
            driver?.manage()?.timeouts()?.implicitlyWait(1, TimeUnit.SECONDS)
        }
    }
    until(ExpectedConditions.visibilityOfElementLocated(by))
}

fun scrollToId(driver: AndroidDriver<MobileElement>, id: String) {
    val el =
            driver.findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceIdMatches(\"$id\"));")
}
fun scrollToText(driver: AndroidDriver<MobileElement>, text: String) {
    val el = driver.findElementByAndroidUIAutomator("new UiScrollable("
            + "new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().text(\"" + text + "\"));")
}
