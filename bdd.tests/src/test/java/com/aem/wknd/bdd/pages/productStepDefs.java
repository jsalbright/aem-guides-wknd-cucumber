package com.aem.wknd.bdd.pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

public class productStepDefs {
    public WebDriver driver;
    public ChromeOptions options;
    String landingPageProductName;
    String offerPageProductName;

    @Given("User is on GreenCart Landing page")
    public void userIsOnGreenCartLandingPage() {

        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    @When("user searches with Shortname {string} and extracted actual name of product")
    public void userSearchesWithShortnameAndExtractedActualNameOfProduct(String shortName) throws InterruptedException {
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        Thread.sleep(2000);
        landingPageProductName = driver.findElement(By.cssSelector("h4.product-name")).getText().split("-")[0].trim();
        System.out.println(landingPageProductName + " is extracted from Home page");
    }

    @Then("user searched for {string} shortname in offers page")
    public void userSearchedForShortnameInOffersPage(String shortName) throws InterruptedException {
        driver.findElement(By.linkText("Top Deals")).click();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();

        driver.switchTo().window(childWindow);
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        Thread.sleep(2000);
        offerPageProductName = driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();
    }

    @And("validate product name in offers page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {
        Assert.assertEquals(offerPageProductName, landingPageProductName);
        driver.quit();
    }
}
