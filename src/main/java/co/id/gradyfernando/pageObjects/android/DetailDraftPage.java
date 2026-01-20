package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DetailDraftPage extends AndroidActions {

	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnSetuju")
	private WebElement setujuButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnTolak")
	private WebElement tolakButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnRevisi")
	private WebElement revisiButton;

    public DetailDraftPage(AndroidDriver driver, String draftId) {
        super(driver);
        setActivity(draftId);

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setActivity(String draftId) {
        driver.get("weoffice://draft/detail?id=" + draftId);
    }

    public String getTitlePage(String tipe) {
        return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Detail Draft\"]")).getText();
    }

}