package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "[data-test='cartBtn']")
    private WebElement cartBtn;

    @FindBy(css = "[data-test='cartBadge']")
    private WebElement cartBadge;

    @FindBy(css = "[data-test='productContainer']")
    private List<WebElement> cartItemNames;

    @FindBy(css = "[data-test='checkoutBtn']")
    private WebElement checkoutBtn;

    @FindBy(css = "[data-test='continueBtn']")
    private WebElement continueShoppingBtn;

    public void open() {
        click(cartBtn);
    }

    public int getCartCount() {
        if (isDisplayed(cartBadge)) {
            return Integer.parseInt(getText(cartBadge));
        }
        return 0;
    }

    public boolean containsProduct(String productName) {
        for (WebElement item : cartItemNames) {
            if (item.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public int getItemsCount() {
        return cartItemNames.size();
    }

    public void proceedToCheckout() {
        click(checkoutBtn);
    }
}
