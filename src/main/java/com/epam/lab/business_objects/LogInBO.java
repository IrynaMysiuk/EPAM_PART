package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LogInPO;

public class LogInBO {
    protected LogInPO logInPO;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    public LogInBO() {
        logInPO = new LogInPO();
    }

    public void loggingToAccount(String login, String password) {
        log.info("Fill login and password to Gmail account");
        logInPO.typeLogin(login);
        logInPO.submitLogin();
        logInPO.typePassword(password);
        logInPO.submitPassword();
    }

    public void switchToGmailTab() {
        log.info("Switch to other Gmail Tab");
        logInPO.switchToLogin();
    }
}
