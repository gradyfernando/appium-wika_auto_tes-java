package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PertimbanganSuratDialog extends AndroidActions {

	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnKirim")
    WebElement kirimButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnPilihJabatan")
    WebElement pilihJabatanButton;

    public PertimbanganSuratDialog(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickSearchUserJabatan() {
        pilihJabatanButton.click();
    }

    public void clickKirim() {
        kirimButton.click();
    }
    
    
}
