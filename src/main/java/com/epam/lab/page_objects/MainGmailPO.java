package com.epam.lab.page_objects;

import com.epam.lab.decorator.NavigationLink;
import com.epam.lab.decorator.TextField;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.lab.singleton.DriverManager.getWait;

public class MainGmailPO extends AbstractPO {

    @FindBy(name = "q")
    private TextField searchType;
    @FindBy(xpath = "(//div[@id='search']//a)[1]")
    private NavigationLink gmailItem;
    @FindBy(xpath = "(//div[contains(@class,'header--desktop')]//a[@ga-event-action='sign in'])[1]")
    private NavigationLink signInButton;
    @FindBy(id = "hplogo")
    private NavigationLink googleLogo;

    public TextField getSearchField() {
        return searchType;
    }

    public NavigationLink getGmailItem() {
        return gmailItem;
    }

    public void waitTitle(String titleText) {
        getWait().until((dr) -> dr.getTitle().startsWith(titleText));
    }

    public NavigationLink getlogIn() {
        return signInButton;
    }

    public void waitGoogleLogo() {
        getWait().until(ExpectedConditions.visibilityOf(googleLogo.getElement()));
    }
}
