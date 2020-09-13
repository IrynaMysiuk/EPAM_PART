package com.epam.lab;

import com.epam.lab.business_objects.LetterBO;
import com.epam.lab.business_objects.LogInBO;
import com.epam.lab.business_objects.MainGmailBO;
import com.epam.lab.utils.ModelGmailUsers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.lab.singleton.DriverContainer.getDriver;
import static com.epam.lab.utils.Constants.GMAIL;
import static com.epam.lab.utils.Constants.URL;

public class GmailTests extends BaseTest {

    @Test(description = "Verify send letter with incorrect address", dataProvider = "currentDataProvider")
    public void checkLoginAccount(ModelGmailUsers modelGmailUsers) {
        MainGmailBO mainGmailBO = new MainGmailBO();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(URL), "Website has incorrect url");
        mainGmailBO.openGmailPage();
        Assert.assertTrue(getDriver().getTitle().contains(GMAIL), "Title does not contain Gmail word");
        mainGmailBO.logIn();
        LogInBO logInBO = new LogInBO();
        logInBO.switchToGmailTab();
        logInBO.loggingToAccount(modelGmailUsers.getLogin(), modelGmailUsers.getPassword());
        LetterBO letterBO = new LetterBO();
        letterBO.sendIncorrectLetter(modelGmailUsers.getIncorrectEmail(), modelGmailUsers.getSubject());
        Assert.assertTrue(letterBO.isAlertTabDisplayed(), "Tab about incorrect address is not displayed");
        letterBO.submitIncorrectAddress();
        letterBO.sendCorrectLetter(modelGmailUsers.getSendTo());
        Assert.assertEquals(letterBO.getActualMessage(), letterBO.getExpectedMessage(),
                "Text message is not the same");
    }
}
