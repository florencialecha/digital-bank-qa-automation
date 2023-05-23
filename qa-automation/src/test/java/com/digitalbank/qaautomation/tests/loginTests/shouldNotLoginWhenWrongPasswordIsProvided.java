package com.digitalbank.qaautomation.tests.loginTests;


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

    @Description("This test should try to log in to the website with the specified credentials")
    @Test(testName = "Try login with valid username and wrong password ",dataProvider = "credentials", dataProviderClass = ShouldNotLoginData.class)
    public void loginTest(String user, String pass) throws Exception {

        // Navigate to the home page of the web application being tested.
        driver.get("http://digitalbank.upcamp.io/bank/login");

        // Try Login to the website
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(user, pass);

        //Assert URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://digitalbank.upcamp.io/bank/login?error");

        //Assert wrong credentials alert on login page
        String cantLoginAlert = loginPage.cantLoginAlert.getText();
        Assert.assertEquals(cantLoginAlert, "Error Invalid credentials or access not granted due to user account status or an existing user session.\n" + "×");



    }
}