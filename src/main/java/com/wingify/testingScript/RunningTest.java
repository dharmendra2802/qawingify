package com.wingify.testingScript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class RunningTest {
	
	// base URL of the page
	private static final String url = "https://sakshingp.github.io/assignment/login.html";
	
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    // Setting Up extentReport
    public void extentSetup() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("docs/TestReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    // Initializing the test with title and some description
    public void createTest(String title, String des) {
        extentTest = extentReports.createTest(title, des);
    }

    // when programs end - to save the test in a HTML file
    public void testEnd() {
        extentReports.flush();
    }

    public void testInvalidCases(TestSetup test) {
       
        // Test - 1
        test.startTest(url);
        test.setCredential("manoj", "");
        loginPrevented("Test Case - 1", test);
       
        // Test - 2
        test.startTest(url);
        test.setCredential("", "1211232");
        loginPrevented("Test Case - 2", test);
        
        // Test - 3
        test.startTest(url);
        test.setCredential("", "");
        loginPrevented("Test Case - 3", test);
        
        // Bridge cases
        // Test - 4 -- User should not be able to login by entering Whitespaces
        test.startTest(url);
        extentTest.warning("Whitespaces must not be consider as VALID");
        test.setCredential("  ", "pass1231");
        loginPrevented("Test Case - 4", test);
        
        // Test - 5
        test.startTest(url);
        extentTest.warning("Whitespaces must not be consider as VALID");
        test.setCredential("   ", "   ");
        loginPrevented("Test Case - 5", test);
    }

    public void testValidCases(TestSetup test) {
        // Test - 1
        test.startTest(url);
        test.setCredential("manoj", "password");
        isloggedIn("Test Case - 1", test);
        
        // Test - 2
        test.startTest(url);
        test.setCredential("manoj", "1211232");
        isloggedIn("Test Case - 2", test);
        
        // Test - 3
        test.startTest(url);
        test.setCredential("12131", "pass");
        isloggedIn("Test Case - 3", test);
        
        // Test - 4 -- Special symbol as username is not good but considered as OK because of the NOTE
        test.startTest(url);
        test.setCredential("@@@!", "pass1231");
        isloggedIn("Test Case - 4", test);
        
        // Test - 5
        test.startTest(url);
        test.setCredential("###", "######");
        isloggedIn("Test Case - 5", test);
    }

    private void isloggedIn(String testcase, TestSetup test) {
       
    	// home page URL - it should be loaded in case of Idle value
    	String expected = "https://sakshingp.github.io/assignment/home.html";
        try {
            Assert.assertEquals(test.getCurrentURL(), expected, "!!! Valid Credentials -- still not logged in !!!");
            extentTest.pass(testcase + " PASSED");
        } catch (AssertionError e) {
        	// storing the AssertionError in a lOG as fail
            extentTest.fail("FAILED " + e);
        }
    }

    private void loginPrevented(String testcase, TestSetup test) {
      
    	// home page URL - it should be loaded in case of Idle value
        String expected = "https://sakshingp.github.io/assignment/login.html";
        try {
            Assert.assertEquals(test.getCurrentURL(), expected, "!!! Invalid Credentials -- still logged in !!!");
            extentTest.pass(testcase + " PASSED ");
        } catch (AssertionError e) {
        	// storing the AssertionError in a lOG as fail
            extentTest.fail("FAILED " + e);
        }
    }

    public void startAmountTest(TestSetup test) {
        test.startTest(url);
        test.setCredential("manoj", "password");
        test.findAmount();
        test.clickAmount();
        extentTest.log(Status.INFO, "Checking Sort by - INCREASE");
        amountTest(test, true);
        test.clickAmount();
        extentTest.log(Status.INFO, "Checking Sort by - DECREASE");
        amountTest(test, false);
    }

    private void amountTest(TestSetup test, boolean isIncrease) {
        List<String> data = test.getAmountValues();
        boolean isAmountSorted = checkSortAmount(data, isIncrease);
        if (isAmountSorted) {
            extentTest.pass("Transaction is Sorted - PASSED ");
        } else {
            extentTest.fail(" FAILED - Values are not SORTED ");
        }
    }

    private boolean checkSortAmount(List<String> data, boolean isIncrease) {
        List<Double> numericValues = new ArrayList<Double>();
        for (String stringNumber : data) {
            String numericString = stringNumber.replaceAll("[^0-9.-]", ""); // Remove non-numeric characters
            double numericValue = Double.parseDouble(numericString);
            numericValues.add(numericValue);
        }

        List<Double> sortedList = new ArrayList<Double>(numericValues); // Create a sorted copy of the list
        if (isIncrease) {
            Collections.sort(sortedList);
        } else {
            Collections.sort(sortedList, Collections.reverseOrder());
        }

        return sortedList.equals(numericValues); // Compare the sorted copy with the original list
    }
}
