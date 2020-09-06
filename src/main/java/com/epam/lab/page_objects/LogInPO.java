package com.epam.lab.page_objects;

import com.epam.lab.decorator.NavigationLink;
import com.epam.lab.decorator.TextField;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

import static com.epam.lab.singleton.DriverContainer.getDriver;

public class LogInPO extends AbstractPO {

    @FindBy(id = "identifierId")
    private TextField inputLogin;
    @FindBy(xpath = "//div[@id='identifierNext']/div/button")
    private NavigationLink pressSubmitLogin;
    @FindBy(xpath = "//div[@id='password']/div/div/div/input")
    private TextField inputPassword;
    @FindBy(xpath = "//div[@id='passwordNext']/div/button")
    private NavigationLink pressSubmitPassword;

    public void switchToLogin() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        String handleName = tabs.get(1);
        getDriver().switchTo().window(handleName);
    }

    public void typeLogin(String login) {
        inputLogin.sendText(login);
    }

    public void submitLogin() {
        pressSubmitLogin.click();
    }

    public void typePassword(String password) {
        inputPassword.sendText(password);
    }

    public void submitPassword() {
        pressSubmitPassword.click();
    }
}

