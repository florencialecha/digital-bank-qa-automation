package com.digitalbank.qaautomation.tests;

import com.digitalbank.qaautomation.pages.CheckingViewPage;
import com.digitalbank.qaautomation.pages.LoginPage;
import com.digitalbank.qaautomation.pages.SideBarPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SingleAccountActivationTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Feature("Account Activation")
    @Story("Activation of a single account at a time")
    @Description("When a user activates one account, any other active account should be deactivated.")
    @Test
    public void shouldAllowOnlyOneActiveAccountAtATime() {

        driver.get("http://digitalbank.upcamp.io/bank/login");
        LoginPage loginPage = new LoginPage(driver);

        // Log in with a user with multiple checking accounts
        SideBarPage sideBarPage = loginPage.logIn("jsmith@demo.io", "Demo123!");
        sideBarPage.clickCheckingList();
        CheckingViewPage checkingViewPage = sideBarPage.clickViewCheckingAccountButton();

        // Activate the first account
        checkingViewPage.activateAccount(1);
        boolean isFirstAccountActive = checkingViewPage.isAccountActive(1);
        Assert.assertTrue(isFirstAccountActive, "The first account should be active after activation");

        // Activate the second account
        checkingViewPage.activateAccount(2);
        boolean isSecondAccountActive = checkingViewPage.isAccountActive(2);
        Assert.assertTrue(isSecondAccountActive, "The second account should be active after activation");

        // Verify that the first account is deactivated
        isFirstAccountActive = checkingViewPage.isAccountActive(1);
        Assert.assertFalse(isFirstAccountActive, "The first account should be deactivated after the second account is activated");
    }

}
