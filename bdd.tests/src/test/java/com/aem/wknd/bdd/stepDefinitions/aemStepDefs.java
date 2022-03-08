package com.aem.wknd.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import pageObjects.AssetsPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import utils.TestContextSetup;

public class aemStepDefs {
    private TestContextSetup testContextSetup;
    private LoginPage loginPage;
    private LandingPage landingPage;
    private AssetsPage assetsPage;

    public aemStepDefs(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
        this.assetsPage = testContextSetup.pageObjectManager.getAssetsPage();
    }

    @Given("user navigates to login page")
    public void userNavigatesToLoginPage() {
        Assert.assertNotNull(loginPage);
    }

    @When("user logs in as admin")
    public void userLogsInAsAdmin() {
        loginPage.enterPassword("admin");
        loginPage.enterUsername("admin");
        loginPage.submitCreds();
    }

    @Then("user sees admin landing page")
    public void userSeesAdminLandingPage() {
        landingPage.open();
        landingPage.assertLandingPage();
        testContextSetup.testBase.WebDriverManager().quit();
    }

    @Given("user is logged in and on AssetsPage")
    public void userIsLoggedInAndOnAssetsPage() {
        userLogsInAsAdmin();
        assetsPage.open();
    }

    @And("searches for asset with keyword {string}")
    public void searchesForAssetWithKeyword(String keyword) {
        assetsPage.selectWkndCucumberFolder();
        assetsPage.searchForAsset(keyword);
        assetsPage.selectFirstAsset();
    }

    @When("user attempts to crop asset")
    public void userAttemptsToCropAsset() {
        assetsPage.editSelectedAsset();
    }

    @Then("asset is cropped")
    public void assetIsCropped() {
        assetsPage.confirmAssetCrop();
    }

}
