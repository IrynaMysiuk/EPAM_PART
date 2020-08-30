package com.epam.lab;

import com.epam.lab.page_objects.LogInPO;
import com.epam.lab.page_objects.MainGmailPO;
import com.epam.lab.singleton.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.epam.lab.singleton.DriverManager.getDriver;
import static com.epam.lab.singleton.DriverManager.setBrowser;
import static com.epam.lab.utils.Constants.URL;

public class GmailTests {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GmailTests.class);
    private WebDriver driver;
    private String randomText;
    private WebDriverWait wait;

    @BeforeMethod
    public void setupDriver() {
        setBrowser(DriverManager.DriverType.CHROME);
        getDriver().get(URL);
        Assert.assertTrue(getDriver().getCurrentUrl().contains(URL), "Website has incorrect url");
        driver = getDriver();
        wait = new WebDriverWait(driver, 30);

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
        By inputPath = By.id("identifierId");
        driver.findElement(inputPath).sendKeys("seleniumlab12@gmail.com");
        WebElement pressContinueButton = driver.findElement(By.xpath("//div[@id='identifierNext']/div/button"));
        pressContinueButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        By inputPassword = By.xpath("//div[@id='password']/div/div/div/input");
        driver.findElement(inputPassword).sendKeys("selenium12");
        WebElement pressSubmitPassword = driver.findElement(By.xpath("//div[@id='passwordNext']/div/button"));
        pressSubmitPassword.click();
    }

    public void openGmailPage() {
        MainGmailPO mainGmailPO = new MainGmailPO();
        mainGmailPO.typeText("Gmail");
        log.info("Page title is:" + driver.getTitle());
        mainGmailPO.waitTitle("Gmail");
        mainGmailPO.selectGmailItem();
        Assert.assertTrue(driver.getTitle().contains("Gmail"), "Title does not contain Gmail word");
        mainGmailPO.logIn();
    }


    public void switchToGmailTab() {
        LogInPO logInPO = new LogInPO();
        logInPO.switchToLogin();
    }

    public void sendIncorrectLetter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Gmail'] > img")));
        WebElement pressSentButton = driver.findElement(By.cssSelector("div[gh='cm']"));
        pressSentButton.click();
        By typeReceiver = By.xpath("//textarea[@role='combobox']");
        driver.findElement(typeReceiver).sendKeys("ira.mysiuk.com");
        By typeGoal = By.xpath("//div/input[@name='subjectbox']");
        driver.findElement(typeGoal).sendKeys("Important message from IntellijIDEA");
        By typeText = By.xpath("//div[@role='textbox']");
        randomText = String.valueOf(new Random().nextInt(10000));
        driver.findElement(typeText).sendKeys(randomText);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@role='group']//div[@role='button'])[1]")));
        WebElement pressSendButton = driver.findElement(By.xpath("(//table[@role=\"group\"]//div[@role='button'])[1]"));
        pressSendButton.click();
    }

    public void verifySentIncorrectLetter() {
        WebElement alertTab = driver.findElement(By.xpath("//div[@role='alertdialog']"));
        Assert.assertTrue(alertTab.isDisplayed(), "Tab about incorrect address is not displayed");
        WebElement submitTabIncorrectAddress = driver.findElement(By.xpath("//button[@name='ok']"));
        submitTabIncorrectAddress.click();
    }

    public void sendCorrectLetter() {
        By changeOnCorrectAddress = By.xpath("//div/div/span[contains(text(),\"ira.mysiuk.com\")]");
        driver.findElement(changeOnCorrectAddress).click();
        By clearField = By.xpath("//span/div[contains(text(),'ira.mysiuk.com')]/following-sibling::div");
        driver.findElement(clearField).click();
        By typeCorrectAddress = By.xpath("//textarea[@role='combobox']");
        driver.findElement(typeCorrectAddress).sendKeys("ira.mysiuk@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@role='group']//div[@role='button'])[1]")));
        WebElement pressSendButton = driver.findElement(By.xpath("(//table[@role=\"group\"]//div[@role='button'])[1]"));
        pressSendButton.click();
    }

    public void verifySentLetter() {
        (new WebDriverWait(driver, 50)).until((dr) -> dr.findElement(By.xpath("(//div[@role='navigation']//div/span/a)[4]")).isDisplayed());
        WebElement sentButton = driver.findElement(By.xpath("(//div[@role='navigation']//div/span/a)[4]"));
        sentButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tbody/tr/td[@role=\"gridcell\"])[8]/span/span")));
        WebElement sentMessages = driver.findElement(By.xpath("(//tbody/tr/td[@role=\"gridcell\"]//span)[20]"));
        String actualMessage = sentMessages.getText().replace(" - \n", "");
        Assert.assertEquals(actualMessage, randomText, "Text message is not the same");
    }
}
