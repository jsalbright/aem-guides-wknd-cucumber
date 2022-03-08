package com.aem.wknd.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import pageObjects.AssetsPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.SitesPage;
import utils.TestContextSetup;

import java.io.IOException;

public class aemStepDefs {
    private SitesPage sitesPage;
    private TestContextSetup testContextSetup;
    private LoginPage loginPage;
    private LandingPage landingPage;
    private AssetsPage assetsPage;

    public aemStepDefs(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
        this.assetsPage = testContextSetup.pageObjectManager.getAssetsPage();
        this.sitesPage = testContextSetup.pageObjectManager.getSitesPage();
    }

    @Given("user navigates to login page")
    public void userNavigatesToLoginPage() {
        Assert.assertNotNull(loginPage);
    }

    @When("user logs in as admin")
    public void userLogsInAsAdmin() {
        loginPage.enterUsername("admin");
        loginPage.enterPassword("admin");
        loginPage.submitCreds();
    }

    @Then("user sees admin landing page")
    public void userSeesAdminLandingPage() throws IOException {
        landingPage.open();
        landingPage.assertLandingPage();
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

//    @When("^user enters (.+) in username field$")
//    public void userEntersUserNameInUsernameField(String username) {
//        loginPage.enterUsername(username);
//    }
//
//    @And("^user enters (.+) in password field$")
//    public void userEntersPasswordInPasswordField(String password) {
//        loginPage.enterPassword(password);
//        loginPage.submitCreds();
//    }
//
//    @Then("user sees landing page")
//    public void userSeesLandingPage() {
//        landingPage.open();
//        landingPage.assertLandingPage();
//    }

    @Given("user is logged in and on SitesPage")
    public void userIsLoggedInAndOnSitesPage() {
        userLogsInAsAdmin();
        sitesPage.open();
    }

    @When("user authors new content page")
    public void userAuthorsNewContentPage() {
        sitesPage.selectEnglishSite();
        sitesPage.clickCreateButton();
        sitesPage.createContentPage();
        sitesPage.enterPageDetails();
        sitesPage.publishContentPage();
    }

    @Then("user sees new content page")
    public void userSeesNewContentPage() {
        sitesPage.openContentPage();
        sitesPage.assertPageTitle();
    }
}
