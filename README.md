# Gmail Selenium Automation Tests / TA_Selenium_PO_Gmail

## About

* It is testing of Gmail page using Selenium
* Project status: working

## Project Structure
1. page_objects - low level logic, path to locators separated in suitable classes
2. bo - business object, common high level logic
3. singleton - Selenium WebDriver with Singleton pattern
4. utils - additional package 

### Content
Task 6. Test steps:
1. Open gmail & login
2. Click on compose button
3. Enter incorrect email in “to” field, fill “subject”/”message” fields & press “send” button 
4. Verify that warning message appears
5. Click “OK” & enter correct email address & click send
6. Verify that message is moved to “Sent mail” folder

### Test Data
Letter info indicated in `src/main/resources/dataGmail.xls`

### Configuration
Look at `src/main/resources/path.properties`

| Name | Default value | Description |
| ------------- | ------------- | ---|
| chromeDriverPath.value  | src/main/resources/chromedriver.exe  | Path to Chrome Driver |
| driver.value  | webdriver.chrome.driver  | Driver name|
|usersGmailXLSPath.value|src/main/resources/dataGmail.xls| Path to Gmail data in .xls|
### Reports

After running tests reports are generated in:

* TestNG report `target/surefire-reports/index.html`
* Emailable report `targer/surefire-reports/emailable-report.html`

### Build

    mvn clean test

### Developer 
 Iryna Mysiuk - https://bitbucket.org/IrynaMysiuk/