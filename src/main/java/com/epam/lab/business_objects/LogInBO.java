package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LogInPO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static com.epam.lab.singleton.DriverContainer.getDriver;

public class LogInBO {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    @When("^user type login \"(login)\" and password \"(password)\" in Gmail page$")
    public void loggingToAccount(String login, String password) {
        LogInPO logInPO = new LogInPO();
        log.info("Fill login and password to Gmail account");
        log.info("Typing login: " + login);
        logInPO.getLoginField().sendText(login);
        logInPO.getLoginButton().click();
        log.info("Typing password: " + password);
        logInPO.getPasswordField().sendText(password);
        logInPO.getPasswordButton().click();
    }

    @And("^Switch in other Gmail tab$")
    public void switchToGmailTab() {
        log.info("Switch to other Gmail Tab");
        switchToLogin();
    }

    @Then("^Switch to tab fo input login$")
    private void switchToLogin() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        String handleName = tabs.get(1);
        getDriver().switchTo().window(handleName);
    }
}
