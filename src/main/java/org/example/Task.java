package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.TimerTask;

public class Task extends TimerTask {

    @Override
    public void run() {
        String phoneIrancell = "09351234567"; //example replace with your real number
        String phoneHamrahAval = "09121234567"; //example replace with your real number
        String passwordIrancell = "12345678"; // example replace with your real password
        String passwordHamrahAval = "87654321"; //example replace with your real password

        try {
            // here i use with two account you can use only one or as many as you want
            appController(phoneIrancell, passwordIrancell);
            appController(phoneHamrahAval, passwordHamrahAval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void appController(String phone, String password) throws InterruptedException {

        Manage manage = new Manage();
        System.setProperty("webdriver.chrome.driver", "/home/"+ manage.findUsername()+"/Downloads/chromedriver-linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=/home/"+ manage.findUsername()+"/.config/chromium");
        WebDriver driver = new ChromeDriver(options);

        String url = "https://www.digikala.com/users/login/?backUrl=/";
        driver.get(url);

        try {
            login(phone, password,driver);
            Thread.sleep(1000);
            click(driver);
            Thread.sleep(1000);
            logout(driver);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Something wrong\n" +
                    "please contact with this Email 'omidnet42@yahoo.com' and report the problem");
        }
    }

    //section click
    public boolean click(WebDriver driver) throws InterruptedException {
        try {
            String urlMission = "https://www.digikala.com/digiclub/missions/";
            driver.get(urlMission);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, window.scrollY + window.innerHeight / 2)");
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[3]/div[2]/div[2]/button/div[2]")).click();
            System.out.println("Clicked successfully");
            return true;
        } catch (Exception e) {
            System.out.println("You already clicked on button");
            return false;
        }
    }

    //section login
    public boolean login(String phone, String password,WebDriver driver) throws InterruptedException {
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
            System.out.println("Login successfully");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Section logout
    public boolean logout(WebDriver driver) throws InterruptedException {
        try {
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(".styles_BaseLayoutMiniProfile__button__DHUHQ > div > .flex > svg")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(".px-4:nth-child(6) .text-subtitle-strong")).click();
            Thread.sleep(2000);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            actions.sendKeys(Keys.TAB).perform();
            actions.sendKeys(Keys.SPACE).perform();
            Thread.sleep(2000);
            driver.quit();
            System.out.println("Logout successfully");
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
