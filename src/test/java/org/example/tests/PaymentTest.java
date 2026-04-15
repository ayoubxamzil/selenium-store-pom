package org.example.tests;

import org.example.base.Authentication;
import org.example.dataProviders.TestDataProvider;
import org.example.pages.CartPage;
import org.example.pages.PaymentPage;
import org.example.pages.ProductOverviewPage;
import org.example.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentTest extends Authentication {

    private SearchPage searchPage;
    private ProductOverviewPage productPage;
    private CartPage cartPage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void initPages() {
        searchPage  = new SearchPage();
        productPage = new ProductOverviewPage();
        cartPage    = new CartPage();
        paymentPage = new PaymentPage();
    }

    private void addProductAndGoToCheckout() {
        searchPage.searchFor("nike");
        searchPage.clickFirstResult();
        productPage.addToCart();
        cartPage.open();
        cartPage.proceedToCheckout();
    }

    @Test(
            description = "commande finalisée avec infos valides",
            dataProvider = "paymentValid",
            dataProviderClass = TestDataProvider.class
    )
    public void paymentValid(String firstname, String lastname, String postalcode) {
        addProductAndGoToCheckout();

        paymentPage.fillShippingInfo(firstname, lastname, postalcode);

        paymentPage.finishOrder();

        Assert.assertTrue(
                paymentPage.isOrderConfirmed(),
                "la confirmation de commande n'est pas affichée"
        );
        Assert.assertTrue(
                paymentPage.getConfirmationMessage().contains("Thank you"),
                "message de confirmation non attendu : " + paymentPage.getConfirmationMessage()
        );
    }

    @Test(
            description = "erreur affichée si les champs de livraison sont invalides",
            dataProvider = "paymentInvalid",
            dataProviderClass = TestDataProvider.class
    )
    public void paymentInvalid(String firstname, String lastname,
                                      String postalcode, String expectedMessage) {
        addProductAndGoToCheckout();

        paymentPage.fillShippingInfo(firstname, lastname, postalcode);

        Assert.assertTrue(
                paymentPage.isErrorDisplayed(),
                "aucune erreur affichée pour des champs invalides"
        );
        Assert.assertTrue(
                paymentPage.getErrorMessage().contains(expectedMessage),
                "message d'erreur inattendu : " + paymentPage.getErrorMessage()
        );
    }
}
