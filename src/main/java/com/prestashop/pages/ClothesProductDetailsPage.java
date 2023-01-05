package com.prestashop.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import com.prestashop.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.prestashop.constants.Url.CLOTHES_PRODUCT_DETAILS_PAGE_URL;

public class ClothesProductDetailsPage extends BasePage {

    @FindBy(css = "select[name='group[1]']")
    private WebElement dropdownDimensionFilter;

    @FindBy(css = ".product-container.row > div:nth-of-type(2)")
    private WebElement productDetails;

    @FindBy(css = ".add-to-cart.btn.btn-primary")
    private WebElement addToCartButton;

    @FindBy(css = ".btn.btn-primary > .material-icons.rtl-no-flip")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".bootstrap-touchspin-up.btn.btn-touchspin.js-touchspin > .material-icons.touchspin-up")
    private WebElement addMoreItemsButton;

    @FindBy(css = ".product-quantity > strong")
    private WebElement productQuantity;

    @FindBy(css = ".product-total .value")
    private WebElement totalValue;

    @FindBy(css = ".btn.btn-secondary")
    private WebElement continueShoppingButton;

    public ClothesProductDetailsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Get expected url")
    public String getExpectedUrl(){
        return CLOTHES_PRODUCT_DETAILS_PAGE_URL;
    }

    @Step("Selecting option from dropdown for clothe product")
    public void selectSizeL(int i) {
        log.info("Selecting option " + i + " from dropdown for clothe product");
        selectOptionFromDropdown(this.dropdownDimensionFilter, i);
    }

    @Step("Get selected option")
    public String getSelectedOption() {
        return getSelectedOptionFromDropdown(this.dropdownDimensionFilter);
    }

    @Step("Get text for clothes products from description to compare further in test")
    public String textDescriptionProduct(){
        log.info("Get text for clothes products from description to compare further in test");
        return getTextWebElement(productDetails);
    }

    @Step("Click on add to cart button form product details page")
    public void clickAddToCartButton(){
        log.info("Click on add to cart button form product details page");
        click(this.addToCartButton, "addToCartButton");
    }

    @Step("Click on proceed to checkout button from popup")
    public void clickProceedToCheckoutButton(){
        log.info("Click on proceed to checkout button from popup");
        waitForVisibilityElement(this.proceedToCheckoutButton);
        click(this.proceedToCheckoutButton, "proceedToCheckoutButton");
    }

    @Step("Click to add one more item of first popular product")
    public void clickAddMoreItemsButton(){
        log.info("Click to add one more item of first popular product");
        addMoreItemsButton.click();
    }

    @Step("Get text from product quantity")
    public String getProductQuantity(){
        log.info("Get text from product quantity");
        waitForVisibilityElement(this.productQuantity);
        return getTextWebElement(this.productQuantity);
    }

    @Step("Get the total cost of clothes products from cart")
    public String getTotalCost(){
        log.info("Get the total cost of clothes products from cart");
        return getTextWebElement(this.totalValue);
    }

    @Step("Click on continue shopping button from popup")
    public void clickContinueShoppingButton(){
        log.info("Click on continue shopping button from popup");
        waitForVisibilityElement(this.continueShoppingButton);
        continueShoppingButton.click();
    }
}