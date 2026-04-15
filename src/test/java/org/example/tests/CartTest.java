package org.example.tests;


import org.example.base.Authentication;
import org.example.pages.CartPage;
import org.example.pages.ProductOverviewPage;
import org.example.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends Authentication {

    private SearchPage searchPage;
    private ProductOverviewPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void initPages() {
        searchPage = new SearchPage();
        productPage = new ProductOverviewPage();
        cartPage = new CartPage();
    }

    @Test(description = "le produit est ajouté dans le panier")
    public void testAddProductToCart() {
        searchPage.searchFor("nike");
        searchPage.clickFirstResult();

        String productName = productPage.getProductName();

        productPage.addToCart();

        cartPage.open();

        Assert.assertEquals(
                cartPage.getCartCount(), 1,
                "le panier doit contenir 1 article"
        );
        Assert.assertTrue(
                cartPage.containsProduct(productName),
                "le produit '" + productName + "' n'est pas dans le panier"
        );
    }

    @Test(description = "le panier s'incrémente après l'ajout des produits")
    public void testCartBadgeIncrement() {
        Assert.assertEquals(
                cartPage.getCartCount(), 0,
                "le panier doit être vide au départ"
        );

        searchPage.searchFor("nike");
        searchPage.clickFirstResult();
        productPage.addToCart();

        Assert.assertEquals(
                cartPage.getCartCount(), 1,
                "le badge doit afficher 1 après l'ajout d'un produit"
        );
    }
}
