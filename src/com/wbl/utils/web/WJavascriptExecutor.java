package com.wbl.utils.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
/**
 * Created by Alok on 10/7/2015.
 */
public class WJavascriptExecutor {

    private final PageDriver _browser;
    private final WebElement _element;
    private Logger _logger;
    JavascriptExecutor js;

    public WJavascriptExecutor(PageDriver browser, WebElement element) {
        _element = element;
        _browser = browser;
        _logger = Logger.getLogger(WJavascriptExecutor.class);
        js = (JavascriptExecutor) _browser;

    }

    public Object javaScriptExecute(String script, Object args){
        try {

            return   js.executeScript(script, args);
        }
        catch (Exception e) {
            _logger.error(e);
            e.printStackTrace();
        };
        return args;
    }
    //  Execute an asynchronous piece of JavaScript in the context of the currently selected frame or window.
    // Unlike executing synchronous JavaScript, scripts executed with this method must explicitly signal they are finished by invoking the provided callback.
    // This callback is always injected into the executed function as the last argument.
    public Object javaScriptAsyncExecute(String script, Object args){

        try {

            return   js.executeAsyncScript(script, args);
        }
        catch (Exception e) {
            _logger.error(e);
            e.printStackTrace();
        };
        return args;
    }

}
