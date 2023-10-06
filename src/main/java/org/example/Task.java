package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.TimerTask;

public class Task extends TimerTask {


    @Override
    public void run() {
        System.setProperty("webdriver.chrome.driver", "/home/h0p3/Downloads/chromedriver-linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=/home/h0p3/.config/chromium");
        WebDriver driver = new ChromeDriver(options);

        String url = "https://www.digikala.com/digiclub/missions/";
        driver.get(url);

        try {
            Thread.sleep(3000);
            driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/div[3]/div[2]/div[2]/button")).click();
        } catch (Exception e) {
            System.out.println("You already clicked on the button or failed to do it\n" +
                    "please check it out or contact with this Email 'omidnet42@yahoo.com'");

        }

        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
