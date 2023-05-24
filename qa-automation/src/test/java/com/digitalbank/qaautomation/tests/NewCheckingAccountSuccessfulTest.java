package com.digitalbank.qaautomation.tests;

import com.digitalbank.qaautomation.dataProviders.AccountData;
import com.digitalbank.qaautomation.pages.CheckingViewPage;
import com.digitalbank.qaautomation.pages.LoginPage;
import com.digitalbank.qaautomation.pages.NewCheckingPage;
import com.digitalbank.qaautomation.pages.SideBarPage;
import com.digitalbank.qaautomation.utils.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewCheckingAccountSuccessfulTest {

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

    @Story("Creation of different types of checking accounts")
    @Description("When a user enters valid data in the fields, a new checking account should be created.")
    @Test(dataProvider = "accountData", dataProviderClass = AccountData.class)
    public void shouldCreateNewAccountWhenValidDataIsProvided(String accountType, String ownershipType, String accountName, String initialDeposit) {

        driver.get(configReader.getLoginUrl());
        LoginPage loginPage = new LoginPage(driver);

        SideBarPage sideBarPage = loginPage.logIn("nightshinigamix@gmail.com", "Data123!");
        sideBarPage.clickCheckingList();
        NewCheckingPage newCheckingPage = sideBarPage.clickNewCheckingPageButton();

        newCheckingPage.selectCheckingAccountType(accountType);
        newCheckingPage.selectAccountOwnership(ownershipType);
        newCheckingPage.setAccountName(accountName);
        newCheckingPage.setInitialDeposit(initialDeposit);

        CheckingViewPage checkingViewPage = newCheckingPage.submitForm();

        boolean isNewAccountCreated = checkingViewPage.existsAccountWithName(accountName);
        String accountCreationErrorMessage = "New account should be created with provided valid data";
        Assert.assertTrue(isNewAccountCreated, accountCreationErrorMessage);

        String accountBalance = checkingViewPage.getAccountBalance(accountName);
        String accountBalanceErrorMessage = "New account balance should match initial deposit";
        Assert.assertEquals(accountBalance, initialDeposit, accountBalanceErrorMessage);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
