package com.wbl.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wbl.pages.PortalPage;
import com.wbl.utils.web.HtmlElement;
import com.wbl.utils.web.PageDriver;
import com.wbl.utils.web.WBy;
import com.wbl.utils.web.WJavascriptExecutor;

/**
 * Created by svelupula on 8/14/2015.
 */
public class HomePage extends PortalPage {

    public HomePage(PageDriver driver) {
        super(driver);
        _logger.debug("Open Home Page");
        driver.findElement("header.home").click();
    }

    public int getSliderItemsCount() {
        return driver.findElements("home.slider.items").size();
    }
    


    public String getFacebookPage()
    {
        try{
	        driver.visibilityWait(WBy.get("header.social.fblink"));
	        WebDriver wd = driver.getWebDriver();
	        WebElement element = wd.findElement(WBy.get("header.social.fblink"));
	        WJavascriptExecutor js = new WJavascriptExecutor(wd,element);
	        js.executeScript("arguments[0].click();");
        }
        catch(Exception e)
        {
        	_logger.error(e);
        }
        return driver.getCurrentUrl();
    }


}
