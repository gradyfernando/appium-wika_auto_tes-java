package co.id.gradyfernando.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class AppiumUtils {

	public AppiumDriverLocalService service;

	public Double convertToDouble(String ammount) {
		return Double.parseDouble(ammount.substring(1));
	}

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(
            jsonContent, 
            new TypeReference<List<HashMap<String, String>>>() {
                
            });

        return data;
    }

    public AppiumDriverLocalService startAppiumService(String configIPAddress, int configPort) {
		String pathToAppiumJS = "//opt//homebrew//lib//node_modules//appium//build//lib//main.js";
		File fileAppiumJS = new File(pathToAppiumJS);

		service = new AppiumServiceBuilder()
				.withAppiumJS(fileAppiumJS)
                .withArgument(GeneralServerFlag.ALLOW_INSECURE, "uiautomator2:adb_shell")
				.withIPAddress(configIPAddress)
				.usingPort(configPort)
				.build();

        service.start();
        return service;
    }

    public void waitForElementToAppear(WebElement element, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(
            ExpectedConditions.attributeContains(
                element, 
                "text", 
                "Cart"));
    }
    
    public String getScreenshot(String testCaseName, AppiumDriver driver) throws IOException {
    	File source = driver.getScreenshotAs(OutputType.FILE);
    	String destinationFile = "fail_screenshot/" + testCaseName + ".png";
    	
    	FileUtils.copyFile(source, new File("test-output/" + destinationFile));
    	return destinationFile;
    }

}