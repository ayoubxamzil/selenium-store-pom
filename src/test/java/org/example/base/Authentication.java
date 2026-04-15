package org.example.base;

import org.example.pages.LoginPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.BeforeMethod;


public abstract class Authentication extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void login() {
        String email = ConfigReader.get("default.email");
        String password = ConfigReader.get("default.password");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.loginWith(email, password);
    }
}