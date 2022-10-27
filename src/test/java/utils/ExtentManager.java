package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends Base {
	private static ExtentReports extent;

	public static ExtentReports getInstance() throws Exception {
		if (extent == null)
			createInstance();
		return extent;
	}

	public static ExtentReports createInstance() throws Exception {
		String fileName = getReportName();
		String directory = System.getProperty("user.dir") + ".//Report//";
		new File(directory).mkdirs();
		String path = directory + fileName;
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark = new ExtentSparkReporter(".//Report//" + fileName + ".html");
		spark.config().setEncoding("utf-8");
		spark.config().setDocumentTitle("Execution Report");
		spark.config().setReportName("Test Results");
		spark.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("Project", ExcelReaderHM.getValue("Project"));
		extent.setSystemInfo("Browser", ExcelReaderHM.getValue("Browser"));
		extent.attachReporter(spark);

		return extent;
	}

	public static String getReportName() {
		String d = new SimpleDateFormat("hh-mm-ss dd-MM-yyyy").format(new Date());
		String fileName = "ExecutionReport_" + d.toString().replace(":", "_").replace(" ", "_");
		return fileName;
	}
}
