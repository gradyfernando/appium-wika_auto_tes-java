package co.id.gradyfernando.android;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.id.gradyfernando.api.LoginApiTest;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.pageObjects.android.DaftarUndanganPage;
import co.id.gradyfernando.pageObjects.android.DetailUndanganPage;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.pageObjects.android.KirimInformasikanPage;
import co.id.gradyfernando.pageObjects.android.PencarianUserDialog;
import co.id.gradyfernando.pageObjects.android.PilihUserPage;
import co.id.gradyfernando.report.ExtentLogger;
import co.id.gradyfernando.testUtils.AndroidBaseTest;
import co.id.gradyfernando.utils.SessionInjector;

public class SekretarisRoleTest extends AndroidBaseTest {

    private User user;

    @BeforeClass
    public void loginSekretaris() throws InterruptedException {
        // Get data first
		user = LoginApiTest.login(
			"TK171561",
			"TK171561",
			"A.1.D", 
			"2.2.0"
		);

        ExtentLogger.info("Login: TK171561");
		
		// Set Token
		SessionInjector.injectToken(driver, user.getToken());

        Thread.sleep(2000);

        HakAksesPage hakAksesPage = new HakAksesPage(driver);
        hakAksesPage.setRole("sekretaris");

        Thread.sleep(2000);
    }

    @Test()
    public void test_checkAvailableButton() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Undangan Masuk");

        DaftarUndanganPage daftarUndanganPage = new DaftarUndanganPage(driver);
        daftarUndanganPage.selectItemWithStatus("butuh approval");

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        ExtentLogger.info("Detail Undangan: " + detailUndanganPage.getNomorSurat());
        detailUndanganPage.checkSekretarisButton();

        backButton();
    }

    @Test()
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

        backButton();
    }

    @Test(groups = {"transaksi", "searchuser"})
    public void test_informasikan_UndanganMasuk() throws InterruptedException {
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
