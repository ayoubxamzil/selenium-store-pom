package org.example.pages;

import org.example.base.BasePage;
import org.example.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(css = "[data-test='firstname']")
    private WebElement firstnameInput;

    @FindBy(css = "[data-test='lastname']")
    private WebElement lastnameInput;

    @FindBy(css = "[data-test='email']")
    private WebElement emailInput;

    @FindBy(css = "[data-test='password']")
    private WebElement passwordInput;

    @FindBy(css = "[data-test='repeatPassword']")
    private WebElement repeatPasswordInput;

    @FindBy(css = "[data-test='signupBtn']")
    private WebElement signupBtn;

    @FindBy(css = "[data-test='successMsg']")
    private WebElement successMsg;

    @FindBy(css = "[data-test='errorMsg']")
    private WebElement globalError;

    @FindBy(css = "[data-test='firstnameError']")
    private WebElement firstnameError;

    @FindBy(css = "[data-test='lastnameError']")
    private WebElement lastnameError;

    @FindBy(css = "[data-test='emailError']")
    private WebElement emailError;

    @FindBy(css = "[data-test='passwordError']")
    private WebElement passwordError;

    @FindBy(css = "[data-test='repeatPasswordError']")
    private WebElement repeatError;

    public void open() {
        driver.get(ConfigReader.get("baseUrl") + "/signup");
    }

    public void submitForm(String firstname, String lastname,
                           String email, String password, String repeat) {
        type(firstnameInput, firstname);
        type(lastnameInput, lastname);
        type(emailInput, email);
        type(passwordInput, password);
        type(repeatPasswordInput, repeat);
        click(signupBtn);
    }

    public boolean isSuccessDisplayed() {
        return isDisplayed(successMsg);
    }

    public String getSuccessMessage() {
        return getText(successMsg);
    }

    public String getGlobalError() {
        return getText(globalError);
    }

    public String getFirstnameError() {
        return getText(firstnameError);
    }

    public String getLastnameError() {
        return getText(lastnameError);
    }

    public String getEmailError() {
        return getText(emailError);
    }

    public String getPasswordError() {
        return getText(passwordError);
    }

    public String getRepeatError() {
        return getText(repeatError);
    }
}