package com.olivia.bdd.function.steps;

import com.olivia.bdd.function.pages.AccountPage;
import com.olivia.bdd.function.pages.HomePage;
import com.olivia.bdd.function.pages.LoginPage;
import com.olivia.bdd.function.pages.LogoutPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.thucydides.core.annotations.Steps;

public class LoginFromHomePage {
	
	@Steps
	HomePage homePage;
	
	@Steps
	LoginPage loginPage;
	
	@Steps
	AccountPage accountPage; 
	
	@Steps
	LogoutPage logoutPage;
	
	@Given("^user is on the demo-store website$")
	public void user_is_on_the_demo_store_website() {
	   homePage.open();
	   homePage.getDriver().manage().window().maximize();
	}

	@Then("^user should see page title is \"([^\"]*)\"$")
	public void user_should_see_page_title_is(String pageTitle) {
	    assertThat(homePage.getDriver().getTitle()).isEqualTo(pageTitle);
	}

	@When("^user wants to go to login page$")
	public void user_wants_to_go_to_login_page() {
	    homePage.accountIcon.click();
	    if(homePage.loginLink.isVisible()) {
	    	homePage.loginLink.click();
	    }
	}
	
	@Then("^user should be redirected to login page and should see \"([^\"]*)\"$")
	public void user_should_be_redirected_to_login_page_and_should_see(String headTitle) {
		assertThat(loginPage.headTitle.getText()).isEqualToIgnoringCase(headTitle);
	}

	@When("^user input correct username and password$")
	public void user_input_correct_username_and_password() {
	    loginPage.emailAddrressInput.sendKeys("user@seleniumacademy.com");
	    loginPage.passwordInput.sendKeys("tester");
	    loginPage.logInButton.click();
	}

	@Then("^user should be redirect to account page$")
	public void user_should_be_redirect_to_account_page() {
	    assertThat(accountPage.getDriver().getCurrentUrl()).contains("/account");
	}
	
	@Given("^user has logged in$")
	public void user_has_logged_in() {
	    homePage.accountIcon.click();
		homePage.loginLink.click();
		loginPage.emailAddrressInput.sendKeys("user@seleniumacademy.com");
	    loginPage.passwordInput.sendKeys("tester");
	    loginPage.logInButton.click();
	}

	@When("^user wants to log out$")
	public void user_wants_to_log_out() {
		accountPage.accountIcon.click();
		accountPage.logOut.click();
	}

	@Then("^user should be redirected to logout page and see \"([^\"]*)\"$")
	public void user_should_be_redirected_to_logout_page_and_see(String logOutMsg) {
		assertThat(logoutPage.getDriver().getCurrentUrl()).contains("logoutSuccess");
		assertThat(logoutPage.logoutMsg.getText()).isEqualTo(logOutMsg);
	}

	@Then("^user should be redirected to home page$")
	public void user_should_be_redirected_to_home_page() {
		//assertThat(homePage.getDriver().getCurrentUrl()).isEqualTo("http://demo-store.seleniumacademy.com/");
		 (new WebDriverWait(homePage.getDriver(), 10)).until(new ExpectedCondition<Boolean>() {
	          public Boolean apply(WebDriver d) {
	              return d.getCurrentUrl().equals("http://demo-store.seleniumacademy.com/");
	          }
	      });
	}


}
