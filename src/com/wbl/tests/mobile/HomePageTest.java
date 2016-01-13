package com.wbl.tests.mobile;


import com.wbl.base.mobile.MobBaseTest;
import com.wbl.pages.mobile.HomePage;
import com.wbl.utils.web.HtmlElement;
import com.wbl.utils.web.PageDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends MobBaseTest {

	private HomePage _hp;

	@BeforeClass
	public void beforeClass() {
		_hp = new HomePage(driver);
	}

	@Test
	public void test()
	{
		/*System.out.println("Inside Test");
		HtmlElement inputField = driver.findElement("android.widget.EditText");
		inputField.click();
		Assert.assertEquals("true", inputField.getAttribute("enabled"));
		inputField.sendKeys("Appium");
		Assert.assertEquals("Appium", inputField.getText());
		//Thread.sleep(4000);
		driver.findElement("mob.enbthn").click();
		Thread.sleep(2000);
		driver.findElement("mob.nonobtn").click();*/

	} 
	
	}



