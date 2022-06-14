package com.telran.oscar.tests;

import com.telran.oscar.pages.AccountPage;
import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.RegisterAndLoginPage;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class EditProfileTests extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;
    AccountPage accountPage;

    private String usersEmail = PropertiesLoader.loadProperty("valid.email");
    private String usersPassword = PropertiesLoader.loadProperty("valid.password");
    private String newPassword = "NewMaster12!";
    private String newEmail = "master_user2@gmail.com";

    @BeforeMethod
    public  void pageInit() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
    }

    @BeforeMethod
    public void preconditions() {
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillLoginForm(usersEmail, usersPassword);
    }

    @Test
    public void editFirstAndLastNamePositiveTest() {
        registerAndLoginPage.clickAccountLink();
        accountPage.clickOnEditButton();
        accountPage.setNewFirstAndLastName("Oliver", "Hustermann");
        Assert.assertEquals(accountPage.actualFirstAndLastName(), "Oliver" + " " + "Hustermann");
    }

    @Test
    public void editEmailPositiveTest() {
        registerAndLoginPage.clickAccountLink();
        accountPage.clickOnEditButton();
        accountPage.setNewEmail(newEmail);
        Assert.assertEquals(accountPage.actualEmail(),newEmail);

    }

    @Test
    public void editPasswordPositiveTest() {
        registerAndLoginPage.clickAccountLink();
        accountPage.clickOnChangePasswordBtn();
        accountPage.setNewPassword(usersPassword, newPassword);
        accountPage.logout();
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillLoginForm(usersEmail, newPassword);
        Assert.assertTrue(homePage.isUserLoggedIn());
    }

    @AfterMethod
    public void returnOriginalUsersData() {
        // вернуть

    }


}
