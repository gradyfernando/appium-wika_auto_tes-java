package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DetailUndanganPage extends AndroidActions {

	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnApprove")
	private WebElement approveButton;

    public DetailUndanganPage(AndroidDriver driver, String undanganId) {
        super(driver);
        setActivity(undanganId);

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setActivity(String undanganId) {
        driver.get("weoffice://undangan/detail?id=" + undanganId);
    }

    public void clickApprove() {
        approveButton.click();
    }

}
