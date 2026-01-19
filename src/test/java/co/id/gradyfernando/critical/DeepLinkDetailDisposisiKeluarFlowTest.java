package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.DisposisiApiTest;
import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.model.Disposisi;
import co.id.gradyfernando.model.enumeration.Jenis;
import co.id.gradyfernando.pageObjects.android.DaftarDisposisiPage;
import co.id.gradyfernando.pageObjects.android.DetailDisposisiPage;
import co.id.gradyfernando.pageObjects.android.HomePage;

public class DeepLinkDetailDisposisiKeluarFlowTest extends BaseDeepLinkTest {
    
    private List<Disposisi> disposisiList;
    private Disposisi sampleDisposisi;

    private final String _detailTitlePage = "Detail Disposisi";
    
    @BeforeClass(alwaysRun = true)
    public void prepare() throws InterruptedException {
        // Get Data Disposisi
        disposisiList = DisposisiApiTest.getListDisposisi(
            Jenis.KELUAR,
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

    @Test()
    public void test_intent_DetailDisposisiKeluar() throws InterruptedException {
        String kodeDisposisi = sampleDisposisi.getKodedisposisi();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Disposisi Keluar");
        
        Thread.sleep(2000);

        DaftarDisposisiPage daftarDisposisiPage = new DaftarDisposisiPage(driver);
        daftarDisposisiPage.selectItemByKodeDisposisi(kodeDisposisi);

        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getKodedisposisi());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getKodedisposisi());

        // Check Title
        String titlePage = detailDisposisiPage.getTitlePage("1");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        backButton();
    }

    @Test
    public void test_deeplink_appscheme_DetailDisposisiKeluar() throws InterruptedException {
        String disposisiId = sampleDisposisi.getIddisposisi();

        // Scheme app 'weoffice'
        driver.get("weoffice://disposisi/detail?id=" + disposisiId + "&tipe=out");

        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getKodedisposisi());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getKodedisposisi(), "Kode disposisi tidak sesuai");

        // Check Title
        String titlePage = detailDisposisiPage.getTitlePage("1");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        backButton();
    }

    @Test
    public void test_deeplink_weburl_DetailDisposisiKeluar() throws InterruptedException {
        String disposisiId = sampleDisposisi.getIddisposisi();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/disposisi/detail/" + disposisiId + "/out";
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getKodedisposisi());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getKodedisposisi(), "Kode Disposisi Tidak Sesuai");

        // Check Title
        String titlePage = detailDisposisiPage.getTitlePage("1");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        backButton();
    }

}
