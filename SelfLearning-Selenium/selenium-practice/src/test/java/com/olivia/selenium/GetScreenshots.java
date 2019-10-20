package com.olivia.selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;

public class GetScreenshots {
  WebDriver driver;
  String driversPath = "./src/test/resources/drivers/";
  String homePage = "http://demo-store.seleniumacademy.com/";
  String sceenshotsPath = "./target/screenshots/";
	
  @Test(priority = 0)
  public void fromChrome() throws IOException {
	  System.setProperty("web.driver.chrome", driversPath + "chromedriver");
	  driver = new ChromeDriver();
	  driver.get(homePage);
	  
	  takeScreenshotsAndSave("chrome.png");
  }
  
  @Test(priority = 2)
  public void fromFirefox() throws IOException {
	  System.setProperty("web.driver.geckodriver", driversPath + "geckodriver");
	  driver = new FirefoxDriver();
	  driver.get(homePage);
	  
	  takeScreenshotsAndSave("firefox.png");
  }
  
  @Test(priority = 2)
  public void fromSafari() throws WebDriverException, IOException {
	  driver = new SafariDriver();
	  driver.get(homePage);
	  
	  takeScreenshotsAndSave("safari.png");
  }
  
  @Test(priority = 1)
  public void fromChromeinHeadlessMode() throws IOException {
	  ChromeOptions option = new ChromeOptions();
	  option.setHeadless(true);
	  
	  driver = new ChromeDriver(option);
	  driver.get(homePage);
	  
	  takeScreenshotsAndSave("chrome_headless.png");
  }

  private void takeScreenshotsAndSave(String fileName) throws IOException {
	  File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(srcFile, new File(sceenshotsPath + fileName));
}
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
