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

    public boolean isFictionDisplayed() {
        return booksHeader.getText().equals("Fiction");
    }

    public boolean isCILDisplayed() {
        return booksHeader.getText().equals("Computers in Literature");
    }

    public boolean isNonFictionDisplayed() {
        return booksHeader.getText().equals("Non-Fiction");
    }

    public boolean isEssentialProgDisplayed() {
        return booksHeader.getText().equals("Essential programming");
    }

    public boolean isHackingDisplayed() {
        return booksHeader.getText().equals("Hacking");
    }

}
