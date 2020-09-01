package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LetterPO;

import java.util.Random;

public class LetterBO {
    protected LetterPO letterPO;
    private String randomText;


    public LetterBO() {
        letterPO = new LetterPO();
    }

    public void sendIncorrectLetter(String incorrectEmail, String subject) {
        letterPO.checkLoadPage();
        letterPO.clickSendLetter();
        letterPO.inputIncorrectAddress(incorrectEmail);
        letterPO.inputLetterTheme(subject);
        randomText = String.valueOf(new Random().nextInt(10000));
        letterPO.inputTextBox(randomText);
        letterPO.checkCompleteLetter();
    }

    public void submitIncorrectAddress() {
        letterPO.submitAlertTab();
    }

    public boolean isAlertTabDisplayed() {
        return letterPO.getAlertDialog().isDisplayed();
    }

    public void sendCorrectLetter(String correctEmail) {
        letterPO.changeAddress();
        letterPO.clearFieldAddress();
        letterPO.inputCorrectAddress(correctEmail);
        letterPO.submitButtonSend();
    }

    public String getActualMessage() {
        letterPO.clickSentButton();
        letterPO.waitSentLetter();
        return letterPO.getSentMessage().getText().replace(" - \n", "");
    }

    public String getExpectedMessage() {
        return randomText;
    }

}
