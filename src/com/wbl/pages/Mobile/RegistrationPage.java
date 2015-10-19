package com.wbl.pages.Mobile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.wbl.utils.mobile.WaitClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage {

	private AppiumDriver appiumDriver;


	public static final By USERNAMETEXTFIELD=By.id("io.selendroid.testapp:id/inputUsername");
	public static final By EMAILTEXTFIELD=By.id("io.selendroid.testapp:id/inputEmail");
	public static final By PASSWORDTEXTFIELD=By.id("io.selendroid.testapp:id/inputPassword");
	public static final By NAMETEXTFIELD=By.id("io.selendroid.testapp:id/inputName");
	public static final By SPINNERBUTTON=By.id("io.selendroid.testapp:id/input_preferedProgrammingLanguage");
	public static final By CHECKBOX=By.id("io.selendroid.testapp:id/input_adds");
	public static final By REGISTERUSERBT=By.id("io.selendroid.testapp:id/btnRegisterUser");
	public static final By SPINNERLIST=By.xpath("//*[@id='android:id/select_dialog_listview']/CheckedTextView");

	public RegistrationPage(AppiumDriver appiumDriver)
	{
		this.appiumDriver=appiumDriver;
	}

	public void enterUserName(String username) throws InterruptedException {
		AndroidElement userElement=(AndroidElement)appiumDriver.findElement(USERNAMETEXTFIELD);
		userElement.sendKeys(username);
		Thread.sleep(2000);
		((AndroidDriver) appiumDriver).hideKeyboard();
	}
	public void enterEmail(String email) throws InterruptedException {
		appiumDriver.findElement(EMAILTEXTFIELD).sendKeys(email);
		((AndroidDriver)appiumDriver).hideKeyboard();
	}
	public void enterPassword(String password)
	{
		appiumDriver.findElement(PASSWORDTEXTFIELD).sendKeys(password);
		((AndroidDriver)appiumDriver).hideKeyboard();
	}
	public void enterName(String name)
	{
		AndroidElement nameElement=(AndroidElement)appiumDriver.findElement(NAMETEXTFIELD);
		nameElement.click();
		nameElement.clear();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		//WaitClass.waitFor(appiumDriver,By.className("android.widget.CheckedTextView"),60);
		nameElement.sendKeys(name);
		((AndroidDriver)appiumDriver).hideKeyboard();
	}
	public void selectProgramLanguage(String text)
	{
		MobileElement element=(MobileElement) appiumDriver.findElement(SPINNERBUTTON);
		appiumDriver.tap(1, element,1);
		WaitClass.waitFor(appiumDriver,By.className("android.widget.CheckedTextView"),60);
		List<WebElement> list=appiumDriver.findElements(By.className("android.widget.CheckedTextView"));
		for(WebElement item:list)
		{
			if(item.getAttribute("text").contains(text))
			{
				appiumDriver.tap(1,item,1);
				break;
			}

		}
	}

	public void clickOnRegister()
	{
		appiumDriver.findElement(REGISTERUSERBT).click();
	}

}
