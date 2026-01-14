package co.id.gradyfernando.testUtils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import co.id.gradyfernando.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	// public LoginPage loginPage;
	
	@BeforeClass(alwaysRun = true)
	public void startAppiumServer() throws URISyntaxException, IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/co/id/gradyfernando/resources/data.properties");
		properties.load(fis);
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : properties.getProperty("ipAddress");
		String port = properties.getProperty("port");

		service = startAppiumService(ipAddress, Integer.parseInt(port));

		UiAutomator2Options options = new UiAutomator2Options();

		String pathChromeDriver = "//Users//integrateknologisolusi//Downloads//chromedriver-mac-arm64//chromedriver";
		options.setChromedriverExecutable(pathChromeDriver);

		options.setDeviceName(properties.getProperty("androidDeviceName"));
		options.setApp(properties.getProperty("appPath"));
		
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// loginPage = new LoginPage(driver);
	}

	public void backButton() {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
        driver.quit();
        service.stop();
	}
	
}
