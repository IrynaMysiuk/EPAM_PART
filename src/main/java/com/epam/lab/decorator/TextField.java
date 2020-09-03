package com.epam.lab.decorator;

import com.epam.lab.utils.Localization;
import org.openqa.selenium.Keys;

public class TextField extends AbstractPageElement {

    public void sendText(String text) {
        if (wrappedElement != null) {
            if (!wrappedElement.isDisplayed())
                wrappedElement = getWebElementWithWait(WaitCondition.VISIBILITY, wrappedElement);
            clear();
            wrappedElement.sendKeys(text);
            log.info(Localization.getMessage(Localization.INPUT_SET_VALUE, text, name, page));
        } else
            log.error(Localization.getMessage(Localization.NO_INPUT, name, page));
    }

    public void sendText(Keys key) {
        if (wrappedElement != null) {
            if (!wrappedElement.isDisplayed())
                wrappedElement = getWebElementWithWait(WaitCondition.VISIBILITY, wrappedElement);
            wrappedElement.sendKeys(key);
            log.info(Localization.getMessage(Localization.INPUT_SET_VALUE, key.name(), name, page));
        } else
            log.error(Localization.getMessage(Localization.NO_INPUT, name, page));
    }

    public void clear() {
        if (wrappedElement != null) {
            wrappedElement.clear();
            log.debug("Clear element");
        } else
            log.error(Localization
                    .getMessage(Localization.NO_INPUT, name, page));
    }

}
