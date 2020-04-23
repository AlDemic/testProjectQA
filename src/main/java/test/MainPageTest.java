package test;

import org.junit.Test;
import org.openqa.selenium.By;


public class MainPageTest extends WebDriverSettings {

    @Test
    public void checkMain() {
        main.goTo();
        System.out.printf("Является ли эта страница главной?: %b%n", main.isHere("prestashop-automation"));
        System.out.printf("Есть ли категория Популярные товары?: %b%n", main.isExist());
        System.out.printf("Валюта популярных товаров соответствует установленной?: %b%n", main.checkTopGoodsCurrency());

        // выбираем USD и ищем делаем запрос "dress"
        System.out.println("Выбираем курс валют \"USD\"");
        main.switchCurrency(3);

        System.out.println("Вводим \"dress\"");
        main.inputSearch("dress");

        // работа со страницей вывода результатов поиска
        System.out.printf("Количество найденных товаров: %s%n", afterSearch.amountOfFoundedGoods());
        System.out.printf("Цена найденных товаров в USD?: %b%n", afterSearch.isFoundedGoodsHaveUsd());

        //выставляем сортировку от Max to Min по прайсу
        System.out.println("Нажимаем на \"Сортировать по: \"");
        afterSearch.click(By.xpath("//a[@class='select-title']"));

        System.out.println("Выбираем сортировку от Max до Min");
        afterSearch.click(By.xpath("//a[5]"));

        pause();

        //проверяем сортировку и делаем вывод значений в консоль
        System.out.printf("Отображение цен от Max к Min?: %b%n", afterSearch.checkPagePriceSortedMaxMin(afterSearch.priceList));

        //проверяем скидки
        System.out.println("Проверяем скидки: ");
        afterSearch.checkDiscountPre();
    }

    private void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }


}