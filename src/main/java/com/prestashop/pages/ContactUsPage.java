package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.prestashop.constants.Url.CONTACT_US_PAGE_URL;

public class ContactUsPage extends BasePage {

    @FindBy(css = "select[name='id_contact']")
    private WebElement dropdownSubject;

    @FindBy(css = "input[name='from']")
    private WebElement emailInput;

    @FindBy(css = "textarea[name='message']")
    private WebElement messageInput;

    @FindBy(css = "input[name='submitMessage']")
    private WebElement sendButton;

    @FindBy(css = ".alert.alert-success.col-xs-12")
    private WebElement alertSuccessSendMessage;

    @FindBy(css = ".alert.alert-danger.col-xs-12")
    private WebElement alertDangerSendMessage;

    public ContactUsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Open Contact us Page")
    public void openContactUsPageUrl(){
        log.info("Opening page: " + CONTACT_US_PAGE_URL);
        openUrl(CONTACT_US_PAGE_URL);
        log.info("Page opened!");
    }

    @Step("Get expected url")
    public String getExpectedUrl(){
        return CONTACT_US_PAGE_URL;
    }

    @Step("User enter his email")
    public void enterUsername(String username) {
        log.info("User enter his email");
        sendKeys(username, this.emailInput, 10);
    }

    @Step("Selecting option from dropdown in contact us page")
    public void selectOptionSubject(int i) {
        log.info("Selecting option " + i + " from dropdown in contact us page");
        selectOptionFromDropdown(this.dropdownSubject, i);
    }

    @Step("Get selected option")
    public String getSelectedOption() {
        return getSelectedOptionFromDropdown(this.dropdownSubject);
    }

    @Step("User enter message in message input")
    public void enterMessage(String message){
        log.info("User enter message in message input");
        sendKeys(message, this.messageInput, 10);
    }

    @Step("Click on send button to send the message")
    public void clickSendMessageButton(){
        log.info("Click on send button to send the message");
        sendButton.click();
    }

    @Step("Verify if email address filed is populated")
    public boolean verifyIfEmailAddressFiledIsPopulated(){
        log.info("Verify if email address filed is populated");
        return verifyIfFiledIsPopulated(this.emailInput);
    }

    @Step("Verify if message filed is populated")
    public boolean verifyIfMessageFiledIsPopulated(){
        log.info("Verify if message filed is populated");
        return verifyIfFiledIsPopulated(this.messageInput);
    }

    @Step("Verify if success alert message is visible on the screen after the user send the message")
    public boolean isVisibleAlertSuccessSendMessage(){
        log.info("Verify if success alert message is visible on the screen after the user send the message");
        return checkIfElementIsDisplayed(this.alertSuccessSendMessage);
    }

    @Step("Verify if error message is visible on the screen after the user send the message")
    public String getEmailErrorMessage(){
        log.info("Verify if error message is visible on the screen after the user send the message");
        return getErrorMessage(this.emailInput);
    }

    @Step("Verify if message filed is empty")
    public boolean verifyIfMessageFieldIsEmpty() {
        log.info("Verify if message filed is empty");
        return verifyIfFieldIsEmpty(this.messageInput);
    }

    @Step("Verify if danger alert message is visible on the screen after the user send the message")
    public boolean isVisibleAlertDangerSendMessage(){
        log.info("Verify if danger alert message is visible on the screen after the user send the message");
        return checkIfElementIsDisplayed(this.alertDangerSendMessage);
    }
}