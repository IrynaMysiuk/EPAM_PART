package com.epam.lab.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static String driverName;
    public static String driverFirefoxName;
    public static String pathChromeDriver;
    public static String pathFirefoxDriver;
    public static String usersGmailPath;
    public static String browser;

    public void readProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/path.properties")) {
            properties.load(fis);
            initPath(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPath(Properties properties) {
        pathChromeDriver = properties.getProperty("chromeDriverPath.value");
        driverName = properties.getProperty("driver.value");
        pathFirefoxDriver = properties.getProperty("firefoxDriverPath.value");
        driverFirefoxName = properties.getProperty("driver.firefox.value");
        usersGmailPath = properties.getProperty("usersGmailXLSPath.value");
        browser = properties.getProperty("browser.name");
    }
}