package com.wbl.tests.ui;

import com.wbl.base.BaseWebTest;
import com.wbl.pages.HomePage;
import com.wbl.utils.web.Browsers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

/**
 * Created by svelupula on 8/14/2015.
 */
public class HomePageTest extends BaseWebTest {

    private HomePage _hp;

    @BeforeClass
    public void beforeClass() {
        _hp = new HomePage(driver);
    }

    @Test(priority = 1, alwaysRun = true)
    public void testSlidesCount() throws IOException{
    if(driver.getBrowser() != Browsers.HtmlUnit)
     driver.getwScreenshot().takeScreenShot(driver._configuration.TakeScreenShot, driver._configuration.ScreenFolderPath);
    assertEquals(6, _hp.getSliderItemsCount());
    if(driver.getBrowser() != Browsers.HtmlUnit)
     driver.getwScreenshot().takeScreenShot(driver._configuration.TakeScreenShot,driver._configuration.ScreenFolderPath);
}

    @Test(priority = 2)
    public void testFBLink()
    {
        boolean isFBPage = _hp.getFacebookPage();
        assertTrue(isFBPage);
    }



}
