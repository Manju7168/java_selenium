package utils;


import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestManager extends DriverSource implements ITestListener {

   static ExtentReports extentReports = ReportUtility.initializeReport();


    private static ExtentTest extentTest;
    public  void setScenario(String name) {
        extentTest =extentReports.createTest(name);
        DataUtils.setExtentTest(extentTest);
        DataUtils.setParentExtentTest(extentTest);

    }

    public  void setSubScenario(String name) {
        DataUtils.setExtentTest(DataUtils.getParentExtentTest().createNode(name));
    }

    public String getTestData(String property) {
        InputStream fi = null;
        Properties properties = new Properties();
        try {
            fi = new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\java\\resources\\testdata\\data.properties");

            properties.load(fi);
            fi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(property);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) { ;
        if(result.getStatus()== ITestResult.FAILURE) {
            DataUtils.getExtentTest().log(Status.FAIL,result.getThrowable());
            DataUtils.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));
        }
        else if(result.getStatus()== ITestResult.SUCCESS) {
            DataUtils.getExtentTest().log(Status.PASS, MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));
        }
        DataUtils.getDriver().close();
        extentReports.flush();
    }


}
