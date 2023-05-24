package com.digitalbank.qaautomation.tests;

import com.digitalbank.qaautomation.pages.HomePage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.digitalbank.qaautomation.dataProviders.LoginData;
import com.digitalbank.qaautomation.pages.LoginPage;

public class LoginSuccessfulTest {

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
    public void shouldLoginWhenValidCredentialsAreProvided(String user, String pass) throws Exception {


        driver.get("http://digitalbank.upcamp.io/bank/login");
        String confirmLoginPage = driver.getCurrentUrl();
        String expectedLoginPage = "http://digitalbank.upcamp.io/bank/login";
        Assert.assertEquals(confirmLoginPage, expectedLoginPage);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(user, pass);
        HomePage homePage = new HomePage(driver);

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "http://digitalbank.upcamp.io/bank/home";
        Assert.assertEquals(currentUrl, expectedUrl);
        String pageTitle = "Dashboard";
        Assert.assertEquals(pageTitle, homePage.dashboardTitle.getText());
    }
}