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
    private String usersPassword = PropertiesLoader.loadProperty("Master12!")


    @BeforeMethod
    public void pageInit() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
    }

    @BeforeMethod
    public void ensurePreconditions() {
        homePage.clickOnLoginAndRegisterLink();
    }

    // .alertinner

    @Test
    public void loginRegisteredUserPositiveTest() {
        registerAndLoginPage.fillLoginForm(usersEmail, usersPassword);
        Assert.assertTrue();
    }


}
