package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
    }

    //click method
    public void click(By elementBy) {
        driver.findElement(elementBy).click();
    }

    //check title
    public boolean isHere(String text) {
        boolean checkIsHere = (driver.getTitle().equals(text)) ? true : false;
        return checkIsHere;
    }

    //search
    public void inputSearch(String text) {
        click(By.xpath("//input[@placeholder='Поиск в каталоге']"));
        driver.findElement(By.xpath("//*[@id=\"search_widget\"]/form/input[2]")).sendKeys(" "+ text +" " + Keys.ENTER);
    }


    //switch currency 1-EUR; 2-UAH; 3-USD
    public void switchCurrency(int number) {
        click(By.xpath("//div[@class='currency-selector dropdown js-dropdown']"));
        driver.findElement(By.xpath("//div[@id='_desktop_currency_selector']//li[" + number + "]")).click();
    }

    //check page-price for USD
    protected boolean checkPagePriceForUSD(List<WebElement> list) {
        int count = 0;

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getText().contains("$")) count++;
        }

        boolean checkAllUsd = (count != list.size()) ? false : true;
        return checkAllUsd;
    }

    //check page-price for UAH
    protected boolean checkPagePriceForUAH(List<WebElement> list) {
        int count = 0;

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getText().contains("₴")) count++;
        }

        boolean checkAllUah = (count != list.size()) ? false : true;
        return checkAllUah;
    }

    //check page-price for EUR
    protected boolean checkPagePriceForEUR(List<WebElement> list) {
        int count = 0;

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getText().contains("€")) count++;
        }

        boolean checkAllEur = (count != list.size()) ? false : true;
        return checkAllEur;
    }

}
