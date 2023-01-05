package com.prestashop.searchFieldTests;

import com.prestashop.base.BaseTest;
import com.prestashop.pages.HeaderPageSection;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.SearchResultPage;
import com.prestashop.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.prestashop.constants.WordsForSearchBox.*;

@Listeners({TestListener.class})

public class SearchFieldTest extends BaseTest {

    @Test (description = "TestCase 01 - Search a product providing a relevant name")
    public void searchBoxTest() {
        log.info("Starting TC 01 - resetPasswordTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        SearchResultPage searchResultPage = new SearchResultPage(driver, log);
        // open home page
        homePage.openHomePage();
        // assert test
        Assert.assertTrue(headerPageSection.isDisplayedSearchFiled(), "Search filed is not displayed!");
        // search a product
        headerPageSection.searchProduct(RELEVANT_NAME_SEARCH);
        // assert test
        Assert.assertTrue(headerPageSection.isSearchBoxPopulated(), "Search box is not populated!");
        Assert.assertTrue(searchResultPage.isDisplayedItemSearch(), "Items page is not displayed!");
    }

    @Test (description="TestCase 02 - Search a product providing an empty string")
    public void searchBoxEmptyTest() {
        log.info("Starting TC 02 - resetPasswordTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        SearchResultPage searchResultPage = new SearchResultPage(driver, log);
        // open home page
        homePage.openHomePage();
        // assert test
        Assert.assertTrue(headerPageSection.isDisplayedSearchFiled(), "Search filed is not displayed!");
        // search a product
        headerPageSection.searchProduct(EMPTY_STRING_SEARCH);
        // assert test
        Assert.assertTrue(headerPageSection.isSearchBoxPopulated(), "Search box is populated!");
        Assert.assertTrue(searchResultPage.isWarningMessageDisplayed(), "Warning message is not displayed!");
    }

    @Test (description="TestCase 03 - Search a product providing a single character")
    public void searchBoxOneCharacterTest() {
        log.info("Starting TC 03 - resetPasswordTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        SearchResultPage searchResultPage = new SearchResultPage(driver, log);
        // open home page
        homePage.openHomePage();
        // search a product
        headerPageSection.searchProduct(ONE_CHARACTER_SEARCH);
        // assert test;
        Assert.assertTrue(headerPageSection.isSearchBoxPopulated(), "Search box is not populated!");
        Assert.assertTrue(searchResultPage.isWarningMessageDisplayed(), "Warning message is not displayed!");
    }

    @Test (description="TestCase 04 - Search a product providing a special character.")
    public void searchBoxSpecialCharacterTest() {
        log.info("Starting TC 04 - searchBoxSpecialCharacterTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        SearchResultPage searchResultPage = new SearchResultPage(driver, log);
        // open home page
        homePage.openHomePage();
        // search a product
        headerPageSection.searchProduct(SPECIAL_CHARACTER_SEARCH);
        // assert test
        Assert.assertTrue(headerPageSection.isSearchBoxPopulated(), "Search box is not populated!");
        Assert.assertTrue(searchResultPage.isWarningMessageDisplayed(), "Warning message is not displayed!");
    }
}