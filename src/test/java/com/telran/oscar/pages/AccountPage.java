package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends PageBase{
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (css = "#delete_profile")
    WebElement deleteAccountBtn;

    @FindBy (css = "#id_password")
    WebElement passwordConfirm;

    @FindBy (xpath = "//button[@class = 'btn btn-lg btn-danger']")
    WebElement deleteSubmitBnt;

    public void deleteProfile(String password){
        deleteAccountBtn.click();
        type(passwordConfirm, password);
        deleteSubmitBnt.click();
    }


}
