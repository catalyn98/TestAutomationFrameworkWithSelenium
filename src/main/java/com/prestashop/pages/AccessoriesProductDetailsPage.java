package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccessoriesProductDetailsPage extends BasePage {

    @FindBy(css = "li:nth-of-type(2) > a[role='tab']")
    private WebElement detailsOfProduct;

    @FindBy(css = "div#product-details > .product-features")
    private WebElement materialProduct;

    @FindBy(css = "li:nth-of-type(2) > label > input[name='group[2]']")
    private WebElement blackOption;

    public AccessoriesProductDetailsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Click on accessories product to see the complete details of this")
    public void clickOnProductDetails() {
        log.info("Click on accessories product to see the complete details of this");
        click(this.detailsOfProduct, "detailsOfProduct");
        waitForVisibilityElement(this.materialProduct);
    }

    @Step("Get text for accessories products from description to compare further in test")
    public String getTextDescriptionProduct(){
        log.info("Get text for accessories products from description to compare further in test");
        return getTextWebElement(materialProduct);
    }

    @Step("Click on black color option to verify if there are accessories of this color")
    public void clickBlackColorOption(){
        log.info("Click on black color option to verify if there are accessories of this color");
        blackOption.click();
    }
}