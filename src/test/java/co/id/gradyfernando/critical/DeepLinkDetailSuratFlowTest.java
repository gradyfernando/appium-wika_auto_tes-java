package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.HakAksesApiTest;
import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.api.SuratApiTest;
import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.Surat;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.model.enumeration.Jenis;
import co.id.gradyfernando.pageObjects.android.DaftarSuratPage;
import co.id.gradyfernando.pageObjects.android.DetailSuratPage;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;
import co.id.gradyfernando.utils.SessionInjector;

public class DeepLinkDetailSuratFlowTest extends AndroidBaseTest {

	private User user;
    private List<Surat> suratList;
    private Surat sampleSurat;

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

        // Get Data Surat
        suratList = SuratApiTest.getListSurat(
            Jenis.MASUK,
            user.getToken(), 
            "0", 
            "2", 
            "0", 
            new HashMap<>()
        );

        // Ensure list are not empty and select it
        Assert.assertTrue(suratList.size() > 0);
        sampleSurat = suratList.get(0);
    }

    @Test
    public void test_intent_DetailSurat() throws InterruptedException {
        String nomorSurat = sampleSurat.getNosurat();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Surat Masuk");
        
        Thread.sleep(2000);

        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        daftarSuratPage.selectItemByNomor(nomorSurat);

        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.scrollToText(sampleSurat.getNosurat());

        Assert.assertEquals(detailSuratPage.getNomorSurat(), nomorSurat);
        backButton();
    }

    @Test
    public void test_deeplink_appscheme_DetailSurat() throws InterruptedException {
        String suratId = sampleSurat.getIdsurat();

        // Scheme app 'weoffice'
        driver.get("weoffice://surat/detail?id=" + suratId);

        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.scrollToText(sampleSurat.getNosurat());

        Assert.assertEquals(detailSuratPage.getNomorSurat(), sampleSurat.getNosurat());
        backButton();
    }

    @Test
    public void test_deeplink_weburl_DetailSurat() throws InterruptedException {
        String suratId = sampleSurat.getIdsurat();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/surat/detailsurat/jenis/in/of/0/id/" + suratId;
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.scrollToText(sampleSurat.getNosurat());

        Assert.assertEquals(detailSuratPage.getNomorSurat(), sampleSurat.getNosurat());
        backButton();
    }

}
