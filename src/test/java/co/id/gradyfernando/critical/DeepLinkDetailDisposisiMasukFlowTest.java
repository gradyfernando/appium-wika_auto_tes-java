package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.HakAksesApiTest;
import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.api.DisposisiApiTest;
import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.Disposisi;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.model.enumeration.Jenis;
import co.id.gradyfernando.pageObjects.android.DaftarDisposisiPage;
import co.id.gradyfernando.pageObjects.android.DetailDisposisiPage;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;
import co.id.gradyfernando.utils.SessionInjector;

public class DeepLinkDetailDisposisiMasukFlowTest extends AndroidBaseTest {

	private User user;
    private List<Disposisi> disposisiList;
    private Disposisi sampleDisposisi;

	private boolean isSelectedRoleExist = false;
    
    @BeforeClass(alwaysRun = true)
    public void prepare() throws InterruptedException {
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

        // Get Data Disposisi
        disposisiList = DisposisiApiTest.getListDisposisi(
            Jenis.MASUK,
            user.getToken(), 
            "0", 
            "2", 
            "0", 
            new HashMap<>()
        );

        // Ensure list are not empty and select it
        Assert.assertTrue(disposisiList.size() > 0);
        sampleDisposisi = disposisiList.get(0);
    }

    @Test
    public void test_intent_DetailDisposisi() throws InterruptedException {
        String nomorDisposisi = sampleDisposisi.getNosurat();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Disposisi Masuk");
        
        Thread.sleep(2000);

        DaftarDisposisiPage daftarDisposisiPage = new DaftarDisposisiPage(driver);
        daftarDisposisiPage.selectItemByNomor(nomorDisposisi);

        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getNosurat());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getNosurat());
        backButton();
    }

    @Test
    public void test_deeplink_appscheme_DetailDisposisi() throws InterruptedException {
        String disposisiId = sampleDisposisi.getIddisposisi();

        // Scheme app 'weoffice'
        driver.get("weoffice://disposisi/detail?id=" + disposisiId);

        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getNosurat());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getNosurat());
        backButton();
    }

    @Test
    public void test_deeplink_weburl_DetailDisposisi() throws InterruptedException {
        String disposisiId = sampleDisposisi.getIddisposisi();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/disposisi/detaildisposisi/jenis/in/of/0/id/" + disposisiId;
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getNosurat());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getNosurat());
        backButton();
    }

}
