package com.digitalbank.qaautomation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckingViewPage extends BasePage {

    @FindBy(id = "emptyAccounts")
    private WebElement noAccountAlert;
    @FindBy(css = "#emptyAccounts #largeModalLabel")
    private WebElement noAccountAlertTitle;

    public CheckingViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the no account alert")
    public String getNoAccountAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> noAccountAlert.isDisplayed());
        System.out.println("Texto que está devolviendo: " + noAccountAlertTitle.getText());
        return noAccountAlertTitle.getText();
    }

}
