package com.aem.wknd.bdd;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
//    TestContextSetup testContextSetup;
    @AfterStep
    public void AddScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            //screenshot
//            ((TakesScreenshot))driver
        }
    }
}
