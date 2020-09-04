package com.epam.lab.decorator;

import com.epam.lab.utils.Localization;
import org.openqa.selenium.interactions.Actions;

import static com.epam.lab.singleton.DriverManager.getDriver;

public class NavigationLink extends AbstractPageElement {

    public void click() {
        String buttonName = "";
        if (wrappedElement != null) {
            buttonName = wrappedElement.getText();
            getWebElementWithWait(WaitCondition.VISIBILITY, wrappedElement).click();
        }
        log.debug("we passed to:"
                + getDriver().getCurrentUrl());
        log.info(Localization
                .getMessage(Localization.CLICK_BUTTON, buttonName, page));
    }

    public void moveToElement() {
        Actions builder = new Actions(getDriver());
        wrappedElement = getWebElementWithWait(WaitCondition.VISIBILITY, wrappedElement);
        log.info(Localization
                .getMessage(Localization.ELEMENT_HOVER, wrappedElement.getText(), page));
        builder.moveToElement(wrappedElement).build().perform();
    }

}
