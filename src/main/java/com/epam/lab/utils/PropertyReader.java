package com.epam.lab.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static String driverName;
    public static String pathChromeDriver;
    public static String usersGmailPath;

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
        usersGmailPath = properties.getProperty("usersGmailXLSPath.value");
    }
}