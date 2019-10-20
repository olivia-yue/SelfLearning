package com.olivia.selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class WindowsAndFramesHandling {
  WebDriver driver;
	
  //@Test
  public void windowsSwtich() {
	  driver.get("http://guidebook.seleniumacademy.com/Window.html");
	  
	  String firstWindow = driver.getWindowHandle();
	  System.out.println("The first window handle is: " + firstWindow);
	  
	  WebElement link = driver.findElement(By.linkText("Google Search"));
	  link.click();
	  
	  String secondWindow = driver.getWindowHandle();
	  System.out.println("The second window handle is: " + secondWindow);
	  System.out.println("The number of windows now are: " + driver.getWindowHandles().size());
	  
	  driver.switchTo().window(firstWindow);
	  
	  Set<String> handles = driver.getWindowHandles();
	  System.out.println(handles);
	  
	  String s = handles.iterator().next();
	  handles.remove(s);
	  System.out.println(s);
	  System.out.println(handles.iterator().next());
  }
  
  //@Test
  public void frameSwitch() {
	  driver.get("http://guidebook.seleniumacademy.com/Frames.html");
	  
	  driver.switchTo().frame(0);
	  WebElement firstField = driver.findElement(By.name("1"));
	  firstField.sendKeys("Hello Frame 1");
	  
	  //This step is important
	  driver.switchTo().defaultContent();
	  
	  driver.switchTo().frame(driver.findElement(By.name("frameTwo")));
	  WebElement secondField = driver.findElement(By.name("2"));
	  secondField.sendKeys("Hello Frame 2");
  }
  
  //@Test
  public void alertHandling() {
	  driver.get("http://guidebook.seleniumacademy.com/Window.html");
	  try {
		  Alert aa = driver.switchTo().alert();
		  aa.accept();
		  aa.dismiss();
		  aa.sendKeys("this is a test");
	  }
	  catch (NoAlertPresentException e)  {
		  System.out.println("No Alert windows!");
	  }
  }
  
  @Test
  public void browserNavigation() {
	 driver.navigate().to("https://google.com");
	 Assert.assertEquals(driver.getTitle(), "Google");
	 
	 WebElement searchBox = driver.findElement(By.name("q"));
	 searchBox.sendKeys("photo",Keys.RETURN);
	 Assert.assertEquals(driver.getTitle(), "photo - Google Search");
	 
	 driver.navigate().back();
	 Assert.assertEquals(driver.getTitle(), "Google");
	 
	 driver.navigate().forward();
	 Assert.assertEquals(driver.getTitle(), "photo - Google Search");
	 
	 driver.navigate().refresh();
  }
  
  @BeforeClass
  public void setUp() {
	  System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
	  driver = new ChromeDriver();
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }
}
