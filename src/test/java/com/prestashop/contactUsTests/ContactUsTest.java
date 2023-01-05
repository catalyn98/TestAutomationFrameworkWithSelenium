package com.prestashop.contactUsTests;

import com.prestashop.base.BaseTest;
import com.prestashop.constants.MessagesAndAlerts;
import com.prestashop.pages.ContactUsPage;
import com.prestashop.pages.HeaderPageSection;
import com.prestashop.pages.HomePage;
import com.prestashop.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.prestashop.constants.MessagesAndAlerts.MESSAGE;
import static com.prestashop.constants.UserInfo.EMAIL_ADDRESS_EU;
import static com.prestashop.constants.UserInfo.EMAIL_WITHOUT_AT_NEU;

@Listeners({TestListener.class})

public class ContactUsTest extends BaseTest {

    @Test(description = "TestCase 01 - The user try to contact the store providing a valid email address")
    public void contactUsValidEmailTest() {
        log.info("Starting TC 01 - contactUsValidEmailTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        ContactUsPage contactUsPage = new ContactUsPage(driver, log);
        // open store page
        homePage.openHomePage();
        // go on to contact us page
        headerPageSection.clickLinkToContactUsPage();
        // assert test - verify if contact us page is displayed
        Assert.assertEquals(contactUsPage.getCurrentUrl(), contactUsPage.getExpectedUrl(), "Contact us page is not displayed!");
        // choose a subject from subject filed
        contactUsPage.selectOptionSubject(1);
        // assert test - verify if subject is choose
        Assert.assertTrue(contactUsPage.getSelectedOption().contains("Webmaster"), "Subject was not changed!");
        // write a valid email in the email filed
        contactUsPage.enterUsername(EMAIL_ADDRESS_EU);
        // assert test - verify if email address filed is populated
        Assert.assertTrue(contactUsPage.verifyIfEmailAddressFiledIsPopulated(), "The email address filed is not populated!");
        // write a message in message filed
        contactUsPage.enterMessage(MESSAGE);
        // assert test - verify if message field is populated
        Assert.assertTrue(contactUsPage.verifyIfMessageFiledIsPopulated(), "The message filed is not populated!");
        // click on send button
        contactUsPage.clickSendMessageButton();
        // assert test - verify if message is send
        Assert.assertTrue(contactUsPage.isVisibleAlertSuccessSendMessage(), "Confirmation message was not displayed!");
    }

    @Test(description = "TestCase 02 - The user try to contact the store providing an email address without @ symbol")
    public void contactUsInvalidEmailTest() {
        log.info("Starting TC 02 - contactUsInvalidEmailTest()");
        ContactUsPage contactUsPage = new ContactUsPage(driver, log);
        // user is already on contact us page
        contactUsPage.openContactUsPageUrl();
        // choose a subject from subject filed
        contactUsPage.selectOptionSubject(1);
        // assert test - verify if subject is choose
        Assert.assertTrue(contactUsPage.getSelectedOption().contains("Webmaster"), "Subject was not changed!");
        // write an invalid email in the email filed
        contactUsPage.enterUsername(EMAIL_WITHOUT_AT_NEU);
        // write a message in message filed
        contactUsPage.enterMessage(MESSAGE);
        // assert test - verify if message field is populated
        Assert.assertTrue(contactUsPage.verifyIfMessageFiledIsPopulated(), "The message filed is not populated!");
        // click on send button
        contactUsPage.clickSendMessageButton();
        //assert test - verify if the warning message for email without @ is displayed
        Assert.assertEquals(contactUsPage.getEmailErrorMessage(), MessagesAndAlerts.EMAIL_VALIDATION_ERROR_MESSAGE, "Warning message for email without @ is not displayed!");
    }

    @Test(description = "TestCase 03 - The user try to contact the store and provide an empty message")
    public void contactUsEmptyMessageTest() {
        log.info("Starting TC 03 - contactUsEmptyMessageTest()");
        ContactUsPage contactUsPage = new ContactUsPage(driver, log);
        // user is already on contact us page
        contactUsPage.openContactUsPageUrl();
        // choose a subject from subject filed
        contactUsPage.selectOptionSubject(1);
        // assert test - verify if subject is choose
        Assert.assertTrue(contactUsPage.getSelectedOption().contains("Webmaster"), "Subject was not changed!");
        // write a valid email in the email filed
        contactUsPage.enterUsername(EMAIL_ADDRESS_EU);
        // assert test - verify if email address filed is populated
        Assert.assertTrue(contactUsPage.verifyIfEmailAddressFiledIsPopulated(), "The email address filed is not populated!");
        // assert test - verify if message field is populated
        Assert.assertTrue(contactUsPage.verifyIfMessageFieldIsEmpty(), "The message filed is not empty!");
        // click on send button
        contactUsPage.clickSendMessageButton();
        //assert test - verify if the error message is displayed when user let input message field empty
        Assert.assertTrue(contactUsPage.isVisibleAlertDangerSendMessage(), "Alert danger message was not displayed!");
    }
}