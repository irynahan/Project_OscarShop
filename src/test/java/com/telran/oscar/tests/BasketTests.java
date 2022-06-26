package com.telran.oscar.tests;

import com.telran.oscar.pages.*;
import com.telran.oscar.utils.DataProviders;
import com.telran.oscar.utils.PropertiesLoader;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Random;


public class BasketTests extends TestBase {

    HomePage homePage;
    RegisterAndLoginPage registerAndLoginPage;
    AccountPage accountPage;
    BasketPage basketPage;
    AllProductsPage allProductsPage;
    ShippingAddressPage shippingAddressPage;


    String email = PropertiesLoader.loadProperty("valid.email");
    String password = PropertiesLoader.loadProperty("valid.password");
    String quantityProd = PropertiesLoader.loadProperty("page");
    private int product = new Random().nextInt(20);
    private int product2 = new Random().nextInt(20);
    private String notRegisterEmail = "omiK#"+ new Random().nextInt(200) + "@google.com";
    private String notRegisterPass = "Nft5Ku!a" + new Random().nextInt(200);


    @BeforeMethod
    public void initPage() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerAndLoginPage = PageFactory.initElements(driver, RegisterAndLoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        basketPage = PageFactory.initElements(driver, BasketPage.class);
        allProductsPage = PageFactory.initElements(driver, AllProductsPage.class);
        shippingAddressPage = PageFactory.initElements(driver, ShippingAddressPage.class);
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
        Assert.assertEquals(basketPage.getTotalItemPrice(),  quantity * price);
    }

    @Test
    public void totalPriceChangeQuantityOfProductsInBasketTest(){
        allProductsPage.viewBasketBtnInMessage();
        basketPage.changeQuantityOfProducts(quantityProd, product);
        int quantity = basketPage.getProductUnits();
        double price = basketPage.getProductPrice();
        Assert.assertEquals(basketPage.getTotalItemPrice(),  quantity * price);
    }

    @Test
    public void basketTotalPriceBeforeDiscountTest(){
        allProductsPage.viewBasketBtnInMessage();
        Assert.assertEquals(basketPage.getPriceFromList(0), basketPage.calculateTotalBeforeDiscountPrice());
    }


    @Test
    public void basketTotalPriceMinusDiscountTest(){
        allProductsPage.addProductToBasket(product);
        allProductsPage.addProductToBasket(product2);
        allProductsPage.viewBasketBtnInMessage();
        Assert.assertEquals(basketPage.getPriceFromList(0), basketPage.calculateTotalBeforeDiscountPrice() - basketPage.getDiscount());
    }

    @Test
    public void totalOrderAmountTest(){
        allProductsPage.addProductToBasket(product2);
        allProductsPage.viewBasketBtnInMessage();
        Assert.assertEquals( basketPage.getPriceFromList(2), basketPage.getPriceFromList(0) + basketPage.getPriceFromList(1));
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "shipmentAddress")

    public void checkoutGuestTest(String titleName, String nameFirst, String nameLast, String address1l,String address2l, String address3l, String cityT, String stateT, String postCodeT, String countryT, String phoneT, String instruct) {
        allProductsPage.viewBasketBtnInMessage();
        basketPage.clickCheckoutBtn();
        basketPage.typeUsersData(notRegisterEmail, notRegisterPass);
        basketPage.clickContinueBtn();
        shippingAddressPage.fillInAddressForm(titleName, nameFirst, nameLast, address1l, address2l, address3l, cityT, stateT, postCodeT, countryT, phoneT, instruct);
        basketPage.clickContinueBtnPayment();
        basketPage.placeOrder();
        basketPage.viewOrderStatus();
        Assert.assertEquals(basketPage.getOrderStatus(), "Pending");
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "shipmentAddress")
    public void checkoutRegisteredUserTest(String titleName, String nameFirst, String nameLast, String address1l,String address2l, String address3l, String cityT, String stateT, String postCodeT, String countryT, String phoneT, String instruct) {
        homePage.clickOnLoginAndRegisterLink();
        registerAndLoginPage.fillLoginForm(email, password);
        homePage.clickViewBasketButton();
        basketPage.clickCheckoutBtn();
        shippingAddressPage.fillInAddressForm(titleName, nameFirst, nameLast, address1l, address2l, address3l, cityT, stateT, postCodeT, countryT, phoneT, instruct);
        basketPage.clickContinueBtnPayment();
        basketPage.placeOrder();
        String orderNumber = basketPage.getOrderNumber();
        basketPage.clickContinueShopping();
        homePage.clickAccountLink();
        accountPage.viewOrdersHistory();
        Assert.assertEquals(accountPage.getOrderNumber(), orderNumber);
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "shipmentAddress")
    public void checkoutWithCreateAccountTest(String titleName, String nameFirst, String nameLast, String address1l,String address2l, String address3l, String cityT, String stateT, String postCodeT, String countryT, String phoneT, String instruct) {
        allProductsPage.viewBasketBtnInMessage();
        basketPage.clickCheckoutBtn();
        basketPage.clickNewUserWithRegistration();
        basketPage.typeUsersData(notRegisterEmail, notRegisterPass);
        basketPage.clickContinueBtn();
        registerAndLoginPage.fillRegisterPass(notRegisterPass, notRegisterPass);
        shippingAddressPage.fillInAddressForm(titleName, nameFirst, nameLast, address1l, address2l, address3l, cityT, stateT, postCodeT, countryT, phoneT, instruct);
        basketPage.clickContinueBtnPayment();
        basketPage.placeOrder();
        String orderNumber = basketPage.getOrderNumber();
        basketPage.clickContinueShopping();
        homePage.clickAccountLink();
        accountPage.viewOrdersHistory();
        Assert.assertEquals(accountPage.getOrderNumber(), orderNumber);
    }

}
