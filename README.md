# qawingify
QA Internship Assignment - writing automation script for the login page for doing functional testing
# Automated Testing Assignment Submission

## Project Details

### Application URL
- The web application can be accessed at: [https://sakshingp.github.io/assignment/login.html](https://sakshingp.github.io/assignment/login.html)
- Repository link: https://github.com/dharmendra2802/qawingify
- Deployment Link: https://dharmendra2802.github.io/qawingify/

## Test Scenarios

### Login Page

The Login Page functionality should be thoroughly tested. The following scenarios have been covered:

1. Valid Credentials:
   - Verify that providing a valid username and password successfully logs in.
   
2. Invalid Credentials:
   - Verify that providing an invalid username and password combination does not log in.
   
3. Empty Credentials:
   - Verify that leaving the username and password fields empty does not log in.
   
4. Special Characters in Username:
   - Verify that the handling of special characters and symbols in the username field is implemented correctly.
   
5. Whitespace as a value of Username/Password:
   - Verify that such value does not let user login.

### Home Page

After successfully logging into the application, the following test scenario has been covered:

1. Transaction Table Sorting:
   - Click the AMOUNT header in the transaction table and verify that the values are sorted in the expected order (ascending or descending).

## Test Implementation Details

The automated testing scripts have been implemented using the following technologies:

- Testing Framework: Selenium WebDriver
- Programming Language: Java
- Test Execution: Utilized a modular approach with separate classes for setup, test execution, and assertions.
- Reporting: Utilized Extent Reports for generating HTML reports with detailed test results.

## Conclusion

The automated testing project covers functional testing scenarios for the Login Page and Home Page functionalities of the web application. The implemented test scripts demonstrate the use of Selenium WebDriver, Java, and Extent Reports for effective automated testing and reporting.

Thank you!

