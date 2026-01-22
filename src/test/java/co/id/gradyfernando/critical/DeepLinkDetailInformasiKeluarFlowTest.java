package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.InformasiApiTest;
import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.model.Informasi;
import co.id.gradyfernando.model.enumeration.Jenis;
import co.id.gradyfernando.pageObjects.android.DaftarInformasiPage;
import co.id.gradyfernando.pageObjects.android.DetailinformasiPage;
import co.id.gradyfernando.pageObjects.android.HomePage;

public class DeepLinkDetailInformasiKeluarFlowTest extends BaseDeepLinkTest {

    private List<Informasi> InformasiList;
    private Informasi sampleInformasi;

    private final String _detailTitlePage = "Detail Informasi";
    
    @BeforeClass(alwaysRun = true)
    public void prepare() throws InterruptedException {
        // Get Data Informasi
        InformasiList = InformasiApiTest.getListInformasi(
            Jenis.KELUAR,
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
    public void test_intent_DetailInformasiKeluar() throws InterruptedException {
        String nomorInformasi = sampleInformasi.getNosurat();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Informasi Keluar");
        
        Thread.sleep(2000);

        DaftarInformasiPage daftarInformasiPage = new DaftarInformasiPage(driver);
        daftarInformasiPage.selectItemByNomor(nomorInformasi);

        DetailinformasiPage detailInformasiPage = new DetailinformasiPage(driver);
        detailInformasiPage.scrollToText(sampleInformasi.getNosurat());

        Assert.assertEquals(detailInformasiPage.getNomorSurat(), sampleInformasi.getNosurat(), "Nomor Surat Tidak Sesuai");

        // Check Title
        String titlePage = detailInformasiPage.getTitlePage("1");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        pressBackButton();
    }

    @Test
    public void test_deeplink_appscheme_DetailInformasiKeluar() throws InterruptedException {
        String InformasiId = sampleInformasi.getIdinformasi();

        // Scheme app 'weoffice'
        driver.get("weoffice://informasi/detail?id=" + InformasiId + "&tipe=out");

        DetailinformasiPage detailInformasiPage = new DetailinformasiPage(driver);
        detailInformasiPage.scrollToText(sampleInformasi.getNosurat());

        Assert.assertEquals(detailInformasiPage.getNomorSurat(), sampleInformasi.getNosurat(), "Nomor Surat Tidak Sesuai");

        // Check Title
        String titlePage = detailInformasiPage.getTitlePage("1");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        pressBackButton();
    }

    @Test
    public void test_deeplink_weburl_DetailInformasiKeluar() throws InterruptedException {
        String InformasiId = sampleInformasi.getIdinformasi();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/informasi/detail/" + InformasiId + "/K";
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailinformasiPage detailInformasiPage = new DetailinformasiPage(driver);
        detailInformasiPage.scrollToText(sampleInformasi.getNosurat());

        Assert.assertEquals(detailInformasiPage.getNomorSurat(), sampleInformasi.getNosurat(), "Nomor Surat Tidak Sesuai");

        // Check Title
        String titlePage = detailInformasiPage.getTitlePage("1");
        Assert.assertEquals(titlePage, _detailTitlePage, "Judul tidak sesuai");

        pressBackButton();
    }

}
