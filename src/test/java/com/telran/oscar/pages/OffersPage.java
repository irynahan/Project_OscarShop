package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OffersPage extends PageBase{
    public OffersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    WebElement offersHeader;

    public boolean isOffersPageDisplayed() {
        return offersHeader.getText().equals("Offers");
    }

    @FindBy (xpath = "//a[@href = '/en-gb/']")
    WebElement logo;

    public HomePage returnToHomePage() {
        click(logo);
        return new HomePage(driver);
    }
}
