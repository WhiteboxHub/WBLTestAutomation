package com.wbl.pages;

import com.wbl.utils.web.HtmlElement;
import com.wbl.utils.web.PageDriver;
import com.wbl.utils.web.WBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by svelupula on 8/14/2015.
 */
public class LoginPage extends PortalPage {


    public LoginPage(PageDriver driver) {
        super(driver);
    }

    public void getLoginPage()
    {
        driver.findElement("id=home.loginLink").click();
    }

    public String getCookie(String cookieName)
    {
        return driver.getCookie(cookieName);
    }

    public boolean perfromLogin(String userName,String password)
    {
        boolean isVisible = false;
        HtmlElement userId = driver.findElement("name=login.username");
        HtmlElement pwd =  driver.findElement("name=login.password");
        try {
            userId.clear();
            pwd.clear();
            userId.sendKeys(userName);
            pwd.sendKeys(password);
            driver.findElement("login.loginBtn").click();
            driver.waitForLoad();
            driver.elementClickWait(WBy.get("id=home.logoutLink"));
            isVisible = driver.findElement("id=home.logoutLink").isDisplayed();
        }
        catch (Exception e)
        {
            _logger.error(e);
        }
        return isVisible;

    }

    public String performLogout(String cookieName ) throws InterruptedException
    {
        driver.findElement("id=home.logoutLink").click();
        driver.waitForLoad();
        Thread.sleep(2000);
        return driver.getCookie(cookieName);
    }


}
