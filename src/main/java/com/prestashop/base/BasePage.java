package com.prestashop.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.prestashop.constants.Url.HOME_PAGE_URL;

public class BasePage {
    protected WebDriver driver;
    protected Logger log;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        PageFactory.initElements(driver, this);
    }

    /** Open page with given URL */
    protected void openUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    protected void sendKeys(String textValue, WebElement webElement, int sleepTime) {
        for (int i = 0; i <= textValue.length() - 1; i++) {
            webElement.sendKeys(Character.toString(textValue.charAt(i)));
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void click(WebElement webElement, String elementName) {
        Assert.assertTrue(checkIfElementIsDisplayed(webElement), elementName + " was not found on the page");
        webElement.click();
    }

    protected boolean checkIfElementIsDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void openNewTab(){
        // Opens a new tab and switches to new tab
        String precedentWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB).navigate().to(HOME_PAGE_URL);
        driver.switchTo().window(precedentWindow);
        driver.close();
    }

    public void waitForVisibilityElement(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public boolean iterateThroughProduct(List<WebElement> webElement, String filter){
        for(WebElement elemProduct: webElement) {
            if(elemProduct.getAttribute("href").contains(filter))
                return true;
        }
        return false;
    }

    public void hoverOn(WebElement webElement){
        //Creating object of an Actions class
        Actions action = new Actions(driver);
        //Performing the mouse hover action on the target element.
        action.moveToElement(webElement).perform();
    }

    public boolean isOptionColorAvailable(WebElement webElement){
        if(webElement.isDisplayed());
        return true;
    }

    /** This method selects given option from dropdown */
    public void selectOptionFromDropdown(WebElement webElement, int i){
        Select dropdown = new Select(webElement);
        dropdown.selectByValue("" + i);
    }

    public String getTextWebElement(WebElement webElement){
        return webElement.getText();
    }

    /** This method returns selected option in dropdown as a string */
    public String getSelectedOptionFromDropdown(WebElement webElement){
        Select dropdown = new Select(webElement);
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        return selectedOption;
    }

    public String getErrorMessage(WebElement webElement){
        return webElement.getAttribute("validationMessage");
    }

    public boolean verifyIfFiledIsPopulated(WebElement webElement){
        return (webElement.getAttribute("value") != null);
    }

    public boolean verifyIfFieldIsEmpty(WebElement webElement) {
        return (webElement.getAttribute("value").isEmpty());
    }

    public boolean verifyIfFieldIsBlank(WebElement webElement) {
        return (webElement.getAttribute("value").isBlank());
    }

    public boolean isOptionSelected(WebElement webElement){
        return webElement.isSelected();
    }

    public boolean checkIfInfoAlreadyExists(WebElement webElement){
        if(checkIfElementIsDisplayed(webElement))
            return false;
        else
            return true;
    }
}