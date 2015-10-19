package com.wbl.utils.mobile;
import io.appium.java_client.AppiumDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;


public class WaitClass {

	public static WebElement waitFor(AppiumDriver appiumDriver,final By locator,long timeout) throws TimeoutException
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(appiumDriver)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class,WebDriverException.class);

		WebElement element= wait.until(new Function<WebDriver,WebElement>()
		{
			public WebElement apply(WebDriver driver)
			{
				return driver.findElement(locator);
			}
		});
		return element;
	}
}
