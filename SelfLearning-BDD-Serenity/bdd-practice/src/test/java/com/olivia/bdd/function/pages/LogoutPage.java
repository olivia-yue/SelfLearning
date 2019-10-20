package com.olivia.bdd.function.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LogoutPage extends PageObject {
	
	@FindBy (css = "div.page-title")
    public WebElementFacade logoutMsg;

}
