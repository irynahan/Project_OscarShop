package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BooksPage extends PageBase{
    public BooksPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    WebElement booksHeader;

    public boolean isBooksPageDisplayed() {
        return booksHeader.getText().equals("Books");
    }

    @FindBy (xpath = "//a[@href = '/en-gb/']")
    WebElement logo;

    public HomePage returnToHomePage() {
        click(logo);
        return new HomePage(driver);
    }
}
