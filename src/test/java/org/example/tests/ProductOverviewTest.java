package org.example.tests;

import org.example.base.Authentication;
import org.example.pages.ProductOverviewPage;
import org.example.pages.SearchPage;
import org.example.utils.DriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductOverviewTest extends Authentication {

    private SearchPage searchPage;
    private ProductOverviewPage productPage;

    @BeforeMethod
    public void initPages() {
        searchPage  = new SearchPage();
        productPage = new ProductOverviewPage();
    }

    @Test(description = "la page affiche les bonnes infos")
    public void visibleProductDetails() {

        searchPage.searchFor("hoka");
        searchPage.clickFirstResult();

        Assert.assertTrue(
                productPage.isLoaded(),
                "la page ne se charge pas"
        );
        Assert.assertFalse(
                productPage.getProductName().isEmpty(),
                "le nom du produit est vide"
        );
        Assert.assertFalse(
                productPage.getProductPrice().isEmpty(),
                "le prix du produit est vide"
        );
        Assert.assertFalse(
                productPage.getProductDescription().isEmpty(),
                "la description du produit est vide"
        );
    }

    @Test(description = "le bouton back redirige vers la liste des produits")
    public void backButtonWorks() {
        searchPage.searchFor("sauce");
        searchPage.clickFirstResult();

        Assert.assertTrue(productPage.isLoaded(), "page produit non chargé");

        productPage.goBack();

        Assert.assertTrue(
                getCurrentUrl().contains("inventory"),
                "l'url est incorrecte après retour"
        );
    }

    private String getCurrentUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }
}