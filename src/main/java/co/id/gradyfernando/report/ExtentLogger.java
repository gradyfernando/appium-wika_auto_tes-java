package co.id.gradyfernando.report;

public class ExtentLogger {

    public static void info(String message) {
        try {
            ExtentReportListeners.getTest().info(message);
        } catch (NullPointerException e) {
            System.out.println("Log info failed. Test Listener object is null");
        }
    }

    public static void pass(String message) {
        try {
            ExtentReportListeners.getTest().pass(message);
        } catch (NullPointerException e) {
            System.out.println("Log info failed. Test Listener object is null");
        }
    }

    public static void skip(String message) {
        try {
            ExtentReportListeners.getTest().skip(message);
        } catch (NullPointerException e) {
            System.out.println("Log info failed. Test Listener object is null");
        }
    }

    public static void fail(String message) {
        try {
            ExtentReportListeners.getTest().fail(message);
        } catch (NullPointerException e) {
            System.out.println("Log info failed. Test Listener object is null");
        }
    }
    
}