package co.id.gradyfernando.pageObjects.android;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
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

    public DetailSuratPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setActivity(String suratId) {
        driver.get("weoffice://surat/detail?id=" + suratId);
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

}
