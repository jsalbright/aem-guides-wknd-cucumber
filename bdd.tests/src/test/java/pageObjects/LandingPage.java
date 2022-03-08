package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    public WebDriver driver;

    private By classNavigationTitle = By.className("granite-title");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        this.driver.navigate().to("http://localhost:4502" + "/aem/start.html");
    }

    public void assertLandingPage() {
        String navigationTitle = driver.findElement(classNavigationTitle).getText();
        Assert.assertEquals("Navigation", navigationTitle);
    }
}
