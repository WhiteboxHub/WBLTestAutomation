package com.wbl.tests.Mobile;

import java.util.concurrent.TimeUnit;

import com.wbl.base.BaseTest;
import com.wbl.pages.Mobile.WebViewPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.wbl.pages.Mobile.HomePage;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebviewPageTest extends BaseTest {

	AppiumDriver appiumDriver;

	public void setAppiumDriver(AppiumDriver appiumDriver){
		this.appiumDriver=appiumDriver;
	}
	@Test
	public void test1()
	{
		HomePage appHomePage=new HomePage(appiumDriver);
		appHomePage.clickOnChromeButton();
		WebViewPage webViewPage=new WebViewPage(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver,10,TimeUnit.SECONDS),webViewPage);
		webViewPage.selectWebViewHtml("formPage");
		webViewPage.switchToWebView();
		
	   // WebElement txt=appiumDriver.findElement( By.className("android.widget.TextView"));
		//Assert.assertEquals("Web View Interaction", txt.getText());
		
	}
}