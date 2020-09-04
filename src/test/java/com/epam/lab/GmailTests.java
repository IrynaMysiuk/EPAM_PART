package com.epam.lab;

import com.epam.lab.business_objects.MainGmailBO;
import com.epam.lab.utils.ModelGmailUsers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.lab.singleton.DriverManager.getDriver;
import static com.epam.lab.utils.Constants.GMAIL;

public class GmailTests extends BaseTest {

    @Test(description = "Verify send letter with incorrect address", dataProvider = "currentDataProvider")
    public void checkLoginAccount(ModelGmailUsers modelGmailUsers) {
        mainGmailBO.openGmailPage();
        Assert.assertTrue(getDriver().getTitle().contains(GMAIL), "Title does not contain Gmail word");
        mainGmailBO.logIn();
        logInBO.switchToGmailTab();
        logInBO.loggingToAccount(modelGmailUsers.getLogin(), modelGmailUsers.getPassword());
        letterBO.sendIncorrectLetter(modelGmailUsers.getIncorrectEmail(), modelGmailUsers.getSubject());
        Assert.assertTrue(letterBO.isAlertTabDisplayed(), "Tab about incorrect address is not displayed");
        letterBO.submitIncorrectAddress();
        letterBO.sendCorrectLetter(modelGmailUsers.getSendTo());
        Assert.assertEquals(letterBO.getActualMessage(), letterBO.getExpectedMessage(),
                "Text message is not the same");
    }


}
