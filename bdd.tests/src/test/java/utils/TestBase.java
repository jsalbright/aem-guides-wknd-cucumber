package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
    public WebDriver driver;

    public WebDriver WebDriverManager() throws IOException {
        Properties prop = FileUtils.getGlobalProperties();
        String url = prop.getProperty("url");
        String browserMode = prop.getProperty("headless").equalsIgnoreCase("true") ? "headless" : "--start-minimized";

        if (driver == null) {
            // Chrome
            if (prop.getProperty("browser").equalsIgnoreCase("chrome")){
                driver = loadChrome(browserMode);

                // Firefox
            } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
                driver = loadFirefox(browserMode);
            }

            driver.get(url);
        }
        return driver;
    }

    private WebDriver loadChrome(String browserMode) {
        ChromeDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(browserMode);
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        this.driver = driver;

        return driver;
    }

    private WebDriver loadFirefox(String browserMode){
        FirefoxDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(browserMode);
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        driver = new FirefoxDriver(options);
        this.driver = driver;

        return driver;
    }
}