package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

import static com.prestashop.constants.Url.CLOTHES_PAGE_URL;

public class ClothesPage extends BasePage {

    @FindBy(css = "section:nth-of-type(1) > .collapse > li:nth-of-type(1) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkboxMenCategoriesFilter;

    @FindBy(css = "section:nth-of-type(1) > .collapse > li:nth-of-type(2) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkBoxWomenCategoriesFilter;

    @FindBy(css = "a[title='Alb']")
    private WebElement checkBoxWhiteColorFilter;

    @FindBy(css = "a[title='Negru']")
    private WebElement checkBoxBlackColorFilter;

    @FindBy(css = "section:nth-of-type(4) > .collapse > li:nth-of-type(1) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkBoxLongSleevesPropertyFilter;

    @FindBy(css = "section:nth-of-type(4) > .collapse > li:nth-of-type(2) > .facet-label > .custom-checkbox > .ps-shown-by-js")
    private WebElement checkBoxShortSleevesPropertyFilter;

    @FindBy(css = "a.thumbnail.product-thumbnail")
    List<WebElement> listClothesProducts;

    @FindBy(css = "img[alt='Hummingbird printed t-shirt']")
    private WebElement itemProductMen;

    @FindBy(css = "img[alt='Brown bear printed sweater']")
    private WebElement itemProductWomen;

    @FindBy(css = ".js-product-miniature.product-miniature")
    private WebElement itemsProduct;

    @FindBy(css = ".filter-block")
    private WebElement filterActive;

    public ClothesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Open Clothes Page")
    public void openClothesPage(){
        log.info("Opening page: " + CLOTHES_PAGE_URL);
        openUrl(CLOTHES_PAGE_URL);
        log.info("Page opened!");
    }

    @Step("Get expected url")
    public String getExpectedUrl(){
        return CLOTHES_PAGE_URL;
    }

    @Step("Set checkbox men categories filter for clothes products")
    public void setCheckboxMenCategoriesFilter() {
        log.info("Set checkbox men categories filter for clothes products");
        checkboxMenCategoriesFilter.click();
    }

    @Step("Set checkbox women categories filter for clothes products")
    public void setCheckboxWomenCategoriesFilter() {
        log.info("Set checkbox women categories filter for clothes products");
        checkBoxWomenCategoriesFilter.click();
    }

    @Step("Set checkbox long sleeves property filter for clothes products")
    public void setCheckboxLongSleevesPropertyFilter() {
        log.info("Set checkbox long sleeves property filter for clothes products");
        checkBoxLongSleevesPropertyFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Set checkbox short sleeves property filter for clothes products")
    public void setCheckboxShortSleevesPropertyFilter() {
        log.info("Set checkbox short sleeves property filter for clothes products");
        checkBoxShortSleevesPropertyFilter.click();
        waitForVisibilityElement(this.filterActive);
    }

    @Step("Iterate through clothes product to verify if all clothes displayed contain the specified filter")
    public boolean iterateThroughProducts(String filter){
        log.info("Iterate through clothes product to verify if all clothes displayed contain the specified filter");
        waitForVisibilityElement(this.filterActive);
        return iterateThroughProduct(listClothesProducts, filter);
    }

    @Step("Hover on clothe product")
    public void hoverOnProduct() {
        log.info("Hover on clothe product");
        hoverOn(this.itemProductMen);
    }

    @Step("Verify if white color option is available for clothes product")
    public boolean isOptionWhiteAvailable(){
        log.info("Verify if white color option is available for clothes product");
        return isOptionColorAvailable(this.checkBoxWhiteColorFilter);
    }

    @Step("Verify if black color option is available for clothes product")
    public boolean isOptionBlackAvailable(){
        log.info("Verify if black color option is available for clothes product");
        return isOptionColorAvailable(this.checkBoxBlackColorFilter);
    }

    @Step("Click on product, a product for men")
    public void clickOnProduct() {
        log.info("Click on product, a product for men");
        click(this.itemProductMen, "itemProductMen");
    }

    @Step("Click on product, a product for women")
    public void clickOnAnotherProduct(){
        log.info("Click on product, a product for women");
        click(this.itemProductWomen, "itemProductWomen");
    }

    @Step("Verify if the items clothes are displayed")
    public boolean isDisplayedItems(){
        log.info("Verify if the items clothes are displayed");
        return checkIfElementIsDisplayed(this.itemsProduct);
    }
}