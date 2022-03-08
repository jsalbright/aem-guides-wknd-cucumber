package com.aem.wknd.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.LandingPage;
import utils.TestContextSetup;

public class landingPageStepDefs {
    public ChromeOptions options;
    TestContextSetup testContextSetup;

    public landingPageStepDefs(TestContextSetup testContextSetup){
        this.testContextSetup = testContextSetup;
    }

    @Given("User is on GreenCart Landing page")
    public void userIsOnGreenCartLandingPage() {
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        options = new ChromeOptions();
        options.addArguments("--headless");
        testContextSetup.driver = new ChromeDriver(options);
        testContextSetup.driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    @When("user searches with Shortname {string} and extracted actual name of product")
    public void userSearchesWithShortnameAndExtractedActualNameOfProduct(String shortName) throws InterruptedException {
        LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
        landingPage.searchItem(shortName);
        Thread.sleep(2000);
        testContextSetup.landingPageProductName = landingPage.getProductName().split("-")[0].trim();
        System.out.println(testContextSetup.landingPageProductName + " is extracted from Home page");
    }
}
