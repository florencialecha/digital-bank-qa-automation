package com.digitalbank.qaautomation.tests;

import com.digitalbank.qaautomation.dataProviders.LoginData;
import com.digitalbank.qaautomation.pages.CheckingViewPage;
import com.digitalbank.qaautomation.pages.LoginPage;
import com.digitalbank.qaautomation.pages.SideBarPage;
import com.digitalbank.qaautomation.utils.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SingleAccountActivationTest {

    private WebDriver driver;
    private ConfigReader configReader;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        configReader = new ConfigReader();
    }

    @Feature("Account Activation")
    @Story("Activation of a single account at a time")
    @Description("When a user activates one account, any other active account should be deactivated.")
    @Test(dataProvider = "credentials", dataProviderClass = LoginData.class)
    public void shouldAllowOnlyOneActiveAccountAtATime(String user, String pass) {

        driver.get(configReader.getLoginUrl());
        LoginPage loginPage = new LoginPage(driver);

        SideBarPage sideBarPage = loginPage.logIn(user, pass);
        sideBarPage.clickCheckingList();
        CheckingViewPage checkingViewPage = sideBarPage.clickViewCheckingAccountButton();

        checkingViewPage.activateAccount(1);
        boolean isFirstAccountActive = checkingViewPage.isAccountActive(1);
        String firstAccountActivationErrorMessage = "The first account should be active after activation";
        Assert.assertTrue(isFirstAccountActive, firstAccountActivationErrorMessage);

        checkingViewPage.activateAccount(2);
        String secondAccountActivationErrorMessage = "The second account should be active after activation";
        boolean isSecondAccountActive = checkingViewPage.isAccountActive(2);
        Assert.assertTrue(isSecondAccountActive, secondAccountActivationErrorMessage);

        isFirstAccountActive = checkingViewPage.isAccountActive(1);
        String firstAccountDeactivationErrorMessage = "The first account should be deactivated after the second account is activated";
        Assert.assertFalse(isFirstAccountActive, firstAccountDeactivationErrorMessage);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
