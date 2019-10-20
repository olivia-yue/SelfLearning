package com.olivia.selenium.appium;

import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class IOSTest extends AppiumService{
	
  private WebDriver driver;
  
  @Test
  public void test() {
	  WebElement searchIcon = driver.findElement(By.cssSelector("a.skip-search span.icon"));
	  searchIcon.click();
	  
	  WebElement searchBox = driver.findElement(By.id("search"));
	  searchBox.sendKeys("Phones");
	  WebElement searchButton = driver.findElement(By.className("search-button"));
	  searchButton.click();
	  
	  System.out.println("testing...");
	  List<WebElement> products = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("h2.product-name a")));
	  Assert.assertEquals(3, products.size());
  }
  @BeforeClass
  public void setUp() throws MalformedURLException {
	  DesiredCapabilities caps = new DesiredCapabilities();
	  caps.setCapability("platformName", "iOS");
	  caps.setCapability("platformVersion", "12.1");
	  caps.setCapability("deviceName", "iPhone XR");
	  caps.setCapability("browserName", "safari");
	  //driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
	  //If use the class AppiumService, need install node.js, then 'npm install appiums'
	  driver = new IOSDriver<>(getServiceUrl(),caps);
	  driver.get("http://demo-store.seleniumacademy.com/");
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }

}
