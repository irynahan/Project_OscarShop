package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AllProductsPage extends PageBase{
    public AllProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (tagName = "h1")
    WebElement pageHeader;

    public boolean isAllProductsPageDisplayed() {
        return pageHeader.getText().equals("All products");
    }

    @FindBy (xpath = "//a[@href = '/en-gb/']")
    WebElement logo;

    public HomePage returnToHomePage() {
        click(logo);
        return new HomePage(driver);
    }

    @FindBy (xpath = "//button[contains(.,'Add to basket')]")
    List<WebElement> addToBasketBtnList;

    @FindBy (xpath = "//a[text()='next']")
    WebElement nextButton;
    public void clickOnNextButton() {
        click(nextButton);
    }

    public void viewNextProductPage(int pageNumber){
        for(int i = 1; i < pageNumber; i++){
            clickOnNextButton();
        }
    }

    @FindBy (css = ".current")
    WebElement currentPage;

    public int getCurrentPage() {
        String pageText = currentPage.getText().split(" ")[1];
        int page = Integer.parseInt(pageText);
        System.out.println("********    " + page);
        return page;
    }

    @FindBy (xpath = "//a[text()='previous']")
    WebElement previousButton;
    public void returnToPreviousPage() {
        click(previousButton);
    }

    @FindBy (css = ".image_container img")
    List <WebElement> productImageList;

    public void viewProductClickByImage( int product) {
        click(productImageList.get(product));
    }

    @FindBy (tagName = "h3")
    List <WebElement> productNamesList;

    public void viewProductClickByName(int product) {
        click(productNamesList.get(product));
    }

    @FindBy (id = "product_description")
    WebElement productDescription;

    public boolean areProductDetailsDisplayed() {
       return productDescription.isDisplayed();
    }

    @FindBy (xpath = "//button[contains(.,'Add to basket')]")
    List <WebElement> addToBasketBntList;

    public AllProductsPage addProductToBasket(int product) {
        click(addToBasketBntList.get(product));
        return this;
    }

    public String getProductName(int product) {
        return productNamesList.get(product).getText();
    }

    @FindBy (css = "#messages div:nth-child(1)>.alertinner")
    WebElement addBasketMessage;

    public boolean isProductAddedToBasket(String productName) {
        if (productName.contains("...")){
            productName = productName.substring(0, productName.length()-3);
        }
        return  addBasketMessage.getText().contains(productName);
    }

    @FindBy (xpath = "//div[@id='messages']//a[text()='View basket']")
    WebElement viewBasketBtnInMessage;

    public BasketPage viewBasketBtnInMessage() {
        click(viewBasketBtnInMessage);
        return new BasketPage(driver);
    }

    @FindBy (css = "#add_to_basket_form button")
    WebElement addToBasketProductDetailsBth;

    public AllProductsPage addProductToBasketProductDetails() {
        click(addToBasketProductDetailsBth);
        return this;
    }

    @FindBy (css = ".product_main h1")
    WebElement productDetailsName;

    public boolean isProductNameContainsName (String name) {
        if (name.contains("...")){
            name = name.substring(0, name.length()-3);
        }
        return productDetailsName.getText().contains(name);
    }

    @FindBy (css = ".product_price>.price_color")
    List <WebElement> priceList;

    public double getProductPriceFromList(int product) {
        String priceText = priceList.get(product).getText().substring(1);
        double price = Double.parseDouble(priceText);
        return price;
    }

    @FindBy (css = ".product_main>p:nth-child(2)")
    WebElement priceProductDetails;


    public double getProductPriceFromProductDetails() {
        String priceText = priceProductDetails.getText().substring(1);
        double price = Double.parseDouble(priceText);
        return price;
    }
}
