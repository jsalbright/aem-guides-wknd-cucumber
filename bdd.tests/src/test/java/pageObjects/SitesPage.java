package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SitesPage {
    public WebDriver driver;
    private WebDriverWait wait;

    private By wkndSiteHref = By.xpath("//div[normalize-space()='wknd-cucumber']");
    private By wkndUSFolderHref = By.xpath("//div[@title='us']");
    private By wkndENFolderHref = By.xpath("//div[@title='en']");

    public SitesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        this.driver.navigate().to("http://localhost:4502" + "/sites.html/content");
    }

    public void selectEnglishSite() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(wkndSiteHref));
        this.driver.findElement(wkndSiteHref).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(wkndUSFolderHref));
        this.driver.findElement(wkndUSFolderHref).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(wkndENFolderHref));
        this.driver.findElement(wkndENFolderHref).click();
    }

}
