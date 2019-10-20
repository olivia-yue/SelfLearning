package com.olivia.bdd.function.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject {
	
	@FindBy (id = "email")
    public WebElementFacade emailAddrressInput;
	
	@FindBy (id = "pass")
    public WebElementFacade passwordInput;
	
	@FindBy (name = "send")
    public WebElementFacade logInButton;
	
	@FindBy (css = "div.page-title")
	public WebElementFacade headTitle;

}
