package co.id.gradyfernando.pageObjects.android;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import co.id.gradyfernando.model.Akses;
import co.id.gradyfernando.model.User;
import co.id.gradyfernando.utils.AndroidActions;
import co.id.gradyfernando.utils.SessionInjector;
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

	public String isHintDisplayed() {
		try {
			WebElement element = driver.findElement(By.id("co.id.integra.weoffice:id/tvTitleHint"));
			return element.getText();
		} catch (NoSuchElementException e) {
			return "";
		}
		
	}

    public void injectSession(User user) {
		HashMap<String, String> userData = new HashMap<>();
		userData.put("action", "userdata");
		userData.put("nama", "\"" + user.getNama() + "\"");
		userData.put("iduser", "\"" + user.getIduser() + "\"");
		userData.put("username", "\"" + user.getUsername() + "\"");
		userData.put("email", "\"" + user.getEmail() + "\"");
		userData.put("num_role", "\"" + user.getNum_role() + "\"");

		SessionInjector.injectToken(driver, user.getToken());
		SessionInjector.injectDataMap(driver, userData);
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
