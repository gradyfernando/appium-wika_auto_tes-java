package co.id.gradyfernando.critical;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;
import co.id.gradyfernando.utils.SessionInjector;

public class HomeCriticalFlowTest extends AndroidBaseTest {

	private HomePage homePage;
	private User user;

	@BeforeMethod
	public void prepare() {
		// Get data first
		user = LoginApiTest.login(
			// "4dminIntegra", 
			// "k6JsDY2?qWSNy;U#", 
			"ET133126",
			"ET133126",
			"A.1.D", 
			"2.2.0"
		);
		
		SessionInjector.injectToken(driver, user.getToken());

	}

	@Test
	public void testDisplayedNameInHome() throws InterruptedException {
		homePage = new HomePage(driver);
		String displayedName = homePage.getNama();
		Assert.assertEquals(user.getNama(), displayedName);
	}
	
}
