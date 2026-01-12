package co.id.gradyfernando.testUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;

import co.id.gradyfernando.utils.AppiumUtils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IOSBaseTest extends AppiumUtils {
	
	public AppiumDriverLocalService service;
	public IOSDriver driver;
	
	@BeforeClass
	public void startAppiumServer() throws MalformedURLException, URISyntaxException, IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/co/id/gradyfernando/resources/data.properties");
		properties.load(fis);
		String ipAddress = properties.getProperty("ipAddress");
		String port = properties.getProperty("port");

		service = startAppiumService(ipAddress, Integer.parseInt(port));

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 16");
		options.setPlatformVersion("18.3");
		// options.setApp("/Users/integrateknologisolusi/Library/Developer/Xcode/DerivedData/UIKitCatalog-awlcegzipachymaygqpmsdtzxgwr/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
		options.setApp("/Users/integrateknologisolusi/eclipse-workspace/Appium/src/test/java/resources/UIKITCatalog.app");
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
