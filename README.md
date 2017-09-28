
* ROHAN MAGARE, 001231457, magare.r@husky.neu.edu    
* RITESH GUPTA, 001280361, gupta.rite@husky.neu.edu
* PRATIKSHA SHETTY, 001643697, shetty.pr@husky.neu.edu
* YOGITA JAIN, 001643815, jain.yo@husky.neu.edu

## Project Title: Web Application Development using Spring boot and Gradle

* The objective of the project is to develop a simple web application wherein a user can be registered and logged in.

1) Prerequisites: 
   * MySql
   * Java jre 8
   * JMeter
   * Apache Tomcat 8

2) Built with:
   * Gradle

3) Includes:
   * .jmx for Jmeter testing
   * .yml for travis-ci testing
   * JUnits for unit testing

For running the application locally
Change the property `spring.profiles.active` in application properties to dev

For running the test cases: 
Change the property `spring.profiles.active` in application properties to test
This will run the Junit tests on travisCI. 

This change is needed because the web application is integrated with MySQl database for it to fetch, authenticate and put data and we are using H2 database to run the tests on Travis on git commit.

