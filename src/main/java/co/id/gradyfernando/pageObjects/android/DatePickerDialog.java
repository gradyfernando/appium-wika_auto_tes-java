package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DatePickerDialog extends AndroidActions {

    @AndroidFindBy(id = "android:id/next")
    WebElement nextMonthButton;
    @AndroidFindBy(id = "android:id/prev")
    WebElement prevMonthButton;
    @AndroidFindBy(id = "android:id/button1")
    WebElement simpanButton;
    @AndroidFindBy(id = "android:id/button2")
    WebElement cancelButton;

    public DatePickerDialog(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void selectDate(String tanggal) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+ tanggal +"\")")).click();
    }

    public void clickNextMonth() {
        nextMonthButton.click();
    }

    public void clickPrevMonth() {
        prevMonthButton.click();
    }

    public void clickOK() {
        simpanButton.click();
    }

    
}
