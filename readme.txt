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