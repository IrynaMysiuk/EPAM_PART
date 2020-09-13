package com.epam.lab.bdd.steps;

import com.epam.lab.ddt.GmailTests;
import com.epam.lab.business_objects.LetterBO;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.testng.Assert;

import static com.epam.lab.singleton.DriverContainer.quitDriver;

public class LetterPageSteps {
    LetterBO letterBO;
    private static final Logger logger = Logger.getLogger(GmailTests.class.getName());

    @Given("^I navigate to letter page$")
    public void navigateLetterPage() {
        letterBO = new LetterBO();
    }

    @When("^I fill in field the invalid email (.*) and subject (.*)$")
    public void inputInvalidData(String incorrectEmail, String subject) {
        letterBO.sendIncorrectLetter(incorrectEmail, subject);
    }

    @And("^I verify that alert tab is displayed$")
    public void verifyAlertTab() {
        Assert.assertTrue(letterBO.isAlertTabDisplayed(), "Tab about incorrect address is not displayed");
    }

    @And("^I submit letter with incorrect address$")
    public void submitIncorrectLetter() {
        letterBO.submitIncorrectAddress();
    }

    @And("^I fill in field the valid email (.*)$")
    public void inputValidData(String correctEmail) {
        letterBO.sendCorrectLetter(correctEmail);
    }


    @Then("^I verify that letter was sent$")
    public void verifySentLetter() {
        Assert.assertEquals(letterBO.getActualMessage(), letterBO.getExpectedMessage(),
                "Text message is not the same");
    }

    @After
    public void afterScenario() {
        quitDriver();
        logger.info("After the Scenario I close browser");
    }

}
