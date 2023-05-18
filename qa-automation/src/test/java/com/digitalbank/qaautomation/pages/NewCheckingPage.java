package com.digitalbank.qaautomation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewCheckingPage extends BasePage {

    @FindBy(name = "accountType")
    private List<WebElement> checkingAccountTypeOptions;
    @FindBy(name = "ownershipType")
    private List<WebElement> accountOwnershipOptions;
    @FindBy(id = "name")
    private WebElement accountNameField;
    @FindBy(id = "openingBalance")
    private WebElement initialDepositField;
    @FindBy(id ="newCheckingSubmit")
    private WebElement submitBtn;

    public NewCheckingPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select account type: {checkingAccountType}")
    public void selectCheckingAccountType(String checkingAccountType) {
        for (WebElement option : checkingAccountTypeOptions) {
            if (option.getAttribute("id").equals(checkingAccountType)) {
                option.click();
                break;
            }
        }
    }

    @Step("Select account ownership: {ownershipType}")
    public void selectAccountOwnership (String ownershipType){
        for (WebElement option : accountOwnershipOptions) {
            if (option.getAttribute("id").equals(ownershipType)) {
                option.click();
                break;
            }
        }
    }

    @Step("Set account name: {accountName}")
    public void setAccountName(String accountName) {
        accountNameField.sendKeys(accountName);
    }

    @Step("Set initial deposit: {initialDeposit}")
    public void setInitialDeposit(String initialDeposit) {
        initialDepositField.sendKeys(initialDeposit);
    }

    @Step("Submit the form")
    public CheckingViewPage submitForm() {
        submitBtn.click();
        return new CheckingViewPage(driver);
    }

}
