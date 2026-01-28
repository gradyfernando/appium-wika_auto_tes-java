package co.id.gradyfernando.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AgendaCalendarPage extends AndroidActions {

    public AgendaCalendarPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    public int countData() {
        try {
            WebElement hintElement = driver.findElement(By.id("co.id.integra.weoffice:id/tvNoAgenda"));
            if (hintElement.isDisplayed()) {
                return 0;
            } else {
                List<WebElement> noSuratLabelElement = driver.findElements(By.id("co.id.integra.weoffice:id/tvPerihal"));
                return noSuratLabelElement.size();
            }
        } catch (NoSuchElementException e) {
            return 0;
        }
    }
}

