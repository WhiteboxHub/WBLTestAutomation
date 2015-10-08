package com.wbl.tests.Mobile;

import com.wbl.base.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import com.wbl.pages.Mobile.HomePage;
import com.wbl.pages.Mobile.RegistrationPage;

public class RegistrationPageTest extends BaseTest {

	AppiumDriver appiumDriver;

	public void setAppiumDriver(AppiumDriver appiumDriver){
		this.appiumDriver=appiumDriver;
	}

	
	@Test(dataProviderClass=MyDataProvider.class , dataProvider="Fillform")
	public void test2(String UserName,String Email,String Password,String Name, String ProgramLanguage)
	{
		
		System.out.println("Inside Test");
		HomePage appHomePage=new HomePage(appiumDriver);
		appHomePage.clickOnRegistrationButton();
		RegistrationPage formPage=new RegistrationPage(appiumDriver);
		formPage.enterUserName(UserName);
		formPage.enterEmail(Email);
		formPage.enterPassword(Password);
		formPage.enterName(Name);
		formPage.selectProgramLanguage(ProgramLanguage);
		formPage.clickOnRegister();
		
		
	}
	
	
}
