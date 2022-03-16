package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.FileUtils;

import java.util.Properties;

public class LandingPage {
    public WebDriver driver;

    private By classNavigationTitle = By.className("granite-title");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        Properties prop = FileUtils.getGlobalProperties();
        this.driver.navigate().to(prop.getProperty("url") + "/aem/start.html");
    }

    public void assertLandingPage() {
        String navigationTitle = driver.findElement(classNavigationTitle).getText();
        Assert.assertEquals("Navigation", navigationTitle);
    }
}
