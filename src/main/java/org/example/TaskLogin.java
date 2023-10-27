package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.security.Key;
import java.util.TimerTask;

public class TaskLogin extends TimerTask {

    @Override
    public void run() {
        String phoneIrancell = "09351234567";
        String phoneHamrahAval = "09121234567";
        String passwordIrancell = "12345678";
        String passwordHamrahAval = "87654321";
        click(phoneIrancell,passwordIrancell);
        click(phoneHamrahAval,passwordHamrahAval);
    }

    public void click(String phone , String password){
        Manage manage = new Manage();
        System.setProperty("webdriver.chrome.driver", "/home/"+manage.findUsername()+"/Downloads/chromedriver-linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=/home/"+manage.findUsername()+"/.config/chromium");
        WebDriver driver = new ChromeDriver(options);

        String url = "https://www.digikala.com/users/login/?backUrl=/";
        String urlMission = "https://www.digikala.com/digiclub/missions/";
        driver.get(url);
        try {
            Thread.sleep(2000);
            driver.findElement(By.name("username")).sendKeys(phone);
            Thread.sleep(1000);
            driver.findElement(By.name("username")).sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            driver.findElement(By.name("password")).sendKeys(password);
            Thread.sleep(1000);
            driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
            Thread.sleep(3000);
            driver.get(urlMission);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, window.scrollY + window.innerHeight / 2)");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(".styles_btn--secondary__Warw0 > .relative")).click();
            Thread.sleep(2000);
            System.out.println("Clicked successfully");
            Thread.sleep(2000);
            driver.quit();
        } catch (Exception e) {
            System.out.println("You already clicked on the button or failed to do it\n" +
                    "please check it out or contact with this Email 'omidnet42@yahoo.com'");
        } finally {
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/header/div[2]/div/div/div[2]/div[1]/div")).click();
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/header/div[2]/div/div/div[2]/div[1]/div[2]/ul/li[6]/span/div[2]/span")).click();
            driver.quit();
        }
    }

}
