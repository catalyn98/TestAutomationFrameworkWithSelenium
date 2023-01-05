package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.prestashop.constants.Url.CHECKOUT_PAGE_URL;
import static com.prestashop.constants.UserInfo.*;

public class OrderPage extends BasePage {

    @FindBy(css = "input[name='firstname']")
    private WebElement inputFirstName;

    @FindBy(css = "input[name='lastname']")
    private WebElement inputLastName;

    @FindBy(css = "form#customer-form > section input[name='email']")
    private WebElement inputEmail;

    @FindBy(css = "input[name='address1']")
    private WebElement inputAddress;

    @FindBy(css = "input[name='postcode']")
    private WebElement inputZipPostalCode;

    @FindBy(css = "input[name='city']")
    private WebElement inputCity;

    @FindBy(css = "input[name='phone']")
    private WebElement inputPhone;

    @FindBy(css = "button[name='confirm-addresses']")
    private WebElement confirmAddressButton;

    @FindBy(css = "form#customer-form  button[name='continue']")
    private WebElement continueButton;

    @FindBy(css = "#checkout-delivery-step input#delivery_option_2")
    private WebElement radioButtonMyCarrier;

    @FindBy(css = "form#js-delivery > button[name='confirmDeliveryOption']")
    private WebElement confirmDeliveryOption;

    @FindBy(css = "#checkout-payment-step input#payment-option-1")
    private WebElement radioButtonPayByCheck;

    @FindBy(css = "input#conditions_to_approve\\[terms-and-conditions\\]")
    private WebElement checkboxTermsAndConditions;

    @FindBy(css = ".btn.btn-primary.center-block")
    private WebElement placeOrderButton;

    @FindBy(css = "div#delivery-addresses > .address-item")
    private WebElement personalInformationForDelivery;

    @FindBy(css = "label:nth-of-type(2) > .custom-radio > input[name='id_gender']")
    private WebElement mrsBox;

    public OrderPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Verify if the user complete his personal information before placing the order")
    public boolean checkIfPersonalInformationAlreadyExists(){
        log.info("Verify if the user complete his personal information before placing the order");
        return checkIfInfoAlreadyExists(this.personalInformationForDelivery);
    }

    @Step("Method checkIfPersonalInformationAlreadyExists() returned true, in this case user have to complete the personal information")
    public void completePersonalInformationForm(String address, String postalCode, String city, String phone){
        log.info("Method checkIfPersonalInformationAlreadyExists() returned true, in this case user have to complete the personal information");
        sendKeys(address, this.inputAddress, 10);
        sendKeys(postalCode, this.inputZipPostalCode, 10);
        sendKeys(city, this.inputCity, 10);
        sendKeys(phone, this.inputPhone, 10);
    }

    @Step("Click button to confirm address")
    public void clickConfirmAddressButton(){
        log.info("Click button to confirm address");
        click(this.confirmAddressButton, "confirmAddressButton");
    }

    @Step("Selecting shipping method")
    public void selectShippingMethod(){
        log.info("Selecting shipping method");
        this.radioButtonMyCarrier.click();
    }

    @Step("Click button to confirm shipping method")
    public void clickConfirmShippingMethod(){
        log.info("Click button to confirm shipping method");
        click(this.confirmDeliveryOption, "confirmDeliveryOption");
    }

    @Step("Selecting payment method")
    public void selectPaymentMethod(){
        log.info("Selecting payment method");
        this.radioButtonPayByCheck.click();
    }

    @Step("Check terms and conditions")
    public void checkTermsAndConditions(){
        log.info("Check terms and conditions");
        this.checkboxTermsAndConditions.click();
    }

    @Step("Click button to place the order")
    public void clickPlaceOrderButton(){
        log.info("Click button to place the order");
        click(this.placeOrderButton, "placeOrderButton");
    }

    @Step("Get expected url")
    public String getExpectedUrl(){
        return CHECKOUT_PAGE_URL;
    }

    @Step("Verify if terms and conditions are accepted")
    public boolean isCheckedTermsAndConditions(){
        log.info("Verify if terms and conditions are accepted");
        return isOptionSelected(this.checkboxTermsAndConditions);
    }

    @Step("Verify if payment method could be selected")
    public boolean isPaymentMethodSelected(){
        log.info("Verify if payment method could be selected");
        return isOptionSelected(this.radioButtonPayByCheck);
    }

    @Step("Verify if shipping method could be selected")
    public boolean isShippingMethodSelected(){
        log.info("Verify if shipping method could be selected");
        return isOptionSelected(this.radioButtonMyCarrier);
    }

    @Step("Selecting addressing mode")
    public void selectAddressingMode(){
        log.info("Selecting addressing mode");
        mrsBox.click();
    }

    @Step("Click to continue button to complete address, postal code, city and phone")
    public void clickContinueToAddressButton(){
        log.info("Click to continue button to complete address, postal code, city and phone");
        click(this.continueButton, "continueButton");
    }

    @Step("Complete user details when user is not logged in")
    public void completeUserDetailsWhenUserIsNotLoggedIn(String firstName, String lastName, String email, String address, String postalCode, String city, String phone){
        log.info("Complete user details when user is not logged in");
        selectAddressingMode();
        sendKeys(firstName, this.inputFirstName, 10);
        sendKeys(lastName, this.inputLastName, 10);
        sendKeys(email, this.inputEmail, 10);
        clickContinueToAddressButton();
        sendKeys(address, this.inputAddress, 10);
        sendKeys(postalCode, this.inputZipPostalCode, 10);
        sendKeys(city, this.inputCity, 10);
        sendKeys(phone, this.inputPhone, 10);
    }

    @Step("Complete order details when user is not logged in")
    public void completeOrderDetailsWhenUserIsNotLoggedIn(){
        log.info("Complete order details when user is not logged in");
        completeUserDetailsWhenUserIsNotLoggedIn(FIRST_NAME_NEU, LAST_NAME_NEU, EMAIL_ADDRESS_NEU, ADDRESS_NEU, ZIP_CODE_NEU, CITY_NEU, PHONE_NEU);
        clickConfirmAddressButton();
        clickConfirmShippingMethod();
        selectPaymentMethod();
        checkTermsAndConditions();
        clickPlaceOrderButton();
    }
}