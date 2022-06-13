package com.telran.oscar.tests;

import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.RegisterAndLoginPage;
import com.telran.oscar.utils.DataProviders;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;

    private String usersEmail = PropertiesLoader.loadProperty("valid.email");
    private String usersPassword = PropertiesLoader.loadProperty("valid.password");

    private String notRegisteredEmail = PropertiesLoader.loadProperty("notRegistered.email");
    private String getNotRegisteredPassword = PropertiesLoader.loadProperty("notRegistered.password");

    private String deletedEmail = PropertiesLoader.loadProperty("toBeDeleted.email");
    private String deletedPassword = PropertiesLoader.loadProperty("toBeDeleted.password");


    @BeforeMethod
    public void pageInit() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
    }

    @BeforeMethod
    public void preconditions() {
        homePage.clickOnLoginAndRegisterLink();
    }


    @Test
    public void loginRegisteredUserPositiveTest() {
        registerAndLoginPage.fillLoginForm(usersEmail, usersPassword);
        Assert.assertTrue(homePage.isUserLoggedIn());
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "loginInvalidPasswordData")

    public void loginWithInvalidPasswordNegativeTest(String password) {
        registerAndLoginPage.fillLoginForm(usersEmail, password);
        Assert.assertTrue(registerAndLoginPage.isWarningAppeared());
    }

    @Test
    public void loginNotRegisteredUserNegativeTest() {
        registerAndLoginPage.fillLoginForm(notRegisteredEmail, getNotRegisteredPassword);
        Assert.assertTrue(registerAndLoginPage.isWarningAppeared());
    }

    @Test
    public void logInDeletedUserNegativeTest() {
        registerAndLoginPage.fillLoginForm(deletedEmail, deletedPassword);
        Assert.assertTrue(registerAndLoginPage.isWarningAppeared());
    }


    @Test
    public void loginEmptyPasswordNegativeTest() {
        registerAndLoginPage.fillLoginForm(usersEmail, "");
        Assert.assertTrue(registerAndLoginPage.isLoginFormDisplayed());
    }

    @Test
    public void loginEmptyFieldsNegativeTest() {
        registerAndLoginPage.fillLoginForm("", "");
        Assert.assertTrue(registerAndLoginPage.isLoginFormDisplayed());
    }

}
