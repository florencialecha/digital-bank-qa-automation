package com.digitalbank.qaautomation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("src/test/java/com/digitalbank/qaautomation/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLoginUrl() {
        return properties.getProperty("app.loginUrl");
    }
    public String getLoginErrorUrl() {
        return properties.getProperty("app.loginErrorUrl");
    }
    public String getLogOutUrl() {
        return properties.getProperty("app.logOutUrl");
    }
    public String getHomeUrl() {
        return properties.getProperty("app.homeUrl");
    }
}
