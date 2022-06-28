package com.telran.oscar.tests;

import com.telran.oscar.pages.*;
import com.telran.oscar.utils.DataProviders;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class ProductTests extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;
    BasketPage basketPage;
    AllProductsPage allProductsPage;
    ClothingPage clothingPage;
    BooksPage booksPage;

    String pageNumberText = PropertiesLoader.loadProperty("page");
    int pageNumber = Integer.parseInt(pageNumberText);
    private int product = new Random().nextInt(20);

    @BeforeMethod
    public void pageInit() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
        basketPage = PageFactory.initElements(driver, BasketPage.class);
        allProductsPage = PageFactory.initElements(driver, AllProductsPage.class);
        clothingPage = PageFactory.initElements(driver, ClothingPage.class);
        booksPage = PageFactory.initElements(driver, BooksPage.class);
    }

    @BeforeMethod
    public void preconditions() {
        homePage.clickAllProductsMenu();
    }


    @Test
    public void nextPaginationTest(){
        allProductsPage.viewNextProductPage(pageNumber);
        Assert.assertEquals( allProductsPage.getCurrentPage(),pageNumber);
    }

    @Test
    public void previousPaginationTest(){
        allProductsPage.viewNextProductPage(pageNumber);
        allProductsPage.returnToPreviousPage();
        Assert.assertEquals( allProductsPage.getCurrentPage(), pageNumber - 1);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "productToView")
    public void viewProductByImageTest(int viewProduct) {
        allProductsPage.viewProductClickByImage(viewProduct);
        Assert.assertTrue(allProductsPage.areProductDetailsDisplayed());
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "productToView")
    public void viewProductByNameTest(int viewProduct) {
        allProductsPage.viewProductClickByName(viewProduct);
        Assert.assertTrue(allProductsPage.areProductDetailsDisplayed());
    }


    @Test (dataProviderClass = DataProviders.class, dataProvider = "productToView")
    public void compareProductNameTest(int viewProduct) {
        String productName = allProductsPage.getProductName(viewProduct);
        allProductsPage.viewProductClickByName(viewProduct);
        Assert.assertTrue(allProductsPage.isProductNameContainsName(productName));
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "productToView")
    public void compareProductPriceTest(int viewProduct) {
        double productPrice = allProductsPage.getProductPriceFromList(viewProduct);
        allProductsPage.viewProductClickByImage(viewProduct);
        Assert.assertEquals(allProductsPage.getProductPriceFromProductDetails(), productPrice);
    }

    @Test
    public void addProductToBasketFromProductListTest(){
        allProductsPage.addProductToBasket(product);
        String productName = allProductsPage.getProductName(product);
        Assert.assertTrue(allProductsPage.isProductAddedToBasket(productName));
        allProductsPage.viewBasketBtnInMessage();
        Assert.assertTrue(basketPage.getProductNameInBasket(basketPage.getLastIndex()).contains(productName));

    }
    @Test
    public void addProductToBasketFromProductDetailsTest(){
        String productName = allProductsPage.getProductName(product);
        allProductsPage.viewProductClickByName(product);
        allProductsPage.addProductToBasketProductDetails();
        Assert.assertTrue(allProductsPage.isProductAddedToBasket(productName));
        allProductsPage.viewBasketBtnInMessage();
        Assert.assertTrue(basketPage.getProductNameInBasket(basketPage.getLastIndex()).contains(productName));

    }

    @Test
    public void sideMenuClothingTest() {
        allProductsPage.clickClothingMenu();
        Assert.assertTrue(clothingPage.isClothingPageDisplayed());
    }

    @Test
    public void sideMenuBooksTest() {
        allProductsPage.clickBooksMenu();
        Assert.assertTrue(booksPage.isBooksPageDisplayed());
    }

    @Test
    public void sideMenuFictionTest() {
        allProductsPage.clickFictionMenu();
        Assert.assertTrue(booksPage.isFictionDisplayed());
    }

    @Test
    public void sideMenuCompInLitTest() {
        allProductsPage.clickComputersInLitMenu();
        Assert.assertTrue(booksPage.isCILDisplayed());
    }

    @Test
    public void sideMenuNonFictionTest() {
        allProductsPage.clickNonFictionMenu();
        Assert.assertTrue(booksPage.isNonFictionDisplayed());
    }

    @Test
    public void sideMenuEssentialLitTest() {
        allProductsPage.clickEssentialProgMenu();
        Assert.assertTrue(booksPage.isEssentialProgDisplayed());
    }

    @Test
    public void sideMenuHackingTest() {
        allProductsPage.clickHackingMenu();
        Assert.assertTrue(booksPage.isHackingDisplayed());
    }

    @Test
    public void getBrokenLinksTest() {
        allProductsPage.checkBrokenLinks();
    }

    @Test
    public void getBrokenImagesTest() {
        allProductsPage.checkBrokenImages();
    }

}
