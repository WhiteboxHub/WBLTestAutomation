package com.wbl.utils.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * Created by Bharti on 10/7/2015.
 */
public class WJavascriptExecutor{

    private Logger _logger;
    private WebElement _element;
    private JavascriptExecutor js;

    public WJavascriptExecutor(WebDriver browser,WebElement element) {
        _logger = Logger.getLogger(WJavascriptExecutor.class);
        _element = element;
        js = (JavascriptExecutor) browser;

    }

	public Object executeScript(String paramString) {
		 try {

	            return   js.executeScript(paramString, _element);
	        }
	        catch (Exception e) {
	            _logger.error(e);
	            e.printStackTrace();
	        }
	        return null;
	}

	public Object executeAsyncScript(String paramString) {
		try {

            return   js.executeAsyncScript(paramString, _element);
        }
        catch (Exception e) {
            _logger.error(e);
            e.printStackTrace();
        }
        return null;
	}

}
