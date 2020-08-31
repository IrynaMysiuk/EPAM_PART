package com.epam.lab;

import com.epam.lab.page_objects.LetterPO;
import com.epam.lab.page_objects.LogInPO;
import com.epam.lab.page_objects.MainGmailPO;
import com.epam.lab.singleton.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;

import static com.epam.lab.singleton.DriverManager.getDriver;
import static com.epam.lab.singleton.DriverManager.setBrowser;
import static com.epam.lab.utils.Constants.*;
@Listeners(ListenerTest.class)
public class GmailTests {


    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GmailTests.class);
    private WebDriver driver;
    private String randomText;
    private LogInPO logInPO;
    private MainGmailPO mainGmailPO;
    private LetterPO letterPO;

    @BeforeMethod
    public void setupDriver() {
        setBrowser(DriverManager.DriverType.CHROME);
        getDriver().get(URL);
        Assert.assertTrue(getDriver().getCurrentUrl().contains(URL), "Website has incorrect url");
        driver = getDriver();
        logInPO = new LogInPO();
        mainGmailPO = new MainGmailPO();
        letterPO = new LetterPO();
    }

    @Test(description = "Verify send letter with incorrect address")
    public void checkLoginAccount() {
        openGmailPage();
        switchToGmailTab();
        loggingToAccount();
        sendIncorrectLetter();
        verifySentIncorrectLetter();
        sendCorrectLetter();
        verifySentLetter();
    }

    @AfterMethod
    public void closeDriver() {
        getDriver().quit();
    }

    public void loggingToAccount() {
        logInPO.typeLogin(GMAIL_LOGIN);
        logInPO.submitLogin();
        logInPO.typePassword(GMAIL_PASSWORD);
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

    public void sendIncorrectLetter() {
        letterPO.checkLoadPage();
        letterPO.clickSendLetter();
        letterPO.inputIncorrectAddress(INCORRECT_ADDRESS);
        letterPO.inputLetterTheme(INPUT_THEME);
        randomText = String.valueOf(new Random().nextInt(10000));
        letterPO.inputTextBox(randomText);
        letterPO.checkCompleteLetter();
    }

    public void verifySentIncorrectLetter() {
        letterPO.getAlertDialog();
        Assert.assertTrue(letterPO.getAlertDialog().isDisplayed(), "Tab about incorrect address is not displayed");
        letterPO.submitAlertTab();
    }

    public void sendCorrectLetter() {
        letterPO.changeAddress();
        letterPO.clearFieldAddress();
        letterPO.inputCorrectAddress(CORRECT_ADDRESS);
        letterPO.submitButtonSend();
    }

    public void verifySentLetter() {
        letterPO.clickSentButton();
        letterPO.waitSentLetter();
        String actualMessage = letterPO.getSentMessage().getText().replace(" - \n", "");
        Assert.assertEquals(actualMessage, randomText, "Text message is not the same");
    }
}
