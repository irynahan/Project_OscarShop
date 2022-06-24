package com.telran.oscar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
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

    @FindBy (xpath = "//div[@class = 'side_categories']//a[contains(text(), 'Clothing')]")
    WebElement clothingMenu;

    public ClothingPage clickClothingMenu() {
        click(clothingMenu);
        return new ClothingPage(driver);
    }


    @FindBy (xpath = " //div[@class = 'side_categories'] //a[contains(text(),'Books')]")
    WebElement booksMenu;

    public BooksPage clickBooksMenu(){
        click(booksMenu);
        return new BooksPage(driver);
    }

    @FindBy (xpath = "//ul[@class = 'nav nav-list']/li[2]/ul/li[1]/a")
    WebElement fictionMenu;

    public BooksPage clickFictionMenu(){
        click(fictionMenu);
        return new BooksPage(driver);
    }

    @FindBy (xpath = "//div[@class = 'side_categories'] //a[contains(text(),'Computers in Literature')]")
    WebElement computerMenu;

    public BooksPage clickComputersInLitMenu(){
        click(computerMenu);
        return new BooksPage(driver);
    }

    @FindBy (xpath = "//div[@class = 'side_categories'] //a[contains(text(),'Non-Fiction')]")
    WebElement nonFictionMenu;

    public BooksPage clickNonFictionMenu(){
        click(nonFictionMenu);
        return new BooksPage(driver);
    }

    @FindBy (xpath = "//div[@class = 'side_categories'] //a[contains(text(),'Essential programming')]")
    WebElement essentialMenu;

    public BooksPage clickEssentialProgMenu(){
        click(essentialMenu);
        return new BooksPage(driver);
    }

    @FindBy (xpath = "//div[@class = 'side_categories'] //a[contains(text(),'Hacking')]")
    WebElement hackingMenu;

    public BooksPage clickHackingMenu(){
        click(hackingMenu);
        return new BooksPage(driver);
    }

    @FindBy (tagName = "a")
    List<WebElement> allLinks;

    public AllProductsPage checkAllUrl() {

        String url = "";
        System.out.println("Total links on the web page: " + allLinks.size());

        Iterator<WebElement> iterator = allLinks.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
        }
        return this;
    }

    public AllProductsPage checkBrokenLinks() {
        for (int i = 0; i < allLinks.size(); i++) {
            WebElement el = allLinks.get(i);
            String url = el.getAttribute("href");
            verifyLinks(url);
        }
        return this;
    }

    private void verifyLinks(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setConnectTimeout(5000);
            connect.connect();
            if (connect.getResponseCode() >= 400) {
                System.out.println(linkUrl + " - " + connect.getResponseMessage() + " is a broken link");
            } else {
                System.out.println(linkUrl + " - " + connect.getResponseMessage());
            }
        } catch (Exception e) {
            System.out.println(linkUrl + " - " + e.getMessage() + " - is a broken link");
        }
    }

    @FindBy(tagName = "img")
    List<WebElement> images;

    public AllProductsPage checkBrokenImages() {
        System.out.println("We have " + images.size() + " " + "images");

        for (int index = 0; index < images.size(); index++) {
            WebElement image = this.images.get(index);
            String imageUrl = image.getAttribute("src");
            System.out.println("URL of image " + (index + 1) + " is: " + imageUrl);
            verifyLinks(imageUrl);

            try {
                boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (typeof arguments[0].naturalWidth != undefined && arguments[0].naturalWidth>0);", image);
                if (imageDisplayed) {
                    System.out.println("DISPLAY - OK");
                    System.out.println("***************************************");
                } else {
                    System.out.println("DISPLAY - BROKEN");
                    System.out.println("***************************************");
                }
            }catch (Exception e) {
                System.out.println("Error Occurred");
            }
        }
        return this;
    }

}
