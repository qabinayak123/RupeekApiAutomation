package com.rupeek.RestAssured.CustomListeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.rupeek.RestAssured.setUp.TestSetup;
import org.testng.*;

import java.util.Arrays;

public class CustomListener extends TestSetup implements ITestListener, ISuiteListener {


    public void onTestStart(ITestResult result) {

        ExtentTest child = classLevelLogger.get().createNode(result.getName());
        testLevelLogger.set(child);

    }

    public void onStart(ISuite iSuite) {

    }

    public void onFinish(ISuite iSuite) {

    }


    public void onTestSuccess(ITestResult result) {
        testLogger().pass("This test case got passed");
        extent.flush();

    }


    public void onTestFailure(ITestResult result) {

        String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        testLogger()
                .fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                        + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
                        + " \n");


        String failureLogg = "This Test case got Failed";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        testLogger().log(Status.FAIL, m);

        extent.flush();
    }

    public void onTestSkipped(ITestResult result) {
        testLogger().skip("This test case got skipped");
        extent.flush();

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
