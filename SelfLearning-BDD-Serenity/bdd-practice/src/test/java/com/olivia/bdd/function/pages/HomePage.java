package com.olivia.bdd.function.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class HomePage extends PageObject {
	
	@FindBy (id = "search")
    public WebElementFacade searchBox;
	
	@FindBy (className = "search-button")
    public WebElementFacade searchButton;
	
	@FindBy (className = "skip-account")
    public WebElementFacade accountIcon;
	
	@FindBy (linkText = "Log In")
    public WebElementFacade loginLink;

}
