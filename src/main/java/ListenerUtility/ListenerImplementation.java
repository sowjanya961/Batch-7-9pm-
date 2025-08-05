package ListenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseTest.BaseClass;

public class ListenerImplementation implements ITestListener, ISuiteListener {

	public ExtentTest test;
	public ExtentReports report;
	public ExtentSparkReporter spark;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report Configuration", true);
		Date d = new Date();// Date from java.util
		String newDate = d.toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report_" + newDate + ".html");
		// configuration
		spark.config().setDocumentTitle("NinzaCRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Edge");

	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		Reporter.log("Report backup", true);

	}

	@Override
	public void onTestStart(ITestResult result) {
		// extentreport(ER)
		test = report.createTest(result.getMethod().getMethodName()+"Execution STARTED====");
		test.log(Status.INFO, "====="+result.getMethod().getMethodName()+"ExecutionSTARTED====");
		// Reporter.log("====="+result.getMethod().getMethodName()+"ExecutionSTARTED====",true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// ER
		test.log(Status.PASS, result.getMethod().getMethodName() + "SUCCESS====");
		// Reporter.log("====="+result.getMethod().getMethodName()+"SUCCESS====",true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// 30-7-25,ss or basic report//
		/*
		 * String testcaseName = result.getMethod().getMethodName();
		 * Reporter.log("===="+testcaseName+"FAILURE====",true); Date d=new
		 * Date();//Date from java.util String newDate = d.toString().replace(" ",
		 * "_").replace(":", "_"); TakesScreenshot t=(TakesScreenshot)BaseClass.sdriver;
		 * File src = t.getScreenshotAs(OutputType.FILE); File dest=new
		 * File("./screenshot/"+testcaseName+" "+newDate+".png"); try {
		 * FileHandler.copy(src, dest); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

		// ER//
		String testcaseName = result.getMethod().getMethodName();
		Date d = new Date();// Date from java.util
		String newDate = d.toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot t = (TakesScreenshot) BaseClass.sdriver;
		String src = t.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src,testcaseName+newDate);
		test.log(Status.FAIL, "====" + testcaseName + "FAILURE====");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// ER
		test.log(Status.SKIP, "=====" + result.getMethod().getMethodName() + "SKIPPED====");
		// Reporter.log("====="+result.getMethod().getMethodName()+"SKIPPED====",true);
	}

}
