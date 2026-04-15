package org.example.pages;

import org.example.base.BasePage;
import org.example.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[data-test='email']")
    private WebElement emailInput;

    @FindBy(css = "[data-test='password']")
    private WebElement passwordInput;

    @FindBy(css = "[data-test='loginBtn']")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public void open() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    public void enterUsername(String email) {
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void loginWith(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}