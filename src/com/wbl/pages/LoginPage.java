package com.wbl.pages;

import com.wbl.utils.web.HtmlElement;
import com.wbl.utils.web.PageDriver;
import com.wbl.utils.web.WBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by svelupula on 8/14/2015.
 */
public class LoginPage extends PortalPage {


    public LoginPage(PageDriver driver) {
        super(driver);
        driver.findElement("id=home.loginLink").click();
    }


    public String getCookie(String cookieName)
    {
        return driver.getCookie(cookieName);
    }

    public boolean perfromLogin(String userName,String password)
    {
        HtmlElement userId = driver.findElement("name=login.username");
        HtmlElement pwd =  driver.findElement("name=login.password");
        userId.clear();
        pwd.clear();
        userId.sendKeys(userName);
        pwd.sendKeys(password);
        driver.findElement("login.loginBtn").click();
      //  driver.implicitWait(120);
        boolean isVisible = driver.findElement("id=home.logoutLink").isDisplayed();
        return isVisible;

    }

    public String performLogout(String cookieName )
    {
        driver.findElement("id=home.logoutLink").click();
        return driver.getCookie(cookieName);
    }


}
