package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LetterPO;

import java.util.Random;

public class LetterBO {
    private String randomText;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

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

    public void submitIncorrectAddress() {
        LetterPO letterPO = new LetterPO();
        log.info("Click submit button in alert message with incorrect address");
        letterPO.getAlertTab().click();
    }

    public boolean isAlertTabDisplayed() {
        LetterPO letterPO = new LetterPO();
        log.info("Check alert tab is displayed");
        return letterPO.getAlertDialog().isDisplayed();
    }

    public void sendCorrectLetter(String correctEmail) {
        LetterPO letterPO = new LetterPO();
        log.info("Update for correct address in letter");
        letterPO.getAddress().click();
        letterPO.getClearFieldAddress().click();
        letterPO.getCorrectAddress().sendText(correctEmail);
        letterPO.clickButtonSend().click();
    }

    public String getActualMessage() {
        LetterPO letterPO = new LetterPO();
        log.info("Click sent button and check text in letter");
        letterPO.clickSentButton().click();
        letterPO.waitSentLetter();
        return letterPO.getSentMessage().getText().replace(" - \n", "");
    }

    public String getExpectedMessage() {
        log.info("Generate text in letter");
        return randomText;
    }

}
