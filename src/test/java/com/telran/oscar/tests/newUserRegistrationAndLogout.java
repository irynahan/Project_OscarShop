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

public class newUserRegistrationAndLogout extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;
    AccountPage accountPage;

    private String email = PropertiesLoader.loadProperty("toBeDeleted.email");
    private String password = PropertiesLoader.loadProperty("toBeDeleted.password");


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

    @Test
    public void registerAndLogoutUserPositiveTest() {
        registerAndLoginPage.fillRegisterForm(email, password, password);
        accountPage.logout();
        Assert.assertTrue(homePage.isUserLoggedOut());
    }

    @AfterMethod
    public void deleteCreatedUser() {
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillLoginForm(email, password);
        registerAndLoginPage.clickAccountLink();
        accountPage.deleteProfile(password);
    }

}
