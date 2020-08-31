package com.epam.lab.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LetterPO extends AbstractPO {

    @FindBy(css = "a[title='Gmail'] > img")
    private WebElement checkLoadGmailPage;
    @FindBy(css = "div[gh='cm']")
    private WebElement pressSendLetter;
    @FindBy(xpath = "//textarea[@role='combobox']")
    private WebElement typeIncorrectAddress;
    @FindBy(xpath = "//div/input[@name='subjectbox']")
    private WebElement typeTheme;
    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement typeText;
    @FindBy(xpath = "(//table[@role='group']//div[@role='button'])[1]")
    private WebElement clickButtonSentLetter;
    @FindBy(xpath = "//div[@role='alertdialog']")
    private WebElement checkAlertTab;
    @FindBy(xpath = "//button[@name='ok']")
    private WebElement submitAlertTab;
    @FindBy(xpath = "//div/div/span[contains(text(),\"ira.mysiuk.com\")]")
    private WebElement changeOnCorrectAddress;
    @FindBy(xpath = "//span/div[contains(text(),'ira.mysiuk.com')]/following-sibling::div")
    private WebElement clearAddressField;
    @FindBy(xpath = "//textarea[@role='combobox']")
    private WebElement typeCorrectAddress;
    @FindBy(xpath = "(//table[@role='group']//div[@role='button'])[1]")
    private WebElement pressSentLetter;
    @FindBy(xpath = "(//div[@role='navigation']//div/span/a)[4]")
    private WebElement sentButton;
    @FindBy(xpath = "(//tbody/tr/td[@role=\"gridcell\"])[8]/span/span")
    private WebElement waitSendLetter;
    @FindBy(xpath = "(//tbody/tr/td[@role=\"gridcell\"]//span)[20]")
    private WebElement sentMessage;

    public void checkLoadPage() {
        getWebElementWithWait(WaitCondition.VISIBILITY, checkLoadGmailPage);
    }

    public void clickSendLetter() {
        WebElement clickSent = getWebElementWithWait(WaitCondition.CLICKABLE, pressSendLetter);
        clickSent.click();
    }

    public void inputIncorrectAddress(String incorrectText) {
        WebElement typeIncorrect = getWebElementWithWait(WaitCondition.VISIBILITY, typeIncorrectAddress);
        typeIncorrect.sendKeys(incorrectText);
    }

    public void inputLetterTheme(String inputTheme) {
        getWebElementWithWait(WaitCondition.CLICKABLE, typeTheme).sendKeys(inputTheme);
    }

    public void inputTextBox(String text) {
        getWebElementWithWait(WaitCondition.VISIBILITY, typeText).sendKeys(text);
    }

    public void checkCompleteLetter() {
        getWebElementWithWait(WaitCondition.VISIBILITY, clickButtonSentLetter).click();
    }

    public WebElement getAlertDialog() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, checkAlertTab);
    }

    public void submitAlertTab() {
        getWebElementWithWait(WaitCondition.CLICKABLE, submitAlertTab).click();
    }

    public void changeAddress() {
        getWebElementWithWait(WaitCondition.CLICKABLE, changeOnCorrectAddress).click();
    }

    public void clearFieldAddress() {
        getWebElementWithWait(WaitCondition.CLICKABLE, clearAddressField).click();
    }

    public void inputCorrectAddress(String correctText) {
        WebElement typeCorrect = getWebElementWithWait(WaitCondition.VISIBILITY, typeCorrectAddress);
        typeCorrect.sendKeys(correctText);
    }

    public void submitButtonSend() {
        getWebElementWithWait(WaitCondition.CLICKABLE, pressSentLetter).click();
    }

    public WebElement getSentButton() {
        return getWebElementWithWait(WaitCondition.CLICKABLE, sentButton);
    }

    public void clickSentButton() {
        getSentButton().click();
    }

    public void waitSentLetter() {
        getWebElementWithWait(WaitCondition.VISIBILITY, waitSendLetter);
    }

    public WebElement getSentMessage() {
        return getWebElementWithWait(WaitCondition.CLICKABLE, sentMessage);
    }
}
