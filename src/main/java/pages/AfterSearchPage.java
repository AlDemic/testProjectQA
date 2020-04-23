package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AfterSearchPage extends BasePage {


    public AfterSearchPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> priceList = driver.findElements(By.className("price"));

    // проверка страницы
    protected boolean isExist() {
        boolean isExist = (driver.findElement(By.xpath("//h2[@class='h2']")).getText().contains("РЕЗУЛЬТАТЫ ПОИСКА")) ? true : false;
        return isExist;
    }

    // проверка страницы
    public boolean isExistStringGoods() {
        boolean isExistStringGoods = (driver.findElement(By.xpath("//*[@id=\"js-product-list-top\"]/div[1]")).getText().contains("Товаров")) ? true : false;
        return isExistStringGoods;
    }
    // проверка найденных товаров на USD
    public boolean isFoundedGoodsHaveUsd() {
        List<WebElement> priceList = driver.findElements(By.className("price"));
        boolean check = (checkPagePriceForUSD(priceList));
        return check;
    }

    //получаем из строки цифру найденных товаров
    public int amountOfFoundedGoods() {
        boolean checkStringResultOfSearch = isExist();
        boolean checkStringOfFoundedGooods = isExistStringGoods();

        if (checkStringResultOfSearch == true && checkStringOfFoundedGooods == true)
            return removeStringAndGetCount(driver.findElement(By.xpath("//*[@id=\"js-product-list-top\"]/div[1]")).getText());
        else
            return 0;
    }

    //метод для получения фактического числа найденных товаров
    public int removeStringAndGetCount(String string) {
        String firstRemove = string.replace("Товаров: ", "");
        String secondRemove = firstRemove.replace(".", "");
        try {
            int numberOfGoods = Integer.parseInt(secondRemove);

            if(numberOfGoods >= 0)
                return numberOfGoods;
        }
        catch (NumberFormatException numberFormatException) {
            numberFormatException.getMessage();
        }
        return 0;
    }

    // метод проверки сортировки
    public boolean checkPagePriceSortedMaxMin(List<WebElement> list) {
        boolean correctSorted = true;
        float prevNumber = Float.MAX_VALUE;
        List<String> changeForFloat = new ArrayList<String>();
        List <Float> listOfPrices = new ArrayList<Float>();

        //заменяем "," на "."
        for(WebElement element : list)
            changeForFloat.add(element.getText().replace(",", "."));

        //убираем из каждого элемента "$", после добавляем в список чисел
        for(String floatString : changeForFloat)
            listOfPrices.add(Float.parseFloat(floatString.replace("$", "")));

        //проверяем каждое число с предыдущим
        for(int x = 0; x < listOfPrices.size(); x++){
            if(listOfPrices.get(x) <= prevNumber) {
                prevNumber = listOfPrices.get(x);
            } else {
                correctSorted = false;
                break;
            }
        }
        return correctSorted;
    }

    // смотрим товары со скидкой
    public void checkDiscountPre() {
        List<WebElement> searchPagePriceDiscount = driver.findElements(By.className("product-price-and-shipping"));
        for(WebElement element : searchPagePriceDiscount ) {
            if(element.getText().contains("%"))
                System.out.printf("Верно ли посчитана скидка?: %b%n", checkDiscount(element.getText()));
        }
    }
    // проверка правильности подсчета скидки
    public boolean checkDiscount(String text) {

        //убираем в строке %, -, $, \n, " "
        String text1 = text.replace(",", ".");
        String removeMin = text1.replace("-", "");
        String removeUsd = removeMin.replace("$", "");
        String preString = removeUsd.replace("\n", "");
        String finalString = preString.replace("%", ".0 ");

        //делаем массив строк
        List<String> massiveOfStrings = Arrays.asList(finalString.split(" "));

        //преобразуем строки в числа и считаем
        float withoutDiscount = Float.parseFloat(massiveOfStrings.get(0));
        float discount = Float.parseFloat(massiveOfStrings.get(1));
        float currentPrice = Float.parseFloat(massiveOfStrings.get(2));
        float checkDifference =  (withoutDiscount - (withoutDiscount * discount / 100));
        float result = withoutDiscount - currentPrice;

        boolean isDiscountCorrect = (!(result > checkDifference)) ? true : false;

        return isDiscountCorrect;
    }

}