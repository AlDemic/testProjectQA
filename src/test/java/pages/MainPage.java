package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // главная
    String SITE_URL = "http://prestashop-automation.qatestlab.com.ua/ru/";

    public void goTo(){
        System.out.println("Переходим на главную");
        driver.get(SITE_URL);
    }

     // проверка страницы
    public boolean isExist() {
        boolean isExist = (driver.findElement(By.xpath("//h1[contains(@class,'h1 products-section-title text-uppercase')]")).getText().contains("ПОПУЛЯРНЫЕ ТОВАРЫ")) ? true : false;
        return isExist;
    }

    // проверка отображения валюты категории "Топ"
    public boolean checkTopGoodsCurrency() {
         String UAH = "₴";
         String EUR = "€";
         String USD = "$";
         List<WebElement> priceList = driver.findElements(By.className("price"));
        if (driver.findElement(By.xpath("//div[@id='_desktop_currency_selector']")).getText().contains(UAH)) {
            return checkPagePriceForUAH(priceList);
        } else if (driver.findElement(By.xpath("//div[@id='_desktop_currency_selector']")).getText().contains(EUR)) {
            return checkPagePriceForEUR(priceList);
        } else if (driver.findElement(By.xpath("//div[@id='_desktop_currency_selector']")).getText().contains(USD)) {
            return checkPagePriceForUSD(priceList);
        } else {
            return false;
        }
    }


}
