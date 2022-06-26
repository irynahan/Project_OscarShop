package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HomePage extends PageBase{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//a[text()='All products']")
    WebElement menuAllProducts;


    public AllProductsPage clickAllProductsMenu() {
        click(menuAllProducts);
        return new AllProductsPage(driver);
    }

    @FindBy (xpath = "//a[text()='Clothing']")
    WebElement menuClothing;

    public ClothingPage clickClothingMenu() {
        click(menuClothing);
        return new ClothingPage(driver);
    }

    @FindBy (xpath = "//a[text()='Books']")
    WebElement menuBooks;

    public BooksPage clickBooksMenu(){
        click(menuBooks);
        return new BooksPage(driver);
    }


    @FindBy (xpath = "//a[text()='Offers']")
    WebElement menuOffers;

    public OffersPage clickOffersMenu(){
        click(menuOffers);
        return new OffersPage(driver);
    }



    @FindBy(id = "login_link")
    WebElement loginLink;

    public RegisterAndLoginPage clickOnLoginAndRegisterLink() {
        click(loginLink);
        return new RegisterAndLoginPage(driver);
    }

    @FindBy (css = ".alertinner")
    WebElement alertMessage;

    public boolean isUserDeleted() {
        return alertMessage.getText().contains("Your profile has now been deleted.");
    }

    public boolean isUserLoggedIn() {
        return alertMessage.getText().contains("Welcome back");
    }

    public boolean isUserLoggedOut() {
        return loginLink.isDisplayed();
    }

    @FindBy (xpath = "//a[@href = '/en-gb/']")
    WebElement logoPart1;

    @FindBy (xpath = "//small")
    WebElement logoPart2;

    public boolean isLogoCorrect() {
        if (logoPart1.getText().contentEquals("Oscar") && logoPart2.getText().contentEquals("Sandbox")) {
            return true;
        }
        return false;
    }

    public HomePage returnToHomePage() {
        click(logoPart1);
        return new HomePage(driver);
    }

    @FindBy   (xpath = "//input[@class = 'form-control']")
    WebElement searchField;
    @FindBy (xpath = "//input[@value = 'Search']")
    WebElement searchBtn;

    public void findProductInStore(String searchProduct) {
        type(searchField, searchProduct);
        click(searchBtn);
    }

    @FindBy (css = "li h3")
    List <WebElement> productNamesList;

    public boolean isProductFound(String productName) {
        return  isElementInList(productName, productNamesList);
    }

    @FindBy (xpath = "//a[text()='View basket']")
    WebElement viewBusketBtn;

    public BasketPage clickViewBasketButton() {
        click(viewBusketBtn);
        return new BasketPage(driver);
    }

    @FindBy (xpath = "//button[contains(.,'Add to basket')]")
    List <WebElement> addToBasketBntList;

    public HomePage clickAddToBasketButton(int element) {
        click(addToBasketBntList.get(element));
        return this;
    }

    @FindBy (xpath = "//div[contains(@class, 'basket-mini')]")
    WebElement basketTotal;


    public double totalBasketAmount() {
        String totalAmountText = basketTotal.getText().split(" ")[2].split("\n")[0].substring(1);
        double totalAmount = Double.parseDouble(totalAmountText);
        return totalAmount;
    }

    @FindBy (xpath = "//a[@href = \"/en-gb/accounts/\"]")
    WebElement accountLink;
    public AccountPage clickAccountLink() {
        accountLink.click();
        return new AccountPage(driver);
    }

}