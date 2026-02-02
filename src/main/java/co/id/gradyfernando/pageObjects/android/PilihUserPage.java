package co.id.gradyfernando.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PilihUserPage extends AndroidActions {
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnCariUser")
	private WebElement searchUserButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnSimpan")
	private WebElement simpanButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnReset")
	private WebElement resetButton;

    public PilihUserPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickResetButton() {
        resetButton.click();
    }

    public void clickSearchUserButton() {
        searchUserButton.click();
    }

    public void clickSimpanButton() {
        simpanButton.click();
    }

    public void selectUserByText(String user) {
        scrollToText(user);

        List<WebElement> parentElements = driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"co.id.integra.weoffice:id/clDataContainer\")"));
        for (WebElement webElement : parentElements) {
            WebElement namaElement = webElement.findElement(By.className("co.id.integra.weoffice:id/tvTitle"));
            WebElement jabatanElement = webElement.findElement(By.className("co.id.integra.weoffice:id/tvSubtitle"));

            if (namaElement.getText().toLowerCase().equals(user.toLowerCase()) || jabatanElement.getText().toLowerCase().equals(user.toLowerCase())) {
                namaElement.click();
            }
        }
        
    }

    public void selectUserByIndex(int index) {
        List<WebElement> selectionElements = driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"co.id.integra.weoffice:id/clDataContainer\")"));
        selectionElements.get(index).click();
    }

    public void verifyUserAfterSearch(List<String> userList) {
        List<WebElement> parentElements = driver.findElements(By.id("co.id.integra.weoffice:id/clDataContainer"));
        int userCount = parentElements.size();
        
        for (String namaUser : userList) {
            scrollToText(namaUser);
            for (int i=0; i<userCount; i++) {
                List<WebElement> childElements = parentElements.get(i).findElements(By.className("android.widget.TextView"));

                WebElement namaElement = childElements.get(0);
                // WebElement jabatanElement = childElements.get(1);

                if (namaElement.getText().equals(namaUser)) {
                    Assert.assertNotNull(parentElements.get(i).findElement(By.className("android.widget.ImageView")), "User tidak terpilih");
                    break;
                }
            }
        }
        
    }
    
}
