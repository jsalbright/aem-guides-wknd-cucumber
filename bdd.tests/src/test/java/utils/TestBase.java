package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public WebDriver driver;
    public ChromeOptions options;

    public WebDriver WebDriverManager() throws IOException {
        FileInputStream fis = new FileInputStream( System.getProperty("user.dir") + "/src/test/resources/global.properties");
        Properties prop = new Properties();
        prop.load(fis);

        String url = prop.getProperty("url");

        if (driver == null) {
            if (prop.getProperty("browser").equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                options = new ChromeOptions();
                options.addArguments("--headless");
//              driver = new ChromeDriver(options);
                driver = new ChromeDriver();
            } else if (prop.getProperty("firefox").equalsIgnoreCase("firefox")) {
                // TODO: Write firefox code
            }

//          driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
            driver.get(url);
        }
        return driver;
    }
}
