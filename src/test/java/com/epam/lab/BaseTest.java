package com.epam.lab;

import com.epam.lab.utils.XLSReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.util.Iterator;

import static com.epam.lab.singleton.DriverContainer.quitDriver;

@Listeners(ListenerTest.class)
public class BaseTest {



    @AfterMethod(alwaysRun = true)
    protected void closeDriver() {
        quitDriver();
    }
}
