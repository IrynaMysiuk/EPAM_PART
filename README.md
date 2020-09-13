# Gmail Selenium Automation Tests / TA_Selenium_PO_Gmail

## About

* It is testing of Gmail page using Selenium
* Project status: working
* Integrated with Cucumber

## Project Structure
1. page_objects - low level logic, path to locators separated in suitable classes
2. bo - business object, common high level logic
3. singleton - Selenium WebDriver with Singleton pattern
4. utils - additional package 
5. bdd - running tests with defined steps (test/java/com/epam/lab/steps) and .feature file (test/resources/validUser.feature)
6. ddt - running tests with data set from .xls file (main/resources/dataGmail.xls)

### Content
**Task 6. Test steps:**

1. Open gmail & login
1. Click on compose button
1. Enter incorrect email in “to” field, fill “subject”/”message” fields & press “send” button 
1. Verify that warning message appears
1. Click “OK” & enter correct email address & click send
1. Verify that message is moved to “Sent mail” folder

### Test Data
Letter info indicated in `src/main/resources/dataGmail.xls`

### Configuration
Look at `src/main/resources/path.properties`

| Name | Default value | Description |
| ------------- | ------------- | ---|
| chromeDriverPath.value  | src/main/resources/chromedriver.exe  | Path to Chrome Driver |
| driver.value  | webdriver.chrome.driver  | Driver name|
|usersGmailXLSPath.value|src/main/resources/dataGmail.xls| Path to Gmail data in .xls|
|browser.name|chrome|Set browser name ("chrome" or "firefox")|
### Reports

After running tests reports are generated in:

* Allure report `allure-results`
Note: execute command `allure serve allure-results`
* TestNG report `target/surefire-reports/index.html`
* Emailable report `targer/surefire-reports/emailable-report.html`

### Build

    mvn clean test
Generate Allure report: 

    allure serve allure-results 
    
### Run
The test suite is running with *5* test accounts by default and multiple data in parallel.
There are *3* test thread in one time. After that we have *2* test thread from *5* test accounts.
Thread pool `src/main/com/epam/lab/singleton/DriverContainer` developed to store web driver instances  

    
### Test Environments

    Chrome Driver (path: src/main/resouces/chromedriver.exe)
    Firefox Driver (path: src/main/resources/geckodriver.exe)

### Developer 
 Iryna Mysiuk - https://bitbucket.org/IrynaMysiuk/