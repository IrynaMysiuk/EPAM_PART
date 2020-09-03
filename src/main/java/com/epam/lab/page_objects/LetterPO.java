package com.epam.lab.page_objects;

import com.epam.lab.decorator.AbstractPageElement;
import com.epam.lab.decorator.NavigationLink;
import com.epam.lab.decorator.TextContainer;
import com.epam.lab.decorator.TextField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LetterPO extends AbstractPO {

    @FindBy(css = "a[title='Gmail'] > img")
    private AbstractPageElement checkLoadGmailPage;
    @FindBy(css = "div[gh='cm']")
    private NavigationLink pressSendLetter;
    @FindBy(xpath = "//textarea[@role='combobox']")
    private TextField typeIncorrectAddress;
    @FindBy(xpath = "//div/input[@name='subjectbox']")
    private TextField typeTheme;
    @FindBy(xpath = "//div[@role='textbox']")
    private TextField typeText;
    @FindBy(xpath = "(//table[@role='group']//div[@role='button'])[1]")
    private NavigationLink clickButtonSentLetter;
    @FindBy(xpath = "//div[@role='alertdialog']")
    private AbstractPageElement checkAlertTab;
    @FindBy(xpath = "//button[@name='ok']")
    private NavigationLink submitAlertTab;
    @FindBy(xpath = "//div/div/span[contains(text(),\"ira.mysiuk.com\")]")
    private NavigationLink changeOnCorrectAddress;
    @FindBy(xpath = "//span/div[contains(text(),'ira.mysiuk.com')]/following-sibling::div")
    private NavigationLink clearAddressField;
    @FindBy(xpath = "//textarea[@role='combobox']")
    private TextField typeCorrectAddress;
    @FindBy(xpath = "(//table[@role='group']//div[@role='button'])[1]")
    private NavigationLink pressSentLetter;
    @FindBy(xpath = "(//div[@role='navigation']//div/span/a)[4]")
    private NavigationLink sentButton;
    @FindBy(xpath = "(//tbody/tr/td[@role=\"gridcell\"])[8]/span/span")
    private AbstractPageElement waitSendLetter;
    @FindBy(xpath = "(//tbody/tr/td[@role=\"gridcell\"]//span)[20]")
    private TextContainer sentMessage;

    public void checkLoadPage() {
        checkLoadGmailPage.getElement();
    }

    public void clickSendLetter() {
        pressSendLetter.click();
    }

    public void inputIncorrectAddress(String incorrectText) {
        typeIncorrectAddress.sendText(incorrectText);
    }

    public void inputLetterTheme(String inputTheme) {
        typeTheme.sendText(inputTheme);
    }

    public void inputTextBox(String text) {
        typeText.sendText(text);
    }

    public void checkCompleteLetter() {
        clickButtonSentLetter.click();
    }

    public WebElement getAlertDialog() {
        return checkAlertTab.getElement();
    }

    public void submitAlertTab() {
        submitAlertTab.click();
    }

    public void changeAddress() {
        changeOnCorrectAddress.click();
    }

    public void clearFieldAddress() {
        clearAddressField.click();
    }

    public void inputCorrectAddress(String correctText) {
        typeCorrectAddress.sendText(correctText);
    }

    public void submitButtonSend() {
        pressSendLetter.click();
    }

    public NavigationLink getSentButton() {
        return sentButton;
    }

    public void clickSentButton() {
        getSentButton().click();
    }

    public void waitSentLetter() {
        waitSendLetter.getElement();
    }

    public String getSentMessage() {
        return sentMessage.getText();
    }
}
