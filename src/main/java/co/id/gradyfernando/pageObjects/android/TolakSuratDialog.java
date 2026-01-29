package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TolakSuratDialog extends AndroidActions {

    @AndroidFindBy(id = "co.id.integra.weoffice:id/tvTitle")
    WebElement titleText;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/imgClose")
    WebElement imgClose;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnKirim")
    WebElement kirimButton;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/etKeterangan")
    WebElement keteranganInput;

    public TolakSuratDialog(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setKeterangan(String keterangan) {
        keteranganInput.sendKeys(keterangan);
    }

    public String getTitle() {
        return titleText.getText();
    }

    public boolean isKirimButtonEnabled() {
        return kirimButton.isEnabled();
    }

    public String getSubmitButtonTitle() {
        return kirimButton.getText().toString();
    }

    public void clickKirim() {
        kirimButton.click();
    }
    
}
