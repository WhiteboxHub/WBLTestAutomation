package com.wbl.utils.web;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;

/**
 * Created by Innovapath on 10/19/2015.
 */
public class WwindowHandles {

    public void switchToWindow(WebDriver driver)
    {
        String newWindow = driver.getWindowHandle();
        driver.switchTo().window(newWindow);
    }



    public void windowHandles(WebDriver driver)
    {
        Iterator<String> handles = driver.getWindowHandles().iterator();
        while(handles.hasNext()){
            String child = handles.next();
            driver.switchTo().window(child);
        }
    }


}

