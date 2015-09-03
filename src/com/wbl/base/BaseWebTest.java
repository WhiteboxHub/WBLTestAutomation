package com.wbl.base;

import com.wbl.utils.web.PageDriver;
import com.wbl.utils.web.WBy;
import org.testng.annotations.*;

/**
 * Created by svelupula on 8/14/2015.
 */
public abstract class BaseWebTest extends BaseTest {

    public PageDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        driver = new PageDriver(_config);
        WBy.loadJsonMap(String.format("%s/locators.json", System.getProperty("user.dir")));
    }

    @AfterSuite
    public void afterSuite() {
        driver.quit();
    }

}
