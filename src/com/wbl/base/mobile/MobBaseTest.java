package com.wbl.base.mobile;

import com.wbl.utils.web.PageDriver;
import com.wbl.utils.web.WBy;
import org.testng.annotations.*;


public abstract class MobBaseTest extends BaseTest {

	public PageDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		driver = new PageDriver(_config);
		WBy.loadJsonMap(String.format("%s/deviceLocators.json", System.getProperty("user.dir")));
	}

	@AfterSuite
	public void afterSuite() {

		driver.quit();
	}
}

