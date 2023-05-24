package com.digitalbank.qaautomation.tests;

import com.digitalbank.qaautomation.utils.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Health Check Tests")
@Feature("Check Website Health")
public class HealthCheckTest {

    private WebDriver driver;
    private ConfigReader configReader;

    @BeforeMethod
    @Description("Setup the web driver and create a new session")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        configReader = new ConfigReader();
    }

    @Test
    @Description("Verify that the main page of the website is accessible")
    public void checkMainPageAccessible() {

        driver.get(configReader.getLoginUrl());
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "http://digitalbank.upcamp.io/bank/login";
        String pageErrorMessage = "The main page should be accessible";
        Assert.assertEquals(currentUrl, expectedUrl, pageErrorMessage);

    }

    @AfterMethod
    @Description("Close the current session of web driver")
    public void tearDown() {
        driver.close();
    }

}
