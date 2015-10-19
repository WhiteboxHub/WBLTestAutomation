package com.wbl.utils.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ${supriya} on 10/14/2015.
 */
public class UtilityClass {

	public static AppiumDriver getDeviceDriver(String OS,String DeviceName)
	{
		AppiumDriver appiumDriver=null;
		if(OS.equalsIgnoreCase("ANDROID"))
		{
			DesiredCapabilities options=new DesiredCapabilities();
			//options.setPlatform(Platform.ANDROID);
			options.setCapability(MobileCapabilityType.PLATFORM_VERSION,"4.4");
			options.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.ANDROID);
			options.setCapability(MobileCapabilityType.DEVICE_NAME,DeviceName);
			options.setCapability("appPackage", "io.selendroid.testapp");
			options.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");

			try {
				appiumDriver=new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else{

		}
		return appiumDriver;
	}
}
