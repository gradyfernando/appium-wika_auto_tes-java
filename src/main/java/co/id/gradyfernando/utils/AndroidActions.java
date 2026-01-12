package co.id.gradyfernando.utils;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.beust.jcommander.internal.Nullable;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {

    protected AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
    }
    
    public static String getAppPackage(@Nullable String viewId) {
    	String appPackage = "co.id.integra.weoffice";
    	
    	if (viewId == null) {
        	return appPackage;
    	} else {
    		return appPackage + ":id/" + viewId;
    	}
    }
    
	public void doLongPressed(WebElement element) {
		RemoteWebElement longPress = (RemoteWebElement) element;
		driver.executeScript("mobile: longClickGesture", Map.of("elementId", longPress.getId(), "pressure", 0.5, "duration", 2000));
	}

	public void doScrollToEnd() {
		boolean canScrollMore = true;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", 
								ImmutableMap.of(
									"left", 100,
									"top", 100,
									"width", 200,
									"height", 200,
									"direction", "down",
									"percent", 100.0
								));
		} while (canScrollMore);
	}

	public void scrollToText(String text) {
        driver.findElement(
			AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
	}

	public void doSwipe(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript(
						"mobile: swipeGesture", 
								ImmutableMap.of(
									"elementId", ((RemoteWebElement) element).getId(),
									"direction", direction,
									"percent", 0.2
								));
	}
	
}
