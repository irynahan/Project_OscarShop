package com.telran.oscar.tests;


import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.RegisterAndLoginPage;
import com.telran.oscar.utils.DataProviders;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegisterNegativeTests extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;

    private String password = PropertiesLoader.loadProperty("valid.password");
    private String email = PropertiesLoader.loadProperty("valid.email");

    private String notRegisteredEmail = PropertiesLoader.loadProperty("notRegistered.email");
    private String notRegisteredPassword = PropertiesLoader.loadProperty("notRegistered.password");

    @BeforeMethod
    public void pageInit() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
    }

    @BeforeMethod
    public void preconditions() {
        homePage.clickOnLoginAndRegisterLink();
    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "registrationWithInvalidEmail")
    public void newUserRegistrationWithInvalidEmailNegativeTest(String email) {
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillRegisterForm(email, notRegisteredPassword, notRegisteredPassword);
        Assert.assertTrue(registerAndLoginPage.isRegisterFormDisplayed());
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "registrationWithInvalidPassword")
    public void newUserRegistrationWithInvalidPasswordNegativeTest(String validEmail,String invalidPassword) {
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillRegisterForm(validEmail, invalidPassword, invalidPassword);
        Assert.assertTrue(registerAndLoginPage.isRegisterFormDisplayed());
    }

    @Test
    public void newUserRegistrationWithRegisteredUsersDataNegativeTest(){
        registerAndLoginPage.fillRegisterForm(email, password, password);
        Assert.assertTrue(registerAndLoginPage.isUserAlreadyExistsErrorPresent());
    }

    @Test
    public void newUserRegistrationWithInvalidPasswordConfirmation() {
        String passwordConfirm = notRegisteredPassword + new Random().nextInt(30);
        registerAndLoginPage.fillRegisterForm(notRegisteredEmail, notRegisteredPassword, passwordConfirm);
        Assert.assertTrue(registerAndLoginPage.isIncorrectPasswordCorfirmErrorPresent());

    }


}
