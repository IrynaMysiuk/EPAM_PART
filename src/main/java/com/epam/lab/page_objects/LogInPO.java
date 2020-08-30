package com.epam.lab.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

import static com.epam.lab.singleton.DriverManager.getDriver;

public class LogInPO extends AbstractPO {
    @FindBy(xpath = "(//div[contains(@class,'header--desktop')]//a[@ga-event-action='sign in'])[1]")
    private WebElement signInButton;

    public void switchToLogin() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        String handleName = tabs.get(1);
        getDriver().switchTo().window(handleName);
    }
}

