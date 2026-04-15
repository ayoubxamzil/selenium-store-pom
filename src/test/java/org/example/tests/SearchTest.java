package org.example.tests;

import org.example.base.Authentication;
import org.example.dataProviders.TestDataProvider;
import org.example.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SearchTest extends Authentication {

    private SearchPage searchPage;


    @BeforeMethod
    public void initPage() {
        searchPage = new SearchPage();
    }

    @Test(
            description = "résultats trouvés pour un mot clé valide",
            dataProvider = "searchValid",
            dataProviderClass = TestDataProvider.class
    )
    public void searchFindsResults(String keyword, String expectedMinResults) {
        searchPage.searchFor(keyword);

        int minExpected = Integer.parseInt(expectedMinResults);

        Assert.assertTrue(
                searchPage.getResultsCount() >= minExpected,
                "pas assez de résultats pour: " + keyword
        );
        Assert.assertTrue(
                searchPage.resultsContain(keyword),
                "aucun résultat pour: " + keyword
        );
    }

    @Test(
            description = "message 'No results' pour un mot clé inexistant",
            dataProvider = "searchNoResults",
            dataProviderClass = TestDataProvider.class
    )

    public void searchNoResults(String keyword) {
        searchPage.searchFor(keyword);

        Assert.assertTrue(
                searchPage.isNoResultsDisplayed(),
                "message 'No results' non affiché pour : " + keyword
        );
        Assert.assertEquals(
                searchPage.getResultsCount(), 0,
                "des résultats affiché pour un mot inexistant : " + keyword
        );
    }
}