package co.id.gradyfernando.pageObjects.android;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DetailSuratPage extends AndroidActions {

	@AndroidFindBy(id = "co.id.integra.weoffice:id/tvNoSurat")
	private WebElement noSuratLabel;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnKirimDisposisi")
	private WebElement disposisiButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnKirimInformasi")
	private WebElement informasiButton;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/cvPlaceholder")
    private WebElement loadingView;

    public DetailSuratPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setActivity(String suratId, String tipe) {
        driver.get("weoffice://surat/detail?id=" + suratId + "&tipe=" + tipe);
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
        scrollToText("Kirim Disposisi");
        disposisiButton.click();
    }

    public String getTitlePage(String tipe) {
        if (tipe == "0") {
            return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Detail Surat Masuk\"]")).getText();
        } else {
            return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Detail Surat Keluar\"]")).getText();
        }
    }

}
