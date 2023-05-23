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
    @Test
    public void shouldDisplayNoAccountsAlertWhenNoAccountsExist() {

        driver.get("http://digitalbank.upcamp.io/bank/login");
        LoginPage loginPage = new LoginPage(driver);

        // Las credenciales de este usuario van a externalizarse cuando se una con la login page
        SideBarPage sideBarPage = loginPage.logIn("tabewec832@andorem.com", "Demo123!");
        sideBarPage.clickCheckingList();
        CheckingViewPage checkingViewPage = sideBarPage.clickViewCheckingAccountButton();

        String expectedMessage = "No Accounts";
        String actualMessage = checkingViewPage.getNoAccountAlert();
        Assert.assertEquals(actualMessage, expectedMessage, "The message should inform the user that no account exists");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
