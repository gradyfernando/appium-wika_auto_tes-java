package co.id.gradyfernando.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DaftarUndanganPage extends AndroidActions {

    @AndroidFindBy(id = "co.id.integra.weoffice:id/rvFilter")
    private WebElement rvFilter;
    
    public DaftarUndanganPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void selectItemWithStatus(String status) {
		List<WebElement> statusLabelElement = driver.findElements(By.id("co.id.integra.weoffice:id/tvStatus"));
        int undanganSize = statusLabelElement.size();

        for (int i=0; i<undanganSize; i++) {
            String statusName = statusLabelElement.get(i).getText();
            
            if (statusName.toLowerCase().contains(status.toLowerCase())) {
                statusLabelElement.get(i).click();
                break;
            }
        }
    }

    public void selectItemByNomor(String nomorSurat) {
        List<WebElement> noSuratLabelElement = driver.findElements(By.id("co.id.integra.weoffice:id/tvNoSurat"));
        int undanganSize = noSuratLabelElement.size();

        for (int i=0; i<undanganSize; i++) {
            String statusName = noSuratLabelElement.get(i).getText();
            
            if (statusName.toLowerCase().contains(nomorSurat.toLowerCase())) {
                noSuratLabelElement.get(i).click();
                break;
            }
        }
    }

    public String checkAdvanceFilterValue(String exactCaption) {
        List<WebElement> itemElements = rvFilter.findElements(By.className("android.view.ViewGroup"));
        
        for (WebElement webElement : itemElements) {
            var statusCaption = webElement.findElement(By.className("android.widget.TextView"));
            var filterButton = webElement.findElement(By.className("android.widget.Button"));

            if (statusCaption.getText().equals(exactCaption)) {
                return filterButton.getText().toLowerCase();
            }
        }

        return "";
    }

    public int countData() {
        try {
            WebElement hintElement = driver.findElement(By.id("co.id.integra.weoffice:id/tvTitleHint"));
            
            if (hintElement.isDisplayed()) {
                return 0;
            } else {
                List<WebElement> noSuratLabelElement = driver.findElements(By.id("co.id.integra.weoffice:id/tvNoSurat"));
                return noSuratLabelElement.size();
            }
        } catch (NoSuchElementException e) {
            return 0;
        }
    }
    
}
