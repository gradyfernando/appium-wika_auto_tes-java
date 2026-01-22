package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.SuratApiTest;
import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.model.Surat;
import co.id.gradyfernando.model.enumeration.Jenis;
import co.id.gradyfernando.pageObjects.android.DaftarSuratPage;
import co.id.gradyfernando.pageObjects.android.DetailSuratPage;
import co.id.gradyfernando.pageObjects.android.HomePage;

public class DeepLinkDetailSuratMasukFlowTest extends BaseDeepLinkTest {

    private List<Surat> suratList;
    private Surat sampleSurat;

    private final String _detailTitlePage = "Detail Surat Masuk";

    @BeforeClass(alwaysRun = true)
    public void prepare() throws InterruptedException {
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

        // Check No Surat
        Assert.assertEquals(detailSuratPage.getNomorSurat(), nomorSurat);

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("0");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        pressBackButton();
    }

    @Test
    public void test_deeplink_appscheme_DetailSurat() throws InterruptedException {
        String suratId = sampleSurat.getIdsurat();

        // Scheme app 'weoffice'
        driver.get("weoffice://surat/detail?id=" + suratId + "&tipe=0");

        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.scrollToText(sampleSurat.getNosurat());

        Assert.assertEquals(detailSuratPage.getNomorSurat(), sampleSurat.getNosurat());

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("0");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        pressBackButton();
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

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("0");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");
        
        pressBackButton();
    }

}
