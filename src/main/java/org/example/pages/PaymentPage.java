package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(css = "[data-test='firstName']")
    private WebElement firstNameInput;

    @FindBy(css = "[data-test='lastName']")
    private WebElement lastNameInput;

    @FindBy(css = "[data-test='postalCode']")
    private WebElement postalCodeInput;

    @FindBy(css = "[data-test='continueBtn']")
    private WebElement continueBtn;

    @FindBy(css = "[data-test='finishBtn']")
    private WebElement finishBtn;

    @FindBy(css = "[data-test='confirmationMsg']")
    private WebElement confirmationMessage;

    @FindBy(css = "[data-test='total']")
    private WebElement totalLabel;

    @FindBy(css = "[data-test='errorMsg']")
    private WebElement errorMessage;

    public void fillShippingInfo(String firstName, String lastName, String postalCode) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, postalCode);
        click(continueBtn);
    }

    public void finishOrder() {
        click(finishBtn);
    }

    public boolean isOrderConfirmed() {
        return isDisplayed(confirmationMessage);
    }

    public String getConfirmationMessage() {
        return getText(confirmationMessage);
    }

    public String getTotalAmount() {
        return getText(totalLabel);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}