package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShippingAddressPage extends PageBase {

    public ShippingAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "id_title")
    WebElement title;

    public void selectTitle(String titleName){
        Select select = new Select(title);
        select.selectByVisibleText(titleName);
    }

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

    public void selectCountry(String countryName){
        Select select = new Select(country);
        select.selectByVisibleText(countryName);
    }

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


    public void fillInAddressForm(String titleName, String nameFirst, String nameLast, String address1l,String address2l, String address3l, String cityT, String stateT, String postCodeT, String countryT, String phoneT, String instruct)  {
        selectTitle(titleName);
        type(firstName, nameFirst);
        type(lastName, nameLast);
        type(lineAddress1, address1l);
        type(lineAddress2, address2l);
        type(lineAddress3, address3l);
        type(city, cityT);
        type(state, stateT);
        type(postcode, postCodeT);
        selectCountry(countryT);
        type(phoneNumber, phoneT);
        type(instructions, instruct);
        clickContinueBtn();
    }
}
