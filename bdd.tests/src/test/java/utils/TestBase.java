package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
    public WebDriver driver;
    public ChromeOptions options;

    public WebDriver WebDriverManager(){
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            options = new ChromeOptions();
            options.addArguments("--headless");
//            driver = new ChromeDriver(options);
            driver = new ChromeDriver();
//            driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
            driver.get("http://localhost:4502");
        }
        return driver;
    }
}
