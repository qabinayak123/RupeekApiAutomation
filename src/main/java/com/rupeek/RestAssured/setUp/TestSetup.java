package com.rupeek.RestAssured.setUp;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.rupeek.RestAssured.util.ConfigProperties;
import com.rupeek.RestAssured.util.Extentmanager;

public class TestSetup {

	public static ConfigProperties configProperties;
	public static ExtentReports extent;

	protected static ThreadLocal<ExtentTest> classLevelLogger = new ThreadLocal<ExtentTest>();
	protected static ThreadLocal<ExtentTest> testLevelLogger = new ThreadLocal<ExtentTest>();


	@BeforeSuite
	public void setUp() {
		configProperties = ConfigFactory.create(ConfigProperties.class);

		//RestAssured.baseURI = configProperties.getBaseURI();
		//RestAssured.basePath = configProperties.getBasePath();
		extent=Extentmanager.GetExtent("./AutomationReport.html");
		
	}

	@BeforeTest
	public void beforeTest() {

	}

	@BeforeClass
	public void beforeClass() {
		ExtentTest classLevelTest = extent.createTest(getClass().getSimpleName());


		classLevelLogger.set(classLevelTest);

	}

	@BeforeMethod
	public void beforeMethod(Method method) {

		ExtentTest test = classLevelLogger.get().createNode(method.getName());


		testLevelLogger.set(test);
	}

	@AfterMethod
	public void afterMethod(ITestResult testCaseResult) {
		if(testCaseResult.isSuccess())
		{
			testLevelLogger.get().pass("This test case got passed");
		}
		else
		{
			testLevelLogger.get().fail("This test case got failed");
		}
		
		extent.flush();

	}

	@AfterClass
	public void AfterClass() {

	}

	@AfterTest
	public void afterTest() {

	}

	@AfterSuite
	public void tearDown() {

	}
	public static ExtentTest testLogger()
	{
		return testLevelLogger.get();
	}


}
