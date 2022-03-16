package com.aem.wknd.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * To run cucumber test.
 **/
@RunWith(Cucumber.class)
// @CucumberContextConfiguration
@CucumberOptions(
        plugin = { "pretty",                                     // applies color to cucumber report
                   "html:target/cucumber-reports/index.html",    // generates HTML cucumber report
                   "json:target/cucumber-reports/cucumber.json", // generates JSON cucumber report
                   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // generates Extent Reports
        },
        features = { "classpath:features" }, // location of feature files
        glue = { "com.aem.wknd.bdd.stepDefinitions" },// location of step
        tags = "@contentpage or @sitespage" // specific tags to execute
        )
// implementation

public class CucumberTest {

}