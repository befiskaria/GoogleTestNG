package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;
	protected ExtentTest logger;

	public static Properties Config = new Properties();

	public Base() {

		try {
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
			Config.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException a) {
			a.printStackTrace();
		}
	}

	public static void launchBrowser() throws Exception {

		String browserName = Config.getProperty("Browser");
		// String browserName = ExcelReaderHM.getValue("Browser");
		if (browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("Firefox")) {

			WebDriverManager.firefoxdriver().setup();
			// System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

			driver = new FirefoxDriver();
		} else if (browserName.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

	}

	public static void browserConfig() {
		driver.manage().timeouts().pageLoadTimeout(Timeutils.webpageTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Timeutils.elementTimeout, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	public static void googleHomepageURL() throws Exception {
		driver.get(Config.getProperty("GoogleURL"));
		// driver.get(ExcelReaderHM.getValue("GoogleURL"));
	}

	public static void gmailURL() throws Exception {
		driver.get(Config.getProperty("GmailURL"));
		// driver.get(ExcelReaderHM.getValue("GmailURL"));
	}

	/*
	 * public static String captureScreenshot(WebDriver driver) throws Exception {
	 * String currentdate = new SimpleDateFormat("hh-mm-ss-dd-MM-yyyy").format(new
	 * Date()); String newScreenshotName = currentdate.toString(); File source =
	 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // File
	 * destination=new File("screenshot.png"); File destination = new
	 * File(".//Report/screenshots//" + newScreenshotName + ".png"); // getting the
	 * absolute path of the screenshot String filePath =
	 * destination.getAbsolutePath(); FileUtils.copyFile(source, destination);
	 * return filePath; }
	 */
}
