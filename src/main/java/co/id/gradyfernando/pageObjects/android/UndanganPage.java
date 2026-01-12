package co.id.gradyfernando.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;

public class UndanganPage extends AndroidActions {

    public UndanganPage(AndroidDriver driver) {
        super(driver);
        
    }

    public void selectItemWithStatus(String status) {
		List<WebElement> undanganElements = driver.findElements(By.id("co.id.integra.weoffice:id/tvStatus"));
        int undanganSize = undanganElements.size();

        for (int i=0; i<undanganSize; i++) {
            String statusName = undanganElements.get(i).getText();
            
            if (statusName.toLowerCase().contains(status.toLowerCase())) {
                undanganElements.get(i).click();
                break;
            }
        }
    }
    
}
