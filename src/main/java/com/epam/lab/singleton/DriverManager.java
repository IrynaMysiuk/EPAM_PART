package com.epam.lab.singleton;

import com.epam.lab.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.epam.lab.utils.Constants.*;

public class DriverManager {

    private static WebDriverWait wait;

    private DriverManager() {
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static WebDriver getDriver(DriverType browser) {
        WebDriver driver;
        if (browser == DriverType.FIREFOX) {
            System.setProperty(PropertyReader.driverFirefoxName, PropertyReader.pathFirefoxDriver);
            driver = new FirefoxDriver();
        } else {
            System.setProperty(PropertyReader.driverName, PropertyReader.pathChromeDriver);
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(START_WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
        wait = new WebDriverWait(driver, WAIT_TIME_OUT);
        return driver;
    }

    public enum DriverType {
        CHROME,
        FIREFOX
    }

}