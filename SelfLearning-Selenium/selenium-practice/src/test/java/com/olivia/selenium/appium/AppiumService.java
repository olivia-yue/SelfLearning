package com.olivia.selenium.appium;

import java.net.URL;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public abstract class AppiumService {
	
	private static AppiumDriverLocalService service;
	
	@BeforeSuite
	public void appiumDriverServiceStart() {
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
	}
	
	@AfterSuite(alwaysRun = true)
	public void appiumDriverServiceStop() {
		service.stop();
	}
	
    public URL getServiceUrl() {
    	return service.getUrl();
    }
	
}
  