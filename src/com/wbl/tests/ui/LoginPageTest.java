package com.wbl.tests.ui;

import com.wbl.base.BaseWebTest;
import com.wbl.pages.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Shilpi on 9/24/2015.
 */
public class LoginPageTest extends BaseWebTest {

    private LoginPage _lp;
    private String cookieName;

    @BeforeTest
    @Parameters("seesionCookie")
    public void init(String cookieName)
    {
        _lp = new LoginPage(driver);
        this.cookieName = cookieName;
    }

    @DataProvider(name = "users-data")
    public Object[][] getUsers() throws Exception {
        Object[][] data = excelUtils.getSimpleExcelData(driver._configuration.FilePath);
        return data;
    }

    @Test(priority = 1,dataProvider = "users-data")
    public void testLogin(String uname,String pwd)
    {
        boolean actual = _lp.perfromLogin(uname, pwd);
        assertTrue(actual);
    }

    @Test(dependsOnMethods = {"testLogin"})
    public void testLogout() throws InterruptedException
    {
        String preSessionId = _lp.getCookie(cookieName);
        String postSessionId = _lp.performLogout(cookieName);
        assertNotEquals(preSessionId, postSessionId);
    }

}
