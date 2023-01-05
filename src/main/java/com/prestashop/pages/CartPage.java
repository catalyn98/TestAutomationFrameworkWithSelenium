package com.prestashop.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import com.prestashop.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(css = ".cart-detailed-actions .btn-primary")
    WebElement proceedToCheckoutButton;

    @FindBy(css = ".cart-summary-line.cart-total > .value")
    private WebElement totalCostCart;

    @FindBy(css = "div#cart-subtotal-products > .js-subtotal.label")
    private WebElement totalNumberOfProductsInCart;

    @FindBy(css = ".remove-from-cart > .float-xs-left.material-icons")
    private WebElement deleteButtonCart;

    @FindBy(css = ".js-cart > .no-items")
    private WebElement noMoreItemsText;

    public CartPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Click on proceed to checkout button from popup")
    public void clickProceedToCheckoutButton(){
        log.info("Click on proceed to checkout button from popup");
        click(this.proceedToCheckoutButton, "proceedToCheckoutButton");
    }

    @Step("Get the total number of products from cart")
    public String getTotalNumberOfProductsFromCart(){
        log.info("Get the total number of products from cart");
        return getTextWebElement(totalNumberOfProductsInCart);
    }

    @Step("Get the total cost of products from cart")
    public String getTotalCostCart(){
        log.info("Get the total cost of products from cart");
        return getTextWebElement(totalCostCart);
    }

    @Step("Click to delete products from cart")
    public void clickDeleteProductButtonCart() {
        log.info("Click to delete products from cart");
        deleteButtonCart.click();
    }

    @Step("Get text from cart to verify if after deleting all products there are no more products")
    public  String getNoMoreItemsText(){
        log.info("Get text from cart to verify if after deleting all products there are no more products");
        waitForVisibilityElement(this.noMoreItemsText);
        return getTextWebElement(noMoreItemsText);
    }
}