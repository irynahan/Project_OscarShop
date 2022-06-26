package com.telran.oscar.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;

public class BasketPage extends PageBase{
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (tagName = "h1")
    WebElement basketHeader;


    public boolean isBasketOpened() {
        return basketHeader.getText().contains("Basket");
    }

    @FindBy(className = "basket-items")
    List<WebElement> itemsInBasketList;

    public int numberOfUniqueItemsInBasket() {
        int itemsNumber = itemsInBasketList.size();
        return itemsNumber;
    }


    @FindBy (xpath = "//*[@class='total align-right']")
    List<WebElement> basketTotalAmountsList;

    public double getPriceFromList(int element) {
        String priceText = basketTotalAmountsList.get(element).getText().substring(1);
        double price = Double.parseDouble(priceText);
        System.out.println(price);
        return price;
    }

    @FindBy (xpath = "//*[@class='align-right']")
    List<WebElement> basketDiskountsList;

    @FindBy (xpath = "//td[@class='basket-discount']/following-sibling::td")
    WebElement discount;

    public double getDiscount() {
        String priceText = discount.getText().substring(2);
        double price = Double.parseDouble(priceText);
        System.out.println(price);
        return price;
    }


    @FindBy (xpath = "//h3[@class='price_color']")
    WebElement totalOrderAmount;

    public double totalOrderAmount() {
        String amountText = totalOrderAmount.getText().substring(1);
        double amount = Double.parseDouble(amountText);
        return amount;
    }

    @FindBy (css = ".basket-items h3")
    List <WebElement> productInBasketNameList;

    public String getProductNameInBasket(int index) {
        return productInBasketNameList.get(index).getText();
    }

    public int getLastIndex() {
        return productInBasketNameList.size() - 1;
    }

    @FindBy (id = "id_form-0-quantity")
    WebElement productQuantity;

    public int getProductUnits() {
        String quantityText = productQuantity.getAttribute("value");
        int quantity = Integer.parseInt(quantityText);
        return quantity;
    }

    public void changeQuantityOfProducts(String quantity, int product){
        type(productQuantity, quantity);
        //productQuantity.sendKeys(Keys.ENTER);
        click(updateQuantityBnt.get(0));
    }

    @FindBy (css = ".input-group-btn button")
    List <WebElement> updateQuantityBnt;

    @FindBy(xpath = "//div[@class='col-sm-1']/p")
    WebElement productPrice;

    public double getProductPrice() {
        String priceText = productPrice.getText().substring(1);
        double price = Double.parseDouble(priceText);
        return price;
    }

    @FindBy(xpath = "//div[@class='col-sm-2']/p")
    WebElement totalProductPrice;

    public double getTotalItemPrice() {
        String priceText = totalProductPrice.getText().substring(1);
        double price = Double.parseDouble(priceText);
        return price;
    }

    @FindBy(xpath = "//div[@class='col-sm-2']/p")
    List<WebElement>  totalProductPriceList;

    public double calculateTotalBeforeDiscountPrice() {
        BigDecimal sum = new BigDecimal(0);
        for (WebElement element: totalProductPriceList){
            sum = sum.add(new BigDecimal(element.getText().substring(1)));
        }
        System.out.println("sum:" + sum);
        return sum.doubleValue();
    }

    @FindBy (css = ".btn-primary")
    WebElement checkoutBtn;
    public void clickCheckoutBtn() {
        click(checkoutBtn);
    }

    @FindBy (name = "username")
    WebElement userEmail;
    @FindBy (name = "password")
    WebElement userPassword;

    public void typeUsersData(String email, String password){
        type(userEmail, email);
        type(userPassword, password);
    }

    @FindBy (id = "id_options_1")
    WebElement newUserPlusRegistration;

    public void clickNewUserWithRegistration() {
        click(newUserPlusRegistration);
    }

    @FindBy ( css = "button")
    WebElement continueBtnWhoAreYou;
    public void clickContinueBtn(){
        click(continueBtnWhoAreYou);
    }


    @FindBy (id = "view_preview")
    WebElement continueBtnPayment;

    public void clickContinueBtnPayment() {
        click(continueBtnPayment);
    }

    @FindBy (id = "place-order")
    WebElement placeOrderBtn;

    public void placeOrder(){
        click(placeOrderBtn);
    }

    @FindBy (xpath = "//a[text() = 'View order status']")
    WebElement orderStatus;

    public void viewOrderStatus() {
        click(orderStatus);
    }

    @FindBy (tagName = "strong")
    WebElement orderNumber;

    public String getOrderNumber(){
        return orderNumber.getText();
    }

    @FindBy (xpath = "//div[@id='content_inner']/p[1]")
    WebElement status;
    public String getOrderStatus(){
        return status.getText();
    }

    @FindBy (xpath = "//a[text()='Continue shopping']")
    WebElement continueShopping;
    public HomePage clickContinueShopping(){
        click(continueShopping);
        return new HomePage(driver);
    }

    @FindBy (xpath = "//a[contains(.,'Edit order contents')]")
    WebElement editOrderLink;

    @FindBy (css = ".shipping-payment >div:nth-child(1)>div:nth-child(2)>a")
    WebElement changeAddressLink;

    @FindBy (css = ".shipping-payment >div:nth-child(1)>div:nth-child(3)>a")
    WebElement changeShipMethodLink;

    @FindBy (css = ".shipping-payment >div:nth-child(2)>div:nth-child(2)>a")
    WebElement changePaymentMethodLink;


}
