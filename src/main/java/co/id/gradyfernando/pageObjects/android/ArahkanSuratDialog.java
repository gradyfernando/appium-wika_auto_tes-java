package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ArahkanSuratDialog extends AndroidActions {

	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnArahkan")
    WebElement arahkanButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnPilihJabatan")
    WebElement pilihJabatanButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnPilihPersonel")
    WebElement pilihPersonalButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/etKeterangan")
    WebElement keteranganText;

    public ArahkanSuratDialog(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickSearchPersonal() {
        pilihPersonalButton.click();
    }

    public void clickSearchJabatan() {
        pilihJabatanButton.click();
    }

    public void setKeterangan(String keterangan) {
        keteranganText.sendKeys(keterangan);
    }

    public void clickArahkan() {
        arahkanButton.click();
    }
    
}
