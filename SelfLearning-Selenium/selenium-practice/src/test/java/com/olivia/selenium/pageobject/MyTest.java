package com.olivia.selenium.pageobject;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyTest {
  private WebDriver driver; 
  private String USER = "*****@*.com";
  private String PASSWD = "*******";
  
  private HomePage homePage;
  private MyDashboardPage dashboardPage;

  @BeforeClass
  public void setUp() {
	  driver = new ChromeDriver();
  }
  
  @AfterClass
  public void tearDown() {
	  //driver.quit();
  }
	
  @Test()
  public void loginTwitter() {
	  LoginPage loginPage = new LoginPage(driver).get();	  
	  homePage = loginPage.logInTwitter(USER, PASSWD);
	  Assert.assertEquals(driver.getTitle(), "Twitter");
  }
  
  @Test(dependsOnMethods = "loginTwitter")
  public void newSingleTextTweet() {
	  String content = "This is a test tweet using selenium; Hello, world!";
	  boolean result = homePage.newSingleTextTweet(content);
	  Assert.assertTrue(result);  
  }
  
  @Test(dependsOnMethods = "newSingleTextTweet")
  public void checkTweetCount() {
	  try {
	    dashboardPage = homePage.goToDashboard();
	  }
	  catch (UnhandledAlertException e) {
		driver.switchTo().alert().accept();
	  }
	  String count = dashboardPage.getTweetsCount();
	  System.out.println(count);
	  Assert.assertEquals(count, "4");
  }
  
  @Test(dependsOnMethods = "checkTweetCount")
  public void deleteAddedTweet() {
	  boolean result = dashboardPage.deleteTweet(1);
	  Assert.assertTrue(result);
	  Assert.assertEquals(dashboardPage.getTweetsCount(), "3");
  }
}