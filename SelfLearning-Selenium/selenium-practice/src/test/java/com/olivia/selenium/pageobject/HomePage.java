package com.olivia.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
	private WebDriver driver;
	
	@FindBy(id = "tweet-box-home-timeline")
	private WebElement tweetBox;
	
	@FindBy(css = "span.button-text.tweeting-text")
	private WebElement tweetButton;
	
	@FindBy(className = "DashboardProfileCard-name")
	private WebElement profileCardName;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://twitter.com/");
	}
	
	public boolean newSingleTextTweet(String tweedContent) {
		boolean result;
		tweetBox.sendKeys(tweedContent);
		if(tweetButton.isEnabled()) {
			tweetButton.click();
			result =  true;
		}
		else {
			System.out.println("The tweet button is not enabled!");
			result = false;
		} 
		return result;
	}
	
	public MyDashboardPage goToDashboard() {
		profileCardName.click();
		return PageFactory.initElements(driver, MyDashboardPage.class);
	}
	

}
