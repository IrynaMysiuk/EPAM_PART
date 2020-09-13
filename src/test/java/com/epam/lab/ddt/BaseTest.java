package com.epam.lab.ddt;

import com.epam.lab.ListenerTest;
import com.epam.lab.utils.XLSReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.util.Iterator;

import static com.epam.lab.singleton.DriverContainer.quitDriver;

@Listeners(ListenerTest.class)
public class BaseTest {

    @DataProvider(name = "currentDataProvider", parallel = true)
    protected Iterator<Object[]> initUsers() {
        XLSReader xslReader = new XLSReader();
        return xslReader.readXSLfile().stream().map(email -> new Object[]{email}).parallel().iterator();
    }

    @AfterMethod(alwaysRun = true)
    protected void closeDriver() {
        quitDriver();
    }
}
