package co.id.gradyfernando.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import co.id.gradyfernando.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DetailDisposisiPage extends AndroidActions {
    
	@AndroidFindBy(id = "co.id.integra.weoffice:id/tvKodeDisposisi")
	private WebElement tvKodeDisposisi;
    @AndroidFindBy(id = "co.id.integra.weoffice:id/btnKirimDisposisi")
	private WebElement disposisiButton;
	@AndroidFindBy(id = "co.id.integra.weoffice:id/btnKirimInformasi")
	private WebElement informasiButton;

    public DetailDisposisiPage(AndroidDriver driver) {
        super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setActivity(String disposisiId) {
        driver.get("weoffice://disposisi/detail?id=" + disposisiId);
    }

    public String getKodeDisposisi() {
        return tvKodeDisposisi.getText();
    }

    public void clickDisposisikan() {
        disposisiButton.click();
    }

    public String getTitlePage(String tipe) {
        // if (tipe == "0") {
        //     return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Detail Disposisi Masuk\"]")).getText();
        // } else {
        //     return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Detail Disposisi Keluar\"]")).getText();
        // }
        return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Detail Disposisi\"]")).getText();
    }

}
