package com.digitalbank.qaautomation.tests.newChecking;

import com.digitalbank.qaautomation.pages.LoginPage;
import com.digitalbank.qaautomation.pages.NewCheckingPage;
import com.digitalbank.qaautomation.pages.SideBarPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewCheckingAccountCreation {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Feature("Account Creation")
    @Story("Creation of a standard individual checking account")
    @Description("When a user enters valid data in the fields, a new standard individual checking account should be created.")
    @Test
    public void shouldCreateNewStandardIndividualAccountWithValidData() {

        driver.get("http://digitalbank.upcamp.io/bank/login");
        LoginPage loginPage = new LoginPage(driver);

        // Log in with a user
        SideBarPage sideBarPage = loginPage.logIn("jsmith@demo.io", "Demo123!");
        sideBarPage.clickCheckingList();
        sideBarPage.clickNewCheckingPageButton();

        // Navigate to New Checking Page
        NewCheckingPage newCheckingPage = sideBarPage.clickNewCheckingPageButton();

        // Fill the form
        newCheckingPage.selectAccountType("Standard Checking");
        newCheckingPage.selectOwnership("Individual");
        newCheckingPage.setAccountName("New Account");
        newCheckingPage.setInitialDeposit("5000");

        // Submit the form
        CheckingViewPage checkingViewPage = newCheckingPage.submitForm();

        // Verify that new account is created
        boolean isNewAccountCreated = checkingViewPage.isAccountCreated("New Account");
        Assert.assertTrue(isNewAccountCreated, "New account should be created with provided valid data");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
