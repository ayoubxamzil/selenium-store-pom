package org.example.dataProviders;

import org.example.utils.ConfigReader;
import org.example.utils.ExcelReader;
import org.testng.annotations.DataProvider;


public class TestDataProvider {

    private static final String FILE = ConfigReader.get("dataPath");


    @DataProvider(name = "loginValid")
    public static Object[][] loginValid() {
        return ExcelReader.getData(FILE, "LoginValid");
    }

    @DataProvider(name = "loginInvalid")
    public static Object[][] loginInvalid() {
        return ExcelReader.getData(FILE, "LoginInvalid");
    }


    @DataProvider(name = "signupValid")
    public static Object[][] signupValid() {
        return ExcelReader.getData(FILE, "SignupValid");
    }

    @DataProvider(name = "signupInvalid")
    public static Object[][] signupInvalid() {
        return ExcelReader.getData(FILE, "SignupInvalid");
    }


    @DataProvider(name = "searchValid")
    public static Object[][] searchValid() {
        return ExcelReader.getData(FILE, "SearchValid");
    }

    @DataProvider(name = "searchNoResults")
    public static Object[][] searchNoResults() {
        return ExcelReader.getData(FILE, "SearchNoResults");
    }


    @DataProvider(name = "paymentValid")
    public static Object[][] paymentValid() {
        return ExcelReader.getData(FILE, "PaymentValid");
    }

    @DataProvider(name = "paymentInvalid")
    public static Object[][] paymentInvalid() {
        return ExcelReader.getData(FILE, "PaymentInvalid");
    }
}