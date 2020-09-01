package com.epam.lab.utils;

public class ModelGmailUsers {

    private String login;
    private String password;
    private String sendTo;
    private String subject;
    private String incorrectEmail;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String toAddress) {
        this.sendTo = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIncorrectEmail() {
        return incorrectEmail;
    }

    public void setIncorrectEmail(String incorrectEmail) {
        this.incorrectEmail = incorrectEmail;
    }

}
