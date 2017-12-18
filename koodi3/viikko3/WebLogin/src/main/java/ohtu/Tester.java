package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.io.File;
import java.util.Random;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


public class Tester {

    public static void main(String[] args) {
        File pathBinary = new File("/home/ljone/Videos/firefox/firefox");
        FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
        

        driver.get("http://localhost:4567");
        
        Random r = new Random();
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akke");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(2);
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekkaa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        
        sleep(2);
        element.submit();
        
        sleep(2);
        
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        
        sleep(2);
        
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        element = driver.findElement(By.name("username"));
        int i = r.nextInt(1000);
        element.sendKeys("kepa" + i);
        element = driver.findElement(By.name("password"));
        element.sendKeys("ojiek");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ojiek");
        
        sleep(2);
        element.submit();
        
        sleep(2);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(2);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
