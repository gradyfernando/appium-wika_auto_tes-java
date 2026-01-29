package co.id.gradyfernando.android;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.pageObjects.android.ArahkanSuratDialog;
import co.id.gradyfernando.pageObjects.android.DaftarUndanganPage;
import co.id.gradyfernando.pageObjects.android.DetailUndanganPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.pageObjects.android.KirimInformasikanPage;
import co.id.gradyfernando.pageObjects.android.PencarianUserDialog;
import co.id.gradyfernando.pageObjects.android.PertimbanganSuratDialog;
import co.id.gradyfernando.pageObjects.android.PilihUserPage;
import co.id.gradyfernando.pageObjects.android.TolakSuratDialog;
import co.id.gradyfernando.testUtils.AndroidBaseTest;

public class SekretarisRoleTest extends AndroidBaseTest {

    @BeforeClass
    public void login() throws InterruptedException {
        // Data to input
        var username = "TK171561";
        var password = "TK171561";
		var selectedRole = "sekretaris";

        injectLoginAndRole(username, password, selectedRole);
    }

    @Test(groups = {"smoke"})
    public void test_checkAvailableButton() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("butuh approval");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.checkSekretarisButton();
    }

    @Test(groups = {"smoke"})
    public void test_checkUnavailableButton() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("dibaca");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.checkNoSekretarisButton();
    }

    @Test(groups = {"transaksi", "sekretaris"})
    public void test_pertimbangkanSurat() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
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
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
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
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
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
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
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

    
}
