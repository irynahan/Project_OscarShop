package com.telran.oscar.tests;

import com.telran.oscar.pages.*;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BasketTests extends TestBase {

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
    private int product = new Random().nextInt(20);
    private int product2 = new Random().nextInt(20);



    @BeforeMethod
    public void initPage() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        basketPage = PageFactory.initElements(driver, BasketPage.class);
        allProductsPage = PageFactory.initElements(driver, AllProductsPage.class);
        clothingPage = PageFactory.initElements(driver, ClothingPage.class);
        booksPage = PageFactory.initElements(driver, BooksPage.class);
        offersPage = PageFactory.initElements(driver, OffersPage.class);
    }

    @BeforeMethod
    public void preconditions() {
        homePage.clickAllProductsMenu();
        allProductsPage.addProductToBasket(product);
        allProductsPage.addProductToBasket(product2);

    }

    @Test
    public void totalPriceSeveralUnitsOneProductTest(){
        allProductsPage.addProductToBasket(product);
        allProductsPage.addProductToBasket(product);
        allProductsPage.viewBasketBtnInMessage();
        int quantity = basketPage.getProductUnits();
        double price = basketPage.getProductPrice();
        Assert.assertEquals( quantity * price, basketPage.getTotalItemPrice());
    }

    @Test
    public void basketTotalBeforeDiscountTest(){
        allProductsPage.viewBasketBtnInMessage();
        Assert.assertEquals(basketPage.getPriceBeforeDiscount(), basketPage.calculateTotalBeforeDiscountPrice());
    }


    @Test
    public void basketTotalOrderAmountTest(){
        allProductsPage.addProductToBasket(product);
        allProductsPage.addProductToBasket(product2);
        allProductsPage.viewBasketBtnInMessage();

        List<WebElement> rows = basketPage.tableTotals.findElements(By.tagName("tr"));
        for(WebElement eachRow:rows){
            List<String> rowText=getColumns(eachRow);
            System.out.println(getColumns(eachRow).stream().collect(Collectors.joining(" | ")));
        }
        System.out.println(basketPage.tableTotals);
        // Assert.assertEquals(basketPage.getPriceBeforeDiscount(), basketPage.calculateTotalBeforeDiscountPrice());
    }

    private List<String> getColumns(WebElement webElement){
        List<String> returnValue = new ArrayList<>();
        for(WebElement eachElement: webElement.findElements(By.tagName("td"))){
            returnValue.add(eachElement.getText());
        }
        for(WebElement eachElement: webElement.findElements(By.tagName("th"))){
            returnValue.add(eachElement.getText());
        }
        return returnValue;
    }





}
