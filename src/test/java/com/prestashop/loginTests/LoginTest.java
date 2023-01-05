package com.prestashop.loginTests;

import com.prestashop.base.BaseTest;
import com.prestashop.constants.MessagesAndAlerts;
import com.prestashop.pages.HeaderPageSection;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.LoginPage;
import com.prestashop.pages.PasswordRecoveryPage;
import com.prestashop.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.prestashop.constants.UserInfo.*;

@Listeners({TestListener.class})

public class LoginTest extends BaseTest {
    @Test(description = "TestCase 01 - User has successfully log in into account")
    public void successfullyLogInTest(){
        log.info("Starting TC 01 - successfullyLogInTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        // open store page
        homePage.openHomePage();
        // go to login page
        headerPageSection.clickSignInButton();
        // assert test
        Assert.assertEquals(loginPage.getCurrentUrl(), loginPage.getExpectedUrl(), "Login page is not displayed!");
        // log in with valid credentials
        loginPage.logIn(EMAIL_ADDRESS_EU, PASSWORD_EU);
        // verify if all fields are populated
        Assert.assertTrue(loginPage.verifyIfUsernameFiledIsPopulated(), "Username filed is not populated!");
        Assert.assertTrue(loginPage.verifyIfPasswordFiledIsPopulated(), "Password filed is not populated!");
        loginPage.clickLogIn();
        // verify if user successfully register
        Assert.assertTrue(headerPageSection.isVisibleSignOutButton(), "Sign out button is not visible, therefore the user maybe could not to log in into account!");
    }

    @Test(description = "TestCase 02 - User can't log in into account because username filed is left empty")
    public void emptyUsernameLogInTest(){
        log.info("Starting TC 02 - emptyUsernameLogInTest()");
        LoginPage loginPage = new LoginPage(driver, log);
        // user is already on login page
        loginPage.openLoginPageUrl();
        // log in with invalid credentials
        loginPage.logIn("", PASSWORD_EU);
        // verify if all fields are populated
        Assert.assertTrue(loginPage.verifyIfUsernameFiledIsEmpty(), "Username filed is populated!");
        Assert.assertTrue(loginPage.verifyIfPasswordFiledIsPopulated(), "Password filed is not populated!");
        loginPage.clickLogIn();
        // assert test - verify if error message is displayed
        Assert.assertEquals(loginPage.getFirstNameErrorMessage(), MessagesAndAlerts.EMPTY_FIELD_ERROR_MESSAGE, "Error message is not displayed!");
    }

    @Test(description = "TestCase 03 - User can't log in into account because password is to short")
    public void shortPasswordLogInTest(){
        log.info("Starting TC 03 - shortPasswordLogInTest()");
        LoginPage loginPage = new LoginPage(driver, log);
        // user is already on login page
        loginPage.openLoginPageUrl();
        // log in with invalid email and valid password
        loginPage.logIn(EMAIL_ADDRESS_EU, SHORT_PASSWORD);
        // verify if all fields are populated
        Assert.assertTrue(loginPage.verifyIfUsernameFiledIsPopulated(), "Username filed is not populated!");
        Assert.assertTrue(loginPage.verifyIfPasswordFiledIsPopulated(), "Password filed is not populated!");
        loginPage.clickLogIn();
        // assert test - verify if the waring message for password shorter than 5 chars is displayed
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), MessagesAndAlerts.PASSWORD_VALIDATION_MESSAGE, "Warning message for short password is not displayed!");
    }

    @Test(description = "TestCase 04 - User can't log in into account because password is incorrect")
    public void wrongPasswordLogInTest(){
        log.info("Starting TC 04 - wrongPasswordLogInTest()");
        LoginPage loginPage = new LoginPage(driver, log);
        // user is already on login page
        loginPage.openLoginPageUrl();
        // log in with valid email and invalid password
        loginPage.logIn(EMAIL_ADDRESS_EU, WRONG_PASSWORD);
        // verify if all fields are populated
        Assert.assertTrue(loginPage.verifyIfUsernameFiledIsPopulated(), "Username filed is not populated!");
        Assert.assertTrue(loginPage.verifyIfPasswordFiledIsPopulated(), "Password filed is not populated!");
        loginPage.clickLogIn();
        // assert test - verify if the waring message for password shorter than 5 chars is displayed
        Assert.assertEquals(loginPage.getAlertMessage(), MessagesAndAlerts.ALERT_MESSAGE, "Warning message is not displayed!");
    }

    @Test(description = "TestCase 05 - User can't log in into account because email is incorrect")
    public void wrongEmailLogInTest(){
        log.info("Starting TC 05 - wrongEmailLogInTest()");
        LoginPage loginPage = new LoginPage(driver, log);
        // user is already on login page
        loginPage.openLoginPageUrl();
        // log in with valid email and invalid password
        loginPage.logIn(WRONG_EMAIL_ADDRESS, PASSWORD_EU);
        // verify if all fields are populated
        Assert.assertTrue(loginPage.verifyIfUsernameFiledIsPopulated(), "Username filed is not populated!");
        Assert.assertTrue(loginPage.verifyIfPasswordFiledIsPopulated(), "Password filed is not populated!");
        loginPage.clickLogIn();
        // assert test - verify if the waring message for password shorter than 5 chars is displayed
        Assert.assertEquals(loginPage.getAlertMessage(), MessagesAndAlerts.ALERT_MESSAGE, "Warning message is not displayed!");
    }

    @Test(description = "TestCase 06 - User reset password")
    public void resetPasswordTest(){
        log.info("Starting TC 06 - resetPasswordTest()");
        LoginPage loginPage = new LoginPage(driver, log);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver, log);
        // user is already on login page
        loginPage.openLoginPageUrl();
        // click on forgot password
        loginPage.clickForgotPassword();
        // assert test
        Assert.assertEquals(passwordRecoveryPage.getCurrentUrl(), passwordRecoveryPage.getExpectedUrl(), "Password recovery page is not displayed!");
        // enter registered email
        passwordRecoveryPage.enterUsername(EMAIL_ADDRESS_EU);
        // verify if field is populated
        Assert.assertTrue(passwordRecoveryPage.verifyIfUsernameFiledIsPopulated(), "Username filed is not populated!");
        // click on send reset link button
        passwordRecoveryPage.clickResetPasswordButton();
    }
}