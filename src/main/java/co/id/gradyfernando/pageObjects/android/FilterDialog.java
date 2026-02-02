package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FilterDialog extends AndroidActions {

    @AndroidFindBy(id = "co.id.integra.weoffice:id/etKeyword")
    WebElement keywordInputText;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/rvAttribute")
    WebElement rvAttribute;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/rvTampilSebagai")
    WebElement rvTampilSebagai;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnResetAttribute")
    WebElement btnResetAttribute;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/etTglMulai")
    WebElement etTglMulai;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/etTglAkhir")
    WebElement etTglAkhir;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnResetTgl")
    WebElement resetTglButton;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnReset")
    WebElement resetFilterButton;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/cbWaktuDibuat")
    WebElement waktuDibuatCheckbox;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnPencarian")
    WebElement pencarianButton;

    public FilterDialog(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void checkAttribute(String attribute) {
        scrollToText("Atribut Pencarian");

        WebElement attributElement = rvAttribute.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + attribute + "\")"));
        attributElement.click();
    }

    public void setKeyword(String keyword) {
        keywordInputText.sendKeys(keyword);
    }

    public void submitPencarian() {
        doScrollToEnd();
        pencarianButton.click();
    }
    
}
