package co.id.gradyfernando.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;

public class SessionInjector {

    public static void injectToken(AndroidDriver driver, String token) {
        driver.executeScript("mobile: shell", Map.of(
            "command", "am",
            "args", List.of(
                "broadcast",
                "-n", "co.id.integra.weoffice/.core.test.TestSessionReceiver",
                "-a", "co.id.integra.weoffice.TestSession",
                "--es", "token", token
            )
        ));
    }

    public static void injectData(AndroidDriver driver, String param, String data) {
        driver.executeScript("mobile: shell", Map.of(
            "command", "am",
            "args", List.of(
                "broadcast",
                "-n", "co.id.integra.weoffice/.core.test.TestDataSessionReceiver",
                "-a", "co.id.integra.weoffice.TestDataSession",
                "--es", param, data
            )
        ));
    }

    public static void injectDataMap(AndroidDriver driver, Map<String, String> dataMap) {
        List<String> intentArgs = new ArrayList<>();

        dataMap.forEach((k, v) -> {
            intentArgs.add("--es");
            intentArgs.add(k);
            intentArgs.add(v);
        });

        List<String> args = new ArrayList<>();
        args.add("broadcast");
        args.add("-n");
        args.add("co.id.integra.weoffice/.core.test.TestDataSessionReceiver");
        args.add("-a");
        args.add("co.id.integra.weoffice.TestDataSession");
        args.addAll(intentArgs);

        driver.executeScript("mobile: shell", Map.of(
            "command", "am",
            "args", args
        ));
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