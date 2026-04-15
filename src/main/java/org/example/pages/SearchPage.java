package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(css = "[data-test='searchInput']")
    private WebElement searchInput;

    @FindBy(css = "[data-test='searchBtn']")
    private WebElement searchButton;

    @FindBy(css = "[data-test='resultsContainer']")
    private List<WebElement> productNames;

    @FindBy(css = "[data-test='errorMsg']")
    private WebElement noResultsMessage;

    public void searchFor(String keyword) {
        type(searchInput, keyword);
        click(searchButton);
    }

    public int getResultsCount() {
        return productNames.size();
    }

    public boolean resultsContain(String keyword) {
        for (WebElement name : productNames) {
            if (name.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void clickFirstResult() {
        if (!productNames.isEmpty()) {
            click(productNames.get(0));
        }
    }

    public boolean isNoResultsDisplayed() {
        return isDisplayed(noResultsMessage);
    }
}