package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.HakAksesApiTest;
import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.api.InformasiApiTest;
import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.Informasi;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.model.enumeration.Jenis;
import co.id.gradyfernando.pageObjects.android.DaftarInformasiPage;
import co.id.gradyfernando.pageObjects.android.DetailinformasiPage;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;
import co.id.gradyfernando.utils.SessionInjector;

public class DeepLinkDetailInformasiMasukFlowTest extends AndroidBaseTest {

	private User user;
    private List<Informasi> InformasiList;
    private Informasi sampleInformasi;

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

        // Get Data Informasi
        InformasiList = InformasiApiTest.getListInformasi(
            Jenis.MASUK,
            user.getToken(), 
            "0", 
            "2", 
            "0", 
            new HashMap<>()
        );

        // Ensure list are not empty and select it
        Assert.assertTrue(InformasiList.size() > 0);
        sampleInformasi = InformasiList.get(0);
    }

    @Test
    public void test_intent_DetailInformasi() throws InterruptedException {
        String nomorInformasi = sampleInformasi.getNosurat();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Informasi Masuk");
        
        Thread.sleep(2000);

        DaftarInformasiPage daftarInformasiPage = new DaftarInformasiPage(driver);
        daftarInformasiPage.selectItemByNomor(nomorInformasi);

        DetailinformasiPage detailInformasiPage = new DetailinformasiPage(driver);
        detailInformasiPage.scrollToText(sampleInformasi.getNosurat());

        Assert.assertEquals(detailInformasiPage.getNomorSurat(), sampleInformasi.getNosurat());
        backButton();
    }

    @Test
    public void test_deeplink_appscheme_DetailInformasi() throws InterruptedException {
        String InformasiId = sampleInformasi.getIdinformasi();

        // Scheme app 'weoffice'
        driver.get("weoffice://Informasi/detail?id=" + InformasiId);

        DetailinformasiPage detailInformasiPage = new DetailinformasiPage(driver);
        detailInformasiPage.scrollToText(sampleInformasi.getNosurat());

        Assert.assertEquals(detailInformasiPage.getNomorSurat(), sampleInformasi.getNosurat());
        backButton();
    }

    @Test
    public void test_deeplink_weburl_DetailInformasi() throws InterruptedException {
        String InformasiId = sampleInformasi.getIdinformasi();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/Informasi/detailInformasi/jenis/in/of/0/id/" + InformasiId;
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailinformasiPage detailInformasiPage = new DetailinformasiPage(driver);
        detailInformasiPage.scrollToText(sampleInformasi.getNosurat());

        Assert.assertEquals(detailInformasiPage.getNomorSurat(), sampleInformasi.getNosurat());
        backButton();
    }

}
