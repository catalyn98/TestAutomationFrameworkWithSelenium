package com.prestashop.filterTests;

import com.prestashop.base.BaseTest;
import com.prestashop.pages.*;
import com.prestashop.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.bouncycastle.util.Strings.toLowerCase;

@Listeners({TestListener.class})

public class FilterTest extends BaseTest {

    @Test(description = "TestCase 01 - Filter clothes by men category")
    public void filterMenClothesCategoriesTest() {
        log.info("Starting TC 01 - filterMenClothesCategoriesTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        ClothesPage clothesPage = new ClothesPage(driver, log);
        // open store page
        homePage.openHomePage();
        // go on clothes page
        headerPageSection.clickLinkClothesPage();
        // assert test - verify if clothes page is displayed
        Assert.assertEquals(clothesPage.getCurrentUrl(), clothesPage.getExpectedUrl(), "Clothes page is not displayed!");
        clothesPage.setCheckboxMenCategoriesFilter();
        // assert test - verify if only the clothes for men are displayed
        Assert.assertTrue(clothesPage.iterateThroughProducts("/men/"), "Are not displayed only the clothes for men!");
    }

    @Test(description = "TestCase 02 - Filter clothes by women category")
    public void filterWomenCategoriesTest() {
        log.info("Starting TC 02 - filterWomenCategoriesTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        ClothesPage clothesPage = new ClothesPage(driver, log);
        // open store page
        homePage.openHomePage();
        // go on clothes page
        headerPageSection.clickLinkClothesPage();
        // assert test - verify if clothes page is displayed
        Assert.assertEquals(clothesPage.getCurrentUrl(), clothesPage.getExpectedUrl(), "Clothes page is not displayed!");
        clothesPage.setCheckboxWomenCategoriesFilter();
        // assert test - verify if only the clothes for women are displayed
        Assert.assertTrue(clothesPage.iterateThroughProducts("/women/"), "Are not displayed only the clothes for women!");
    }

    @Test(description = "TestCase 03 - Filter clothes by size category")
    public void filterSizeLTest() {
        log.info("Starting TC 03 - filterSizeLTest()");
        ClothesPage clothesPage = new ClothesPage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        // open clothes page
        clothesPage.openClothesPage();
        // select a random product from homepage
        clothesPage.clickOnProduct();
        // select size L from Clothes product details page
        clothesProductDetailsPage.selectSizeL(3);
        // assert test
        Assert.assertTrue(clothesProductDetailsPage.getCurrentUrl().contains(toLowerCase(clothesProductDetailsPage.getSelectedOption())), "Size L is not available for clothes!");
    }

    @Test(description = "TestCase 04 - Filter clothes by white color category")
    public void filterWhiteClothesTest() {
        log.info("Starting TC 04 - filterWhiteClothesTest()");
        ClothesPage clothesPage = new ClothesPage(driver, log);
        // open clothes page
        clothesPage.openClothesPage();
        // hover on a product
        clothesPage.hoverOnProduct();
        // assert test
        Assert.assertTrue(clothesPage.isOptionWhiteAvailable(), "The white color is not available for clothes!");
    }

    @Test(description = "TestCase 05 - Filter clothes by black color category")
    public void filterBlackClothesTest() {
        log.info("Starting TC 05 - filterBlackClothesTest()");
        ClothesPage clothesPage = new ClothesPage(driver, log);
        // open clothes page
        clothesPage.openClothesPage();
        // hover on a product
        clothesPage.hoverOnProduct();
        // assert test
        Assert.assertTrue(clothesPage.isOptionBlackAvailable(), "The black color is not available for clothes!");
    }

    @Test(description = "TestCase 06 - Filter clothes by long sleeves category")
    public void filterWithLongSleevesClothesTest() {
        log.info("Starting TC 06 - filterWithLongSleevesClothesTest()");
        ClothesPage clothesPage = new ClothesPage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        // open clothes page
        clothesPage.openClothesPage();
        // checkbox properties long sleeves filter
        clothesPage.setCheckboxLongSleevesPropertyFilter();
        clothesPage.clickOnAnotherProduct();
        // assert test
        Assert.assertTrue(clothesProductDetailsPage.textDescriptionProduct().contains("long sleeves"), "The long sleeves property is not available for clothes!");
    }

    @Test(description = "TestCase 07 - Filter clothes by short sleeves category")
    public void filterWithShortSleevesClothesTest() {
        log.info("Starting TC 07 - filterWithShortSleevesClothesTest()");
        ClothesPage clothesPage = new ClothesPage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        // open clothes page
        clothesPage.openClothesPage();
        // checkbox properties short sleeves filter
        clothesPage.setCheckboxShortSleevesPropertyFilter();
        clothesPage.clickOnProduct();
        // assert test
        Assert.assertTrue(clothesProductDetailsPage.textDescriptionProduct().contains("short sleeves"), "The short sleeves property is not available for clothes!");
    }

    @Test(description = "TestCase 08 - Filter accessories by white color category")
    public void filterWhiteAccessoriesTest() {
        log.info("Starting TC 08 - filterWhiteAccessoriesTest()");
        HomePage homePage = new HomePage(driver, log);
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver, log);
        AccessoriesProductDetailsPage accessoriesProductDetailsPage = new AccessoriesProductDetailsPage(driver, log);
        // open store page
        homePage.openHomePage();
        // go on accessories page
        accessoriesPage.openAccessoriesPage();
        // assert test
        Assert.assertEquals(accessoriesPage.getCurrentUrl(), accessoriesPage.getExpectedUrl(), "Accessories page is not displayed!");
        // checkbox color filter
        accessoriesPage.setCheckboxWhiteColorFilter();
        accessoriesPage.clickOnProduct();
        // assert test
        Assert.assertTrue(accessoriesProductDetailsPage.getCurrentUrl().contains("culoare-alb"), "The white color is not available for accessories!");
    }

    @Test(description = "TestCase 09 - Filter accessories by black color category")
    public void filterBlackAccessoriesTest() throws InterruptedException {
        log.info("Starting TC 09 - filterBlackAccessoriesTest()");
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver, log);
        AccessoriesProductDetailsPage accessoriesProductDetailsPage = new AccessoriesProductDetailsPage(driver, log);
        // open accessories page
        accessoriesPage.openAccessoriesPage();
        // assert test
        Assert.assertEquals(accessoriesPage.getCurrentUrl(), accessoriesPage.getExpectedUrl(), "Accessories page is not displayed!");
        // checkbox color filter
        accessoriesPage.setCheckboxBlackColorFilter();
        accessoriesPage.clickOnProduct();
        accessoriesProductDetailsPage.clickBlackColorOption();
        Thread.sleep(1000);
        // assert test
        Assert.assertTrue(accessoriesProductDetailsPage.getCurrentUrl().contains("culoare-negru"), "The white color is not available for accessories!");
    }

    @Test(description = "TestCase 10 - Filter accessories by stationery category")
    public void filterStationeryAccessoriesTest() {
        log.info("Starting TC 10 - filterStationeryAccessoriesTest()");
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver, log);
        // open accessories page
        accessoriesPage.openAccessoriesPage();
        // checkbox stationery category filter
        accessoriesPage.setCheckboxStationeryCategoriesFilter();
        // assert test
        Assert.assertTrue(accessoriesPage.iterateThroughProducts("/stationery/"), "Are not displayed only the accessories for accessories!");
    }

    @Test(description = "TestCase 11 - Filter accessories by home accessories category")
    public void filterHomeAccessoriesTest() {
        log.info("Starting TC 11 - filterHomeAccessoriesTest()");
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver, log);
        // open accessories page
        accessoriesPage.openAccessoriesPage();
        // checkbox home accessories' category filter
        accessoriesPage.setCheckboxHomeAccessoriesCategoriesFilter();
        // assert test
        Assert.assertTrue(accessoriesPage.iterateThroughProducts("/home-accessories/"), "Are not displayed only the home-accessories for accessories!");
    }

    @Test(description = "TestCase 12 - Filter accessories by ceramic composition")
    public void filterCompositionCeramicAccessoriesTest() {
        log.info("Starting TC 12 - filterCompositionCeramicAccessoriesTest()");
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver, log);
        AccessoriesProductDetailsPage accessoriesProductDetailsPage = new AccessoriesProductDetailsPage(driver, log);
        // open accessories page
        accessoriesPage.openAccessoriesPage();
        // checkbox ceramic composition filter
        accessoriesPage.setCheckboxCeramicCompositionFilter();
        // click on a product
        accessoriesPage.clickOnProduct();
        // click on accessories details tab from product details tab
        accessoriesProductDetailsPage.clickOnProductDetails();
        // assert test
        Assert.assertTrue(accessoriesProductDetailsPage.getTextDescriptionProduct().contains("Ceramic"), "There are no accessories made from ceramic material!");
    }

    @Test(description = "TestCase 13 - Filter accessories by polyester composition")
    public void filterCompositionPolyesterAccessoriesTest() {
        log.info("Starting TC 13 - filterCompositionPolyesterAccessoriesTest()");
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver, log);
        AccessoriesProductDetailsPage accessoriesProductDetailsPage = new AccessoriesProductDetailsPage(driver, log);
        // open accessories page
        accessoriesPage.openAccessoriesPage();
        // checkbox polyester composition filter
        accessoriesPage.setCheckboxPolyesterCompositionFilter();
        // click on a product
        accessoriesPage.clickOnProduct();
        // click on accessories details tab from product details tab
        accessoriesProductDetailsPage.clickOnProductDetails();
        // assert test
        Assert.assertTrue(accessoriesProductDetailsPage.getTextDescriptionProduct().contains("Poliester"), "There are no accessories made from polyester material!");
    }

    @Test(description = "TestCase 14 - Filter accessories by recycled cardboard composition")
    public void filterCompositionRecycledAccessoriesTest() {
        log.info("Starting TC 14 - filterCompositionRecycledAccessoriesTest()");
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver, log);
        AccessoriesProductDetailsPage accessoriesProductDetailsPage = new AccessoriesProductDetailsPage(driver, log);
        // open accessories page
        accessoriesPage.openAccessoriesPage();
        // checkbox recycled cardboard composition filter
        accessoriesPage.setCheckboxRecycledCardboardCompositionFilter();
        // click on a product
        accessoriesPage.clickOnProduct();
        // click on accessories details tab from product details tab
        accessoriesProductDetailsPage.clickOnProductDetails();
        // assert test
        Assert.assertTrue(accessoriesProductDetailsPage.getTextDescriptionProduct().contains("Recycled Cardboard"), "There are no accessories made from recycled cardboard material!");
    }
}