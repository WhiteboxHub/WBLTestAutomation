package com.wbl.pages.Mobile;

import org.openqa.selenium.By;

import com.wbl.utils.mobile.WaitClass;

import io.appium.java_client.AppiumDriver;

public class HomePage {

	public static final By REGISTRATIONBUTTON=By.id("io.selendroid.testapp:id/startUserRegistration");

	public static final By CHROMEBUTTON=By.id("io.selendroid.testapp:id/buttonStartWebview");

	private AppiumDriver appiumDriver;


	public HomePage(AppiumDriver appiumDriver)
	{
		this.appiumDriver=appiumDriver;
	}
	public void clickOnRegistrationButton()
	{
		WaitClass.waitFor(appiumDriver, REGISTRATIONBUTTON, 60);
		appiumDriver.findElement(REGISTRATIONBUTTON).click();

	}
	public void clickOnChromeButton()
	{
		WaitClass.waitFor(appiumDriver, CHROMEBUTTON, 60);
		appiumDriver.findElement(CHROMEBUTTON).click();

	}

}
