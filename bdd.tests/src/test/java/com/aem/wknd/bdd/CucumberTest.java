package com.aem.wknd.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * To run cucumber test.
 */
@RunWith(Cucumber.class)
// @CucumberContextConfiguration
@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber-reports/index.html", "json:target/cucumber-reports/cucumber.json" },
        // location
        // of
        // test
        // result
        // data
        // output
        features = { "classpath:features" }, // location of feature files
        glue = { "com.aem.wknd.bdd.pages" }) // location of step
// implementation

public class CucumberTest {

}