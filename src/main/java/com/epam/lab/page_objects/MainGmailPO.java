package com.epam.lab.page_objects;

import com.epam.lab.decorator.NavigationLink;
import com.epam.lab.decorator.TextField;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import static com.epam.lab.singleton.DriverManager.getWait;

public class MainGmailPO extends AbstractPO {

    @FindBy(name = "q")
    private TextField searchType;
    @FindBy(xpath = "(//div[@id='search']//a)[1]")
    private NavigationLink gmailItem;
    @FindBy(xpath = "(//div[contains(@class,'header--desktop')]//a[@ga-event-action='sign in'])[1]")
    private NavigationLink signInButton;

    public void typeText(String text) {
        searchType.sendText(text);
        searchType.sendText(Keys.ENTER);
    }

    public void selectGmailItem() {
        gmailItem.click();
    }

    public void waitTitle(String titleText) {
        getWait().until((dr) -> dr.getTitle().startsWith(titleText));
    }

    public void logIn() {
        signInButton.click();
    }
}
