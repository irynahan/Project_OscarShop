package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends PageBase{

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".page-header")
    WebElement titleBooks;

    public String isProductPage() {
        return titleBooks.getText();
    }
}
