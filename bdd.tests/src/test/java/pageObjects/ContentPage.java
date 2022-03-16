package pageObjects;

import io.cucumber.java.mk_latn.No;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileUtils;

import java.time.Duration;
import java.util.Properties;

public class ContentPage {
    public WebDriver driver;
    public Actions builder;

    private By editButtonXpath = By.xpath("//button[contains(text(),'Edit')]");
    private By toggleSidePanelButtonId = By.id("sidepanel-toggle-button");
    private By assetSearchFieldId = By.id("assetsearch");
    private By selectedAssetSelector = By.cssSelector("[data-path=\"/content/dam/wknd-cucumber/asset.jpg\"]");
    private By editableDropAreaSelector = By.cssSelector("[data-text=\"Drag components here\"]");
    private By previewButtonXpath = By.xpath("//coral-button-label[normalize-space()='Preview']");
    private By addedImageSelector = By.cssSelector("[data-asset=\"/content/dam/wknd-cucumber/asset.jpg\"]");

    private By deleteContentPageButtonSelector = By.cssSelector("button[trackingelement=\"delete\"]");
    private By contentPageXpath = By.xpath("//*[@src=\"/content/wknd-cucumber/us/en/Hello-Name.thumb.48.48.png?ck=\"]/..");
    private By deleteButtonXpath = By.xpath("/html/body/coral-dialog/div[2]/coral-dialog-footer/button[2]/coral-button-label");
    private By archiveCheckBoxXpath = By.xpath("//input[@name=\"archive\"]");

    private WebDriverWait wait;

    public ContentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.builder = new Actions(driver);
    }

    public void open() {
        Properties prop = FileUtils.getGlobalProperties();
        this.driver.navigate().to(prop.getProperty("url") + "/editor.html/content/wknd-cucumber/us/en/Hello-Name.html");
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(editableDropAreaSelector));
        //builder.dragAndDrop(driver.findElement(selectedAssetSelector), driver.findElement(editableDropAreaSelector)).perform();

        boolean notFound = true;
        int i = 0;
        while (notFound && i < 10) {
           try {
               builder.dragAndDrop(driver.findElement(selectedAssetSelector), driver.findElement(editableDropAreaSelector)).perform();
               notFound = false;
           } catch (StaleElementReferenceException ser) {
               System.out.println("WARNING: Stale element detected - retrying");
               notFound = true;
           } catch (NoSuchElementException nse) {
               System.out.println("ERROR: No such element");
               notFound = true;
           } catch ( Exception e) {
               System.out.println(e.getMessage());
           }
           i++;
        }

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

    public void assertPageTitle() {
        Assert.assertEquals("Hello-World", driver.getTitle());
    }

    public void deletePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contentPageXpath));
        this.driver.findElement(contentPageXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteContentPageButtonSelector));
        this.driver.findElement(deleteContentPageButtonSelector).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(archiveCheckBoxXpath));
        this.driver.findElement(archiveCheckBoxXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButtonXpath));
        this.driver.findElement(deleteButtonXpath).click();

    }
}
