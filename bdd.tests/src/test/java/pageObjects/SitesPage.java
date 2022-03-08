package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SitesPage {
    public WebDriver driver;
    private WebDriverWait wait;

    private By wkndSiteXpath = By.xpath("//div[normalize-space()='wknd-cucumber']");
    private By wkndUSFolderXpath = By.xpath("//div[@title='us']");
    private By wkndENFolderXpath = By.xpath("//div[@title='en']");
    private By createButtonXpath = By.xpath("//button[@class='granite-collection-create foundation-toggleable-control coral3-Button coral3-Button--primary']");
    private By pageItemXpath = By.xpath("//a[@class='cq-siteadmin-admin-createpage foundation-collection-action coral-Link coral3-BasicList-item coral3-AnchorList-item']//coral-list-item-content[@class='coral3-BasicList-item-content'][normalize-space()='Page']");
    private By contentPageXpath = By.xpath("//coral-card-title[normalize-space()='Content Page']");
    private By nextButtonXpath = By.xpath("//coral-panel[@class='coral3-Panel is-selected']//button[@type='button']");
    // //coral-panel[@class='coral3-Panel is-selected']//button[@type='button']
    //

    public SitesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        this.driver.navigate().to("http://localhost:4502" + "/sites.html/content");
    }

    public void selectEnglishSite() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(wkndSiteXpath));
        this.driver.findElement(wkndSiteXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(wkndUSFolderXpath));
        this.driver.findElement(wkndUSFolderXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(wkndENFolderXpath));
        this.driver.findElement(wkndENFolderXpath).click();
    }

    public void clickCreateButton() {
        this.driver.findElement(createButtonXpath).click();
    }

    public void createContentPage() {
        this.driver.findElement(pageItemXpath).click();
        this.driver.findElement(contentPageXpath).click();
        this.driver.findElement(nextButtonXpath).click();

//        wait.until(ExpectedConditions.visibilityOfElementLocated());
    }
}
