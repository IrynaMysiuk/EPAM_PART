package com.epam.lab.page_objects;

import com.epam.lab.decorator.AbstractPageElement;
import com.epam.lab.decorator.NavigationLink;
import com.epam.lab.decorator.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.lab.singleton.DriverContainer.getDriver;

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
    private String sentMessage = "(//tbody/tr/td[@role=\"gridcell\"]//span[contains(text(), \"%s\")])";

    public void getAndWaitLoadPage() {
        checkLoadGmailPage.getElement();
    }

    public NavigationLink getSendLetter() {
        return pressSendLetter;
    }

    public TextField getIncorrectAddress() {
        return typeIncorrectAddress;
    }

    public TextField getLetterTheme() {
        return typeTheme;
    }

    public TextField getTextBox() {
        return typeText;
    }

    public NavigationLink getCompleteLetter() {
        return clickButtonSentLetter;
    }

    public WebElement getAlertDialog() {
        return checkAlertTab.getElement();
    }

    public NavigationLink getAlertTab() {
        return submitAlertTab;
    }

    public NavigationLink getAddress() {
        return changeOnCorrectAddress;
    }

    public NavigationLink getClearFieldAddress() {
        return clearAddressField;
    }

    public TextField getCorrectAddress() {
        return typeCorrectAddress;
    }

    public NavigationLink clickButtonSend() {
        return pressSentLetter;
    }

    public NavigationLink clickSentButton() {
        return sentButton;
    }

    public void waitSentLetter() {
        waitSendLetter.getElement();
    }

    public WebElement getSentMessage(String randomText) {
        return getDriver().findElement(By.xpath(String.format(sentMessage, randomText)));
    }
}
