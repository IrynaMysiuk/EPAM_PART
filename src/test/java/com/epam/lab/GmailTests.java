package com.epam.lab;

import com.epam.lab.page_objects.LetterPO;
import com.epam.lab.page_objects.LogInPO;
import com.epam.lab.page_objects.MainGmailPO;
import com.epam.lab.singleton.DriverManager;
import com.epam.lab.utils.XLSReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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

    private String login;
    private String password;
    private String correctEmail;
    private String subject;
    private String incorrectEmail;

    public GmailTests() {
    }

    @Factory(dataProvider = "currentDataProvider")
    public GmailTests(String login, String password, String correctEmail, String subject, String incorrectEmail) {
        this.login = login;
        this.password = password;
        this.correctEmail = correctEmail;
        this.subject = subject;
        this.incorrectEmail = incorrectEmail;
    }

    @DataProvider(name = "currentDataProvider")
    public Object[][] initUsers() {
        XLSReader xslReader = new XLSReader();
        return xslReader.readXSLfile();
    }

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

    public void sendIncorrectLetter() {
        letterPO.checkLoadPage();
        letterPO.clickSendLetter();
        letterPO.inputIncorrectAddress(incorrectEmail);
        letterPO.inputLetterTheme(subject);
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
        letterPO.inputCorrectAddress(correctEmail);
        letterPO.submitButtonSend();
    }

    public void verifySentLetter() {
        letterPO.clickSentButton();
        letterPO.waitSentLetter();
        String actualMessage = letterPO.getSentMessage().getText().replace(" - \n", "");
        Assert.assertEquals(actualMessage, randomText, "Text message is not the same");
    }
}
