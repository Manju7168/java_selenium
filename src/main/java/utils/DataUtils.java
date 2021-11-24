package utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DataUtils {

    private static WebDriver driver;
    private static ExtentTest extentTest;
    private static ExtentTest parentExtentTest;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        DataUtils.driver = driver;
    }

    public static ExtentTest getExtentTest() {
        return extentTest;
    }

    public static void setExtentTest(ExtentTest extentTest) {
        DataUtils.extentTest = extentTest;
    }

    public static ExtentTest getParentExtentTest() {
        return parentExtentTest;
    }

    public static void setParentExtentTest(ExtentTest parentExtentTest) {
        DataUtils.parentExtentTest = parentExtentTest;
    }

}
