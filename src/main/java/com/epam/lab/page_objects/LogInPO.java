package com.epam.lab.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

import static com.epam.lab.singleton.DriverManager.getDriver;

public class LogInPO extends AbstractPO {
    @FindBy(id = "identifierId")
    private WebElement inputLogin;
    @FindBy(xpath = "//div[@id='identifierNext']/div/button")
    private WebElement pressSubmitLogin;
    @FindBy(xpath = "//div[@id='password']/div/div/div/input")
    private WebElement inputPassword;
    @FindBy(xpath = "//div[@id='passwordNext']/div/button")
    private WebElement pressSubmitPassword;

    public void switchToLogin() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        String handleName = tabs.get(1);
        getDriver().switchTo().window(handleName);
    }

    public void typeLogin(String login) {
        WebElement typeLogin = getWebElementWithWait(WaitCondition.VISIBILITY, inputLogin);
        typeLogin.sendKeys(login);
    }

    public void submitLogin() {
        WebElement clickContinue = getWebElementWithWait(WaitCondition.CLICKABLE, pressSubmitLogin);
        clickContinue.click();
    }

    public void typePassword(String password) {
        WebElement typePassword = getWebElementWithWait(WaitCondition.VISIBILITY, inputPassword);
        typePassword.sendKeys(password);

    }

    public void submitPassword() {
        WebElement clickContinueButton = getWebElementWithWait(WaitCondition.CLICKABLE, pressSubmitPassword);
        clickContinueButton.click();
    }
}

