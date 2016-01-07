package com.wbl.pages.mobile;

import com.wbl.utils.web.PageDriver;
import com.wbl.utils.web.WBy;
import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;

import javax.xml.ws.WebEndpoint;

public class HomePage extends MobPortalPage {

	public HomePage(PageDriver driver) {
		super(driver);
		_logger.debug("Open Home Page");
	}

	public void clickOnRegistrationButton() throws Exception
	{
		driver.visibilityWait(WBy.get("id=mob.registration.id"));

		driver.findElement("id=mob.registration.id").click();

	}
	public void clickOnChromeButton() throws Exception
	{

		driver.visibilityWait(WBy.get("id=mob.registration.id"));
		driver.findElement("id=mob.registration.id").click();

		
	}
	
}
