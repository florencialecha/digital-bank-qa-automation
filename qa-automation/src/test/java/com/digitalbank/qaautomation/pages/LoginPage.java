package com.digitalbank.qaautomation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit")
    private WebElement singInBtn;

    @FindBy (className = "sufee-alert")
    private WebElement cantLoginAlert;

    @Step ("Checking can't login alert")
    public String getCantLoginAlert() {
        String cantLoginAlertMsg = cantLoginAlert.getText();
        return cantLoginAlertMsg;
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Log in with username: {username} and password: {password}")
    public SideBarPage logIn(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        singInBtn.click();
        return new SideBarPage(driver);
    }
}
