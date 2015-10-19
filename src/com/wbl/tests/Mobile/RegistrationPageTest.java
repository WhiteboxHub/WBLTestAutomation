package com.wbl.tests.Mobile;

import com.wbl.base.MobBaseTest;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import com.wbl.pages.Mobile.HomePage;
import com.wbl.pages.Mobile.RegistrationPage;

public class RegistrationPageTest extends MobBaseTest {


	@Test(dataProviderClass=MyDataProvider.class , dataProvider="Fillform")
	public void test2(String UserName,String Email,String Password,String Name, String ProgramLanguage) throws InterruptedException {

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
