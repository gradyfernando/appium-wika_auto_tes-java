package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DetailDraftPage extends AndroidActions {

    public DetailDraftPage(AndroidDriver driver, String draftId) {
        super(driver);
        setActivity(draftId);

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setActivity(String draftId) {
        driver.get("weoffice://draft/detail?id=" + draftId);
    }

}