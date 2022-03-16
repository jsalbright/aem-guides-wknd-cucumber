package com.aem.wknd.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.SitesPage;
import utils.TestContextSetup;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Hooks {
    TestContextSetup testContextSetup;

    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @Before
    public void BeforeScenario() throws IOException {
        WebDriver driver = testContextSetup.testBase.WebDriverManager();
        By idUsername = By.id("username");
        By idPassword = By.id("password");
        By idSubmitButton = By.id("submit-button");

        driver.findElement(idUsername).sendKeys("admin");
        driver.findElement(idPassword).sendKeys("admin");
        driver.findElement(idSubmitButton).click();
    }

    @After
    public void AfterScenario() throws IOException {
        testContextSetup.testBase.WebDriverManager().quit();
    }

    @After("@contentpage")
    public void AfterContentPageScenario() throws InterruptedException, IOException {
        WebDriver driver = testContextSetup.testBase.WebDriverManager();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SitesPage sitesPage = testContextSetup.pageObjectManager.getSitesPage();

        By deleteChildPageButtonSelector = By.cssSelector("button[trackingelement=\"delete\"]");
        By deleteButtonXpath = By.xpath("/html/body/coral-dialog/div[2]/coral-dialog-footer/button[2]/coral-button-label");
        By archiveCheckBoxXpath = By.xpath("//input[@name=\"archive\"]");

        sitesPage.open();
        sitesPage.selectEnglishSite();
        sitesPage.selectChildPage();

        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteChildPageButtonSelector));
        driver.findElement(deleteChildPageButtonSelector).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(archiveCheckBoxXpath));
        driver.findElement(archiveCheckBoxXpath).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButtonXpath));
        driver.findElement(deleteButtonXpath).click();
    }

    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        WebDriver driver = testContextSetup.testBase.WebDriverManager();
        if (scenario.isFailed()) {
            File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");
        }
    }
}
