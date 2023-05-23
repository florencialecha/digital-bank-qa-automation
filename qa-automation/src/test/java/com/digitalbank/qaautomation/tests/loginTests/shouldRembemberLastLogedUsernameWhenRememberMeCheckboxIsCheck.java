package com.digitalbank.qaautomation.tests.loginTests;


import com.digitalbank.qaautomation.dataProviders.RememberMeLoginData;
import com.digitalbank.qaautomation.pages.HeaderPage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.digitalbank.qaautomation.pages.LoginPage;

public class shouldRembemberLastLogedUsernameWhenRememberMeCheckboxIsCheck {


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

    @Description("This test will verify that after logout the 'Username' credential is remembered if the 'Remember Me' box was checked on login. ")
    @Test(testName = "Try login with valid username and wrong password ",dataProvider = "credentials", dataProviderClass = RememberMeLoginData.class)
    public void loginTest(String user, String pass) throws Exception {

        // Navigate to the home page of the web application being tested.
        driver.get("http://digitalbank.upcamp.io/bank/login");

        // Try Login to the website
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(user, pass);
        HeaderPage headerPage = new HeaderPage(driver);

        //Assert login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://digitalbank.upcamp.io/bank/home");

        //Logout
        headerPage.logOut();

        //Assert Remembered username
        String rememberedUsername = loginPage.usernameInput.getAttribute("value");
        Assert.assertEquals(rememberedUsername, user);




    }
}

