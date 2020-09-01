package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LetterPO;

import java.util.Random;

public class LetterBO {
    protected LetterPO letterPO;
    private String randomText;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    public LetterBO() {
        letterPO = new LetterPO();
    }

    public void sendIncorrectLetter(String incorrectEmail, String subject) {
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
        log.info("Click submit button in alert message with incorrect address");
        letterPO.submitAlertTab();
    }

    public boolean isAlertTabDisplayed() {
        log.info("Check alert tab is displayed");
        return letterPO.getAlertDialog().isDisplayed();
    }

    public void sendCorrectLetter(String correctEmail) {
        log.info("Update for correct address in letter");
        letterPO.changeAddress();
        letterPO.clearFieldAddress();
        letterPO.inputCorrectAddress(correctEmail);
        letterPO.submitButtonSend();
    }

    public String getActualMessage() {
        log.info("Click sent button and check text in letter");
        letterPO.clickSentButton();
        letterPO.waitSentLetter();
        return letterPO.getSentMessage().getText().replace(" - \n", "");
    }

    public String getExpectedMessage() {
        log.info("Generate text in letter");
        return randomText;
    }

}
