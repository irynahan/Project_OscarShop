package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllProductsPage extends PageBase{
    public AllProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (tagName = "h1")
    WebElement pageHeader;

    public boolean isAllProductsPageDisplayed() {
        return pageHeader.getText().equals("All products");
    }

    @FindBy (xpath = "//a[@href = '/en-gb/']")
    WebElement logo;

    public HomePage returnToHomePage() {
        click(logo);
        return new HomePage(driver);
    }
}
