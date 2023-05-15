package com.digitalbank.qaautomation.tests;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HealthCheckTest {

    WebDriver driver;

    @BeforeMethod
    @Description("Setup the web driver and create a new session")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    @Description("Verify that the main page of the website is accessible")
    public void checkMainPageAccessible() {

        driver.get("http://digitalbank.upcamp.io/bank");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://digitalbank.upcamp.io/bank/login", "Main page is not accessible");

    }

    @AfterMethod
    @Description("Close the current session of web driver")
    public void tearDown() {
        driver.close();
    }

}
