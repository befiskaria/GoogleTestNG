package testClasses;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.GooglePOM;
import utils.Base;
import utils.ExtentListenerClass;

import static java.lang.Thread.currentThread;
import static utils.ExtentListenerClass.takeScreenshot;

@Listeners(ExtentListenerClass.class)
public class Google extends Base {
	static GooglePOM gp;
	SoftAssert Assert = new SoftAssert();

	public Google() {
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
	 * browserConfig(); googleHomepageURL(); gp = new GooglePOM(); }
	 */

	public void startBrowser() throws Exception {
		launchBrowser();
		browserConfig();
		googleHomepageURL();
		gp = new GooglePOM();
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	/*
	 * 1. Go to https://www.google.com/ 2.Click on About 3.Click on view all our
	 * products 4.select the option Shop Google store 5.Verify the price of Pixel 7
	 */
	@Test(priority = 1, enabled = true, groups = { "Regression" })
	public void verifyViewAllProductsPageTitle() throws Exception {
		System.out.println(currentThread().getId());
		gp.clickAbout();
		takeScreenshot();
		gp.verifyViewProductsTitle();
		//test.pass( "", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());

	}

	@Test(priority = 2, enabled = true, groups = { "Sanity" })
	public void getPixelPrice() throws Exception {
		System.out.println(currentThread().getId());
		gp.clickAbout();
		gp.clickViewProducts();
		gp.clickShopstore();
		gp.SwitchToNewTab();
		gp.selectPixel7();
		gp.pricePrint();
	}

	@Test(priority = 3, enabled = true, groups = { "Regression" })
	public void befisTest() throws Exception {
		System.out.println(currentThread().getId());
		throw new SkipException("Skipped the test case");
		// gp.clickTerms();
	}
}
