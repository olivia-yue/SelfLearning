package com.olivia.bdd.function.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccountPage extends PageObject {
	
	@FindBy (css = "p.welcome-msg")
    public WebElementFacade welcomeMsg;
	
	@FindBy (css = "div.page-title")
    public WebElementFacade headTitle;
	
	@FindBy (css = "div.welcome-msg")
    public WebElementFacade helloMsg;
	
	@FindBy (className = "skip-account")
    public WebElementFacade accountIcon;
	
	@FindBy (linkText = "Log Out")
    public WebElementFacade logOut;

}
