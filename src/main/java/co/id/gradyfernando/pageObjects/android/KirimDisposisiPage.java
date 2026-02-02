package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class KirimDisposisiPage extends AndroidActions {

    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnAction")
    WebElement pilihTanggalButton;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/etCatatanInstruksi")
    private WebElement catatanInputText;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnDaftarPenerima")
	private WebElement pilihPenerimaButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnDaftarInstruksi")
	private WebElement pilihInstruksiButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnDaftarSifat")
	private WebElement pilihSifatButton;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/cbIsPublic")
    WebElement cbIsPublic;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnLanjut")
	private WebElement lanjutButton;

    public KirimDisposisiPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void inputCatatan(String catatan) {
        catatanInputText.sendKeys(catatan);
    }

    public void clickSetTanggal() {
        scrollToText("Tanggal Surat");
        pilihTanggalButton.click();
    }

    public void selectPenerima() {
        scrollToText("Penerima");
        pilihPenerimaButton.click();
    }

    public void clickInstruksi() {
        scrollToText("Instruksi");
        pilihInstruksiButton.click();
    }

    public void clickSifat() {
        scrollToText("Sifat");
        pilihSifatButton.click();
    }

    public void clickLanjut() {
        scrollToText("Lanjut");
        lanjutButton.click();
    }
    
}
