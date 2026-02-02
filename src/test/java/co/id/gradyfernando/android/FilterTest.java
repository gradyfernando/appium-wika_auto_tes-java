package co.id.gradyfernando.android;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.id.gradyfernando.pageObjects.android.AdvanceFilterDialog;
import co.id.gradyfernando.pageObjects.android.DaftarSuratPage;
import co.id.gradyfernando.pageObjects.android.FilterDialog;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;

public class FilterTest extends AndroidBaseTest {
    
    @BeforeClass
    public void login() throws InterruptedException {
        // Get data first
        var username = "TK171561";
        var password = "TK171561";
		var selectedRole = "sekretaris";

        injectLoginAndRole(username, password, selectedRole);
    }

    @BeforeMethod
    public void openSuratPage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.setActivity();
        Thread.sleep(1000);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Surat Masuk");

    }

    @Test
    public void test_filterSuratTolak() throws InterruptedException {
        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        daftarSuratPage.clickAdvanceFilter("Status Surat");

        AdvanceFilterDialog advanceFilterDialog = new AdvanceFilterDialog(driver);
        advanceFilterDialog.selectFilter("Ditolak");
    }

    @Test
    public void test_filterSurat_Belumditindaklanjuti() throws InterruptedException {
        String selectedStatus = "Belum Ditindaklanjuti";

        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        daftarSuratPage.clickAdvanceFilter("Status Surat");

        AdvanceFilterDialog advanceFilterDialog = new AdvanceFilterDialog(driver);
        advanceFilterDialog.selectFilter(selectedStatus);

        daftarSuratPage.selectItemWithStatus(selectedStatus);
    }

    @Test
    public void test_filterSurat_noSurat() throws InterruptedException {
        DaftarSuratPage daftarSuratPage = new DaftarSuratPage(driver);
        daftarSuratPage.clickSearch();

        FilterDialog filterDialog = new FilterDialog(driver);
        filterDialog.checkAttribute("No Surat");
        filterDialog.setKeyword("SE.01.00");
        filterDialog.submitPencarian();
        
        
    }


    
}
