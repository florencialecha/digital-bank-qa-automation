package com.digitalbank.qaautomation.tests;

import com.digitalbank.qaautomation.dataProviders.LoginData;
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

public class NoAccountExistViewTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Feature("Checking Account Display")
    @Story("Display message when viewing empty checking account")
    @Description("When a user with no checking accounts views their checking accounts, a 'No Account' message should be displayed.")
    @Test(dataProvider = "credentials", dataProviderClass = LoginData.class)
    public void shouldDisplayNoAccountsAlertWhenNoAccountsExist(String user, String pass) {

        driver.get("http://digitalbank.upcamp.io/bank/login");
        LoginPage loginPage = new LoginPage(driver);

        SideBarPage sideBarPage = loginPage.logIn(user, pass);
        sideBarPage.clickCheckingList();
        CheckingViewPage checkingViewPage = sideBarPage.clickViewCheckingAccountButton();

        String expectedAlert = "No Accounts";
        String actualAlert = checkingViewPage.getNoAccountAlert();
        String alertErrorMessage = "The message should inform the user that no account exists";
        Assert.assertEquals(actualAlert, expectedAlert, alertErrorMessage);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
