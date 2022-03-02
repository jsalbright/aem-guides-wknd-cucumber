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
        plugin = { "pretty",                                    // applies color to cucumber report
                   "html:target/cucumber-reports/index.html",   // generates HTML cucumber report
                   "json:target/cucumber-reports/cucumber.json" // generates JSON cucumber report
        },
        features = { "classpath:features" }, // location of feature files
        glue = { "com.aem.wknd.bdd.pages" }) // location of step
// implementation

public class CucumberTest {

}