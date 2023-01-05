package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.prestashop.constants.Url.ACCESSORIES_PAGE_URL;

public class AccessoriesPage extends BasePage {

    @FindBy(css = "li:nth-of-type(1) > .facet-label > .custom-checkbox > .color")
    private WebElement checkBoxWhiteColorFilter;

    @FindBy(css = "li:nth-of-type(2) > .facet-label > .custom-checkbox > .color")
    private WebElement checkBoxBlackColorFilter;

    @FindBy(css = "section:nth-of-type(1) > .collapse > li:nth-of-type(2) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkBoxStationeryCategoriesFilter;

    @FindBy(css = "section:nth-of-type(1) > .collapse > li:nth-of-type(1) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkBoxHomeAccessoriesCategoriesFilter;

    @FindBy(css = "section:nth-of-type(3) > .collapse > li:nth-of-type(1) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkBoxCeramicCompositionFilter;

    @FindBy(css = "section:nth-of-type(3) > .collapse > li:nth-of-type(2) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkBoxPolyesterCompositionFilter;

    @FindBy(css = "section:nth-of-type(3) > .collapse > li:nth-of-type(3) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkBoxRecycledCardboardCompositionFilter;

    @FindBy(css = "div:nth-of-type(1) > .js-product-miniature.product-miniature h2 > a")
    private WebElement itemProductPillow;

    @FindBy(css = "a.thumbnail.product-thumbnail")
    List<WebElement> listAccessoriesProducts;

    @FindBy(css = ".filter-block")
    private WebElement filterActive;

    public AccessoriesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Open Accessories Page")
    public void openAccessoriesPage(){
        log.info("Opening page: " + ACCESSORIES_PAGE_URL);
        openUrl(ACCESSORIES_PAGE_URL);
        log.info("Page opened!");
    }

    @Step("Get expected url")
    public String getExpectedUrl(){
        return ACCESSORIES_PAGE_URL;
    }

    @Step("Set checkbox white color filter for accessories products")
    public void setCheckboxWhiteColorFilter() {
        log.info("Set checkbox white color filter for accessories products");
        checkBoxWhiteColorFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Set checkbox black color filter for accessories products")
    public void setCheckboxBlackColorFilter() {
        log.info("Set checkbox black color filter for accessories products");
        checkBoxBlackColorFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Set checkbox stationery category filter for accessories products")
    public void setCheckboxStationeryCategoriesFilter() {
        log.info("Set checkbox stationery category filter for accessories products");
        checkBoxStationeryCategoriesFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Set checkbox home accessories category filter for accessories products")
    public void setCheckboxHomeAccessoriesCategoriesFilter() {
        log.info("Set checkbox home accessories category filter for accessories products");
        checkBoxHomeAccessoriesCategoriesFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Set checkbox composition filter for accessories products that are made from ceramic")
    public void setCheckboxCeramicCompositionFilter() {
        log.info("Set checkbox composition filter for accessories products that are made from ceramic");
        checkBoxCeramicCompositionFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Set checkbox composition filter for accessories products that are made from polyester")
    public void setCheckboxPolyesterCompositionFilter() {
        log.info("Set checkbox composition filter for accessories products that are made from polyester");
        checkBoxPolyesterCompositionFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Set checkbox composition filter for accessories products that are made from recycled cardboard")
    public void setCheckboxRecycledCardboardCompositionFilter()  {
        log.info("Set checkbox composition filter for accessories products that are made from recycled cardboard");
        checkBoxRecycledCardboardCompositionFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Pick an accessories product (pillow) and click on")
    public void clickOnProduct() {
        log.info("Pick an accessories product (pillow) and click on");
        click(this.itemProductPillow, "itemProductPillow");
    }

    @Step("Iterate through accessories product to verify if all accessories displayed contain the specified filter")
    public boolean iterateThroughProducts(String filter){
        log.info("Iterate through accessories product to verify if all accessories displayed contain the specified filter");
        waitForVisibilityElement(this.filterActive);
        return iterateThroughProduct(listAccessoriesProducts, filter);
    }
}