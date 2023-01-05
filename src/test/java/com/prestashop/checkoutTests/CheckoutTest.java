package com.prestashop.checkoutTests;

import com.prestashop.base.BaseTest;
import com.prestashop.constants.MessagesAndAlerts;
import com.prestashop.pages.*;
import com.prestashop.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.prestashop.constants.UserInfo.*;

@Listeners({TestListener.class})

public class CheckoutTest extends BaseTest {

    @Test(description = "TestCase 01  - User can complete a successful checkout")
    public void successfulCheckoutTest()  {
        log.info("Starting TC 01 - successfulCheckoutTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        CartPage cartPage = new CartPage(driver, log);
        OrderPage orderPage = new OrderPage(driver, log);
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // click to log in into account
        headerPageSection.clickSignInButton();
        // log in with valid credentials
        loginPage.logIn(EMAIL_ADDRESS_EU, PASSWORD_EU);
        loginPage.clickLogIn();
        // click on image logo to redirect to the main page
        headerPageSection.clickLogo();
        // assert test - check that at least one product is displayed on the home page
        Assert.assertTrue(homePage.isDisplayedItems(), "No product is displayed on the home page!");
        // select a random product from homepage
        homePage.clickFirstPopularProduct();
        // assert test - verify if product details page is displayed
        Assert.assertEquals(clothesProductDetailsPage.getCurrentUrl(), clothesProductDetailsPage.getExpectedUrl(), "Product details page is not displayed!");
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on proceed to checkout button from popup
        clothesProductDetailsPage.clickProceedToCheckoutButton();
        // assert test - verify if product is added to cart
        Assert.assertEquals(headerPageSection.getCartCount(), "(1)", "Product was not added to cart!");
        // click on proceed to checkout button from cart page
        cartPage.clickProceedToCheckoutButton();
        // assert test - verify if proceed to checkout page is displayed
        Assert.assertEquals(orderPage.getCurrentUrl(), orderPage.getExpectedUrl());
        // verify if the user complete his personal information before placing the order
        if(orderPage.checkIfPersonalInformationAlreadyExists()){ // if checkIfPersonalInformationAlreadyExists() method return true
            // then the user have to complete the personal information
            orderPage.completePersonalInformationForm(ADDRESS_EU, ZIP_CODE_EU, CITY_EU, PHONE_EU);
        }
        // click to confirm address
        orderPage.clickConfirmAddressButton();
        // select a shipping method
        orderPage.selectShippingMethod();
        // assert test - verify if shipping method could be selected
        Assert.assertTrue(orderPage.isShippingMethodSelected(), "Shipping method couldn't be selected!");
        // click confirm shipping method to continue to the payment section
        orderPage.clickConfirmShippingMethod();
        // select a payment method
        orderPage.selectPaymentMethod();
        // assert test - verify if payment method could be selected
        Assert.assertTrue(orderPage.isPaymentMethodSelected(), "Payment method couldn't be selected!");
        // check the terms and conditions checkbox to agree on terms
        orderPage.checkTermsAndConditions();
        // assert test - verify if terms and conditions are accepted
        Assert.assertTrue(orderPage.isCheckedTermsAndConditions(), "Terms and conditions are not accepted!");
        // click on place the order
        orderPage.clickPlaceOrderButton();
        // assert test - verify if order has been placed successfully and an e-mail is received
        Assert.assertTrue(orderConfirmationPage.isDisplayedConfirmOrderMessage(), "Confirm order message is not displayed, therefore the order maybe not been placed!");
    }

    @Test(description = "TestCase 02 - A product is added to cart")
    public void addProductToCartTest()  {
        log.info("Starting TC 02 - addProductToCartTest()");
        HomePage homePage = new HomePage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // select a random product from homepage
        homePage.clickFirstPopularProduct();
        // assert test - verify if product details page is displayed
        Assert.assertEquals(clothesProductDetailsPage.getCurrentUrl(), clothesProductDetailsPage.getExpectedUrl(), "Product details page is not displayed!");
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on proceed to checkout button from popup
        clothesProductDetailsPage.clickProceedToCheckoutButton();
        // assert test - verify if product is added to cart and cart number is updated
        Assert.assertEquals(headerPageSection.getCartCount(), "(1)", "Product was not added to cart or cart number was not updated!");
    }

    @Test(description = "TestCase 03 - Validate product details page")
    public void validateProductDetailsPageTest() {
        log.info("Starting TC 03 - validateProductDetailsPageTest()");
        HomePage homePage = new HomePage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        ClothesPage clothesPage = new ClothesPage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // go to my store - clothes page
        headerPageSection.clickLinkClothesPage();
        // assert test - check that at least one product is displayed on the clothes page
        Assert.assertTrue(clothesPage.isDisplayedItems(), "No product is displayed on the clothes page!");
        // click on product
        clothesPage.clickOnProduct();
        // assert test - verify if product details page is displayed
        Assert.assertEquals(clothesProductDetailsPage.getCurrentUrl(), clothesProductDetailsPage.getExpectedUrl(), "Product details page is not displayed!");
    }

    @Test(description = "TestCase 04 - Switch new tab and close precedent tab don't remove added products from cart")
    public void addToCartThenSwitchTabTest() {
        log.info("Starting TC 04 - addToCartThenSwitchTabTest()");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // click to log in into account
        headerPageSection.clickSignInButton();
        // log in with valid credentials
        loginPage.logIn(EMAIL_ADDRESS_EU, PASSWORD_EU);
        loginPage.clickLogIn();
        // click on image logo to redirect to the main page
        headerPageSection.clickLogo();
        // select a random product from homepage
        homePage.clickFirstPopularProduct();
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on proceed to checkout button from popup
        clothesProductDetailsPage.clickProceedToCheckoutButton();
        // assert test - verify if item is added to cart
        Assert.assertEquals(headerPageSection.getCartCount(), "(1)", "Item was not added to cart!");
        homePage.openNewTab();
        // assert test - verify if product is kept in the cart
        Assert.assertEquals(headerPageSection.getCartCount(), "(1)", "The item is not kept in the cart!");
    }

    @Test(description = "TestCase 05 - Change the quantity, total cost of Cart is updated")
    public void changeQuantityTest() {
        log.info("Starting TC 05 - changeQuantityTest()");
        HomePage homePage = new HomePage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // select a random product from home page
        homePage.clickFirstPopularProduct();
        // click to add one more item of first popular product
        clothesProductDetailsPage.clickAddMoreItemsButton();
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // assert test - verify if product details page is displayed
        Assert.assertEquals(clothesProductDetailsPage.getCurrentUrl(), clothesProductDetailsPage.getExpectedUrl(), "Products details page is not displayed!");
        // verify if the product  quantity is updated
        Assert.assertEquals(clothesProductDetailsPage.getProductQuantity(),"2");
        // verify if the cost of product is updated
        Assert.assertEquals(clothesProductDetailsPage.getTotalCost(),"45,51 lei");
    }

    @Test(description = "TestCase 06 - User add different products and different quantities")
    public void addDifferentProductsAndDifferentQuantitiesTest() {
        log.info("Starting TC 06 - addDifferentProductsAndDifferentQuantitiesTest()");
        HomePage homePage = new HomePage(driver, log);
        ClothesPage clothesPage = new ClothesPage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        CartPage cartPage = new CartPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // go to my store - clothes page
        headerPageSection.clickLinkClothesPage();
        // assert test - check that at least one product is displayed on the clothes page
        Assert.assertTrue(clothesPage.isDisplayedItems(), "No product is displayed on the clothes page!");
        /*
         * add first product
         */
        // click on item
        clothesPage.clickOnProduct();
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on continue shopping button from popup
        clothesProductDetailsPage.clickContinueShoppingButton();
        // click on image logo to redirect to the main page
        headerPageSection.clickLogo();
        /*
         * add second product
         */
        // select a random product from homepage
        homePage.clickSecondPopularProduct();
        // click to add one more item of first popular product
        clothesProductDetailsPage.clickAddMoreItemsButton();
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on continue shopping button from popup
        clothesProductDetailsPage.clickContinueShoppingButton();
        // assert test - verify if product is added to cart and cart number is updated
        Assert.assertEquals(headerPageSection.getCartCount(),"(3)", "Products was not added to cart or cart number was not updated!");
        // go to cart page
        headerPageSection.clickCartButton();
        // verify the total number of products in cart
        Assert.assertEquals(cartPage.getTotalNumberOfProductsFromCart(),"3 articole");
        // verify if the total cost of cart  is updated = 91,11 lei
        Assert.assertEquals(cartPage.getTotalCostCart(),"91,11 lei");
    }

    @Test(description = "TestCase 07 - Remove all items form the cart")
    public void removeItemsFromCartTest() {
        log.info("Starting TC 07 - removeItemsFromCartTest()");
        HomePage homePage = new HomePage(driver, log);
        ClothesPage clothesPage = new ClothesPage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        CartPage cartPage = new CartPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // go to my store - clothes page
        headerPageSection.clickLinkClothesPage();
        // assert test - check that at least one product is displayed on the clothes page
        Assert.assertTrue(clothesPage.isDisplayedItems(), "No product is displayed on the clothes page!");
        // click on item
        clothesPage.clickOnProduct();
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on continue shopping button from popup
        clothesProductDetailsPage.clickContinueShoppingButton();
        // assert test - verify if product is added to cart and cart number is updated
        Assert.assertEquals(headerPageSection.getCartCount(),"(1)", "Product was not added to cart or cart number was not updated!");
        // go to cart page
        headerPageSection.clickCartButton();
        // click to delete all products from cart
        cartPage.clickDeleteProductButtonCart();
        // verify if all items are deleted
        Assert.assertEquals(cartPage.getNoMoreItemsText(), MessagesAndAlerts.NO_MORE_ITEMS_MESSAGE, "The items were not deleted from the cart!");
        // verify the total number of products in cart
        Assert.assertEquals(cartPage.getTotalNumberOfProductsFromCart(),"0 articole");
        // verify that after all products are deleted the total cost of cart is 0 lei
        Assert.assertEquals(cartPage.getTotalCostCart(),"0,00 lei", "Total cost of cart is not 0 lei!");
    }

    @Test(description = "TestCase 08 - User can access the Checkout Page only after adding product to the cart")
    public void accessCheckoutPageTest() {
        log.info("Starting TC 08 - accessCheckoutPageTest()");
        HomePage homePage = new HomePage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        HeaderPageSection headerPageSection = new HeaderPageSection(driver, log);
        CartPage cartPage = new CartPage(driver, log);
        OrderPage orderPage = new OrderPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // select a random product from home page
        homePage.clickFirstPopularProduct();
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on proceed to checkout button from popup
        clothesProductDetailsPage.clickProceedToCheckoutButton();
        // assert test - verify if product is added to cart and cart number is updated
        Assert.assertEquals(headerPageSection.getCartCount(),"(1)", "Product was not added to cart or cart number was not updated!");
        // click on proceed to checkout button from cart page
        cartPage.clickProceedToCheckoutButton();
        // verify if checkout page is accessed after adding the product to the cart
        Assert.assertEquals(orderPage.getCurrentUrl(), orderPage.getExpectedUrl(), "The checkout page could not be accessed!");
    }

    @Test(description = "TestCase 09 - User is redirected to Checkout Payment Page Only after entering valid mandatory fields")
    public void goToCheckoutPaymentPageWenUserIsNotLoggedInTest() {
        log.info("Starting TC 09 - goToCheckoutPaymentPageWenUserIsNotLoggedInTest()");
        HomePage homePage = new HomePage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        CartPage cartPage = new CartPage(driver, log);
        OrderPage orderPage = new OrderPage(driver, log);
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // select a random product from home page
        homePage.clickFirstPopularProduct();
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on proceed to checkout button from popup
        clothesProductDetailsPage.clickProceedToCheckoutButton();
        // click on proceed to checkout button from cart page
        cartPage.clickProceedToCheckoutButton();
        // add user details when user is not logged in
        orderPage.completeUserDetailsWhenUserIsNotLoggedIn(FIRST_NAME_NEU, LAST_NAME_NEU, EMAIL_ADDRESS_NEU, ADDRESS_NEU, ZIP_CODE_NEU, CITY_NEU, PHONE_NEU);
    }

    @Test(description = "TestCase 10 - Can't place an order until you agree the term and conditions and chose payment method")
    public void completeOrderWhenUserIsNotLoggedIn() {
        log.info("Starting TC 10 - completeOrderWhenUserIsNotLoggedIn()");
        HomePage homePage = new HomePage(driver, log);
        ClothesProductDetailsPage clothesProductDetailsPage = new ClothesProductDetailsPage(driver, log);
        CartPage cartPage = new CartPage(driver, log);
        OrderPage orderPage = new OrderPage(driver, log);
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver, log);
        // access web shop - home page
        homePage.openHomePage();
        // select a random product from home page
        homePage.clickFirstPopularProduct();
        // click on add to cart button form product details page
        clothesProductDetailsPage.clickAddToCartButton();
        // click on proceed to checkout button from popup
        clothesProductDetailsPage.clickProceedToCheckoutButton();
        // click on proceed to checkout button from cart page
        cartPage.clickProceedToCheckoutButton();
        //Complete all users details and order details when user is not logged in
        orderPage.completeOrderDetailsWhenUserIsNotLoggedIn();
        // assert test - verify if order has been placed successfully and an e-mail is received
        Assert.assertTrue(orderConfirmationPage.isDisplayedConfirmOrderMessage(), "Confirm order message is not displayed, therefore the order maybe not been placed!");
    }
}