package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContentPage {
    public WebDriver driver;
    public Actions builder;

    private By editButtonXpath = By.xpath("//button[contains(text(),'Edit')]");
    private By toggleSidePanelButtonId = By.id("sidepanel-toggle-button");
    private By assetSearchFieldId = By.id("assetsearch");
    private By selectedAssetSelector = By.cssSelector("[data-path=\"/content/dam/wknd-cucumber/asset.jpg\"]");
    private By editableDropAreaSelector = By.cssSelector("[title=\"Container [Root]\"]");
    private By previewButtonXpath = By.xpath("//coral-button-label[normalize-space()='Preview']");
    private By addedImageSelector = By.cssSelector("[data-asset=\"/content/dam/wknd-cucumber/asset.jpg\"]");

    private WebDriverWait wait;

    public ContentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.builder = new Actions(driver);
        this.driver.manage().window().maximize();
    }

    public void open(String url) {
        this.driver.navigate().to("http://localhost:4502" + url);
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public void enterEditMode() {
        wait.until(ExpectedConditions.elementToBeClickable(editButtonXpath));
        this.driver.findElement(editButtonXpath).click();
    }

    public void toggleSidePanel() {
        wait.until(ExpectedConditions.elementToBeClickable(toggleSidePanelButtonId));
        this.driver.findElement(toggleSidePanelButtonId).click();
    }

    public void searchForAsset() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(assetSearchFieldId));
        this.driver.findElement(assetSearchFieldId).sendKeys("asset.jpg");
        this.driver.findElement(assetSearchFieldId).sendKeys(Keys.ENTER);
    }

    public void dragAndDropAsset() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectedAssetSelector));
        WebElement from = driver.findElement(selectedAssetSelector);
        wait.until(ExpectedConditions.presenceOfElementLocated(editableDropAreaSelector));
        WebElement to = driver.findElement(editableDropAreaSelector);
        builder.dragAndDrop(from, to).perform();
        wait.until(ExpectedConditions.elementToBeClickable(previewButtonXpath));
        this.driver.findElement(previewButtonXpath).click();
    }

    public void assertAssetVisible() {
        // Have to switch to iframe otherwise driver fails to find element
        driver.switchTo().frame(driver.findElement(By.id("ContentFrame")));
        wait.until(ExpectedConditions.presenceOfElementLocated(addedImageSelector));
        Assert.assertNotNull(this.driver.findElement(addedImageSelector));
        // Switch back to primary driver from iframe
        driver.switchTo().defaultContent();
    }

//    public void deleteContentPage() {
//        selectEnglishSite();
//        this.driver.findElement(testContentPageXpath).click();
//        this.driver.findElement(moreButtonSelector).click();
//        this.driver.findElement(deleteContentPageButtonXpath).click();
//    }
}
