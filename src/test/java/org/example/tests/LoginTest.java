package org.example.tests;


import org.example.base.BaseTest;
import org.example.dataProviders.TestDataProvider;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void openPage() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @Test(
            description = "login réussi avec credentials valides",
            dataProvider = "loginValid",
            dataProviderClass = TestDataProvider.class
    )

    public void testLoginSuccess(String email, String password) {
        loginPage.loginWith(email, password);
        HomePage homePage = new HomePage();
        Assert.assertTrue(
                homePage.isLoaded(), "homepage non chargée pour : " + email
        );
        Assert.assertEquals(
                homePage.getPageTitle(), "Products", "titre incorrect après login pour : " + email
        );
    }

    @Test(
            description = "erreur affichée pour credentials invalides",
            dataProvider = "loginInvalid",
            dataProviderClass = TestDataProvider.class
    )
    public void testLoginFailure(String email, String password, String expectedMessage) {
        loginPage.loginWith(email, password);

        Assert.assertTrue(
                loginPage.isErrorDisplayed(), "aucun message d'erreur pour : " + email
        );
        Assert.assertTrue(
                loginPage.getErrorMessage().contains(expectedMessage),
                "message non attendu : " + loginPage.getErrorMessage()
        );
    }
}
