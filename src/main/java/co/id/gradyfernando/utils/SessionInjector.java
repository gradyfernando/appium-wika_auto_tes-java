package co.id.gradyfernando.utils;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;

public class SessionInjector {

    public static void injectToken(AndroidDriver driver, String token) {
        System.err.println(" =============== " );
        driver.executeScript("mobile: shell", Map.of(
            "command", "am",
            "args", List.of(
                "broadcast",
                "-n", "co.id.integra.weoffice/.core.test.TestSessionReceiver",
                "-a", "co.id.integra.weoffice.TestSession",
                "--es", "token", token
            )
        ));
        System.err.println("token: " + token);
    }

    public static void injectData(AndroidDriver driver, String param, String data) {

    }

    public static void injectDataMap(AndroidDriver driver, Map<String, String> dataMap) {

    }

    public static void injectXml(AndroidDriver driver, String token) {
        String sharedPrefsXml = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n" +
            "<map>\n" +
            "    <string name=\"token\">" + token + "</string>\n" +
            "</map>";

        // Convert to Base64
        String base64Content = Base64.getEncoder().encodeToString(sharedPrefsXml.getBytes());

        // Push the file to the shared preferences directory
        driver.executeScript("mobile: pushFile", Map.of(
            "remotePath", "/data/data/com.your.package/shared_prefs/your_prefs_name.xml",
            "payload", base64Content
        ));
    }
    
}