package com.prestashop.createAccountTests;

import com.prestashop.base.BaseTest;
import com.prestashop.constants.MessagesAndAlerts;
import com.prestashop.pages.CreateAccountPage;
import com.prestashop.pages.HeaderPageSection;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.LoginPage;
import com.prestashop.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.prestashop.constants.UserInfo.*;

@Listeners({TestListener.class})

public class CreateAccountTest extends BaseTest {

    @Test(description = "TestCase 01  - Register a new user")
    public void registerNewUserSuccessfullyTest() {
        log.info("Starting TC 01 - registerNewUserSuccessfullyTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, log);
        // open store page
        homePage.openHomePage();
        // go to login page
        headerPageSection.clickSignInButton();
        // assert test
        Assert.assertEquals(loginPage.getCurrentUrl(), loginPage.getExpectedUrl(), "Login page is not displayed!");
        // click on create nw account hyperlink
        loginPage.clickLinkCreateNewAccount();
        // assert test
        Assert.assertEquals(createAccountPage.getCurrentUrl(), createAccountPage.getExpectedUrl(), "Create account page is not displayed!");
        // select genders from radio button
        createAccountPage.selectGender();
        // assert test - verify if gender could be selected
        Assert.assertTrue(createAccountPage.isGenderSelected(), "Gender couldn't be selected!");
        // fill out mandatory fields
        createAccountPage.createAccount(FIRST_NAME_NEU, LAST_NAME_NEU, PASSWORD_NEU, BIRTHDAY_NEU);
        //verify if all mandatory fields are populated
        Assert.assertTrue(createAccountPage.verifyIfFirstNameFieldIsPopulated(), "First name filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfLastNameFieldIsPopulated(), "Last name filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfEmailFieldIsPopulated(), "Email filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfPasswordFieldIsPopulated(), "Password filed is not populated!");
        // click on save button
        createAccountPage.clickOnSaveButton();
        // verify if user successfully register
        Assert.assertTrue(headerPageSection.isVisibleSignOutButton(), "Sign out button is not visible, therefore the user maybe could not to create an account!");
    }

    @Test(description = "TestCase 02 - User try to register and leaves empty mandatory fields")
    public void registerLeaveEmptyMandatoryFieldsTest(){
        log.info("Starting TC 02 - registerLeaveEmptyMandatoryFieldsTest()");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, log);
        // user is already on create account page
        createAccountPage.openCreateAccountPage();
        // select genders from radio button
        createAccountPage.selectGender();
        // assert test - verify if gender could be selected
        Assert.assertTrue(createAccountPage.isGenderSelected(), "Gender couldn't be selected!");
        //verify if all mandatory fields are left empty
        Assert.assertTrue(createAccountPage.verifyIfFirstNameFieldIsEmpty(), "First name filed is not empty!");
        Assert.assertTrue(createAccountPage.verifyIfLastNameFieldIsEmpty(), "Last name filed is not empty!");
        Assert.assertTrue(createAccountPage.verifyIfEmailFieldIsEmpty(), "Email filed is not empty!");
        Assert.assertTrue(createAccountPage.verifyIfPasswordFieldIsEmpty(), "Password filed is not empty!");
        // click on save button
        createAccountPage.clickOnSaveButton();
        // assert test - verify if error message is displayed
        Assert.assertEquals(createAccountPage.getFirstNameErrorMessage(), MessagesAndAlerts.EMPTY_FIELD_ERROR_MESSAGE, "Error message is not displayed!");
    }

    @Test(description = "TestCase 03 - User try to register and complete mandatory fields but enter email without @symbol!")
    public void enterEmailAddressWithoutAtSignTest(){
        log.info("Starting TC 03 - enterEmailAddressWithoutAtSignTest()");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, log);
        // user is already on create account page
        createAccountPage.openCreateAccountPage();
        // select genders from radio button
        createAccountPage.selectGender();
        // assert test - verify if gender could be selected
        Assert.assertTrue(createAccountPage.isGenderSelected(), "Gender couldn't be selected!");
        // fill out mandatory fields
        createAccountPage.createAccount(FIRST_NAME_NEU, LAST_NAME_NEU, EMAIL_WITHOUT_AT_NEU, PASSWORD_NEU, BIRTHDAY_NEU);
        //verify if all mandatory fields are populated
        Assert.assertTrue(createAccountPage.verifyIfFirstNameFieldIsPopulated(), "First name filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfLastNameFieldIsPopulated(), "Last name filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfEmailFieldIsPopulated(), "Email filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfPasswordFieldIsPopulated(), "Password filed is not populated!");
        // click on save button
        createAccountPage.clickOnSaveButton();
        //assert test - verify if the warning message for email without @ is displayed
        Assert.assertEquals(createAccountPage.getEmailErrorMessage(), MessagesAndAlerts.EMAIL_VALIDATION_ERROR_MESSAGE, "Warning message for email without @ is not displayed!");
    }

    @Test(description = "TestCase 04 - User try to register and complete mandatory fields but enter a short password!")
    public void enterShortPasswordTest(){
        log.info("Starting TC 04 - enterShortPasswordTest()");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, log);
        // user is already on create account page
        createAccountPage.openCreateAccountPage();
        // select genders from radio button
        createAccountPage.selectGender();
        // assert test - verify if gender could be selected
        Assert.assertTrue(createAccountPage.isGenderSelected(), "Gender couldn't be selected!");
        // fill out mandatory fields
        createAccountPage.createAccount(FIRST_NAME_NEU, LAST_NAME_NEU, SHORT_PASSWORD, BIRTHDAY_NEU);
        //verify if all mandatory fields are populated
        Assert.assertTrue(createAccountPage.verifyIfFirstNameFieldIsPopulated(), "First name filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfLastNameFieldIsPopulated(), "Last name filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfEmailFieldIsPopulated(), "Email filed is not populated!");
        Assert.assertTrue(createAccountPage.verifyIfPasswordFieldIsPopulated(), "Password filed is not populated!");
        // click on save button
        createAccountPage.clickOnSaveButton();
        // assert test - verify if the waring message for password shorter than 5 chars is displayed
        Assert.assertEquals(createAccountPage.getPasswordErrorMessage(), MessagesAndAlerts.PASSWORD_VALIDATION_MESSAGE, "Warning message for short password is not displayed!");
    }

    @Test(description = "TestCase 05 - User try to register and complete mandatory fields but enter a short password!")
    public void enterBlankSpacesRequiredFieldTest(){
        log.info("Starting TC 05 - enterBlankSpacesRequiredFieldTest()");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, log);
        // user is already on create account page
        createAccountPage.openCreateAccountPage();
        // select genders from radio button
        createAccountPage.selectGender();
        // assert test - verify if gender could be selected
        Assert.assertTrue(createAccountPage.isGenderSelected(), "Gender couldn't be selected!");
        // fill out mandatory fields
        createAccountPage.createAccount(" ", " ",PASSWORD_NEU, BIRTHDAY_NEU);
        //assert test - verify if all mandatory fields are populated with blank space
        Assert.assertTrue(createAccountPage.verifyIfFirstNameFieldHasBlankSpace(),"First name filed has not blank space!");
        Assert.assertTrue(createAccountPage.verifyIfLastNameFieldHasBlankSpace(),"Last name filed has not blank space!");
        // click on save button
        createAccountPage.clickOnSaveButton();
        // assert test - verify if error message is displayed
        Assert.assertEquals(createAccountPage.getFirstAlertMessage(), MessagesAndAlerts.INVALID_FORMAT_ERROR_MESSAGE);
        Assert.assertEquals(createAccountPage.getSecondAlertMessage(), MessagesAndAlerts.INVALID_FORMAT_ERROR_MESSAGE);
    }
}