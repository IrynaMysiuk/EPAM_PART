package com.epam.lab.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.lab.singleton.DriverManager.getWait;

public class MainGmailPO extends AbstractPO {
    @FindBy(name = "q")
    private WebElement searchType;
    @FindBy(xpath = "(//div[@id='search']//a)[1]")
    private WebElement gmailItem;

    @FindBy(xpath = "(//div[contains(@class,'header--desktop')]//a[@ga-event-action='sign in'])[1]")
    private WebElement signInButton;

    public void typeText(String text) {
        WebElement searchInput = getWebElementWithWait(WaitCondition.VISIBILITY, searchType);
        searchInput.sendKeys(text);
        searchInput.submit();
    }

    public void selectGmailItem() {
        getWebElementWithWait(WaitCondition.CLICKABLE, gmailItem).click();
    }

    public void waitTitle(String titleText) {
        getWait().until((dr) -> dr.getTitle().startsWith(titleText));
    }

    public void logIn() {
        getWebElementWithWait(WaitCondition.CLICKABLE, signInButton).click();
    }

}
