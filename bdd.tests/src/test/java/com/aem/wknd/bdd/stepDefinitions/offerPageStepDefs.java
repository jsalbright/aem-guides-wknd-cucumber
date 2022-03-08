package com.aem.wknd.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.junit.Assert;
import org.openqa.selenium.By;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContextSetup;

import java.util.Iterator;
import java.util.Set;

public class offerPageStepDefs {
    String offerPageProductName;
    TestContextSetup testContextSetup;
    PageObjectManager pageObjectManager;

    public offerPageStepDefs(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @Then("user searched for {string} shortname in offers page")
    public void userSearchedForShortnameInOffersPage(String shortName) throws InterruptedException {
        switchToOffersPage();
        OffersPage offersPage = testContextSetup.pageObjectManager.getOffersPage();
        offersPage.searchItem(shortName);
        Thread.sleep(2000);
        offerPageProductName = offersPage.getProductName();
    }

    @And("validate product name in offers page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {
        Assert.assertEquals(offerPageProductName, testContextSetup.landingPageProductName);
        testContextSetup.driver.quit();
    }

    public void switchToOffersPage(){
        LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
        landingPage.selectTopDealsPage();
        testContextSetup.genericUtils.SwitchWindowToChild();
    }
}
