package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingAddressPage extends PageBase {

    public ShippingAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "id_title")
    WebElement title;

    @FindBy (name = "first_name")
    WebElement firstName;

    @FindBy (name = "last_name")
    WebElement lastName;

    @FindBy (name = "line1")
    WebElement lineAddress1;

    @FindBy (name = "line2")
    WebElement lineAddress2;

    @FindBy (name = "line3")
    WebElement lineAddress3;

    @FindBy (name = "line4")
    WebElement city;

    @FindBy (name = "state")
    WebElement state;

    @FindBy (name = "postcode")
    WebElement postcode;

    @FindBy (name = "country")
    WebElement country;

    @FindBy (name = "phone_number")
    WebElement phoneNumber;

    @FindBy (name = "notes")
    WebElement instructions;



    @FindBy(tagName = "button")
    WebElement continueBtn;

    public void clickContinueBtn() {
        click(continueBtn);
    }

    @FindBy (xpath = "//a[text()='return to basket']")
    WebElement returnToBasket;





}
