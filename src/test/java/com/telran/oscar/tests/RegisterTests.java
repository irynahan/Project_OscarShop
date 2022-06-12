package com.telran.oscar.tests;


import com.telran.oscar.pages.HomePage;
import com.telran.oscar.pages.RegisterAndLoginPage;
import com.telran.oscar.utils.DataProviders;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTests extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;

    public static String password = PropertiesLoader.loadProperty("valid.password");

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
    public void newUserRegistrationWithInvalidEmailTest(String email) {
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillRegisterForm(email, password, password);
        Assert.assertTrue(registerAndLoginPage.isRegisterFormDisplayed());
    }

}
