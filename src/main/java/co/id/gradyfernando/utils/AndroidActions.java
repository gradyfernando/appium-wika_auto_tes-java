package co.id.gradyfernando.utils;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
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
        // driver.findElement(
		// 	AppiumBy.androidUIAutomator(
		// 		"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
		driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().textContains(\""+ text +"\"))"
        ));
	}

	public void scrollUntilVisible(WebElement el) {
		int maxScroll = 6;

		while (maxScroll-- > 0) {
			try {
				if (el.isDisplayed()) {
					// extra scroll to avoid half-cut element
					scrollDown();
					return;
				}
			} catch (NoSuchElementException ignored) {}

			scrollDown();
		}

		throw new RuntimeException("Element not visible after scrolling");
	}


	public void scrollDown() {
		Dimension size = driver.manage().window().getSize();

		int startX = size.width / 2;
		int startY = (int) (size.height * 0.6);
		int endY   = (int) (size.height * 0.4);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(
				Duration.ZERO,
				PointerInput.Origin.viewport(),
				startX,
				startY
		));
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(finger.createPointerMove(
				Duration.ofMillis(400),
				PointerInput.Origin.viewport(),
				startX,
				endY
		));
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe));
	}

	public By findElementByClassAndText(String className, String textContain) {
		return AppiumBy.androidUIAutomator(
			"new UiScrollable(new UiSelector().scrollable(true))" +
			".scrollIntoView(" +
				"new UiSelector().className(\""+className+"\").text(\""+textContain+"\")" +
			")"
		);
	}

	public void scrollToXpath(String xpath) {
		driver.findElement(AppiumBy.androidUIAutomator(
			"new UiScrollable(new UiSelector().scrollable(true))" +
			".scrollIntoView(new UiSelector().xpath(\""+xpath+"\"))"
		));
	}

	public By scrollToFindElementByUISelector(String uiSelector) {
		return AppiumBy.androidUIAutomator(
			"new UiScrollable(new UiSelector().scrollable(true))" +
			".scrollIntoView(" + uiSelector +")"
		);
	}

	public String findElementByResourceId(String resourceId) {
		return "new UiSelector().resourceId(\""+resourceId+"\")";
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
