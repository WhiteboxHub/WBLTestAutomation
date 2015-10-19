package com.wbl.tests.Mobile;


import com.wbl.base.MobBaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends MobBaseTest {


	private static final By NONOBUTTON=By.xpath("//*[@class='android.widget.Button' and @text='No, no']");
	private static final By ENBUTTON=By.xpath("//*[@class='android.widget.Button' and @text='EN Button']");

	@Test
	public void test() throws InterruptedException
	{
		System.out.println("Inside Test");
		WebElement inputField = appiumDriver.findElement(By.className("android.widget.EditText"));
		inputField.click();
		Assert.assertEquals("true", inputField.getAttribute("enabled"));
		inputField.sendKeys("Appium");
		Assert.assertEquals("Appium", inputField.getText());
		//Thread.sleep(4000);
		appiumDriver.findElement(ENBUTTON).click();


		Thread.sleep(2000);
		appiumDriver.findElement(NONOBUTTON).click();

	}

}


