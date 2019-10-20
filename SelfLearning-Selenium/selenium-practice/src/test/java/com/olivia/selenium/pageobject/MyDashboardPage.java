package com.olivia.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyDashboardPage {
	WebDriver driver;
	
	@FindBy(css = "a[data-nav='tweets'] span[class='ProfileNav-value']")
	private WebElement tweetCount;
	
	@FindBy(css = "a[data-nav='following'] span[class='ProfileNav-value']")
	private WebElement followingCount;
	
	@FindBy(css = "a[data-nav='followers'] span[class='ProfileNav-value']")
	private WebElement followerCount;
	
	@FindBy(css= "ol#stream-items-id li:nth-of-type(1) div[class='js-tweet-text-container']")
	private WebElement latestTweetContent;
	
	@FindBy(css= "li.js-actionDelete")
	private WebElement deleteButton;
	
	@FindBy(css= "div[class='dropdown']")
	private List<WebElement> dropDownButtons;
	
	@FindBy(css = "div[class='dropdown-menu is-autoCentered']")
	private List<WebElement> dropDownMenu;
	
	public MyDashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTweetsCount() {
		return tweetCount.getText();
	}
	
	public String getFollowingCount() {
		return followingCount.getText();
	}
	
	public String getFollowersCount() {
		return followerCount.getText();
	}
	
	public String getLatestTweets() {
		return latestTweetContent.getText();
	}
	
	public boolean deleteTweet(int num) {
		boolean result;
		dropDownButtons.get(num).click();
		if (dropDownMenu.get(num).isDisplayed()) {
			deleteButton.click();
			result = true;
		}
		else {
			System.out.println("Dropdown menu is not dispalyed");
			result =false;
		}
		return result;
	}

}
