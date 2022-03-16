package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileUtils;

import java.time.Duration;
import java.util.Properties;

public class AssetsPage {
    public WebDriver driver;

    private By wkndCucumberFolderXpathHref = By.xpath("//a[@href='/assets.html/content/dam/wknd-cucumber']");
    private By searchButtonXpath = By.xpath("//coral-icon[@aria-label='search']");
    private By searchInputXpath = By.xpath("//input[@placeholder='Type to search ...']");
    private By searchResultSelector = By.xpath("//*[@id=\"granite-omnisearch-result\"]/coral-masonry-item[1]/a");
    private By assetEditButtonXpath = By.xpath("/html/body/div/div[1]/coral-actionbar/coral-actionbar-primary/coral-actionbar-item[5]/button");
    private By startCropButtonXpath = By.xpath("//button[contains(@title,'Start Crop')]");
    private By squareCropButtonXpath = By.xpath("//*[@id=\"coral-1\"]/button[6]");
    private By confirmCropButtonXpath = By.xpath("/html/body/div[2]/div[1]/div/div[2]/div[2]/button[2]");
    private By finishEditButtonXpath = By.xpath("/html/body/div[2]/div[1]/div/div[1]/div[2]/button[4]");
    private By assetImageXpath = By.xpath("//*[@id=\"asset-mainimage\"]");

    private WebDriverWait wait;

    public AssetsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        Properties prop = FileUtils.getGlobalProperties();
        this.driver.navigate().to(prop.getProperty("url") + "/assets.html/content/dam");
    }

    public void selectWkndCucumberFolder() {
        this.driver.findElement(wkndCucumberFolderXpathHref).click();
    }

    public void searchForAsset(String keyword) {
        this.driver.findElement(searchButtonXpath).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputXpath));
        WebElement searchField = this.driver.findElement(searchInputXpath);
        searchField.sendKeys(keyword);
        searchField.sendKeys(Keys.ENTER);
    }

    public void selectFirstAsset() {
        // This function will only work if the first item returned is an image
        // TODO: Update function to filter to only images
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultSelector));
        this.driver.findElement(searchResultSelector).click();
    }

    public void editSelectedAsset() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(assetEditButtonXpath));
        this.driver.findElement(assetEditButtonXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(startCropButtonXpath));
        this.driver.findElement(startCropButtonXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(squareCropButtonXpath));
        this.driver.findElement(squareCropButtonXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmCropButtonXpath));
        this.driver.findElement(confirmCropButtonXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(finishEditButtonXpath));
        this.driver.findElement(finishEditButtonXpath).click();
    }

    public void confirmAssetCrop() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(assetImageXpath));
        int imageHeight = driver.findElement(assetImageXpath).getSize().getHeight();
        int imageWidth = driver.findElement(assetImageXpath).getSize().getWidth();

        Assert.assertEquals(imageHeight, imageWidth);
    }
}
