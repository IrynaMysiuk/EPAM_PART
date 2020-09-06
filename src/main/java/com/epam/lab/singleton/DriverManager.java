package com.epam.lab.singleton;

import com.epam.lab.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.epam.lab.utils.Constants.URL;

public class DriverManager {

    private static WebDriverWait wait;

    private DriverManager() {
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static WebDriver getDriver(DriverType browser) {
        WebDriver driver;
        System.setProperty(PropertyReader.driverName, PropertyReader.pathChromeDriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
        wait = new WebDriverWait(driver, 40);
        return driver;
    }

    public enum DriverType {
        CHROME,
        FIREFOX
    }

}