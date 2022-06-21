package com.telran.oscar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    //h3[@class='price_color']
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
}
