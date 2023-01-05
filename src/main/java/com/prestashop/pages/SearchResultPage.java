package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {

    @FindBy(css = ".js-product-miniature.product-miniature")
    private WebElement product;

    @FindBy(css = "section#content")
    private WebElement warningMessage;

    public SearchResultPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Verify if items search is displayed")
    public boolean isDisplayedItemSearch(){
        log.info("Verify if items search is displayed");
        return checkIfElementIsDisplayed(this.product);
    }

    @Step("Verify if warning message is displayed")
    public boolean isWarningMessageDisplayed(){
        log.info("Verify if warning message is displayed");
        return checkIfElementIsDisplayed(this.warningMessage);
    }
}