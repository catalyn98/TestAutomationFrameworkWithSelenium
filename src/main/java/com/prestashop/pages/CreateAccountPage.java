package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

import static com.prestashop.constants.Url.REGISTER_PAGE_URL;
import static com.prestashop.utils.Utils.getUniqueEmailAddress;

public class CreateAccountPage extends BasePage {

    @FindBy(css = "label:nth-of-type(1) > .custom-radio > input[name='id_gender']")
    private WebElement addressingMode;

    @FindBy(css = "input[name='firstname']")
    private WebElement inputFirstName;

    @FindBy(css = "input[name='lastname']")
    private WebElement inputLastName;

    @FindBy(css = "section input[name='email']")
    private WebElement inputEmail;

    @FindBy(css = "input[name='password']")
    private WebElement inputPassword;

    @FindBy(css = "input[name='birthday']")
    private WebElement inputBirthday;

    @FindBy(css = ".btn.btn-primary.float-xs-right.form-control-submit")
    private WebElement saveButton;

    @FindBy(css = "div:nth-of-type(2) > .col-md-6 > .help-block > ul > .alert.alert-danger")
    private WebElement firstAlert;

    @FindBy(css = "div:nth-of-type(3) > .col-md-6 > .help-block > ul > .alert.alert-danger")
    private WebElement secondAlert;

    public CreateAccountPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Open Create account Page")
    public void openCreateAccountPage(){
        log.info("Opening page: " + REGISTER_PAGE_URL);
        openUrl(REGISTER_PAGE_URL);
        log.info("Page opened!");
    }

    @Step("Get expected url")
    public String getExpectedUrl(){
        return REGISTER_PAGE_URL;
    }

    @Step("Selecting addressing mode")
    public void selectGender(){
        log.info("Selecting addressing mode");
        addressingMode.click();
    }

    @Step("Complete mandatory filed to create an account")
    public void createAccount(String firstName, String lastName, String password, String birthday){
        log.info("Complete mandatory filed to create an account");
        sendKeys(firstName, this.inputFirstName, 10);
        sendKeys(lastName, this.inputLastName, 10);
        sendKeys(getUniqueEmailAddress(), this.inputEmail, 10);
        sendKeys(password, this.inputPassword, 10);
        sendKeys(birthday, this.inputBirthday, 10);
    }

    @Step("Complete mandatory filed to create an account - polymorphism")
    public void createAccount(String firstName, String lastName, String email, String password, String birthday){
        sendKeys(firstName, this.inputFirstName, 10);
        sendKeys(lastName, this.inputLastName, 10);
        sendKeys(email, this.inputEmail, 10);
        sendKeys(password, this.inputPassword, 10);
        sendKeys(birthday, this.inputBirthday, 10);
    }

    @Step("Click on save button to complete the registration")
    public void clickOnSaveButton(){
        log.info("Click on save button to complete the registration");
        saveButton.click();
    }

    @Step("Verify if gender is selected")
    public boolean isGenderSelected(){
        log.info("Verify if gender is selected");
        return isOptionSelected(this.addressingMode);
    }

    @Step("Verify if first name filed is populated")
    public boolean verifyIfFirstNameFieldIsPopulated(){
        log.info("Verify if first name filed is populated");
        return verifyIfFiledIsPopulated(this.inputFirstName);
    }

    @Step("Verify if last name filed is populated")
    public boolean verifyIfLastNameFieldIsPopulated(){
        log.info("Verify if last name filed is populated");
        return verifyIfFiledIsPopulated(this.inputLastName);
    }

    @Step("Verify if password filed is populated")
    public boolean verifyIfPasswordFieldIsPopulated(){
        log.info("Verify if password filed is populated");
        return verifyIfFiledIsPopulated(this.inputPassword);
    }

    @Step("Verify if email filed is populated")
    public boolean verifyIfEmailFieldIsPopulated(){
        log.info("Verify if email filed is populated");
        return verifyIfFiledIsPopulated(this.inputEmail);
    }

    @Step("Verify if first name filed is empty")
    public boolean verifyIfFirstNameFieldIsEmpty(){
        log.info("Verify if first name filed is empty");
        return verifyIfFieldIsEmpty(this.inputFirstName);
    }

    @Step("Verify if last name filed is empty")
    public boolean verifyIfLastNameFieldIsEmpty(){
        log.info("Verify if last name filed is empty");
        return verifyIfFieldIsEmpty(this.inputLastName);
    }

    @Step("Verify if password filed is empty")
    public boolean verifyIfPasswordFieldIsEmpty(){
        log.info("Verify if password filed is empty");
        return verifyIfFieldIsEmpty(this.inputPassword);
    }

    @Step("Verify if email filed is empty")
    public boolean verifyIfEmailFieldIsEmpty(){
        log.info("Verify if email filed is empty");
        return verifyIfFieldIsEmpty(this.inputEmail);
    }

    @Step("Verify if first name filed has blank space")
    public boolean verifyIfFirstNameFieldHasBlankSpace(){
        log.info("Verify if first name filed has blank space");
        return verifyIfFieldIsBlank(this.inputFirstName);
    }

    @Step("Verify if last name filed has blank space")
    public boolean verifyIfLastNameFieldHasBlankSpace(){
        log.info("Verify if last name filed has blank space");
        return verifyIfFieldIsBlank(this.inputLastName);
    }

    @Step("Verify if error message for first name filed is displayed")
    public String getFirstNameErrorMessage(){
        log.info("Verify if error message for first name filed is displayed");
        return getErrorMessage(this.inputFirstName);
    }

    @Step("Verify if error message for first name filed is displayed")
    public String getEmailErrorMessage(){
        log.info("Verify if error message for first name filed is displayed");
        return getErrorMessage(this.inputEmail);
    }

    @Step("Verify if error message for password filed is displayed")
    public String getPasswordErrorMessage(){
        log.info("Verify if error message for password filed is displayed");
        return getErrorMessage(this.inputPassword);
    }

    @Step("Display alert message")
    public String getFirstAlertMessage() {
        return getTextWebElement(this.firstAlert);
    }

    @Step("Display alert message")
    public String getSecondAlertMessage() {
        return getTextWebElement(this.secondAlert);
    }
}