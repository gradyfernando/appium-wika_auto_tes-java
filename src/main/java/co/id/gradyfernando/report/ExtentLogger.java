package co.id.gradyfernando.report;

public class ExtentLogger {

    public static void info(String message) {
        ExtentReportListeners.getTest().info(message);
    }

    public static void pass(String message) {
        ExtentReportListeners.getTest().pass(message);
    }

    public static void fail(String message) {
        ExtentReportListeners.getTest().fail(message);
    }
    
}