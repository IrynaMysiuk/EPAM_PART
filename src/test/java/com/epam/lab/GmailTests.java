package com.epam.lab;

import com.epam.lab.utils.ModelGmailUsers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.lab.utils.Constants.GMAIL;

public class GmailTests extends BaseTest {

    @Test(description = "Verify send letter with incorrect address", dataProvider = "currentDataProvider")
    public void checkLoginAccount(ModelGmailUsers modelGmailUsers) {
        openGmailPage();
        switchToGmailTab();
        loggingToAccount(modelGmailUsers.getLogin(), modelGmailUsers.getPassword());
        letterBO.sendIncorrectLetter(modelGmailUsers.getIncorrectEmail(), modelGmailUsers.getSubject());
        Assert.assertTrue(letterBO.isAlertTabDisplayed(), "Tab about incorrect address is not displayed");
        letterBO.submitIncorrectAddress();
        letterBO.sendCorrectLetter(modelGmailUsers.getSendTo());
        Assert.assertEquals(letterBO.getActualMessage(), letterBO.getExpectedMessage(),
                "Text message is not the same");
    }

    public void loggingToAccount(String login, String password) {
        logInPO.typeLogin(login);
        logInPO.submitLogin();
        logInPO.typePassword(password);
        logInPO.submitPassword();
    }

    public void openGmailPage() {
        mainGmailPO.typeText(GMAIL);
        log.info("Page title is:" + driver.getTitle());
        mainGmailPO.waitTitle(GMAIL);
        mainGmailPO.selectGmailItem();
        Assert.assertTrue(driver.getTitle().contains(GMAIL), "Title does not contain Gmail word");
        mainGmailPO.logIn();
    }

    public void switchToGmailTab() {
        logInPO.switchToLogin();
    }

}
