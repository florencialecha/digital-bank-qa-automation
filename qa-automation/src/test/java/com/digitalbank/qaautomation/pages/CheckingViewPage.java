package com.digitalbank.qaautomation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckingViewPage extends BasePage {

    @FindBy(id = "emptyAccounts")
    private WebElement noAccountAlert;
    @FindBy(css = "#emptyAccounts #largeModalLabel")
    private WebElement noAccountAlertTitle;
    @FindBy(id = "page-title")
    private WebElement pageTitle;
    @FindBy(css = "#firstRow .card-body")
    private List<WebElement> accountCards;
    @FindBy(id = "transactionTable")
    private WebElement transactionTable;

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

    @Step("Check if at least one account card is displayed")
    public boolean isAccountCardDisplayed() {
        return !accountCards.isEmpty();
    }

    @Step("Check if transaction table is displayed")
    public boolean isTransactionTableDisplayed() {
        return transactionTable.isDisplayed();
    }

    @Step("Get page title text")
    public String getPageTitleText() {
        return pageTitle.getText();
    }

    @Step("Check if account is active")
    public boolean isAccountActive(int accountIndex) {
        WebElement accountCard = accountCards.get(accountIndex - 1);
        WebElement toggleActivation = accountCard.findElement(By.cssSelector("label.switch input[type='checkbox']"));
        boolean isChecked = toggleActivation.isSelected();

        return isChecked;
    }

    @Step("Activate account")
    public void activateAccount(int accountIndex) {
        WebElement accountCard = accountCards.get(accountIndex - 1);
        WebElement switchElement = accountCard.findElement(By.cssSelector("label.switch span.switch-label"));
        WebElement toggleActivation = accountCard.findElement(By.cssSelector("label.switch input[type='checkbox']"));
        boolean isChecked = toggleActivation.isSelected();

        if (!isChecked) {
            switchElement.click();
        }
    }

    @Step("Check if account with name '{accountName}' exists")
    public boolean existsAccountWithName(String accountName) {
        for (WebElement accountCard : accountCards) {
            WebElement accountNameElement = accountCard.findElement(By.cssSelector(".h4.m-0"));
            if (accountNameElement.getText().equals(accountName)) {
                return true;
            }
        }
        return false;
    }

}
