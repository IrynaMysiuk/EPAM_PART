package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LogInPO;

public class LogInBO {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);


    public void loggingToAccount(String login, String password) {
        LogInPO logInPO = new LogInPO();
        log.info("Fill login and password to Gmail account");
        log.info("Typing login: " + login);
        logInPO.typeLogin(login);
        logInPO.submitLogin();
        log.info("Typing password: " + password);
        logInPO.typePassword(password);
        logInPO.submitPassword();
    }

    public void switchToGmailTab() {
        LogInPO logInPO = new LogInPO();
        log.info("Switch to other Gmail Tab");
        logInPO.switchToLogin();
    }
}
