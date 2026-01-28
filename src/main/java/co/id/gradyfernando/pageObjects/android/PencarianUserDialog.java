package co.id.gradyfernando.pageObjects.android;

import java.util.ArrayList;
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

public class PencarianUserDialog extends AndroidActions{

    private List<String> selectedUser = new ArrayList<>();

    public PencarianUserDialog(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "co.id.integra.weoffice:id/etPencarian")
    WebElement pencarianInput;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/imgClose")
    WebElement imgClose;

    public List<String> getSelectedNamaUser() {
        return selectedUser;
    }

    public void cariUserByNama(String user) throws InterruptedException {
        pencarianInput.sendKeys(user);

        Thread.sleep(2000);
        
        List<WebElement> userElements = driver.findElements(By.id("co.id.integra.weoffice:id/tvTitle"));
        int userSize = userElements.size();

        for (int i=0; i<userSize; i++) {
            String userName = userElements.get(i).getText();
            
            if (userName.toLowerCase().contains(user.toLowerCase())) {
                userElements.get(i).click();
                System.out.println("Add user: " + userName);
                selectedUser.add(userName);
                break;
            }
        }
    }

    public void searchUser(String user) throws InterruptedException {
        pencarianInput.sendKeys(user);

        Thread.sleep(2000);

        List<WebElement> parentElements = driver.findElements(By.id("co.id.integra.weoffice:id/clDataContainer"));
        int userCount = parentElements.size();
        System.out.println("userCount : " + userCount);
        
        for (int i=0; i<userCount; i++) {
            List<WebElement> childElements = parentElements.get(i).findElements(By.className("android.widget.TextView"));

            WebElement namaElement = childElements.get(0);
            WebElement jabatanElement = childElements.get(1);

            System.out.println("Cari nama user : " + namaElement.getText());
            System.out.println("Cari jabatan user : " + jabatanElement.getText());

            if (namaElement.getText().equals(user) || jabatanElement.getText().equals(user)) {
                selectedUser.add(namaElement.getText());
                parentElements.get(i).click();

                Thread.sleep(500);
                break;
            }
        }
    }

    // public String getUserElementBy(String section) throws InterruptedException {
    //     pencarianInput.sendKeys(user);

    //     Thread.sleep(2000);

    //     List<WebElement> parentElements = driver.findElements(By.id("co.id.integra.weoffice:id/clDataContainer"));
    //     int userCount = parentElements.size();
        
    //     for (int i=0; i<userCount; i++) {
    //         WebElement childElement = parentElements.get(i);

    //         switch (section) {
    //             case "nama":
    //                 WebElement namaElement = childElement.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"co.id.integra.weoffice:id/tvTitle\")"));
    //                 return namaElement.getText();
    //             case "jabatan":
    //                 WebElement jabatanElement = childElement.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"co.id.integra.weoffice:id/tvSubtitle\")"));
    //                 return jabatanElement.getText();
    //             case "selected":
    //                 try {
    //                 WebElement imgSelected = childElement.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"co.id.integra.weoffice:id/imgSelected\")"));
    //                     return "1";
    //                 } catch (Exception e) {
    //                     return "0";
    //                 }
    //         }
    //     }

    //     return "";

    // }

    public void closePage() {
        imgClose.click();
    }
    
}
