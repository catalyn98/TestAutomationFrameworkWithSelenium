package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.prestashop.constants.Url.*;

public class LoginPage extends BasePage {

    @FindBy(css = "section input[name='email']")
    private WebElement emailInput;

    @FindBy(css = "input[name='password']")
    private WebElement passwordInput;

    @FindBy(css = "button#submit-login")
    private WebElement buttonLogIn;

    @FindBy(css = "[data-link-action='display-register-form']")
    private WebElement linkCreateNewAccount;

    @FindBy(css = ".alert.alert-danger")
    private WebElement alertMessage;

    @FindBy(css = "form#login-form a[rel='nofollow']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Open login page")
    public void openLoginPageUrl(){
        log.info("Opening page: " + LOGIN_PAGE_URL);
        openUrl(LOGIN_PAGE_URL);
        log.info("Page opened!");
    }

    @Step("Get expected url")
    public String getExpectedUrl(){
        return LOGIN_PAGE_URL;
    }

    @Step("Login into account")
    public void logIn(String username, String password) {
        log.info("Login into account with email " + username + " and password " + password);
        sendKeys(username, this.emailInput, 10);
        sendKeys(password, this.passwordInput, 10);
    }

    @Step("Click on login button")
    public void clickLogIn(){
        log.info("Click on login button");
        click(this.buttonLogIn, "button log in");
    }

    @Step("Click on link to redirect to the create account page")
    public void clickLinkCreateNewAccount(){
        log.info("Click on link to redirect to the create account page");
        click(linkCreateNewAccount, "linkCreateNewAccount");
    }

    @Step("Verify if email input filed is populated")
    public boolean verifyIfUsernameFiledIsPopulated(){
        log.info("Verify if email input filed is populated");
        return verifyIfFiledIsPopulated(this.emailInput);
    }

    @Step("Verify if password filed is populated")
    public boolean verifyIfPasswordFiledIsPopulated(){
        log.info("Verify if password filed is populated");
        return verifyIfFiledIsPopulated(this.passwordInput);
    }

    @Step("Verify if email filed is empty")
    public boolean verifyIfUsernameFiledIsEmpty(){
        log.info("Verify if email filed is empty");
        return verifyIfFieldIsEmpty(this.emailInput);
    }

    @Step("Verify if error message for first name filed is displayed")
    public String getFirstNameErrorMessage(){
        log.info("Verify if error message for first name filed is displayed");
        return getErrorMessage(this.emailInput);
    }

    @Step("Verify if error message for password filed is displayed")
    public String getPasswordErrorMessage(){
        log.info("Verify if error message for password filed is displayed");
        return getErrorMessage(this.passwordInput);
    }

    @Step("Verify if the waring message for password shorter than 5 chars is displayed")
    public String getAlertMessage(){
        log.info("Verify if the waring message for password shorter than 5 chars is displayed");
        return getTextWebElement(this.alertMessage);
    }

    @Step("Click on link to redirect to the password recovery page")
    public void clickForgotPassword(){
        log.info("Click on link to redirect to the password recovery page");
        forgotPasswordLink.click();
    }
}