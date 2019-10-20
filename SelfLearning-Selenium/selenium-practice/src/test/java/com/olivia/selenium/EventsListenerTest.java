package com.olivia.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EventsListenerTest {
  WebDriver driver;
  EventFiringWebDriver eventFiringWebDriver;
  TheEventListener2 eventListener2;
  TheEventListener eventListener;
	
  //@Test
  public void theFirstListenerTest() {
	  eventFiringWebDriver.register(eventListener2);
	  eventFiringWebDriver.register(eventListener);
	  
	  eventFiringWebDriver.get("https://facebook.com");
	  eventFiringWebDriver.get("http://google.com");
	  
	  WebElement gmailLink  = eventFiringWebDriver.findElement(By.cssSelector("a.gb_P"));
      gmailLink.click();
  }
  
  //@Test
  public void accessibilityCheck() {
	  // only need to run listener2
	  eventFiringWebDriver.register(eventListener2);
	  eventFiringWebDriver.get("http://demo-store.seleniumacademy.com/");
  }
  
  @Test
  public void pageLoadPerformance() {
	  // only need to run listener
	  eventFiringWebDriver.register(eventListener);
	  eventFiringWebDriver.get("http://demo-store.seleniumacademy.com/");
  }
  
  @BeforeClass
  public void setUp() {
	  driver = new ChromeDriver();
	  eventFiringWebDriver = new EventFiringWebDriver(driver);
	  eventListener2 = new TheEventListener2();
	  eventListener = new TheEventListener();
  }
  
  @AfterClass
  public void tearDown() {
	  eventFiringWebDriver.unregister(eventListener);
	  eventFiringWebDriver.unregister(eventListener2);
	  eventFiringWebDriver.quit();
  }
}
