package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class KirimInformasikanPage extends AndroidActions {

    @AndroidFindBy(id = "co.id.integra.weoffice:id/etCatatanInstruksi")
    private WebElement catatanInputText;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnDaftarPenerima")
	private WebElement pilihPenerimaButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnLanjut")
	private WebElement lanjutButton;

    public KirimInformasikanPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void inputCatatan(String catatan) {
        catatanInputText.sendKeys(catatan);
    }

    public void selectPenerima() {
        pilihPenerimaButton.click();
    }

    public void clickLanjut() {
        scrollToText("Lanjut");
        lanjutButton.click();
    }
    
    
}
