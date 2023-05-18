package com.digitalbank.qaautomation.tests.loginTests;


import com.digitalbank.qaautomation.pages.SideBarPage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.digitalbank.qaautomation.dataProviders.LoginData;
import com.digitalbank.qaautomation.pages.LoginPage;

public class LoginTestCP01 {

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
    @Test(testName = "Login with valid credentials ",dataProvider = "credentials", dataProviderClass = LoginData.class)
    public void loginTest(String user, String pass) throws Exception {


        // Navigate to the home page of the web application being tested.
        driver.get("http://digitalbank.upcamp.io/bank/login");

        LoginPage loginPage = new LoginPage(driver);
        SideBarPage sideBarPage = loginPage.logIn(user, pass);
        if (sideBarPage.bankLogo.isDisplayed()){
            System.out.println("Login successful");
        }








    }
}