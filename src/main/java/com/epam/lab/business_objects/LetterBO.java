package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LetterPO;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Random;

public class LetterBO {
    private String randomText;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    @When("^user input \"(incorrectEmail)\" email address$")
    public void sendIncorrectLetter(String incorrectEmail, String subject) {
        LetterPO letterPO = new LetterPO();
        log.info("Sending incorrect address in letter");
        letterPO.getAndWaitLoadPage();
        letterPO.getSendLetter().click();
        letterPO.getIncorrectAddress().sendText(incorrectEmail);
        letterPO.getLetterTheme().sendText(subject);
        randomText = String.valueOf(new Random().nextInt(10000));
        letterPO.getTextBox().sendText(randomText);
        letterPO.getCompleteLetter().click();
    }

    @Then("^Submit letter with incorrect address$")
    public void submitIncorrectAddress() {
        LetterPO letterPO = new LetterPO();
        log.info("Click submit button in alert message with incorrect address");
        letterPO.getAlertTab().click();
    }

    @And("^Alert tab should be displayed$")
    public boolean isAlertTabDisplayed() {
        LetterPO letterPO = new LetterPO();
        log.info("Check alert tab is displayed");
        return letterPO.getAlertDialog().isDisplayed();
    }

    @Then("^Retype email address on correct address$")
    public void sendCorrectLetter(String correctEmail) {
        LetterPO letterPO = new LetterPO();
        log.info("Update for correct address in letter");
        letterPO.getAddress().click();
        letterPO.getClearFieldAddress().click();
        letterPO.getCorrectAddress().sendText(correctEmail);
        letterPO.clickButtonSend().click();
    }

    @And("^Submit letter with correct address$")
    public String getActualMessage() {
        LetterPO letterPO = new LetterPO();
        log.info("Click sent button and check text in letter");
        letterPO.clickSentButton().click();
        letterPO.waitSentLetter();
        return letterPO.getSentMessage().getText().replace(" - \n", "");
    }

    @Then("^Generate test in letter$")
    public String getExpectedMessage() {
        log.info("Generate text in letter");
        return randomText;
    }
}
