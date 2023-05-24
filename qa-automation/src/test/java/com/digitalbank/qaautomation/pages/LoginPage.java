package com.digitalbank.qaautomation.pages;

import io.qameta.allure.Step;
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

    @Step("Checking remembered username")
    public String getRememberedUsername() {
        String rememberedUsername = usernameInput.getAttribute("value");
        return rememberedUsername;
    }

}
