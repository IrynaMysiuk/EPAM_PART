package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LetterPO;

import java.util.Random;

public class LetterBO {
    private String randomText;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);


    public void sendIncorrectLetter(String incorrectEmail, String subject) {
        LetterPO letterPO = new LetterPO();
        log.info("Sending incorrect address in letter");
        letterPO.checkLoadPage();
        letterPO.clickSendLetter();
        letterPO.inputIncorrectAddress(incorrectEmail);
        letterPO.inputLetterTheme(subject);
        randomText = String.valueOf(new Random().nextInt(10000));
        letterPO.inputTextBox(randomText);
        letterPO.checkCompleteLetter();
    }

    public void submitIncorrectAddress() {
        LetterPO letterPO = new LetterPO();
        log.info("Click submit button in alert message with incorrect address");
        letterPO.submitAlertTab();
    }

    public boolean isAlertTabDisplayed() {
        LetterPO letterPO = new LetterPO();
        log.info("Check alert tab is displayed");
        return letterPO.getAlertDialog().isDisplayed();
    }

    public void sendCorrectLetter(String correctEmail) {
        LetterPO letterPO = new LetterPO();
        log.info("Update for correct address in letter");
        letterPO.changeAddress();
        letterPO.clearFieldAddress();
        letterPO.inputCorrectAddress(correctEmail);
        letterPO.submitButtonSend();
    }

    public String getActualMessage() {
        LetterPO letterPO = new LetterPO();
        log.info("Click sent button and check text in letter");
        letterPO.clickSentButton();
        letterPO.waitSentLetter();
        return letterPO.getSentMessage().replace(" - \n", "");
    }

    public String getExpectedMessage() {
        log.info("Generate text in letter");
        return randomText;
    }

}
