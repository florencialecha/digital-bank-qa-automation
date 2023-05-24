package com.digitalbank.qaautomation.tests;

import com.digitalbank.qaautomation.dataProviders.ShouldNotLoginData;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.digitalbank.qaautomation.pages.LoginPage;

public class shouldNotLoginWhenWrongPasswordIsProvided {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        ChromeOptions allowRemoteOrigins=new ChromeOptions();
        allowRemoteOrigins.addArguments("--remote-allow-origins=*");
        driver = (WebDriver) new ChromeDriver(allowRemoteOrigins);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Description("This test should try to log in to the website with valid username and wrong password")
    @Test(testName = "Try login with valid username and wrong password ",dataProvider = "credentials", dataProviderClass = ShouldNotLoginData.class)
    public void loginTest(String user, String pass) throws Exception {

        driver.get("http://digitalbank.upcamp.io/bank/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(user, pass);

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "http://digitalbank.upcamp.io/bank/login?error";
        Assert.assertEquals(currentUrl, expectedUrl);

        String cantLoginAlert = loginPage.getCantLoginAlert();
        String expectedCantLoginAlert = ("Error Invalid credentials or access not granted due to user account status or an existing user session.\n" + "×");
        Assert.assertEquals(cantLoginAlert, expectedCantLoginAlert);
    }
}