package co.id.gradyfernando.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/TestResultReport.html");

            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("TestNG + ExtentReports");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("OS", "Mac");
            extent.setSystemInfo("Tester", "Grady Fernando");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}