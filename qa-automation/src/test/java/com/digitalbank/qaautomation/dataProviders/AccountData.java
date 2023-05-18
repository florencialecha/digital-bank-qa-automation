package com.digitalbank.qaautomation.dataProviders;

import org.testng.annotations.DataProvider;

public class AccountData {
    @DataProvider(name = "accountData")
    public Object[][] accountData() {
        return new Object[][]{
                {"Standard Checking", "Individual"},
                {"Standard Checking", "Joint"},
                {"Interest Checking", "Joint"},
                {"Interest Checking", "Individual"}
        };
    }
}
