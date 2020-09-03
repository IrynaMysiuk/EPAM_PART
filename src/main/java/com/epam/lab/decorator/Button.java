package com.epam.lab.decorator;

import com.epam.lab.utils.Localization;

public class Button extends NavigationLink {

    public void submit() {
        if (wrappedElement != null) {
            if (!isClickable(wrappedElement))
                wrappedElement = getWebElementWithWait(WaitCondition.CLICKABLE, wrappedElement);
            wrappedElement.submit();
            log.info(Localization.getMessage(Localization.BUTTON_SUBMIT, wrappedElement.getText(), page));
        } else
            log.error(Localization.getMessage(Localization.NO_BUTTON, name));
    }

}
