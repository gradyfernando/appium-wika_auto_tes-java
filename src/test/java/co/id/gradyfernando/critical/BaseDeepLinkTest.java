package co.id.gradyfernando.critical;

import java.util.List;
import java.util.function.Consumer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import co.id.gradyfernando.api.HakAksesApiTest;
import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;
import co.id.gradyfernando.utils.SessionInjector;

public class BaseDeepLinkTest extends AndroidBaseTest {

	User user;
	boolean isSelectedRoleExist = false;

    @BeforeClass(alwaysRun = true)
    public void authUserAdmin() throws InterruptedException {
        // Get data first
		user = LoginApiTest.login(
			"4dminIntegra",
			"k6JsDY2?qWSNy;U#",
			"A.1.D", 
			"2.2.0"
		);
		
		// Set Token
		SessionInjector.injectToken(driver, user.getToken());
		SessionInjector.injectData(driver, "nama", "\"" + user.getNama() + "\"");

        // Set role
        List<Akses> aksesList = HakAksesApiTest.getAkses(user.getToken());
		var selectedRole = "administrator";

		HakAksesPage hakAksesPage = new HakAksesPage(driver);

		aksesList.forEach(new Consumer<Akses>(){
            @Override
            public void accept(Akses akses) {
				if (akses.getNamarole().toLowerCase().equals(selectedRole.toLowerCase())) {
					isSelectedRoleExist = true;
				}
            }
        });

		// Set Role
		Assert.assertTrue(isSelectedRoleExist);
		hakAksesPage.setRole(selectedRole);

        Thread.sleep(2000);
    }
    
}
