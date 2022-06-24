package com.telran.oscar.pages;

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
    @FindBy (xpath = "//td[contains(., 'before discounts')]/following-sibling::td")
    WebElement priceBeforeDiscount;

    @FindBy (xpath = "//th[contains(., 'Basket total')]/following-sibling::th")
    WebElement totalPriceItems;

    public double getPriceBeforeDiscount() {
        String priceText = null;
        if (priceBeforeDiscount.isDisplayed()){
            priceText = priceBeforeDiscount.getText().substring(1);
        } else {
            priceText = totalPriceItems.getText().substring(1);
        }
        double price = Double.parseDouble(priceText);
        System.out.println(price);
        return price;
    }

    @FindBy (xpath = "//td[@class='basket-discount']/following-sibling::td")
    WebElement discount;

    @FindBy (xpath = "//th[contains(.,'after')]/following-sibling::th")
    WebElement basketTotalPrice;

    @FindBy (xpath = "//table[@class='table table-condensed']")
    public WebElement tableTotals;

    public double calculateTotalPriceAfterDiscount() {
        return 0.00;
    }

    @FindBy (xpath = "//div[@id = 'basket_totals']//tr[7]/th[2]")
    WebElement shippingPrice;




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

    //@FindBy (css = "[type = 'number']")
    @FindBy (id = "id_form-0-quantity")
    WebElement productQuantityList;

    public int getProductUnits() {
        String quantityText = productQuantityList.getAttribute("value");
        int quantity = Integer.parseInt(quantityText);
        return quantity;
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






    @FindBy (id = "view_preview")
    WebElement continueBtnPayment;

    @FindBy (id = "place-order")
    WebElement placeOrderBtn;

    @FindBy (xpath = "//a[contains(.,'Edit order contents')]")
    WebElement editOrderLink;

    @FindBy (css = ".shipping-payment >div:nth-child(1)>div:nth-child(2)>a")
    WebElement changeAddressLink;

    @FindBy (css = ".shipping-payment >div:nth-child(1)>div:nth-child(3)>a")
    WebElement changeShipMethodLink;

    @FindBy (css = ".shipping-payment >div:nth-child(2)>div:nth-child(2)>a")
    WebElement changePaymentMethodLink;


}
