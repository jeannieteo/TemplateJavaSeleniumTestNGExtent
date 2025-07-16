package utilities;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            // Extent report setup via ExtentManager
            Date date = new Date();
            String dateS = date.toString().replaceAll(":", "").replaceAll(" ", "");
            String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + dateS + "_report.html";
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void removeTest() {
        test.remove();
    }
}