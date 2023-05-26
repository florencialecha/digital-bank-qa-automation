package com.digitalbank.qaautomation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage {
    @FindBy (id = "page-title")
    public WebElement dashboardTitle;

    public HomePage(WebDriver driver) { super(driver); }

}
