package com.epam.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GmailTests {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GmailTests.class);
    private WebDriver driver;
    private String randomText;
    private WebDriverWait wait;

    public GmailTests() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @BeforeMethod
    public void setupDriver() {
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
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
        driver.quit();
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
        WebElement inputField = driver.findElement(By.name("q"));
        inputField.sendKeys("Gmail");
        inputField.submit();
        log.info("Page title is:" + driver.getTitle());
        (new WebDriverWait(driver, 50)).until((dr) -> dr.getTitle().startsWith("Gmail"));
        WebElement getPostPage = driver.findElement(By.xpath("//div[@id='rso']/div/div/div/div/a"));
        getPostPage.click();
        Assert.assertTrue(driver.getTitle().contains("Gmail"), "Title does not contain Gmail word");
        WebElement logInToAccount = driver.findElement
                (By.xpath("(//div[contains(@class,'header--desktop')]//a[@ga-event-action='sign in'])[1]"));
        logInToAccount.click();
    }


    public void switchToGmailTab() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        String handleName = tabs.get(1);
        driver.switchTo().window(handleName);
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
        By changeOnCorrectAddress=By.xpath("//div/div/span[contains(text(),\"ira.mysiuk.com\")]");
        driver.findElement(changeOnCorrectAddress).click();
        By clearField = By.xpath("//span/div[contains(text(),'ira.mysiuk.com')]/following-sibling::div");
           driver.findElement(clearField).click();
        By typeCorrectAddress=By.xpath("//textarea[@role='combobox']");
          driver.findElement(typeCorrectAddress).sendKeys("ira.mysiuk@gmail.com");
        By typeGoal = By.xpath("//div/input[@name='subjectbox']");
        driver.findElement(typeGoal).sendKeys("Important message from IntellijIDEA");
        By typeText = By.xpath("//div[@role='textbox']");
        randomText = String.valueOf(new Random().nextInt(10000));
        driver.findElement(typeText).sendKeys(randomText);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@role='group']//div[@role='button'])[1]")));
        WebElement pressSendButton = driver.findElement(By.xpath("(//table[@role=\"group\"]//div[@role='button'])[1]"));
        pressSendButton.click();
    }

    public void verifySentLetter() {
        WebElement sentButton = driver.findElement(By.xpath("//a[@aria-label=\"Надіслані\"]"));
        sentButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tbody/tr/td[@role=\"gridcell\"])[8]/span/span")));
        WebElement sentMessages = driver.findElement(By.xpath("(//tbody/tr/td[@role=\"gridcell\"]//span)[20]"));
        String actualMessage = sentMessages.getText().replace(" - \n", "");
        Assert.assertEquals(actualMessage, randomText, "Text message is not the same");
    }
}
