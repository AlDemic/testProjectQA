package test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import pages.AfterSearchPage;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class OtherDriverSettings {
    public WebDriver driver;
    public MainPage main;
    public AfterSearchPage afterSearch;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/just-/Downloads/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        main = PageFactory.initElements(driver, MainPage.class);
        afterSearch = PageFactory.initElements(driver, AfterSearchPage.class);
    }

    @After
    public void finish() {
        driver.quit();
    }
}



