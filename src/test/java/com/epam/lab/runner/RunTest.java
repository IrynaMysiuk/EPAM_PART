package com.epam.lab.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/validUser.feature"}, glue = {"com.epam.lab"})
public class RunTest extends AbstractTestNGCucumberTests {
}
