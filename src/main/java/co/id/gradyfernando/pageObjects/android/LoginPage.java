package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(id = "co.id.integra.weoffice:id/etUsername")
	private WebElement usernameField;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/etPassword")
	private WebElement passwordField;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnLogin")
	private WebElement loginButton;
	
	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void setActivity() {
		String activityName = "co.id.integra.weoffice/co.id.integra.weoffice.view.activity.LoginActivity";
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			"intent", activityName
		));
	}
	
	public void setUsernamePassword(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
	}
	
	public void clickLogin() {
		loginButton.click();
	}

//	public void setNameField(String name) {
//		nameField.sendKeys(name);
//		driver.hideKeyboard();
//	}
//
//	public void setGender(String gender) {
//		if (gender.contains("female")) {
//			femaleOption.click();
//		} else {
//			maleOption.click();
//		}
//	}
//
//	public void setCountrySelection(String country) {
//		countrySelection.click();
//		scrollToText(country);
//        driver.findElement(By.xpath("//android.widget.TextView[@text='"+ country +"']")).click();
//	}
//
//	public ProductCatalogue clickShop() {
//		shopButton.click();
//		return new ProductCatalogue(driver);
//	}

}
