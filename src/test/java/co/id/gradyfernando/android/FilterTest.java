package co.id.gradyfernando.android;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.pageObjects.android.AdvanceFilterDialog;
import co.id.gradyfernando.pageObjects.android.DaftarSuratPage;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;
import co.id.gradyfernando.utils.SessionInjector;

public class FilterTest extends AndroidBaseTest {

    private User user;
    
    @BeforeClass
    public void loginSekretaris() throws InterruptedException {
        // Get data first
		user = LoginApiTest.login(
			"TK171561",
			"TK171561",
			"A.1.D", 
			"2.2.0"
		);
		
		// Set Token
		SessionInjector.injectToken(driver, user.getToken());

        Thread.sleep(2000);

        HakAksesPage hakAksesPage = new HakAksesPage(driver);
        hakAksesPage.setActivity();
        hakAksesPage.setRole("sekretaris");

        Thread.sleep(2000);
    }

    @Test
    public void test_filterSuratTolak() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Surat Masuk");

        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        daftarSuratPage.clickAdvanceFilter("Status Surat");

        AdvanceFilterDialog advanceFilterDialog = new AdvanceFilterDialog(driver);
        advanceFilterDialog.selectFilter("Ditolak");
    }
    
}
