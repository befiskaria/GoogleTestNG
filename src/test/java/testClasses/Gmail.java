package testClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.GmailPOM;
import utils.Base;
import utils.ExtentListenerClass;

import static java.lang.Thread.currentThread;

@Listeners(ExtentListenerClass.class)
public class Gmail extends Base {
	static GmailPOM gm;
	SoftAssert Assert = new SoftAssert();

	public Gmail() {
		super();
	}

	@BeforeMethod(alwaysRun = true)
	/*
	 * @Parameters("BrowserType") public static void
	 * startBrowser(@Optional("Chrome") String browserName) throws Exception { if
	 * (browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("CH")
	 * || browserName.equalsIgnoreCase("Google Chrome")) {
	 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
	 * 
	 * } else if (browserName.equalsIgnoreCase("Firefox") ||
	 * browserName.equalsIgnoreCase("MF") ||
	 * browserName.equalsIgnoreCase("Mozilla Firefox")) {
	 * 
	 * WebDriverManager.firefoxdriver().setup(); driver = new FirefoxDriver(); }
	 * else if (browserName.equalsIgnoreCase("Edge") ||
	 * browserName.equalsIgnoreCase("ME") ||
	 * browserName.equalsIgnoreCase("Microsoft Edge")) {
	 * WebDriverManager.edgedriver().setup(); driver = new EdgeDriver(); } else {
	 * Reporter.log("Sorry we do not support this browser", true); }
	 * browserConfig(); gmailURL(); gm = new GmailPOM(); }
	 */
	public void startBrowser() throws Exception {
		launchBrowser();
		browserConfig();
		gmailURL(); 
		gm = new GmailPOM();
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1, enabled = true, groups = { "Regression" })
	public void verifyLearnMorePageTitle() throws Exception {
		gm.clickLearnMore();
		gm.SwitchToNewTab();
		Thread.sleep(1000);
		gm.verifyLearnMorePageTitle();
		// extentTest.log(Status.INFO, "",
		// MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());
	}

	@Test(priority = 2, enabled = true, groups = { "Smoke", "Sanity" })
	public void verifyLinkTerms() throws Exception {
		gm.clickTerms();
		// extentTest.log(Status.INFO, "",
		// MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());
	}
}
