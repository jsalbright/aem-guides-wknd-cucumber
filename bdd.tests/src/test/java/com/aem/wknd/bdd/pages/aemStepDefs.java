package com.aem.wknd.bdd.pages;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class aemStepDefs {
    public WebDriver driver;;

    @Given("user navigates to login page")
    public void userNavigatesToLoginPage() {
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:4502");
    }

    @When("user logs in as admin")
    public void userLogsInAsAdmin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.id("submit-button")).click();
    }

    @Then("user sees admin landing page")
    public void userSeesAdminLandingPage() {
        String titleText = driver.findElement(By.className("granite-title")).getText();
        Assert.assertEquals("Navigation", titleText);
        driver.close();
    }
}
