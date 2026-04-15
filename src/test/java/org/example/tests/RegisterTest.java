package org.example.tests;

import org.example.base.BaseTest;
import org.example.dataProviders.TestDataProvider;
import org.example.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeMethod
    public void openPage() {
        registerPage = new RegisterPage();
        registerPage.open();
    }

    @Test(
            description = "inscription réussie avec données valides",
            dataProvider = "signupValid",
            dataProviderClass = TestDataProvider.class
    )
    public void signupValid(String firstname, String lastname,
                                  String email, String password, String repeat) {
        registerPage.submitForm(firstname, lastname, email, password, repeat);

        Assert.assertTrue(
                registerPage.isSuccessDisplayed(), "message de succès non affiché"
        );
    }

    @Test(
            description = "erreurs de validation affichées pour données invalides",
            dataProvider = "signupInvalid",
            dataProviderClass = TestDataProvider.class
    )

    public void signupInvalid(String firstname, String lastname, String email,
                                  String password, String repeat,
                                  String expectedField, String expectedMessage) {
        registerPage.submitForm(firstname, lastname, email, password, repeat);

        String actualError = getFieldError(expectedField);

        Assert.assertFalse(
                actualError.isEmpty(),
                "aucune erreur sur le champ : " + expectedField
        );
        Assert.assertTrue(
                actualError.contains(expectedMessage),
                "message inattendu sur '" + expectedField + "' : " + actualError
        );
    }


    private String getFieldError(String field) {
        switch (field) {
            case "firstname": return registerPage.getFirstnameError();
            case "lastname":  return registerPage.getLastnameError();
            case "email":     return registerPage.getEmailError();
            case "password":  return registerPage.getPasswordError();
            case "repeat":    return registerPage.getRepeatError();
            case "global":    return registerPage.getGlobalError();
            default: throw new IllegalArgumentException("Champ inconnu : " + field);
        }
    }
}