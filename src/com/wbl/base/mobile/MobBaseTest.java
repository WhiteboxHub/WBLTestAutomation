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
/*
	public static final Configuration _config;

	static {
		_config = new Configuration("mob");
	}

	public MobBaseTest(PageDriver driver) {
		super(driver);
		_logger.debug("MobBaseTest");
	}

	@BeforeTest
	public void setUp() throws Exception {

		//driver.get_appiumDriver();

		//driver = UtilityClass.getDeviceDriver(_config.MobileOs, _config.Devicename);
		driver.winstallApp("D:/supriya/softwares/selendroid-test-app-0.16.0.apk");
				((AndroidDriver) driver._appiumDriver).startActivity("io.selendroid.testapp", ".HomeScreenActivity");
	}

	@AfterTest
	public void After1()
	{
		//((AndroidDriver)appiumDriver).removeApp("io.selendroid.testapp");
		driver.quit();
	}*/

