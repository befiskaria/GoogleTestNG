package utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListenerClass extends Base implements ITestListener {
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getTestClass().getName() + "." + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		String logText = "<b>Test method '" + result.getMethod().getMethodName() + "' executed successfully</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
	}

	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test method '" + result.getMethod().getMethodName() + "' skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
	}

	public void onTestFailure(ITestResult result) {

		String logText = "<b>Test method '" + result.getMethod().getMethodName() + "' failed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		try {
			extentTest.get().fail("<b><font color=red>" + "Screenshot at the time of fail" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver, result)).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String exceptionMessage1 = result.getThrowable().toString();
		String exceptionMessage2 = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get()
				.fail("<details><summary><b><font color=red>" + "Exception occured, click to see details:"
						+ "</font></b></summary>" + (exceptionMessage1 + exceptionMessage2).replaceAll(",", "<br>")
						+ "</details> \n");
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

	public static String captureScreenshot(WebDriver driver, ITestResult result) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(".//Report/screenshots//" + result.getMethod().getMethodName() + ".png");
		String filePath = destination.getAbsolutePath();
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}

}
