package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public WebDriver driver;
    public LandingPage landingPage;
    public LoginPage loginPage;
    public AssetsPage assetsPage;
    public SitesPage sitesPage;
    public ContentPage contentPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LandingPage getLandingPage() {
        landingPage = new LandingPage(driver);
        return landingPage;
    }

    public LoginPage getLoginPage() {
        loginPage = new LoginPage(driver);
        return loginPage;
    }

    public AssetsPage getAssetsPage() {
        assetsPage = new AssetsPage(driver);
        return assetsPage;
    }

    public SitesPage getSitesPage() {
        sitesPage = new SitesPage(driver);
        return sitesPage;
    }

    public ContentPage getContentPage() {
        contentPage = new ContentPage(driver);
        return contentPage;
    }
}
