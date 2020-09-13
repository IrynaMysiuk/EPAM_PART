package com.epam.lab.bdd.steps;

import com.epam.lab.business_objects.MainGmailBO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import static com.epam.lab.singleton.DriverContainer.getDriver;
import static com.epam.lab.utils.Constants.GMAIL;
import static com.epam.lab.utils.Constants.URL;

public class MainPageSteps {

    MainGmailBO mainGmailBO;

    @Given("^I navigate to Gmail page$")
    public void goToGmailPage() {
        mainGmailBO = new MainGmailBO();
    }

    @When("^I verify that current url contains correct url$")
    public void verifyUrl() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains(URL), "Website has incorrect url");
    }

    @And("^I open Gmail page$")
    public void openGmailPage() {
        mainGmailBO.openGmailPage();
    }

    @And("^I verify the current url contain 'Gmail' word$")
    public void verifyUrlContainsGmail() {
        Assert.assertTrue(getDriver().getTitle().contains(GMAIL), "Title does not contain Gmail word");
    }

    @Then("^I click 'Login'$")
    public void clickLogin() {
        mainGmailBO.logIn();
    }
}
