package com.olivia.selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RemoteWebDriverTest {
  private WebDriver remoteDriver;
  private DesiredCapabilities caps;
  
  private String STANDALONE_IP = "192.168.1.97";
  private String GRID_HUB_IP = "192.168.1.67";
  private String PORT = "4444";
  private String REMOTE_ADDRESS = "http://" + GRID_HUB_IP + ":" + PORT + "/wd/hub";
	
  @Test
  public void searchNoExistProduct() {
	  WebElement searchBox = remoteDriver.findElement(By.id("search"));
	  searchBox.sendKeys("shoes");
	  searchBox.submit();
	  
	  //Assert.assertEquals(remoteDriver.getTitle(), "Search results for: 'shoes'");
	  new WebDriverWait(remoteDriver, 5).until(ExpectedConditions.titleIs("Search results for: 'shoes'"));
	  
	  WebElement pageTitle = remoteDriver.findElement(By.cssSelector("div.page-title"));
	  Assert.assertEquals(pageTitle.getText(), "SEARCH RESULTS FOR 'SHOES'");
	  
	  try {
	    WebElement noteMsg = remoteDriver.findElement(By.cssSelector("p.note-msg"));
	    Assert.assertEquals(noteMsg.getText(), "Your search returns no results.");
	  }
	  catch (NoSuchElementException e) {
		Assert.assertFalse(true, "IT is not expected results");
	  }
	  
	  Assert.assertThrows(NoSuchElementException.class, () -> remoteDriver.findElement(By.cssSelector("div.category-products")));
  }
  
  @BeforeClass
  public void setUp() throws MalformedURLException {
	  caps = new DesiredCapabilities();
	  caps.setBrowserName("chrome");
	  caps.setPlatform(Platform.MAC);
	  
	  //caps = DesiredCapabilities.chrome();
	  
	  remoteDriver = new RemoteWebDriver(new URL(REMOTE_ADDRESS), caps);
	  remoteDriver.get("http://demo-store.seleniumacademy.com/");
  }

  @AfterClass
  public void tearDown() {
	  remoteDriver.quit();
  }

}
