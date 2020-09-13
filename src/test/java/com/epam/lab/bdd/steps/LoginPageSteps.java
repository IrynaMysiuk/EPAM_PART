package com.epam.lab.bdd.steps;

import com.epam.lab.business_objects.LogInBO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class LoginPageSteps {
    LogInBO logInBO;

    @Given("^I navigate to login page$")
    public void navigateToLoginPage() {
        logInBO = new LogInBO();
    }

    @When("^I switch to Gmail login page$")
    public void switchToGmailTab() {
        logInBO.switchToGmailTab();
    }

    @Then("^I fill in the login field (\\w+@\\w+.\\w+) and password (\\w+)$")
    public void inputValidData(String login, String password) {
        logInBO.loggingToAccount(login, password);
    }

    @And("^I input in the login field (\\w+@\\w+.\\w+)$")
    public void inputInvalidEmail(String login) {
        logInBO.inputEmail(login);
    }

    @Then("^I get error message$")
    public void verifyErrorMessage() {
        Assert.assertFalse(logInBO.getErrorMessage().isEmpty(), "The error message is not displayed");
    }

}
