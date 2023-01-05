package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.prestashop.constants.Url.PASSWORD_RECOVERY_PAGE_URL;

public class PasswordRecoveryPage extends BasePage {

    @FindBy(css = "input#email")
    private WebElement emailInput;

    @FindBy(css = ".center-email-fields.form-group > button:nth-of-type(1)")
    private WebElement sendResetPasswordButton;

    public PasswordRecoveryPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Get expected url")
    public String getExpectedUrl(){
        return PASSWORD_RECOVERY_PAGE_URL;
    }

    @Step("Enter email address to recover password")
    public void enterUsername(String username) {
        log.info("Enter email address to recover password");
        sendKeys(username, this.emailInput, 10);
    }

    @Step("Click on reset password button")
    public void clickResetPasswordButton(){
        log.info("Click on reset password button");
        sendResetPasswordButton.click();
    }

    @Step("Verify if email input filed is populated")
    public boolean verifyIfUsernameFiledIsPopulated(){
        log.info("Verify if email input filed is populated");
        return verifyIfFiledIsPopulated(this.emailInput);
    }
}