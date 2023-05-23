
package com.digitalbank.qaautomation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

    public class HeaderPage extends BasePage {

        @FindBy(css = ".dropdown-toggle .user-avatar")
        private WebElement userProfileBtn;

        @FindBy(css = ".nav-link[href='/bank/user/delete-data']")
        private WebElement deleteDataBtn;

        @FindBy(css = ".nav-link[href='/bank/logout']")
        private WebElement logoutBtn;

        public HeaderPage(WebDriver driver) {
            super(driver);
        }

        public void clickUserProfileBtn() {
            userProfileBtn.click();
        }

//        public HomePage clickDeleteDataButton() {
//            deleteDataBtn.click();
//            return new HomePage(driver);
//        }
        @Step ("Logging out of the site")
        public LoginPage logOut(){
            userProfileBtn.click();
            logoutBtn.click();
            return new LoginPage(driver);
    }
}
