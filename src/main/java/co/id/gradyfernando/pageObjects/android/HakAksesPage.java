package co.id.gradyfernando.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HakAksesPage extends AndroidActions {

    @AndroidFindBy(id = "co.id.integra.weoffice:id/rvRole")
    private WebElement rvRole;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnLogout")
	private WebElement logoutButton;

    private Akses akses;

    public HakAksesPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

	public void setActivity() {
		String activityName = "co.id.integra.weoffice/co.id.integra.weoffice.view.activity.HakAksesActivity";
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			"intent", activityName
		));
	}

    public void setRole(String role) {
        List<WebElement> roleElements = driver.findElements(By.id("co.id.integra.weoffice:id/tvNama"));
        int roleSize = roleElements.size();

        for (int i=0; i<roleSize; i++) {
            String roleName = roleElements.get(i).getText();
            
            if (roleName.toLowerCase().contains(role.toLowerCase())) {
                roleElements.get(i).click();
                break;
            }
        }
        
    }

    
    public void clickLogout() {
        logoutButton.click();
    }

}
