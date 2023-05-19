package com.digitalbank.qaautomation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideBarPage extends BasePage {

    @FindBy(id = "checking-menu")
    private WebElement checkingListBtn;

    @FindBy(id = "view-checking-menu-item")
    private WebElement viewCheckingBtn;

    public SideBarPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on the checking list button")
    public void clickCheckingList() {
        checkingListBtn.click();
    }

    @Step("Click on the view checking account button")
    public CheckingViewPage clickViewCheckingAccountButton() {
        viewCheckingBtn.click();
        return new CheckingViewPage(driver);
    }

    @Step ("Confirm if we logged in to the website")
    public boolean isLogoPresent() {
        return !driver.findElements(By.className("navbar-brand")).isEmpty();
    }
}
