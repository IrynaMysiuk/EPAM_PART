package com.epam.lab.steps;

import com.epam.lab.business_objects.LogInBO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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

    @Then("^I fill in the login field (.*) and password (.*)$")
    public void inputValidData(String login, String password) {
        logInBO.loggingToAccount(login, password);
    }

}
