package com.digitalbank.qaautomation.tests;


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

public class LoginRememberMeTest {


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
    public void shouldRembemberLastLogedUsernameWhenRememberMeCheckboxIsCheck(String user, String pass) throws Exception {

        driver.get("http://digitalbank.upcamp.io/bank/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(user, pass);
        HeaderPage headerPage = new HeaderPage(driver);

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "http://digitalbank.upcamp.io/bank/home";
        Assert.assertEquals(currentUrl, expectedUrl);

        headerPage.logOut();

        String currentLogoutUrl = driver.getCurrentUrl();
        String expectedLogoutUrl = "http://digitalbank.upcamp.io/bank/login";
        Assert.assertEquals(currentLogoutUrl, expectedLogoutUrl);
        String rememberedUsername = loginPage.getRememberedUsername();
        Assert.assertEquals(rememberedUsername, user);
        
    }
}

