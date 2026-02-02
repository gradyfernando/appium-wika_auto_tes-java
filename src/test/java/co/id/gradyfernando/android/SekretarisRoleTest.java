package co.id.gradyfernando.android;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.id.gradyfernando.pageObjects.android.AdvanceFilterDialog;
import co.id.gradyfernando.pageObjects.android.ArahkanSuratDialog;
import co.id.gradyfernando.pageObjects.android.DaftarSuratPage;
import co.id.gradyfernando.pageObjects.android.DaftarUndanganPage;
import co.id.gradyfernando.pageObjects.android.DatePickerDialog;
import co.id.gradyfernando.pageObjects.android.DetailSuratPage;
import co.id.gradyfernando.pageObjects.android.DetailUndanganPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.pageObjects.android.KirimDisposisiPage;
import co.id.gradyfernando.pageObjects.android.KirimInformasikanPage;
import co.id.gradyfernando.pageObjects.android.PencarianUserDialog;
import co.id.gradyfernando.pageObjects.android.PertimbanganSuratDialog;
import co.id.gradyfernando.pageObjects.android.PilihUserPage;
import co.id.gradyfernando.pageObjects.android.TolakSuratDialog;
import co.id.gradyfernando.pageObjects.android.basePageObject.PemilihanDialog;
import co.id.gradyfernando.testUtils.AndroidBaseTest;

public class SekretarisRoleTest extends AndroidBaseTest {

    HomePage homePage;

    @BeforeClass
    public void login() throws InterruptedException {
        // Data to input
        var username = "ES951927";
        var password = "ES951927";
		var selectedRole = "direktur utama";

        injectLoginAndRole(username, password, selectedRole);

        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void openHomePage() throws InterruptedException {
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
    }

    @Test(groups = {"smoke"})
    public void test_checkAvailableButton() throws InterruptedException {
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("butuh approval");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.checkSekretarisButton();
    }

    @Test(groups = {"smoke"})
    public void test_checkUnavailableButton() throws InterruptedException {
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("dibaca");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.checkNoSekretarisButton();
    }

    @Test(groups = {"transaksi", "sekretaris"})
    public void test_pertimbangkanSurat() throws InterruptedException {
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("butuh approval");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.clickPertimbangkan();

        PertimbanganSuratDialog pertimbanganSuratDialog = new PertimbanganSuratDialog(driver);
        pertimbanganSuratDialog.clickSearchUserJabatan();

        PencarianUserDialog pencarianUserDialog = new PencarianUserDialog(driver);
        pencarianUserDialog.searchUser("AGUNG BUDI WASKITO");
        
        Thread.sleep(500);
        pertimbanganSuratDialog.clickKirim();
    }

    @Test(groups = {"transaksi", "sekretaris", "searchuser"})
    public void test_diarahkan() throws InterruptedException {
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("butuh approval");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.clickDiarahkan();

        ArahkanSuratDialog arahkanSuratDialog = new ArahkanSuratDialog(driver);
        arahkanSuratDialog.clickSearchJabatan();

        PencarianUserDialog pencarianUserDialog = new PencarianUserDialog(driver);
        pencarianUserDialog.searchUser("AGUNG BUDI WASKITO");

        arahkanSuratDialog.setKeterangan("Testing Automaton");
        
        Thread.sleep(500);
        arahkanSuratDialog.clickArahkan();
    }

    @Test(groups = {"transaksi", "sekreatris"})
    public void test_tolakSurat() throws InterruptedException {
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("butuh approval");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.clickTolak();

        TolakSuratDialog tolakSuratDialog = new TolakSuratDialog(driver);
        String titleDialog = tolakSuratDialog.getTitle();
        Assert.assertTrue(titleDialog.contains("Tolak"));

        String buttonText = tolakSuratDialog.getSubmitButtonTitle();
        Assert.assertTrue(buttonText.contains("Tolak"));

        Assert.assertFalse(tolakSuratDialog.isKirimButtonEnabled());
        tolakSuratDialog.setKeterangan("Undangan ditolak - AutoTest");
        Assert.assertTrue(tolakSuratDialog.isKirimButtonEnabled());
    }

    @Test(groups = {"transaksi", "searchuser"})
    public void test_informasikan_UndanganMasuk() throws InterruptedException {
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("butuh approval");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.clickInformasikan();

        KirimInformasikanPage kirimInformasikanPage = new KirimInformasikanPage(driver);
        kirimInformasikanPage.selectPenerima();

        PilihUserPage pilihUserPage = new PilihUserPage(driver);
        pilihUserPage.clickSearchUserButton();
        
        PencarianUserDialog pencarianUserDialog = new PencarianUserDialog(driver);
        pencarianUserDialog.searchUser("Admin System WE OFFICE");
        pencarianUserDialog.searchUser("Superadmin");
        pencarianUserDialog.closePage();

        pilihUserPage.verifyUserAfterSearch(pencarianUserDialog.getSelectedNamaUser());
        pilihUserPage.clickSimpanButton();
        
        kirimInformasikanPage.inputCatatan("Uji coba otomatis");
        kirimInformasikanPage.clickLanjut();
    }

    @Test(groups = {"transaksi", "searchuser"})
    public void test_disposisikan_Surat() throws InterruptedException {
        homePage.selectMenuFromAllMenu("Surat Masuk");

        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        daftarSuratPage.clickAdvanceFilter("Status Surat");

        AdvanceFilterDialog advanceFilterDialog = new AdvanceFilterDialog(driver);
        advanceFilterDialog.selectFilter("Belum Ditindaklanjuti");

        daftarSuratPage.selectItemWithStatus("Dibaca");
        
        DetailSuratPage detailSuratPage = new DetailSuratPage(driver);
        detailSuratPage.clickDisposisikan();

        KirimDisposisiPage kirimDisposisiPage = new KirimDisposisiPage(driver);
        kirimDisposisiPage.clickSetTanggal();
        
        DatePickerDialog datePickerDialog = new DatePickerDialog(driver);
        datePickerDialog.selectDate("1");
        datePickerDialog.clickOK();

        // Pilih Penerima
        kirimDisposisiPage.selectPenerima();
        
        PilihUserPage pilihUserPage = new PilihUserPage(driver);
        pilihUserPage.selectUserByIndex(0);
        pilihUserPage.selectUserByIndex(1);
        pilihUserPage.clickSimpanButton();

        PemilihanDialog pilihanDialog = new PemilihanDialog(driver);
        // Pilih Instruksi
        kirimDisposisiPage.clickInstruksi();
        pilihanDialog.selectItemWithText("Lihat Catatan Instruksi");
        pilihanDialog.clickSimpan();

        // Pilih Sifat
        kirimDisposisiPage.clickSifat();
        pilihanDialog.selectItemWithText("Biasa");
        pilihanDialog.clickSimpan();

        kirimDisposisiPage.inputCatatan("Mohon dikonfirmasi - AutoT");
        kirimDisposisiPage.clickLanjut();

        Thread.sleep(2000);
    }
    
}
