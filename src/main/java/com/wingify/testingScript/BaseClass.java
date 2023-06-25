package com.wingify.testingScript;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

    private static TestSetup test;

    public static void main(String[] args) {

        test = new TestSetup();

        RunningTest r = new RunningTest();

        // Initializing extentReport
        r.extentSetup();
        
        // Running some valid test cases
        r.createTest("Testing Valid Values", "Entering Valid values and checking if it is being logged in or not");
        r.testValidCases(test);

        // Running some invalid cases
        r.createTest("Testing Invalid Values", "Entering Invalid Values and checking if it is being prevented to logged in or not");
        r.testInvalidCases(test);

        // Testing the Amount Sorting
        r.createTest("Testing Amount Values", "Checking if Amount values are sorted or not");
        r.startAmountTest(test);

        // Stop
        test.driverStop();
        //Flushing and saving the test results in file
        r.testEnd();

    }

}
