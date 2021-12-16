Manual Test Case:

1. Open url http://weathershopper.pythonanywhere.com/
2. Click on Buy Moisturizers.
3. Add two moisturizers to the cart. First, select the least expensive moisturizer that contains Aloe. For your second moisturizer, select the least expensive moisturizer that contains almond.
4. Verify that products are added to cart and click on Cart button.
5. Verify that the shopping cart looks correct. Products are correctly added, their prices are correct and the total price is correct.
6. Click on Pay with Card button.
7. Fill all the details and click on Pay button.
8. Verify that Payment is successful and correct success message is displayed on the Confirmation page.
9. Verify above steps for Sunscreens page as well.

Note: The above scenarios are automated in the TestClass.java file. Only difference is that the navigation to Moisturizers or Sunscreens page is done based on temperature value as mentioned in the guidelines.






This project is developed with the help of following technology stack:
1. Java (Version 16) - Programming Language
2. Selenium (Version 4.0.0) - Web UI Automation Library
3. Maven (Version 3.8.4) - Build Management Tool
4. Extent Reports - Reporting Framework
5. TestNG (Version 7.4.0) - Basis TDD framework which also helps in facilitating parallel execution

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Prerequisites installations before running test:
1. Maven - Along with Environment variable settings (Can be referred via official installation site)
	https://www.javatpoint.com/how-to-install-maven#:~:text=%20How%20to%20install%20Maven%20on%20windows%20,maven%20home%20and%20java%20home.%20%20More%20
2. Java - (Can be referred via official installation site)
3. Chromium Edge and Chrome browsers

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

The project contains terminologies wrapped under packages as follows:
1. pageobjects - This package contains all the Page Object classes representing web elements and methods to interact with those web elements present on a particular page
2. resources - contains all utility and helper classes including BaseTest
3. testdata - contains all testdata files in csv format
4. testsuites - contains testng xml suites
5. uitests - contains main driver classes which are used for writing the actual test cases with the help of common methods written in page object classes present in pageobjects package
6. BaseTest - This class is present in the resources package and consists of methods related to browser initialization, all page objects' initialization and other initializations in ThreadSafe form wherever needed in order to make parallel execution possible. All the other types of classes should extend this class.
7. config.properties file - This file has all the global properties like browser, waits and testURL which can be set at a global level for running tests.
Currently chrome and edge browser are supported. Following are the values that needs to specified here for running tests on a pareticular browser.
browser=chrome
browser=edge
Default is Chrome if none is specified.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Steps to run a suite:
1. Create a new test class in the uitests package. For now, tests performing steps as per the problem statement are present in the class TestClass.java and one more dummy test is present in TestHeadersOfAllPages.java class under uitests package.
2. Create a new testng xml suite under uitests. For now, a suite file is present under this package which contains above two test classes.
3. In order to run the above xml suite file, open cmd, navigate to project home folder and run the following command:

mvn clean test -DtextNGSuiteFileName=src/test/java/testsuites/<suitefilename>.xml -Dparallel=tests/classes/methods -DthreadCount=<no of parallel threads>

e.g.
mvn clean test -DtextNGSuiteFileName=src/test/java/testsuites/testng.xml -Dparallel=classes -DthreadCount=2

4. The above command will run all the tests present in the specified suite xml file.