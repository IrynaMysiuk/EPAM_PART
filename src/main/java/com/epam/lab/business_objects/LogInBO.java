package com.epam.lab.business_objects;

import com.epam.lab.page_objects.LogInPO;

import java.util.ArrayList;

import static com.epam.lab.singleton.DriverContainer.getDriver;

public class LogInBO {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);


    public void loggingToAccount(String login, String password) {
        LogInPO logInPO = new LogInPO();
        log.info("Fill login and password to Gmail account");
        log.info("Typing login: " + login);
        logInPO.getLoginField().sendText(login);
        logInPO.getLoginButton().click();
        log.info("Typing password: " + password);
        logInPO.getPasswordField().sendText(password);
        logInPO.getPasswordButton().click();
    }

    public void switchToGmailTab() {
        log.info("Switch to other Gmail Tab");
        switchToLogin();
    }

    private void switchToLogin() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        String handleName = tabs.get(1);
        getDriver().switchTo().window(handleName);
    }
}
