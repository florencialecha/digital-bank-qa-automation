package com.digitalbank.qaautomation.tests;

import com.digitalbank.qaautomation.dataProviders.AccountData;
import com.digitalbank.qaautomation.pages.CheckingViewPage;
import com.digitalbank.qaautomation.pages.LoginPage;
import com.digitalbank.qaautomation.pages.NewCheckingPage;
import com.digitalbank.qaautomation.pages.SideBarPage;
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

public class NewCheckingAccountCreation {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Story("Creation of different types of checking accounts")
    @Description("When a user enters valid data in the fields, a new checking account should be created.")
    @Test(dataProvider = "accountData", dataProviderClass = AccountData.class)
    public void shouldCreateNewAccountWhenValidDataIsProvided(String accountType, String ownershipType, String accountName, String initialDeposit) {

        driver.get("http://digitalbank.upcamp.io/bank/login");
        LoginPage loginPage = new LoginPage(driver);

        // Log in with a user
        SideBarPage sideBarPage = loginPage.logIn("jsmith@demo.io", "Demo123!");
        sideBarPage.clickCheckingList();

        // Navigate to New Checking Page
        NewCheckingPage newCheckingPage = sideBarPage.clickNewCheckingPageButton();

        // Fill the form
        newCheckingPage.selectCheckingAccountType(accountType);
        newCheckingPage.selectAccountOwnership(ownershipType);
        newCheckingPage.setAccountName(accountName);
        newCheckingPage.setInitialDeposit(initialDeposit);

        // Submit the form
        CheckingViewPage checkingViewPage = newCheckingPage.submitForm();

        // Verify that new account is created
        boolean isNewAccountCreated = checkingViewPage.existsAccountWithName(accountName);
        Assert.assertTrue(isNewAccountCreated, "New account should be created with provided valid data");

        // Verify that the account balance is correct
        String accountBalance = checkingViewPage.getAccountBalance(accountName);
        Assert.assertEquals(accountBalance, initialDeposit, "New account balance should match initial deposit");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
