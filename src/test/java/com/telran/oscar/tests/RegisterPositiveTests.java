package com.telran.oscar.tests;

import com.telran.oscar.pages.AccountPage;
import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.RegisterAndLoginPage;
import com.telran.oscar.utils.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPositiveTests extends TestBase {

    private String lastCreatedUsersPassword=null;

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;
    AccountPage accountPage;

    @BeforeMethod
    public void pageInit() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
    }

    @BeforeMethod
    public void preconditions() {
        homePage.clickOnLoginAndRegisterLink();
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "newUserRegistrationValidData")
    public void newUserRegistrationPositiveTest(String email, String password) {
        registerAndLoginPage.fillRegisterForm(email, password, password);
        lastCreatedUsersPassword=password;
        Assert.assertTrue(registerAndLoginPage.isUserRegistered());
        //registerAndLoginPage.clickAccountLink();
        //accountPage.deleteProfile(password);
        //Assert.assertTrue(homePage.isUserDeleted());
    }

    @AfterMethod
    public void deleteCreatedUser() {
        registerAndLoginPage.clickAccountLink();
        accountPage.deleteProfile(lastCreatedUsersPassword);
        Assert.assertTrue(homePage.isUserDeleted());
    }
}
