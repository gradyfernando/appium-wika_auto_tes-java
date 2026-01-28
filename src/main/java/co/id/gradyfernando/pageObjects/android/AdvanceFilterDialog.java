package co.id.gradyfernando.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdvanceFilterDialog extends AndroidActions {

    public AdvanceFilterDialog(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void selectFilter(String exactFilterValue) {
        List<WebElement> filterItems = driver.findElements(By.className("android.widget.CheckedTextView"));
        for (WebElement filterItem : filterItems) {
            if (filterItem.getText().equals(exactFilterValue)) {
                filterItem.click();
                break;
            }
        }
    }
    
}
