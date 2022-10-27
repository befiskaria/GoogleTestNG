package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;

    public static Properties Config = new Properties();

    public Base() {

        try {
            FileInputStream file = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
            Config.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public static void launchBrowser() throws Exception {

        //String browserName = Config.getProperty("Browser");
        String browserName = ExcelReaderHM.getValue("Browser");
        if (browserName.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browserName.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

    }

    public static void browserConfig() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Timeutils.pageTimeout));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeutils.elementTimeout));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public static void googleHomepageURL() throws Exception {
        //driver.get(Config.getProperty("GoogleURL"));
        driver.get(ExcelReaderHM.getValue("GoogleURL"));
    }

    public static void gmailURL() throws Exception {
        //driver.get(Config.getProperty("GmailURL"));
        driver.get(ExcelReaderHM.getValue("GmailURL"));
    }

    public static String captureScreenshot(WebDriver driver) throws Exception {
        String currentDate = new SimpleDateFormat("hh-mm-ss-dd-MM-yyyy").format(new Date());
        String newScreenshotName = currentDate.toString();
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// File destination = new File("screenshot.png");
        File destination = new File(".//Report/screenshots//" + newScreenshotName + ".png");
        // getting the absolute path of the screenshot
        String filePath = destination.getAbsolutePath();
        FileUtils.copyFile(source, destination);
        return filePath;
    }
}
