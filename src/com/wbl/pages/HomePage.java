package com.wbl.pages;

import com.wbl.utils.web.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wbl.pages.PortalPage;

/**
 * Created by svelupula on 8/14/2015.
 */
public class HomePage extends PortalPage {

    public HomePage(PageDriver driver) {
        super(driver);
        _logger.debug("Open Home Page");
        try {
            driver.findElement("header.home").click();
        }
        catch(Exception e)
        {
            _logger.error(e);
        }
    }

    public int getSliderItemsCount() {
        return driver.findElements("home.slider.items").size();
    }
    


    public boolean getFacebookPage()
    {
        boolean status = false;
        try{
	        driver.visibilityWait(WBy.get("header.social.fblink"));
            if(driver.getBrowser() != Browsers.HtmlUnit) {
                driver.executeJavaScript("arguments[0].click();","header.social.fblink");
            }
            else
            {
                driver.findElement("header.social.fblink").click();
            }
            driver.waitForLoad();
            status = driver.getCurrentUrl().contains("facebook")?true:false;
        }
        catch(Exception e)
        {
        	_logger.error(e);
        }
        return status;
    }


}
