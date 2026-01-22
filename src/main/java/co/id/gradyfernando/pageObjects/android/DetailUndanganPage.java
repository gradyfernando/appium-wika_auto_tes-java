package co.id.gradyfernando.pageObjects.android;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DetailUndanganPage extends AndroidActions {

	@AndroidFindBy(id = "co.id.integra.weoffice:id/tvNoSurat")
	private WebElement noSuratLabel;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnKirimDisposisi")
	private WebElement disposisiButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnKirimInformasi")
	private WebElement informasiButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnApprove")
	private WebElement approveButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnTolak")
	private WebElement tolakButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnDiarahkan")
	private WebElement diarahkanButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnPertimbangan")
	private WebElement pertimbanganButton;

    public DetailUndanganPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setActivity(String undanganId, String tipe) {
        driver.get("weoffice://undangan/detail?id=" + undanganId + "&tipe=" + tipe);
    }

    public void openPageByDeepLinkWebUrl(String url) {
        driver.executeScript("mobile: shell", Map.of(
            "command", "am",
            "args", List.of(
                "start",
                "-a", "android.intent.action.VIEW",
                "-d", url,
                "co.id.integra.weoffice"
            )
        ));
    }

    public String getNomorSurat() {
        return noSuratLabel.getText();
    }

    public void clickDisposisikan() {
        disposisiButton.click();
    }

    public String getTitlePage(String tipe) {
        if (tipe == "0") {
            return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Detail Undangan Masuk\"]")).getText();
        } else {
            return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Detail Undangan Keluar\"]")).getText();
        }
    }

    public void clickInformasikan() {
        scrollToText("Informasikan");
        informasiButton.click();
    }
   
    public void clickApprove() {
        approveButton.click();
    }

    public void checkSekretarisButton() {
        scrollToText("Approve");
        Assert.assertTrue(approveButton.isDisplayed());

        scrollToText("Tolak");
        Assert.assertTrue(tolakButton.isDisplayed());

        scrollToText("Diarahkan");
        Assert.assertTrue(diarahkanButton.isDisplayed());

        scrollToText("Pertimbangan");
        Assert.assertTrue(pertimbanganButton.isDisplayed());
    }

    public void checkNoSekretarisButton() {
        Assert.assertThrows(NoSuchElementException.class, () -> {
            scrollToText("Pertimbangan");

            approveButton.isDisplayed();
            tolakButton.isDisplayed();
            diarahkanButton.isDisplayed();
            pertimbanganButton.isDisplayed();
        });
    }
    

}
