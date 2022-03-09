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
    private By selectedAssetXpath = By.xpath("//*[@id=\"coral-id-15\"]/coral-panel-content/div/div[2]/coral-masonry/coral-masonry-item/coral-card");
    private By editableDropAreaSelector = By.cssSelector("#OverlayWrapper > div.cq-Overlay.cq-Overlay--component.cq-Overlay--container > div");
    private By previewButtonXpath = By.xpath("//coral-button-label[normalize-space()='Preview']");
    private By addedImageSelector = By.cssSelector("node[data-asset = '/content/dam/wknd-cucumber/asset.jpg']");
// //div[@class='cq-Overlay cq-Overlay--component cq-droptarget cq-Overlay--placeholder js-cq-droptarget--enabled is-hover']
//     #coral-id-15 > coral-panel-content > div > div.content-panel.editor-SidePanel-results > coral-masonry > coral-masonry-item > coral-card
// coral-card[class='editor-Card-asset card-asset cq-draggable u-coral-openHand coral3-Card'] coral-card-info
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
        WebElement from = driver.findElement(selectedAssetXpath);
        wait.until(ExpectedConditions.presenceOfElementLocated(editableDropAreaSelector));
        WebElement to = driver.findElement(editableDropAreaSelector);
        builder.dragAndDrop(from, to).perform();
        wait.until(ExpectedConditions.elementToBeClickable(previewButtonXpath));
        this.driver.findElement(previewButtonXpath).click();

    }

    public void assertAssetVisible() {
        Assert.assertNotNull(this.driver.findElement(addedImageSelector));
    }

//    public void deleteContentPage() {
//        selectEnglishSite();
//        this.driver.findElement(testContentPageXpath).click();
//        this.driver.findElement(moreButtonSelector).click();
//        this.driver.findElement(deleteContentPageButtonXpath).click();
//    }
}
