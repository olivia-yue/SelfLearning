package com.olivia.selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class MouseControl {
  private WebDriver driver;
  private Actions actions;
	
  @Test
  public void performCompositeAction() {
	  WebElement one = driver.findElement(By.name("one"));
	  WebElement six = driver.findElement(By.name("six"));
	  WebElement eleven = driver.findElement(By.name("eleven"));
	  
	  //before selected, the class value is "ui-state-default ui-selectee"
	  System.out.println(one.getAttribute("class"));
	  //before selected, the background color
	  System.out.println(one.getCssValue("background-color"));
	  
	  //add all actions into the Actions builder.
	  //Actions actions = new Actions(driver);
	  actions.keyDown(Keys.CONTROL)
	         .click(one)
	         .click(eleven)
	         .click(six)
	         .keyUp(Keys.CONTROL);
	  
	  //generate the composite action
	  Action compositeAction = actions.build();
	  //perform the composite action
	  compositeAction.perform(); 
	  
	  //How to verify the right elements are selected?
	  //After selected, the class value is "ui-state-default ui-selectee ui-selected"
	  System.out.println(one.getAttribute("class"));
	  System.out.println(one.getCssValue("background-color"));
	  Assert.assertEquals(one.getAttribute("class"), "ui-state-default ui-selectee ui-selected");
	  Assert.assertEquals(one.getCssValue("background-color"), "rgba(243, 152, 20, 1)");
  }
  
  @Test
  public void performAction() {
	  WebElement two = driver.findElement(By.name("two"));
	  WebElement four = driver.findElement(By.name("four"));
	  WebElement eight = driver.findElement(By.name("eight"));
	  WebElement twelve = driver.findElement(By.name("twelve"));
	   
	  System.out.println(twelve.getAttribute("class"));
	  System.out.println(twelve.getCssValue("background-color"));
	  
	  //add all actions into the Actions builder.
	  //Actions actions = new Actions(driver);
	  actions.keyDown(Keys.CONTROL)
	         .click(two)
	         .click(four)
	         .click(eight)
	         .click(twelve)
	         .keyUp(Keys.CONTROL);
	  
	  actions.perform();
	  
	  //After selected, the class value is "ui-state-default ui-selectee ui-selected"
	  System.out.println(two.getAttribute("class"));
	  System.out.println(two.getCssValue("background-color"));
	  Assert.assertEquals(two.getAttribute("class"), "ui-state-default ui-selectee ui-selected");
	  Assert.assertEquals(two.getCssValue("background-color"), "rgba(243, 152, 20, 1)");
  }
  
  @Test
  public void mouseMoveByOffsetAndClick() {
	  WebElement one = driver.findElement(By.name("one"));
	  System.out.println(one.getLocation().getX() + ";" + one.getLocation().getY());
	  
	  WebElement six = driver.findElement(By.name("six"));
	  System.out.println(six.getLocation().getX() + ";" + six.getLocation().getY());
	  
	  WebElement twelve = driver.findElement(By.name("twelve"));
	  System.out.println(twelve.getLocation().getX() + ";" + twelve.getLocation().getY());
	  
	  int height = 80;
	  int width = 100;
	  int border = 1;
	  
	  //Actions actions = new Actions(driver);
	  
	  //click on 1
	  actions.moveToElement(one);
	  actions.moveByOffset(one.getLocation().getX() + border, one.getLocation().getY() + border).click();
	  actions.perform();
	  Assert.assertEquals(one.getCssValue("background-color"), "rgba(243, 152, 20, 1)");
	  
	  //click on 12
	  actions.moveByOffset(3 * width + 7 * border, 2 * height + 5 * border).click();
	  actions.perform();
	  Assert.assertEquals(twelve.getCssValue("background-color"), "rgba(243, 152, 20, 1)");
	  
	  //click on 6
	  actions.moveByOffset(-2 * width - 4 * border, -1 * height - 2 * border).click();
	  actions.build().perform();
	  Assert.assertEquals(six.getCssValue("background-color"), "rgba(243, 152, 20, 1)");
  }
  
  @Test
  public void clickOnElement() {
	  WebElement seven = driver.findElement(By.name("seven"));
	  //Actions actions = new Actions(driver);
	  actions.click(seven);
	  actions.perform();
	  Assert.assertEquals(seven.getCssValue("background-color"), "rgba(243, 152, 20, 1)");
	  
	  WebElement four = driver.findElement(By.name("four"));
	  actions.click(four).click(seven).perform();
  }
  
  @Test(priority = 1)
  public void clickAndHold() {
	  driver.navigate().to("http://guidebook.seleniumacademy.com/Sortable.html");
	  actions.moveByOffset(200, 20).clickAndHold().moveByOffset(120, 0).release().perform();
	  
	  WebElement four = driver.findElement(By.name("four"));
	  WebElement nine = driver.findElement(By.name("nine"));
	  //following two have the same effect
	  actions.clickAndHold(four).release(nine).perform();
	  actions.moveToElement(four).clickAndHold().release(nine).perform();
  }
  
  @BeforeMethod
  public void refreshPage() {
	  driver.navigate().refresh();
  }
  
  @BeforeClass
  public void setUp() {
	  //If you put the driver path into the $PATH, then don't need to set this
	  //System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
	  
	  driver = new ChromeDriver();
	  actions = new Actions(driver);
	  driver.get("http://guidebook.seleniumacademy.com/Selectable.html");
  }

  @AfterClass
  public void tearDown() {
	  //driver.quit();
  }

}
