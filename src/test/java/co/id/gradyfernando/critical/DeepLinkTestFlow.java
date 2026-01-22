package co.id.gradyfernando.critical;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.DisposisiApiTest;
import co.id.gradyfernando.api.DraftApiTest;
import co.id.gradyfernando.api.SuratApiTest;
import co.id.gradyfernando.config.ApiConfig;
import co.id.gradyfernando.model.Disposisi;
import co.id.gradyfernando.model.Draft;
import co.id.gradyfernando.model.Surat;
import co.id.gradyfernando.model.enumeration.Jenis;
import co.id.gradyfernando.pageObjects.android.DaftarDisposisiPage;
import co.id.gradyfernando.pageObjects.android.DaftarDraftPage;
import co.id.gradyfernando.pageObjects.android.DaftarUndanganPage;
import co.id.gradyfernando.pageObjects.android.DetailDisposisiPage;
import co.id.gradyfernando.pageObjects.android.DetailDraftPage;
import co.id.gradyfernando.pageObjects.android.DetailSuratPage;
import co.id.gradyfernando.pageObjects.android.DetailUndanganPage;
import co.id.gradyfernando.pageObjects.android.HomePage;

public class DeepLinkTestFlow extends BaseDeepLinkTest {
    
    private List<Disposisi> disposisiList;
    private Disposisi sampleDisposisi;

    private List<Surat> undanganList;
    private Surat sampleUndangan;

    private List<Draft> draftList;
    private Draft sampleDraft;

    private final String _limitData = "1";
       
    @Test(groups = {"apitest"})
    public void prepareDisposisiKeluar() throws InterruptedException {
        // Get Data Disposisi
        disposisiList = DisposisiApiTest.getListDisposisi(
            Jenis.KELUAR,
            user.getToken(), 
            "0", 
            _limitData, 
            "0", 
            new HashMap<>()
        );

        // Ensure list are not empty and select it
        Assert.assertTrue(disposisiList.size() > 0);
        sampleDisposisi = disposisiList.get(0);
    }

    @Test(groups = {"apitest"})
    public void prepareDisposisiMasuk() throws InterruptedException {
        // Get Data Disposisi
        disposisiList = DisposisiApiTest.getListDisposisi(
            Jenis.MASUK,
            user.getToken(), 
            "0", 
            _limitData, 
            "0", 
            new HashMap<>()
        );

        // Ensure list are not empty and select it
        Assert.assertTrue(disposisiList.size() > 0);
        sampleDisposisi = disposisiList.get(0);
    }

    @Test(groups = {"apitest"})
    public void prepareUndanganMasuk() throws InterruptedException {
        // Get Data Surat
        undanganList = SuratApiTest.getListUndangan(
            Jenis.MASUK,
            user.getToken(), 
            "0", 
            _limitData, 
            "0", 
            new HashMap<>()
        );

        // Ensure list are not empty and select it
        Assert.assertTrue(undanganList.size() > 0);
        sampleUndangan = undanganList.get(0);
    }

    @Test(groups = {"apitest"})
    public void prepareUndanganKeluar() throws InterruptedException {
        // Get Data Surat
        undanganList = SuratApiTest.getListUndangan(
            Jenis.KELUAR,
            user.getToken(), 
            "0", 
            _limitData, 
            "0", 
            new HashMap<>()
        );

        // Ensure list are not empty and select it
        Assert.assertTrue(undanganList.size() > 0);
        sampleUndangan = undanganList.get(0);
    }

    @Test(groups = {"apitest"})
    public void prepareDraft() throws InterruptedException {
        // Get Data Surat
        draftList = DraftApiTest.getListDraft(
            user.getToken(), 
            "0", 
            _limitData, 
            new HashMap<>()
        );

        // Ensure list are not empty and select it
        Assert.assertTrue(draftList.size() > 0);
        sampleDraft = draftList.get(0);
    }

    @Test(dependsOnMethods = {"prepareDraft"})
    public void test_intent_DetailDraft() throws InterruptedException {
        // String kodeDisposisi = sampleDraft.getKodedisposisi();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Draft Surat / Undangan Digital");
        
        Thread.sleep(2000);

        DaftarDraftPage daftarDraftPage = new DaftarDraftPage(driver);
        // daftarDraftPage.selectItemByKodeDisposisi(kodeDisposisi);

        DetailDraftPage detailDraftPage = new DetailDraftPage(driver);
        // detailDraftPage.scrollToText(sampleDraft.getKodedisposisi());

        // Assert.assertEquals(detailDraftPage.getKodeDisposisi(), sampleDraft.getKodedisposisi());

        // Check Title
        String titlePage = detailDraftPage.getTitlePage();
        Assert.assertEquals(titlePage, "Detail Draft", "Judul tidak sesuai");

        pressBackButton();
        pressBackButton();
    }

    
    @Test(dependsOnMethods={"prepareDraft"})
    public void test_deeplink_appscheme_Draft() throws InterruptedException {
        String draftId = sampleDraft.getIddraft();

        // Scheme app 'weoffice'
        driver.get("weoffice://draft/detail?id=" + draftId);

        DetailDraftPage detailDraftPage = new DetailDraftPage(driver);
        // detailDraftPage.scrollToText(sampleDraft.getKodedisposisi());

        // Assert.assertEquals(detailDraftPage.getKodeDisposisi(), sampleDraft.getKodedisposisi(), "Kode disposisi tidak sesuai");

        // Check Title
        String titlePage = detailDraftPage.getTitlePage();
        Assert.assertEquals(titlePage, "Detail Draft", "Judul tidak sesuai");

        pressBackButton();
    }

    @Test(dependsOnMethods={"prepareDraft"})
    public void test_deeplink_weburl_DetailDraft() throws InterruptedException {
        String draftId = sampleDraft.getIddraft();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/draft/detail/" + draftId;
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailDraftPage detailDraftPage = new DetailDraftPage(driver);
        // detailDraftPage.scrollToText(sampleDraft.getKodedisposisi());

        // Assert.assertEquals(detailDraftPage.getKodeDisposisi(), sampleDraft.getKodedisposisi(), "Kode Disposisi Tidak Sesuai");

        // Check Title
        String titlePage = detailDraftPage.getTitlePage();
        Assert.assertEquals(titlePage, "Detail Draft", "Judul tidak sesuai");

        pressBackButton();
    }

    @Test(dependsOnMethods={"prepareDisposisiKeluar"})
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
        Assert.assertEquals(titlePage, "Detail Disposisi", "Judul tidak sesuai");

        pressBackButton();
        pressBackButton();
    }

    @Test(dependsOnMethods={"prepareDisposisiKeluar"})
    public void test_deeplink_appscheme_DetailDisposisiKeluar() throws InterruptedException {
        String disposisiId = sampleDisposisi.getIddisposisi();

        // Scheme app 'weoffice'
        driver.get("weoffice://disposisi/detail?id=" + disposisiId + "&tipe=out");

        DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
        detailDisposisiPage.scrollToText(sampleDisposisi.getKodedisposisi());

        Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getKodedisposisi(), "Kode disposisi tidak sesuai");

        // Check Title
        String titlePage = detailDisposisiPage.getTitlePage("1");
        Assert.assertEquals(titlePage, "Detail Disposisi", "Judul tidak sesuai");

        pressBackButton();
    }

    @Test(dependsOnMethods={"prepareDisposisiKeluar"})
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
        Assert.assertEquals(titlePage, "Detail Disposisi", "Judul tidak sesuai");

        pressBackButton();
    }

    @Test(dependsOnMethods={"prepareDisposisiMasuk"})
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
        Assert.assertEquals(titlePage, "Detail Disposisi", "Judul tidak sesuai");

        pressBackButton();
    }

    @Test(dependsOnMethods={"prepareDisposisiMasuk"})
    public void test_deeplink_appscheme_DetailDisposisiMasuk() throws InterruptedException {
        if (disposisiList.size() > 0) {
            String disposisiId = sampleDisposisi.getIddisposisi();

            // Scheme app 'weoffice'
            driver.get("weoffice://disposisi/detail?id=" + disposisiId + "&tipe=in");

            DetailDisposisiPage detailDisposisiPage = new DetailDisposisiPage(driver);
            detailDisposisiPage.scrollToText(sampleDisposisi.getKodedisposisi());

            Assert.assertEquals(detailDisposisiPage.getKodeDisposisi(), sampleDisposisi.getKodedisposisi());

            // Check Title
            String titlePage = detailDisposisiPage.getTitlePage("0");
            Assert.assertEquals(titlePage, "Detail Disposisi", "Judul tidak sesuai");

            pressBackButton();
            pressBackButton();
        } else {
            throw new SkipException("Tidak ada data dari API: getlistdisposisimasuk");
        }
        
    }

    @Test(dependsOnMethods={"prepareDisposisiMasuk"})
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
        Assert.assertEquals(titlePage, "Detail Disposisi", "Judul tidak sesuai");

        pressBackButton();
    }

    @Test(dependsOnMethods = {"prepareUndanganMasuk"})
    public void test_intent_DetailUndanganMasuk() throws InterruptedException {
        String nomorSurat = sampleUndangan.getNosurat();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Undangan Masuk");
        
        Thread.sleep(2000);

        DaftarUndanganPage daftarSuratPage = new DaftarUndanganPage(driver);
        daftarSuratPage.selectItemByNomor(nomorSurat);

        DetailUndanganPage detailSuratPage = new DetailUndanganPage(driver);
        detailSuratPage.scrollToText(sampleUndangan.getNosurat());

        // Check No Surat
        Assert.assertEquals(detailSuratPage.getNomorSurat(), nomorSurat, "Nomor surat tidak sesuai dengan sample");

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("0");
        Assert.assertEquals(titlePage, "Detail Undangan Masuk", "Judul tidak sesuai");

        pressBackButton();
        pressBackButton();
    }

    @Test(dependsOnMethods = {"prepareUndanganMasuk"})
    public void test_deeplink_appscheme_DetailUndanganMasuk() throws InterruptedException {
        String suratId = sampleUndangan.getIdsurat();

        // Scheme app 'weoffice'
        driver.get("weoffice://undangan/detail?id=" + suratId + "&tipe=0");

        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.scrollToText(sampleUndangan.getNosurat());

        Assert.assertEquals(detailSuratPage.getNomorSurat(), sampleUndangan.getNosurat());

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("0");
        Assert.assertEquals(titlePage, "Detail Undangan Masuk", "Judul tidak sesuai");

        pressBackButton();
    }

    @Test(dependsOnMethods = {"prepareUndanganMasuk"})
    public void test_deeplink_weburl_DetailUndanganMasuk() throws InterruptedException {
        String suratId = sampleUndangan.getIdsurat();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/undangan/detailundangan/jenis/in/of/0/id/" + suratId;
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.scrollToText(sampleUndangan.getNosurat());

        Assert.assertEquals(detailSuratPage.getNomorSurat(), sampleUndangan.getNosurat());

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("0");
        Assert.assertEquals(titlePage, "Detail Undangan Masuk", "Judul tidak sesuai");
        
        pressBackButton();
    }

    @Test(dependsOnMethods = {"prepareUndanganKeluar"})
    public void test_intent_DetailUndanganKeluar() throws InterruptedException {
        String nomorSurat = sampleUndangan.getNosurat();
        
        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Undangan Keluar (ttd basah)");
        
        Thread.sleep(2000);

        DaftarUndanganPage daftarSuratPage = new DaftarUndanganPage(driver);
        daftarSuratPage.selectItemByNomor(nomorSurat);

        DetailUndanganPage detailSuratPage = new DetailUndanganPage(driver);
        detailSuratPage.scrollToText(sampleUndangan.getNosurat());

        // Check No Surat
        Assert.assertEquals(detailSuratPage.getNomorSurat(), nomorSurat, "Nomor surat tidak sesuai dengan sample");

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("1");
        Assert.assertEquals(titlePage, "Detail Undangan Keluar", "Judul tidak sesuai");

        pressBackButton();
        pressBackButton();
    }

    @Test(dependsOnMethods = {"prepareUndanganKeluar"})
    public void test_deeplink_appscheme_DetailUndanganKeluar() throws InterruptedException {
        String suratId = sampleUndangan.getIdsurat();

        // Scheme app 'weoffice'
        driver.get("weoffice://undangan/detail?id=" + suratId + "&tipe=1");

        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.scrollToText(sampleUndangan.getNosurat());

        Assert.assertEquals(detailSuratPage.getNomorSurat(), sampleUndangan.getNosurat());

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("1");
        Assert.assertEquals(titlePage, "Detail Undangan Keluar", "Judul tidak sesuai");

        pressBackButton();
    }

    @Test(dependsOnMethods = {"prepareUndanganKeluar"})
    public void test_deeplink_weburl_DetailUndanganKeluar() throws InterruptedException {
        String suratId = sampleUndangan.getIdsurat();

        // Scheme url 'weoffice'
        String webUrl = ApiConfig.BASE_URL + "/undangan/detailundangan/jenis/out/of/0/id/" + suratId;
        System.err.println(webUrl);
        driver.get(webUrl);
        
        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.scrollToText(sampleUndangan.getNosurat());

        Assert.assertEquals(detailSuratPage.getNomorSurat(), sampleUndangan.getNosurat());

        // Check Title
        String titlePage = detailSuratPage.getTitlePage("1");
        Assert.assertEquals(titlePage, "Detail Undangan Keluar", "Judul tidak sesuai");
        
        pressBackButton();
    }

}
