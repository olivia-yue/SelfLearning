package com.olivia.selenium;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class CookieOperations {
  WebDriver driver;
  String loginPageUrl;
	
  @Test(priority = 0)
  public void toLoginPage() {
	  WebElement accountIcon = driver.findElement(By.className("skip-account"));
	  accountIcon.click();
	  
	  WebElement headAccount = driver.findElement(By.id("header-account"));
	  if (headAccount.isDisplayed()) {
		  WebElement accountLink = driver.findElement(By.linkText("My Account"));
		  accountLink.click();
	  }
	  Assert.assertEquals(driver.getTitle(), "Customer Login");
	  loginPageUrl = driver.getCurrentUrl();
	  System.out.println(loginPageUrl);
  }
  
  @Test(priority = 1)
  public void cookieStore() {
	  driver.get(loginPageUrl);
	  driver.findElement(By.id("email")).sendKeys("user@seleniumacademy.com");
	  driver.findElement(By.id("pass")).sendKeys("tester");
	  driver.findElement(By.id("send2")).click();
	  
	  Assert.assertEquals(driver.getTitle(), "My Account");
	  new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("My Account"));
	  
	  //Set<Cookie> ck1 = driver.manage().getCookies();
	  //String s = ck1.toString();
	  
	  
	  File dataFile = new File("./target/cookies/browser.data");
	  try {
		  dataFile.delete();
		  dataFile.createNewFile();
		  FileWriter fw = new FileWriter(dataFile);
		  BufferedWriter bw = new BufferedWriter(fw);
		  for (Cookie ck: driver.manage().getCookies()) {
			  bw.write(ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain()  + ";" + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.isSecure());
			  bw.newLine();
		  }
		  bw.flush();
		  bw.close();
		  fw.close();
	  }
	  catch (IOException e) {
		  e.printStackTrace();
	  }
	  //for next test case
	  driver.manage().deleteAllCookies();
  }
  
  @Test(priority = 2)
  public void cookieLoad() {
	  //no cookie info
	  driver.get("http://demo-store.seleniumacademy.com/");
	  driver.get(loginPageUrl);
	  Assert.assertEquals(driver.getTitle(), "Customer Login");
	  
	  //Load cookie info, then don't need to input account info
	  try {
		  File dataFile = new File("./target/cookies/browser.data");
		  FileReader fr= new FileReader(dataFile);
		  BufferedReader br = new BufferedReader(fr);
		  String line;
		  while((line = br.readLine()) != null) {
			  StringTokenizer str = new StringTokenizer(line, ";");
			  while(str.hasMoreTokens()) {
				  String name = str.nextToken();
				  String value = str.nextToken();
				  String domain = str.nextToken();
				  String path = str.nextToken();
				  Date expiry = null;
				  String date;
				  if(!(date = str.nextToken()).equals("null")) {
					  SimpleDateFormat formatter = new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
					  expiry = formatter.parse(date);
				  }
				  boolean isSecurity = new Boolean(str.nextToken());
				  Cookie ck = new Cookie(name, value, domain, path, expiry,isSecurity);
				  driver.manage().addCookie(ck);
			  }
		  }
		  br.close();
		  fr.close();
		  
		  driver.get("http://demo-store.seleniumacademy.com/");
		  driver.get(loginPageUrl);
		  String pageTitle = driver.findElement(By.cssSelector("div.page-title")).getText();
		  Assert.assertEquals(pageTitle, "MY DASHBOARD");
	  }
	  catch (Exception e) {
		  e.printStackTrace();
	  }
  }
  
  @BeforeClass
  public void setUp() {
	  System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
	  driver = new ChromeDriver();
	  driver.get("http://demo-store.seleniumacademy.com/");
  }

  @AfterClass
  public void tearDown() {
	  driver.quit();
  }

}
