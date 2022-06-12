package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegisterAndLoginPage extends PageBase {

    public RegisterAndLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "id_registration-email")
    WebElement emailField;

    @FindBy(id = "id_registration-password1")
    WebElement passwordField;

    @FindBy(id = "id_registration-password2")
    WebElement confirmPassword;

    @FindBy(name = "registration_submit")
    WebElement registrationSubmit;

    public RegisterAndLoginPage fillRegisterForm(String email, String password, String confirm) {
        type(emailField, email);
        type(passwordField, password);
        type(confirmPassword, confirm);
        click(registrationSubmit);
        return this;
    }

    @FindBy(id = "register_form")
    WebElement registerForm;

    public boolean isRegisterFormDisplayed() {
        return registerForm.isDisplayed();
    }

    @FindBy(id = "id_login-username")
    WebElement username;

    @FindBy(id = "id_login-password")
    WebElement loginPassword;

    @FindBy(name = "login_submit")
    WebElement loginSubmit;

    public RegisterAndLoginPage fillLoginForm(String email, String password) {
        type(username,email);
        type(loginPassword,password);
        click(loginSubmit);
        return this;
    }

    @FindBy (css = ".alertinner")
    WebElement registrationMessage;

    public boolean isUserRegistered() {
        return registrationMessage.getText().contains("Thanks for registering!");
    }

    @FindBy (xpath = "//a[@href = \"/en-gb/accounts/\"]")
    WebElement accountLink;
    public AccountPage clickAccountLink() {
        accountLink.click();
        return new AccountPage(driver);
    }
}

