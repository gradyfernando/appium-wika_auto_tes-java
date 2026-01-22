package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

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

public class DeepLinkDetailDisposisiMasukFlowTest extends BaseDeepLinkTest {

    private List<Disposisi> disposisiList;
    private Disposisi sampleDisposisi;

    private final String _detailTitlePage = "Detail Disposisi";
    
    @BeforeClass(alwaysRun = true)
    public void prepare() throws InterruptedException {
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
    public void test_intent_DetailDisposisiMasuk() throws InterruptedException {
        String kodeDisposisi = sampleDisposisi.getKodedisposisi();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Disposisi Masuk");
        
        Thread.sleep(2000);

        DaftarDisposisiPage daftarDisposisiPage = new DaftarDisposisiPage(driver);
        daftarDisposisiPage.selectItemByKodeDisposisi(kodeDisposisi);

        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getKodedisposisi());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getKodedisposisi(), "Disposisi Masuk: Kode Tidak Sesuai");

        // Check Title
        String titlePage = detailDisposisiPage.getTitlePage("0");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        pressBackButton();
    }

    @Test
    public void test_deeplink_appscheme_DetailDisposisiMasuk() throws InterruptedException {
        String disposisiId = sampleDisposisi.getIddisposisi();

        // Scheme app 'weoffice'
        driver.get("weoffice://disposisi/detail?id=" + disposisiId + "&tipe=in");

        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getKodedisposisi());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getKodedisposisi());

        // Check Title
        String titlePage = detailDisposisiPage.getTitlePage("0");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        pressBackButton();
    }

    @Test
    public void test_deeplink_weburl_DetailDisposisiMasuk() throws InterruptedException {
        String disposisiId = sampleDisposisi.getIddisposisi();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/disposisi/detail/" + disposisiId + "/in";
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getKodedisposisi());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getKodedisposisi());

        // Check Title
        String titlePage = detailDisposisiPage.getTitlePage("0");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        pressBackButton();
    }

}
