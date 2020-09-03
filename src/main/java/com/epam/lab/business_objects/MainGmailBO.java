package com.epam.lab.business_objects;

import com.epam.lab.page_objects.MainGmailPO;

import static com.epam.lab.singleton.DriverManager.getDriver;
import static com.epam.lab.utils.Constants.GMAIL;

public class MainGmailBO {
    protected MainGmailPO mainGmailPO;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    public MainGmailBO() {
        mainGmailPO = new MainGmailPO();
    }

    public void openGmailPage() {
        log.info("Check loading Gmail page");
        mainGmailPO.typeText(GMAIL);
        log.info("Page title is:" + getDriver().getTitle());
        mainGmailPO.waitTitle(GMAIL);
        mainGmailPO.selectGmailItem();
    }

    public void logIn() {
        log.info("Log in");
        mainGmailPO.logIn();
    }
}
