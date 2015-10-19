package com.wbl.utils.web;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;

/**
 * Created by Innovapath on 10/19/2015.
 */
public class WwindowHandles {

    WebDriver _webDriver;

    public WwindowHandles(WebDriver driver)
    {
        this._webDriver = driver;
    }

    public void switchToWindow()
    {
        String newWindow = _webDriver.getWindowHandle();
        _webDriver.switchTo().window(newWindow);
    }



    public void windowHandles()
    {
        Iterator<String> handles = _webDriver.getWindowHandles().iterator();
        while(handles.hasNext()){
            String child = handles.next();
            _webDriver.switchTo().window(child);
        }
    }


}

