package com.epam.lab;

import com.epam.lab.business_objects.LetterBO;
import com.epam.lab.page_objects.LogInPO;
import com.epam.lab.page_objects.MainGmailPO;
import com.epam.lab.singleton.DriverManager;
import com.epam.lab.utils.XLSReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.util.Iterator;

import static com.epam.lab.singleton.DriverManager.getDriver;
import static com.epam.lab.singleton.DriverManager.setBrowser;
import static com.epam.lab.utils.Constants.URL;

@Listeners(ListenerTest.class)
public class BaseTest {
    protected WebDriver driver;
    protected LogInPO logInPO;
    protected MainGmailPO mainGmailPO;
    protected LetterBO letterBO;
    protected static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GmailTests.class);

    @BeforeMethod
    protected void setupDriver() {
        setBrowser(DriverManager.DriverType.CHROME);
        getDriver().get(URL);
        Assert.assertTrue(getDriver().getCurrentUrl().contains(URL), "Website has incorrect url");
        driver = getDriver();
        logInPO = new LogInPO();
        mainGmailPO = new MainGmailPO();
        letterBO = new LetterBO();

    }

    @DataProvider(name = "currentDataProvider")
    protected Iterator<Object[]> initUsers() {
        XLSReader xslReader = new XLSReader();
        return xslReader.readXSLfile().stream().map(email -> new Object[]{email}).iterator();
    }

    @AfterMethod
    protected void closeDriver() {
        getDriver().quit();
    }
}
