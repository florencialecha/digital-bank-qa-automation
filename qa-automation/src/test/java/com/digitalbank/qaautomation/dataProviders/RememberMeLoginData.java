package com.digitalbank.qaautomation.dataProviders;

import org.testng.annotations.DataProvider;

public class RememberMeLoginData {
    @DataProvider(name = "credentials")
    public static Object[][] getsLoginData() {
        return new Object[][]{
                {"nightshinigamix@gmail.com", "Data123!"}
        };
    }
}