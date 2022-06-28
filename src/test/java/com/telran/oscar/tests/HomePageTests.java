package com.telran.oscar.tests;

import com.telran.oscar.pages.*;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;
    AccountPage accountPage;
    BasketPage basketPage;
    AllProductsPage allProductsPage;
    ClothingPage clothingPage;
    BooksPage booksPage;
    OffersPage offersPage;

    String email = PropertiesLoader.loadProperty("valid.email");
    String password = PropertiesLoader.loadProperty("valid.password");

    @BeforeMethod
    public void pageInit() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        basketPage = PageFactory.initElements(driver, BasketPage.class);
        allProductsPage = PageFactory.initElements(driver, AllProductsPage.class);
        clothingPage = PageFactory.initElements(driver, ClothingPage.class);
        booksPage = PageFactory.initElements(driver, BooksPage.class);
        offersPage = PageFactory.initElements(driver, OffersPage.class);
    }

    @Test
    public void logoTest() {
        Assert.assertTrue(homePage.isLogoCorrect());
    }

    @Test
    public void loginOrRegisterLinkActiveTest() {
        homePage.clickOnLoginAndRegisterLink();
        Assert.assertTrue(registerAndLoginPage.isRegisterFormDisplayed());
    }

    @Test
    public void logoutLinkActiveTest() {
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillLoginForm(email, password);
        accountPage.logout();
        Assert.assertTrue(homePage.isUserLoggedOut());
    }

    @Test
    public void accountLinkActiveTest() {
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillLoginForm(email, password);
        registerAndLoginPage.clickAccountLink();
        Assert.assertTrue(accountPage.isProfileDisplayed());
    }

    @Test
    public void searchFiledTest() {
        homePage.findProductInStore("Hacking Work");
        Assert.assertTrue(homePage.isProductFound("Hacking Work"));
    }

    @Test
    public void viewBasketTest() {
        homePage.clickViewBasketButton();
        Assert.assertTrue(basketPage.isBasketOpened());
    }

    @Test
    public void addProductToBasketTest(){
        int initialItemsNumber = 0;
        homePage.clickAddToBasketButton(0);
        homePage.clickAddToBasketButton(1);
        homePage.clickViewBasketButton();
        int finalItemsNumber = basketPage.numberOfUniqueItemsInBasket();
        Assert.assertEquals(finalItemsNumber, initialItemsNumber + 2);
    }

    @Test
    public void totalBasketAmountTest(){
        homePage.clickAddToBasketButton(0);
        homePage.clickAddToBasketButton(1);
        double totalBasketAmountHomePage = homePage.totalBasketAmount();
        homePage.clickViewBasketButton();
        double totalOrderAmountBasketPage = basketPage.totalOrderAmount();
        Assert.assertEquals(totalBasketAmountHomePage, totalOrderAmountBasketPage);
    }


    @Test
    public void menuElementsAllProductsTest() {
        homePage.clickAllProductsMenu();
        Assert.assertTrue(allProductsPage.isAllProductsPageDisplayed());
    }

    @Test
    public void menuElementsClothingTest() {
        homePage.clickClothingMenu();
        Assert.assertTrue(clothingPage.isClothingPageDisplayed());

    }

    @Test
    public void menuElementsBooksTest() {
        homePage.clickBooksMenu();
        Assert.assertTrue(booksPage.isBooksPageDisplayed());

    }

    @Test

    public void menuElementsOffersTest() {
        homePage.clickOffersMenu();
        Assert.assertTrue(offersPage.isOffersPageDisplayed());

    }

}

