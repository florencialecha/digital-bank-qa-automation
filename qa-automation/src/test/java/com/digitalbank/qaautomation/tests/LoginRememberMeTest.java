package com.digitalbank.qaautomation.tests;


import com.digitalbank.qaautomation.dataProviders.RememberMeLoginData;
import com.digitalbank.qaautomation.pages.HeaderPage;
import com.digitalbank.qaautomation.utils.ConfigReader;
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
    private ConfigReader configReader;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        ChromeOptions allowRemoteOrigins=new ChromeOptions();
        allowRemoteOrigins.addArguments("--remote-allow-origins=*");
        driver = (WebDriver) new ChromeDriver(allowRemoteOrigins);
        configReader = new ConfigReader();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Description("This test will verify that after logout the 'Username' credential is remembered if the 'Remember Me' box was checked on login. ")
    @Test(dataProvider = "credentials", dataProviderClass = RememberMeLoginData.class)
    public void shouldRembemberLastLogedUsernameWhenRememberMeCheckboxIsCheck(String user, String pass) throws Exception {

        driver.get(configReader.getLoginUrl());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(user, pass);
        HeaderPage headerPage = new HeaderPage(driver);

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = configReader.getHomeUrl();
        Assert.assertEquals(currentUrl, expectedUrl);

        headerPage.logOut();

        String currentLogoutUrl = driver.getCurrentUrl();
        String expectedLogoutUrl = "http://digitalbank.upcamp.io/bank/login?logout";
        Assert.assertEquals(currentLogoutUrl, expectedLogoutUrl);
        String rememberedUsername = loginPage.getRememberedUsername();
        Assert.assertEquals(rememberedUsername, user);
        
    }
}

