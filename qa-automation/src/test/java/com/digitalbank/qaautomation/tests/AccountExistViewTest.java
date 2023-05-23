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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountExistViewTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Feature("Checking Account Display")
    @Story("Display account cards and transaction history when checking accounts exist")
    @Description("When a user with checking accounts views their checking accounts, account cards and transaction history should be displayed.")
    @Test
    public void shouldDisplayCheckingViewPageWhenAccountsExist() {

        driver.get("http://digitalbank.upcamp.io/bank/login");
        LoginPage loginPage = new LoginPage(driver);

        SideBarPage sideBarPage = loginPage.logIn("tabewec832@andorem.com", "Demo123!");
        sideBarPage.clickCheckingList();
        CheckingViewPage checkingViewPage = sideBarPage.clickViewCheckingAccountButton();

        String expectedTitle = "View Checking Accounts";
        String actualTitle = checkingViewPage.getPageTitleText();
        String titleErrorMessage = "The page title should be 'View Checking Accounts' when accounts exist";
        Assert.assertEquals(actualTitle, expectedTitle, titleErrorMessage);

        boolean isAccountCardDisplayed = checkingViewPage.isAccountCardDisplayed();
        String accountCardErrorMessage = "Account cards should be displayed when accounts exist";
        Assert.assertTrue(isAccountCardDisplayed, accountCardErrorMessage);

        boolean isTransactionTableDisplayed = checkingViewPage.isTransactionTableDisplayed();
        String transactionTableErrorMessage = "Transaction history should be displayed when accounts exist";
        Assert.assertTrue(isTransactionTableDisplayed, transactionTableErrorMessage);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
