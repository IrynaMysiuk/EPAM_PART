package com.epam.lab.decorator;

import com.epam.lab.utils.Localization;

import static com.epam.lab.singleton.DriverContainer.getDriver;

public class NavigationLink extends AbstractPageElement {

    public void click() {
        String buttonName = "";
        if (wrappedElement != null) {
            buttonName = wrappedElement.getText();
            getWebElementWithWait(WaitCondition.VISIBILITY, wrappedElement).click();
        }
        log.debug("Passed to:"
                + getDriver().getCurrentUrl());
        log.info(Localization
                .getMessage(Localization.CLICK_BUTTON, buttonName, page));
    }


}
