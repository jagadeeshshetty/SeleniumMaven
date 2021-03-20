# WebUI Automated Acceptance Test using Selenium WebDriver + Java

[![Actions Status](https://github.com/jagadeeshshetty/SeleniumMaven/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/jagadeeshshetty/SeleniumMaven/actions)

## Programming language and libraries

`Java` `Selenium WebDriver 4.X` `Maven` `Log4J`

## Run all test cases

`mvn clean test`

## Run specific test case

`mvn clean test -Dtest=TestLogin#succeeded`

`mvn clean test -Dtest=TestException#webDriverException`

`mvn clean test -Dsurefire.suiteXmlFiles=testng.xml`

## Terminal

```commandline
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< this.me.jc:SeleniumMaven >----------------------
[INFO] Building SeleniumMaven 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ SeleniumMaven ---
[INFO] Deleting /Users/jc/SeleniumMaven/target
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ SeleniumMaven ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ SeleniumMaven ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ SeleniumMaven ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/jc/SeleniumMaven/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ SeleniumMaven ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to /Users/jc/SeleniumMaven/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ SeleniumMaven ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running tests.TestLogin
2020-07-30 00:00:00 INFO  tests.TestLogin : 20 - [setUp] Setting up Chrome driver.
Starting ChromeDriver 84.0.4147.30 (48b3e868b4cc0aa7e8149519690b6f6949e110a8-refs/branch-heads/4147@{#310}) on port 1032
Only local connections are allowed.
ChromeDriver was started successfully.
Jul 30, 2020 12:00:02 AM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: W3C
2020-07-30 00:00:02 INFO  tests.TestLogin : 28 - [setUp] ChromeDriver: chrome on MAC (659faefff8337a619558159fd6867b9e)
2020-07-30 00:00:06 INFO  tests.TestLogin : 37 - [succeeded] Invoked URL.
2020-07-30 00:00:07 INFO  tests.TestLogin : 39 - [succeeded] Entered username.
2020-07-30 00:00:07 INFO  tests.TestLogin : 41 - [succeeded] Entered password.
2020-07-30 00:00:08 INFO  tests.TestLogin : 43 - [succeeded] Clicked on button.
2020-07-30 00:00:08 WARN  tests.TestLogin : 49 - [tearDown] Driver object not null. So Cleaning up.
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 8.389 s - in tests.TestLogin
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  13.655 s
[INFO] Finished at: 2020-07-30T00:00:08+05:30
[INFO] ------------------------------------------------------------------------
```

## Reporting - Allure

- On macOS `brew install allure`
  or get binary
  from `https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.13.8/allure-commandline-2.13.8.zip`
  link and set the path.
- Annotations
    - Description
    - Severity
    - Epic
    - Feature
    - Story
    - Step
- Run
    - testng.xml
    - `allure serve target/allure-results`
- tbd
