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
import java.util.Random;

public class EditProfileTests extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;
    AccountPage accountPage;

    private String usersEmail = "master" + new Random().nextInt(300) + "_user" + new Random().nextInt(700)+"@gmail.com";
    private String usersPassword = "NewMaster"+ new Random().nextInt(500);
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
        registerAndLoginPage.fillRegisterForm(usersEmail, usersPassword, usersPassword);
    }

    @Test
    public void editFirstAndLastNamePositiveTest() {
        System.out.println(usersEmail + "--" + usersPassword);
        registerAndLoginPage.clickAccountLink();
        accountPage.clickOnEditButton();
        accountPage.setNewFirstAndLastName("Milla", "Huster");
        Assert.assertEquals(accountPage.actualFirstAndLastName(), "Milla" + " " + "Huster");

    }

    @Test
    public void editEmailPositiveTest() {
        System.out.println(usersEmail + "--" + usersPassword);
        registerAndLoginPage.clickAccountLink();
        accountPage.clickOnEditButton();
        accountPage.setNewEmail(newEmail);
        Assert.assertEquals(accountPage.actualEmail(),newEmail);


    }

    @Test
    public void editPasswordPositiveTest() {
        System.out.println(usersEmail + "--" + usersPassword);
        registerAndLoginPage.clickAccountLink();
        accountPage.clickOnChangePasswordBtn();
        accountPage.setNewPassword(usersPassword, newPassword);
        accountPage.logout();
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillLoginForm(usersEmail, newPassword);
        Assert.assertTrue(homePage.isUserLoggedIn());

    }

}
