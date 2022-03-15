package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public WebDriver driver;

    private By idUsername = By.id("username");
    private By idPassword = By.id("password");
    private By idSubmitButton = By.id("submit-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String name){
        driver.findElement(idUsername).sendKeys(name);
    }

    public void enterPassword(String password){
        driver.findElement(idPassword).sendKeys(password);
    }

    public void submitCreds(){
        driver.findElement(idSubmitButton).click();
    }
}
