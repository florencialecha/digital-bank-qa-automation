package com.digitalbank.qaautomation.dataProviders;

import org.testng.annotations.DataProvider;

public class AccountData {
    @DataProvider(name = "accountData")
    public Object[][] accountData() {
        return new Object[][]{
                {"Standard Checking", "Individual", "Standard Individual Account", "5000"},
                {"Standard Checking", "Joint", "Standard Joint Account", "5000"},
                {"Interest Checking", "Joint", "Interest Joint Account", "5000"},
                {"Interest Checking", "Individual", "Interest Individual Account", "5000"}
        };
    }
}
