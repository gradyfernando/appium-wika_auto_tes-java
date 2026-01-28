package co.id.gradyfernando.android;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import co.id.gradyfernando.pageObjects.android.DetailUndanganPage;
import co.id.gradyfernando.pageObjects.android.HakAksesPage;
import co.id.gradyfernando.pageObjects.android.HomePage;
import co.id.gradyfernando.pageObjects.android.LoginPage;
import co.id.gradyfernando.pageObjects.android.UndanganPage;
import co.id.gradyfernando.testUtils.AndroidBaseTest;

public class LoginTest extends AndroidBaseTest {

    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void preSetup() {
        loginPage = new LoginPage(driver);
    	loginPage.setActivity();
    }

    @Test(dataProvider = "getData")    
    public void Test_Login_Success(HashMap<String, String> input) throws InterruptedException {
        loginPage.setUsernamePassword(input.get("username"), input.get("password"));
        loginPage.clickLogin();
    }

    @Test(groups = {"smoke"}) 
    public void test_loginThenLogout() throws InterruptedException {
        loginPage.setUsernamePassword("TK171561", "TK171561");
        loginPage.clickLogin();

        Thread.sleep(2000);

        HakAksesPage hakAksesPage = new HakAksesPage(driver);
        hakAksesPage.clickLogout();
    }

    @Test
    public void test_loginFailedShowMessage() throws InterruptedException {
        loginPage.setUsernamePassword("asdf", "fdsa");
        loginPage.clickLogin();

        Thread.sleep(2000);

        Assert.assertNotNull(driver.findElement(By.id("co.id.integra.weoffice:id/tvTitleHint")));
    }

    @Test
    public void test_viewSuratMasukSekretaris_Success() throws InterruptedException {
        loginPage.setUsernamePassword("TK171561", "TK171561");
        loginPage.clickLogin();

        Thread.sleep(2000);

        HakAksesPage hakAksesPage = new HakAksesPage(driver);
        hakAksesPage.setRole("sekretaris");

        Thread.sleep(2000);

        HomePage homePage = new HomePage(driver);
        homePage.openProfileMenu();
        homePage.selectMenuFromAllMenu("Undangan Masuk");
        
        Thread.sleep(2000);

        UndanganPage undanganPage = new UndanganPage(driver);
        undanganPage.selectItemWithStatus("Butuh Approval");

        Thread.sleep(2000);

        DetailUndanganPage detailUndanganPage = new DetailUndanganPage(driver);
        detailUndanganPage.scrollToText("Approve");
        detailUndanganPage.clickApprove();

        Thread.sleep(2000);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "/src/test/java/co/id/gradyfernando/testData/LoginData.json");
        return new Object[][] {
            {data.get(0)},
            {data.get(1)}
        };
    }
    
}
