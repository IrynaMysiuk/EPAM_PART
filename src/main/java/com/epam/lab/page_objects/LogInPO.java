package com.epam.lab.page_objects;

import com.epam.lab.decorator.NavigationLink;
import com.epam.lab.decorator.TextField;
import org.openqa.selenium.support.FindBy;

public class LogInPO extends AbstractPO {

    @FindBy(id = "identifierId")
    private TextField inputLogin;
    @FindBy(xpath = "//div[@id='identifierNext']/div/button")
    private NavigationLink pressSubmitLogin;
    @FindBy(xpath = "//div[@id='password']/div/div/div/input")
    private TextField inputPassword;
    @FindBy(xpath = "//div[@id='passwordNext']/div/button")
    private NavigationLink pressSubmitPassword;

    public TextField getLoginField() {
        return inputLogin;
    }

    public NavigationLink getLoginButton() {
        return pressSubmitLogin;
    }

    public TextField getPasswordField() {
        return inputPassword;
    }

    public NavigationLink getPasswordButton() {
        return pressSubmitPassword;
    }
}

