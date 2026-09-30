package com.amazon;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;
public class AmazonTest {
	
@Test
    public  void amazonTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.amazon.in/");

       // System.out.println(driver.getTitle());
        
        Thread.sleep(3000);
        
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Laptop");

        driver.findElement(By.id("nav-search-submit-button")).click();
        
        driver.findElement(By.xpath("(//div[@data-component-type='s-search-result']//h2)[1]")).click();
        
        Thread.sleep(3000);
        
        String currentWindow = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
            }
        }
        
        String title = driver.findElement(By.id("productTitle")).getText();

        System.out.println("Product Title = " + title);
        
        Assert.assertTrue(title.length() > 0, "Product title is not displayed");
        
        String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();

        System.out.println("Product Price = ₹" + price);
        
        Assert.assertTrue(price.length() > 0, "Product price is not displayed");
        
        String url = driver.getCurrentUrl();
        
        System.out.println("Product URL = " + url);
        
        driver.findElement(By.id("add-to-cart-button")).click();

        Thread.sleep(3000);

        System.out.println("Product added to cart successfully");
        
        driver.findElement(By.id("nav-cart")).click();

        Thread.sleep(3000);

        System.out.println("Cart page opened successfully");
        
        String cartProduct = driver.findElement(By.xpath("//div[contains(@class,'sc-list-item')]//span[contains(@class,'a-truncate-full')]")).getText();

        System.out.println("Cart Product = " + cartProduct);
        
        String quantity = driver.findElement(By.xpath("//span[@data-a-selector='value']")).getText();

        System.out.println("Cart Quantity = " + quantity);
        
        Assert.assertEquals(quantity, "1", "Cart quantity is not 1");
        
        driver.findElement(By.xpath("//input[@value='Delete']")).click();

        Thread.sleep(2000);

        System.out.println("Product removed from cart successfully");
    }
}