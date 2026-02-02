package co.id.gradyfernando.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.gson.Gson;

import co.id.gradyfernando.api.HakAksesApiTest;
import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.pageObjects.android.LoginPage;
import co.id.gradyfernando.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class AndroidBaseTest extends AppiumUtils {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public User user;
	public boolean isSelectedRoleExist = false;
	
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

	public void injectLoginAndRole(String username, String password, String selectedRole) throws InterruptedException {
		// Get data first
		user = LoginApiTest.login(
			username,
			password,
			"A.1.D", 
			"2.2.0"
		);
		
        LoginPage loginPage = new LoginPage(driver);
        loginPage.injectSession(user);

        // Set role
        List<Akses> aksesList = HakAksesApiTest.getAkses(user.getToken());
		HakAksesPage hakAksesPage = new HakAksesPage(driver);

		if (aksesList.size() > 1) {
			hakAksesPage.setActivity();
			Thread.sleep(1000);

			aksesList.forEach(new Consumer<Akses>(){
				@Override
				public void accept(Akses akses) {
					if (akses.getNamarole().toLowerCase().equals(selectedRole.toLowerCase())) {
						isSelectedRoleExist = true;

						List<String> menus = getMenuFromAPI(user, akses);

						hakAksesPage.injectSession(akses);
						hakAksesPage.injectMenuData(menus);
						
						hakAksesPage.setRole(selectedRole);
					}
				}
			});
		} else {
			Akses akses = aksesList.get(0);
			isSelectedRoleExist = true;
			List<String> menus = getMenuFromAPI(user, akses);

			hakAksesPage.injectSession(akses);
			hakAksesPage.injectMenuData(menus);
		}
        
		Assert.assertTrue(isSelectedRoleExist);
        Thread.sleep(1000);
	}

	private List<String> getMenuFromAPI(User user, Akses akses) {
		Map<String, Object> responseData = HakAksesApiTest.setAkses(user, akses);
		String jsonResponse = new Gson().toJson(responseData);

		List<String> menus = new ArrayList<String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonResponse);
			JSONArray menuJson = jsonObject.getJSONArray("menu");
			
			for (int i = 0; i < menuJson.length(); i++) {
				menus.add(menuJson.getString(i));
			}
		} catch (org.json.JSONException e) {
			e.printStackTrace();
		}

		return menus;
	}

	public void pressBackButton() {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
        driver.quit();
        service.stop();
	}
	
}
