package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends PageBase{
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (css = "#delete_profile")
    WebElement deleteProfileBtn;

    @FindBy (css = "#id_password")
    WebElement passwordConfirm;

    @FindBy (xpath = "//button[@class = 'btn btn-lg btn-danger']")
    WebElement deleteSubmitBnt;

    public void deleteProfile(String password){
        deleteProfileBtn.click();
        type(passwordConfirm, password);
        deleteSubmitBnt.click();
    }

    @FindBy (xpath = "//a[@href = '/en-gb/accounts/profile/edit/']")
    WebElement editProfileBtn;

    public AccountPage clickOnEditButton() {
        editProfileBtn.click();
        return this;
    }

    @FindBy (name = "first_name")
    WebElement firstNameField;

    @FindBy (name = "last_name")
    WebElement lastNameField;


    @FindBy (css = ".btn-primary")
    WebElement saveEditsBtn;

    public AccountPage setNewFirstAndLastName(String firstName, String lastName) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        click(saveEditsBtn);
        return this;
    }

    @FindBy (name = "email")
    WebElement emailField;

    public AccountPage setNewEmail(String email) {
        type(emailField, email);
        click(saveEditsBtn);
        return this;
    }

    @FindBy (xpath = "//table//tr[2]/td")
    WebElement clientsEmail;
    public String actualEmail() {
        return clientsEmail.getText();
    }

    @FindBy (xpath = "//table//tr[1]/td")
    WebElement clientsName;

    public String actualFirstAndLastName() {
        return clientsName.getText();
    }

    @FindBy (xpath = "//a[@href = '/en-gb/accounts/change-password/']")
    WebElement changePasswordBtn;

    public AccountPage clickOnChangePasswordBtn() {
        click(changePasswordBtn);
        return this;
    }

    @FindBy (name = "old_password")
    WebElement oldPasswordField;

    @FindBy (name = "new_password1")
    WebElement newPasswordField;

    @FindBy (name = "new_password2")
    WebElement passwordConfirmField;


    public AccountPage setNewPassword(String oldPassword, String newPassword) {
        type(oldPasswordField, oldPassword);
        type(newPasswordField, newPassword);
        type(passwordConfirmField, newPassword);
        click(saveEditsBtn);
        return this;
    }

    @FindBy (id = "logout_link")
    WebElement logoutLink;

    public HomePage logout() {
        click(logoutLink);
        return new HomePage(driver);
    }

    @FindBy (tagName = "h1")
    WebElement profileHeader;

    public boolean isProfileDisplayed() {
        return profileHeader.isDisplayed();
    }

    @FindBy (xpath = "//a[@href='/en-gb/accounts/orders/']")
    WebElement orderHistory;
    public void viewOrdersHistory() {
        click(orderHistory);
    }

    @FindBy (xpath = "//table//tr[2]//a")
    WebElement orderNumber;
    public String getOrderNumber(){
        return orderNumber.getText();
    }
}
