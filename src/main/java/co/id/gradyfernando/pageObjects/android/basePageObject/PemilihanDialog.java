package co.id.gradyfernando.pageObjects.android.basePageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PemilihanDialog extends AndroidActions {

    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnAction")
    WebElement simpanButton;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnReset")
    WebElement resetButton;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/search_button")
    WebElement searchButton;
    @AndroidFindBy(accessibility = "Navigate up")
    WebElement backButton;

    public PemilihanDialog(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    public void selectItemWithText(String text) {
        String xPathElement = "//android.widget.TextView[@resource-id=\"co.id.integra.weoffice:id/tvValue\" and @text=\""+ text +"\"]";
        driver.findElement(AppiumBy.xpath(xPathElement)).click();
    }

    public void selectItemByIndex(int index) {
        List<WebElement> elements = driver.findElements(By.id("co.id.integra.weoffice:id/clDataContainer"));
        elements.get(index).click();
    }

    public void clickReset() {
        resetButton.click();
    }

    public void clickSimpan() {
        simpanButton.click();
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void setSearchKeyword(String keyword) {
        WebElement clearSearch = driver.findElement(By.id("co.id.integra.weoffice:id/search_close_btn"));
        WebElement searchBox = driver.findElement(By.id("co.id.integra.weoffice:id/search_src_text"));

        clearSearch.click();
        searchBox.sendKeys(keyword);
    }

    public void clickBackButton() {
        backButton.click();
    }
    

}
