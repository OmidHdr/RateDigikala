package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.TimerTask;

public class Task extends TimerTask {

    @Override
    public void run() {
        Manage manage = new Manage();
        System.setProperty("webdriver.chrome.driver", "/home/"+manage.findUsername()+"/Downloads/chromedriver-linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=/home/"+manage.findUsername()+"/.config/chromium");
        WebDriver driver = new ChromeDriver(options);

        String url = "https://www.digikala.com/digiclub/missions/";
        driver.get(url);

        try {
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, window.scrollY + window.innerHeight / 2)");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(".styles_btn--secondary__Warw0 > .relative")).click();
            System.out.println("Clicked successfully");
            Thread.sleep(2000);
            driver.quit();
        } catch (Exception e) {
            System.out.println("You already clicked on the button or failed to do it\n" +
                    "please check it out or contact with this Email 'omidnet42@yahoo.com'");
            driver.quit();
        }
    }

}
