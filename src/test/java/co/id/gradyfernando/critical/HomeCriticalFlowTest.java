package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.HakAksesApiTest;
import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.pageObjects.android.DetailUndanganPage;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;
import co.id.gradyfernando.utils.SessionInjector;

public class HomeCriticalFlowTest extends AndroidBaseTest {

	private HakAksesPage hakAksesPage;

	private List<Akses> aksesList;
	private Akses selectedAkses;
	private User user;
	private boolean isSekretarisExist = false;

	@BeforeClass(alwaysRun = true)
	public void prepare() {
		// Get data first
		user = LoginApiTest.login(
			"TK171561",
			"TK171561",
			"A.1.D", 
			"2.2.0"
		);
		
		// Set Token
		SessionInjector.injectToken(driver, user.getToken());

		// Set User Data to Session
		HashMap<String, String> userData = new HashMap<>();
		userData.put("action", "userdata");
		userData.put("nama", "\"" + user.getNama() + "\"");
		userData.put("iduser", "\"" + user.getIduser() + "\"");
		userData.put("username", "\"" + user.getUsername() + "\"");
		userData.put("email", "\"" + user.getEmail() + "\"");
		userData.put("num_role", "\"" + user.getNum_role() + "\"");

		SessionInjector.injectDataMap(driver, userData);
	}

	@Test
	public void test_LoginSekretaris() throws InterruptedException {
		aksesList = HakAksesApiTest.getAkses(user.getToken());
		var selectedRole = "sekretaris";

		hakAksesPage = new HakAksesPage(driver);

		aksesList.forEach(new Consumer<Akses>(){
            @Override
            public void accept(Akses akses) {
				if (akses.getNamarole().toLowerCase().equals(selectedRole.toLowerCase())) {
					isSekretarisExist = true;
					selectedAkses = akses;
				}
            }
        });

		// Set Role
		Assert.assertTrue(isSekretarisExist);
		hakAksesPage.injectSession(selectedAkses);
		hakAksesPage.setRole(selectedRole);
		
		HakAksesApiTest.setAkses(user, selectedAkses);

		Thread.sleep(2000);
	}

	@Test
	public void test_openUndanganMasuk() throws InterruptedException {
		// SessionInjector.injectToken(driver, user.getToken());

		// var selectedRole = "sekretaris";
		// hakAksesPage = new HakAksesPage(driver);
		// hakAksesPage.setRole(selectedRole);

		// Thread.sleep(2000);

        // UndanganPage undanganPage = new UndanganPage(driver);
        // undanganPage.selectItemWithStatus("Butuh Approval");

        // Thread.sleep(2000);

		DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.scrollToText("Approve");

		Thread.sleep(2000);
	}
	
}
