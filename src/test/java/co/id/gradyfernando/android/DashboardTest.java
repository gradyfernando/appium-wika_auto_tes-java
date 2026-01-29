package co.id.gradyfernando.android;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.id.gradyfernando.pageObjects.android.DaftarDisposisiPage;
import co.id.gradyfernando.pageObjects.android.DaftarDraftPage;
import co.id.gradyfernando.pageObjects.android.DaftarInformasiPage;
import co.id.gradyfernando.pageObjects.android.DaftarSuratPage;
import co.id.gradyfernando.pageObjects.android.DaftarUndanganPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.report.ExtentLogger;
import co.id.gradyfernando.testUtils.AndroidBaseTest;

public class DashboardTest extends AndroidBaseTest {
    
    private HomePage homePage;
    
    @BeforeClass
    public void login() throws InterruptedException {
        var username = "TK171561";
        var password = "TK171561";
		var selectedRole = "sekretaris";

        injectLoginAndRole(username, password, selectedRole);

        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void setToHomePage() throws InterruptedException {
        homePage.setActivity();
        Thread.sleep(500);
    }

    //
    // Belum Dibaca
    //
    @Test(groups = {"smoke"})
    public void test_numberNotification_unRead_Surat() throws InterruptedException {
        var unreadSuratCount = homePage.selectItemDashboard("Belum Dibaca", "Surat");
        ExtentLogger.info("Section: Belum Dibaca - Surat");

        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        Assert.assertEquals(daftarSuratPage.checkAdvanceFilterValue("Status Surat"), "Belum dibaca".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unreadSuratCount), daftarSuratPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_unRead_Undangan() throws InterruptedException {
        var unreadUndanganCount = homePage.selectItemDashboard("Belum Dibaca", "Undangan");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        Assert.assertEquals(daftarUndanganPage.checkAdvanceFilterValue("Status Surat"), "Belum dibaca".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unreadUndanganCount), daftarUndanganPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_unRead_Disposisi() throws InterruptedException {
        var unreadDisposisiCount = homePage.selectItemDashboard("Belum Dibaca", "Disposisi");

        DaftarDisposisiPage daftarDisposisiPage = new DaftarDisposisiPage(driver);
        Assert.assertEquals(daftarDisposisiPage.checkAdvanceFilterValue("Status Disposisi"), "Belum dibaca".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unreadDisposisiCount), daftarDisposisiPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_unRead_Informasi() throws InterruptedException {
        var unreadInformasiCount = homePage.selectItemDashboard("Belum Dibaca", "Infomasi");

        DaftarInformasiPage daftarInformasiPage = new DaftarInformasiPage(driver);
        Assert.assertEquals(daftarInformasiPage.checkAdvanceFilterValue("Status Informasi"), "Belum dibaca".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unreadInformasiCount), daftarInformasiPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    //
    // Belum ditindaklanjuti
    //

    @Test(groups = {"smoke"})
    public void test_numberNotification_unprocessed_Surat() throws InterruptedException {
        var unreadSuratCount = homePage.selectItemDashboard("Belum Ditindaklanjuti", "Surat");

        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        Assert.assertEquals(daftarSuratPage.checkAdvanceFilterValue("Status Surat"), "Belum ditindaklanjuti".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unreadSuratCount), daftarSuratPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_unprocessed_Undangan() throws InterruptedException {
        var unreadUndanganCount = homePage.selectItemDashboard("Belum Ditindaklanjuti", "Undangan");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        Assert.assertEquals(daftarUndanganPage.checkAdvanceFilterValue("Status Surat"), "Belum ditindaklanjuti".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unreadUndanganCount), daftarUndanganPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_unprocessed_Disposisi() throws InterruptedException {
        var unreadDisposisiCount = homePage.selectItemDashboard("Belum Ditindaklanjuti", "Disposisi");

        DaftarDisposisiPage daftarDisposisiPage = new DaftarDisposisiPage(driver);
        Assert.assertEquals(daftarDisposisiPage.checkAdvanceFilterValue("Status Disposisi"), "Belum ditindaklanjuti".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unreadDisposisiCount), daftarDisposisiPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_unprocessed_Informasi() throws InterruptedException {
        var unreadInformasiCount = homePage.selectItemDashboard("Belum Ditindaklanjuti", "Informasi");

        DaftarInformasiPage daftarInformasiPage = new DaftarInformasiPage(driver);
        Assert.assertEquals(daftarInformasiPage.checkAdvanceFilterValue("Status Informasi"), "Belum ditindaklanjuti".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unreadInformasiCount), daftarInformasiPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    // 
    // Proses & Agenda
    //
    @Test(groups = {"smoke"})
    public void test_numberNotification_ProsesAgenda_Draft() throws InterruptedException {
        var unProcessedCount = homePage.selectItemDashboard("Proses & Agenda", "Draft (Butuh Proses)");

        DaftarDraftPage daftarDraftPage = new DaftarDraftPage(driver);
        Assert.assertEquals(daftarDraftPage.checkAdvanceFilterValue("Status Draft"), "Butuh Proses Saya".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unProcessedCount), daftarDraftPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_ProsesAgenda_Surat() throws InterruptedException {
        var unsentCount = homePage.selectItemDashboard("Proses & Agenda", "Surat (Belum Dikirim)");

        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        Assert.assertEquals(daftarSuratPage.checkAdvanceFilterValue("Status Surat"), "Belum Dikirim / Final".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unsentCount), daftarSuratPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_ProsesAgenda_Undangan() throws InterruptedException {
        var unsentCount = homePage.selectItemDashboard("Proses & Agenda", "Undangan (Belum Dikirim)");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        Assert.assertEquals(daftarUndanganPage.checkAdvanceFilterValue("Status Surat"), "Belum Dikirim / Final".toLowerCase(), "Filter tidak sesuai");
        Assert.assertEquals(Integer.parseInt(unsentCount), daftarUndanganPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_numberNotification_ProsesAgenda_Agenda() throws InterruptedException {
        var todayAgendaCount = homePage.selectItemDashboard("Proses & Agenda", "Agenda (Hari ini)");

        DaftarDraftPage daftarDraftPage = new DaftarDraftPage(driver);
        Assert.assertEquals(Integer.parseInt(todayAgendaCount), daftarDraftPage.countData(), "Jumlah notifikasi tidak sesuai");
    }

    @Test(groups = {"smoke"})
    public void test_notificationBadge() throws InterruptedException {
        Thread.sleep(500);
        homePage.openNotificationMenu();
        var badgeCount = homePage.getNotificationBadgeNumber();
        var childCount = homePage.getNotificationListChild();

        Assert.assertEquals(badgeCount, childCount, "Jumlah badge dan jumlah notification tidak sesuai");
    }

}
