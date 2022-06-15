package com.telran.oscar.tests;

import com.telran.oscar.utils.MyListener;
import com.telran.oscar.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    //WebDriver driver;
    EventFiringWebDriver driver;

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static String baseURL = PropertiesLoader.loadProperty("url");

    @BeforeMethod
    public void setUp() {
        //driver = new ChromeDriver();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.register(new MyListener());
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }
}
