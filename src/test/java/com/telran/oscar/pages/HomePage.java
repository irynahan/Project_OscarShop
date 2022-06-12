package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".dropdown-submenu")
    WebElement submenuBook;

    public ProductPage selectCategory() {
        click(submenuBook);
        return new ProductPage(driver);
    }

    @FindBy(id = "login_link")
    WebElement loginLink;

    public RegisterAndLoginPage clickOnLoginAndRegisterLink() {
        click(loginLink);
        return new RegisterAndLoginPage(driver);
    }

    @FindBy (css = ".alertinner")
    WebElement deleteProfileMessage;

    public boolean isUserDeleted() {
        return deleteProfileMessage.getText().contains("Your profile has now been deleted.");
    }

}