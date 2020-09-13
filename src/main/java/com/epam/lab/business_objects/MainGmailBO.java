package com.epam.lab.business_objects;

import com.epam.lab.page_objects.MainGmailPO;
import org.openqa.selenium.Keys;

import static com.epam.lab.singleton.DriverContainer.getDriver;
import static com.epam.lab.utils.Constants.GMAIL;

public class MainGmailBO {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    public void openGmailPage() {
        MainGmailPO mainGmailPO = new MainGmailPO();
        mainGmailPO.waitGoogleLogo();
        log.info("Check loading Gmail page");
        mainGmailPO.getSearchField().sendText(GMAIL);
        mainGmailPO.getSearchField().sendText(Keys.ENTER);
        log.info("Page title is:" + getDriver().getTitle());
        mainGmailPO.waitTitle(GMAIL);
        mainGmailPO.getGmailItem().click();
    }

    public void logIn() {
        MainGmailPO mainGmailPO = new MainGmailPO();
        log.info("Log in");
        mainGmailPO.getlogIn().click();
    }
}
