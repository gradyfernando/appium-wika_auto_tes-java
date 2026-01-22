package co.id.gradyfernando.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DaftarDisposisiPage extends AndroidActions {
    
    public DaftarDisposisiPage(AndroidDriver driver) {
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

    public void selectItemByKodeDisposisi(String kodeDisposisi) {
        List<WebElement> kodeDisposisiElement = driver.findElements(By.id("co.id.integra.weoffice:id/tvKodeDisposisi"));
        int undanganSize = kodeDisposisiElement.size();

        for (int i=0; i<undanganSize; i++) {
            String statusName = kodeDisposisiElement.get(i).getText();
            
            if (statusName.toLowerCase().contains(kodeDisposisi.toLowerCase())) {
                kodeDisposisiElement.get(i).click();
                break;
            }
        }
    }

}
