package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public WebDriver driver;

    public PageObjectManager(WebDriver driver){
        this.driver = driver;
    }
    public LandingPage landingPage;

    public LandingPage getLandingPage(){
        landingPage = new LandingPage(driver);
        return landingPage;
    }

    public OffersPage offersPage;

    public OffersPage getOffersPage(){
        offersPage = new OffersPage(driver);
        return offersPage;
    }
}
