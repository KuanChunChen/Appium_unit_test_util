package com.oring.oring_wm_ui_test.apkTest

import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.apache.commons.io.FileUtils
import org.openqa.selenium.*
import org.openqa.selenium.remote.RemoteWebElement
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

private fun screenShot(driver: IOSDriver<MobileElement>?, saveDir: String){
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

fun ExtentTest.screenshotInfo(driver: IOSDriver<MobileElement>?, fileName: String, message: String) {
    val saveDir = "$screenShotsDir$fileName.png"


    screenShot(driver, saveDir)
//    addScreenCaptureFromPath(saveDir)
//    info(message).addScreenCaptureFromPath(saveDir)
    info(message, MediaEntityBuilder.createScreenCaptureFromPath(saveDir).build())
}


fun WebDriverWait.untilViewLoad(viewID: String): WebElement {
    return this.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(viewID)))

}

fun WebDriverWait.untilXpath(xPath: String): WebElement {
    return this.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(xPath)))

}
fun WebDriverWait.untilIosClassChain(iosClassChain: String): WebElement {
    return this.until(ExpectedConditions.presenceOfElementLocated(MobileBy.iOSClassChain(iosClassChain)))

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

fun iosScrollToName(driver: AndroidDriver<MobileElement>, elementName: String, findClassName: String) {
    val parent = driver.findElement(By.className(findClassName)) as RemoteWebElement
    val parentID = parent.id
    val scrollObject = HashMap<String, String>()
    scrollObject["element"] = parentID
    scrollObject["name"] = elementName
    driver.executeScript("mobile:scroll", scrollObject)
}

fun iosScrollToPredicateString(driver: AndroidDriver<MobileElement>, predicateString: String, findClassName: String) {

    val parent = driver.findElementByAccessibilityId(findClassName) as RemoteWebElement

    val parentID = parent.id
    val scrollObject = HashMap<String, String>()
    scrollObject["element"] = parentID

    scrollObject["predicateString"] = predicateString
    driver.executeScript("mobile:scroll", scrollObject)

}

fun scrollToUnSeeElement(driver: AndroidDriver<MobileElement>, elementIosChain: String) {
//    val element: WebElement = driver.findElement(MobileBy.iOSClassChain(elementIosChain))
    val element: WebElement = driver.findElementByAccessibilityId(elementIosChain)

    val js = driver as JavascriptExecutor

    val scrollObjects = HashMap<Any, Any>()
    scrollObjects["element"] = (element as RemoteWebElement).id
    scrollObjects["direction"] = "down"
    driver.executeScript("mobile: scroll", scrollObjects)
}

fun tapItemByDescription(driver: AndroidDriver<MobileElement>, text: String) {
    println("tapItemByDescription(): $text")
    val js = driver as JavascriptExecutor
    val scrollObject = HashMap<Any, Any>()
    scrollObject["predicateString"] = "name == \"RELAY\" AND type == \"XCUIElementTypeOther\""
    js.executeScript("mobile: scroll", scrollObject)

}

fun scrollToDown(driver: AndroidDriver<MobileElement>) {

    val js = driver as JavascriptExecutor
    val scrollObject = HashMap<String, String>()
    scrollObject["direction"] = "down"
    js.executeScript("mobile: scroll", scrollObject)
}


fun scrollToUp(driver: AndroidDriver<MobileElement>) {

    val js = driver as JavascriptExecutor
    val scrollObject = HashMap<String, String>()
    scrollObject["direction"] = "up"
    js.executeScript("mobile: scroll", scrollObject)
}

fun scrollToDown(driver: IOSDriver<MobileElement>) {

    val js = driver as JavascriptExecutor
    val scrollObject = HashMap<String, String>()
    scrollObject["direction"] = "down"
    js.executeScript("mobile: scroll", scrollObject)
}