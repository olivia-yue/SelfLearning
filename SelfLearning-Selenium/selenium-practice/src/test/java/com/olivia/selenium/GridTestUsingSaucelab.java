package com.olivia.selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class GridTestUsingSaucelab {
  private WebDriver driver;
  private DesiredCapabilities caps;
  
  private String SOURCE_LAB_KEY = "37744fa8-5947-4452-9f09-e351427e3987";
  private String SOURCE_LAB_USER = "olivia.yue";
		  
  @Test
  public void searchProductsInStock() {
	  WebElement searchBox = driver.findElement(By.id("search"));
	  searchBox.clear();
	  searchBox.sendKeys("photo");
	  searchBox.submit();
	  
	  new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Search results for: 'photo'"));
	  
	  WebElement pageTitle = driver.findElement(By.cssSelector("div.page-title"));
	  Assert.assertEquals(pageTitle.getText(), "SEARCH RESULTS FOR 'PHOTO'");
	  
	  Assert.assertThrows(NoSuchElementException.class, () -> driver.findElement(By.cssSelector("p.note-msg")));
  }
  
  @Test
  public void linksCountTest() {
	  List<WebElement> links = driver.findElements(By.tagName("a"));
	  System.out.println("Total Links: " + links.size());
	  
	  long count = links.stream().filter(item -> item.isDisplayed()).count();
	  System.out.println("The visible links: " + count);
  }
  
  @Test
  public void imgAltCheck() {
	  List<WebElement> imgs = driver.findElements(By.tagName("img"));
	  System.out.println("Total images: " + imgs.size());
	  
	  List<WebElement> imgsWithoutAlt = imgs.stream().filter(item -> item.getAttribute("alt") == "").collect(Collectors.toList());
	  System.out.println("Total images without alt attribute: " + imgsWithoutAlt.size());
  }
  
  @Test
  public void searchProducts() {
	  WebElement searchBox = driver.findElement(By.id("search"));
	  searchBox.clear();
	  searchBox.sendKeys("phones");
	  WebElement searchButton = driver.findElement(By.className("search-button"));
	  searchButton.click();
	  new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Search results for: 'phones'"));
	  
	  List<WebElement> searchItems = driver.findElements(By.cssSelector("h2.product-name a"));  
	  List<String> expectedItems = Arrays.asList("Madison Earbuds","Madison Overear Headphones","MP3 Player with Audio");
	  List<String> expectedItemsToUpperCase = expectedItems.stream().map(item -> item.toUpperCase()).collect(Collectors.toList());
	  List<String> productsNames = searchItems.stream().map(WebElement::getText).collect(Collectors.toList());
	  System.out.println(productsNames);
	  System.out.println(expectedItemsToUpperCase);
	  Assert.assertEquals(productsNames, expectedItemsToUpperCase);	  	  
  }
  
  @Test
  public void searchAndViewProduct() {
	  WebElement searchBox = driver.findElement(By.id("search"));
	  searchBox.clear();
	  searchBox.sendKeys("glass");
	  searchBox.submit();
	  new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Search results for: 'glass'"));
	  
	  List<WebElement> productSearched = driver.findElements(By.cssSelector("h2.product-name a"));
	  productSearched.stream().filter(item -> item.getText().equalsIgnoreCase("Herald Glass Vase")).findFirst().get().click();
	  
	  Assert.assertEquals(driver.getTitle(), "Herald Glass Vase");
  }
  
  @BeforeClass
  public void setUp() throws MalformedURLException {
	  caps = DesiredCapabilities.firefox();
	  caps.setCapability("platform", "Linux");
	  caps.setCapability("version", "45.0");
	  
	  driver = new RemoteWebDriver(new URL(MessageFormat.format("http://{0}:{1}@ondemand.saucelabs.com:80/wd/hub", SOURCE_LAB_USER, SOURCE_LAB_KEY)), caps);
	  driver.get("http://demo-store.seleniumacademy.com/");
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }

}
