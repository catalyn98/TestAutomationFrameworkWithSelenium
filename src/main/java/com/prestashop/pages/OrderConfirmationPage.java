package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BasePage {

    @FindBy(css = "section#content-hook_order_confirmation .col-md-12")
    private WebElement confirmOrderMessage;

    public OrderConfirmationPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Verify if order has been placed successfully and an e-mail is received")
    public boolean isDisplayedConfirmOrderMessage(){
        log.info("Verify if order has been placed successfully and an e-mail is received");
        return checkIfElementIsDisplayed(this.confirmOrderMessage);
    }
}