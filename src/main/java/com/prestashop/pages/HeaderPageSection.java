package com.prestashop.pages;

import com.prestashop.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPageSection extends BasePage {

    @FindBy(css = "div#_desktop_user_info .hidden-sm-down")
    private WebElement sigInButton;

    @FindBy (css = "input[name='s']")
    private WebElement searchInput;

    @FindBy (css = "div#search_widget .material-icons.search")
    private WebElement searchIcon;

    @FindBy(css = "img[alt='TAU Prestashop']")
    private WebElement logo;

    @FindBy(css=".cart-products-count")
    private WebElement cartCount;

    @FindBy(css = "ul#top-menu > li:nth-of-type(1) > .dropdown-item")
    private WebElement linkToClothes;

    @FindBy(css = ".material-icons.shopping-cart")
    private WebElement cartButton;

    @FindBy(css = ".hidden-sm-down.logout")
    private WebElement signOutButton;

    @FindBy(css = "div#contact-link > a")
    private WebElement linkToContactUs;

    public HeaderPageSection(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Click to log in into account")
    public void clickSignInButton(){
        log.info("Click to log in into account");
        click(this.sigInButton, "sign in button");
    }

    /** Execute search functionality */
    @Step("Search for product")
    public void searchProduct(String productName) {
        log.info("Search for product " + productName);
        sendKeys(productName, this.searchInput,10);
        click(this.searchIcon, "search icon");
    }

    @Step("Verify if search field is displayed")
    public boolean isDisplayedSearchFiled(){
        log.info("Verify if search field is displayed");
        return checkIfElementIsDisplayed(this.searchInput);
    }

    @Step("Verify if search box is populated")
    public boolean isSearchBoxPopulated(){
        log.info("Verify if search box is populated");
        return verifyIfFiledIsPopulated(this.searchInput);
    }

    @Step("Click on image logo to redirect to the main page")
    public void clickLogo(){
        log.info("Click on image logo to redirect to the main page");
        click(this.logo, "logo");
    }

    @Step("Get text from cart count to verify if product was added")
    public String getCartCount(){
        log.info("Get text from cart count to verify if product was added");
        waitForVisibilityElement(this.cartCount);
        return getTextWebElement(this.cartCount);
    }

    @Step("Click on link to redirect to the clothes page")
    public void clickLinkClothesPage(){
        log.info("Click on link to redirect to the clothes page");
        click(this.linkToClothes, "linkToClothes");
    }

    @Step("Click on cart button to redirect to the cart page")
    public void clickCartButton(){
        log.info("Click on cart button to redirect to the cart page");
        click(this.cartButton, "cartButton");
    }

    @Step("Verify if sign out button is visible, if is visible most likely successfully authenticated")
    public boolean isVisibleSignOutButton(){
        log.info("Verify if sign out button is visible, if is visible most likely successfully authenticated");
        return checkIfElementIsDisplayed(this.signOutButton);
    }

    @Step("Click on link to redirect to the contact us page")
    public void clickLinkToContactUsPage(){
        log.info("Click on link to redirect to the contact us page");
        linkToContactUs.click();
    }
}