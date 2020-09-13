package com.epam.lab.bdd.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/"}, glue = {"com.epam.lab"})
public class RunTest extends AbstractTestNGCucumberTests {
}
