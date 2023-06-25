package com.wingify.testingScript;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSetup {

    private WebDriver driver;
    private WebElement username;
    private WebElement password;
    private WebElement login;
    private WebElement amountHeader;

    // Function to initialize the driver
    public TestSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chrome-driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    // Function to start the test
    public void startTest(String url) {
        driver.navigate().to(url);

        // Maximize current window
        driver.manage().window().maximize();

        // Find the elements through ID
        username = driver.findElement(By.id("username"));
        password = driver.findElement(By.id("password"));
        login = driver.findElement(By.id("log-in"));
    }

    public void setCredential(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);

        // Click the login button
        this.login.click();
    }

    // To stop driver
    public void driverStop() {
        driver.close();
    }

    // To get current URL
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    // to find amount element in the web page
    public void findAmount() {
        amountHeader = driver.findElement(By.xpath("//th[@id='amount']"));
    }

    // clicking the amount to sort the values
    public void clickAmount() {
        // Click functionality on 'amount' header to sort the values
        amountHeader.click();
    }

    // fetching the amount values from the table
    public List<String> getAmountValues() {

    	List<WebElement> amountValues = driver.findElements(By.xpath("//table//tr/td[5]"));
        List<String> sortedAmounts = new ArrayList<String>();

        for (WebElement amountValue : amountValues) {
            sortedAmounts.add(amountValue.getText());
        }
        // adding the values in a list and returning it
        return sortedAmounts;
    }
}
