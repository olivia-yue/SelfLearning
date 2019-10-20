package com.olivia.selenium;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class TheEventListener2 extends AbstractWebDriverEventListener {
	
	private String GOOGLE_ACCESSIBILITY_DEVTOOLS = "https://raw.github.com/GoogleChrome/accessibility-developer-tools/stable/dist/js/axs_testing.js";
	
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: " + url);
	}
	
	@Override
	public void afterNavigateTo(String urlTo, WebDriver driver) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			URL url = new URL(GOOGLE_ACCESSIBILITY_DEVTOOLS);
			String script = IOUtils.toString(url.openStream(),StandardCharsets.UTF_8);
			jsExecutor.executeScript(script);
			String report= (String)jsExecutor.executeScript("var results = axs.Audit.run();return axs.Audit.createReport(results);");
			System.out.println("********Accessibility Report for " + driver.getTitle() + " *********");
			System.out.println(report);
			System.out.println("********Report End********");
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
	    System.out.println("The text is: " + text);
	}
	
	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
	    System.out.println("Find the web element: " + element.getText());
    }
	
	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
	    System.out.println("Click on!");
    }

}
