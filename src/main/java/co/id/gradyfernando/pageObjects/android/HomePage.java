package co.id.gradyfernando.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends AndroidActions {

	@AndroidFindBy(id = "co.id.integra.weoffice:id/tvNamaUser")
	private WebElement tvNamaUser;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/navigation_menu")
	private WebElement tabProfile;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/navigation_dashboard")
	private WebElement tabDashboard;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/navigation_notifications")
	private WebElement tabNotification;

	public HomePage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void setActivity() {
		String activityName = "co.id.integra.weoffice/co.id.integra.weoffice.view.activity.DashboardActivity";
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			"intent", activityName
		));
	}

	public void openProfileMenu() {
		tabProfile.click();
	}
	public void openNotificationMenu() {
		tabNotification.click();
	}
	public void openDashboardMenu() {
		tabDashboard.click();
	}

	public void selectMenuFromAllMenu(String menu) {
		List<WebElement> menuElements = driver.findElements(By.id("co.id.integra.weoffice:id/tvMenu"));
        int menuSize = menuElements.size();

        for (int i=0; i<menuSize; i++) {
            String roleName = menuElements.get(i).getText();
            
            if (roleName.toLowerCase().contains(menu.toLowerCase())) {
                menuElements.get(i).click();
				break;
            }
        }
	}

	public String getNama() {
		return tvNamaUser.getText();
	}

}
