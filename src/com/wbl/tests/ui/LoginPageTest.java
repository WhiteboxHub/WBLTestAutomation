package com.wbl.tests.ui;

import com.wbl.base.BaseWebTest;
import com.wbl.pages.LoginPage;
import com.wbl.utils.web.Browsers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Shilpi on 9/24/2015.
 */
public class LoginPageTest extends BaseWebTest {

    private LoginPage _lp;
    private String cookieName;
    private String sheetName;

    @BeforeTest
    @Parameters({"seesionCookie","sheetName"})
    public void init(String cookieName,String sheetName)
    {
        _lp = new LoginPage(driver);
        this.cookieName = cookieName;
        this.sheetName = sheetName;
    }

    @DataProvider(name = "users-data")
    public Object[][] getUsers() throws Exception {
        Object[][] data = excelUtils.getSimpleExcelData(driver._configuration.DataFilePath,sheetName);
        return data;
    }

    @Test(priority = 1,dataProvider = "users-data")
    public void testLogin(String uname,String pwd)throws IOException
    {
        _lp.getLoginPage();
        boolean actual = _lp.perfromLogin(uname, pwd);
        if(driver.getBrowser() != Browsers.HtmlUnit)
        driver.getwScreenshot().takeScreenShot(driver._configuration.TakeScreenShot, driver._configuration.ScreenFolderPath);
        assertTrue(actual);
        if(driver.getBrowser() != Browsers.HtmlUnit)
        driver.getwScreenshot().takeScreenShot(driver._configuration.TakeScreenShot, driver._configuration.ScreenFolderPath);
    }

    @Test(dependsOnMethods = {"testLogin"})
    public void testLogout() throws InterruptedException,IOException
    {
        String preSessionId = _lp.getCookie(cookieName);
        String postSessionId = _lp.performLogout(cookieName);
        if(driver.getBrowser() != Browsers.HtmlUnit)
        driver.getwScreenshot().takeScreenShot(driver._configuration.TakeScreenShot, driver._configuration.ScreenFolderPath);
        assertNotEquals(preSessionId, postSessionId);
        if(driver.getBrowser() != Browsers.HtmlUnit)
        driver.getwScreenshot().takeScreenShot(driver._configuration.TakeScreenShot,driver._configuration.ScreenFolderPath);
    }

}
