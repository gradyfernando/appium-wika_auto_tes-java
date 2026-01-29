package co.id.gradyfernando.pageObjects.android;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
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
	@AndroidFindBy(id = "co.id.integra.weoffice:id/rvResumeDoc")
	private WebElement rvResumeDoc;

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

	public void isDisplayed() {

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

	public int countNotification() {
		Set<Integer> positions = new HashSet<>();
		boolean reachedEnd = false;
		String currSource;

		while (!reachedEnd) {
    		currSource = driver.getPageSource();

			List<WebElement> items =
				driver.findElements(AppiumBy.id("co.id.integra.weoffice:id/cvMenuContainer"));

			for (int i = 0; i < items.size(); i++) {
				positions.add(i);
			}

			driver.findElement(AppiumBy.androidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
			));

			String newSource = driver.getPageSource();

			if (newSource.equals(currSource)) {
				reachedEnd = true;
				break; // reached end
			}
		}

		System.out.println("Total items = " + positions.size());

		return positions.size();
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

	public String selectItemDashboard(String section, String item) throws InterruptedException {
		// Scroll & find caption
		driver.findElement(findElementByClassAndText("android.widget.TextView", section));

		// Find Elements of Card View
		var selectedCardViewIndex = 0;
		List<WebElement> cardViews = driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"co.id.integra.weoffice:id/cvMenuContainer\")"));
		for (int i = 0; i < cardViews.size(); i++) {
			var xPathIndex = i + 1;
			var xPath = "(//androidx.cardview.widget.CardView[@resource-id=\"co.id.integra.weoffice:id/cvMenuContainer\"])["+xPathIndex+"]";

			// Get element by xpath + index
			System.out.println("xPath: " + xPath);
			var cardViewElement = driver.findElement(AppiumBy.xpath(xPath));

			// Get caption to be checked
			var captionElements = cardViewElement.findElements(By.className("android.widget.TextView"));
			System.out.println("text caption elements: " + captionElements.size());
			var captionText = captionElements.get(0).getText();

			if (captionElements.size() < 9) {
        		scrollDown();
				captionElements = cardViewElement.findElements(By.className("android.widget.TextView"));
				System.out.println("recheck text caption elements: " + captionElements.size());
			}

			if (captionText.equals(section)) {
				System.out.println("captionSelected: " + captionElements.get(0).getText());
				selectedCardViewIndex = xPathIndex;

				WebElement rvLink = driver.findElement(By.xpath("(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"co.id.integra.weoffice:id/rvLink\"])["+selectedCardViewIndex+"]"));
				List<WebElement> itemsLink = rvLink.findElements(By.className("android.widget.LinearLayout"));

				for (WebElement linkElement : itemsLink) {
					List<WebElement> textElements = linkElement.findElements(By.className("android.widget.TextView"));
					WebElement titleText = textElements.get(0);
					WebElement numberText = textElements.get(1);
					var notificationCount = numberText.getText();

					System.out.println("titleText:" + titleText.getText());
					if (titleText.getText().trim().equals(item)) {
						System.out.println("clicked titleText:" + titleText.getText());

						titleText.click();
						Thread.sleep(2000);
						return notificationCount;
					}
				}
				break;
			}
		}

		return null;
	}

	public String getNama() {
		return tvNamaUser.getText();
	}

	public int getNotificationListChild() {
		return getChildCount(AppiumBy.id("co.id.integra.weoffice:id/tvNotification"));
	}

	// Get badge number from bottom navigation bar
	public String getNotificationBadgeNumber() {
		WebElement bottomNavBar = driver.findElement(By.id("co.id.integra.weoffice:id/navigation_notifications"));
		String input = bottomNavBar.getAttribute("content-desc");

		// Find the index of the first space
        int firstSpaceIndex = input.indexOf(' ');

        // Find the index of the second space, starting the search after the first space
        // We add 1 to firstSpaceIndex to begin searching immediately after the first space found.
        int secondSpaceIndex = input.indexOf(' ', firstSpaceIndex + 1);

        // Check if both spaces were found to prevent StringIndexOutOfBoundsException
        if (firstSpaceIndex != -1 && secondSpaceIndex != -1) {
            // Extract the substring between the first and second space.
            // The beginIndex is inclusive, and the endIndex is exclusive.
            String result = input.substring(firstSpaceIndex + 1, secondSpaceIndex);
			return result;
        } else {
			return "0";
        }
	}

}
