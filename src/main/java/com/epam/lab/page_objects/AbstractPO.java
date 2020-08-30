package com.epam.lab.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.lab.singleton.DriverManager.getDriver;
import static com.epam.lab.singleton.DriverManager.getWait;

public class AbstractPO {

    protected AbstractPO() {
        PageFactory.initElements(getDriver(), this);
    }


    protected WebElement getWebElementWithWait(WaitCondition waitCondition, WebElement element) {
        if (waitCondition.equals(WaitCondition.VISIBILITY))
            return getWait().until(ExpectedConditions.visibilityOf(element));
        else if (waitCondition.equals(WaitCondition.CLICKABLE))
            return getWait().until(ExpectedConditions.elementToBeClickable(element));
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public enum WaitCondition {
        VISIBILITY,
        CLICKABLE
    }
}