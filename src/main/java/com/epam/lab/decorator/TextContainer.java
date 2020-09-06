package com.epam.lab.decorator;

import com.epam.lab.utils.Localization;

public class TextContainer extends AbstractPageElement {

    public String getText() {
        if (wrappedElement != null) {
            try {
                if (!wrappedElement.isDisplayed())
                    wrappedElement = getWebElementWithWait(WaitCondition.VISIBILITY, wrappedElement);
                String containerValue = wrappedElement.getText();
                log.debug(Localization.getMessage(Localization.TEXT_CONTAINER_GET, containerValue, name));
                return containerValue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        log.error(Localization.getMessage(Localization.NO_TEXT_CONTAINER, name));
        return null;
    }
}
