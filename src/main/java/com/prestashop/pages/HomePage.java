package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.prestashop.constants.Url.HOME_PAGE_URL;

public class HomePage extends BasePage {

    @FindBy(css = ".js-product-miniature.product-miniature")
    private WebElement itemsProduct;

    @FindBy(css = "img[alt='Hummingbird printed t-shirt']")
    private WebElement firstPopularProduct;

    @FindBy(css = "img[alt='Brown bear printed sweater']")
    private WebElement secondPopularProduct;

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Open Home page")
    public void openHomePage(){
        log.info("Opening page: " + HOME_PAGE_URL);
        openUrl(HOME_PAGE_URL);
        log.info("Page opened!");
    }

    @Step("Check that at least one product is displayed on the home page")
    public boolean isDisplayedItems(){
        log.info("Check that at least one product is displayed on the home page");
        return checkIfElementIsDisplayed(this.itemsProduct);
    }

    @Step("Click on first popular product from the home page")
    public void clickFirstPopularProduct(){
        log.info("Click on first popular product from the home page");
        click(this.firstPopularProduct, "firstPopularProduct");
    }

    @Step("Click on second popular product from the home page")
    public void clickSecondPopularProduct(){
        log.info("Click on second popular product from the home page");
        click(this.secondPopularProduct, "secondPopularProduct");
    }
}