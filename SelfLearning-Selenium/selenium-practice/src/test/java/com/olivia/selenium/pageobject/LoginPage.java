package com.olivia.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class LoginPage extends LoadableComponent<LoginPage> {

	private WebDriver driver;
	
	@FindBy(css = "input.js-username-field.email-input.js-initial-focus")
	private WebElement usernameInput;
	
	@FindBy(css = "input.js-password-field")
	private WebElement passwordInput;
	
	@FindBy(css = "button.submit.EdgeButton.EdgeButton--primary.EdgeButtom--medium")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePage logInTwitter(String username, String password) {
		//new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(usernameInput));
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

	@Override
	protected void load() {
		driver.get("https://twitter.com/login");	
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertEquals(driver.getTitle(), "Login on Twitter");
	}
}
