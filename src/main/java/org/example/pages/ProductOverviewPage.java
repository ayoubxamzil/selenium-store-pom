package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductOverviewPage extends BasePage {

    @FindBy(css = "[data-test='productName']")
    private WebElement productName;

    @FindBy(css = "[data-test='productDesc']")
    private WebElement productDescription;

    @FindBy(css = "[data-test='price']")
    private WebElement productPrice;

    @FindBy(css = "[data-test='addToCartBtn']")
    private WebElement addToCartButton;

    @FindBy(css = "[data-test='backBtn']")
    private WebElement backButton;

    public String getProductName() {
        return getText(productName);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public boolean isLoaded() {
        return isDisplayed(productName);
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void goBack() {
        click(backButton);
    }
}