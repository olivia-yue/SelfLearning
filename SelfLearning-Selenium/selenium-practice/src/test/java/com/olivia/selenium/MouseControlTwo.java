package com.olivia.selenium;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class MouseControlTwo {
  WebDriver driver;
  Actions actions;
	
  @Test
  public void dragAndDropBy() {
	  driver.get("http://guidebook.seleniumacademy.com/DragMe.html");
	  WebElement dragBox = driver.findElement(By.id("draggable"));
	  
	  // Attention: never forget "action.perform()" in the end!!!!
	  actions.dragAndDropBy(dragBox, 300, 300).perform();
  }
  
  @Test
  public void dragAndDrop() {
	  driver.get("http://guidebook.seleniumacademy.com/DragAndDrop.html");
	  WebElement dragBox = driver.findElement(By.id("draggable"));
	  WebElement dropBox = driver.findElement(By.id("droppable"));
	  
	  actions.dragAndDrop(dragBox, dropBox).perform();
  }
  
  @Test
  public void doubleClick() {
	  driver.get("http://guidebook.seleniumacademy.com/DoubleClick.html");
	  WebElement dblClick = driver.findElement(By.name("dblClick"));
	  
	  actions.moveToElement(dblClick).doubleClick().perform();
	  
	  assertEquals(driver.switchTo().alert().getText(), "Double Clicked !!");
	  driver.switchTo().alert().accept();
	  
	  actions.doubleClick(dblClick).perform();
	  try {
	    driver.switchTo().alert();
	  }
	  catch (NoAlertPresentException e){
		System.out.println("No alert present!");  
	  }
	  finally {
		driver.switchTo().alert().accept();	
	  }
	  
  }
  
  @Test
  public void contextClick() {
	  driver.get("http://guidebook.seleniumacademy.com/ContextClick.html");
	  WebElement contextMenu = driver.findElement(By.id("div-context"));
	  actions.contextClick(contextMenu).click(driver.findElement(By.name("Item 3"))).perform();
	  
	  assertEquals(driver.switchTo().alert().getText(), "Item 3 Clicked");
	  driver.switchTo().alert().accept();
  }
  
  @BeforeClass
  public void setUp() {
	  driver = new FirefoxDriver();
	  actions = new Actions(driver);
  }
  
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
