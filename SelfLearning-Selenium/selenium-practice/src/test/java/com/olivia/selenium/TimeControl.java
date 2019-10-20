package com.olivia.selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TimeControl {
  WebDriver driver;
	
  @Test(priority = 1)
  public void setTimeoutForPageLoad() {
	  //The TimeoutException should be thrown
	  driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MILLISECONDS);
	  try {
		  driver.get("https://google.com");
	  }
	  catch (TimeoutException e) {
		  System.out.println("Page load time is greater than 10 milliseconds");
		  //e.printStackTrace();
	  }  
  }
  
  @Test(priority = 0)
  public void setImplicitTimeout() {
	  driver.get("https://google.com");
	  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	  
	  WebElement searchBox = driver.findElement(By.name("q"));
	  searchBox.sendKeys("photo", Keys.RETURN);
	  
	  WebElement testField = driver.findElement(By.linkText("Images"));
	  testField.click();
  }
  
  @Test(priority = 1)
  public void setExplicitTimeout() {
	  driver.get("http://demo-store.seleniumacademy.com/");
	  /* Method1 */
	  //WebElement searchBox = driver.findElement(By.id("search"));
	  
	  /* Method2 */
	  //WebElement searchBox = (new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("search"))));
	  
	  /* Method3 */
	  WebElement searchBox = (new WebDriverWait(driver,5).until((ExpectedCondition<WebElement>)d -> d.findElement(By.id("search"))));
	  
	  searchBox.sendKeys("Photo");
	  searchBox.submit();
	  
	  //Assert.assertEquals(driver.getTitle(), "Search results for: 'Photo'");
	  
	  //new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Search results for: 'Photo'"));
	  
	  (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver d) {
              return d.getTitle().toLowerCase().startsWith("search ");
          }
      });
  }
  
  @BeforeClass
  public void setUp() {
	  System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver");
	  driver = new FirefoxDriver();
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }

}
