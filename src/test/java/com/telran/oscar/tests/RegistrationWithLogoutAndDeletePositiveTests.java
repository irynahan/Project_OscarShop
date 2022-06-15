package com.telran.oscar.tests;

import com.telran.oscar.pages.AccountPage;
import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.RegisterAndLoginPage;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationWithLogoutAndDeletePositiveTests extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;
    AccountPage accountPage;

    private String email = "client" + new Random().nextInt(200) + "_user" + new Random().nextInt(600)+"@gmail.com";
    private String password = "NewClient"+ new Random().nextInt(400);


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
    public void registerAndDeleteUserPositiveTest() {
        System.out.println(email + "--" + password);
        registerAndLoginPage.fillRegisterForm(email, password, password);
        registerAndLoginPage.clickAccountLink();
        accountPage.deleteProfile(password);
        Assert.assertTrue(homePage.isUserDeleted());
    }

    @Test
    public void registerAndLogoutUserPositiveTest() {
        System.out.println(email + "--" + password);
        registerAndLoginPage.fillRegisterForm(email, password, password);
        accountPage.logout();
        Assert.assertTrue(homePage.isUserLoggedOut());
    }

}
