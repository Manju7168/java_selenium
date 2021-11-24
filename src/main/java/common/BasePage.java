package common;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DataUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePage extends DataUtils {
    Logger logger = LoggerFactory.getLogger(BasePage.class);
    private WebDriver driver = DataUtils.getDriver();
    WebDriverWait webDriverWait = new WebDriverWait(driver, 60);


    public WebElement waitForElement(By locator) {
        logger.info("Before waitForElement: " + locator);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        logger.info("After waitForElement: " + locator);
        return driver.findElement(locator);
    }

    public void launchApplication(String url) {
        driver.get(url);
        logger.info("Url successfully launched: " + url);
    }

    public void selectByVisibleText(By locator, String option) {
        logger.info("Before Selecting " + option + " from Dropdown: " + locator);
        Select select = new Select(waitForElement(locator));
        select.selectByVisibleText(option);
        logger.info("After Selecting " + option + " from Dropdown: " + locator);
    }

    public void buttonClick(By locator) {
        logger.info("Before clicking Element: " + locator);
        waitForElement(locator).click();
        logger.info("After clicking Element: " + locator);
    }

    public void radiobuttonClick(By locator) {
        logger.info("Before clicking Element: " + locator);
        if (!waitForElement(locator).isSelected())
            waitForElement(locator).click();
        logger.info("After clicking Element: " + locator);
    }

    public void enterText(By locator, String text) {
        logger.info("Before entering text" + text + "in Element: " + locator);
        waitForElement(locator).sendKeys(text);
        logger.info("After entering text" + text + "in Element: " + locator);
    }

    public String getElementText(By locator) {
        String txt = waitForElement(locator).getText();
        logger.info("GetElementText for locator :" + locator);
        return txt;
    }

    public void sendKeyboardKey(By locator, Keys s) {
        waitForElement(locator).sendKeys(s);
        logger.info("sendKeyboardKey  :" + locator);
    }

    public void waitForPageLoaded() {
        logger.info("Before wait for DomLoad ");
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 90);
            wait.until(expectation);
        } catch (Throwable error) {

        }
        logger.info("After wait for DomLoad");
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Throwable error) {

        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void moveToElement(By locator) {
        logger.info("Before moving to Element : " + locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForElement(locator));
        logger.info("After moving to Element : " + locator);
    }

    public void report(String status, String message) {
        ExtentTest extentTest = DataUtils.getExtentTest();
        switch (status) {
            case CommonConstants.INFO:
                extentTest.log(Status.INFO, MarkupHelper.createLabel(message, ExtentColor.WHITE));
                try {
                    extentTest.log(Status.INFO, "", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(DataUtils.getDriver())).build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case CommonConstants.FAIL:
            case "error":
                extentTest.log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
                try {
                    extentTest.log(Status.FAIL, "", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(DataUtils.getDriver())).build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case CommonConstants.PASS:
                extentTest.log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
                try {
                    extentTest.log(Status.PASS, "", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(DataUtils.getDriver())).build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void reportWithoutScreenshot(String status, String message) {
        ExtentTest extentTest = DataUtils.getExtentTest();
        if (status.equalsIgnoreCase(CommonConstants.INFO)) {
            extentTest.log(Status.INFO, MarkupHelper.createLabel(message, ExtentColor.WHITE));
        } else if (status.equalsIgnoreCase("error") || status.equalsIgnoreCase(CommonConstants.FAIL)) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
        } else if (status.equalsIgnoreCase(CommonConstants.PASS))
            extentTest.log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }

    public static String getScreenshot(WebDriver driver) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String file = "TestsScreenshots\\" + dateName + ".png";
        String destination = System.getProperty("user.dir") + "\\target\\extent-report\\" + file;
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return file;
    }

}
